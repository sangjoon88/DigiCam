package ArrangedNum;

import java.util.Scanner;

public class ArrangedNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x, y;
        
        System.out.print("Input X Number: ");
        x = sc.nextInt();
        
        while (x < 100 || x > 9999999) {
            System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
            System.out.print("Input X Number: ");
            x = sc.nextInt();
        }

        System.out.print("Input Y Number: ");
        y = sc.nextInt();
        
        while (y < x || y > 9999999) {
            System.out.println("잘못 입력하였습니다. Y는 X보다 크거나 같아야 합니다.");
            System.out.print("Input Y Number: ");
            y = sc.nextInt();
        }

        int count = 0;
        
        for (int i = x; i <= y; i++) {
            if (isOrdered(i)) {
                System.out.println(i);
                count++;
            }
        }
        
        System.out.println("Total count: " + count);
    }
    
    public static boolean isOrdered(int number) {
        String numStr = String.valueOf(number);
        for (int i = 0; i < numStr.length() - 1; i++) {
            if (numStr.charAt(i) >= numStr.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}