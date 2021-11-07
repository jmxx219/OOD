import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * @file: LogHandBasedStrategy.java 전략패턴: 
 * 이전에 컴퓨터가 낸 패를 보고 유저는 패를 내는데 컴퓨터가 내는 각 패에 대해서 유저는 같은 패를 다시 낼 확률이 높다고 가정한다. 
 * 따라서 컴퓨터가 낼 수 있는 세 가지 패에 따라 유저가 냈었던 패를 유지(logUserHand)한다. 이 logUserHand는 유저의 패 정보를 저장해나간다.
 * 이를 이용하여 컴퓨터의 이전 패에 대해 유저가 낸 패들을 담은 배열에서 하나를 뽑아 패를 낸다.
 * 가장 많이 냈다면 배열에서도 가장 많이 담겨있기 때문에 배열에서 하나를 랜덤으로 뽑게 된다면, 가장 많이 냈던 패를 또 다시 뽑을 확률이 높다.
 * 만약 컴퓨터의 이전 패에 대한 정보가 아무것도 담겨있지 않는다면 컴퓨터는 랜덤으로 패를 내도록 한다.
 */
public class LogHandBasedStrategy implements PlayingStrategy {
	@Override
	public HandType computeNextHand(GameModel gameModel) {
		ArrayList<HandType> log = gameModel.logUserHand().get(gameModel.lastComputerHand());
		
		HandType userNextEx = log.get(ThreadLocalRandom.current().nextInt(log.size()));
		
		
		if(!gameModel.isUserAttack()) { // 컴퓨터가 공격일 때, 컴터는 예상한 유저의 패와 같은 패를 내야 이긴다.
			return userNextEx;
		}
		return userNextEx.winValueOf(); // 컴퓨터가 수비일 때, 컴터는 우선권을 가져오는게 최선이므로 예상한 유저 패를 이기는 패를 낸다.
	}
}
