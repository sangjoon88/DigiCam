import java.util.Scanner;

abstract class Pop {
	public abstract void pop();
}
//

class Memory extends Pop {

	Scanner sc = new Scanner(System.in);
	int arr[] = new int[4];
	
	public void push() {
		for (int z = 0; z < 4; z++) {
			arr[z] = sc.nextInt();
		}
	}

	public void pop() {
	}

}

//

class MyStack extends Memory {
	public void pop() {
		for (int z = 4; z >= 0; z--) {
			System.out.println(arr[z]);
		}
	}
}
//
	class MyQueue extends Memory {
		
		public void pop() {
			for (int z = 0; z < 5; z++) {
				System.out.println(arr[z]);
			}
		 }
	}

 public class IsAAbs {
	
	 static int k;

	 public static void main(String[] args) {
	
		 IsAAbs is = new IsAAbs();
		 Memory mm =new Memory();
		 MyQueue mq = new MyQueue();
		 MyStack ms = new MyStack();
		 System.out.println("배열 입력하세요");
		 mm.push();
		 System.out.println("Mystack 이면 1, MyQueue 면 2");
		 Scanner sc = new Scanner(System.in);
		 is.k = sc.nextInt();
		 if(k==0) {ms.pop();}
		 else {mq.pop();}
		 
		 
		 
		 
	 }
	 
	 
	 
 }
	