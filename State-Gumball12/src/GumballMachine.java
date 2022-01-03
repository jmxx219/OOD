/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * 상태 패턴
 * GumballMachine.java
 * 문맥 클래스
 * State Driven Transition (상태 중심 전이)
 * 상태 객체 메소드에 문맥 전달 버전
 * 상태 기반 전이 방식임에도 상태 객체를 공유할 수 있음
 * @author 김상진
 */
public class GumballMachine{
	private GumballState currentState;
	private int count = 0;
	
	public GumballMachine(int numberGumballs) {
		count = numberGumballs;
 		if(count > 0) currentState = NoCoinState.getInstance();
 		else currentState = SoldOutState.getInstance();
	}	
	public boolean isEmpty(){
		return (count==0);
	}
	public void changeStateTo(GumballState state) {
		currentState = state;
	}
	
	public void insertCoin(){	
		currentState.insertCoin(this);
	}
	public void ejectCoin(){	
		currentState.ejectCoin(this);
	}
	public void turnCrank(){	
		currentState.turnCrank(this);
		currentState.dispense(this);
	}
	public void refill() {
		currentState.refill(this);
	}
	public int getNumberOfGumballs(){
		return count;
	}
	void dispense(){
		System.out.println(count);
		if(count>0) --count;
	}
	void refill(int gumballs) {
		count = gumballs;
	}
}
