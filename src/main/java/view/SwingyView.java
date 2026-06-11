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

	public void switchView() {
		if (currentView == guiView) {
			currentView = consoleView;
		} else {
			currentView = guiView;
		}
		currentView.startListen();
	}

	// -- INSTRUCTIONS -- //
	//generic
	public void display(String message) { currentView.displayMessage(message); }
	public void unknownCommand(String command) { currentView.unknownCommand(command); }
	public void badArgument() { currentView.badArgument(); }
	public void badCommandUsage() { currentView.badCommandUsage(); }

	//commands
	public void move(String direction) { currentView.handleMove(direction); }
}
