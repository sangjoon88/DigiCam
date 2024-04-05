package Sale;

public class Calculate {
	EmpInfo calc = new EmpInfo();
	int num;
	private int salBasic;
	private int salPosition;
	private int salLunch;
	private double salTax;
	Admin ad = new Admin();

	public Calculate() {

	}

	public double getSalTax() {
		return salTax;
	}

	public void setSalTax(double salTax) {
		this.salTax = salTax;
	}

	public int getSalLunch() {
		return 2400000;
	}

	public void setSalLunch(int salLunch) {
		this.salLunch = salLunch;
	}

	public int getSalPosition() {
		return salPosition;
	}

	public void setSalPosition(int salPosition) {
		this.salPosition = salPosition;
	}

	public int getSalBasic() {
		return salBasic;
	}

	public void setSalBasic(int salBasic) {
		this.salBasic = salBasic;
	}

	public void setCal(String p) {

		switch (p) {
		case "사원":
			this.salBasic = 30000000;
			this.salPosition = 0;
			break;
		case "대리":
			this.salBasic = 35000000;
			this.salPosition = 2000000;
			break;
		case "과장":
			this.salBasic = 40000000;
			this.salPosition = 3500000;
			break;
		case "차장":
			this.salBasic = 50000000;
			this.salPosition = 5000000;
			break;
		case "부장":
			this.salBasic = 65000000;
			this.salPosition = 7000000;
			break;
		case "대표":
			this.salBasic = 80000000;
			this.salPosition = 10000000;
			break;
		}
	}

	public int getCal() {
		return (int) ((double) (getSalBasic() * Math.pow(1.03, ad.getYears())) + getSalPosition() + getSalLunch());

	}

	public void setReal(String p) {
		setCal(p);
		if (getCal() <= 14000000) {
			setSalTax(0.06);
		} else if (getCal() > 14000000 && getCal() <= 50000000) {
			setSalTax(0.15);
		} else if (getCal() > 50000000 && getCal() <= 88000000) {
			setSalTax(0.24);
		} else if (getCal() > 88000000 && getCal() <= 150000000) {
			setSalTax(0.35);
		} else if (getCal() > 150000000 && getCal() <= 300000000) {
			setSalTax(0.38);
		} else if (getCal() > 300000000 && getCal() <= 500000000) {
			setSalTax(0.4);
		} else if (getCal() > 500000000) {
			setSalTax(0.45);
		}
	}

	public double getReal() {
		return getSalTax();
	}

	public void real() {
		System.out.print("세후급여 : ");
		System.out.println(getCal() - (int) ((double) getCal() * getSalTax()));
	}

	public void calcPrt() {
		System.out.println("세전급여 : " + getCal());
	}

	public void exit() {
		System.out.println("퇴직금 : " + getCal() / 12 * ad.getYears());
	}

}