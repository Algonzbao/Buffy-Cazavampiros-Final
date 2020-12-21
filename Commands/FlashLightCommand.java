package Commands;

import logic.Game;

public class FlashLightCommand extends NoParamsCommand {

	protected final static String name = "FlashLight";
	protected final static String shortcut = "l";
	private final static String details = "[L]ight";
	private final static String help = "Flash a light beam and destroy all the vampires except from Dracula";
	private int cost;
	
	public FlashLightCommand() {
		super(name, shortcut, details, help);
		this.cost = 50;
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean execute(Game game) {
		if (game.enoughCoins(this.cost)) {
			game.decreaseCoins(this.cost);
		game.useFlashLight();
		game.update();
		}
		else {
			System.out.println("You dont have enought coins");
		}
		return true;
	}
	
	public NoParamsCommand getNoParamsCommand() {
		// TODO Auto-generated method stub
		return new FlashLightCommand();
	}

}
