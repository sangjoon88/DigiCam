/*
 * inner class  >> outter class 의 멤버를 내것처럼 사용할 수 있게 해주는 클래스. 프라이빗이어도 사용가능하다. 외부아니고 자기를가지고 있는 아우터클래스
 *  >> 특정목적 . outer class에 맞는 목적으로.
 *  >> 주로 event처리시 사용함.
 *  
 * 1. member inner class
 * 2. static inner class
 * 3. local inner class
 * 4. anonymous inner class
 * 
 * outer class
 * inner class
 * 
 */
public class InnerClassExam01 {

	private int a;
	private static int b;

	public void dispTest() {
		class Test {		
			public void disp1() {
			System.out.println(a);
				System.out.println(b);
			}
		}
		Test test=new Test();
		test.disp1();
	}

//	static class Test{												//멤버인 클래스
//		public void disp() {
//		System.out.println(a);
//		System.out.println(b);
//		}
//	}

	public static void main(String[] args) {

		InnerClassExam01 ice01 = new InnerClassExam01();
		ice01.dispTest();
		
		// InnerClassExam01 ice01 = new InnerClassExam01();
		// InnerClassExam01.Test t = ice01.new Test();

	//	InnerClassExam01.Test t = new InnerClassExam01().new Test();

	//	t.disp();

	}

	class Test1 { // 스태틱 멤버인 클래스

	}

	void disp() {
		class Test2 { // 함수 안에 있는 클래스 local class
		}
	}
}
