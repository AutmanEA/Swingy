package model.map;

import java.util.ArrayList;

public class GameMap {

	private int size;
	private Coordinates center;
	private Coordinates hero_current_position;
	private Coordinates hero_previous_position;

	private ArrayList<Coordinates> encounters = new ArrayList<>();

	public GameMap(int heroLevel) {
		size = ((heroLevel - 1) * 5) + 10 - (heroLevel % 2);

		center = new Coordinates(size / 2, size / 2);
		hero_current_position = new Coordinates(center);
		hero_previous_position = new Coordinates(center);

		for(int y = 1; y < size - 1; y++) {
			for (int x = 1; x < size - 1; x++) {
				if (!(x == center.getX() && y == center.getY())) {
					if (Math.random() < 0.8) {
						encounters.add(new Coordinates(x, y));
					}
				}
			}
		}
	}

	public int distanceFromCenter(Coordinates target) {
		if (target == null) {
			//TODO:exception
		}
		return (int)Math.round(Math.sqrt(Math.pow(center.getX() - target.getX(), 2) + Math.pow(center.getY() - target.getY(), 2)));
	}

	public void moveHeroBy(int x, int y) {
		if (!hasWon()) {
			hero_previous_position.set(hero_current_position);
			hero_current_position.add(x, y);
		}
	}

	public boolean hasEncounters() {
		if (encounters.contains(hero_current_position)) {
			return true;
		}
		return false;
	}

	public boolean hasWon() {
		if (hero_current_position.getX() <= 0
			|| hero_current_position.getX() >= size - 1
			|| hero_current_position.getY() <= 0
			|| hero_current_position.getY() >= size - 1) {
			return true;
		}
		return false;
	}

	public void cancelMove() {
		hero_current_position.set(hero_previous_position);
	}

	public Coordinates getHeroCurrentPosition() {
		return hero_current_position;
	}

}
