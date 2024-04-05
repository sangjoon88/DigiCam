//탑건 만든 버전


package studentScoreLink;
import java.util.Scanner;
public class AllStudentLinkedList {
	static StudentScore head;
	static StudentScore cur;
	static StudentScore newNode;
	static StudentScore del;

	private int index;

	public int getIndex() {
		return index;
	}

	public static void main(String[] args) {
		/*
		 * 1. 학생정보입력 2. 학생정보수정 3. 학생정보검색 4. 학생전체출력
		 */

		AllStudentLinkedList stu = new AllStudentLinkedList();
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. 학생정보입력");
			System.out.println("2. 학생정보수정");
			System.out.println("3. 학생정보검색");
			System.out.println("4. 학생전체출력");
			System.out.println("5. 학생정보삭제");
			System.out.println("6.  종료");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				if (stu.index == 0) {
					newNode = stu.setStudent();
					newNode.next = head;
					head = cur = newNode;
					stu.index++;
					break;
				} else {
					newNode = stu.setStudent();
					newNode.next = cur.next;
					cur.next = newNode;
					stu.index++;
					cur = cur.next;
					break;
					
				}
			case 2:
				stu.setModify();
				break;
			case 3:
				stu.setSearch();
				break;
			case 4:
				stu.setDisp();
				break;
			case 5:
				stu.setDel();
				break;
			case 6:
				System.exit(0);
				;
				break;
			}

		} while (true);

	}

	private void setDel() {
		Scanner sc = new Scanner(System.in);
		System.out.println("누구의 정보를 삭제할까요?: ");
		String name = sc.next();
		cur = head;
		if (name.equals(head.getName())) {
			
			del = head;
			head = head.next;
			del.next = null;
			del = null;
			cur = head;
		} else  {
			while (cur!= null || cur.next!= null) {
				if (name.equals(cur.next.getName())) {
					del = cur.next;
					cur.next = del.next;
					del.next = null;
					del = null;
				}
				cur = cur.next;
			}
		}
	}

	private void setModify() {
		Scanner sc = new Scanner(System.in);
		System.out.println("누구의 점수를 수정할까요?: ");
		String name = sc.next();

		cur = head;

		while (cur != null) {
			if (name.equals(cur.getName())) {
				System.out.println("찾았습니다. ");

				while (true) {
					System.out.println("1.국어점수수정");
					System.out.println("2.영어점수수정");
					System.out.println("3.수학점수수정");
					System.out.println("4.수정안함");

					int n = sc.nextInt();
					switch (n) {
					case 1:
						System.out.println("국어점수를 입력해주세요: ");
						cur.setKor(sc.nextInt());
						break;
					case 2:
						System.out.println("영어점수를 입력해주세요: ");
						cur.setEng(sc.nextInt());
						break;
					case 3:
						System.out.println("수학점수를 입력해주세요: ");
						cur.setMat(sc.nextInt());
						break;

					}
					if (n == 4)
						break;
				}
				break;
			}
			cur = cur.next;
		}

	}

	private void setSearch() {
		Scanner sc = new Scanner(System.in);
		System.out.print("찾으려는 학생의 이름을 검색해주세요: ");
		String name = sc.next();

		cur = head;

		while (cur != null) {
			if (name.equals(cur.getName())) {
				System.out.println("찾았습니다. ");
				System.out.print(cur.getName() + "\t");
				System.out.print(cur.getKor() + "\t");
				System.out.print(cur.getEng() + "\t");
				System.out.print(cur.getMat() + "\t");
				System.out.print(cur.getTotal() + "\t");
				System.out.println(cur.getAvg());
				break;
			}
			cur = cur.next;
		}
	}

	private void setDisp() {

		cur = head;

		while (cur != null) {
			System.out.print(cur.getName() + "\t");
			System.out.print(cur.getKor() + "\t");
			System.out.print(cur.getEng() + "\t");
			System.out.print(cur.getMat() + "\t");
			System.out.print(cur.getTotal() + "\t");
			System.out.println(cur.getAvg());
			cur = cur.next;
		}
	}

	private StudentScore setStudent() {

		StudentScore newnode = new StudentScore(); // 학생객체생성
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력해주세요: ");
		newnode.setName(sc.next());
		System.out.print("국어점수를 입력해주세요: ");
		newnode.setKor(sc.nextInt());
		System.out.print("영어점수를 입력해주세요: ");
		newnode.setEng(sc.nextInt());
		System.out.print("수학점수를 입력해주세요: ");
		newnode.setMat(sc.nextInt());
		newnode.getTotal();
		newnode.getAvg();

		return newnode;

	}

}