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
		return heroClass.getAttack() + equipment.get("weapon").getBonus();
	}
	public int getDefense() {
		return heroClass.getDefense() + equipment.get("armor").getBonus();
	}
	public int getMaxHitpoints() {
		return heroClass.getHitpoints() + equipment.get("helmet").getBonus();
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
		//TODO: Notify ?
	}

	public void equip(String artifactType, int bonus) {
		Artifact newArtifact = new Artifact(artifactType, bonus);
		String typeKey = artifactType.toLowerCase();

		equipment.put(typeKey, newArtifact);

		if (typeKey.equals("helmet")) {
			int oldBonus = equipment.get("helmet").getBonus();
			int difference = bonus - oldBonus;

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
		int helmetBonus = equipment.get("helmet").getBonus();
		int armorBonus = equipment.get("armor").getBonus();
		int weaponBonus = equipment.get("weapon").getBonus();

		return new HeroData(
			name,
			heroClass.getHeroClassName(),
			exp,
			helmetBonus,
			armorBonus,
			weaponBonus
		);
	}

	public Hero(HeroData heroData) {
		name = heroData.heroName();

		switch (heroData.heroClass().toLowerCase()) {
			case "thief"	-> heroClass = new Thief();
			case "magus"	-> heroClass = new Magus();
			case "warrior"	-> heroClass = new Warrior();
			case "tank"		-> heroClass = new Tank();
			default			-> heroClass = new RandomDude();
		}

		current_hitpoints = heroClass.getHitpoints();
		addExperience(heroData.exp());
		equip("helmet", heroData.helmetBonus());
		equip("armor", heroData.armorBonus());
		equip("weapon", heroData.weaponBonus());
	}
}
