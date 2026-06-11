package model;

import model.hero.Hero;
import model.hero.HeroData;

public class SwingyModel {
	private Game game;

	public void createGame(HeroData heroData) {
		Hero hero = new Hero(heroData);

		game = new Game(hero);
	}

	public HeroData loadHero(String name) {
		//TODO: fetch from database > unique id = name ? c'est triste mais bon :),

		//TODO: et du coup si le name existe pas, on envoie FAILURE ou on cree un nouveau ?

		return new HeroData(name, "thief", 2350, 0, 0, 0);
	}

	public GameState getGameState() { return game.getState(); }
	public int getHeroLevel() { return game.getHero().getLevel(); }

	//commands
	public GameEvent.onMove		move(String direction) { return game.move(direction); }
	public GameEvent.onFight	fight() { return game.fight(); }
	public GameEvent.onFight	run() { return game.run(); }
}
