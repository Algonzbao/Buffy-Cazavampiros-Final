package Elements;

import logic.Game;
import logic.GameObjects.IAttack;

public abstract class GameElement implements IAttack {

	protected int x;
	protected int y;
	protected int hp;
	protected boolean canZero = false;
	protected Game game;

	public GameElement(Game game, int X, int Y, int health) {
		this.x = X;
		this.y = Y;
		this.game = game;
		hp = health;
	}
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	public boolean isAlive() {
		return hp > 0;
	}

	/*
	 * public void initElement(GameElement element) {
	 * if(element.getClass().equals(Missile.class)) game.shootMissile(); }
	 */

	public int getHealth() {
		return this.hp;
	}

	public boolean isOnPosition(int X, int Y) {
		return this.x == X && y == Y;
	}

	public void getDamage(int damage) {
		if (hp >= damage)
			hp -= damage;
		else {
			hp = 0;
		}
	}

	public boolean isOut() {
		return !game.insideBoard(x,y);
	}
	public abstract boolean positionY0();
	public abstract String toString();
	protected abstract void update();
	public abstract boolean receiveSlayerAttack(int damage);
	public abstract boolean receiveVampireAttack(int damage) ;
	protected abstract void setGarlicX(int i);
	protected abstract void receiveFlashLightAttack();
	public abstract boolean receiveExplosiveAttack(int damage);
	protected abstract void zeroHpAction();
}
