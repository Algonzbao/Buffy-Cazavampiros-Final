package Commands;

import logic.Game;

public class ExitCommand extends NoParamsCommand {
		protected final static String name = "exit";
		protected final static String shortcut = "e";
		private final static String details = "[e]xit";
		private final static String help = "Terminates the program.";

	public ExitCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.StopPlaying();
		return false;
	}
	
	public NoParamsCommand getNoParamsCommand() {
		// TODO Auto-generated method stub
		return new ExitCommand();
	}
}
