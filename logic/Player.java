package logic;

public class Player {
	private int coins;
	private int random;
	private Game game;

	public Player(Game game) {
		this.game = game;
		this.coins = 0;
	}

	public int calculateCoinCicle() {
		random = game.getRand().nextInt(2); //funcion entre 0-1 y lo guardo en random
		if (random < 0.5) {
			coins += 10;
		}
		return coins;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
}