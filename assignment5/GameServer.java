import java.io.*;
import java.net.*;

/**
 * Tic-Tac-Toe Server to handle two-player games over the network.
 * COMP2396 Assignment 5
 * @author Cheng Ho Ming, Eric (3036216734)
 */
public class GameServer {
    private static final int PORT = 8901;
    private static PlayerHandler playerX;
    private static PlayerHandler playerO;
    private static char[][] board = new char[3][3];
    private static boolean gameEnded = false;

    /**
     * Main method to run the server.
     * @param args Command-line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("Tic-Tac-Toe Server is Running...");
        try {
            while (true) {
                Socket socket = listener.accept();
                if (playerX == null) {
                    playerX = new PlayerHandler(socket, 'X');
                    System.out.println("Player X connected.");
                    Thread playerX_Thread = new Thread(playerX);
                    playerX_Thread.start();
                } else if (playerO == null) {
                    playerO = new PlayerHandler(socket, 'O');
                    System.out.println("Player O connected.");
                    Thread playerO_Thread = new Thread(playerO);
                    playerO_Thread.start();
                } else {
                    // Reject additional players to prevent overload
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("SERVERFULL");
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }
    }

    /**
     * Make a move on the board.
     * @param row The row of the move
     * @param col The column of the move
     * @param symbol The player's symbol ('X' or 'O')
     * @return True if the move is valid, false otherwise
     */
    private synchronized static boolean makeMove(int row, int col, char symbol) {
        if (board[row][col] == '\0') {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    /**
     * Check if a player has won the game.
     * @return The winning player's symbol ('X' or 'O'), or 'D' for draw, or '\0' if no winner
     */
    private synchronized static char checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] &&
                board[i][1] == board[i][2] &&
                board[i][0] != '\0') {
                return board[i][0];
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] &&
                board[1][i] == board[2][i] &&
                board[0][i] != '\0') {
                return board[0][i];
            }
        }
        // Check diagonals
        if (board[0][0] == board[1][1] &&
            board[1][1] == board[2][2] &&
            board[0][0] != '\0') {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] &&
            board[1][1] == board[2][0] &&
            board[0][2] != '\0') {
            return board[0][2];
        }
        // Check for draw
        boolean draw = true;
        for (int i = 0; i < 3 && draw; i++) {
            for (int j = 0; j < 3 && draw; j++) {
                if (board[i][j] == '\0') {
                    draw = false;
                }
            }
        }
        if (draw) return 'D';
        return '\0';
    }

    /**
     * Internal class to handle player connections.
     */
    private static class PlayerHandler implements Runnable {
        private Socket socket;
        private char symbol; // 'X' or 'O'
        private BufferedReader in;
        private PrintWriter out;
        private String playerName;
        private boolean nameSubmitted = false;

        /**
         * Constructor for the player handler.  
         * @param socket The player's socket object
         * @param symbol The player's symbol ('X' or 'O')
         */
        public PlayerHandler(Socket socket, char symbol) {
            this.socket = socket;
            this.symbol = symbol;
        }

        /**
         * Run the player handler.
         * Handle player connections and messages.
         * Handle game logic.
         */
        public void run() {
            try {
                in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // First, get the player's name
                out.println("SUBMITNAME");
                playerName = in.readLine();
                if (playerName == null || playerName.trim().isEmpty()) {
                    out.println("INVALIDNAME");
                    socket.close();
                    return;
                }
                nameSubmitted = true;
                out.println("NAMEACCEPTED " + playerName);
                System.out.println("Player " + symbol + ": " + playerName + " has joined.");

                // Check if both players have submitted their names
                if (playerX != null && playerX.nameSubmitted &&
                    playerO != null && playerO.nameSubmitted) {
                    initializeGame();
                }

                // Handle messages from the player
                String command;
                while ((command = in.readLine()) != null) {
                    if (command.startsWith("MOVE")) {
                        String[] parts = command.split(" ");
                        if (parts.length != 3) {
                            out.println("INVALID");
                            continue;
                        }
                        int row, col;
                        try {
                            row = Integer.parseInt(parts[1]);
                            col = Integer.parseInt(parts[2]);
                        } catch (NumberFormatException e) {
                            out.println("INVALID");
                            continue;
                        }
                        synchronized(GameServer.class) {
                            if ((symbol == 'X' && this == playerX) ||
                                (symbol == 'O' && this == playerO)) {
                                boolean valid = makeMove(row, col, symbol);
                                if (valid) {
                                    broadcast("MOVE " + symbol + " " + row + " " + col);
                                    char result = checkWin();
                                    if (result == symbol) {
                                        out.println("VICTORY");
                                        getOpponent().out.println("DEFEAT");
                                        gameEnded = true;
                                    } else if (result == 'D') {
                                        broadcast("TIE");
                                        gameEnded = true;
                                    } else {
                                        // Switch turns
                                        getOpponent().out.println("YOURMOVE");
                                        this.out.println("WAIT");
                                    }
                                } else {
                                    out.println("INVALID");
                                }
                            }
                        }
                    } else if (command.startsWith("RESET")) {
                        synchronized(GameServer.class) {
                            if (gameEnded) {
                                resetGame();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error handling player " + symbol + ": " + e);
            } finally {
                try { socket.close(); } catch (IOException e) {}
                synchronized(GameServer.class) {
                    if (symbol == 'X') playerX = null;
                    else playerO = null;
                    if (!gameEnded) {
                        broadcast("OTHERLEFT");
                        // resetGame();
                    }
                }
            }
        }

        /**
         * Initialize the game.
         */
        private void initializeGame() {
            broadcast("GAMESTART " + playerX.playerName + " " + playerO.playerName);
            playerX.out.println("YOURMOVE");
            playerO.out.println("WAIT");
            System.out.println("Game started between " + playerX.playerName + " (X) and " + playerO.playerName + " (O).");
        }

        /**
         * Reset the game state.
         */
        private void resetGame() {
            board = new char[3][3];
            gameEnded = false;
            playerX.out.println("RESET");
            playerO.out.println("RESET");
            playerX.out.println("YOURMOVE");
            playerO.out.println("WAIT");
            System.out.println("Game has been reset.");
        }

        /**
         * Get the opponent's handler.
         * @return The opponent's handler
         */
        private PlayerHandler getOpponent() {
            return (this == playerX) ? playerO : playerX;
        }

        /**
         * Broadcast a message to all players.
         * @param message The message to broadcast
         */
        private void broadcast(String message) {
            if (playerX != null) playerX.out.println(message);
            if (playerO != null) playerO.out.println(message);
        }
    }
}