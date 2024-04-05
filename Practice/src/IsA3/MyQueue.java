package IsA3;

public class MyQueue extends Memory{
	private int front;                       //앞에서 부터 뺴야하니까 프론트라는 변수만들고
	
	public int pop() {                 //추상매소드 만들고
		return arr[front++];           // 앞에서 뺄꺼니까
	}
	
}
