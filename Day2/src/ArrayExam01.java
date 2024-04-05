
/*
 *  한사람의 성적처리프로그램
 *  입력 : 이름, 국,영,수
 *  연산: 총점, 평균
 *  출력 : 이름,국,영,수,총,평
 *  
 *  
 */

import java.util.Scanner;

public class ArrayExam01 {
	public static void main(String[] args) {

		String name; // 이름
		int[] score = new int[4]; // 국,영,수,총 > 배열 선언과 생성을 동시에 함
		float avg;

		Scanner sc = new Scanner(System.in);

		System.out.print("name input: ");
		name = sc.next(); // 이름 입력

		for (int i = 0; i < 3; i++) {
			System.out.print("Score input: ");
			score[i] = sc.nextInt(); // 각 칸에다 값이 입력 됨
			score[3] += score[i]; // 총점 내기 += 다시 볼것
		}

		avg = score[3] / 3.f; // 평균 내기 3.f 는 float 라서 쓴건가?

		System.out.print(name + "\t"); // 이름 출력 \t >>이스케이프 문자 탭정도 띄어쓰기
		for (int sco : score) { // 점수 출력인데 for(int sco:score) {} 이거 뭔지 질문하기
			System.out.print(sco + "\t");
		}
		System.out.println(avg); // 평균출력

	}

}

//		score[0] = sc.nextInt();
//		score[1] = sc.nextInt();
//		score[2] = sc.nextInt();
// score[3] = score[0] + score[1] + score[2];

// avg = score[3] / 3;

// System.out.print(name + " ");
// for (int i = 0; i < score.length; i++) {
// System.out.print(score[i] + ", ");
// }

// System.out.print(avg);

// array 동일한 data 타입을 메모리상에 순차적으로 확보하는 것 // 같은타입 // 순차적!
// [] 대괄호로 사용

// int a,b,c,d,e; 뜨문 뜨문 생김 // 배열의 장점은 첫번째만 알면 위치를 알 수 있다
// int[ ] arr; 아직 배열이 없고 int[]을 접근할수 있는 ref 을 만든것
// arr=new int[5]; >> 다섯개짜리 배열을 만든 것

// int []arr=new int[5];

// for (int i = 0;i<arr.length;i++) {
// System.out.println ( arr[i]); }

// for (int i:arr) {
// System.out.println(i); //for each 문
