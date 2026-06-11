package view;

import controller.InputEvent;

public interface GameView {
	public void setListener(InputEvent listener);
	public void startListen();
	public void displayMessage(String message);
}
