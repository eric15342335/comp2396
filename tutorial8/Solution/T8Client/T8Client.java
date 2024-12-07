import javax.swing.SwingUtilities;

public class T8Client {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				View view = new View();
				Controller controller = new Controller(view);
				controller.start();
			}
		});
	}
}
