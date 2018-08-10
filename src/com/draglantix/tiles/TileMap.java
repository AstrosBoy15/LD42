package com.draglantix.tiles;

import com.draglantix.engine.Engine;

public class TileMap {

	private int width, height;

	public Tile[][] tiles;

	public TileMap(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setTile(Tile tile, int x, int y) {
		tiles[(int) (x + width / 2)][(int) (y + height / 2)] = tile;
		Engine.addTiles(tile);
	}

	public Tile getTile(int x, int y) {
		return tiles[x + width / 2][y + height / 2];
	}

}
