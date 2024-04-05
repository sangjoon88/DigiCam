//일섭이 형 꺼, *** 무슨소리지?


package ArrangedNum;

import java.util.*;

public class organizedNumber {

	static int count = 0;

	public static void organizedNumberRunner()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Input X number : "); //숫자 범위 받기
		int small = sc.nextInt();

		System.out.println("Input Y number : "); //숫자 범위 받기
		int big = sc.nextInt();

		for (int k = small; k < big + 1; k++) {
			int number = k;
			int[] digits = arrMaker(number);
			count = numberSort(digits, number);
		}
		System.out.println("count : " + count);
}

	private static int numberSort(int[] digits, int number) {
		// TODO Auto-generated method stub
		int x = 0;
		String numberStr = String.valueOf(number);
		int len = numberStr.length();
		//숫자 내림차순 성사 개수 파악
		for (int i = 0; i < numberStr.length() - 1; i++) {
			if (digits[i] < digits[i + 1]) {
				x += 1;
			}
		}
		//숫자 내림차순 성사 개수와 자릿수 비교하여 정돈된 수 여부 파악
		if (x > len - 2) {
			System.out.print(number);
			System.out.print("  ");
			count = count + 1;
		} // 앞자리 수가 뒷자리수보다 작은 것이 자리수에 비례해서 커져야 정돈된 수

		return count;
	}

	private static int[] arrMaker(int number) {
		// TODO Auto-generated method stub
		// 숫자를 문자열로 변환하여 각 자리수에 접근
		int space = String.valueOf(number).length();


		int[] digits = new int[space];
		String numberStr = String.valueOf(number);

		// 각 자리수를 배열에 저장
		for (int i = 0; i < numberStr.length(); i++) {
			digits[i] = Character.getNumericValue(numberStr.charAt(i));
//			System.out.print(digits[i]);

		}

		return digits;
	}

} // public class organizedNumber