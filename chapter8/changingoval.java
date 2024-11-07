import javax.swing.*; // for JFrame, JPanel
import java.awt.*; // for Color
import java.awt.event.*; // for MouseListener

public class changingoval {
    /// random returns a Color object with random values
    public Color randomColor() {
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        Color randomColor = new Color(red, green, blue);
        return randomColor;
    }
    int x, y = 70;
    // required, draw panel is a separate new class to implement
    class MyDrawPanel extends JPanel {
        /// need to implement drawing details inside paintComponent()
        /// as specified in the API
        public void paintComponent(Graphics g) {
            // required to call super class paintComponent
            // to prevent re-drawing the button on the top
            super.paintComponent(g);
            // the object g is actually Graphics2D
            Graphics2D g2d = (Graphics2D) g;
            // requires Graphics2D object to use GradientPaint
            // Graphics does not support it
            g2d.clearRect(0,0, this.getWidth(), this.getHeight());
            GradientPaint paint = new GradientPaint(70, 70, randomColor(), 150, 150, randomColor());
            g2d.setPaint(paint);
            g2d.fillOval(x-70, y-70, 140, 140);
        }
    }
    MyDrawPanel drawPanel = new MyDrawPanel();
    public static void main(String[] args) {
        changingoval gui = new changingoval();
        gui.go();
    }
    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawPanel);

        JButton button = new JButton("Change color");
        frame.add(button, BorderLayout.SOUTH);

        frame.addMouseListener(new clickingListener());

        frame.setSize(300, 300);
        frame.setVisible(true);

        while (true) {
            // circle follows the mouse
            //get the mouse position
            Point p = frame.getMousePosition();
            if (p != null) {
                x = (int) p.getX();
                y = (int) p.getY();
            }
            drawPanel.repaint();
            try {
                Thread.sleep(50);
            } catch (Exception ex) { }
        }
    }

    class clickingListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            // panel.repaint() will call paintComponent() again
            // automatically
            drawPanel.repaint();
        }
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
    }
}
