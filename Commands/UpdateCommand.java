package Commands;

import logic.Game;

public class UpdateCommand extends NoParamsCommand {

	protected final static String name = "";
	protected final static String shortcut = "";
	private final static String details = "";
	private final static String help = "Skips one cycle.";

	public UpdateCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		game.noCommandExe();
		game.update();
		return true;
	}

	@Override
	public NoParamsCommand getNoParamsCommand() {
		return new UpdateCommand();
	}
}
