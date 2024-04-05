package BaseBall1;

import java.util.Scanner;

public class Baseball1 {

	public static void main(String[] args) {
		BaseBallPlaying game = new BaseBallPlaying();
		int index = 0;
		Scanner sc = new Scanner(System.in);

		while (index != 3) {
			int rnd = (int) (Math.random() * 10);
			if (game.rule.getAnswer().contains(rnd)) {
				continue;
			} else {
				game.rule.getAnswer().add(rnd);
				index++;
			}
		}

		do {
//			System.out.println(game.rule.getAnswer());
			game.input(sc); // 플레이어 숫자 입력
			game.playBall(); // Strike&Ball 판정
			game.playStop(); // 게임 지속여부
//			System.out.println(game.rule.getMyAns());
		} while (game.rule.getStrike() != 3);

	}
}