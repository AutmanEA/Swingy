package model;

import model.hero.Hero;
import model.map.Coordinates;
import model.map.GameMap;

public class Game {
	private Hero	hero;
	private GameMap	map;

	public Game(Hero p_hero) {
		hero = p_hero;

		int	heroLevel = hero.getLevel();
		map = new GameMap(heroLevel);
	}

	public MoveResult move(String direction) {
		switch (direction) {
			case "n"	-> map.moveHeroBy(0, -1);
			case "s"	-> map.moveHeroBy(0, 1);
			case "e"	-> map.moveHeroBy(1, 0);
			case "w"	-> map.moveHeroBy(-1, 0);
		}
		if (map.hasWon()) {
			return new MoveResult.Victory();
		} else if (map.hasEncounters()) {
			return new MoveResult.Fight();
		} else {
			return new MoveResult.Nothing();
		}
	}

	public void run() {
		map.cancelMove();
	}

	public void fight() {
		battle(map.getHeroCurrentPosition());
	}

	private void battle(Coordinates battlePosition) {
		int villainStrenght = 1 + map.distanceFromCenter(battlePosition);
		int villainExperienceDone = villainStrenght * 100;
		boolean loot = Math.random() < villainStrenght / 10;

		int strikeNumber = (villainStrenght / hero.getAttack()) + 1;

		for(int i = 0; i < strikeNumber; i++) {
			hero.doDamage((int)Math.round((Math.random() * villainStrenght) - hero.getDefense()));
			if (hero.getHitpoints() <= 0) {
				//TODO: notifier que le hero est mort
				//TODO: arret de tout
			}
		}
		hero.addExperience(villainExperienceDone);
		//TODO: notifier que le hero a gagné
		if (loot) {
			//TODO: notifier que le hero peut equiper un truc (random du type?)
		}
	}

	public void equip(String artifactType) {
		int artifactBonus = (int)(Math.random() * 10) + map.distanceFromCenter(map.getHeroCurrentPosition());

		hero.equip(artifactType, artifactBonus);
		//TODO: notifier que le hero a bien equipe un nouvel equipement
	}

}
