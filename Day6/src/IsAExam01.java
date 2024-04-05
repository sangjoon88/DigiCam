/* Is - a 관게 : 상속관계
 * extends : 상속 표현
 * 클래스는 단일상속 (객체는 단일 상속) // 인터페이스는 다중상속이 된다
 * 다중상속은 모호성이 발생해서 아마 자바에서 C++에 있는 기능에서 없애서 개발한거 같다
 * 객체끼리는 단일상속만 허용한다
 * 반복이 용이하다
 * 
 * 
 * 
 *  오버라이딩 ( 다형성 하나로 여러개 표현) 편할려고 ㅆ느는거
 *  : 부모의 메소드와 똑같은 형태 (오버로딩이랑 다름) 
 *  그리고 부모꺼가 맘에 안들어서 기능을 추가하거나 수정하고 싶을 때 사용
 *  동적바인딩 오버라이딩
 */

class A {
	
	public void disp() {
		System.out.println("a.......");
	}
	
	
}




public class IsAExam01 extends A{  //isaexam01 은 서브클래스 extends object 는 생략되어 있다;
                                                                              // 눈에 안보이지만 생성자가 있다
	
	public String toString() {
      return super.toString()+"Superman";  //super class꺼 // super 와 this로 어디꺼인지 구분한다!  // 오버라이딩은 부모께 마음에 안들 떄 수정해서 쓴다. // 오브젝트를 상속받고 있는 바로 윗단을 보는 것
      																		//
		
		  //return "Superman";
		
		//return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
	
	public static void main(String[] args) {
		
		IsAExam01 isa = new IsAExam01();  //.눈에 안보이지만 생성자가 있다.
		System.out.println(isa);          				// 레퍼런스 변수선언할 때에는 뒤에 생략되어 있고 자동으로 불러오는거다.
		System.out.println(isa.toString()); // Object에 있는 toString을 상속 //isa 랑 Object랑 클래스가 사실은 다르지만 상속받았으니까 그냥 쓸 수 잇다. HasA면 오류 날 것
		//상속을 받앗더라도 부모의 private 은 접근할 수 없다. 그래서 게터 세터 꼭 만들어라.
		
	isa.disp();
		
	}
	
}
