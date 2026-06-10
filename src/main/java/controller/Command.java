package controller;

public record Command(PlayerAction action, Object payload) {
	public Command(PlayerAction action) {
		this(action, null);
	}
}
