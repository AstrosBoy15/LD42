package com.draglantix.models;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.joml.Vector2f;

public class SpriteSheet {

	private BufferedImage image;
	private int width, height;

	public SpriteSheet(String file) {
		try {
			this.image = ImageIO.read(new FileInputStream(file));
			this.width = image.getWidth();
			this.height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage crop(Vector2f pos, Vector2f scale) {
		return image.getSubimage((int) pos.x, (int) pos.y, (int) scale.x, (int) scale.y);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
