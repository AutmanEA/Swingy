package controller;

public enum PlayerActionStatus {
	FAILURE,			//payload = String -> error message

	GAME_STARTED,		//no-payload
	HERO_LOAD_SUCCESS,	//no-payload

	MOVE_SUCCESS,		//no-payload
	FIGHT_STARTED,		//no-payload
	FIGHT_LOOT,			//payload = Artifact -> loot with type and bonus
	FIGHT_END,			//payload = int -> exp earned
	LEVEL_UP,			//no-payload

	//* IDEA: use payload to deliver game stats
	GAME_LOSE,			//no-payload
	GAME_WIN,			//no-payload
}
