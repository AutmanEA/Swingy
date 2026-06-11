package model.hero;

import java.util.HashMap;

public class Hero {
	private String name;
	private HeroClass heroClass;
	private int level = 1;
	private int exp = 0;
	private int current_hitpoints;
	private HashMap<String, Artifact> equipment = new HashMap<String, Artifact>();

	public String getName() {
		return name;
	}
	public int getLevel() {
		return level;
	}
	public int getAttack() {
		return heroClass.getAttack() + equipment.get("weapon").bonus();
	}
	public int getDefense() {
		return heroClass.getDefense() + equipment.get("armor").bonus();
	}
	public int getMaxHitpoints() {
		return heroClass.getHitpoints() + equipment.get("helmet").bonus();
	}
	public int getHitpoints() {
		return current_hitpoints;
	}
	public void doDamage(int damage) {
		current_hitpoints -= damage;
	}
	public void addExperience(int p_exp) {
		exp += p_exp;
		updateLevel(exp);
	}

	private void updateLevel(int p_exp) {
		while (p_exp > ((level * 1000) + (Math.pow((level - 1), 2) * 450))) {
			level++;
		}
	}

	public void equip(Artifact artifact) {
		String typeKey = artifact.type();

		equipment.put(typeKey, artifact);

		if (typeKey.equals("helmet")) {
			int oldBonus = equipment.get("helmet").bonus();
			int difference = artifact.bonus() - oldBonus;

			current_hitpoints += difference;

			if (current_hitpoints > getMaxHitpoints()) {
				current_hitpoints = getMaxHitpoints();
			}
			if (current_hitpoints <= 0) {
				current_hitpoints = 1;
			}
		}
	}

	public HeroData toData() {
		int helmetBonus = equipment.get("helmet").bonus();
		int armorBonus = equipment.get("armor").bonus();
		int weaponBonus = equipment.get("weapon").bonus();

		return new HeroData(
			name,
			heroClass.name().toLowerCase(),
			exp,
			helmetBonus,
			armorBonus,
			weaponBonus
		);
	}

	public Hero(HeroData heroData) {
		name = heroData.heroName();

		switch (heroData.heroClass().toLowerCase()) {
			case "thief"	-> heroClass = HeroClass.THIEF;
			case "magus"	-> heroClass = HeroClass.MAGUS;
			case "warrior"	-> heroClass = HeroClass.WARRIOR;
			case "tank"		-> heroClass = HeroClass.TANK;
			default			-> heroClass = HeroClass.DEFAULT;
		}

		current_hitpoints = heroClass.getHitpoints();
		addExperience(heroData.exp());
		equip(new Artifact("helmet", heroData.helmetBonus()));
		equip(new Artifact("armor", heroData.armorBonus()));
		equip(new Artifact("weapon", heroData.weaponBonus()));
	}
}
