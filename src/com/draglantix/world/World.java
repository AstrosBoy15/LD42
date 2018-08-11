package com.draglantix.world;

import java.util.Random;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.Assets;
import com.draglantix.buildings.Building;
import com.draglantix.buildings.House;
import com.draglantix.engine.Engine;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.tiles.Tile;
import com.draglantix.window.Window;

public class World {

	private Assets assets;

	private Random rand;

	private boolean isMouseLeftButtonDown = false;
	private boolean isMouseRightButtonDown = false;
	private boolean hasCollidedRight = false;
	private boolean hasCollidedLeft = false;
	private Building currentSelectedBuilding = null;

	public World(Assets assets) {
		this.assets = assets;
		rand = new Random();
	}

	public void init() {
		assets.playAssets.player = new House(assets.playAssets.squareTex.getTextureID(), new Vector2f(0, 0),
				new Vector2f(0, 0), new Vector2f(16, 16), assets);
		Engine.addBuildings(assets.playAssets.player);

		assets.playAssets.createCamera();

		House sheep = new House(assets.playAssets.squareTex.getTextureID(), new Vector2f(64, 64), new Vector2f(0, 0),
				new Vector2f(assets.playAssets.triangleTex.getWidth(), assets.playAssets.triangleTex.getHeight()),
				assets);
		House sheep2 = new House(assets.playAssets.squareTex.getTextureID(), new Vector2f(0, 128), new Vector2f(0, 0),
				new Vector2f(assets.playAssets.triangleTex.getWidth(), assets.playAssets.triangleTex.getHeight()),
				assets);
		House sheep3 = new House(assets.playAssets.squareTex.getTextureID(), new Vector2f(128, 0), new Vector2f(0, 0),
				new Vector2f(assets.playAssets.triangleTex.getWidth(), assets.playAssets.triangleTex.getHeight()),
				assets);
		Engine.addBuildings(sheep, sheep2, sheep3);

		createMap();
	}

	public void createMap() {
		int numberOfTilesX = Configs.worldWidth / Configs.TILE_SIZE;
		int numberOfTilesY = Configs.worldHeight / Configs.TILE_SIZE;
		for (int x = -numberOfTilesX / 2; x < numberOfTilesX / 2; x++) {
			for (int y = -numberOfTilesY / 2; y < numberOfTilesY / 2; y++) {
				int stoneChance = rand.nextInt(10);
				if (stoneChance == 1) {
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

	public void tick() {
		// Left mouse button
		if (Window.getInput().isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			if (!isMouseLeftButtonDown) {
				for (Gui gui : Engine.getGuis()) {
					if (gui.getBounding() != null) {
						if (gui.checkForCollision()) {
							hasCollidedRight = true;
							gui.getComponenet().update();
							break;
						}
					}
				}
			}
			isMouseLeftButtonDown = true;
			if (currentSelectedBuilding != null && !hasCollidedRight) {
				currentSelectedBuilding.removeGui();
				currentSelectedBuilding = null;
			}
		} else {
			isMouseLeftButtonDown = false;
			hasCollidedRight = false;
		}

		// Right mouse button
		if (Window.getInput().isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {
			if (!isMouseRightButtonDown && !isMouseLeftButtonDown) {
				isMouseRightButtonDown = true;
				for (Building building : Engine.getBuildings()) {
					if (building.checkForCollisions()) {
						if (currentSelectedBuilding != null) {
							currentSelectedBuilding.removeGui();
						}
						currentSelectedBuilding = building;
						currentSelectedBuilding.update();
						break;
					}
				}
			}
		} else {
			isMouseRightButtonDown = false;
		}

		if (!hasCollidedRight) {
			assets.playAssets.camera.update();
		}
	}

}
