package Sale;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Admin {
	int years, months;

	int num = 0;
	int id = 0;
	static EmpInfo[] ei = new EmpInfo[100];

	public void input() {
		Scanner sc = new Scanner(System.in);
		EmpInfo info = new EmpInfo();

		ei[num] = new EmpInfo();
		System.out.print("이름:");
		info.setName(sc.next());
		info.setNumber(num);
		System.out.print("직책:");
		System.out.println("1.사원 2.대리 3.과장 4.차장 5.부장 6.대표");
		int rank = sc.nextInt();
		switch (rank) {
		case 1:
			info.setPosition("사원");
			break;
		case 2:
			info.setPosition("대리");
			break;
		case 3:
			info.setPosition("과장");
			break;
		case 4:
			info.setPosition("차장");
			break;
		case 5:
			info.setPosition("부장");
			break;
		case 6:
			info.setPosition("대표");
			break;
		default:
			System.out.println("잘못 된 직급입니다!");
		}

		System.out.print("재직여부(0=재직, 1=휴직, 2=퇴직):");
		info.setConyn(sc.nextInt());
		System.out.print("패스워드(숫자4자리):");
		info.setPw(sc.nextInt());
		System.out.print("입행일자(yyyymmdd):");
		info.setJoinYmd(sc.next());
		System.out.print("관리자: (y/n)");
		info.setAdmin(sc.next().equals("y"));
		System.out.print("부서:");
		info.setEmpDept(sc.next());
		ei[num] = info;
		System.out.println("등록한 직원의 ID는 "+num+"입니다.");
		num++;
	}

	public void sal() {
		Scanner sc = new Scanner(System.in);
		Calculate ci = new Calculate();
		EmpInfo yi = new EmpInfo();
		System.out.println("사원번호를 입력하세요.");
		num = sc.nextInt();
		ci.setCal(ei[num].getPosition());
		ci.calcPrt();
		ci.setReal(ei[num].getPosition());
		ci.real();
		ci.exit();
	}
	
	public void salUser() {
		EmpInfo yi = new EmpInfo();
		Calculate ci = new Calculate();
		ci.setCal(ei[id].getPosition());
		ci.calcPrt();
		ci.setReal(ei[id].getPosition());
		ci.real();
		ci.exit();
	}

	public void empPrt() {
		if (num == 0) {
			System.out.println("현재 등록된 직원이 없습니다.");

		}
		for (int i = 0; i < num; i++) {
			ei[i].info();
		}
	}

	public void Edit() {
		System.out.print("편집할 사번을 입력해주세요 : ");
		Scanner sc = new Scanner(System.in);
		int empNum = sc.nextInt();
		int editNum = 0, index = 0;
		for (int j = 0; j < num; j++) {
			if (ei[j].getNumber() == empNum) {
				System.out.println("직원명\t소속부서\t입사년월일\t직책\t재직여부\t관리자여부");
				System.out.print(ei[j].getName() + "\t");
				System.out.print(ei[j].getEmpDept() + "\t");
				System.out.print(ei[j].getJoinYmd());
				System.out.print(ei[j].getPosition() + "\t");
				System.out.print(ei[j].getConyn() + "\t");
				System.out.println(ei[j].isAdmin() + "\t");
				System.out.println("수정할 항목을 선택하세요.");
				System.out.println("1. 직원명 2. 소속부서 3. 입사년월일 4. 직책 5. 재직여부 6. 관리자여부 7. 비밀번호");
				editNum = sc.nextInt();
				index = j;
			}
			if (editNum == 1) {// 직원명
				System.out.println("직원명을 새로 입력하세요.");
				ei[index].setName(sc.next());
				System.out.println("직원명이 " + ei[index].getName() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 2) {// 소속부서
				System.out.println("소속부서를 새로 입력하세요.");
				ei[index].setEmpDept(sc.next());
				System.out.println("소속부서가 " + ei[index].getEmpDept() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 3) {// 입사년월일
				System.out.println("입사년월일을 새로 입력하세요.");
				ei[index].setJoinYmd(sc.next());
				System.out.println("입사년월일이 " + ei[index].getJoinYmd() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 4) {// 직책
				System.out.println("직책을 새로 입력하세요.");
				ei[index].setPosition(sc.next());
				System.out.println("직책이 " + ei[index].getPosition() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 5) {// 재직여부
				System.out.println("재직여부를 새로 입력하세요.");
				ei[index].setJoinYmd(sc.next());
				System.out.println("재직여부가 " + ei[index].getJoinYmd() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 6) {// 관리자여부
				System.out.println("관리자여부를 새로 입력하세요.");
				ei[index].setConyn(sc.nextInt());
				System.out.println("관리자여부가 " + ei[index].getConyn() + "으로 변경되었습니다.");
				break;
			} else if (editNum == 7) {// 비밀번호
				System.out.println("비밀번호를 새로 입력하세요.");
				ei[index].setPw(sc.nextInt());
				System.out.println("비밀번호가 " + ei[index].isAdmin() + "으로 변경되었습니다.");
				break;
			}
		}
	}

	public void setYears(int years) {
		this.years = years;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getYears() {
		String empEnterDateStr = ei[num].getJoinYmd();
		LocalDate empEnterDate = LocalDate.parse(empEnterDateStr, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate currentDate = LocalDate.now(); // 현재 날짜
		Period period = Period.between(empEnterDate, currentDate);
		int experienceYears = period.getYears();
		return years = experienceYears;
	}

	public int getMonths(int a) {
		String empEnterDateStr = ei[num].getJoinYmd();
		LocalDate empEnterDate = LocalDate.parse(empEnterDateStr, DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate currentDate = LocalDate.now(); // 현재 날짜
		Period period = Period.between(empEnterDate, currentDate);
		int experienceMonths = period.getMonths();
		return months = experienceMonths;
	}

	public boolean AdminState(int id, int pw) {
		Scanner sc = new Scanner(System.in);
		System.out.println("급여관리프로그램 시작합니다.");
		System.out.println("ID : ");
		id = sc.nextInt();
		System.out.println("PW : ");
		pw = sc.nextInt();
		if (ei[id].getPw() == pw && ei[id].getNumber() == id && ei[id].isAdmin() == true) {
			System.out.println("관리자 로그인");
			return true;
		} else if (ei[id].getPw() == pw && ei[id].getNumber() == id && ei[id].isAdmin() == false)
			System.out.println("사용자 로그인");
			salUser();
		return false;
	}
}