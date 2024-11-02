import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;

public class TextAreaEx implements ActionListener {  
	JTextArea text;
	
	public static void main(String[] args) {  
		TextAreaEx gui = new TextAreaEx();  
		gui.go();
	}

	public void go() {
		JFrame frame = new JFrame();  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JButton button = new JButton("Just Click It");  
		button.addActionListener(this);
		text = new JTextArea(10, 20);  
		text.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(text);  
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);  
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroller);
		frame.add(panel, BorderLayout.CENTER);  
		frame.add(button, BorderLayout.SOUTH);
		frame.setSize(350, 300);  
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {  
		text.append("button clicked\n");
	}
}