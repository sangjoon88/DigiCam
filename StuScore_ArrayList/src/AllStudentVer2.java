import java.util.*;

public class AllStudentVer2 {

//	private int index; 	
	ArrayList <StudentScoreHasA> stu = new ArrayList<StudentScoreHasA>();
//	private StudentScoreHasA [] stu ;
//	
//	public AllStudentVer2() {
//		this(100) ;
//		//stu = new StudentScoreHasA[100];
//	}
//	public AllStudentVer2(int num) {
//		stu = new StudentScoreHasA[num];
//	}
	
//	public int getIndex() {
//		return index;
//	}
	
//	public StudentScoreHasA[] getStu() {
//		return stu;
//	}
		
	public static void main(String[] args) {
		/*
		 * 1. 학생정보입력
		 * 2. 학생정보수정
		 * 3. 학생정보검색
		 * 4. 학생전체출력
		 */
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
	
	private void setModify() {
		Scanner sc = new Scanner(System.in);
		System.out.println("누구의 점수를 수정할까요? ");
		String name = sc.next();
		
		int i=0;
		for(StudentScoreHasA scomodi : stu) 
			if(scomodi.getName().equals(name)); {
				System.out.println("찾았습니다. ");
				
				while(true) {
					System.out.println("1.국어점수수정");
					System.out.println("2.영어점수수정");
					System.out.println("3.수학점수수정");
					System.out.println("4.수정안함");
					
					int n = sc.nextInt();
					switch(n) {
					case 1: ; break;
					case 2: stu[i].setEng(sc.nextInt()); break;
					case 3: stu[i].setMath(sc.nextInt()); break;
				}
			}
	}
//		while(true) {
//			if( name.equals(stu[i].getName())) {
//				System.out.println("찾았습니다. ");
//				
//				while(true) {
//					System.out.println("1.국어점수수정");
//					System.out.println("2.영어점수수정");
//					System.out.println("3.수학점수수정");
//					System.out.println("4.수정안함");
//					
//					int n = sc.nextInt();
//					switch(n) {
//					case 1: stu[i].setKor(sc.nextInt()); break;
//					case 2: stu[i].setEng(sc.nextInt()); break;
//					case 3: stu[i].setMath(sc.nextInt()); break;
//					
//					}
//					if(n == 4) break; 
//				}
//				break;
//			}
//			i++;
//		}
//		
//	}

	private void setSearch() {
		Scanner sc = new Scanner(System.in);
		System.out.println("누구 찾나요? ");
		String name = sc.next();

		String nameIndex= name;
		for(StudentScoreHasA abc : stu) {
			if(stu.getName().equals(name)) {
				
			}
		}
		
		//for (StudentScoreHasA stuIndex : stu) {
//			if (stuIndex.getName().equals(name)) {
//				System.out.print(stuIndex.getName() + "\t");
//				System.out.print(stuIndex.getKor() + "\t");
//				System.out.print(stuIndex.getEng() + "\t");
//				System.out.print(stuIndex.getMath() + "\t");
//				System.out.print(stuIndex.getTotal() + "\t");
//				System.out.println(stuIndex.getAvg());
//				break; // 원하는 학생을 찾았으므로 반복문을 종료
//			}
//			
//		}
//	 }

/*		int i=0;
		while(true) {
			if(stu.contains(name)) {
				int index = stu.indexOf(name);
				System.out.println("찾았습니다. ");
				
				System.out.print(name+"\t");
				//stu가 ArrayList인데 stu[index]와 같이 배열 형태로 접근하고 있습니다. ArrayList에서는 인덱스로 직접 요소에 접근할 수 없습니다. 
				System.out.print(stu[index].getKor()+"\t");
				System.out.print(stu[i].getEng()+"\t");
				System.out.print(stu[i].getMath()+"\t");
				System.out.print(stu[i].getTotal()+"\t");
				System.out.println(stu[i].getAvg());
				
				break;
				
			}
			
			i++;
		}
		
	}
	*/
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
	private void setStudent() {
		
		StudentScoreHasA stu = new StudentScoreHasA(); // 학생객체생성
		Scanner sc = new Scanner(System.in);
		
		stu.setName(sc.next());
		stu.setKor(sc.nextInt());
		stu.setEng(sc.nextInt());
		stu.setMath(sc.nextInt());
		stu.getTotal();
		stu.getAvg();
		
		
		
	}
	
	
	

}