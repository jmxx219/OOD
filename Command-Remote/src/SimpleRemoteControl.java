/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file SimpleRemoteControl.java: 단순 만능 리모컨
 * 버튼 수 10개: On 5개, Off 5개, undo 1개 
 */
public class SimpleRemoteControl {
	private Command[] onCommands = new Command[5];
	private Command[] offCommands = new Command[5];
	private Command undoCommand;
	
	public SimpleRemoteControl(){
		for(int i=0; i<onCommands.length; i++) {
			onCommands[i] = NoCommand.unique;
			offCommands[i] = NoCommand.unique;
		}
		undoCommand = NoCommand.unique;
	}
	public void setCommand(int slot, Command onCommand, Command offCommand){
		if(slot<0||slot>=onCommands.length) 
			throw new IndexOutOfBoundsException();
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}
	public void onButtonWasPressed(int slot){
		if(slot<0||slot>=onCommands.length) 
			throw new IndexOutOfBoundsException();
		onCommands[slot].execute();
		undoCommand = onCommands[slot];
	}
	public void offButtonWasPressed(int slot){
		if(slot<0||slot>=onCommands.length) 
			throw new IndexOutOfBoundsException();
		offCommands[slot].execute();
		undoCommand = offCommands[slot];
	}
	// 마지막으로 실행한 명령을 undo 
	public void undoButtonPressed() {
		undoCommand.undo();
		undoCommand = NoCommand.unique;
	}
}
