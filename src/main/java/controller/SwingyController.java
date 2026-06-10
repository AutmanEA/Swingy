package controller;

import java.util.Scanner;

import model.MoveResult;
import model.SwingyModel;
import view.SwingyView;

public class SwingyController {
	private SwingyView view;
	private SwingyModel model = new SwingyModel();

	public SwingyController(String mode) throws Exception { //TODO: better exception
		switch (mode) {
			case "console"	-> view = new SwingyView("console");
			case "gui"		-> view = new SwingyView("gui");
			default			-> throw new Exception(); //TODO: better exception
		}
	}

	public void exec() throws Exception {
		Scanner scanner = new Scanner(System.in); //TODO: attention je vais sans doute pas que ecouter ca


		//TODO:delete test
		model.createGame(model.loadHero("Joey"));
		//TODO:endtest

		while (scanner.hasNextLine()) {
			String datas[] = scanner.nextLine().toLowerCase().trim().split("\\s+");

			if (datas.length == 0) {
				scanner.close();
				return;
			}

			String command = datas[0];
			String argument;

			if (datas.length == 2) {
				argument = datas[1];
			} else argument = "";

			processCommand(command, argument);
		}
		scanner.close();
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
		MoveResult result;

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
			case MoveResult.Fight():
				view.display("fight detected");
				//TODO:handleFight()
				break;
			case MoveResult.Victory():
				view.display("victory :)");
				//TODO:handleVictory()
				break;
			case MoveResult.Nothing():
				view.display("tout va bien continue frr");
				break;
		}
	}
}
