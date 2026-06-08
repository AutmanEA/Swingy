package controller;

public class Command {
	private PlayerAction action;
	private String argument;

	public Command(PlayerAction _action, String _arg) {
		action = _action;
		argument = _arg;
	}

	public Command(PlayerAction _action) {
		action = _action;
		argument = "";
	}

	public PlayerAction getAction() {
		return action;
	}

	public String getArgument() {
		return argument;
	}
}
