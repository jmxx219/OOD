/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file SteroOnWithCDCommand.java: 오디오의 CD 켜기 명령
 */
public class SteroOnWithCDCommand implements Command {
	private Stero stero;
	public SteroOnWithCDCommand(Stero stero){
		this.stero = stero;
	}
	@Override
	public void execute() {
		stero.on();	
		stero.setInput(Stero.InputType.CD);
		stero.setVolume(11);
	}
	@Override
	public void undo() {
		stero.off();
	}
}
