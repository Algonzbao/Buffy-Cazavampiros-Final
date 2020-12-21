package Commands;

import logic.Game;
import Commands.Command;
import Commands.ExitCommand;
import Commands.HelpCommand;
import Commands.UpdateCommand;
import Commands.ResetCommand;
import control.Controller;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		
			NoParamsCommand noParams;
			if (commandWords.length != 1)
				return null;
			else if (matchCommandName(commandWords[0])) {
				 noParams = getNoParamsCommand();
				return noParams;
			}
			else 
				return null;
		}
	public abstract NoParamsCommand getNoParamsCommand();
		/*if (commandWords[0].equals("h") || commandWords[0].equals("help")) {
			c = new HelpCommand();

		} else if (commandWords[0].equals("r") || commandWords[0].equals("reset")) {

			c = new ResetCommand();

		} else if (commandWords[0].equals("e") || commandWords[0].equals("exit")) {

			c = new ExitCommand();

		} else if (commandWords[0].equals("c") || commandWords[0].equals("coins")) {

			c = new CoinsCommand();

		} else if (commandWords[0].equals("l") || commandWords[0].equals("light")) {

			c = new FlashLightCommand();

		}  else if (commandWords[0].equals("g") || commandWords[0].equals("garlic")) {

			c = new GarlicPushCommand();

		}else if (commandWords[0].equals("") || commandWords[0].equals("none")) {

			c = new UpdateCommand();

		} else {

			c = null;

		}

		return c;*/

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		return false;
	}


}
