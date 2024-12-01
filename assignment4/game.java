import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.*;

/**
 * A simple Tic Tac Toe game with GUI.
 * COMP2396 Assignment 4
 * @author @eric15342335 Cheng Ho Ming, Eric (3036216734)
 */
public class game {
    JFrame frame;
    JLabel welcomeLabel;
    JTextField playerNameField;
    JButton submitButton;
    JPanel gameBoard;
    JPanel scorePanel;
    JLabel playerScore, computerScore, drawScore;
    enum State {
        EMPTY, PLAYER, COMPUTER
    }
    static State[][] board = new State[3][3];
    int board_filled = 0;
    /**
     * Main method to start the game.
     * Initialize the game board and GUI.
     * @param args Does nothing
     */
    public static void main(String[] args) {
        game gui = new game();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = State.EMPTY;
            }
        }
        gui.go();
    }
    /**
     * Initialize the GUI.
     * Set up the game board, player name input, and score display.
     * @param void
     */
    public void go() {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // top display
        // "Enter your player name..." and "WELCOME {NAME}"
        JPanel topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.NORTH);
        welcomeLabel = new JLabel("Enter your player name...");
        topPanel.add(welcomeLabel);
        // bottom display with name input and time
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        frame.add(bottomPanel, BorderLayout.SOUTH);
        // player name input: holds text, textfield, and submit button
        JPanel playerNameInputElements = new JPanel();
        bottomPanel.add(playerNameInputElements);
        playerNameInputElements.setLayout(new GridBagLayout());
        // text
        JLabel playerNameLabel = new JLabel("Enter your name: ");
        playerNameInputElements.add(playerNameLabel);
        // text field for input
        playerNameField = new JTextField(20);
        playerNameInputElements.add(playerNameField);
        // submit button
        submitButton = new JButton("Submit");
        playerNameInputElements.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String input = playerNameField.getText();
                welcomeLabel.setText("WELCOME " + input.toUpperCase());
                frame.setTitle("Tic Tac Toe - Player: " + input);
                // disable
                playerNameField.setEnabled(false);
                submitButton.setEnabled(false);
                // enable all gameboard buttons
                Component[] components = gameBoard.getComponents();
                for (Component component : components) {
                    component.setEnabled(true);
                }
            }
        }); 
        // time display
        // get current system time 24 hr format
        JLabel timeLabel = new JLabel(getLocalTime());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        bottomPanel.add(timeLabel, c);
        // update time every second
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText(getLocalTime());
            }
        });
        timer.start();
        // menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        // menu: control
        JMenu controlMenu = new JMenu("Control");
        menuBar.add(controlMenu);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        controlMenu.add(exit);
        // menu: help
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        JMenuItem instruction = new JMenuItem("Instruction");
        helpMenu.add(instruction);
        instruction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            Object[] options = {"Yes"};
            JOptionPane.showOptionDialog(frame, "Some information about the game:\n"
                + "- The move is not occupied by any mark.\n"
                + "- The move is made in the player's turn.\n"
                + "- The move is made within the 3 x 3 board.\n"
                + "The game would continue and switch among the player and the computer until it reaches either one of the following conditions:\n"
                + "- Player wins.\n"
                + "- Computer wins.\n"
                + "- Draw.", "Instructions", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            }
        });
        // the 3x3 game board
        // is actually buttons?
        gameBoard = new JPanel();
        frame.add(gameBoard, BorderLayout.CENTER);
        gameBoard.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setText(" ");
            // white background
            button.setBackground(Color.WHITE);
            // no effect when clicked
            button.setFocusPainted(false);
            // disable at beginning
            button.setEnabled(false);
            gameBoard.add(button);
            // actual game logic handling
            final int const_i = i;
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    // if button is not empty, do nothing
                    if (button.getText().equals("X") || button.getText().equals("O")) {
                        return;
                    }
                    // player's turn
                    button.setText("X");
                    // set bigger text and green
                    button.setFont(new Font("Arial", Font.BOLD, 40));
                    button.setForeground(Color.GREEN);
                    board[const_i / 3][const_i % 3] = State.PLAYER;
                    board_filled++;
                    // computer's turn
                    welcomeLabel.setText("Valid move, waiting for your opponent.");
                    // check if game is over
                    if (endGameCheck() != END_GAME_STATUS.CONTINUE) {
                        return;
                    }
                    Component[] components = gameBoard.getComponents();
                    // disable all buttons
                    for (Component component : components) {
                        JButton button = (JButton)component;
                        button.setEnabled(false);
                        // set disabled text color to original color
                        button.setUI(new MetalButtonUI() {
                            protected Color getDisabledTextColor() {
                                return button.getForeground();
                            }
                        });
                    }
                    // wait 2 seconds using timer
                    Timer timer = new Timer(2000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // find an empty spot by random
                            int x = (int)(Math.random() * 3);
                            int y = (int)(Math.random() * 3);
                            while (board[x][y] != State.EMPTY && board_filled < 9) {
                                x = (int)(Math.random() * 3);
                                y = (int)(Math.random() * 3);
                            }
                            board[x][y] = State.COMPUTER;
                            board_filled++;
                            // update the button
                            int index = x * 3 + y;
                            JButton computerButton = (JButton)components[index];
                            computerButton.setText("O");
                            computerButton.setFont(new Font("Arial", Font.BOLD, 40));
                            computerButton.setForeground(Color.RED);
                            // enable all buttons
                            for (Component component : components) {
                                component.setEnabled(true);
                            }
                            // update top message
                            welcomeLabel.setText("Your opponent has moved, now is your turn.");
                            // check if game is over
                            endGameCheck();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            });
        }
        // score display on the right
        scorePanel = new JPanel();
        scorePanel.setBorder(BorderFactory.createTitledBorder("Score"));
        frame.add(scorePanel, BorderLayout.EAST);
        // labels inside the score panel
        scorePanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.anchor = GridBagConstraints.WEST;
        c2.ipadx = 30;
        c2.ipady = 80;
        // player score
        JLabel playerScoreText = new JLabel("Player Wins:");
        c2.gridy = 0;
        scorePanel.add(playerScoreText, c2);
        playerScore = new JLabel("0");
        c2.gridx = 1;
        scorePanel.add(playerScore, c2);
        // computer score
        JLabel computerScoreText = new JLabel("Computer Wins:");
        c2.gridx = 0;
        c2.gridy = 1;
        scorePanel.add(computerScoreText, c2);
        computerScore = new JLabel("0");
        c2.gridx = 1;
        scorePanel.add(computerScore, c2);
        // draw score
        JLabel drawScoreText = new JLabel("Draws:");
        c2.gridx = 0;
        c2.gridy = 2;
        scorePanel.add(drawScoreText, c2); 
        drawScore = new JLabel("0");
        c2.gridx = 1;
        scorePanel.add(drawScore, c2);
        // launch the gui
        frame.setSize(500,500);
        frame.setVisible(true);
    }
    /** 
     * Get the current system time in 24 hour format.
     * Truncate the miliseconds precision.
     * @return String of the current time in HH:mm:ss format, e.g. "Current Time: 12:34:56"
    */
    private String getLocalTime() {
        return "Current Time: " + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    enum END_GAME_STATUS {
        PLAYER_WIN, COMPUTER_WIN, DRAW, CONTINUE
    }
    /**
     * Responsible for popping a dialog box when the game ends.
     * Also updates the score display.
     * Otherwise, return END_GAME_STATUS.CONTINUE.
     * @return END_GAME_STATUS.PLAYER_WIN if player wins, END_GAME_STATUS.COMPUTER_WIN if computer wins, END_GAME_STATUS.DRAW if draw, END_GAME_STATUS.CONTINUE if game continues
     */
    private END_GAME_STATUS endGameCheck() {
        END_GAME_STATUS status = checkWin();
        String message_delivered = "";
        if (status == END_GAME_STATUS.PLAYER_WIN) {
            message_delivered = "Player wins!";
            playerScore.setText(Integer.parseInt(playerScore.getText()) + 1 + "");
        } else if (status == END_GAME_STATUS.COMPUTER_WIN) {
            message_delivered = "Computer wins!";
            computerScore.setText(Integer.parseInt(computerScore.getText()) + 1 + "");
        } else if (status == END_GAME_STATUS.DRAW) {
            message_delivered = "It's a draw!";
            drawScore.setText(Integer.parseInt(drawScore.getText()) + 1 + "");
        } else {
            return END_GAME_STATUS.CONTINUE;
        }
        Object[] options = {"Yes"};
        JOptionPane.showOptionDialog(frame, message_delivered, "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        // reset the game
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = State.EMPTY;
            }
        }
        board_filled = 0;
        Component[] components = gameBoard.getComponents();
        for (Component component : components) {
            JButton button = (JButton)component;
            button.setText(" ");
        }
        return status;
    }
    /**
     * Check if the game is over by looking at the board[][] array.
     * @return END_GAME_STATUS.PLAYER_WIN if player wins, END_GAME_STATUS.COMPUTER_WIN if computer wins, END_GAME_STATUS.DRAW if draw, END_GAME_STATUS.CONTINUE if game continues
     */
    private END_GAME_STATUS checkWin() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == State.PLAYER && board[i][1] == State.PLAYER && board[i][2] == State.PLAYER) {
                return END_GAME_STATUS.PLAYER_WIN;
            }
            if (board[i][0] == State.COMPUTER && board[i][1] == State.COMPUTER && board[i][2] == State.COMPUTER) {
                return END_GAME_STATUS.COMPUTER_WIN;
            }
        }
        // check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == State.PLAYER && board[1][i] == State.PLAYER && board[2][i] == State.PLAYER) {
                return END_GAME_STATUS.PLAYER_WIN;
            }
            if (board[0][i] == State.COMPUTER && board[1][i] == State.COMPUTER && board[2][i] == State.COMPUTER) {
                return END_GAME_STATUS.COMPUTER_WIN;
            }
        }
        // check diagonals
        if (board[0][0] == State.PLAYER && board[1][1] == State.PLAYER && board[2][2] == State.PLAYER) {
            return END_GAME_STATUS.PLAYER_WIN;
        }
        if (board[0][0] == State.COMPUTER && board[1][1] == State.COMPUTER && board[2][2] == State.COMPUTER) {
            return END_GAME_STATUS.COMPUTER_WIN;
        }
        if (board[0][2] == State.PLAYER && board[1][1] == State.PLAYER && board[2][0] == State.PLAYER) {
            return END_GAME_STATUS.PLAYER_WIN;
        }
        if (board[0][2] == State.COMPUTER && board[1][1] == State.COMPUTER && board[2][0] == State.COMPUTER) {
            return END_GAME_STATUS.COMPUTER_WIN;
        }
        // check draw
        if (board_filled == 9) {
            return END_GAME_STATUS.DRAW;
        }
        return END_GAME_STATUS.CONTINUE;
    }
}
