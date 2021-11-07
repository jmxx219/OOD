/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진 
 * 탬플릿 메소드 패턴
 * PokerGame.java
 * 포커 게임: 5장씩 나누어줌, 조커는 사용하지 않음
 */
public class PokerGame extends CardGame {
	public PokerGame() {
		super(5);
	}
	@Override
	protected void dealCards() {
	}
}
