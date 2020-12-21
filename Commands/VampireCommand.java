package Commands;

import control.Controller;
import logic.Game;


public class VampireCommand extends Command {
	protected final static String name = "add vampire";
	protected final static String shortcut = "v";
	private final static String details = "[v]ampire";
	private final static String help = "Add a vampire to game.";
	int x;
	int y;
	String type;
	
	public VampireCommand() {
		super(name, shortcut, details, help);
	}

	@Override
	public boolean execute(Game game) {
		if (game.checkEmpty(this.x, this.y) && game.insideBoard(this.x, this.y)) {
			// HAY DINERO aqui
		
				// CREAR Y AÑADIR EL BICHO EN LA LISTA
			game.addVampireAction(this.type, this.x, this.y);
			}
			else {
				System.out.println("Wrong position");
			}
		
		return true;
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		Command c = null;
		//AddCommand add = new AddCommand();
		if(commandWords[0].equals("v")||commandWords[0].equals("vampire")) {
			c = this; // = add;
			this.type = (commandWords[1]);
			this.x = (Integer.parseInt(commandWords[2]));
			this.y = (Integer.parseInt(commandWords[3]));
			
		}
		else {
			c = null;
		}
		return c;
	}

}
