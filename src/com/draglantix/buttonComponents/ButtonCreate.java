package com.draglantix.buttonComponents;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.buildings.Building;
import com.draglantix.buildings.House;
import com.draglantix.buildings.Mill;
import com.draglantix.buildings.Mine;
import com.draglantix.buildings.Road;
import com.draglantix.engine.Engine;
import com.draglantix.font.Font;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.models.Texture;
import com.draglantix.window.Window;

public class ButtonCreate extends ButtonComponent {

	private int num;
	private Gui gui;
	private Building building;
	private Vector3f color;
	protected Gui[] guis;
	protected Font[] fonts;
	protected Vector2f guiScale = new Vector2f(75, 20);

	public static final int BLANK = 0;
	public static final int UPGRADE = 1;
	public static final int COLLECT = 2;
	public static final int DESTROY = 3;
	public static final int BUILD = 5;

	public ButtonCreate(int num, Gui gui, Building building) {
		this.num = num;
		this.gui = gui;
		this.building = building;
	}

	public void tick() {
		if (checkCollision() && num != 0) {
			color = new Vector3f(0, 150f, 200f);
		} else {
			color = Configs.COLOUR_NULL;
		}
	}

	public void update() {
		
		Texture tex;
		
		switch (num) {
		
		case Building.MINE:
			tex = gui.getAssets().playAssets.mine1Tex;
			Engine.addBuildings(new Mine(tex.getTextureID(), building.getPosition(),
				new Vector2f(0, 0), new Vector2f(tex.getWidth(),tex.getHeight()),
			gui.getAssets()));
			Engine.removeBuildings(building);
			building.reset();
			break;
		case Building.HOUSE:
			tex = gui.getAssets().playAssets.house1Tex;
			Engine.addBuildings(new House(tex.getTextureID(), building.getPosition(),
				new Vector2f(0, 0), new Vector2f(tex.getWidth(),tex.getHeight()),
			gui.getAssets()));
			Engine.removeBuildings(building);
			building.reset();
			break;
		case Building.MILL:
			tex = gui.getAssets().playAssets.mill1Tex;
			Engine.addBuildings(new Mill(tex.getTextureID(), building.getPosition(),
				new Vector2f(0, 0), new Vector2f(tex.getWidth(),tex.getHeight()),
			gui.getAssets()));
			Engine.removeBuildings(building);
			building.reset();
			break;
		case Building.ROAD:	
			tex = gui.getAssets().playAssets.road1Tex;
			Engine.addBuildings(new Road(tex.getTextureID(), building.getPosition(),
			new Vector2f(0, 0), new Vector2f(tex.getWidth(),tex.getHeight()),
			gui.getAssets()));
			Engine.removeBuildings(building);
			building.reset();
			break;
		default:
			break;
		}
	}

	public Vector3f getColour() {
		return color;
	}

	private boolean checkCollision() {
		if (Window.getInput().getMousePos().x - Configs.WIDTH / 2 < gui.getBounding().getMax().x
				- gui.getAssets().playAssets.camera.getPosition().x
				&& Window.getInput().getMousePos().x - Configs.WIDTH / 2 > gui.getBounding().getMin().x
						- gui.getAssets().playAssets.camera.getPosition().x
				&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 < gui.getBounding().getMax().y
						+ gui.getAssets().playAssets.camera.getPosition().y
				&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 > gui.getBounding().getMin().y
						+ gui.getAssets().playAssets.camera.getPosition().y) {
			return true;
		}
		return false;
	}
	
	public void addGuis() {
		Engine.addGuis(guis);
	}

	public void addFonts() {
		Engine.addFonts(fonts);
	}

	public void removeGuis() {
		Engine.addGuis(guis);
	}

	public void removeFonts() {
		Engine.removeFonts(fonts);
	}

}
