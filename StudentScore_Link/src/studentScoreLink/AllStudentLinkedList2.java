/// 선생님이 오름차순으로 정렬해달라고 한 부분
// 입력시 자동으로 정렬 되는건 했는데
// 수정하거나 정보 삭제 했을 때 반영 되는건 안됨

package studentScoreLink;
import java.util.Scanner;
public class AllStudentLinkedList2 {
	static StudentScore head;
	static StudentScore cur;
	static StudentScore newNode;
	static StudentScore del;
//	static StudentScore insert; // 오름차순으로 삽입기능을 위한 주소 저장공간 하나 만듬 > 얘는 커서를 옮기기전에 주소를 저장해줄꺼야 > 
	//문법적으로는 insert = cur; / cur=cur.next 순서

	private int index;

	public int getIndex() {
		return index;
	}

	public static void main(String[] args) {
		/*
		 * 1. 학생정보입력 2. 학생정보수정 3. 학생정보검색 4. 학생전체출력
		 */

		AllStudentLinkedList2 stu = new AllStudentLinkedList2();
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
				 newNode = stu.setStudent();
				    if (head == null || newNode.getTotal() >= head.getTotal()) {	
				// 맨 앞이 비어있거나, 새로운 학생점수가,  맨 앞 보다 큰 경우
				        newNode.next = head; // 머리에 저장되 있던 주소의 인스턴스는 이제 뒤로 밀려야 해/ 뉴 노드의 넥스트에 넣어줘서 연결해줌
				        head = newNode; // 새로 생긴 인스턴스 주소를 머리에 넣어줘야 기준이 잡힘.
				    } else {
				        // 중간에 삽입해야 하는 경우
				        cur = head; // 커서를 머리 위치로 옮겨줘서 찾을 수 있게 해줌
				        while (cur.next != null && newNode.getTotal() < cur.next.getTotal()) { //커서의 넥스트 가 null이 아니면서,  커서의 넥스트가 뉴노드의 값이 작을 떄까지 커서를 움직임
				            cur = cur.next; 
				        }
				        newNode.next = cur.next; // 커서의 넥스트(=즉 새로 끼워넣어야 하는 애보다 큰 숫자가 나왔을 때) 얘를 새로생긴애 넥스트에 주소를 넣어줘서 연결해주고
				        cur.next = newNode; //새로 생긴애 주소를 새로생긴에 뒤에 입력해줌
				    }
				    cur=head;
				    break;
				  
//				newNode = stu.setStudent();
//				if (stu.index == 0) {	
//					head = cur = newNode;
//					newNode.next = null;
//					stu.index++;
//					break;
//				} else {					
//					if (newNode.getTotal() >= cur.getTotal()) {
//						newNode.next = head;
//						head = newNode;
//						while(cur != null) {
//							cur = cur.next;}
//							cur.next=null;
//						cur = head;
//					} else { 
//						while (newNode.getTotal()<cur.getTotal())
//						{insert = cur; 
//						cur=cur.next;
//						} newNode.next = cur;
//						insert.next=newNode;
//						while(cur != null) {
//							cur = cur.next;}
//							cur.next=null;
//						cur = head;
//					}	
//				} break;
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