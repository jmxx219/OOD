import java.util.Stack;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 */
public class CommandManager {
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();

	public void execute(Command command) {
		undoStack.add(command);
		command.execute();
		redoStack.clear();
	}
	public boolean undo() {
		if(!undoStack.isEmpty()) {
			Command command = undoStack.pop();
			redoStack.add(command);
			command.undo();
			return true;
		}
		return false;
	}
	
	public boolean redo() {
		if(!redoStack.isEmpty()) {
			Command command = redoStack.pop();
			undoStack.add(command);
			command.redo();
			return true;
		}
		return false;
	}
}
