package Commands;

import Elements.Board;
import Elements.Slayer;
import Elements.Vampire;
import control.Controller;
import logic.Game;
import logic.Player;

public class AddCommand extends Command {

	protected final static String name = "add";
	protected final static String shortcut = "a";
	private final static String details = "[a]dd";
	private final static String help = "Add a slayer to game.";
	int x;
	int y;
	public AddCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		if (game.checkEmpty(this.x, this.y) && game.insideBoard(this.x, this.y)) {
			// HAY DINERO aqui
			if (game.enoughCoins(Slayer.getCost())) {
				game.decreaseCoins(Slayer.getCost());
				// CREAR Y AÑADIR EL BICHO EN LA LISTA
				Slayer s = new Slayer(game, this.x, this.y);
				game.addSlayer(s, x, y);
				game.update();
			}
			else {
				System.out.println("You dont have enought coins");
			}
		}
		return true;
	}

	@Override
	public Command parse(String[] commandWords, Controller Controller) {
		Command c = null;
		//AddCommand add = new AddCommand();
		if(commandWords[0].equals("a")||commandWords[0].equals("add")) {
			c = this; // = add;
			this.x = (Integer.parseInt(commandWords[1]));
			this.y = (Integer.parseInt(commandWords[2]));

		}else {
			c = null;
		}

		return c;
	}
}
