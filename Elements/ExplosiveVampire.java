package Elements;

import logic.Game;
import logic.GameObjects.IAttack;

public class ExplosiveVampire extends Vampire {
	private int damage;
	private int speed;
	private int internalCycle;
	private int garlic;

	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, game);
		this.damage = 1;
		this.speed = 1;
		this.garlic = 2;
		this.internalCycle = 0;
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
			} 
			garlic++;
		}
		garlic--;
	}
	public void attack() {
	
			for (int i = this.x - 1; i < this.x + 2; i++) {
				for (int j = this.y - 1; j < this.y + 2; j++) {
					IAttack other = game.getAttackableInPosition(i, j);
					if (other != null) {
						other.receiveExplosiveAttack(damage);
					}
				}
			}
		
	}
	public void zeroHpAction() {
			attack();
	}

	public String toString() {
		return "V*V ["+this.getHealth()+"]";
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

	@Override
	public boolean positionY0() {
		return this.x == 0;
	}

	@Override
	public boolean receiveVampireAttack(int damage) {
		return false;
	}
	public boolean receiveExplosiveAttack(int damage) {
		return false;
	}

}
