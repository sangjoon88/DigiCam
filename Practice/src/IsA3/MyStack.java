package IsA3;

public class MyStack extends Memory {

	public int pop() {                   //추상메소드였던 pop을 오버라이드
		return arr[--top];                // top 값을 줄이면서 arr 값을 int로 리턴
	}
}
