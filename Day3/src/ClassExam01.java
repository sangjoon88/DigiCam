/*
 * 구성요소
 * 1. field : 객체가 생성된 후 지속적으로 사용되어지는 변수 (공간이 아님) 내생각에는 여기에 선언할 수 있는게 변수니까 그냥 변수라고 하는거 같음
 * 				-접근지정자 pr df protect private  주로 private. 
 *                - static, final(필드에다 파이날이라고 붙이면 못 바끔 = 읽기전용) / 변수에 final 붙이면 상수 (ex 회사이름)  // 스테틱은 미리 만들어서 공유 쓰는거다 그래서 스태틱,파이널 같이 쓰는 경우가 많음
 *                객체당 한개씩 써야할 데이터면 인스턴스(4444444 byte 계속) / 이 클래스에 전체가 쓸꺼면 클래스 (4 byte 공유)
 *                - instance field, static field
 *                
 *                ***** 인스턴스는 객체를 생성해야 쓸수 있다
 *                
 * 2. constructor (생성자 함수)
 * 	-  객체 생성시 자동 호출되어지는 함수 (딱 1번)
 * 	- 	ClassExam01 ce= new ClassExam01();  // 생성자 호출 숨어잇음
 * 	- 생성자함수를 명시적으로 제공하지 않을 경우 디폴트 생성자를 제공해준다.
 * 	- 리턴타입이 존재하지 않고, 함수명은 클래스 명과 동일.
 * 	- 기능 1) 디폴트 기능 : 할거 없음, 객체 등록
 * 				2) 사용자 기능 :  객체생성시 필드초기화(=초기화는 비운다는 의미가 아니고 시작값이라는 의미)
 * 	-오버로딩이 가능 (0원으로 시작할지, 5만원으로 시작할지, 5만원 + 카드로 시작할지)
 * 	-접근지정자를 주로 public 으로 지정
 * 	-디폴트생성자를 명시적으로 만들경우 숨어있는 디폴트는 제공하지 않는다.
 * 	-명시적으로 생성자를 하나 이상 만들경우 디폴트는 제공하지 않는다.
 * 
 * 
 * 3. method 
 * -인스턴스 매소드가 있고 스태틱 메소드가 있는데 인스턴스가 더 많이 쓴다
 * - 외부에서 내부 필드를 접근해서 사용할수 있게 하기 위해. 프라이빗은 절대 접근 못한다(C++은 프랜드 기능이 있어서 가능한데 자바는 막아놨다
 * - 접근지정자를 주로 public으로 지정
 * - instace methed, static method >> this 여부(객체를 구분해준다는 뜻, 인스턴스에는 있다. 디폴트)
 * -오버로딩이 가능하다. (편의성)
 * 
 * 
 * -오버로딩??
 *  동일한 함수 명으로 여러개의 함수를 만들어서 제공
 *  같은 기능을 수행하는 것을 하나의 이름으로 묶어서 제공함으로써 사용자가 편하게 슬 수 있게 해주는 기능.
 *  같은 성질을 하는 것으로 묶어라 제발 / 제발
 *  문법적으로는 함수명을 동일하게 하면서 매개변수 (함수 뒤에 괄호()). 인자, argument = 같은말) 를 가지고 구분할 수 있어야 한다.
 *  	- 매개변수로 구분하는 방법 
 *  		-1. 매개변수 개수가 다를경우
 *  		-2. 매개변수 타입이 다를 경우 
 * 
 */




	public class ClassExam01 {
		// 필드 >> 객체를 계속 쓸 때 여기에 씀
		private int a; 
		private char s; 	
		private float c; 
		
		
		public static void main(String[] args) {
			
			ClassExam01 ce = new ClassExam01();  // 생성자호출 
			ce.output(1,2);
			ce.output(3.4);
			ce.output(11);
			ce.output();

		}
		public void output(int a,int b) {
			System.out.println("a,b");
		}
		public void output(double a) {
			System.out.println("a");
		}
		public void output(int a) {
			System.out.println("int a");
		}
		
		public void output() {
			System.out.println("Nothing");
		}

	}

// 필드 >> 객체를 계속 쓸 때 여기에 씀

