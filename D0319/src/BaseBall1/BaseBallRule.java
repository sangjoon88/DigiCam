package BaseBall1;
import java.util.ArrayList;
import java.util.Scanner;

public class BaseBallRule {

	private ArrayList<Integer> answer; 
	private ArrayList<Integer> myAns; 
	private int index;
	private int count;
	private int strike;
	private int ball;
	
	public BaseBallRule() {
		this.answer = new ArrayList<Integer>(3);
		this.myAns = new ArrayList<Integer>(3); 
		this.index = 0;
		this.count = 0;
		this.strike = 0;
		this.ball = 0;
	}

	public ArrayList<Integer> getAnswer() {
		return answer;
	}
	public void setAnswer(ArrayList<Integer> answer) {
		this.answer = answer;
	}
	public ArrayList<Integer> getMyAns() {
		return myAns;
	}
	public void setMyAns(ArrayList<Integer> myAns) {
		this.myAns = myAns;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStrike() {
		return strike;
	}
	public void setStrike(int strike) {
		this.strike = strike;
	}
	public int getBall() {
		return ball;
	}
	public void setBall(int ball) {
		this.ball = ball;
	}
	
	
}