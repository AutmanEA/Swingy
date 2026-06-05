package model;

import model.hero.Hero;
import model.map.GameMap;

public class Game {
	private Hero	hero;
	private GameMap	map;

	public Game(Hero p_hero) {
		hero = p_hero;

		int	heroLevel = hero.getLevel();
		map = new GameMap(heroLevel);
	}

	public void move(String direction) {
		switch (direction) {
			case "n"	-> map.moveHeroBy(0, -1);
			case "s"	-> map.moveHeroBy(0, 1);
			case "e"	-> map.moveHeroBy(1, 0);
			case "w"	-> map.moveHeroBy(-1, 0);
		}
	}
}
