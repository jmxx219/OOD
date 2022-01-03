/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2021년도 2학기
 * 상태 패턴
 * NoCoinState.java
 * 상태 객체
 * @author 김상진
 */
public class NoCoinState implements GumballState {
	private static final NoCoinState instance = new NoCoinState();
	private NoCoinState(){}
	public static NoCoinState getInstance(){
		return instance;
	}
	@Override
	public void insertCoin(GumballMachine gumballMachine) {
		System.out.println("동전이 삽입되었");
		gumballMachine.changeStateTo(HasCoinState.getInstance());
	}

	@Override
	public void ejectCoin(GumballMachine gumballMachine) {
		System.out.println("반환할 동전 없음");
	}

	@Override
	public void turnCrank(GumballMachine gumballMachine) {
		System.out.println("동전이 없어 손잡이를 돌릻 수 없음");
	}

	@Override
	public void dispense(GumballMachine gumballMachine) {
		System.out.println("동전을 투입해야 구입할 수 있음");
	}
	
	@Override
	public void refill(GumballMachine gumballMachine) {
		System.out.println("껌볼이 없는 경우에는 껌볼을 채울 수 있음");
	}
}
