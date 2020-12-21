package Elements;

import logic.Game;

public class BloodTank extends GameElement{
	private int hp;
	private int cost;
	public BloodTank(Game game, int X, int Y, int cost) {
		super(game, X, Y, cost);
		this.cost = cost;
		this.hp = 1;
	}

	@Override
	public void attack() {
	}

	@Override
	public boolean positionY0() {
		return false;
	}

	@Override
	public String toString() {
		return "B-B[" + this.cost +"]";
	}

	@Override
	protected void update() {
		float income = (this.cost*10)/100;
		game.addCoins(Math.round(income));
	}
	public int getHealth() {
		return this.hp;
	}

	@Override
	public boolean receiveSlayerAttack(int damage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveVampireAttack(int damage) {
		this.hp -= hp;
		return true;
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
