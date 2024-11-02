import javax.swing.*;  
import java.awt.*;

public class BorderLayoutEx {
	public static void main(String[] args) {  
		BorderLayoutEx gui = new BorderLayoutEx();  
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		JButton button = new JButton("click me");
		// JButton button = new JButton("click me you mean it");
		frame.add(button, BorderLayout.EAST);
		frame.setSize(200, 200);  
		frame.setVisible(true);
	}

	/* public void go() {
		JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		JButton button = new JButton("There is no spoon.");
		frame.add(button, BorderLayout.NORTH);
		frame.setSize(200, 200);  
		frame.setVisible(true);
	} */

	/* public void go() {
		JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("Click this!");  
		Font bigFont = new Font("serif", Font.BOLD, 28);  
		button.setFont(bigFont);
		frame.add(button, BorderLayout.NORTH);  
		frame.setSize(200, 200);
		frame.setVisible(true);
	} */

	/* public void go() {
		JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton east = new JButton("East");  
		JButton west = new JButton("West"); 
		JButton north = new JButton("North"); 
		JButton south = new JButton("South"); 
		JButton center = new JButton("Center");
		frame.add(east, BorderLayout.EAST);  
		frame.add(west, BorderLayout.WEST);  
		frame.add(north, BorderLayout.NORTH);  
		frame.add(south, BorderLayout.SOUTH);  
		frame.add(center, BorderLayout.CENTER);
		frame.setSize(300, 300);  
		frame.setVisible(true);
	} */

}