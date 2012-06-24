package game.bomberman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class OnlineClient implements Runnable {
	private String serverIP;
	private int port = 9999;
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	public boolean join = false;

	private ChooseMenu cm;
	public NewMyFrame newmf;

	public OnlineClient() {
		cm = ObjectContainer.cm;
	}

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

	public void process(String str) {
		if (str == null)
			return;
		if (str.equals("start")) {
			System.out.println("game start");
			cm.setVisible(false);
			ObjectContainer.newmf = new NewMyFrame();

		}
	}
}
