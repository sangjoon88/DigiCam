package IsA2;

public abstract class Memory {
	

		protected int []arr;
		protected int top;
		
		public Memory() {
			arr = new int[5];
			top = 0;
		}
		
		public void push(int data) {
			arr[top++] = data;
		}
		
		public abstract int pop();
		
	}

