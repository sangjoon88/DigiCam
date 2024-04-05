package arr;

public class SortExam {

	public static void main(String[] args) {
		
		int[] arr = {90,78,100,30,55};
		
		bubbleSort(arr);
		
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		System.out.println(arr[3]);
		System.out.println(arr[4]);
		
	
	}
		
	static void bubbleSort(int[] arr) {
	
		int count = 0;
		int forcount = 0;
		
		for(int i = 0; i<arr.length; i++) {               //왼쪽부터 끝까지 비교하면서 돌건데
			for(int j = 0; j<arr.length-1-i; j++) {       // 한바퀴 돌고 나면 맨 오른쪽에는 큰 숫자가 가는게 확정이 되서 다음바퀴에는 오른쪽 비교 안해 도 됨.
				if (arr[j] > arr[j+1]) {   				// 여기는 바꾸는 작업 > 자바는 직접 바꿀수 없어서 변수하나를 설정해서 복사 복사~
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					
					count++;
				}
				forcount++;
			}		
		}
		System.out.println("Count : " +count);
		System.out.println("ForCount : "+ forcount);
	}
	
	
	}
