package model.hero;

public enum HeroClass {
	THIEF(18, 9, 9),
	WARRIOR(15, 12, 9),
	MAGUS(24, 3, 9),
	TANK(6, 15, 15),
	DEFAULT(12, 12, 12);

	private final int attack;
	private final int defense;
	private final int hitpoints;

	HeroClass(int _attack, int _defense, int _hitpoints) {
		attack = _attack;
		defense = _defense;
		hitpoints = _hitpoints;
	}

	public int getAttack() { return attack; }
	public int getDefense() { return defense; }
	public int getHitpoints() { return hitpoints; }
}
