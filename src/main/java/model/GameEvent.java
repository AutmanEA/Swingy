package model;

public sealed interface GameEvent
permits GameEvent.onMove, GameEvent.onFight {

	enum onMove implements GameEvent {
		NOTHING,
		ENCOUNTER,
		VICTORY
	}

	sealed interface onFight extends GameEvent
	permits GameEvent.onFight.Victory, GameEvent.onFight.Loot, GameEvent.onFight.Lose, GameEvent.onFight.Run {

		record Run() implements onFight {}
		record Victory(boolean levelUp) implements onFight {}
		record Loot(String artifactType, int artifactBonus, boolean levelUp) implements onFight {}
		record Lose() implements onFight {}
	}

}
