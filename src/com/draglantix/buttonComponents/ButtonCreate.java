package com.draglantix.buttonComponents;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.buildings.Building;
import com.draglantix.buildings.FarmLand;
import com.draglantix.buildings.House;
import com.draglantix.engine.Engine;
import com.draglantix.font.Font;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
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
		switch (num) {
		case Building.CEMETERY:
//			Engine.addBuildings(new Cemetery(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
//			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
//					gui.getAssets().playAssets.squareTex.getHeight()),
//			gui.getAssets()));
			break;
		case Building.CHURCH:
//			Engine.addBuildings(new House(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
//			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
//					gui.getAssets().playAssets.squareTex.getHeight()),
//			gui.getAssets()));
			break;
		case Building.FACTORY:
//			Engine.addBuildings(new House(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
//			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
//					gui.getAssets().playAssets.squareTex.getHeight()),
//			gui.getAssets()));
			break;
		case Building.FARMLAND:
			Engine.addBuildings(new FarmLand(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
					gui.getAssets().playAssets.squareTex.getHeight()),
			gui.getAssets()));
			Engine.removeBuildings(building);
			building.reset();
			break;
		case Building.HOUSE:
			Engine.addBuildings(new House(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
					gui.getAssets().playAssets.squareTex.getHeight()),
			gui.getAssets()));
			Engine.removeBuildings(building);
			building.reset();
			break;
		case Building.MILL:
//			Engine.addBuildings(new House(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
//			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
//					gui.getAssets().playAssets.squareTex.getHeight()),
//			gui.getAssets()));
			break;
		case Building.RAILROAD:
//			Engine.addBuildings(new House(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
//			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
//					gui.getAssets().playAssets.squareTex.getHeight()),
//			gui.getAssets()));
			break;
		case Building.SCHOOL:
//			Engine.addBuildings(new House(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
//			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
//					gui.getAssets().playAssets.squareTex.getHeight()),
//			gui.getAssets()));
			break;
		case Building.TAVERN:
//			Engine.addBuildings(new House(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
//			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
//					gui.getAssets().playAssets.squareTex.getHeight()),
//			gui.getAssets()));
			break;
		case Building.TENEMENT:
//			Engine.addBuildings(new House(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
//			new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
//					gui.getAssets().playAssets.squareTex.getHeight()),
//			gui.getAssets()));
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
