package controller;

import java.util.Scanner;

import view.SwingyView;

public class SwingyController {
	private SwingyView view;
	private boolean isGameRunning = false;

	public SwingyController(String mode) throws Exception { //TODO: better exception
		switch (mode) {
			case "console"	-> view = new SwingyView("console");
			case "gui"		-> view = new SwingyView("gui");
			default			-> throw new Exception(); //TODO: better exception
		}
	}

	// private void newHero() {
		//TODO: creer et remplir le heroData du model, save le nouveau hero dans la BDD (pas encore dispo)
		//TODO: pour ça, je dois faire des va et vien avec la view pour demander a chaque fois chaque info du hero
		//TODO: tout enregistrer dans le model avec un heroBuilder je suppose
		//TODO: puis confirmer la reussite a la view
	// }

	private Command setCommand(String datas[]) {
		if (datas.length > 2) {
			return null;
		}

		switch (datas[0]) {
			case "move":
				if (datas.length > 1) {
					String direction;

					switch (datas[1]) {
						case "n", "north"	-> direction = "north";
						case "s", "south"	-> direction = "south";
						case "e", "east"	-> direction = "east";
						case "w", "west"	-> direction = "west";
						default				-> direction = "";
					}
					return new Command(PlayerAction.MOVE, direction);
				} else return new Command(PlayerAction.MOVE);
			case "fight":
				return new Command(PlayerAction.FIGHT);
			case "run":
				return new Command(PlayerAction.RUN);
			case "equip":
				return new Command(PlayerAction.EQUIP);
			case "exit", "quit", "q":
				return new Command(PlayerAction.EXIT);
			case "help":
				return new Command(PlayerAction.HELP);
			case "new":
				return new Command(PlayerAction.NEW_HERO); //TODO: new input cycle to create hero
			case "load":
				return new Command(PlayerAction.LOAD_HERO); //TODO: load with name?
			default:
				return null;
		}
	}

	public void exec() throws Exception {
		Scanner scanner = new Scanner(System.in); //TODO: attention je vais sans doute pas que ecouter ca

		while (scanner.hasNextLine()) {
			String datas[] = scanner.nextLine().toLowerCase().trim().split("\\s+");

			if (datas.length == 0) {
				scanner.close();
				return;
			}
			Command command = setCommand(datas);

			if (isGameRunning) {
				view.processGameCommand(command);
			} else {
				view.processMenuCommand(command);
			}

			isGameRunning = true; //TODO: a voir pour lancer le jeu suite a un feedback ?
		}
		scanner.close();
	}
}
