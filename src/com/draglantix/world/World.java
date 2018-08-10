package com.draglantix.world;

import java.util.Random;

import org.joml.Vector2f;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.entities.Player;
import com.draglantix.entities.Sheep;
import com.draglantix.main.Configs;
import com.draglantix.tiles.Tile;

public class World {

	private Assets assets;

	private Random rand;

	public World(Assets assets) {
		this.assets = assets;
		rand = new Random();
	}

	public void init() {
		assets.playAssets.player = new Player(Configs.TEXTURE_NULL, new Vector2f(0, 0), new Vector2f(0, 0),
				new Vector2f(16, 16), 5, assets);
		Engine.addEntities(assets.playAssets.player);

		assets.playAssets.createCamera();

		Sheep sheep = new Sheep(assets.playAssets.triangleTex.getTextureID(), new Vector2f(64, 64), new Vector2f(0, 0),
				new Vector2f(assets.playAssets.triangleTex.getWidth(), assets.playAssets.triangleTex.getHeight()), 1,
				assets);
		Sheep sheep2 = new Sheep(assets.playAssets.triangleTex.getTextureID(), new Vector2f(0, 128), new Vector2f(0, 0),
				new Vector2f(assets.playAssets.triangleTex.getWidth(), assets.playAssets.triangleTex.getHeight()), 1,
				assets);
		Sheep sheep3 = new Sheep(assets.playAssets.triangleTex.getTextureID(), new Vector2f(128, 0), new Vector2f(0, 0),
				new Vector2f(assets.playAssets.triangleTex.getWidth(), assets.playAssets.triangleTex.getHeight()), 1,
				assets);
		Engine.addEntities(sheep, sheep2, sheep3);

		createMap();
	}

	public void createMap() {
		int numberOfTilesX = Configs.worldWidth / Configs.TILE_SIZE;
		int numberOfTilesY = Configs.worldHeight / Configs.TILE_SIZE;
		for(int x = -numberOfTilesX / 2; x < numberOfTilesX / 2; x++) {
			for(int y = -numberOfTilesY / 2; y < numberOfTilesY / 2; y++) {
				int stoneChance = rand.nextInt(10);
				if(stoneChance == 1) {
					assets.playAssets.tileMap.setTile(new Tile(assets.playAssets.stoneData, new Vector2f(
							(x * Configs.TILE_SIZE * Configs.worldScale + Configs.TILE_SIZE * Configs.worldScale / 2)
									* 2,
							(y * Configs.TILE_SIZE * Configs.worldScale + Configs.TILE_SIZE * Configs.worldScale / 2)
									* 2),
							new Vector2f(0, 0), new Vector2f(Configs.TILE_SIZE * Configs.worldScale,
									Configs.TILE_SIZE * Configs.worldScale),
							assets), x, y);
				} else {
					assets.playAssets.tileMap.setTile(new Tile(assets.playAssets.grassData, new Vector2f(
							(x * Configs.TILE_SIZE * Configs.worldScale + Configs.TILE_SIZE * Configs.worldScale / 2)
									* 2,
							(y * Configs.TILE_SIZE * Configs.worldScale + Configs.TILE_SIZE * Configs.worldScale / 2)
									* 2),
							new Vector2f(0, 0), new Vector2f(Configs.TILE_SIZE * Configs.worldScale,
									Configs.TILE_SIZE * Configs.worldScale),
							assets), x, y);
				}
			}
		}
	}

}
