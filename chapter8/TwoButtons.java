import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;

public class TwoButtons {  
	JFrame frame;
	JLabel label;

	public static void main(String[] args) {  
		TwoButtons gui = new TwoButtons(); 
		gui.go();
	}

	public void go() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton labelButton = new JButton("Change Label");  
		labelButton.addActionListener(new LabelListener());  
		JButton colorButton = new JButton("Change Circle");  
		colorButton.addActionListener(new ColorListener());
		label = new JLabel("I'm a label");  
		MyDrawPanel2 drawPanel = new MyDrawPanel2();
		frame.add(colorButton, BorderLayout.SOUTH);  
		frame.add(drawPanel, BorderLayout.CENTER);  
		frame.add(labelButton, BorderLayout.WEST);  
		frame.add(label, BorderLayout.EAST);
		frame.setSize(500, 300);  
		frame.setVisible(true);
	}

	class LabelListener implements ActionListener {  
		public void actionPerformed(ActionEvent event) {
			label.setText("Ouch!");
		}
	} // close inner class

	class ColorListener implements ActionListener {  
		public void actionPerformed(ActionEvent event) {
			frame.repaint();
		}
	} // close inner class
}