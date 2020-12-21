package Commands;

import logic.Game;

public class ResetCommand extends NoParamsCommand {
	
	protected final static String name = "reset";
	protected final static String shortcut = "r";
	private final static String details = "[r]eset";
	private final static String help = "Starts a new game.";

	public ResetCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.inicializar();
		return false;
	}

	@Override
	public NoParamsCommand getNoParamsCommand() {
		// TODO Auto-generated method stub
		return this;
	}
}
