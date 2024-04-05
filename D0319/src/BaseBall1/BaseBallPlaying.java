package BaseBall1;
import java.util.Scanner;

public class BaseBallPlaying {
	
	BaseBallRule rule;
	
	public BaseBallPlaying() {
		rule = new BaseBallRule();
	}
	
	public void input(Scanner sc) {

		System.out.print("input three number : ");
		String myAnssc = sc.next();

		
		for (int i = 0; i < 3; i++) {
			rule.getMyAns().add((int) myAnssc.charAt(i)-48);
		}
	}
	
	public void playBall() {
		for (int i = 0; i < 3; i++) {
			if (rule.getAnswer().contains(rule.getMyAns().get(i))) {
				if (rule.getAnswer().get(i).equals(rule.getMyAns().get(i))) {
					int strike = rule.getStrike()+1;
					rule.setStrike(strike);
				} else {
					int ball = rule.getBall()+1;
					rule.setBall(ball);
				}
			}
		}
		int count = rule.getCount()+1;
		rule.setCount(count);
		System.out.println(rule.getStrike() + " strike, " + rule.getBall() + "ball");
	}
	
	public void playStop() {
		if(rule.getStrike()==3) {
			System.out.println("success!! count: "+ rule.getCount());
		} else {
			rule.getMyAns().clear();
			rule.setStrike(0);
			rule.setBall(0);
		}
	}
	
}