package view;

public class ConsoleView implements SwingyView {

	@Override
	public void displayMessage(String message) {
		System.out.println("> " + message);
	}
}
