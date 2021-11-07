/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file Command.java: 명령 객체들이 제공해야 하는 interface 
 */
public interface Command {
	void execute();
	void undo();
	// default boolean undoable() { return true; }
}
