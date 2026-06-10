package controller;

public record Feedback(PlayerActionStatus status, Object payload) {
	public Feedback(PlayerActionStatus status) {
		this(status, "");
	}
}
