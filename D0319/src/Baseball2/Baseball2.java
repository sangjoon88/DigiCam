import java.util.*;

public class Baseball2 {	
	Setting strike;
	Setting ball;
	Setting t;
	Setting com;
	Setting me;
	
	public Baseball2() {
		strike = new Setting();
		ball = new Setting();
		t = new Setting();
		com = new Setting();
		me = new Setting();
	}
	
	public Baseball2(Setting strike, Setting ball, Setting t, Setting com, Setting me) {
		this.strike = strike;
		this.ball = ball;
		this.t = t;
		this.com = com;
		this.me = me;
	}
	

	public static void main(String[] args) {
	
	}
}