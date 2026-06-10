package controller;

public enum PlayerAction {
	NEW_HERO,
	LOAD_HERO,
	EXPORT, //TODO: save into a textfile for mandatory
	IMPORT, //TODO: loads from a textfile for mandatory

	MOVE,
	FIGHT,
	RUN,
	EQUIP,		//

	HELP,		//no-payload
	EXIT		//no-payload
}
