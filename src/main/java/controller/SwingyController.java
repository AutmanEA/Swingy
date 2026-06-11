package controller;

import model.GameEvent;
import model.GameState;
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
		String[] data = input.split("\\s+");

		String command = getCommand(data);
		String argument = getArgument(data);
		if (command != null)
			processCommand(command, argument);
	}

	public void exec() throws Exception {
		model.createGame(model.loadHero("Joey")); //TODO: delete
		view.startListen();
	}

	private String getCommand(String[] data) {
		return data[0];
	}

	private String getArgument(String[] data) {
		if (data.length == 2) {
			return data[1];
		}
		return null;
	}

	private void processCommand(String command, String argument) {
		switch (command) {
			case "help":
				//TODO
			case "exit", "quit":
				//TODO
		}

		GameState state = model.getGameState();
		switch (state) {
			case GameState.EXPLORING	-> processExploringCommands(command, argument);
			case GameState.IN_MENU		-> processMenuCommands(command, argument);
			case GameState.FIGHTING		-> processFightingCommands(command, argument);
			case GameState.LOOTING		-> processLootingCommands(command, argument);
		}
	}

	private void processMenuCommands(String command, String argument) {
		view.unknownCommand(command);
	}

	private void processExploringCommands(String command, String argument) {
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

	private void processFightingCommands(String command, String argument) {
		switch (command) {
			case "fight"	-> handleFight();
			case "run"		-> handleRun();
			default			-> view.unknownCommand(command);
		}
	}

	private void processLootingCommands(String command, String argument) {
		view.unknownCommand(command);
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
			case GameEvent.onMove.ENCOUNTER	-> view.display("fight detected, please choose between fight or run");
			case GameEvent.onMove.VICTORY	-> view.display("victory :)");
			case GameEvent.onMove.NOTHING	-> view.display("bla bla bla");
		}
	}

	private void handleFight() {
		view.display("ok tu te bagar");
		processFightResult(model.fight());
	}

	private void handleRun() {
		view.display("tu tente de fuir et...");
		processFightResult(model.run());
	}

	private void processFightResult(GameEvent.onFight fightResult) {
		view.display("debut de la bagar");
		switch (fightResult) {
			case GameEvent.onFight.Loot(String artifactType, int artifactBonus, boolean levelUp) -> {
				if (levelUp) view.display("t'as level up !");
				view.display("tu loot ca : " + artifactType + " qui a cette puissance : " + artifactBonus + " tu veux quiper?");
			}
			case GameEvent.onFight.Victory(boolean levelUp) -> {
				if (levelUp) view.display("t'as level up !");
				view.display("bravo t'as gagne le fight");
			}
			case GameEvent.onFight.Lose() -> view.display("t'as perdu retour au menu");
			case GameEvent.onFight.Run()  -> view.display("t'as reussi a run, continue a explorer");
		}
	}
}
