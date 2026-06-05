package model.hero;

public class Artifact {
	private String type = "";
	private int bonus = 0;

	//TODO: add name ? not mandatory and needs a name randomizer, flemme?

	Artifact() {}
	Artifact(String p_type, int p_bonus) {
		type = p_type;
		bonus = p_bonus;
	}
	public String getType() {
		return type;
	}
	public int getBonus() {
		return bonus;
	}
}
