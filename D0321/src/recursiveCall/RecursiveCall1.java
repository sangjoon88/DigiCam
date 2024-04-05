package recursiveCall;

import java.util.Scanner;

public class RecursiveCall1 {

	private int n;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력바람");
		int n = sc.nextInt();

		System.out.println(factorial(n));
	}

	static int factorial(int n) {

		int result = 1;

		for (int i = n; i > 0; i--) {
			result = result * i;
		}

		return result;

	}
}