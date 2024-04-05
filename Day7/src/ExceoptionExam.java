/*예외처리
 * -결과가 중요한게 아니라 시스템이 멈추는는걸 막는 처리
 * -정상적인 마무리를 수행하게 해주는 것
 * - try -catch 문
 * 
 * 
 * 
 *// 상위는 제일 아래 써라
 * 
 */

import java.util.InputMismatchException;
import java.util.Scanner;
public class ExceoptionExam {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
		int num = sc.nextInt();
																	// 예상되는 예외를 인식.
		System.out.println(3/num);
		} catch(ArithmeticException ae) {                //예외 처리기
			//ae.printStackTrace();
			System.out.println("0 입력하지 마세요 ");
		} catch(InputMismatchException in) {
			System.out.println("제대로 입력해 ");
		}
		System.out.println("Bye");
		
    	}
	}
