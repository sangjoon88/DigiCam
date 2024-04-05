package IsA2;

public class MyStack extends Memory {
	@Override
	public int pop() {
		
		return arr[--top];
	
     }
}