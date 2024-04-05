import java.util.Scanner;
public class ArrayExam03 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int n=0; // 인원수
		System.out.print("인원수를 입력하세요: ");
		n=sc.nextInt();
		
		String []name=new String[n]; //이름
		int [][]score = new int [n][4]; //국,영,수,총
		float [] avg=new float[n]; // 평균
		
		
		for(int j=0; j<n; j++)	{                         //행수가 n행이니까
			// 입력 : 이름, 국, 영, 수
			System.out.print("name input: ");
			name[j] = sc.next();
			
			for(int i=0;i<3;i++) {
				System.out.print("Score input : ");
				score[j][i] = sc.nextInt();
				score[j][3] += score[j][i];
			}
			avg[j] = score[j][3]/3.f;
		}
		 //연산 : 총 , 평
		
		// 출력 : 이름, 국어, 영어, 수학, 총점, 평균
		for(int j=0; j<n;j++) {                                     //행수가 n행이니까
			System.out.print(name[j]+"\t");
			for(int i=0;i<4;i++) {
				System.out.print(score[j][i]+"\t");
			}
			System.out.println(avg[j]);
			
			
		}
		
	}
}
