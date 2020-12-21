package Commands;

import Elements.BloodTank;
import Elements.Board;
import Elements.Slayer;
import control.Controller;
import logic.Game;
import logic.Player;

public class BloodTankCommand extends Command {
	protected final static String name = "BloodTank";
	protected final static String shortcut = "b";
	private final static String details = "[B]loodTank";
	private final static String help = "Gives a 10% of its cost as coins for the player.";
	int x;
	int y;
	int cost;
	public BloodTankCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	public boolean execute(Game game) {
		if (game.checkEmpty(this.x, this.y) && game.insideBoard(this.x, this.y)) {
			// HAY DINERO aqui
			if (game.enoughCoins(this.cost)) {
				game.decreaseCoins(this.cost);
				// CREAR Y AÑADIR EL BICHO EN LA LISTA
				BloodTank b = new BloodTank(game, this.x, this.y, this.cost);
				game.addTank(b, x, y);
				game.update();
			} else {
				System.out.println("You dont have enought coins");
			}
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords, Controller Controller) {
		Command c = null;
		// AddCommand add = new AddCommand();
		if (commandWords[0].equals("b") || commandWords[0].equals("blood")) {
			c = this; // = add;
			this.x = (Integer.parseInt(commandWords[1]));
			this.y = (Integer.parseInt(commandWords[2]));
			this.cost = (Integer.parseInt(commandWords[3]));

		} else {
			c = null;
		}

		return c;
	}

}
