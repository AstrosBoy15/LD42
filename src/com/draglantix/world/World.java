package com.draglantix.world;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.Assets;
import com.draglantix.buildings.Building;
import com.draglantix.buildings.Empty;
import com.draglantix.buildings.FarmLand;
import com.draglantix.buildings.House;
import com.draglantix.engine.Engine;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.statistics.Statistics;
import com.draglantix.tiles.Tile;
import com.draglantix.window.Window;

public class World {

	private Assets assets;

	private boolean isMouseLeftButtonDown = false;
	private boolean isMouseRightButtonDown = false;
	private boolean hasCollidedRight = false;
	//private boolean hasCollidedLeft = false;
	private Building currentSelectedBuilding = null;

	public World(Assets assets) {
		this.assets = assets;
	}

	public void init() {
		House house = new House(assets.playAssets.squareTex.getTextureID(), new Vector2f(0, 0),
				new Vector2f(0, 0), new Vector2f(16, 16), assets);

		assets.playAssets.createCamera();

		Empty empty = new Empty(assets.playAssets.squareTex.getTextureID(), new Vector2f(64, 64), new Vector2f(0, 0),
				new Vector2f(assets.playAssets.squareTex.getWidth(), assets.playAssets.squareTex.getHeight()), assets);
		House house2 = new House(assets.playAssets.squareTex.getTextureID(), new Vector2f(0, 128), new Vector2f(0, 0),
				new Vector2f(assets.playAssets.squareTex.getWidth(), assets.playAssets.squareTex.getHeight()), assets);
		FarmLand farmLand = new FarmLand(assets.playAssets.squareTex.getTextureID(), new Vector2f(128, 0), new Vector2f(0, 0),
				new Vector2f(assets.playAssets.squareTex.getWidth(), assets.playAssets.squareTex.getHeight()), assets);
		Engine.addBuildings(empty, house, house2, farmLand);

		createMap();
	}

	public void createMap() {
		int numberOfTilesX = Configs.worldWidth / Configs.TILE_SIZE;
		int numberOfTilesY = Configs.worldHeight / Configs.TILE_SIZE;
		for (int x = -numberOfTilesX / 2; x < numberOfTilesX / 2; x++) {
			for (int y = -numberOfTilesY / 2; y < numberOfTilesY / 2; y++) {
				assets.playAssets.tileMap.setTile(new Tile(assets.playAssets.grassData, new Vector2f(
						(x * Configs.TILE_SIZE * Configs.worldScale + Configs.TILE_SIZE * Configs.worldScale / 2) * 2,
						(y * Configs.TILE_SIZE * Configs.worldScale + Configs.TILE_SIZE * Configs.worldScale / 2) * 2),
						new Vector2f(0, 0),
						new Vector2f(Configs.TILE_SIZE * Configs.worldScale, Configs.TILE_SIZE * Configs.worldScale),
						assets), x, y);

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
				currentSelectedBuilding.reset();
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
							currentSelectedBuilding.reset();
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
		
		Statistics.update();
	}

}
