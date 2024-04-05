package IsA;
import java.util.Scanner;
public class MemoryExam {
public static void main(String[] args) {
		
		MyStack ms = new MyStack();
		MyQueue mq = new MyQueue();
		
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.print("1.Stack 2.Queue 3.exit");
			int num = sc.nextInt();
			
			if(num == 1) {
				
				while(true) {
					System.out.print("1.push 2.pop 3.back");
					
					int n = sc.nextInt();
					
					if(n == 1) {
						ms.push(sc.nextInt());
						
					}else if(n == 2) {
						System.out.println(ms.pop());						
					}else break;
				}
			}else if(num == 2) {
				
				while(true) {
					System.out.print("1.push 2.pop 3.back");
					
					int n = sc.nextInt();
					
					if(n == 1) {
						mq.push(sc.nextInt());
						
					}else if(n == 2) {
						System.out.println(mq.pop());
					}else break;
				}
			}else System.exit(0);
		
		}while(true);
	}

	
}
