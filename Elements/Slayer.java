package Elements;

import logic.Game;
import logic.GameObjects.IAttack;

public class Slayer extends GameElement {
	private final static int cost = 50;
	private int frequency;
	private final static int damage = 1;

	public Slayer(Game game, int x, int y) {
		super(game, x, y, 3);
		this.frequency = 1;
	}

	public void attack() {
		if (isAlive()) {
			boolean shoot = false;
			for (int i = this.x+1 ; i < game.boardColumns(); i++) {
				if (shoot == false) {
					IAttack other = game.getAttackableInPosition(i,y);
					if (other != null) {
						shoot = other.receiveSlayerAttack(damage);
					}
				}
			}
		}
		
	}

	public boolean receiveVampireAttack(int damage) {
		this.hp -= damage;
		return true;
	}

	public void update() {
		attack();
	}

	public String toString() {
		String str = "<-> [" + this.hp + "]";

		return str;
	}

	public static int getCost() {
		return cost;
	}

	public int getFrequency() {
		return frequency;
	}

	@Override
	public boolean positionY0() {
		return false;
	}

	@Override
	public boolean receiveSlayerAttack(int damage) {
		return false;
	}

	@Override
	protected void setGarlicX(int i) {
	}

	@Override
	protected void receiveFlashLightAttack() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean receiveExplosiveAttack(int damage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void zeroHpAction() {
		// TODO Auto-generated method stub
		
	}

}