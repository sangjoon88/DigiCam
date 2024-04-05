
public class InnerExam6 {

	private int a;
	
	public void dispInter() {
		new AAA() {
			
			public void disp() {
				System.out.println(a);
			}
		}
	} .disp();;
	
	public static void main(String[] args) {
		InterExam01 inter = new InterExam01();
		inter.dispInter();
	}

}
