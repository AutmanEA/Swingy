package controller;

public class Command {
	private PlayerAction action;
	private Object payload;

	public Command(PlayerAction _action, Object _payload) {
		action = _action;
		payload = _payload;
	}

	public Command(PlayerAction _action) {
		action = _action;
		payload = null;
	}

	public PlayerAction getAction() {
		return action;
	}

	public Object getArgument() {
		return payload;
	}
}
