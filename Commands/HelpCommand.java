package Commands;

import logic.Game;

public class HelpCommand extends NoParamsCommand {

	protected final static String name = "help";
	protected final static String shortcut = "h";
	private final static String details = "[h]elp";
	private final static String help = "Prints this help message.";


	public HelpCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		System.out.print(CommandGenerator.commandHelp());
		return false;
	}

	@Override
	public NoParamsCommand getNoParamsCommand() {
		// TODO Auto-generated method stub
		return new HelpCommand();
	}

}
