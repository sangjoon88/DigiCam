import java.util.Scanner;

public class Exception02 {

	public static void main(String[] args) throws ArithmeticException, InterruptedException {    //이 함수에 throws가 붙어있는데
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		
		System.out.println(3/num);
		
		Thread.sleep(3000);
		
		System.out.println("Bye");
}
}