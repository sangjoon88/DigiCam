package arr;

public class InsertSort {
	
	public static void main(String[] args) {
		int data[] = new int[1000];

		for (int i = 0; i < 1000; i++) {
			data[i] = (int) (Math.random() * 1000);
		}

		insertionSort(data, 1000);

		for (int i = 0; i <= 998; i++) {
			if (data[i] > data[i + 1]) {
				System.out.println("Error");
			}
			System.out.println(data[i]);
		}

	}

	public static void insertionSort(int[] data, int num) {
		for (int i = 1; i < num; i++) {
			int j;
			int tmp = data[i];
			for (j = i; j > 0 && data[j - 1] > tmp; j--) {
				data[j] = data[j - 1];
			}
			data[j] = tmp;
		}
	}
}
