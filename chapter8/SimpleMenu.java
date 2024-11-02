import javax.swing.*;  
import java.awt.event.*;

public class SimpleMenu implements ActionListener {  
	JTextArea text;
	
	public static void main(String[] args) {  
		SimpleMenu simpleMenu = new SimpleMenu();  
		simpleMenu.go();
	}

	public void go() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();  JMenu 
		menu = new JMenu("Menu A");
		JMenuItem menuItem = new JMenuItem("Item");
		menuItem.addActionListener(this);  
		menu.add(menuItem);  
		menuBar.add(menu);  
		frame.setJMenuBar(menuBar);
		text = new JTextArea();  
		text.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(text);  
		scroller.setVerticalScrollBarPolicy(
		ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(  
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.add(scroller);  
		frame.setSize(350, 300);  
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {  
		text.append("Menu item selected\n");
	}
}