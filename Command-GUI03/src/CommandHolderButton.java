import javafx.scene.control.Button;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * @file Command.java: 명령 패턴에서 명령 interface
 * 명령 객체를 유지할 수 있도 버튼 클래스 확장
 */
class CommandHolderButton extends Button implements Command{
	private Command command;
	public CommandHolderButton(String text) {
		super(text);
	}
	public void setCommand(Command command) {
		this.command = command;
	}
	public Command getCommand() {
		return command;
	}
	@Override
	public void execute() {
		command.execute();
	}
	@Override
	public void undo() {
		command.undo();
	}
}