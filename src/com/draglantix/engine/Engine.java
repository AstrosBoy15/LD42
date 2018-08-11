package com.draglantix.engine;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.draglantix.assets.Assets;
import com.draglantix.buildings.Building;
import com.draglantix.buildings.BuildingRenderer;
import com.draglantix.buildings.BuildingShader;
import com.draglantix.font.Font;
import com.draglantix.font.FontRenderer;
import com.draglantix.font.FontShader;
import com.draglantix.guis.Gui;
import com.draglantix.guis.GuiRenderer;
import com.draglantix.guis.GuiShader;
import com.draglantix.main.Configs;
import com.draglantix.maps.Map;
import com.draglantix.maps.MapRenderer;
import com.draglantix.maps.MapShader;
import com.draglantix.models.RawModel;
import com.draglantix.tiles.Tile;
import com.draglantix.tiles.TileMap;
import com.draglantix.tiles.TileRenderer;
import com.draglantix.tiles.TileShader;

public class Engine {

	private static List<Gui> guis;
	private static GuiRenderer guiRenderer;
	
	private static List<Building> buildings;
	private static BuildingRenderer buildingRenderer;

	private static List<Tile> tiles;
	private static TileRenderer tileRenderer;

	private static List<Font> fonts;
	private static FontRenderer fontRenderer;

	private static List<Map> maps;
	private static MapRenderer mapRenderer;

	private static Assets assets;

	private static Vector3f clearColor = new Vector3f();

	public static RawModel quad;

	public static void init(Assets assets) {
		Engine.assets = assets;
		float[] positions = { -1, 1, -1, -1, 1, 1, 1, -1 };
		quad = assets.loader.loadToVAO(positions);

		guis = new ArrayList<Gui>();
		guiRenderer = new GuiRenderer(new GuiShader("gui"), assets);

		buildings = new ArrayList<Building>();
		buildingRenderer = new BuildingRenderer(new BuildingShader("entity"), assets);

		tiles = new ArrayList<Tile>();
		tileRenderer = new TileRenderer(new TileShader("tile"), assets);

		fonts = new ArrayList<Font>();
		fontRenderer = new FontRenderer(new FontShader("font"), assets);

		maps = new ArrayList<Map>();
		mapRenderer = new MapRenderer(new MapShader("map"), assets);
	}

	public static void tickIntro() {
		tickGuis();
		tickFonts();
	}

	public static void tickMenu() {
		tickGuis();
		tickFonts();
	}

	public static void tickPlay() {
		tickBuildings();
		tickGuis();
		tickMaps();
		tickFonts();
		tickTiles();
	}

	public static void tickPause() {

	}

	public static void renderIntro() {
		renderGuis();
		renderFonts();
	}

	public static void renderMenu() {
		renderGuis();
		renderFonts();
	}

	public static void renderPlay() {
		// renderMap(false);
		renderTiles();
		renderBuildings();
		renderFonts();
		renderGuis();
		// renderMap(true);
	}

	public static void renderPause() {
		// renderMap(false);
		renderTiles();
		renderBuildings();
		renderGuis();
		renderFonts();
		// renderMap(true);
	}

	public static void prepare(ShaderProgram shader) {
		shader.start();
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	public static void finish(ShaderProgram shader) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	public static void cleanUp() {
		guiRenderer.cleanUp();
		buildingRenderer.cleanUp();
		mapRenderer.cleanUp();
		tileRenderer.cleanUp();
		fontRenderer.cleanUp();
	}

	private static void tickGuis() {
		for(Gui gui : guis) {
			gui.tick();
		}
	}

	private static void tickBuildings() {
		for(Building building : buildings) {
			building.tick();
		}
	}

	private static void tickMaps() {
		for(Map map : maps) {
			map.tick();
		}
	}

	private static void tickTiles() {
		for(Tile tile : getCurrentTiles(assets.playAssets.tileMap, assets.playAssets.camera.getPosition())) {
			tile.tick();
		}
	}

	private static void tickFonts() {
		for(Font font : fonts) {
			font.tick();
		}
	}

	private static void renderGuis() {
		guiRenderer.renderGuis(guis);
	}

	private static void renderTiles() {
		tileRenderer.renderTiles(getCurrentTiles(assets.playAssets.tileMap, assets.playAssets.camera.getPosition()));
	}

	/*
	 * private static void renderMaps(boolean fgRequest) {
	 * mapRenderer.renderMaps(maps, fgRequest); }
	 */

	private static void renderFonts() {
		fontRenderer.renderFonts(fonts);
	}

	private static void renderBuildings() {
		buildingRenderer.renderBuildings(buildings);
	}

	public static void addGuis(Gui... guis) {
		for(Gui addRequest : guis) {
			Engine.guis.add(addRequest);
		}
	}

	public static void addBuildings(Building... buildings) {
		for(Building addRequest : buildings) {
			Engine.buildings.add(addRequest);
		}
	}

	public static void addTiles(Tile... tiles) {
		for(Tile addRequest : tiles) {
			Engine.tiles.add(addRequest);
		}
	}

	public static void addMaps(Map... maps) {
		for(Map addRequest : maps) {
			Engine.maps.add(addRequest);
		}
	}

	public static void addFonts(Font... fonts) {
		for(Font addRequest : fonts) {
			Engine.fonts.add(addRequest);
		}
	}

	public static void removeEntities(Building... buildings) {
		for(Building addRequest : buildings) {
			Engine.buildings.remove(addRequest);
		}
	}

	public static void removeGuis(Gui... guis) {
		for(Gui addRequest : guis) {
			Engine.guis.remove(addRequest);
		}
	}

	public static void removeTiles(Tile... tiles) {
		for(Tile addRequest : tiles) {
			Engine.tiles.remove(addRequest);
		}
	}

	public static void removeMaps(Map... maps) {
		for(Map addRequest : maps) {
			Engine.maps.remove(addRequest);
		}
	}

	public static void removeFonts(Font... fonts) {
		for(Font addRequest : fonts) {
			Engine.fonts.remove(addRequest);
		}
	}

	public static List<Building> getBuildings() {
		return buildings;
	}

	public static List<Gui> getGuis() {
		return guis;
	}

	public static List<Tile> getTiles() {
		return tiles;
	}

	public static List<Map> getMaps() {
		return maps;
	}

	public static List<Font> getFonts() {
		return fonts;
	}

	public static void setClearColor(Vector3f color) {
		clearColor = color;
	}

	public static Vector3f getClearColor() {
		return clearColor;
	}

	public static List<Tile> getCurrentTiles(TileMap map, Vector2f pos) {
		int numTilesX = (int) (assets.playAssets.camera.getZoom() * Configs.WIDTH / Configs.TILE_SIZE / Configs.worldScale + 4);
		int numTilesY = (int) (assets.playAssets.camera.getZoom() * Configs.HEIGHT / Configs.TILE_SIZE
				/ Configs.worldScale + 4);

		List<Tile> tiles = new ArrayList<Tile>();

		for(int x = -numTilesX / 2; x < numTilesX / 2; x++) {
			for(int y = -numTilesY / 2; y < numTilesY / 2; y++) {
				try {
					tiles.add(map.getTile((int) (x + pos.x / (Configs.TILE_SIZE * Configs.worldScale * 2)),
							(int) (y + pos.y / (Configs.TILE_SIZE * Configs.worldScale * 2))));
				} catch (ArrayIndexOutOfBoundsException exception) {

				}
			}
		}
		return tiles;
	}

}
