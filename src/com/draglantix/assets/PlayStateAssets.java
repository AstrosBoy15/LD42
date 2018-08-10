package com.draglantix.assets;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.buttonComponents.ButtonTest;
import com.draglantix.engine.Camera;
import com.draglantix.engine.Engine;
import com.draglantix.entities.Player;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.maps.Map;
import com.draglantix.models.Animation;
import com.draglantix.models.SpriteSheet;
import com.draglantix.models.Texture;
import com.draglantix.tiles.TileData;
import com.draglantix.tiles.TileMap;
import com.draglantix.util.FBO;
import com.draglantix.window.Window;
import com.draglantix.world.World;

public class PlayStateAssets {

	public Player player;

	public Camera camera;

	private Assets assets;

	public Texture triangleTex, squareTex;

	public Texture fgTex;
	public Texture mapTex;
	public Texture bg1Tex;
	public Texture bg2Tex;
	public Texture skyTex;

	public SpriteSheet tileSet;

	public Animation playerWalk;
	public Animation playerIdle;

	public TileMap tileMap;

	public Map sky, bg1, bg2, map, fg;

	public TileData grassData, stoneData;

	public boolean isCameraCreated = false;

	public FBO worldBuffer;

	public World world;

	public Gui button;

	public PlayStateAssets(Assets assets) {

		this.assets = assets;

		worldBuffer = new FBO(Window.getWidth(), Window.getHeight());

	}

	public void init() {

		fgTex = new Texture("res/textures/world/fg.png");
		mapTex = new Texture("res/textures/world/map.png");
		bg1Tex = new Texture("res/textures/world/bg1.png");
		bg2Tex = new Texture("res/textures/world/bg2.png");
		skyTex = new Texture("res/textures/world/sky.png");

		Configs.worldScale = 2;
//		Configs.worldWidth = mapTex.getWidth() * Configs.worldScale * 2;
//		Configs.worldHeight = mapTex.getHeight() * Configs.worldScale * 2;

		Configs.worldWidth = 384 * Configs.worldScale * 8;
		Configs.worldHeight = 384 * Configs.worldScale * 8;

		tileSet = new SpriteSheet("res/textures/world/tileSet.png");

		triangleTex = new Texture("res/textures/triangle.png");
		squareTex = new Texture("res/textures/square.png");

		grassData = new TileData(new Texture(tileSet.crop(new Vector2f(0, 0), new Vector2f(Configs.TILE_SIZE))), 0,
				false);
		stoneData = new TileData(
				new Texture(tileSet.crop(new Vector2f(Configs.TILE_SIZE, 0), new Vector2f(Configs.TILE_SIZE))), 1,
				false);

		tileMap = new TileMap(Configs.worldWidth / Configs.TILE_SIZE, Configs.worldHeight / Configs.TILE_SIZE);

		Engine.setClearColor(new Vector3f(.5f, 0, 1));

		sky = new Map(assets.loader.loadTexture(skyTex), new Vector2f(Window.getWidth(), Window.getHeight()), 1f, false,
				assets);
		bg2 = new Map(assets.loader.loadTexture(bg2Tex),
				new Vector2f(Configs.worldWidth / 1.7f, Configs.worldHeight / 2), -0.04f, false, assets);
		bg1 = new Map(assets.loader.loadTexture(bg1Tex),
				new Vector2f(Configs.worldWidth / 1.6f, Configs.worldHeight / 1.6f), -0.1f, false, assets);
		map = new Map(assets.loader.loadTexture(mapTex), new Vector2f(Configs.worldWidth / 2, Configs.worldHeight / 2),
				0f, false, assets);
		fg = new Map(assets.loader.loadTexture(fgTex), new Vector2f(Configs.worldWidth, Configs.worldHeight), -1f, true,
				assets);

		Engine.addMaps(sky, bg2, bg1, map, fg);

		playerWalk = new Animation(4, 5, "res/textures/player/walk");
		playerIdle = new Animation(2, 2, "res/textures/player/idle");

		world = new World(assets);

		button = new Gui(assets.loader.loadTexture(squareTex),
				new Vector2f(-Configs.WIDTH / 2 + 100, Configs.HEIGHT / 2 - 50), new Vector2f(0, 0),
				new Vector2f(100, 50), 1, Configs.COLOUR_NULL, true, assets);
		button.setComponent(new ButtonTest());
		// Engine.addGuis(button);
	}

	public void createCamera() {
		camera = new Camera(new Vector2f(0, 0), 0, 0, .5f);
		isCameraCreated = true;
	}
	
	public void cleanUp() {
		worldBuffer.cleanUp();
	}

}
