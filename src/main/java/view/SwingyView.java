package view;

import controller.InputEvent;

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

	public void setListener(InputEvent listener) {
		consoleView.setListener(listener);
		guiView.setListener(listener);
	}

	public void startListen() {
		currentView.startListen();
	}

	public void unknownCommand() {
		display("UNKNOWN COMMAND: (command \"help\" to get command list)");
	}

	public void move(String direction) {
		display("Hero moved " + direction);
	}

	public void moveError() {
		display("Command failed, use move with one argument -> north (n), south (s), east (e) or west (w)");
	}

	public void badCommandUsage() {
		display("This command can't be used now (command \"help\" to get command list)");
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
		currentView.startListen();
	}
}
