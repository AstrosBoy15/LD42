package com.draglantix.assets;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.buildings.Building;
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

	public Texture squareTex, blankTex, mine1Tex, mine2Tex, mine3Tex,
						house1Tex, house2Tex, house3Tex,
						mill1Tex, mill2Tex, mill3Tex,
						road1Tex, turn1Tex, road2Tex, 
						turn2Tex, road3Tex, turn3Tex;

	public SpriteSheet tileSet;

	public TileMap tileMap;

	public TileData sandData;

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

		tileSet = new SpriteSheet("res/textures/world/buildings.png");
		
		squareTex = new Texture("res/textures/square.png");
		blankTex = new Texture("res/textures/blank.png");
		
		mine1Tex = new Texture(tileSet.crop(new Vector2f(32, 0), new Vector2f(Configs.TILE_SIZE)));
		mine2Tex = new Texture(tileSet.crop(new Vector2f(64, 0), new Vector2f(Configs.TILE_SIZE)));
		mine3Tex = new Texture(tileSet.crop(new Vector2f(96, 0), new Vector2f(Configs.TILE_SIZE)));
		
		house1Tex = new Texture(tileSet.crop(new Vector2f(0, 32), new Vector2f(Configs.TILE_SIZE)));
		house2Tex = new Texture(tileSet.crop(new Vector2f(32, 32), new Vector2f(Configs.TILE_SIZE)));
		house3Tex = new Texture(tileSet.crop(new Vector2f(64, 32), new Vector2f(Configs.TILE_SIZE)));
		
		mill1Tex = new Texture(tileSet.crop(new Vector2f(96, 32), new Vector2f(Configs.TILE_SIZE)));
		mill2Tex = new Texture(tileSet.crop(new Vector2f(0, 64), new Vector2f(Configs.TILE_SIZE)));
		mill3Tex = new Texture(tileSet.crop(new Vector2f(32, 64), new Vector2f(Configs.TILE_SIZE)));
		
		road1Tex = new Texture(tileSet.crop(new Vector2f(64, 64), new Vector2f(Configs.TILE_SIZE)));
		turn1Tex = new Texture(tileSet.crop(new Vector2f(96, 64), new Vector2f(Configs.TILE_SIZE)));
		road2Tex = new Texture(tileSet.crop(new Vector2f(0, 96), new Vector2f(Configs.TILE_SIZE)));
		turn2Tex = new Texture(tileSet.crop(new Vector2f(32, 96), new Vector2f(Configs.TILE_SIZE)));
		road3Tex = new Texture(tileSet.crop(new Vector2f(64, 96), new Vector2f(Configs.TILE_SIZE)));
		turn3Tex = new Texture(tileSet.crop(new Vector2f(96, 96), new Vector2f(Configs.TILE_SIZE)));


		sandData = new TileData(new Texture(tileSet.crop(new Vector2f(0, 0), new Vector2f(Configs.TILE_SIZE))), 0,
				false);

		tileMap = new TileMap(Configs.worldWidth / Configs.TILE_SIZE, Configs.worldHeight / Configs.TILE_SIZE);

		Engine.setClearColor(new Vector3f(.5f, 0, 1));

		world = new World(assets);
	}
	
	public Texture currentBuilding(int type, int stage) {
		switch(type) {
			case Building.MINE:
				switch(stage) {
					case 1:
						return mine1Tex; 
					case 2:
						return mine2Tex;
					case 3:
						return mine3Tex;
					default:
						return null;
				}
			case Building.HOUSE:
				switch(stage) {
				case 1:
					return house1Tex; 
				case 2:
					return house2Tex;
				case 3:
					return house3Tex;
				default:
					return null;
				}
			case Building.MILL:
				switch(stage) {
				case 1:
					return mill1Tex; 
				case 2:
					return mill2Tex;
				case 3:
					return mill3Tex;
				default:
					return null;
				}
			case Building.ROAD:
				switch(stage) {
				case 1:
					return road1Tex; 
				case 2:
					return road2Tex;
				case 3:
					return road3Tex;
				default:
					return null;
				}
			default:
				break;
		}
		return null;
		
	}
	

	public void createCamera() {
		camera = new Camera(new Vector2f(0, 0), 0, 0, .5f);
		isCameraCreated = true;
	}

	public void cleanUp() {
		worldBuffer.cleanUp();
	}

}
