import java.util.Scanner;
public class IF {
	
	public static void main(String []args) { 
		
		Scanner sc;
		sc = new Scanner(System.in);		
		
	
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		if(a>b) { System.out.println(a);}
		else if(a<b) { System.out.println(b);}
		else {System.out.println("같음");} 
		
	}

}
