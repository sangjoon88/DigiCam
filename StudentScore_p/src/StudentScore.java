
public class StudentScore {

	Name name; // Name타입의 이름

	Subject kor; // subject 타입의 kor.
	Subject eng; // subject 타입의 eng
	Subject math; //  subject 타입의 math.  > 이때는 자리만 만들어져 있는거고  > 아래 생성자가 실행되면 그때 실제 객체가 생김
	
	int total; // 총첨이랑
	float avg; // 평균
	
	public StudentScore() {    //생성자 메소드 // 왜 이렇게 따로 생성자를 만드는가? studentScore의 타입을 어떻게 사용할지 모르기 때문에
		name = new Name();   //Name 클래스에 정의되있는 인스턴스가 생성 됨
		kor = new Subject(); // kor 이라는 참조변수가 있는 subject 인스턴스가 만들어짐
		eng = new Subject();
		math = new Subject();
		total = 0;
		avg = 0;
	}
	
	public StudentScore(Name name, Subject kor, Subject eng, Subject math) {
		this.name = name;
		this.kor=kor;
		this.eng=eng;
		this.math=math;
	}
	
	public void setName(String name) {
		this.name.setName(name);
	}
	
	public void setKor(int kor) {
		this.kor.setSubject(kor);
	}
	
	public void setMath(int math) {
		this.math.setSubject(math);
	}
	
	public void setEng(int eng) {
		this.eng.setSubject(eng);
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public void setAvg(int avg) {
		this.avg = avg;
	}
	
	public String getName() {
		return this.name.getName();
	}
	public int getKor() {
		return this.kor.getSubject();
	}
	public int getMath() {
		return this.math.getSubject();
	}
	public int getEng() {
		return this.eng.getSubject();
	}
	
	public int getTotal() {
		return total = getKor()+getMath()+getEng();
	}
	public float getAvg() {
		return avg = getTotal() / 3.f ;
	}
	
	public static void main (String [] args) {
		StudentScore stu=new StudentScore();
		
		stu.setName("이상준");
		stu.setMath(100);
		stu.setKor(90);
		stu.setEng(89);
		
		System.out.println(stu.getName());
		System.out.println(stu.getMath());
		System.out.println(stu.getKor());
		System.out.println(stu.getEng());
		System.out.println(stu.getTotal());
		System.out.println(stu.getAvg());
		
	}
	
	
}
