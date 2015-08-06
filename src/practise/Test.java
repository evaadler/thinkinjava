package practise;

import java.awt.geom.Area;

public class Test {
	/**
	 * 产生100以内最小五个偶数
	 */
	public void createEven() {
		int flag = 0;
		for (int i = 1; i < 100; i++) {
			if (i % 2 == 0 && flag < 5) {
				System.out.println(i);
				flag++;
			}
		}
	}

	/**
	 * 产生100以内最大五个奇数
	 */
	public void createOdd() {
		int flag = 0;
		for (int i = 100; i > 0; i--) {
			if (flag >= 5) {
				break;
			}
			if (i % 2 != 0) {
				System.out.println(i);
				flag++;
			}
		}
	}

	/**
	 * 产生100以内质数
	 */
	public void createCrimeNumber() {

		for (int i = 3; i < 100; i++) {
			boolean flag = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (!flag) {
				continue;
			}
			System.out.println(i);
			// System.out.println(i);
		}
	}

	/**
	 * 画出菱形，钻石的样子 * *** ***** ******* ********* ******* ***** *** *
	 */
	public void createDiomand() {
		int row = 19;
		for (int i = 1; i <= row; i++) {
			// 输出空格
			for (int j = 1; j <= row - i; j++) {
				System.out.print("  ");
			}
			// 输出*
			for (int j = 0; j < 2 * i - 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for (int i = row - 1; i > 0; i--) {
			// 输出空格
			for (int j = 0; j < row - i; j++) {
				System.out.print("  ");
			}
			// 输出*
			for (int j = (i) * 2 - 1; j > 0; j--) {
				System.out.print("*");
			}
			System.out.println();

		}
	}

	/**
	 * 
	 */
	public void createTriangle() {
		int row = 19;
		for (int i = 1; i <= row; i++) {
			// 输出空格
			for (int j = 1; j <= row - i; j++) {
				System.out.print("  ");
			}
			// 输出*
			for (int j = 0; j < 2 * i - 1; j++) {
				if (j == 0 || j == 2 * i - 2) {
					System.out.print("*");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		for (int i = row - 1; i > 0; i--) {
			// 输出空格
			for (int j = 0; j < row - i; j++) {
				System.out.print("  ");
			}
			// 输出*
			for (int j = (i) * 2 - 1; j > 0; j--) {
				if (j == 2 * i - 1 || j == 1) {
					System.out.print("*");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();

		}
	}

	public static void main(String[] args) {
		//Test test = new Test();
		// test.createEven();
		// test.createOdd();
		// test.createCrimeNumber();
		//test.createDiomand();
		// test.createTriangle();
		int number = 0;
		int array[] = new int[10];
		int arr[] = { 1, 2, 3, 4, 5 };
		while (true) {
			if (number < arr.length)
				array[number++] = arr[number];
			else
				break;
		}
	}
}

class Rectangle {
	int width;
	int height;

	private void round() {
		// TODO Auto-generated method stub

	}

	private void area() {
		// TODO Auto-generated method stub
		
	}
}
