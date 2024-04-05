package Sale;

public class EmpInfo {
	private int number;
	private String name;
	private EmpDept position;
	private EmpDept empDept;
	private int conyn;
	private int pw;
	private boolean admin;
	private String joinYmd;

	public EmpInfo() {
		position = new EmpDept();
		empDept = new EmpDept();

	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;

	}

	public void setEmpDept(String empDept) {
		this.empDept.setDe(empDept);
	}

	public String getEmpDept() {
		return empDept.getDe();
	}

	public void setPosition(String position) {
		this.position.setPo(position);
	}

	public String getPosition() {
		return position.getPo();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getConyn() {
		return conyn;
	}

	public void setConyn(int conyn) {
		this.conyn = conyn;
	}

	public int getPw() {
		return pw;
	}

	public void setPw(int pw) {
		this.pw = pw;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;

	}

	public String getJoinYmd() {
		return joinYmd;
	}

	public void setJoinYmd(String joinYmd) {
		this.joinYmd = joinYmd;
	}

	public void info() {
		System.out.println("1.이름 :" + getName());
		System.out.println("2.사원번호 :" + getNumber());
		System.out.println("3.부서 :" + getEmpDept());
		System.out.println("4.직책 :" + getPosition());
		System.out.println("5.입사일 :" + getJoinYmd());
		System.out.println("6.관리자여부 :" + isAdmin());

	}

}
