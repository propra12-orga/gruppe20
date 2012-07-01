package game.bomberman.thing;

import java.awt.image.BufferedImage;

public abstract class Thing {
	protected int x;
	protected int y;
	protected BufferedImage showImage;

	public BufferedImage getShowImage() {
		return showImage;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setShowImage(BufferedImage showImage) {
		this.showImage = showImage;
	}

	public abstract void affect();

}
