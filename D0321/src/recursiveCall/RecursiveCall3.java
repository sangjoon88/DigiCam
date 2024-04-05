package recursiveCall;

import java.util.Scanner;

public class RecursiveCall3 {

	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력바람");
		int n = sc.nextInt();

		System.out.println(factorial(n));
	}

	static int factorial(int n) {

		if (n > 0)
			return n * factorial(n - 1);
		else
			return 1;

		// return(n>0)?n*factorial(n-1):1;        > 연산자 조건 한줄로 끝남...
	}
}