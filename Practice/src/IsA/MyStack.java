package IsA;

public class MyStack extends Memory {
	
	@Override
	public int pop() {
		
		return arr[--top];
	
     }
}