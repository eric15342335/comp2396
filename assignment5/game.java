import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A two-player Tic Tac Toe game with GUI over the network.
 * COMP2396 Assignment 5
 * @author Cheng Ho Ming, Eric (3036216734)
 */
public class game {
    JFrame frame;
    JLabel welcomeLabel;
    JTextField playerNameField;
    JButton submitButton;
    JPanel gameBoard;
    JPanel scorePanel;
    JLabel player1Score, player2Score, drawScore;
    enum State {
        EMPTY, PLAYER, OPPONENT
    }
    static char[][] board = new char[3][3];
    int board_filled = 0;

    // Networking components
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String playerName;
    char mySymbol;
    String opponentName;
    AtomicBoolean myTurn = new AtomicBoolean(false);

    /**
     * Main method to start the game.
     * Initialize the game board and GUI.
     * @param args Does nothing
     */
    public static void main(String[] args) {
        game gui = new game();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.go();
            }
        });
    }

    /**
     * Initialize the GUI and establish connection to server.
     */
    public void go() {
        // Initialize empty board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '\0';
            }
        }

        // Setup GUI
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Top display
        JPanel topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.NORTH);
        welcomeLabel = new JLabel("Enter your player name...");
        topPanel.add(welcomeLabel);

        // Bottom display with name input and time
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Player name input: holds text, textfield, and submit button
        JPanel playerNameInputElements = new JPanel();
        bottomPanel.add(playerNameInputElements);
        playerNameInputElements.setLayout(new GridBagLayout());

        // Text
        JLabel playerNameLabel = new JLabel("Enter your name: ");
        playerNameInputElements.add(playerNameLabel);

        // Text field for input
        playerNameField = new JTextField(20);
        playerNameInputElements.add(playerNameField);

        // Submit button
        submitButton = new JButton("Submit");
        playerNameInputElements.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                playerName = playerNameField.getText().trim();
                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Name cannot be empty.");
                    return;
                }
                submitName();
            }
        });

        // Time display
        JLabel timeLabel = new JLabel(getLocalTime());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        bottomPanel.add(timeLabel, c);

        // Update time every second
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText(getLocalTime());
            }
        });
        timer.start();

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Menu: Control
        JMenu controlMenu = new JMenu("Control");
        menuBar.add(controlMenu);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                closeConnection();
                System.exit(0);
            }
        });
        controlMenu.add(exit);

        // Menu: Help
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        JMenuItem instruction = new JMenuItem("Instruction");
        helpMenu.add(instruction);
        instruction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Object[] options = {"Okay"};
                JOptionPane.showOptionDialog(frame, "Some information about the game:\n\n"
                    + "Criteria for a valid move:\n"
                    + "- The move is not occupied by any mark.\n"
                    + "- The move is made in the player's turn.\n"
                    + "- The move is made within the 3 x 3 board.\n"
                    + "\n"
                    + "The game would continue and switch among the opposite player until it reaches either one of the following conditions:\n"
                    + "- Player 1 wins.\n"
                    + "- Player 2 wins.\n"
                    + "- Draw.\n"
                    + "- One of the players leaves the game.", "Game Information", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            }
        });

        // The 3x3 game board
        gameBoard = new JPanel();
        frame.add(gameBoard, BorderLayout.CENTER);
        gameBoard.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setText(" ");
            // White background
            button.setBackground(Color.WHITE);
            // Disable at beginning
            button.setEnabled(false);
            gameBoard.add(button);
            // Actual game logic handling
            final int const_i = i;
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (!myTurn.get()) {
                        JOptionPane.showMessageDialog(frame, "Not your turn!");
                        return;
                    }
                    if (!button.getText().equals(" ")) {
                        JOptionPane.showMessageDialog(frame, "Cell already occupied!");
                        return;
                    }
                    // Send move to server
                    int row = const_i / 3;
                    int col = const_i % 3;
                    out.println("MOVE " + row + " " + col);
                }
            });
        }

        // Score display on the right
        scorePanel = new JPanel();
        scorePanel.setBorder(BorderFactory.createTitledBorder("Score"));
        frame.add(scorePanel, BorderLayout.EAST);
        // Labels inside the score panel
        scorePanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.anchor = GridBagConstraints.WEST;
        c2.insets = new Insets(5,5,5,5);

        // Player score
        JLabel player1ScoreText = new JLabel("Player 1 Wins:");
        c2.gridy = 0;
        c2.gridx = 0;
        scorePanel.add(player1ScoreText, c2);
        player1Score = new JLabel("0");
        c2.gridx = 1;
        scorePanel.add(player1Score, c2);

        // Opponent score
        JLabel player2ScoreText = new JLabel("Player 2 Wins:");
        c2.gridx = 0;
        c2.gridy = 1;
        scorePanel.add(player2ScoreText, c2);
        player2Score = new JLabel("0");
        c2.gridx = 1;
        scorePanel.add(player2Score, c2);

        // Draw score
        JLabel drawScoreText = new JLabel("Draws:");
        c2.gridx = 0;
        c2.gridy = 2;
        scorePanel.add(drawScoreText, c2); 
        drawScore = new JLabel("0");
        c2.gridx = 1;
        scorePanel.add(drawScore, c2);

        // Frame settings
        frame.setSize(600,600);
        frame.setVisible(true);

        // Initialize networking
        initializeConnection();
    }

    /**
     * Establish connection to the server.
     */
    private void initializeConnection() {
        try {
            // Connect to the server on localhost
            socket = new Socket("127.0.0.1", 8901);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Start a thread to listen for messages from the server
            Thread listener = new Thread(new Runnable() {
                public void run() {
                    try {
                        String response;
                        while ((response = in.readLine()) != null) {
                            if (response.startsWith("SUBMITNAME")) {
                                // Server requests player to submit name, no action needed here
                            } else if (response.startsWith("INVALIDNAME")) {
                                // Invalid name, prompt user
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        JOptionPane.showMessageDialog(frame, "Invalid name submitted. Connection closing.");
                                        closeConnection();
                                        System.exit(0);
                                    }
                                });
                            } else if (response.startsWith("NAMEACCEPTED")) {
                                // Name accepted, wait for both players to join
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        welcomeLabel.setText("WELCOME " + playerName);
                                        frame.setTitle("Tic Tac Toe - Player: " + playerName.toUpperCase());
                                    }
                                });
                            } else if (response.startsWith("GAMESTART")) {
                                String[] parts = response.split(" ");
                                if (parts.length >= 3) {
                                    String playerXName = parts[1];
                                    String playerOName = parts[2];
                                    if (mySymbol == '\0') { // First time receiving GAMESTART
                                        if (playerXName.equals(playerName)) {
                                            mySymbol = 'X';
                                            opponentName = playerOName;
                                        } else {
                                            mySymbol = 'O';
                                            opponentName = playerXName;
                                        }
                                        if (mySymbol == 'X') {
                                            welcomeLabel.setText("Game started. Now is your turn.");
                                            myTurn.set(true);
                                            enableGameBoard(true);
                                        } else {
                                            welcomeLabel.setText("WELCOME " + playerName);
                                            myTurn.set(false);
                                            enableGameBoard(false);
                                        }
                                    }
                                }
                            } else if (response.startsWith("MOVE")) {
                                String[] parts = response.split(" ");
                                if (parts.length != 4) continue;
                                char symbol = parts[1].charAt(0);
                                int row = Integer.parseInt(parts[2]);
                                int col = Integer.parseInt(parts[3]);
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        updateBoard(symbol, row, col);
                                        if (symbol != mySymbol) {
                                            welcomeLabel.setText("Your opponent has moved. Now is your turn.");
                                            myTurn.set(true);
                                            enableGameBoard(true);
                                        } else {
                                            welcomeLabel.setText("Valid move, wait for your opponent.");
                                            myTurn.set(false);
                                            enableGameBoard(false);
                                        }
                                    }
                                });
                            } else if (response.startsWith("VICTORY")) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        int option = JOptionPane.showConfirmDialog(frame, "Congratulations. You wins! Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                                        if (option == JOptionPane.YES_OPTION) {
                                            out.println("RESET");
                                        } else {
                                            closeConnection();
                                            System.exit(0);
                                        }
                                        if (mySymbol == 'X') {
                                            player1Score.setText(Integer.parseInt(player1Score.getText()) + 1 + "");
                                        } else {
                                            player2Score.setText(Integer.parseInt(player2Score.getText()) + 1 + "");
                                        }
                                    }
                                });
                            } else if (response.startsWith("DEFEAT")) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        int option = JOptionPane.showConfirmDialog(frame, "You Lose! Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                                        if (option == JOptionPane.YES_OPTION) {
                                            out.println("RESET");
                                        } else {
                                            closeConnection();
                                            System.exit(0);
                                        }
                                        if (mySymbol == 'X') {
                                            player2Score.setText(Integer.parseInt(player2Score.getText()) + 1 + "");
                                        } else {
                                            player1Score.setText(Integer.parseInt(player1Score.getText()) + 1 + "");
                                        }
                                    }
                                });
                            } else if (response.startsWith("TIE")) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        int option = JOptionPane.showConfirmDialog(frame, "It's a Draw! Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                                        if (option == JOptionPane.YES_OPTION) {
                                            out.println("RESET");
                                        } else {
                                            closeConnection();
                                            System.exit(0);
                                        }
                                        drawScore.setText(Integer.parseInt(drawScore.getText()) + 1 + "");
                                    }
                                });
                            } else if (response.startsWith("YOURMOVE")) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        welcomeLabel.setText("Your opponent has moved. Now is your turn.");
                                        myTurn.set(true);
                                        enableGameBoard(true);
                                    }
                                });
                            } else if (response.startsWith("WAIT")) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        welcomeLabel.setText("Valid move, wait for your opponent.");
                                        myTurn.set(false);
                                        enableGameBoard(false);
                                    }
                                });
                            } else if (response.startsWith("MESSAGE")) {
                                String msg = response.substring(8);
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        welcomeLabel.setText(msg);
                                    }
                                });
                            } else if (response.startsWith("INVALID")) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        JOptionPane.showMessageDialog(frame, "Invalid Move!");
                                    }
                                });
                            } else if (response.startsWith("OTHERLEFT")) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        JOptionPane.showMessageDialog(frame, "Game Ends. One of the players left.");
                                        welcomeLabel.setText("Game started. Wait for your opponent.");
                                        resetBoard();
                                        enableGameBoard(false);
                                    }
                                });
                            } else if (response.startsWith("RESET")) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        resetBoard();
                                    }
                                });
                            } else if (response.startsWith("SERVERFULL")) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        JOptionPane.showMessageDialog(frame, "Server is full. Try again later.");
                                        closeConnection();
                                        System.exit(0);
                                    }
                                });
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Connection lost.");
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                JOptionPane.showMessageDialog(frame, "Connection to server lost.");
                                resetBoard();
                            }
                        });
                    }
                }
            });
            listener.start();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Cannot connect to the server.");
            System.exit(0);
        }
    }

    /**
     * Submit the player's name to the server.
     */
    private void submitName() {
        out.println(playerName);
        // Update GUI
        welcomeLabel.setText("Waiting for another player to join...");
        playerNameField.setEnabled(false);
        submitButton.setEnabled(false);
    }

    /**
     * Update the board with the move.
     * @param symbol The symbol to be placed on the board
     * @param row The row of the move
     * @param col The column of the move
     */
    private void updateBoard(char symbol, int row, int col) {
        board[row][col] = symbol;
        int index = row * 3 + col;
        JButton button = (JButton)gameBoard.getComponent(index);
        button.setText(String.valueOf(symbol));
        button.setFont(new Font("Arial", Font.BOLD, 40));
        if (symbol == 'X') {
            button.setForeground(Color.GREEN);
        } else {
            button.setForeground(Color.RED);
        }
    }

    /**
     * Reset the board to start a new game or due to opponent leaving.
     */
    private void resetBoard() {
        board_filled = 0;
        for (int i = 0; i < 3; i++) {
            for (int j=0; j <3; j++) {
                board[i][j] = '\0';
            }
        }
        Component[] components = gameBoard.getComponents();
        for (Component component : components) {
            JButton button = (JButton)component;
            button.setText(" ");
            button.setEnabled(false);
            button.setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return button.getForeground();
                }
            });
        }
        if (mySymbol == 'X') {
            welcomeLabel.setText("WELCOME " + playerName + ". Your move.");
            myTurn.set(true);
            enableGameBoard(true);
        } else if (mySymbol == 'O') {
            welcomeLabel.setText("WELCOME " + playerName + ". Wait for opponent's move.");
            myTurn.set(false);
            enableGameBoard(false);
        } else {
            // No symbol, possibly due to unsuccessful connection
            welcomeLabel.setText("Enter your player name...");
        }
    }

    /**
     * Enable or disable the game board buttons based on the player's turn.
     * @param enable True to enable, false to disable
     */
    private void enableGameBoard(boolean enable) {
        Component[] components = gameBoard.getComponents();
        for (Component component : components) {
            JButton button = (JButton)component;
            button.setEnabled(false);
            if (button.getText().equals(" ")) {
                button.setEnabled(enable);
            }
            button.setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return button.getForeground();
                }
            });
            button.setFocusPainted(false);
        }
    }

    /** 
     * Get the current system time in 24-hour format without milliseconds.
     * @return String representation of current time, e.g., "Current Time: 12:34:56"
    */
    private String getLocalTime() {
        return "Current Time: " + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * Close the connection to the server.
     */
    private void closeConnection() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            // Ignore exceptions
        }
    }
}