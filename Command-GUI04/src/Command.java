/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * @file Command.java: 명령 패턴에서 명령 interface
 */
public interface Command {
	void execute();
	void undo();
}
