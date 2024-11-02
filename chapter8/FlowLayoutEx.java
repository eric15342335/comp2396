import javax.swing.*;  
import java.awt.*;

public class FlowLayoutEx {
	public static void main(String[] args) {
		FlowLayoutEx gui = new FlowLayoutEx();
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);  
		frame.add(panel, BorderLayout.EAST);  
		frame.setSize(250, 200);  
		frame.setVisible(true);
	}

	/* public void go() {
		JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();  
		panel.setBackground(Color.DARK_GRAY);  
		JButton button = new JButton("shock me");  	
		panel.add(button);
		frame.add(panel, BorderLayout.EAST);
		frame.setSize(250, 200);  frame.setVisible(true);
	} */

	/* public void go() {
		JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();  
		panel.setBackground(Color.DARK_GRAY);  
		JButton button = new JButton("shock me"); 
		JButton button2 = new JButton("bliss");
		panel.add(button);
		panel.add(button2);
		frame.add(panel, BorderLayout.EAST);
		frame.setSize(250, 200);  frame.setVisible(true);
	} */

	/* public void go() {
		JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();  
		panel.setBackground(Color.DARK_GRAY);  
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  
		JButton button = new JButton("shock me");
		JButton button2 = new JButton("bliss");
		panel.add(button);  
		panel.add(button2);  
		frame.add(panel, BorderLayout.EAST);  
		frame.setSize(250, 200);  
		frame.setVisible(true);
	} */
}