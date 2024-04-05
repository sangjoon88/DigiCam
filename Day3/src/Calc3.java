/// 계산기 클래스로 빼는 법
// 메인이 외부라고 생각하고 필드, 메쏘드,  컨스터럭터만 해놓는다.
//메인이 사용자라고 생각하고 필드를 채워봐라

//필드/ 컨스트럭터 /메소드

import java.util.Scanner;

public class Calc3 {
	// 변수선언
	private int num1, num2;
	private char ch;
	private double result;
	private char ans;

	public Calc3() {

	}

	public Calc3(int n1, char c, int n2) {
		num1 = n1;
		ch = c;
		num2 = n2;
	}

	public void setNum1(int n) { // this가 첫번째 매개변수 앞에 숨어 있음, 이건 매개변수가 2개임 > reference 변수 > 자기자신을 접근하는 레퍼런스로 항상 선언
									// 되 있음
		num1 = n;
	}

	public void setNum2(int n) {
		num2 = n;
	}

	public void setCh(char c) {
		ch = c;
	}

	public int getNum1() {
		return num1;
	}

	public int getNum2() {
		return num2;
	}

	public char getCh() {
		return ch;
	}

	public double plus(int n1, int n2) {
		return result = n1 + n2;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Calc3 calc = new Calc3();

		int num = sc.nextInt();
		// calc.setNum1(num);
		// calc.setNum1(sc.nextInt());
		char ch = sc.next().charAt(0);
		// calc.setCh(ch);
		int num2 = sc.nextInt();
		// calc.setNum2(num2);

		Calc3 calc = new Calc3(num, ch, num2);

		System.out.println(calc.getNum1() + "" + calc.getCh() + "" + calc.getNum2() + "=");
		System.err.println(calc.plus(num, num2));

	}

}
