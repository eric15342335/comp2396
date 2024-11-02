import java.awt.*;  
import javax.swing.*;

class MyDrawPanel2 extends JPanel {
	public void paintComponent(Graphics g) {
		int red = (int) (Math.random() * 256);
		int green = (int) (Math.random() * 256);
		int blue = (int) (Math.random() * 256);
		g.setColor(new Color(red, green, blue)); 
		g.fillOval(70, 70, 100, 100);
	}
}