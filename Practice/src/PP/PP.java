package PP;

import java.util.*;

public class PP {

	public static void main(String[] args){
		int balance = 0;
		Scanner sc = new Scanner(System.in);
		
		
		
		while (true) {
			System.out.println ("----------------------------------------");
			System.out.println ("1. 입금, 2 출금, 3. 잔액확인, 4. 종료");
			System.out.println ("----------------------------------------");
			
			int n = sc.nextInt();
			
			switch (n) {
			case 1 :  System.out.println( " 얼마를 입력하시겠습니까?");
						int d = sc.nextInt();
						balance = deposit(balance, d); break;
						
			case 2 : System.out.println("얼마를 출력하시겠습니까");
				int w = sc.nextInt();
				balance = withdraw(balance, w); break;
				
			case 3 : System.out.println(balance); break;
			case 4 : return;
			
			
		}
	
		}
}

	public static int deposit(int b, int d) {
		b = b + d;
		System.out.println(d + "원을 입금하였습니다. 현재잔액 : " + b);
		return b;
	}

	public static int withdraw(int b, int w) {
		if (b >= w) {
			b = b - w;
			System.out.println(w + "원을 출금하였습니다. 현재잔액 :" + b);
		} else {
			System.out.println(w + "원을 출금하려 했으나 잔액이 부족합니다");
		}
		return b;

	}

}