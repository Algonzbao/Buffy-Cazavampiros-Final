package logic;

import java.util.ArrayList;
import java.util.Random;

import Elements.BloodTank;
import Elements.Board;
import Elements.GameElement;
import Elements.Slayer;
import Elements.Vampire;
import logic.GameObjects.IAttack;
import view.GamePrinter;
import view.IPrintable;

public class Game implements IAttack, IPrintable{
	// Atributos
	private Board board;

	private GamePrinter gamePrinter;
	private Player player;
	private Level level;
	private Random rand;
	private int numCycles;
	private int row;
	private int column;
	private int vampiresCreated;
	private int vampiresLeft;
	private int vampiresDead; 
	private boolean exit;
	private boolean sameCycle = false;

	// Constuctor
	public Game(Level level, Random rand) {
		this.level = level;
		this.rand = rand;
		this.inicializar();
	}

	// GAME STUFF
	public void update() {
		board.update(); //actualizar listas
		removeDead();
		player.calculateCoinCicle();
	}

	public void inicializar() {
		board = new Board();
		//list = new Board();
		this.row = level.getDimX();
		this.column= level.getDimY();
		this.gamePrinter = new GamePrinter(this, row, column);
		this.player = new Player(this);
		player.setCoins(50);
		//QUITAR VAMP MANAGER
		numCycles = 0;
		vampiresCreated = 0;
		vampiresLeft = level.getNumberOfVampires();
		vampiresDead = 0;
		exit = false;
	}
	
	public void addCycle() {
		this.numCycles++;
	}
	
	public void removeDead() {
		board.delete();	
			//En lugar de esto al final de cada turno compruebo cuantos elementos en la lista quedan de tipo vamp
			//vampireManager.setVampiresAliveToAppear(vampireManager.getVampiresAliveToAppear() - 1);
	}
	public void draculaDead() {
		board.draculaDead();
	}
	
	public void updateVampireCount() {
		 this.vampiresDead++;
	}
	
	public boolean isSameCycle(){
		return sameCycle;
	}
	
	public void setSameCycle(boolean cycle) {
		this.sameCycle = cycle;
	}
	public int boardColumns() {
		return level.getDimX();
	}
	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	//PRINT GAME
	public boolean insideBoard(int x, int y) {
		return x >= 0 && x < row && y >= 0
				&& y < column;
	}
	
	public String toString() {
		return  gamePrinter.toString();
	}
	
	public String getInfo() {
		String row1 = "\nNumber of cycles: " + numCycles;
		String row2 = "\nCoins: " + player.getCoins();
		String row3 = "\nRemaining vampires: " + this.vampiresLeft;
		String row4 = "\nVampires on the board: " +(this.vampiresCreated - this.vampiresDead);

		return row1 + row2 + row3 + row4;
	}
	
	@Override
	public String getPositionToString(int x, int y) {
		return board.printPosition(x, y);
	}
	
	public void updateGamePrinter() {
		this.setGamePrinter(new GamePrinter(this, this.row, this.column));
	}
	
	//LISTA
	public IAttack getAttackableInPosition(int x, int y) {
		if(board.checkObject(x, y)) {
			return board.returnElement(x, y);
		}
		return null;
	}
	
	public boolean addSlayer(Slayer slayer, int x, int y){
		boolean added = false;
		if(checkinboard(x, y) && checkEmpty(x,y)) {
				//board update()
				if(board.Add(slayer,this)) {
					addCycle();
					//decreaseCoins(slayerCost);
					added = true;
				}
			} else {
				System.out.println("Slayer can't be added");
				added = false;
			}

		return added;
	}
	
	public boolean addTank(BloodTank tank, int x, int y){
		boolean added = false;
		if(checkinboard(x, y) && checkEmpty(x,y)) {
				//board update()
				if(board.Add(tank,this)) {
					addCycle();
					//decreaseCoins(slayerCost);
					added = true;
				}
			} else {
				System.out.println("Tank can't be added");
				added = false;
			}

		return added;
	}
	public void useFlashLight() {
		board.useFlashLight();
	}
	
	public void garlicPush() {
		board.garlicPush();
	}
	public boolean checkEmpty(int x, int y) {
		return board.checkEmpty(x, y);
	}
	
	private boolean checkinboard(int x, int y) {
		//solo permite poner s hasta la penultima columna
		return x >=0 && x<row-1 && y >=0 && y <column;
	}
	//VAMPIRE THINGS
	private String chooseVampire(int numRandom) {
		String vampire;
		switch(numRandom) {
		case 0:
			vampire="Vampire";
			break;
		case 1:
			vampire="Dracula";
			break;
		case 2:
			vampire="Explosive";
			break;
		default :
			vampire = "Vampire";
		}
		return vampire;
	}

	public boolean toAddVampire() {
		boolean add = false;
		if (level.getNumberOfVampires() - this.vampiresCreated > 0) {
			add = this.rand.nextDouble() < level.getVampireFrequency();
		}
		return add;
	}
	
	//END GAME
	public String getWinnerMessage() {
		if(this.checkWinnerPlayer()) {
			return "VICORY!";
		}
		else if(this.checkWinnerVampire()) {
			return "VAMPIRES WIN";
		}
		else {
			return "GAME EXIT";
		}
	}
	
	public boolean checkWinnerPlayer() {
		return level.getNumberOfVampires() == this.vampiresDead;
	}
	
	public boolean checkWinnerVampire() {
		return board.checkWinnerVampire();
	}
	
	public void StopPlaying() {
		exit = true;
	}
	
	public boolean vampiresLeft() {
		return level.getNumberOfVampires() != this.vampiresDead;
	}
	
	public boolean isFinished() {
		return this.checkWinnerPlayer() || this.checkWinnerVampire() || exit;
	}
	
	//PLAYER
	public boolean enoughCoins(int slayerCost) {
		return player.getCoins() >= slayerCost;
	}
	
	public void decreaseCoins(int coins) {
		player.setCoins(player.getCoins()-coins);
	}
	
	public void addCoins(int coins) {
		player.setCoins(player.getCoins()+coins);
	}

	//COMANDS
	public void noCommandExe() {
		int vampType = rand.nextInt(3);
		int getColumn = getColumn();
		int vampireCol = rand.nextInt(getColumn);
		if (this.toAddVampire() && checkEmpty(getRow() - 1, vampireCol)) {
			addVampireAction(chooseVampire(vampType),getRow()-1, vampireCol);
		}
		
		addCycle();
	}
	//TAL VEZ METER EN BOARD
	public void addVampireAction(String vampType,int row, int vampireCol) {
		Vampire v = Board.vampireType(vampType,row, vampireCol,this);
		//Vampire vampire = new Vampire(getRow() - 1, vampireCol, this);
		board.Add(v, this);
		vampiresCreated++;
		this.vampiresLeft = level.getNumberOfVampires() - vampiresCreated;
	}
	//END
	

	

	

	/*public String getObject(int x, int y) {
		return board.getObject(x, y);
	}*/

	// SLAYER STUFF
	/*public void attackToVampire(int x, int damage) {
		int i = 0;
		while (i < column && !board.foundVampire(x, i)) {
			i++;
		}
		if (board.foundVampire(x, i)) {
			board.vampDecreaseHealth(board.searchPositionV(x, i), damage);
		}
	}*/
	
	
	
	// VAMPIRE STUFF
	/*public void attackToSlayer(int x, int y, int damage) {
		if (board.foundSlayer(x, y)) {
			board.slayDecreaseHealth(board.searchPositionS(x, y), damage);
		}
	}
	public boolean seeClose(int x , int y) {
		return board.foundVampire(x, y-1);
	}
	 */
	


	// TO STRING
	

	// COMANDOS
	/*public void addCommandExe(int param1, int param2) {
		if (checkEmpty(param1, param2) && insideBoard(Integer.toString(param1), Integer.toString(param2))) {
			// HAY DINERO
			if (player.getCoins() >= Slayer.getCost()) {
				player.setCoins(player.getCoins() - Slayer.getCost());
				// CREAR Y AÑADIR EL BICHO EN LA LISTA
				Slayer s = new Slayer(param1, param2, this);
				board.addSlayer(s);
				// AVANCE O CREACION DE VAMPIRO
				int vampireRow = rand.nextInt(getRow() - 1);
				if (vampireManager.toAddVampire() && checkEmpty(vampireRow, getColumn() - 1)) {
					Vampire vampire = new Vampire(vampireRow, getColumn() - 1, this);
					board.addVamp(vampire);
					vampireManager.setVampires(vampireManager.getLeftVampires() - 1);
				}
				addCycle();
			}
			else {
				System.out.println("You dont have enought coins");
			}
		}
	}*/

	
	
	
	



	// GETTERS Y SETTERS
	public Level getLevel() {
		return level;
	}

	public void setGamePrinter(GamePrinter gamePrinter) {
		this.gamePrinter = gamePrinter;
	}

	public Random getRand() {
		return rand;
	}

	public int getNumCycles() {
		return numCycles;
	}

	public void setNumCycles(int numCycles) {
		this.numCycles = numCycles;
	}

	public GamePrinter getGamePrinter() {
		return gamePrinter;
	}
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}