import java.util.Arrays;

public class MacroCommand implements Command {
	private Command[] commands;
	
	public MacroCommand(Command... commands) {
		this.commands = commands;
	}
	@Override
	public void execute() {
		Arrays.stream(commands).forEach(Command::execute);
	}

	@Override
	public void undo() {
		for(int i=commands.length-1; i>=0; --i)
			commands[i].undo();
		/*
		List<Command> rev = Arrays.asList(commands);
		Collections.reverse(rev);
		rev.forEach(Command::undo);
		*/
	}

}
