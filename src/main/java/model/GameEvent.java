package model;

public sealed interface GameEvent
permits GameEvent.onMove
{
	enum onMove implements GameEvent {
		NOTHING,
		FIGHT,
		VICTORY
	}
}
