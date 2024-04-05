

	public class  Exception9 {
		public static void main(String[] args) {
	    	String data = null;
	       
	    	try {
	    	System.out.println(data.toString());
	    	} catch (NullPointerException npe) {
	    		System.out.println("null에 접근했습니다. 확인하세요");
	    	}
	    }
	 }

	// NPE : NullPointerException 에러
	//객체 참조가 없는 상태, null 값을 가지고 있는 참조 변수로 객체 접근 연산자인 도트를 사용했을 때 발생
	//해당 객체가 null 인 상태에서의 접근을 했을 때 해당 값이 null에 대한 접근을 하여 발생하는 에러로 객체가 없는 상타에서
	// 객체를 사용하려 했으나 해당 객체는 없는 상태이기 때문에 발생하는 에러
	
	
