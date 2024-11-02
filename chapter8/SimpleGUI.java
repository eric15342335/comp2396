import javax.swing.*;

public class SimpleGUI {
	public static void main(String[] args) {  
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("click me");  
		frame.add(button);
		frame.setSize(300, 300);  
		frame.setVisible(true);
	}
}