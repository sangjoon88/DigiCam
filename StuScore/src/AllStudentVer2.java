import java.util.Scanner;

public class AllStudentVer2 {

	private int index; 	
	private StudentScoreHasA [] stu ;
	
	public AllStudentVer2() {                        //생성자 오버로딩
		this(100) ;
		
	}
	public AllStudentVer2(int num) {                  // 생성자 오버로딩 위에거랑 둘다 가능
		stu = new StudentScoreHasA[num];
	}
	
	public int getIndex() {
		return index;
	}
	
	public StudentScoreHasA[] getStu() {
		return stu;
	}
		
	public static void main(String[] args) {

		AllStudentVer2 stu = new AllStudentVer2();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. 학생정보입력");
			System.out.println("2. 학생정보수정");
			System.out.println("3. 학생정보검색");
			System.out.println("4. 학생전체출력");
			System.out.println("5.  종료");
			
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1: stu.setStudent(); break;
			case 2: stu.setModify(); break;
			case 3: stu.setSearch(); break;
			case 4: stu.setDisp(); break;
			case 5: System.exit(0);; break;
			}
		
		}while(true); 

	}
	
	private void setStudent() {
		
		StudentScoreHasA stu = new StudentScoreHasA(); // 학생객체생성
		Scanner sc = new Scanner(System.in);
		
		stu.setName(sc.next());
		stu.setKor(sc.nextInt());
		stu.setEng(sc.nextInt());
		stu.setMath(sc.nextInt());
		stu.getTotal();
		stu.getAvg();
		
		this.stu[index++]=stu;  //// 핵심 : stu[0] 부터 stu 인스턴스 생성된 주소를 하나씩 하나씩 넣어줌
		
	}
	
	private void setModify() {
		Scanner sc = new Scanner(System.in);
		System.out.println("누구의 점수를 수정할까요? ");
		String name = sc.next();
		
		int i=0;
		while(true) {
			if( name.equals(stu[i].getName())) {
				System.out.println("찾았습니다. ");
				
				while(true) {
					System.out.println("1.국어점수수정");
					System.out.println("2.영어점수수정");
					System.out.println("3.수학점수수정");
					System.out.println("4.수정안함");
					
					int n = sc.nextInt();
					switch(n) {
					case 1: stu[i].setKor(sc.nextInt()); break;
					case 2: stu[i].setEng(sc.nextInt()); break;
					case 3: stu[i].setMath(sc.nextInt()); break;
					
					}
					if(n == 4) break; 
				}
				break;
			}
			i++;
		}
		
	}
	private void setSearch() {
		Scanner sc = new Scanner(System.in);
		System.out.println("누구 찾나요? ");
		String name = sc.next();
		
		int i=0;
		while(true) {
			if( name.equals(stu[i].getName())) {
				System.out.println("찾았습니다. ");
				
				System.out.print(stu[i].getName()+"\t");
				System.out.print(stu[i].getKor()+"\t");
				System.out.print(stu[i].getEng()+"\t");
				System.out.print(stu[i].getMath()+"\t");
				System.out.print(stu[i].getTotal()+"\t");
				System.out.println(stu[i].getAvg());
				
				break;
				
			}
			
			i++;
		}
		
	}
	private void setDisp() {
		
		for(StudentScoreHasA s:stu) {
			if(s == null) break;
			System.out.print(s.getName()+"\t");
			System.out.print(s.getKor()+"\t");
			System.out.print(s.getEng()+"\t");
			System.out.print(s.getMath()+"\t");
			System.out.print(s.getTotal()+"\t");
			System.out.println(s.getAvg());
		}
		
	}

	
	

}