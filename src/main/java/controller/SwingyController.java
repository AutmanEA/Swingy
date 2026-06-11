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
		// boolean isRunning = true;

		model.createGame(model.loadHero("Joey")); //TODO:delete
		view.startListen();


		// while (isRunning) {
		// 	String input = waitInput(view.getPrompt());

		// 	if (input.length() == 0) {
		// 		return;
		// 	}

		// 	String command = getCommand(input);
		// 	String argument = getArgument(input);

		// 	if (command != null)
		// 		processCommand(command, argument);
		// }
	}


	// private String waitInput(String prompt) {
	// 	Scanner scanner = new Scanner(System.in); //TODO: attention je vais sans doute pas que ecouter ca
	// 	String userInput = "";

	// 	view.display(prompt);
	// 	if (scanner.hasNextLine()) {
	// 		userInput = scanner.nextLine().toLowerCase().trim();
	// 	}
	// 	scanner.close();
	// 	return userInput;
	// }

	private String getCommand(String data) {
		String[] split = data.split("\\s+");

		if (data.isEmpty() || data.isBlank())
			return null;
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
			case "move"					-> handleMove(argument);
			// case "fight"				-> ;
			// case "run"					-> ;
			// case "equip"				-> ;
			// case "exit", "quit", "q"	-> ;
			// case "help"					-> ;
			// case "new"					-> ;
			// case "load"					-> ;
			// case "export"				-> ;
			// case "import"				-> ;
			default						-> view.unknownCommand();
		}
	}

	private void handleMove(String direction) {
		GameEvent.onMove result;

		switch (direction) {
			case "n", "north":
				view.move("north");
				result = model.getGame().move("n");
				break;
			case "s", "south":
				view.move("south");
				result = model.getGame().move("s");
				break;
			case "w", "west":
				view.move("west");
				result = model.getGame().move("w");
				break;
			case "e", "east":
				view.move("east");
				result = model.getGame().move("e");
				break;
			default:
				view.moveError();
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
