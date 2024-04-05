import java.util.Scanner;
public class Calcul {

public static void main(String []args) { 
		
	  Scanner sc = new Scanner(System.in);		
		
		int num1 = sc.nextInt();
		String op = sc.next(); 
		int num2 = sc.nextInt();
		
		switch(op) {
		case "+": System.out.println(num1+""+op+""+num2+"="+(num1+num2)); break;
		case "-": System.out.println(num1+""+op+""+num2+"="+(num1-num2)); break;
	    case "*": System.out.println(num1+""+op+""+num2+"="+(num1*num2)); break;
	    case "/": System.out.println(num1+""+op+""+num2+"="+((double)num1/num2)); break;
	    default : System.out.println("잘못된입력");
		}
}
}
