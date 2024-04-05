package IsA2;
import java.util.Scanner;
public class MemoryExam {
public static void main(String[] args) {
		
		MyStack ms = new MyStack();
		MyQueue mq = new MyQueue();
		Memory mm;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.print("1.Stack 2.Queue 3.exit");
			int num = sc.nextInt();
			
			if(num == 1) 
				mm=ms;
				
				else 
					mm=mq;
				
				
				while(true) {
					System.out.print("1.push 2.pop 3.back");
					
					int n = sc.nextInt();
					
					if(n == 1) {
						mm.push(sc.nextInt());
						
					}else if(n == 2) {
						System.out.println(mm.pop());						
					}else break;
				}
		}	while(true);
		
		
		}
	
}
