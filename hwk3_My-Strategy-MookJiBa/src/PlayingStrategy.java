/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 2019136072 손지민
 * @file PlayingStrategy.java
 * 전략 패턴: 전략 Interface
 *  3개의 전략은 모두 필요한 클라이언트의 상태가 조금씩 다르다. 
 *  랜덤전략은 클라이언트 상태가 필요하지 않고, LastHandBasedStrategy 전략은 lastUserHand와 isUserAttack 상태가 필요하다.
 *  내가 짠 LogHandBasedStrategy 전략은 lastComputerHand와 logUserHand가 필요하다.
 *  이와 같이 전략이 추가될 수록 필요한 클라이언트의 정보는 달라질 수 있다. 
 *  데이터로 클라이언트이 상태를 전달하게 될 경우, 매개변수가 늘어나고 전략이 추가될 때마다 모든 전략의 매개변수를 수정해야하는 일이 발생할 수 있다.
 *  이 때문에 현재의 3가지 전략에서도 필요한 클라이언트의 상태가 모두 다르고 전략이 추가할 때 마다 매개변수를 모두 수정해줘야하는 일이 발생한다.
 *  따라서 클라이언트의 상태를 데이터로 전달하지 않고 클라이언트를 전달하여 세 가지 전략을 모두 사용할 수 있도록 하였다.
 */
public interface PlayingStrategy {
	HandType computeNextHand(GameModel gameModel);
}
