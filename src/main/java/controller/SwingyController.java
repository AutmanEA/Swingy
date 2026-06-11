package controller;

import model.GameEvent;
import model.SwingyModel;
import view.SwingyView;

public class SwingyController implements InputEvent {
	private SwingyView view;
	private SwingyModel model = new SwingyModel();

	public SwingyController(String mode) throws Exception { //TODO: better exception
		switch (mode) {
			case "console"	-> view = new SwingyView("console");
			case "gui"		-> view = new SwingyView("gui");
			default			-> throw new Exception(); //TODO: better exception
		}
		view.setListener(this);
	}

	@Override
	public void onInput(String input) {
		String command = getCommand(input);
		String argument = getArgument(input);
		if (command != null)
			processCommand(command, argument);
	}

	public void exec() throws Exception {
		model.createGame(model.loadHero("Joey")); //TODO:delete
		view.startListen();
	}

	private String getCommand(String data) {
		String[] split = data.split("\\s+");

		return split[0];
	}

	private String getArgument(String data) {
		String[] split = data.split("\\s+");

		if (split.length == 2) {
			return split[1];
		}
		return null;
	}

	private void processCommand(String command, String argument) {
		switch (command) {
			case "move":
				if (argument != null)
					handleMove(argument);
				else
					view.badArgument();
				break;
			// case "fight"				-> ;
			// case "run"					-> ;
			// case "equip"				-> ;
			// case "exit", "quit", "q"	-> ;
			// case "help"					-> ;
			// case "new"					-> ;
			// case "load"					-> ;
			// case "export"				-> ;
			// case "import"				-> ;
			default:
				view.unknownCommand(command);
		}
	}

	private void handleMove(String direction) {
		GameEvent.onMove result;

		switch (direction) {
			case "n", "north":
				view.move("north");
				result = model.move("n");
				break;
			case "s", "south":
				view.move("south");
				result = model.move("s");
				break;
			case "w", "west":
				view.move("west");
				result = model.move("w");
				break;
			case "e", "east":
				view.move("east");
				result = model.move("e");
				break;
			default:
				view.badArgument();
				return;
		}
		switch (result) {
			case GameEvent.onMove.FIGHT:
				view.display("fight detected");
				//TODO:handleFight()
				break;
			case GameEvent.onMove.VICTORY:
				view.display("victory :)");
				//TODO:handleVictory()
				break;
			case GameEvent.onMove.NOTHING:
				view.display("tout va bien continue frr");
				break;
		}
	}
}
