package game.bomberman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author timozjx
 * 
 */

public class OnlineClient implements Runnable {
	private String serverIP;
	private Socket socket;
	private int port = 9999;
	private DataOutputStream dos;
	private DataInputStream dis;
	private ChooseMenu cm;
	public Process process;
	public NewMyFrame newmf;

	public OnlineClient() {
		cm = ObjectContainer.cm;
	}

	/**
	 * Socket intialisieren client wird verbindet laut der ip Adresse
	 */
	public boolean connect(String serverIP) {
		try {
			System.out.println("IP:" + serverIP);
			socket = new Socket(serverIP, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			System.out.println("Server hat zu lange gewartet");
			return false;
		} catch (IOException e) {
			System.out.println("Server hat zu lange gewartet");
			return false;
		}
		return true;
	}

	public void send(String str) {
		try {
			if (dos != null) {
				dos.writeUTF(str);
				System.out.println("Information :" + str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * wenn es kein Information eingelesen kann, stopp den Thread ,diese
	 * Methoden wird aufgerufen in run-Methode
	 * 
	 * @return message
	 */
	public String read() {
		String msg = null;
		try {
			if (true)
				msg = dis.readUTF();
		} catch (IOException e) {
			System.out.println("Server hat Problem");
			try {
				if (dis != null)
					dis.close();
				if (dos != null)
					dos.close();
				if (socket != null)
					socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return msg;
	}

	@Override
	public void run() {
		while (true) {

			String msg = read();

			if (true) {
				process(msg);
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	/**
	 * Der Client hat Aktion wenn es Information bekommen
	 * 
	 * @param str
	 */
	public void process(String str) {
		if (str == null)
			return;
		if (str.equals("start")) {
			System.out.println("game start");
			cm.setVisible(false);
			cm = null;
			int temp[];
			// online game
			// die Koordinaten wird verwechselt
			// fuer Client wir die Koordianten an der 384,452
			// aber fuer Client die selbe is Player 1
			temp = Config.point1;
			Config.point1 = Config.point2;
			Config.point2 = temp;
			Config.netGame = true;
			ObjectContainer.newmf = new NewMyFrame();
			this.newmf = ObjectContainer.newmf;
			newmf.jPanel1.netGame();
			process = ObjectContainer.process;
		} else {
			process.getPlayerStatus(str);
		}
	}
}
