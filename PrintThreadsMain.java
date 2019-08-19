package AssignmentSession7;


	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.Reader;

	class FileWaiting implements Runnable {
		File f;
		BufferedWriter br;

		@Override
		public void run() {
			try {
				Thread.sleep(10000);
				f = new File("C:\\Users\\Zair\\eclipse-workspace\\Assignments\\src\\AssignmentSession7\\NewFile");
				if (!f.exists()) {
					br = new BufferedWriter(new FileWriter(f, true));
					br.write("My name is Zair Sebastian! & don't you 4get it!");
					br.close();
				}
				System.out.println("File Created");
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}

		}
	}

	class FileReading implements Runnable {
		File f, vowel, consonant, other;
		BufferedWriter bWrite;
		Reader bRead;

		Thread t;

		StringBuilder sbVowel = new StringBuilder();
		StringBuilder sbConsonant = new StringBuilder();
		StringBuilder sbOther = new StringBuilder();

		@Override
		public void run() {
			try {
				f = new File("C:\\Users\\Zair\\eclipse-workspace\\Assignments\\\\src\\AssignmentSession7\\NewFile");
				bRead = new FileReader(f);
				int c = 0;
				while ((c = bRead.read()) != -1) {
					if (isVowel((char) c))
						sbVowel.append((char) c);
					else if (isConsonant((char) c))
						sbConsonant.append((char) c);
					else {
						sbOther = new StringBuilder(sbOther.toString().trim());
						sbOther.append((char) c);
					}
				}
				t = new Thread(new FileWriting(sbVowel.toString(), 0));
				t.start();
				t = new Thread(new FileWriting(sbConsonant.toString(), 1));
				t.start();
				t = new Thread(new FileWriting(sbOther.toString(), 2));
				t.start();

				System.out.println("File Read");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		boolean isVowel(char c) {
			return "AEIOUaeiou".indexOf(c) != -1;
		}

		boolean isConsonant(char c) {
			return "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz".indexOf(c) != -1;
		}

	}

	class FileWriting implements Runnable {
		String str;
		int type;
		File f;
		BufferedWriter br;

		public FileWriting(String str, int type) {
			this.str = str;
			this.type = type;
		}

		@Override
		public void run() {
			try {
				if (type == 0)
					f = new File("C:\\Users\\Zair\\eclipse-workspace\\Assignments\\src\\AssignmentSession7\\Vowelss");
				if (type == 1)
					f = new File("C:\\Users\\Zair\\eclipse-workspace\\Assignments\\src\\AssignmentSession7\\Consonantss");
				if (type == 2)
					f = new File("C:\\Users\\Zair\\eclipse-workspace\\Assignments\\src\\AssignmentSession7\\Otherss");
				if (!f.exists()) {
					br = new BufferedWriter(new FileWriter(f, true));
					br.write(str);
					br.close();
				}
				System.out.println("File type " + type + " Created");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public class PrintThreadsMain {

		public static void main(String[] args) {
			Thread t1 = new Thread(new FileWaiting());
			Thread t2 = new Thread(new FileReading());

			t1.start();
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t2.start();

		}
}
