package com.draglantix.tiles;

import com.draglantix.models.Texture;

public class TileData {

	private Texture texture;
	private int id;
	private boolean hasBounding;

	public TileData(Texture texture, int id, boolean hasBounding) {
		this.texture = texture;
		this.id = id;
		this.hasBounding = hasBounding;
	}

	public Texture getTexture() {
		return texture;
	}

	public int getID() {
		return id;
	}

	public boolean getBounding() {
		return hasBounding;
	}

}
