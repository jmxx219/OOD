import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 2019136072 손지민
 * @file: GameModel.java 
 * 묵찌바 게임에 필요한 데이터를 유지하고 게임 로직 제공
 */
public class GameModel {
	private ComputerPlayer computer 
		 = new ComputerPlayer(new RandomStrategy()); // 가위바위보할 때 RandomStrategy 이용
//		= new ComputerPlayer(new LastHandBasedStrategy()); // 묵찌빠를 진행할 때 LastHandBasedStrategy 이용
//		= new ComputerPlayer(new LogHandBasedStrategy()); // 묵찌빠를 진행할 때 LogHandBasedStrategy 이용
	
	private HandType currUserHand = HandType.MOOK;
	private boolean playingMookJiBa = false;
	private boolean isUserAttack = false;
	private HandType lastUserHand = HandType.MOOK;
	private HandType lastComputerHand = HandType.MOOK;
	private Map<HandType, ArrayList<HandType>> logUserHand;
	
	public GameModel() {
		logUserHand = new HashMap<HandType, ArrayList<HandType>>();
		for(int i = 0; i < 3; i++) {
			ArrayList<HandType> list = new ArrayList<HandType>();
			logUserHand.put(HandType.valueOf(i), list);
		}
	}
	
	
	// 새 게임을 할 때마다 객체를 생성하는 대신 사용 (상태 초기화)
	public void init() {
		lastUserHand = null;
		currUserHand = HandType.MOOK;
		playingMookJiBa = false;
		isUserAttack = false;
		computer = new ComputerPlayer(new RandomStrategy());
	}
	
	
	// 기본 Getter
	public boolean isUserAttack() {
		return isUserAttack;
	}
	
	public boolean playingMookJiBa() {
		return playingMookJiBa;
	}
	
	public HandType lastUserHand() {
		return lastUserHand;
	}
	
	public void setUserHand(HandType userHand) {
		this.lastUserHand = this.currUserHand;
		this.currUserHand = userHand;
	}
	
	public HandType lastComputerHand() {
		return lastComputerHand;
	}
	
	public Map<HandType, ArrayList<HandType>> logUserHand() {
		return logUserHand;
	}
	
	// 다음 컴퓨터 손 계산함
	public HandType getComputerNextHand() {
		this.lastComputerHand = computer.getHand();
		
		if(playingMookJiBa) computer.setStrategy(new LogHandBasedStrategy());
		
		if(lastComputerHand != null) {
			if(logUserHand.get(lastComputerHand).size() == 0) { // 컴퓨터의 이전 패에 대한 유저의 로그가 아직 쌓이지 않았다면 랜덤으로 컴퓨터 패를 결정한다.
				computer.setStrategy(new RandomStrategy());
			}
			ArrayList<HandType> list = logUserHand.get(lastComputerHand);
			list.add(currUserHand);
			logUserHand.put(lastComputerHand, list); // 컴퓨터의 이전 패에 대해 유저가 낸 패를 저장한다.
			
//			for(int i=0; i<3; i++) {
//				System.out.print(HandType.valueOf(i)+": ");
//				logUserHand.get(HandType.valueOf(i)).forEach(s-> System.out.print(s + ", "));
//				System.out.println();
//			}
		}
			
		return computer.nextHand(this);
	}
	
	// 묵찌바 게임 결과 판단
	// @return 묵찌바 게임의 결
	public GameResult playMookJiBa() {
		if(currUserHand == computer.getHand()) {
			return isUserAttack ? GameResult.USERWIN : GameResult.COMPUTERWIN; 
		}
		else if(!isUserAttack && currUserHand == computer.getHand().winValueOf()) {
			isUserAttack = true;
		}
		else if(isUserAttack && computer.getHand() == currUserHand.winValueOf()) {
			isUserAttack = false;
		}
		return GameResult.DRAW;
	}
	
	// 가위바위보 게임 결과 판단
	// @return 가위바위보 게임의 결과 
	public GameResult playGawiBawiBo() {
		if(currUserHand == computer.getHand().winValueOf()) {
			isUserAttack = true;
			playingMookJiBa = true;
			return GameResult.USERWIN;
		}
		else if(computer.getHand() == currUserHand.winValueOf()) {
			playingMookJiBa = true;
			return GameResult.COMPUTERWIN;
		}
		return GameResult.DRAW; 
	}
}
