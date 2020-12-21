package Elements;

import java.util.ArrayList;

import logic.Game;
import logic.GameObjects.IAttack;

public class Board {
	private static final int BOARD_SIZE = 32;
	private static boolean dBoy;

	private ArrayList<GameElement> list;

	public Board() {
		list = new ArrayList<GameElement>();
		dBoy = false;
	}

	public boolean Add(GameElement element, Game game) {
		if (list.size() < BOARD_SIZE) {
			list.add(list.size(), element);
		}
		return list.size() < BOARD_SIZE;
	}

	public boolean checkObject(int x, int y) {
		int i = 0;
		boolean found = false;
		while (i < list.size() && found == false) {
			if ((list.get(i).getX() == x) && (list.get(i).getY() == y)) {
				found = true;
			} else {
				i++;
			}
		}
		return found;
	}
	public IAttack returnElement(int x, int y) {
		return list.get(searchPosition(x,y));
	}

	public boolean delete() {
		boolean deleted = false;
		for (int i = 0; i < list.size(); i++) {
			if (list.size() > 0 && list.get(i).getHealth() <= 0) {
				this.zeroHpAction(i);
				list.remove(i);
				//CONTAR CUANTOS ELEMENTOS SE MUEREN
				// list[i]=list[i+1];
				deleted = true;
			}
		}
		return deleted;
	}
	public void draculaDead() {
		dBoy = false;
	}
	public void update() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).update();
		}
	}
	private int searchPosition(int x, int y) {
		int i = 0;
		boolean found = false;
		while (i < list.size() && !found) {
			if ((list.get(i).getX() == x) && (list.get(i).getY() == y)) {
				found = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public void decreaseHealth(int x, int y, int damage) {
		int pos = searchPosition(x, y);
		list.get(pos).getDamage(damage);
	}
	public void useFlashLight() {
		for(int i = 0; i < list.size();i++) {
			list.get(i).receiveFlashLightAttack();
		}
	}
	public void garlicPush() {
		for(int i = 0; i < list.size();i++) {
			if(checkEmpty(list.get(i).getX()+1, list.get(i).getY())){
				list.get(i).setGarlicX(list.get(i).x+1);
			}
		}
	}

	public String printPosition(int x, int y) {
		String stringSquare = " ";
		if(checkObject(x,y)) {
			stringSquare = list.get(searchPosition(x, y)).toString();
		}
		return stringSquare;
	}

	public boolean checkEmpty(int x, int y) {
		boolean empty = false;
		if (!checkObject(x, y)) {
			empty = true;
		}
		return empty;
	}
	/*
	 * public String printInfoDebug(int i) { return list.get(i).printInfo(); }
	 */

	public boolean checkWinnerVampire() {
		boolean endVampire = false;
			for (int i = 0; i < list.size(); i++) {
				if (!endVampire) {
					endVampire = list.get(i).positionY0();
			}
		}
		return endVampire;
	}
	public static Vampire vampireType(String type ,int x,int y,Game game) {
		Vampire v;
		
		if((type.equals("Dracula")||type.equals("d"))&& (dBoy == false)) {
			v=new Dracula(x,y,game);
			dBoy = true;
			System.out.print("Dracula has arrisen");
		}
		else if(type.equals("Explosive")||type.equals("e")) {
			v=new ExplosiveVampire(x,y,game);
		} else {
			v=new Vampire(x,y,game);
		}
		return v;
	}
	public void zeroHpAction(int i) {
			list.get(i).zeroHpAction();
	}

	public int getYPosition(int i) {
		return list.get(i).getY();
	}
	public int getSize() {
		return list.size();
	}

}

/*
 * ASIGNMENT 1
 * 
 * package Elements;
 * 
 * 
 * public class Board { private VampireList vampireList; private SlayerList
 * slayerList;
 * 
 * 
 * public Board() { this.vampireList = new VampireList(); this.slayerList = new
 * SlayerList(); } public boolean checkEmpty(int x, int y) { boolean empty =
 * false; if (!foundSlayer(x, y) && !foundVampire(x, y)) { empty = true; }
 * return empty; } public String getObject(int x, int y) { String str; if
 * (foundSlayer(x, y)) { str = printPositionS(searchPositionS(x, y)); } else if
 * (foundVampire(x, y)) { str = printPositionV(searchPositionV(x, y)); } else {
 * str = " "; } return str; }
 * 
 * public boolean checkWinnerVampire() { boolean found = false; if
 * (vampireList.winnerVampire()) found = true; return found; } public void
 * update() { slayerList.update(); vampireList.update(); }
 * 
 * public void initialize() { vampireList = new VampireList(); slayerList = new
 * SlayerList(); }
 * 
 * public boolean deleteVamp() { return vampireList.Delete(); }
 * 
 * public void deleteSlayer() { slayerList.Delete(); }
 * 
 * public boolean foundSlayer(int x, int y) { return slayerList.foundSlayer(x,
 * y); }
 * 
 * public boolean foundVampire(int x, int y) { return
 * vampireList.vampireFound(x, y); }
 * 
 * public int searchPositionS(int x, int y) { return
 * slayerList.searchPosition(x, y); }
 * 
 * public int searchPositionV(int x, int y) { return
 * vampireList.searchPosition(x, y); }
 * 
 * public String printPositionS(int i) { return slayerList.printPosition(i); }
 * 
 * public String printPositionV(int i) { return vampireList.printPosition(i); }
 * public void vampDecreaseHealth(int pos, int damage) {
 * vampireList.decreaseHealth(pos, damage); } public void slayDecreaseHealth(int
 * pos, int damage) { slayerList.decreaseHealth(pos, damage); } public int
 * vampCounter() { return vampireList.getcounter(); } public void
 * addVamp(Vampire vamp) { vampireList.Add(vamp); } public void addSlayer(Slayer
 * slay) { slayerList.Add(slay); } }
 */