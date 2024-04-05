
public class ClassExam02 {

	private int a; //field
	
	
	public ClassExam02() {				//디폴트 생성자
		System.out.println("디폴트생성자");
	}
	
	public ClassExam02(int aa) {				//디폴트 생성자  >> 오버로딩 때문에 만든거겠지?
		a = aa;
	}
	
	
	//method : 
	// 문법은 아니지만 문법처럼 쓰이는 
	// setter(외부에서 주는 데이터를 입력함수/필드에 있는 값을), getter(외부에서 필드값을 얻어가는 함수/필드에 있는 값을)
	
	public void setA(int num) {    // set으로 써라, 입력만할거니까 보이드(리턴할 일이 없음)
		a = num;
	}
	
	public int getA() {
		return a;
	}
	
	
	public static void main(String[] args) {

		ClassExam02 ce2 = new ClassExam02(); // 생성자 호출
		
		ClassExam02 ce3 = new ClassExam02(10); // 생성자 호출
		
		ClassExam02 ce4 = new ClassExam02(5600); // 생성자 호출
		
		ce2.setA(1234)   ;//ce2.a=500;
		
		System.out.println(ce2.getA()); //(ce2.a);
		System.out.println(ce3.a);
		System.out.println(ce4.a);
	}

}
