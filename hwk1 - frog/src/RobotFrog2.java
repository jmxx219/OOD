/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * 
 * 포함관계를 이용하여 설계 및 구현
 * 
 * 1. 장점
 * - 코드 중복을 줄여주고 기존 클래스를 확장하지 않아도 된다.
 * 
 * 2. 단점
 * - 공통 리모컨을 이용할 수 없다.
 */

public class RobotFrog2 {
	private Frog frog = new Frog();
	private int battery = 5;
	
	public void jump(){
		if(this.battery > 0) {
			frog.jump();
			this.battery -= 1;
		}
	}

	public void croak(){
		if(this.battery > 0) {
			frog.croak();
			this.battery -= 1;
		}
	}
	
	public void charge() {
		this.battery = 5;
	}
}
