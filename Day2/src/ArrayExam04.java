//함수로 빼기
//인원수 입력 메소드 있었으면
//입력하는 메소드
import java.util.Scanner;

public class ArrayExam04 {

	static String[] name; // 변수를 먼저 선언하고 여기에다가 값을 공유하고 입력하고 싶다 그래서 스테틱을 입력햇음.. 스테틱 안쓰면 게터쎄터 인가??;
	static int[][] score;
	static float[] avg;
	static int a;

	public static void main(String[] args) {                         // 메인이 사용자라고 생각하자
		Scanner sc = new Scanner(System.in);                     //원래는 각 메소드에 써놨었는데, 메인에서 선언하고 빌려줄 수 있다.

		arraySetting(sc);													// 메소드 불러온거 sc라고 써주고. 함수 입력부에 Scanner sc 라고 써줌
		input(sc);
		output();
	}

	public static void arraySetting(Scanner sc) {
		
		System.out.println("인원수를 입력하세요");
		a = sc.nextInt();
		name = new String[a];
		score = new int[a][4];
		avg = new float[a];
	}

	public static void input(Scanner sc) {
	//	Scanner sc = new Scanner(System.in);
		for (int j = 0; j < a; j++) { // 행수가 a행이니까
			// 입력 : 이름, 국, 영, 수
			System.out.print("name input: ");
			name[j] = sc.next();

			for (int i = 0; i < 3; i++) {
				System.out.print("Score input : ");
				score[j][i] = sc.nextInt();
				score[j][3] += score[j][i];
			}
			avg[j] = score[j][3] / 3.f;
		}
	}

	public static void output() {
		for (int j = 0; j < a; j++) { // 행수가 n행이니까
			System.out.print(name[j] + "\t");
			for (int i = 0; i < 4; i++) {
				System.out.print(score[j][i] + "\t");
			}
			System.out.println(avg[j]);
		}
	}

}