package model.hero;

public class RandomDude extends HeroClass {
	RandomDude() {
		super("RandomDude");
		attack = 7 + ((int)(Math.random() * 100) % 6);
		defense = 7 + ((int)(Math.random() * 100) % 6);
		hitpoints = 7 + ((int)(Math.random() * 100) % 6);
	}
}
