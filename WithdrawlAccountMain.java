package AssignmentSession7;


	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;

	class WithdrawThread extends Thread {
		int bal;
		File f;

		public WithdrawThread(int balance) {
			this.bal = balance;
		}

		@Override
		public void run() {
			super.run();
			bal = withdraw(bal);
			System.out.println("Actual balance: " + bal);
		}

		public int withdraw(int bal) {
			
			for (int i = 0; i <= 10; i++) {
				bal = bal - 1000;
			}
			return bal;
		}
	}

	class EmailThread extends Thread {
		int bal;

		public EmailThread(int balance) {
			this.bal = bal;
		}

		@Override
		public void run() {
			super.run();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("EMAIL: A recent change has occured with your balance");

		}
	}

	class SmsThread extends Thread {
		int bal;

		public SmsThread(int balance) {
			this.bal = balance;
		}

		@Override
		public void run() {
			super.run();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("SMS: A recent change has occured with your balance");

		}
	}

	class LogNotificationsThread extends Thread {
		File f;
		BufferedWriter bw;

		public LogNotificationsThread(File f2) {
			this.f = f2;
		}

		@Override
		public void run() {
			super.run();
			try {
				Thread.sleep(2000);
				bw = new BufferedWriter(new FileWriter(f, true));
				bw.write("SMS notification and Email notification sent\n");
				System.out.println("It Was Written");
				bw.close();
			} catch (IOException |InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public class WithdrawlAccountMain {

		public static void main(String[] args) {

			int balance = 1000000;
			File f = new File("C:\\Users\\Zair\\eclipse-workspace\\Assignments\\src\\AssignmentSession7\\HasBeenNotified.txt");
			WithdrawThread wt = new WithdrawThread(balance);
			EmailThread et = new EmailThread(wt.bal);
			SmsThread st = new SmsThread(wt.bal);
			LogNotificationsThread lnt = new LogNotificationsThread(f);
			wt.start();
			et.start();
			st.start();
			lnt.start();


}
}