package Elements;

import logic.Game;
import logic.GameObjects.IAttack;

public class Dracula extends Vampire {
	private int speed;
	private int damage;
	private int internalCycle;
	private int garlic;

	public Dracula(int x, int y, Game game) {
		super(x, y, game);
		this.internalCycle = 0;
		this.speed = 1;
		this.damage = 3;
		this.garlic = 0;
	}

	public void attack() {
		if (isAlive()) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null) {
				//TEMPORAL HABRÁ QUE SABER CUÁNTA VIDA TIENEN
				other.receiveVampireAttack(damage);
			}
		}
	}
	public void receiveFlashLightAttack() {
		this.hp = this.hp;
	}
	public boolean updateDraculaAlive() {
		if(this.hp == 0) {
			game.draculaDead();
		}
		return false;
	}

	public String toString() {
		String str = "V-V [" + this.hp + "]";

		return str;
	}

	// GETTERS Y SETTERS
	public int getInternalCycle() {
		return internalCycle;
	}

	public void setInternalCycle(int internalCycle) {
		this.internalCycle = internalCycle;
	}

	public int getSpeed() {
		return speed;
	}

	public void setGarlicX(int newX) {
		this.x = newX;
		this.internalCycle = 0;
		this.garlic = 2;
		if(this.x > 7){
			this.hp = 0;
		}
	}

	@Override
	public boolean positionY0() {
		return this.x == 0;
	}

	@Override
	public boolean receiveVampireAttack(int damage) {
		return false;
	}

}
