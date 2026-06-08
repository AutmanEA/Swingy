package view;

public class ConsoleView implements GameView {
	public void displayMessage(String message) {
		System.out.println("> " + message);
	}
}
