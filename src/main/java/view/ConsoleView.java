package view;

import java.util.Scanner;

import controller.InputEvent;

public class ConsoleView implements GameView {
	private InputEvent listener;

	@Override
	public void displayMessage(String message) {
		System.out.println("> " + message);
	}

	@Override
	public void setListener(InputEvent listener) {
		this.listener = listener;
	}

	@Override
	public void startListen() {
		try (Scanner scanner = new Scanner(System.in)) {
			while (scanner.hasNextLine()) {
				String input = scanner.nextLine().toLowerCase().trim();

				listener.onInput(input);
			}
		}
	}

	@Override
	public void unknownCommand(String command) {
		displayMessage("UNKNOWN COMMAND IN THIS CONTEXT: " + command + " (command \"help\" to get command list)");
	}

	@Override
	public void badArgument() {
		displayMessage("BAD ARGUMENT: this command needs specific arguments (command \"help\" to get command list)");
	}

	@Override
	public void handleMove(String direction) {
		displayMessage("Hero moved " + direction);
	}
}
