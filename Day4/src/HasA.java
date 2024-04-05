class A {
	private int a; /// 인티저인 데이터 타입 a 변수 생성

	public A() {
	} /// 클래스 A 자체의 객체화?

	public void setA(int a) {
		this.a = a;
	} /// set a

	public int getA() {
		return a;
	}
}

public class HasA {

	private String name;
	private A age; // 객체에 접근할 수 있는 레퍼런스를 만든 것 // A클래스에 접근할 수 있는 레퍼런스 변수 age라고 만듦(참조변수) >아직 인스턴스가
					// 생성되지 않은 상태임

	public HasA() {
		name = "";
		age = new A(); // >>생성자 함수
	} // 생성할 때 사용해라~~ 메인에서 사용 되겠지

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age.setA(age);
	} // this.age = age;}
		// this.age.a=age;

	public String getName() {
		return name;
	}

	public int getAge() {
		return this.age.getA();
	}

	public static void main(String[] args) {
		HasA has = new HasA();

		has.setName("Spuerman");
		has.setAge(12342);

		System.out.println(has.getName());
		System.out.println(has.getAge());

	}

}
