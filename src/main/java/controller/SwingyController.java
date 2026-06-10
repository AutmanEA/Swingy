package controller;

import java.util.Scanner;

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
		Feedback feedback;

		while (scanner.hasNextLine()) {
			String datas[] = scanner.nextLine().toLowerCase().trim().split("\\s+");

			if (datas.length == 0) {
				scanner.close();
				return;
			}

			//TODO: changer le systeme, il faut que je verifie si la commande est bonne ici avant de l'envoyer a la vue ou au modele
			//TODO: je veux pas parser 3 fois, si je parse ici, dans le modele je fais confiance que je recois le bon payload au bon moment
			//TODO: il me faut donc
				//TODO: --> dans la vue une methode qui affiche un message d'erreur et un comportement si c'est OK
				//TODO: rien de spécial dans le modele, juste je peux check ici si le payload est good
			Command command = setCommand(datas);

			view.processCommand(command, model.getGame() != null);
			if (command != null)
				feedback = model.processCommand(command);
		}
		scanner.close();
	}
}
