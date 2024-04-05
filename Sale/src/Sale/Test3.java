package Sale;
import java.util.Scanner;
public class Test3 {
//	static Calculate ca = new Calculate();
	public static void main(String[] args) {    //main
		Scanner sc = new Scanner(System.in);
		Admin as = new Admin();
		System.out.println("1.최초등록   2.로그인");
		int log = sc.nextInt();
		switch (log) {
		case 1:
			as.input();
		case 2:
			System.out.println("급여관리프로그램 시작합니다.");
			System.out.println("ID : ");
			int id = sc.nextInt();
			System.out.println("PW : ");
			int pw = sc.nextInt();
			if (as.AdminState(id, pw) == true) {
				while (true) {
					System.out.print("1.직원등록 2.정보수정 3.직원별급여계산 4.전직원정보 5.프로그램 종료 : ");
					int option = sc.nextInt();
					switch (option) {
					case 1:
						as.input();
						break;
					case 2:
						as.Edit();
						break;
					case 3:
						as.sal();
						break;
					case 4:
						as.empPrt();
						break;
					case 5:
						System.exit(0);
						break;
					}
				}
			} else {
				System.out.println("사용자모드입니다. 사원번호를 입력해주세요");
				as.sal();
				System.exit(0);
			}
		}
	}
}