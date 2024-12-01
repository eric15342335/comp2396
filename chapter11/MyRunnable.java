public class MyRunnable implements Runnable {  
	public void run() { go(); }

	public void go() { doMore(); }

	public void doMore() {  
		System.out.println("top of the stack");
	}

	public static void main(String[] args) {  
		Runnable threadJob = new MyRunnable();  
		Thread myThread = new Thread(threadJob);  
		myThread.start();
		System.out.println("back in main");
	}
}