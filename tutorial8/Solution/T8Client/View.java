import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class View {

	private JFrame frame;
	private JPanel[] panels;

	private JLabel label;

	private JButton upButton;
	private JButton downButton;

	public View() {
		setFrame();
		setDisplayPanel();
		setControlPanel();
	}

	private void setFrame() {
		frame = new JFrame("Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);

		Container cp = frame.getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

		panels = new JPanel[2];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			cp.add(panels[i]);
		}
	}

	private void setDisplayPanel() {
		label = new JLabel("-");
		label.setFont(label.getFont().deriveFont(64.0f));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panels[0].setLayout(new BoxLayout(panels[0], BoxLayout.Y_AXIS));

		panels[0].add(label);
	}

	private void setControlPanel() {

		upButton = new JButton("Up");
		downButton = new JButton("Down");
		upButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		downButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel panelsContainer = new JPanel();

		panels[1].setLayout(new BoxLayout(panels[1], BoxLayout.Y_AXIS));
		panelsContainer.setLayout(new BoxLayout(panelsContainer, BoxLayout.X_AXIS));

		panels[1].add(panelsContainer);
		panelsContainer.add(upButton);
		panelsContainer.add(downButton);
	}

	public JButton getUpButton() {
		return upButton;
	}

	public JButton getDownButton() {
		return downButton;
	}

	public JLabel getResultLabel() {
		return label;
	}
}
