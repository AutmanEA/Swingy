package model;

import controller.Command;
import controller.Feedback;
import controller.PlayerAction;
import controller.PlayerActionStatus;
import model.hero.Hero;
import model.hero.HeroData;

public class SwingyModel {
	private Game game;

	public Game getGame() {
		return game;
	}

	public Feedback processCommand(Command command) {
		if (command == null)
			return null;

		if (game == null) {
			switch (command.action()) {
				case PlayerAction.NEW_HERO:
					createGame(loadHero("joey"));
					break;

				default:
					break;
			}
		} else {
			switch (command.action()) {
				case PlayerAction.MOVE:
					game.move((String) command.payload());
				default:
					break;
			}
		}
		return new Feedback(PlayerActionStatus.FAILURE, "y a R frr"); //TODO: remove
	}

	private PlayerActionStatus createGame(HeroData heroData) {
		Hero hero = new Hero(heroData);

		game = new Game(hero);

		return PlayerActionStatus.GAME_STARTED;
	}

	private HeroData loadHero(String name) {
		//TODO: fetch from database > unique id = name ? c'est triste mais bon :),

		//TODO: et du coup si le name existe pas, on envoie FAILURE ou on cree un nouveau ?

		return new HeroData(name, "thief", 2350, 0, 0, 0);
	}
}
