package model;

import model.hero.Artifact;
import model.hero.Hero;
import model.map.Coordinates;
import model.map.GameMap;

public class Game {
	private Hero		hero;
	private GameMap		map;
	private GameState	state = GameState.EXPLORING;
	private Artifact	pendingLoot = null;

	public GameState getState() {
		return state;
	}

	public Game(Hero p_hero) {
		hero = p_hero;

		int	heroLevel = hero.getLevel();
		map = new GameMap(heroLevel);
	}

	public GameEvent.onMove move(String direction) {
		clearLoot();

		switch (direction) {
			case "n"	-> map.moveHeroBy(0, -1);
			case "s"	-> map.moveHeroBy(0, 1);
			case "e"	-> map.moveHeroBy(1, 0);
			case "w"	-> map.moveHeroBy(-1, 0);
		}
		if (map.hasWon()) {
			state = GameState.IN_MENU;
			return GameEvent.onMove.VICTORY;
		} else if (map.hasEncounters()) {
			state = GameState.FIGHTING;
			return GameEvent.onMove.ENCOUNTER;
		} else {
			state = GameState.EXPLORING;
			return GameEvent.onMove.NOTHING;
		}
	}

	public GameEvent.onFight run() {
		if (Math.random() < 0.5) {
			state = GameState.EXPLORING;
			map.cancelMove();
			return new GameEvent.onFight.Run();
		}
		return fight();
	}

	public GameEvent.onFight fight() {
		state = GameState.FIGHTING;
		return battle(map.getHeroCurrentPosition());
	}

	private GameEvent.onFight battle(Coordinates battlePosition) {
		int villainStrenght = 1 + map.distanceFromCenter(battlePosition);
		int villainExperienceDone = villainStrenght * 100;
		boolean loot = Math.random() < (villainStrenght % 11) / 10;

		int strikeNumber = (villainStrenght / hero.getAttack()) + 1;

		for(int i = 0; i < strikeNumber; i++) {
			hero.doDamage((int)Math.round((Math.random() * villainStrenght) - hero.getDefense()));
			if (hero.getHitpoints() <= 0) {
				state = GameState.IN_MENU;
				return new GameEvent.onFight.Lose();
			}
		}
		int oldLevel = hero.getLevel();
		hero.addExperience(villainExperienceDone);
		boolean levelUp = oldLevel != hero.getLevel();

		if (loot) {
			state = GameState.LOOTING;
			String[] lootTypes = {"helmet", "weapon", "armor"};
			String lootType = lootTypes[(int)(Math.random() * lootTypes.length)];
			int lootBonus = (int)(Math.random() * 10) + villainStrenght;

			pendingLoot = new Artifact(lootType, lootBonus);
			return new GameEvent.onFight.Loot(lootType, lootBonus, levelUp);
		}
		state = GameState.EXPLORING;
		return new GameEvent.onFight.Victory(levelUp);
	}

	public void equip() {
		if (pendingLoot != null)
			hero.equip(pendingLoot);
		clearLoot();
	}

	public void clearLoot() {
		if (pendingLoot != null)
			pendingLoot = null;
	}

}
