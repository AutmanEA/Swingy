package model.hero;

public record HeroData(
	String heroName,
	String heroClass,
	int exp,
	int helmetBonus,
	int armorBonus,
	int weaponBonus) {}
