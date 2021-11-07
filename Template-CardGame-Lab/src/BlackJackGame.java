
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진 
 * 탬플릿 메소드 패턴
 * @file BlackJackGame.java
 * 블랙잭 게임: 2장씩 나누어 줌, 조커를 사용하지 않음
 */
public class BlackJackGame extends CardGame {
	public BlackJackGame() {
		super(2);
	}
	@Override
	protected void dealCards() {
	}
}
