package Commands;

import logic.Game;

public class CoinsCommand extends NoParamsCommand {
	protected final static String name = "coins";
	protected final static String shortcut = "c";
	private final static String details = "[c]oins";
	private final static String help = "Adds 1000 coins to the player[Debug tool only].";
	public CoinsCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean execute(Game game) {
		game.addCoins(1000);
		return true;
	}
	
	public NoParamsCommand getNoParamsCommand() {
		// TODO Auto-generated method stub
		return new CoinsCommand();
	}
}
