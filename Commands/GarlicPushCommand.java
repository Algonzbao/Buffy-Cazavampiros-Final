package Commands;

import logic.Game;

public class GarlicPushCommand extends NoParamsCommand {

	protected final static String name = "GarlicPush";
	protected final static String shortcut = "g";
	private final static String details = "[g]arlic";
	private final static String help = "Push all the vampires one tile back, the ones in the last one die";
	private int cost;
	
	public GarlicPushCommand() {
		super(name, shortcut, details, help);
		this.cost = 10;
	}
	@Override
	public boolean execute(Game game) {
		if (game.enoughCoins(this.cost)) {
			game.decreaseCoins(this.cost);
		game.garlicPush();
		game.update();
		}
		else {
			System.out.println("You dont have enought coins");
		}
		return true;
	}
	
	public NoParamsCommand getNoParamsCommand() {
		// TODO Auto-generated method stub
		return new GarlicPushCommand();
	}

}
