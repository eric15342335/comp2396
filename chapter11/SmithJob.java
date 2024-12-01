public class SmithJob implements Runnable {  
	private BankAccount account = new BankAccount();

	public static void main(String[] args) {  
		SmithJob theJob = new SmithJob();  
		Thread mrSmith = new Thread(theJob);  
		Thread mrsSmith = new Thread(theJob);  
		mrSmith.setName("Mr. Smith");  
		mrsSmith.setName("Mrs. Smith");
		mrSmith.start();  
		mrsSmith.start();
	}

	public void run() {
		for (int x = 0; x < 2; x++) {  
			makeWithdrawal(60);
		}
	}

	private void makeWithdrawal(int amount) {  
		if (account.getBalance() >= amount) {
			System.out.println(getName() + " is about to withdraw");  
			try {
				System.out.println(getName() + " is going to sleep");  
				Thread.sleep(500);
			} catch (Exception ex) { ex.printStackTrace(); }
			System.out.println(getName() + " wakes up");  
			account.withdraw(amount);
			System.out.println(getName() + " completes the withdrawal");  
			if (account.getBalance() < 0) {
				System.out.println("Overdrawn!");
			}
		}
		else {
			System.out.println("Not enough money for " + getName());
		}
	}

	private String getName() {
		return Thread.currentThread().getName();
	}
}