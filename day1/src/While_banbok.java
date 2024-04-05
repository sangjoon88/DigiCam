
public class While_banbok {

	public static void main(String[] args) {

		/*
		 * 사각형 갯수, 접은 함수
		 * 
		 * 1,0 500개 넘어가면
		 */

		int rect = 1;
		int count = 0;
		while (rect < 500) {
			rect = rect * 2; // rect <<=1;
			count++;
		}

		System.out.println(count);
		System.out.println(rect);

		// Scanner sc = new Scanner(System.in);
		// int num1 = sc.nextInt();

		// for(int i =num1 ; i < 600 ; Math.pow(2, num1)) {
		// System.out.println(i);
	}
}

