
public class Name {										//Name 클래스를 생성

	private String name;                                // 스트링 타입의 네임이라는 필드 생성
	
	public String getName() {						 // 겟 네임, 구조 네임 값을 주는 결과 값으로 줌// 겟셋도 메소드다
		return name;
	}
	
	public void setName(String a) {
		this.name=a;										//this 는 생략 가능
	}
	
}
