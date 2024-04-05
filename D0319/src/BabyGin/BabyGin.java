package BabyGin;

import java.util.Scanner;

public class BabyGin {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("6개의 숫자를 입력하세요 (0~9 사이, 공백 없이): ");
		String input = scanner.next();
		if (isBabyGin(input)) {
			System.out.println(input + "은(는) Baby-gin입니다.");
		} else {
			System.out.println(input + "은(는) Baby-gin이 아닙니다.");
		}
	}

	private static boolean isBabyGin(String input) {   // 참 거짓 불리언으로 리턴
		int[] counts = new int[10]; // 0에서 9까지 숫자의 빈도를 저장할 배열

		// 입력받은 숫자의 빈도 계산
		for (int i = 0; i < 6 ; i++) {
			counts[input.charAt(i)-48]++;  //아스키코드 - 48 로 스트링을 인트로 맞추고 해당 배열에 넣기 위한 작업
		}

		int triplet = 0;
		int run = 0;

		for (int i = 0; i < counts.length;) {
			if (counts[i] >= 3) { // Triplete 확인
				counts[i] -= 3;
				triplet++;
				continue;
			}
			if (i <= 7 && counts[i] >= 1 && counts[i + 1] >= 1 && counts[i + 2] >= 1) { // Run 확인
				counts[i]--;
				counts[i + 1]--;
				counts[i + 2]--;
				run++;
				continue;
			}
			i++;
		}

		return triplet + run == 2; // Triplete과 Run의 합이 2면 Baby-gin
	}
}