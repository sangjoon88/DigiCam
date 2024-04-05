
public class While_gugu {

	public static void main(String[] args) {

		int i = 1;

		while (i < 10) {
			int j = 2;
			while (j < 10) {
				System.out.print(j + "*" + i + "=" + (i * j) + "\t");
				j++;
			}
			i++;
			System.out.println();
		}

//		for(int i =1; i<10;i++) {
//			for(int j = 2; j<10; j++) {
//				System.out.print(j+"*"+i+"="+(i*j)+"\t");
//			}
//				System.out.println();
//		}
	}
}