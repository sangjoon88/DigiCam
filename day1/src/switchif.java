import java.util.Scanner;

public class switchif {

	public static void main(String []args) { 
			
		  Scanner sc = new Scanner(System.in);		
			
			int num1 = sc.nextInt();
			String op2 = sc.next(); 
			char op = op2.charAt(0);
			
			int num2 = sc.nextInt();
			
			// int num1 = sc.nextInt();
			//String op = sc.next();
			//int num2 = sc.nextInt();
			// if (op.equals("+")) {   > 가운데 부호를 문자로 인식했을 때,  기존 방식 op2를 
			
			if (op == '+') {
				System.out.println(num1+""+op+""+num2+"="+(num1+num2));
			}else if (op == '-') {
				System.out.println(num1+""+op+""+num2+"="+(num1-num2));
			}else if (op == '*') {
				System.out.println(num1+""+op+""+num2+"="+(num1*num2));
			}else if (op == '/') {
				System.out.println(num1+""+op+""+num2+"="+((double)num1/num2));
			}else {
				System.out.println("잘못된 입력");
			}
				
				
				
				
		
	}
}