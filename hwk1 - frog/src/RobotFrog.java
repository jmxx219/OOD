/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * 
 * 상속을 이용하여 설계 및 구현
 * 
 * 1. 장점
 * - 코드 중복을 줄여주고 코드를 재사용할 수 있게 해준다.
 * - 부모 타입을 이용하여 다양한 종류의 자식을 공통 리모컨으로 처리할 수 있다.
 * 
 * 2. 단점
 * - 자식 클래스는 자신의 필요와는 상관없이 부모 클래스의 모든 메소드를 상속받는다.
 * - subclassing만 되고 subtyping은 보장되지 않을 수 있다.
 */

public class RobotFrog extends Frog {
	private int battery = 5;
	
	public void jump(){
		if(this.battery > 0) {
			super.jump();
			this.battery -= 1;
		}
	}

	public void croak(){
		if(this.battery > 0) {
			super.croak();
			this.battery -= 1;
		}
	}
	
	public void charge() {
		this.battery = 5;
	}
}