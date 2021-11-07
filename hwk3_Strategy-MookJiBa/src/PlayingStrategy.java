/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 2019136072 손지민
 * @file PlayingStrategy.java
 * 전략 패턴: 전략 Interface
 */
public interface PlayingStrategy {
	HandType computeNextHand(GameModel gameModel);
}
