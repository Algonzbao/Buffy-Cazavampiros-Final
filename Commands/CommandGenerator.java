package Commands;

import control.Controller;

public class CommandGenerator {

	
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new CoinsCommand(),
			new FlashLightCommand(),
			new GarlicPushCommand(),
			new BloodTankCommand(),
			new VampireCommand()
			};
	
	public static Command parseCommand(String[] commandWords, Controller controller) {
		boolean found = false;
		Command command = null;
		int i = 0;
		
		while (i < availableCommands.length && !found) {
			command = availableCommands[i].parse(commandWords, controller);
			
			if (command != null) {
				found = true;
			}
			else {
				++i;
			}
		}
		
		return command;
			
		
	}
	
	public static String commandHelp() {
		String help ="";
		for (Command c : availableCommands) {
			help+= c.helpText();
		}
		return help;
	}
	
}
