

	public class GenericEx1Main{
		public static void main(String[] args){
		GenericEx1<String> t = new GenericEx1<String>();

		String[] ss = {"애","아","서"};
		t.set(ss);
		t.print();
		/* 좋은 방법이 아님
		09 GenericEx1 t1 = new GenericEx1();
		10 Integer[] s = {1,2,3};
		11 t1.set(s);
		12 t1.print(); */
		 }
		}
