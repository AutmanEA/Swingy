package model;

import java.util.Arrays;
import java.util.HashMap;

public class Hero {
	private String name;
	private HeroClass heroClass;
	private int level = 0;
	private int exp = 0;
	private int current_hitpoints;
	private HashMap<String, Artifact> equipment = new HashMap<String, Artifact>();

	public void setName(String p_name) {
		name = p_name;
	}
	public String getName() {
		return name;
	}

	public void setHeroClass(String p_heroClassName) {
		switch (p_heroClassName.toLowerCase()) {
			case "thief"	-> heroClass = new Thief();
			case "magus"	-> heroClass = new Magus();
			case "warrior"	-> heroClass = new Warrior();
			case "tank"		-> heroClass = new Tank();
			default			-> System.err.println("TODO> exception"); //TODO: EXCEPTION
		}
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
