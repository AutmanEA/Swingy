package model;

import controller.Command;
import controller.PlayerAction;
import model.hero.Hero;
import model.hero.HeroData;

public class SwingyModel {
	private Game game;

	public Game getGame() {
		return game;
	}

	public void processCommand(Command command) {
		if (game == null) {
			switch (command.getAction()) {
				case PlayerAction.NEW_HERO:
					createGame(loadHero("joey"));
					break;

				default:
					break;
			}
		} else {

		}
	}

	public void createGame(HeroData heroData) {
		Hero hero = new Hero(heroData);

		game = new Game(hero);
	}

	public HeroData loadHero(String name) {
		//TODO: fetch from database > unique id = name ? c'est triste mais bon :)
		return new HeroData(name, "thief", 2350, 0, 0, 0);
	}
}
//TODO c'est lui qui va appeler les bonnes fonctions de la games aussi avec les commandes qu'on recoit
//TODO il va faire tourner la game mais c'est lui qui va gerer la communication avec le controller et la DB
