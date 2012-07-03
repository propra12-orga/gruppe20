package game.bomberman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OnlineServer implements Runnable {

	private int port = 9999;
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	public boolean join = false;
	public Process process;

	public OnlineServer() {

	}

	public void startServer() {
		try {
			ServerSocket s = new ServerSocket(port);
			System.out.println("Server hat gestartet");
			socket = s.accept();
			System.out.println(ObjectContainer.olclient);
			System.out.println("Client wird eingeladen"
					+ socket.getInetAddress());
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void send(String str) {
		if (dos != null) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	public String read() {
		String msg = null;

		if (dis != null)
			try {
				msg = dis.readUTF();
			} catch (IOException e) {

				e.printStackTrace();
			}

		return msg;
	}

	public void run() {
		this.startServer();
		while (dis != null) {

			String msg = read();
			if (msg != null) {
				System.out.println("wir bekommen die Information " + msg);
				process(msg);
			}
		}
	}

	public void process(String str) {
		if (str.equals("join")) {
			join = true;
		} else {

			if (process != null) {
				process.getPlayerStatus(str);
			}
		}
	}

}
