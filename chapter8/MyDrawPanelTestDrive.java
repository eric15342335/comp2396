import javax.swing.*;

public class MyDrawPanelTestDrive {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyDrawPanel drawPanel = new MyDrawPanel();
		frame.add(drawPanel);
		frame.setSize(300, 300);  
		frame.setVisible(true);
	}
}