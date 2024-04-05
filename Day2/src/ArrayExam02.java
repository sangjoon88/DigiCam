
/*
 * 3사람의 성적처리 프로그램
 * 입력 : 이름, 국, 영, 수
 * 연산 : 총점, 평균
 * 출력 : 이름, 국, 영. 수, 총, 평
 */
import java.util.Scanner;
public class ArrayExam02 {
	public static void main(String[] args) {

		String[] name = new String[3]; // 이름
		int[][] score = new int[3][4]; // 국,영,수,총
		float[] avg = new float[3]; // 평균

		Scanner sc = new Scanner(System.in);

		for (int j = 0; j < 3; j++) {
			// 입력 : 이름, 국, 영, 수
			System.out.print("name input: ");
			name[j] = sc.next();

			for (int i = 0; i < 3; i++) {
				System.out.print("Score input : ");
				score[j][i] = sc.nextInt(); // 00,01,02 > 10,11,12 > 20,21,22
				score[j][3] += score[j][i]; // score[0][3] = score[0][3]+score[0][0] +score[0][1] (다음루프때 더해지고) +
											// score[0][2] (다음루프때 더해지고)
			}
			avg[j] = score[j][3] / 3.f; // 나누기
		}
		// 연산 : 총 , 평

		// 출력 : 이름, 국어, 영어, 수학, 총점, 평균
		for (int j = 0; j < 3; j++) {
			System.out.print(name[j] + "\t");
			for (int i = 0; i < 4; i++) {
				System.out.print(score[j][i] + "\t");
			}
			System.out.println(avg[j]);

		}
	}
}
