package com.draglantix.world;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.Assets;
import com.draglantix.buildings.Building;
import com.draglantix.buildings.Empty;
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
		assets.playAssets.createCamera();
		createMap();
	}

	public void createMap() {
		int numberOfTilesX = Configs.worldWidth / Configs.TILE_SIZE;
		int numberOfTilesY = Configs.worldHeight / Configs.TILE_SIZE;
		for (int x = -numberOfTilesX / 2; x < numberOfTilesX / 2; x++) {
			for (int y = -numberOfTilesY / 2; y < numberOfTilesY / 2; y++) {
				
				Vector2f pos = new Vector2f(
						(x * Configs.TILE_SIZE * Configs.worldScale + Configs.TILE_SIZE * Configs.worldScale / 2) * 2,
						(y * Configs.TILE_SIZE * Configs.worldScale + Configs.TILE_SIZE * Configs.worldScale / 2) * 2);
				
				Vector2f scale = new Vector2f(Configs.TILE_SIZE * Configs.worldScale, Configs.TILE_SIZE * Configs.worldScale);
				
				assets.playAssets.tileMap.setTile(new Tile(assets.playAssets.sandData, pos,
						new Vector2f(0, 0), scale,
						assets), x, y);
				Engine.addBuildings(new Empty(assets.playAssets.blankTex.getTextureID(), pos, new Vector2f(0, 0), new Vector2f(32, 32), assets));

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
