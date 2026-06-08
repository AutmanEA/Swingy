package model.map;

public class Coordinates {
	private int x;
	private int y;


	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	Coordinates(int _x, int _y) {
		x = _x;
		y = _y;
	}

	Coordinates(Coordinates _toCopy) {
		x = _toCopy.x;
		y = _toCopy.y;
	}

	public void add(int _x, int _y) {
		x += _x;
		y += _y;
	}

	public void set(Coordinates target) {
		x = target.x;
		y = target.y;
	}
}
