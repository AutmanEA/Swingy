package controller;

import java.util.ArrayList;
import java.util.Scanner;

import view.ConsoleView;
import view.GuiView;
import view.SwingyView;

public class SwingyController {
	SwingyView view;

	private ArrayList<String> command = new ArrayList<>();

	public SwingyController(String mode) throws Exception { //TODO: better exception
		switch (mode) {
			case "console"	-> view = new ConsoleView();
			case "gui"		-> view = new GuiView();
			default			-> throw new Exception(); //TODO: better exception
		}
	}

	private void setCommand(String commandLine) {
		command.clear();
		Scanner scanner = new Scanner(commandLine);

		if (!scanner.hasNext()) {
			command.add("");
		}
		while (scanner.hasNext()) {
			command.add(scanner.next());
		}
		scanner.close();
	}

	private void move() {
		if (command.size() < 2) {
			view.displayMessage("Command failed, use move with one argument -> north (n), south (s), east (e) or west (w)");
		} else {
			String direction;

			switch (command.get(1)) {
				case "n", "north"	-> direction = "north";
				case "s", "south"	-> direction = "south";
				case "e", "east"	-> direction = "east";
				case "w", "west"	-> direction = "west";
				default				-> {
					view.displayMessage("Unkown direction, please choose north (n), south (s), east (e) or west (w)");
					return ;
				}
			}
			//TODO: actually moves in model
			//TODO: if encounters, not the same message
			view.displayMessage("Hero moved " + direction + ", nothing happened.");
		}
	}

	public void exec() throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextLine()) {
			String data = scanner.nextLine().toLowerCase().trim();
			setCommand(data);

			if (command.size() == 0) {
				//TODO: throw exception
				scanner.close();
				return;
			}

			switch (command.get(0)) {
				case "exit", "quit"	-> {
					view.displayMessage("Quitting... see you later.");
					scanner.close();
					return;
				}
				case ""				-> view.displayMessage("Enter a command -> \"help\" to get command list");
				case "move"			-> move();
				default				-> view.displayMessage("UNKNOWN COMMAND: " + command.get(0) + " (command \"help\" to get command list)");
			}
		}

		scanner.close();
	}
}
