import java.util.Scanner;

public class StarMethod {
    public static void main(String[] args) {

    	System.out.println("홀수입력 바람!");
    	Scanner sc = new Scanner(System.in);
    	
    	int b = sc.nextInt();                        //  
    	
    	diaPrint(b);
    }
    	 public static void diaPrint(int a) {  // 돌려보낼게 없으니까 보이드? (반환 타입 , 메소드 이름(카멜케이스), 타입 변수명 )
    		
    		for (int i = 0; i < a; i++) {
   			for (int j = 0; j < a; j++) {
   				if (i <= a / 2) {
  					if (i + j <= a / 2 - 1)
    						System.out.print(" ");
    					else if (j - i >= a / 2 + 1)
    						System.out.print(" ");
    					else
    						System.out.print("*");
    				} else if (i > a / 2) {
    					if (i - j >= a / 2 + 1)
    						System.out.print(" ");
    					else if (i + j >= a / 2 * 3 + 1)
    						System.out.print(" ");
    					else
    						System.out.print("*");
    				}
    			}
    			System.out.println();
    		}
    	}
}
 	
    	
    

//import java.util.Scanner;
//public class Star6 {
//	public static void main(String[] args) {
//		System.out.println("홀수입력");
//		Scanner sc = new Scanner(System.in);
//		int num = sc.nextInt();
//		for (int i = 0; i < num; i++) {
//			for (int j = 0; j < num; j++) {
//				if (i <= num / 2) {
//					if (i + j <= num / 2 - 1)
//						System.out.print(" ");
//					else if (j - i >= num / 2 + 1)
//						System.out.print(" ");
//					else
//						System.out.print("*");
//				} else if (i > num / 2) {
//					if (i - j >= num / 2 + 1)
//						System.out.print(" ");
//					else if (i + j >= num / 2 * 3 + 1)
//						System.out.print(" ");
//					else
//						System.out.print("*");
//				}
//			}
//			System.out.println();
//		}
//	}
//
//}