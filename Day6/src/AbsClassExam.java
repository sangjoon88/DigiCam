/* 추상클래스는 추상메소드를 하나 이상 가지고 잇는 클래스이다.
 * 추상클래스는 객체 생성을 할 수는 없고, 상속을 해주는 목적으로 사용한다.
 * 추상메소드는 서브클래스의 강제성을 부여한다 (오버라이딩을 해야한다)
 * 오버라이딩을 하지 않을 경우 서브클래스도 추상클래스화 되서 객체를 생성할 수 없다.
 * 
 * 상속관계시 항상 공통된 것은 수퍼클래스에 정의를 해야하는데
 * 개념적으로는 수퍼에 있어야 하지만, 기능적으로는 수퍼에 정의할 수 없을 때 만든다
 * 
 */



abstract class Area {                   // 추상메소드 abstract 이라고 명시
	
	public abstract void draw() ;   // 추상메소드 abstract 이라고 명시
}


class Rect extends Area {
	@Override											//시각적 효과일 뿐 이해하기 쉽게 @Override
	public void draw() {
		System.out.println("Rect");
	}
}


class Circle extends Area {
	public void draw() {
		System.out.println("Circle");
	}
}


class Tri extends Area {
	public void draw() {
		System.out.println("Tri");
	}
}

public class AbsClassExam {

	public static void main(String[] args) {
		Tri tri = new Tri();
		tri.draw();
		
		Rect rect = new Rect();
		rect.draw();
		
		Circle cir = new Circle();
		cir.draw();

	}

}
