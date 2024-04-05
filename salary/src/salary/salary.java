package salary;
import java.util.Scanner;
public class Admin {
	int num = 0;
	EmpInfo[] ei = new EmpInfo[100];
	public void input() {
		Scanner sc = new Scanner(System.in);
		EmpInfo info = new EmpInfo();
		ei[num] = new EmpInfo();
		System.out.print("이름:");
		info.setName(sc.next());
		info.setNumber(num);
		System.out.print("직책:");
		info.setPosition(sc.next());
		System.out.print("재직여부(0=재직, 1=휴직, 2=퇴직):");
		info.setConyn(sc.nextInt());
		System.out.print("패스워드(숫자4자리):");
		info.setPw(sc.nextInt());
		System.out.print("관리자: (y/n)");
		info.setAdmin(sc.next() == "y");
		System.out.print("부서:");
		info.setEmpDept(sc.next());
		ei[num] = info;
		num++;
	}
	public void Edit() {
		System.out.print("편집할 사번을 입력해주세요 : ");
		int empNum = sc.nextInt();
		int editNum = 0, editScoreNum = 0;
		for (int j = 0; j < num; j++) {
			if (ei[j].getNumber().equals(empNum)) {
				System.out.println("직원명\t소속부서\t입사년월일\t직책\t재직여부\t관리자여부");
				System.out.print(ei[j].getName() + "\t");
				System.out.print(ei[j].getEmpDept() + "\t");
				System.out.print(ei[j].getJoinYmd());
				System.out.print(ei[j].getPosition() + "\t");
				System.out.print(ei[j].getConyn() + "\t");
				System.out.println(ei[j].getisAdmin() + "\t");
				System.out.println("수정할 항목을 선택하세요.");
				System.out.println("1. 직원명 2. 소속부서 3. 입사년월일 4. 직책 5. 재직여부 6. 관리자여부 7. 비밀번호");
				editNum = sc.nextInt();
			}
			if (editNum == 1) {// 직원명
				System.out.println("직원명을 새로 입력하세요.");
				ei[empNum].setName(sc.next());
				System.out.println("직원명이 " + ei[empNum].getName() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 2) {// 소속부서
				System.out.println("소속부서를 새로 입력하세요.");
				ei[empNum].setEmpDept(sc.next());
				System.out.println("소속부서가 " + ei[empNum].getEmpDept() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 3) {// 입사년월일
				System.out.println("입사년월일을 새로 입력하세요.");
				ei[empNum].setJoinYmd(sc.nextInt());
				System.out.println("입사년월일이 " + ei[empNum].getJoinYmd() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 4) {// 직책
				System.out.println("직책을 새로 입력하세요.");
				ei[empNum].setPosition(sc.nextInt());
				System.out.println("직책이 " + ei[empNum].getPosition() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 5) {// 재직여부
				System.out.println("재직여부를 새로 입력하세요.");
				ei[empNum].setJoinYmd(sc.nextInt());
				System.out.println("재직여부가 " + ei[empNum].getJoinYmd() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 6) {// 관리자여부
				System.out.println("관리자여부를 새로 입력하세요.");
				ei[empNum].setConyn(sc.nextInt());
				System.out.println("관리자여부가 " + ei[empNum].getConyn() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 7) {// 비밀번호
				System.out.println("비밀번호를 새로 입력하세요.");
				ei[empNum].setisAdmin(sc.nextInt());
				System.out.println("비밀번호가 " + ei[empNum].getisAdmin() + "으로 변경되었습니다.");
				break;
			}
		}
	}