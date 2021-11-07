/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file NoCommand.java: 널 명령 객체, dummy 객체
 */
public class NoCommand implements Command {
	public static final NoCommand unique = new NoCommand();
	private NoCommand() {}
	@Override
	public void execute() {}
	@Override
	public void undo() {}
}
