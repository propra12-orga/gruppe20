package game.bomberman.thing;

import game.bomberman.Bomb;
import game.bomberman.Player;

public class BombBuff extends Thing {

	// Itemsdauer
	private int continueTime = 200;
	// Player mit BombBuff
	private Player bb;
	// Bomb type
	private int type = 1;
	private Bomb[] bombs;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	// public BombBuff(Player bb) {
	// this.bb = bb;
	// this.bombs = bb.getBombs();
	// // Items einfuegen in Players Items
	// bb.things.add(this);
	// }

	public int getContinueTime() {
		return continueTime;
	}

	public void timeDown() {
		continueTime--;
	}

	@Override
	public void affect() {
		if (type == 1 && continueTime > 0) {
			this.BombArea();
		} else if (type == 1 && continueTime <= 0) {
			this.disappear();

		}
		if (continueTime > 0) {
			this.timeDown();
		}
	}

	public void BombArea() {
		int area = 9;

		// bomb radius vergroessen
		// ungeradezahl (area-1)/2
		// Thread je 50 ms
		if (continueTime < 200 - 2)
			return;

		if (bombs[0].getCountdown() > 0) {

		}
		bombs[0].setXlength(area);
		bombs[0].setYlength(area);

		bombs[1].setXlength(area);
		bombs[1].setYlength(area);

		System.out.println("bomb power bis 9");
	}

	public void disappear() {
		System.out.println("bomb power disapper");
		int area = 5; // bomb status disapper
		bombs[0].setXlength(area);
		bombs[0].setYlength(area);
		bombs[1].setXlength(area);
		bombs[1].setYlength(area);
		// time is up,remove BUff
		bb.things.remove(this);
	}

}
