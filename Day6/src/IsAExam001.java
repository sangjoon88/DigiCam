/* super, super()
 * 1. super : 부모의 멤버를 사용하고자 할 떄 사용
 * 오버라이딩 된 멤버를 사용할 때 부모를 명시적으로 적용하기 위해서 사용
 * 
 * 2. super() : 부모의 생성자를 호출
 * 					항상 sub class 의 생성자 첫번째 라인이 하드코딩 (다른 작업 먼저할 수 없다는 의미)
 * 					명시적으로 사용이 가능하나 위치는 변경할 수 없다.
 * 
 * 
 */


class B {
	public B() {
		// 여기에 super(); 하드코딩 // 무조건 첫번째 줄에 고정되 있다.
		System.out.println("Super class");
	}
}

public class IsAExam001 extends B {

	public IsAExam001() {
		// 부모의 생성자를 호출했다는 합리적인 의심을 할 수 있다
		// super();
		System.out.println("Sub class");

	}

	public static void main(String[] args) {

		IsAExam001 isa = new IsAExam001();
	}

}
