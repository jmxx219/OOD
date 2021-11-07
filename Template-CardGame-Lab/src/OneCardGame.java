/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진 
 * 탬플릿 메소드 패턴
 * OneCardGame.java
 * 원카드 게임: 7장씩 나누어 줌, 2장의 조커 포함
 */
public class OneCardGame extends CardGame {
	public OneCardGame() {
		super(7);
	}
	@Override
	protected void dealCards() {
	}
	@Override
	protected boolean needJokerCards() {
		return true;
	}
}
