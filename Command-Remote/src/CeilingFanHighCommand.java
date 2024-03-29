/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file CeilingFanHighCommand.java: 천장형 선풍기 가장 빠른 속도로 켜기 명령
 */
public class CeilingFanHighCommand implements Command {
	private CeilingFan fan;
	private CeilingFan.SPEED previousSpeed = CeilingFan.SPEED.OFF;
	public CeilingFanHighCommand(CeilingFan fan){
		this.fan = fan;
	}
	@Override
	public void execute() {
		if(fan.getSpeed()!=CeilingFan.SPEED.HIGH) {
			previousSpeed = fan.getSpeed();
			fan.setSpeed(CeilingFan.SPEED.HIGH);
		}
	}
	@Override
	public void undo() {
		fan.setSpeed(previousSpeed);
	}
}
