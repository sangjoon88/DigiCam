package IsA;

public class MyQueue extends MyStack {
	private int front;
	
	@Override
	public int pop() {
		
		return arr[front++];
	}
}
