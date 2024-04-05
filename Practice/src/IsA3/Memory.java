package IsA3;

public abstract class Memory {

	protected int [] arr;  // 배열 생성
	protected int top; // 선생님은 왜 탑이라고 붙였을까...
	
	public Memory() {                 //메모리라는 생성자 설정
		arr=new int[5];  
		top=0;
	}
	
	public void push(int data) {
		arr[top++] = data;					// push라는 메소드 설정 >  top 증감연산자 data 가 들어갈 때 마다 top도 늘어남
	}
	
	public abstract int pop();  // 추상메소드 팝 > sub class에서 완성해서 사용할 예정
	
}
