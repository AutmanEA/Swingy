package view;

import controller.InputEvent;

public interface GameView {
	public void setListener(InputEvent listener);
	public void startListen();
	public void displayMessage(String message);

	public void unknownCommand(String command);
	public void badArgument();
	public void badCommandUsage();
	public void handleMove(String direction);
}
