// 피보나치
package recursiveCall;


import java.util.Scanner;

public class RecursiveCall4 {
	int[] arr = new int[2000];

	public int getArr(int n) {
		return arr[n];
	}

	public void setArr(int n, int a) {
		this.arr[n] = a;
	}

	public static void main(String[] args) {

	

		Scanner sc = new Scanner(System.in);
		System.out.println("3을 입력 바람");

		pivo(sc.nextInt());
		//System.out.println("보고싶은 값은 뭐야?");
		int a = sc.nextInt();
		for (int i = 0; i <= a; i++) {
		//	System.out.print(rc4.getArr(i));
		}
		//System.out.print("해당 항의 값은 : " + rc4.getArr(a));
	}

	static void pivo(int n) {
		RecursiveCall4 rc4 = new RecursiveCall4();
		rc4.setArr(0, 0);
		rc4.setArr(1, 1);
		
		if (n >= 1999) {
		
			rc4.setArr(n, rc4.getArr(n - 1) + rc4.getArr(n - 2));
			pivo(n + 1);
		}

	}

}