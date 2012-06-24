package game.bomberman;

import java.io.File;
import java.io.IOException;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.MediaLocator;

public class MySound {
	public static javax.media.Player player = null;

	public static String bg = "";
	public static String bombSet = "";
	public static String fire = "";
	public static String dead = "";

	static {
		String r = System.getProperty("user.dir") + "\\sound\\";
		bg = r + "bmg1.wav";
		bombSet = r + "boom2.wav";
		fire = r + "fire2.wav";
		dead = r + "cry.wav";
	}

	public static void PlaySound(String path, int status) throws Exception,
			CannotRealizeException, IOException {
		File musicFile = new File(path);
		if (musicFile.exists()) {
			MediaLocator locator = new MediaLocator("file:"
					+ musicFile.getAbsolutePath());
			player = Manager.createRealizedPlayer(locator);
			player.prefetch();
		}
		player.start();
	}

	public static void stop() {
		if (player != null)
			player = null;
	}
}
