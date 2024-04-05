import java.util.Scanner;

public class AllStudent {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt(); 
		
		StudentScoreHasA []stu = new StudentScoreHasA[num];
		
		
		for(int i=0;i<num;i++) {
			stu[i] = new StudentScoreHasA(); 
			
			stu[i].setName(sc.next());
			stu[i].setKor(sc.nextInt());
			stu[i].setEng(sc.nextInt());
			stu[i].setMath(sc.nextInt());
		
		}
		
		for(int i=0;i<num;i++) {
			System.out.println(stu[i].getName());
			System.out.println(stu[i].getKor());
			System.out.println(stu[i].getEng());
			System.out.println(stu[i].getMath());
			System.out.println(stu[i].getTotal());
			System.out.println(stu[i].getAvg());
		}

	}

}