public class Exception8 {
	public static void main(String[] args) {
		int[] arr = new int[3];
		try {
			for (int i = 0; i <= 3; i++) {
				arr[i] = i;
			}
		} catch (ArrayIndexOutOfBoundsException ob) {
			System.out.println("배열의 크기를 초과했습니다");
		}
	}
}

//ArrayIndexOutofBoundsException : 배열이 할당된 배열의 인덱스 범위를 초과해서 사용할 경우 발생하는 에러