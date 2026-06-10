package model;

import model.hero.Hero;
import model.hero.HeroData;

public class SwingyModel {
	private Game game;

	public Game getGame() {
		return game;
	}

	public void createGame(HeroData heroData) {
		Hero hero = new Hero(heroData);

		game = new Game(hero);
	}

	public HeroData loadHero(String name) {
		//TODO: fetch from database > unique id = name ? c'est triste mais bon :),

		//TODO: et du coup si le name existe pas, on envoie FAILURE ou on cree un nouveau ?

		return new HeroData(name, "thief", 2350, 0, 0, 0);
	}
}
