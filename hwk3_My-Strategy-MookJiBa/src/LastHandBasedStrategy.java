import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * @file: LastHandBasedStrategy.java 전략패턴: 구체적인 전략 클래스 지난 묵찌바의 결과가 사용자 결정에 많은
 *        영향을 준다
 */
public class LastHandBasedStrategy implements PlayingStrategy {
	@Override
	public HandType computeNextHand(GameModel gameModel) {

		List<HandType> predictNextUserHand = new ArrayList<HandType>();
		for(int i =0; i < 3; i++) {
			if(HandType.valueOf(i) == gameModel.lastUserHand()) {
				predictNextUserHand.add(gameModel.lastUserHand());
			}
			else {
				predictNextUserHand.add(HandType.valueOf(i));
				predictNextUserHand.add(HandType.valueOf(i));
			}
		}
		
		HandType userNextEx = predictNextUserHand.get(ThreadLocalRandom.current().nextInt(5));
		
		if(!gameModel.isUserAttack()) { // 컴퓨터가 공격일 때, 컴터는 유저와 같은 패를 내야 이긴다.
			return userNextEx;
		}
		return userNextEx.winValueOf(); // 컴퓨터가 수비일 때, 컴터는 우선권을 가져오는게 최선이므로 유저 패를 이기는 패를 낸다.
	}
}
