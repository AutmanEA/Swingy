package view;

public class GuiView implements GameView {
	public void displayMessage(String message) {
		System.out.println("> " + message);
	}
}
