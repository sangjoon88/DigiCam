/*
 *  java는 100% 상속구조
 *  java는 100% 객체지향언어이다 > 무조건 객체를 사용 > class
 *  
 *  시작과 끝을 담당하는 함수 > main함수
 *  함수 > function > method
 *  
 *  이름 규칙
 *  1. 클래스 : 첫글자는 대문자로 작성
 *  2. 메소드, 변수(상수를 저장하는 메모리 공간,변하는 수 아님) : 소문자로 작성
 *  3. 상수(변하지 않는 값) :전부 대문자 MAX
 *  4. 단어와 단어가 이어질 때 두번째 단어의 첫글자는 대문자로 작성 blackwhite, BlackWhite
 *  
 *  DataType
 *  1. 기본타입
 *  	문자형 char 2byte
 *  	정수형 byte short int long =>값의 범위 오버플로우, 
 *  	실수형 float double
 *  2. Referance type : 레퍼런스 변수를 사용
 *  	-배열
 *  	-객체	
 *  	-enum.
 *  
 *  casting
 *  1.자동형변환 3 + 4.2 = error / int + double / 서로다른타입은 연산이 불가 / 작은타입과 큰타입이 더해지면 큰타입으로 
 *  					바뀜, 3.0 + 4.2 = 7.2 / double + double
 *  2.강제형변환 3 /4 = 0 , int / int = int  >> (double)3/(double)4 = 0.75
 *  
 *  
 */

//패키지 (폴더 위치)
//임포트 : api 가져다 쓰겠다  //패키지 임포트가 위에 올 수 있다

//import java.lang.*;
import java.util.Scanner;
public class Hello {

	public static void main(String []args) {      // 프로그램을 시작해주고 끝내줌.
	
		
		// datatype 변수형 
		// 변수형 = 값; ==이 같다는 의미, 초기화
		// 변수 - 지역변수 local variable : method안에 선언되어지는 변수
		//                              : 항상 꼭 초기화를 해야한다.
		
	//	int num;
	//	num = 10;
		
	//	char ch = 'A'; // ""문자열
		
	//	System.out.println(num);
	//	System.out.println("Hello");
	//	System.out.println(333);
		
		//System.out.print("Hello"+333);
		//System.out.print(333);
		
		Scanner sc;
		sc = new Scanner(System.in);		
		
		String str = sc.next();
		int num = sc.nextInt();
		
		System.out.println(str);
		System.out.println(num);
		
		
	    	//Hello hello; // hello 는 레퍼런스변수
	     //	hello = new Hello(); // 객체생성
		
	}
	
}
