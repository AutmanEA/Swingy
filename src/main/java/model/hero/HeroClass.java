package model.hero;

public abstract class HeroClass {
	protected String heroClassName = "";
	protected int attack = 0;
	protected int defense = 0;
	protected int hitpoints = 0;

	HeroClass() {}
	HeroClass(String p_heroClassName) {
		heroClassName = p_heroClassName;
	}

	public String getHeroClassName() {
		return heroClassName;
	}
	public int getHitpoints() {
		return hitpoints;
	}
	public int getAttack() {
		return attack;
	}
	public int getDefense() {
		return defense;
	}
}
