package com.draglantix.assets;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.engine.Camera;
import com.draglantix.engine.Engine;
import com.draglantix.main.Configs;
import com.draglantix.models.SpriteSheet;
import com.draglantix.models.Texture;
import com.draglantix.tiles.TileData;
import com.draglantix.tiles.TileMap;
import com.draglantix.util.FBO;
import com.draglantix.window.Window;
import com.draglantix.world.World;

public class PlayStateAssets {

	public Camera camera;

	private Assets assets;

	public Texture squareTex;

	public SpriteSheet tileSet;

	public TileMap tileMap;

	public TileData grassData;

	public boolean isCameraCreated = false;

	public FBO worldBuffer;

	public World world;

	public PlayStateAssets(Assets assets) {

		this.assets = assets;

		worldBuffer = new FBO(Window.getWidth(), Window.getHeight());

	}

	public void init() {

		Configs.worldScale = 2f;
//		Configs.worldWidth = mapTex.getWidth() * Configs.worldScale * 2;
//		Configs.worldHeight = mapTex.getHeight() * Configs.worldScale * 2;

		Configs.worldWidth = (int) (192 * Configs.worldScale);
		Configs.worldHeight = (int) (192 * Configs.worldScale);

		tileSet = new SpriteSheet("res/textures/world/grass.png");
		
		squareTex = new Texture("res/textures/square.png");

		grassData = new TileData(new Texture(tileSet.crop(new Vector2f(0, 0), new Vector2f(Configs.TILE_SIZE))), 0,
				false);

		tileMap = new TileMap(Configs.worldWidth / Configs.TILE_SIZE, Configs.worldHeight / Configs.TILE_SIZE);

		Engine.setClearColor(new Vector3f(.5f, 0, 1));

		world = new World(assets);
	}

	public void createCamera() {
		camera = new Camera(new Vector2f(0, 0), 0, 0, .5f);
		isCameraCreated = true;
	}

	public void cleanUp() {
		worldBuffer.cleanUp();
	}

}
