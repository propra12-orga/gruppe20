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
	public void bombProcess(int b1X, int b1Y, int b2X, int b2Y) {
		bombs[0].setX(b1X);
		bombs[0].setY(b1Y);
		bombs[1].setX(b2X);
		bombs[1].setY(b2Y);

		for (int i = 0; i < bombs.length; i++) {
			if (bombs[i].getY() > 0) {
				bombs[i].setCountdown(70);
				bombs[i].setShowImage(StaticValue.allBoomImage.get(0));
			}
		}
		newmf.jPanel1.drawBombs(newmf.jPanel1.getGraphics(), bombs, player);
	}

	/**
	 * behandeln die Information von Socket,
	 * 
	 * @param message
	 */
	public void getPlayerStatus(String message) {
		int x = -100;
		int y = -100;

		int b1X = -200;
		int b1Y = -200;
		int b2X = -200;
		int b2Y = -200;

		String status = "";

		// Beispiel
		// msg:1x:195_y:168_s:right--moving_b1:50|118_b2:35|168
		if (message != null) {
			if (this.bb2 == null) {
				return;
			}
			System.out.println("get the information:" + message);
			// wenn der Anfang der Information ist 0
			if (message.startsWith("0")) {
				String reg = "0x:(-?\\d+)_y:(-?\\d+)x:(-?\\d+)_y:(-?\\d+)";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(message);
				while (matcher.find()) {
					b1X = Integer.parseInt(matcher.group(1));
					b1Y = Integer.parseInt(matcher.group(2));
					b2X = Integer.parseInt(matcher.group(3));
					b2Y = Integer.parseInt(matcher.group(4));
					this.bombProcess(b1X, b1Y, b2X, b2Y);

				}
			}
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
