package recursiveCall;

import java.util.Scanner;

public class RecursiveCall5 {

    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("몇 번째 피보나치 수를 계산하시겠습니까?");
        int n = sc.nextInt();
        int result = fibonacci(n);
        
        System.out.println(n + "번째 피보나치 수: " + result);
    }
    
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}