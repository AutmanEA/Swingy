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
		if (p_exp > ((level * 1000) + (Math.pow((level - 1), 2) * 450))) {
			level++;
		}
		//TODO: Notify ?
	}

	public Hero(String p_name, String p_heroClassName, int p_exp) {
		name = p_name;

		switch (p_heroClassName.toLowerCase()) {
			case "thief"	-> heroClass = new Thief();
			case "magus"	-> heroClass = new Magus();
			case "warrior"	-> heroClass = new Warrior();
			case "tank"		-> heroClass = new Tank();
			default			-> System.err.println("TODO> exception");
		}

		current_hitpoints = heroClass.getHitpoints() + equipment.get("helmet").getBonus();
		addExperience(p_exp);
	}

	public void equip(Artifact artifact) {

		equipment.put(artifact.getType().toLowerCase(), artifact);


		// String artifactType = artifact.getType().toLowerCase();
		// String[] types = new String[] {"helmet", "weapon", "armor"}; //TODO: attention probleme de conception, c'est le controller qui va check si l'objet peut etre equipe ou pas


		// if (Arrays.asList(types).contains(artifactType)) {
		// } else {
			// //TODO: throw an exception ?
			// System.err.println("equipement inconnu");
		// }
	}
}
