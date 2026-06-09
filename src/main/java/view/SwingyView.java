package view;

import controller.Command;
import controller.PlayerAction;

public class SwingyView {
	private ConsoleView consoleView;
	private GuiView guiView;
	private GameView currentView;

	public SwingyView(String mode) {
		consoleView = new ConsoleView();
		guiView = new GuiView();

		if (mode.equals("gui")) {
			currentView = guiView;
		} else if (mode.equals("console")) {
			currentView = consoleView;
		}
	}

	public void processCommand(Command command, boolean isInGame) {
		if (command != null) {
			PlayerAction action = command.getAction();

			switch (action) {
				case PlayerAction.HELP:
					display("help :)"); //TODO
					break;
				case PlayerAction.EXIT:
					display("Quitting... see you later."); //TODO
					break;
				default:
					break;
			}
			if (isInGame) {
				processGameCommand(command);
			} else {
				processMenuCommand(command);
			}
		} else {
			display("UNKNOWN COMMAND: (command \"help\" to get command list)");
		}
	}

	private void processMenuCommand(Command command) {
		PlayerAction action = command.getAction();

		switch (action) {
			case PlayerAction.NEW_HERO:
				display("Hero created");
				break;
			default:
				display("This command can't be used in menu (command \"help\" to get command list)");
				break;
		}
	}

	private void processGameCommand(Command command) {
		PlayerAction action = command.getAction();

		switch (action) {
			case PlayerAction.MOVE:
				String argument = "";
				if (command.getArgument() instanceof String) {
					argument = (String) command.getArgument();
				}
				System.err.println(argument);
				if (argument.isEmpty()) {
					display("Command failed, use move with one argument -> north (n), south (s), east (e) or west (w)");
				} else {
					display("Hero moved " + argument);
				}
				break;
			default:
				display("This command can't be used in game (command \\\"help\\\" to get command list)");
				break;
		}
	}

	public void display(String message) {
		currentView.displayMessage(message);
	}

	public void switchView() {
		if (currentView == guiView) {
			currentView = consoleView;
		} else {
			currentView = guiView;
		}
	}

 /*
 	private void move() {
		if (command.size() < 2) {
			view.display("Command failed, use move with one argument -> north (n), south (s), east (e) or west (w)");
		} else {
			String direction;

			switch (command.get(1)) {
				case "n", "north"	-> direction = "north";
				case "s", "south"	-> direction = "south";
				case "e", "east"	-> direction = "east";
				case "w", "west"	-> direction = "west";
				default				-> {
					view.display("Unkown direction, please choose north (n), south (s), east (e) or west (w)");
					return ;
				}
			}
			//TODO: actually moves in model
			//TODO: if encounters, not the same message
			view.display("Hero moved " + direction + ", nothing happened.");
		}
	}
 */
}
