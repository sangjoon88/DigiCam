import java.util.*;

public class BaseballGame {

	private int strike; // 스트라이크
	private int ball; // 볼
	private int t; // 시도 횟수
	private ArrayList<Integer> com;
	private ArrayList<Integer> me;

	public BaseballGame() {
		this.strike = 0;
		this.ball = 0;
		this.t = 1;
		this.com = new ArrayList();
		this.me = new ArrayList();
	}

	public static void main(String[] args) {

		BaseballGame bs = new BaseballGame();

		int index = 0; // 여기는 컴퓨터가 배열에 자기 숫자를 넣는 부분
		while (index != 3) { // ArrayList 를 사용하기 떄문에 3칸을 만들려고
			int input = (int) (Math.random() * 10); // 센스를 발휘.. Math 클래스의 ramdom 메소드를 사용! 스태틱으로 자바랭에 올라가 있음
			if (bs.com.contains(input)) { // 랜덤으로 생긴숫자가 com에 있으면 중복 방지해야하니까 다음줄에서 다시해!!
				continue; // continue ?
			} else {
				bs.com.add(input); // 중복이 없으면 넣어 add 는 순서대로 차곡차곡 넣어줌
				index++; // 다음번도 수행해야하니
			}
		}

		Scanner sc = new Scanner(System.in);

		do {
			System.out.println(bs.com); // 볼려고 넣어놧어
			System.out.println("input three number : ");

			String mynum = sc.next(); // 내가 공격시도 하는 세글자를 넣을건데 스트링으로 받을꺼야
			for (int i = 0; i < 3; i++) {
				bs.me.add((int) mynum.charAt(i) - 48); // 받을 세글자를 세개로 쪼개서 아스키코드를 인트로 전환한다음에 me라는 배열에 넣을것임(-48) 센스
			}

			for (int j = 0; j < 3; j++) { // com과 me 를 비교 할거야 . 스트라이크 조건은 컨테인+이퀄
				if (bs.com.contains(bs.me.get(j))) { // 볼 조건은 컨테인하기만 하면 됨.
					if (bs.com.get(j).equals(bs.me.get(j))) {
						bs.strike++;
					} else {
						bs.ball++;
					}
				}
			}

			System.out.println(bs.strike + "strike," + bs.ball + "ball");

			if (bs.strike == 3) {
				System.out.println("Succcess!! Out count: " + bs.t);
			} else {
				bs.me.clear();
				bs.strike = 0;
				bs.ball = 0;
			}

		} while (bs.strike != 3);

	}
}

// ArrayList에 contain이 있다는거
// ArrayList  add는 순차적으로 들어간다
// Math 클래스에 ramdom 메소드
//bs.me.add((int) mynum.charAt(i)-48); >
