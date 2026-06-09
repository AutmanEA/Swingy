package controller;

public enum PlayerAction {
	//main menu actions
	NEW_HERO,
	LOAD_HERO,
	EXPORT, //TODO: save into a textfile for mandatory
	IMPORT, //TODO: loads from a textfile for mandatory

	//in-game actions
	MOVE,
	FIGHT,
	RUN,
	EQUIP,

	//meta actions
	HELP,
	EXIT
}
