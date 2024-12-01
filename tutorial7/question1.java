import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class question1 {
    // put drawing panel at this scope since
    // listeners need modify it
    MyDrawPanel drawPanel = new MyDrawPanel();
    // also the coordinates of the rectangle
    int x = 95;
    int y = 70;
    // and size
    int size_x = 100;
    int size_y = 100;
    // put frame here because we want to repaint frame as a whole in listeners
    JFrame frame = new JFrame();
    public static void main(String[] args) {
        question1 gui = new question1();
        gui.go();
    }
    public void go() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // putting all buttons together
        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.NORTH);
        // all buttons
        JButton left = new JButton("left");
        JButton right = new JButton("right");
        JButton smaller = new JButton("smaller");
        JButton bigger = new JButton("Bigger");
        panel.add(left);
        panel.add(right);
        panel.add(smaller);
        panel.add(bigger);
        // adding listeners for buttons
        left.addActionListener(new LeftListener());
        right.addActionListener(new RightListener());
        smaller.addActionListener(new SmallerListener());
        bigger.addActionListener(new BiggerListener());
        // drawing panel
        frame.add(drawPanel);
        // preparing to launch
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
    class LeftListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Timer timer = new Timer(0, new ActionListener() {
                int count = 0;
                public void actionPerformed(ActionEvent e) {
                    if (count < 20) {
                        x--;
                        drawPanel.repaint();
                        count++;
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();
        }
    }
    class RightListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Timer timer = new Timer(0, new ActionListener() {
                int count = 0;
                public void actionPerformed(ActionEvent e) {
                    if (count < 20) {
                        x++;
                        drawPanel.repaint();
                        count++;
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();
        }
    }
    class SmallerListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Timer timer = new Timer(0, new ActionListener() {
                int count = 0;
                public void actionPerformed(ActionEvent e) {
                    if (count < 20) {
                        size_x -= 1;
                        size_y -= 1;
                        drawPanel.repaint();
                        count++;
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();
        }
    }
    class BiggerListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Timer timer = new Timer(0, new ActionListener() {
                int count = 0;
                public void actionPerformed(ActionEvent e) {
                    if (count < 20) {
                        size_x += 1;
                        size_y += 1;
                        drawPanel.repaint();
                        count++;
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();
        }
    }
    class MyDrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            int red = 100;
            int green = 100;
            int blue = 100;
            g.clearRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(new Color(red, green, blue));
            g.fillRect(x, y, size_x, size_y);
        }
    }
}
