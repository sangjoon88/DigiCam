public class StudentScoreHasA {
	//이름 -> Name클래스, 과목 -> Subject클래스	//총점, 평균
	
	Name name;
	Subject kor;
	Subject eng;
	Subject math;
	//Subject scor[];
	int total;
	float avg;
	
	public StudentScoreHasA() {// 필드에서 setName setAge 에 사용하려면 객체 생성해야 해서 생성자 선언함 (main이랑은 별개)
		name = new Name();
		kor = new Subject();
		math = new Subject();
		eng = new Subject();
		total = 0; avg = 0;
	}
	
	public StudentScoreHasA(Name name, Subject kor, Subject eng, Subject math) { // 위에거와 같은데 어떤형식으로 할지 모르니 만든것 (사용자가 선택함)
		this.name = name;
		this.kor = kor;
		this.math = math;
		this.eng = eng;
	} 	
	public void setName(String name) {
		this.name.setName(name);
	}	// a 는 private a 여서 접근할 수 없음. 접근 가능한 건 public setA getA 임.
	public void setKor(int kor) {
		this.kor.setSubject(kor);
	}
	public void setMath(int math) {
		this.eng.setSubject(math);
	}
	public void setEng(int eng) {
		this.math.setSubject(eng);
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
	
	public static void main(String[] args) {
		
		StudentScoreHasA stu = new StudentScoreHasA();
		
		
		stu.setName("Superman");
		stu.setMath(100);
		stu.setKor(90);
		stu.setEng(70);
		
		System.out.println(stu.getName());
		System.out.println(stu.getMath());
		System.out.println(stu.getKor());
		System.out.println(stu.getEng());
		System.out.println(stu.getTotal());
		System.out.println(stu.getAvg());
	}
}
