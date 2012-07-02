package game.bomberman;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Zustaendig fuer die Kommunikation zwischen Server und Client.
 * 
 * @author timo
 * 
 */
public class Process {
	private NewMyFrame newmf;
	private Player bb2;
	private Bomb[] bombs;
	private Player player;

	public Process() {
		this.newmf = ObjectContainer.newmf;
		this.bb2 = newmf.jPanel1.getBb2();
		this.bombs = newmf.jPanel1.getBomb2();
	}

	/**
	 * uebermittelt aktuelle Koordinaten des Spielers und seine
	 * Bewegungsanimation
	 * 
	 * @param x
	 * @param y
	 * @param status
	 */
	public void moveProcess(int x, int y, String status) {
		if (bb2 != null) {
			bb2.setX(x);
			bb2.setY(y);
			bb2.setStatus(status);
			newmf.jPanel1.repaint();
		}
	}

	/**
	 * wenn wir die Bomb setzen bekommen wir die Koordinaten
	 * 
	 * @param b1X
	 * @param b1Y
	 * @param b2X
	 * @param b2Y
	 */
	public void bombProcess(int type, int x, int y) {

		if (type == 11) {
			bombs[0].setX(x);
			bombs[0].setY(y);
			bombs[0].setCountdown(70);
			bombs[0].setShowImage(StaticValue.allBoomImage.get(0));

		} else if (type == 12) {
			bombs[1].setX(x);
			bombs[1].setY(y);
			bombs[1].setCountdown(70);
			bombs[1].setShowImage(StaticValue.allBoomImage.get(0));
		}

	}

	/**
	 * behandeln die Information von Socket,
	 * 
	 * @param message
	 */
	public void getPlayerStatus(String message) {
		int x = -100;
		int y = -100;

		int type = 0;
		// type fuer welche Bomb
		int bx = -1000;
		int by = -1000;

		String status = "";

		if (message != null) {
			if (this.bb2 == null)
				return;
			System.out.println("Information£ºmsg:" + message);
			// bomb
			if (message.startsWith("31")) {
				String reg = "3(\\d+)x:(-?\\d+)_y:(-?\\d+)";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(message);
				while (matcher.find()) {
					type = Integer.parseInt(matcher.group(1));
					bx = Integer.parseInt(matcher.group(2));
					by = Integer.parseInt(matcher.group(3));
					this.bombProcess(type, bx, by);

				}
			}
			// player
			if (message.startsWith("1")) {

				String reg = "1x:(\\d+)_y:(\\d+)_s:(\\w+)";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(message);
				while (matcher.find()) {
					System.out.println("group():" + matcher.group(0));
					x = Integer.parseInt(matcher.group(1));
					y = Integer.parseInt(matcher.group(2));
					status = matcher.group(3);
					this.moveProcess(x, y, status);

				}

			}
			// tuer
			if (message.startsWith("2")) {

				String reg = "2x:(\\d+)_y:(\\d+)";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(message);
				while (matcher.find()) {
					x = Integer.parseInt(matcher.group(1));
					y = Integer.parseInt(matcher.group(2));
					Config.dx = x;
					Config.dy = y;
				}

			}
		}
	}

}
