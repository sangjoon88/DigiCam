package studentScoreLink;

public class StudentScore {
		//이름은 name 클래스 
		//과목=> subject
		//총점 평균 여기서 연산
		
		private Name name;
		private Subject kor;
		private Subject mat;
		private Subject eng;
		StudentScore next;
		int total;
		double avg;
		
	public StudentScore() {
		name = new Name();
		kor = new Subject();
		mat = new Subject();
		eng = new Subject();
	};

	public StudentScore(Name name, Subject kor, Subject eng, Subject mat) {
		this.name = name;
		this.kor = kor;
		this.mat = mat;
		this.eng = eng;
	}
		
		public String getName() {
			return this.name.getName();
		}

		public void setName(String name) {
			this.name.setName(name);
		}

		public int getKor() {
			return this.kor.getScore();
		}

		public void setKor(int kor) {
			this.kor.setScore(kor);
		}

		public int getMat() {
			return this.mat.getScore();
		}

		public void setMat(int mat) {
			this.mat.setScore(mat);
		}

		public int getEng() {
			return this.eng.getScore();
		}

		public void setEng(int eng) {
			this.eng.setScore(eng);
		}
		
		public int getTotal() {
			return this.kor.getScore()+this.mat.getScore()+this.eng.getScore();
		}
		
		public double getAvg() {
			return ((double)this.kor.getScore()+this.mat.getScore()+this.eng.getScore())/3;
		}

		public static void main(String[] args) {
			//이름 국어 영어 수학 총점 평균 
			
			StudentScore stu = new StudentScore();
			stu.setName("Superman");
			stu.setKor(90);
			stu.setMat(100);
			stu.setEng(70);
			System.out.println(stu.getName());
			System.out.println(stu.getKor());
			System.out.println(stu.getMat());
			System.out.println(stu.getEng());
			System.out.println(stu.getTotal());
			System.out.println(stu.getAvg());
		}
}
