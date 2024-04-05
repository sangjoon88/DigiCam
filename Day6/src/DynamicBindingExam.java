class Halbe {
	public void disp () {
		System.out.println("Halbe");
	}
}

class Abe extends Halbe {
	public void disp () {
		System.out.println("Abe");
		
	public void dispAbe() {
		System.out.println("")
	}
}


class Me extends Abe {
	public void disp () {
		System.out.println("Me");
}

public class DynamicBindingExam {
	public static void main(String[] args) {
		Me me= new Me();
		me.disp();
		
		Abe abe = new Abe();
		abe.disp();
		
		Halbe halbe = new Halbe();
		
	}
}

}
