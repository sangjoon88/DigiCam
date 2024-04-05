package IsA3;

import java.util.Scanner;

public class MemoryExam {
	public static void main(String[] args) {

		MyStack ms = new MyStack();	// ms 인스턴스 생성 Memory+MyStack
		MyQueue mq = new MyQueue(); // mq 인스턴스 생성 Memory + MyQueue

		Scanner sc = new Scanner(System.in);

		do {
			System.out.print("1.Stack 2.Queue 3.exit");
			int num = sc.nextInt();

			if (num == 1) {                                                  //1번이면
				while (true) {												// 참일경우
					System.out.print("1.push 2.pop 3.back");  // 골라라
					int n = sc.nextInt();
					if (n == 1) {                                         //1번이면 푸시면
						ms.push(sc.nextInt());                         //스택에서 푸쉬기능 (입력한 값을)
					} else if (n == 2) {								//2번이면
						System.out.println(ms.pop());             //스택에서 팝기능
					} else
						break;										  //아니면 멈춰라
				}
			} else if (num == 2) {									//동일
				while (true) {
					System.out.print("1.push 2.pop 3.back");
					int n = sc.nextInt();
					if (n == 1) {
						mq.push(sc.nextInt());
					} else if (n == 2) {
						System.out.println(mq.pop());
					} else
						break;
				}
			} else
				System.exit(0); 	                               // 이런게 있다니..

		} while (true);
	}
}
	 
	 
