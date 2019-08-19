package AssignmentSession7;

import java.util.Scanner;

class ThreadFood extends Thread {

	@Override
	public void run() {
		System.out.println("FOOD");

	}

}

class ThreadFish extends Thread {

	@Override
	public void run() {
		System.out.println("FISH");

	}

}

class ThreadFoodForFish extends Thread {

	@Override
	public void run() {
		System.out.println("FOOD FOR FISH");

	}

}

class ThreadNumber extends Thread {
	int number;

	public ThreadNumber(int number) {
		this.number = number;
	}

	@Override
	public void run() {
		System.out.println(number);
	}

}

public class FoodAndFish {

	public static void main(String[] args) {

		int n;
		Scanner scan = new Scanner(System.in);
		Thread t1,t2,t3,t4 = null;

		for (int i = 1; i < 11; i++) {

			if (i % 2 == 0) {
				t1 = new ThreadFood();
				t1.start();
				try {
					t1.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else if (i % 3 == 0) {
				t2 = new ThreadFish();
				t2.start();
				try {
					t2.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (i % 5 == 0) {
				t3 = new ThreadFoodForFish();
				t3.start();
				try {
					t3.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				t4 = new ThreadNumber(i);
				t4.start();
				try {
					t4.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
