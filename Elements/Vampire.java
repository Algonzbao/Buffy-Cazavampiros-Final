package Elements;

import logic.Game;
import logic.GameObjects.IAttack;

public class Vampire extends GameElement {
	private int speed;
	private int damage;
	private int internalCycle;
	private int garlic;

	public Vampire(int x, int y, Game game) {
		super(game, x, y, 5);
		this.internalCycle = 0;
		this.speed = 1;
		this.damage = 1;
		this.garlic = 2;
	}

	public void attack() {
		if (isAlive()) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null) {
				other.receiveVampireAttack(damage);
			}
		}
	}
	public void receiveFlashLightAttack() {
		this.hp = 0;
		game.updateVampireCount();
	}
	public boolean receiveSlayerAttack(int damage) {
		this.hp -= damage;
		if(this.hp== 0) {
			game.updateVampireCount();
		}
		return true;
	}


	public void update() {
		internalCycle += 1;

		if (garlic == 0) {// COMPROBAR SI HAY HUECO
			if (game.checkEmpty(x - 1, y) && internalCycle % 2 == 0) {
				this.x = this.x - speed;
			} else if (game.checkEmpty(x - 1, y) && internalCycle % 2 != 0) {
				// ESTO LO PONGO ASI PQ QUIERO QUE SE MANTENGA EN SU SITIO PERO TAMBIEN NECESITO
				// UN ELSE PARA ATACAR
				this.x = this.x;
			} else {
				this.attack();
			}
			garlic++;
		}
		garlic--;
	}

	public String toString() {
		String str = "V^V [" + this.hp + "]";

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
		this.garlic = 1;
		if(this.x >= game.getRow()){
			this.hp = 0;
			game.updateVampireCount();
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

	@Override
	public boolean receiveExplosiveAttack(int damage) {
		this.hp -= damage;
		if(this.hp== 0) {
			game.updateVampireCount();
		}
		return true;
	}

	@Override
	protected void zeroHpAction() {
		// TODO Auto-generated method stub
		
	}

}
