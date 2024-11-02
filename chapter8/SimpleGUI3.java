import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;

public class SimpleGUI3 implements ActionListener {
	JFrame frame;

	public static void main(String[] args) {  
		SimpleGUI3 gui = new SimpleGUI3();  
		gui.go();
	}

	public void go() {  
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("Change colors");  
		button.addActionListener(this);
		MyDrawPanel2 drawPanel = new MyDrawPanel2();
		frame.add(button, BorderLayout.SOUTH);  
		frame.add(drawPanel, BorderLayout.CENTER);  
		frame.setSize(300, 300);  
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {  
		frame.repaint();
	}
}