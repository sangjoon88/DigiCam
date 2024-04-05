class AA {
	private int a; // 10

	public AA(int a) {
		this.a = a;
	}

	public int getA() {
		return a; 
	}

}

////
class BB extends AA {
	private int b; // 20

	public BB(int a, int b) {
		super(a);
		this.b = b;

	}

	public int getB() {
		return b;
	}
}


/////
class CC extends BB {

	private int c; // 30

	public CC(int a, int b, int c) {

		super(a, b);
		this.c = c;

	}

	public int getC() {
		return c;
	}
}


/////
public class SuperExam01 {

	public static void main(String[] args) {

		CC cc = new CC(10, 20, 30);

		System.out.println(cc.getA());
		System.out.println(cc.getB());
		System.out.println(cc.getC());
	}

}
