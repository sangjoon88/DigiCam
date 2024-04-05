package SmallNum;
import java.util.Arrays;
import java.util.Scanner;

public class SmallNum {

	public static void main(String[] args) {
		//변수선언
		int[] small = new int[4];  // 네 숫자 입력변수
		int num = 0;
		for (int j = 1000; j < 10000; j++) {
			num = j; 
			for (int i = 0; i < 4; i++) {		// 숫자 자르기		
				small[i] = num%10;
				num = num/10;
			}		
			Arrays.sort(small);			//정렬
			
			if (small[0] == 0) {  // 0일때 자리변경
				for (int i = 1; i < 4; i++) {
					if (small[i] != 0) {
						small[0] = small[i];
						small[i] = 0;
						break;
					}
				}
			}
			//출력
			System.out.print(j+"\t");
			for (int i : small)
				System.out.print(i);
			System.out.println();
		}
	}
}