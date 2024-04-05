class B {
	private int a;

	public B() {
	}

	public void setB(int a) {
		this.a = a;
	}

	public int getB() {
		return a;
	}
}

public class HasAExam {

	private String name;
	private B age;

	public HasAExam() {
		name = "";
		age = new B();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age.setB(age);
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return this.age.getB();
	}

	public static void man(String[] args) {
		HasAExam has = new HasAExam();

		has.setName("Superman");
		has.setAge(1234);

		System.out.println(has.getName());
		System.out.println(has.getAge());
	}

}