import java.awt.*;
import javax.swing.*;

public class MyDrawPanel extends JPanel {
	public void paintComponent(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(20, 50, 100, 70);
	}
}