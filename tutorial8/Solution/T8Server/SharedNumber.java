
public class SharedNumber {
	private int n;

	public SharedNumber() {
		this.n = 0;
	}

	public synchronized void up() {
		n++;
	}

	public synchronized void down() {
		n--;
	}

	public synchronized int getNumber() {
		return n;
	}
}
