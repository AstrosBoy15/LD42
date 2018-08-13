package com.draglantix.buildings;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.assets.Assets;
import com.draglantix.buttonComponents.ButtonBuilding;
import com.draglantix.collsion.Polygon;
import com.draglantix.font.Font;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.util.ObjectData;
import com.draglantix.util.Timer;
import com.draglantix.window.Window;

public abstract class Building extends ObjectData {

	protected Timer timer;
	protected Gui[] guis;
	protected Font[] fonts;
	protected String name;
	protected Vector2f guiScale = new Vector2f(75, 20);
	protected int type;
	
	public static final int TYPES_OF_BUILDINGS = 11;
	
	public static final int EMPTY = 0;
	public static final int HOUSE = 1;
	public static final int CHURCH = 2;
	public static final int TAVERN = 3;
	public static final int MILL = 4;
	public static final int CEMETERY = 5;
	public static final int SCHOOL = 6;
	public static final int TENEMENT = 7;
	public static final int FACTORY = 8;
	public static final int RAILROAD = 9;
	public static final int FARMLAND = 10;

	public Building(int texture, int numAnimations, Vector2f position, Vector2f rotation, Vector2f scale,
			Polygon bounding, String name, int type, Assets assets) {
		super(texture, numAnimations, position, rotation, scale.mul(Configs.worldScale), bounding, assets);
		this.name = name;
		this.type = type;
	}

	public void tick() {
		if (animations.size() != 0) {
			setTexture(getAnimation(currentAnimation).getTexture().getTextureID());
		}
	}

	public void createMultiGUI() {
		for (int i = 0; i < guis.length; i++) {
			Gui gui = new Gui(assets.playAssets.squareTex.getTextureID(),
					new Vector2f(position.x + guiScale.x,
							position.y - guiScale.y * (2 * i + 1)),
					new Vector2f(0, 0), new Vector2f(guiScale.x, guiScale.y), 0.75f,
					new Vector3f(.15f, .15f, .15f), true, false, assets);
			gui.setComponent(new ButtonBuilding(i, gui, this));
			guis[i] = gui;

			String msg;

			switch (i) {
			case 0:
				msg = name;
				break;
			case 1:
				msg = "Upgrade";
				break;
			case 2:
				msg = "Collect";
				break;
			case 3:
				msg = "Destroy";
				break;
			default:
				msg = "Error";
				break;
			}

			Font font = new Font(assets.introAssets.fontTex.getTextureID(), "res/font/fnt.txt", msg,
					new Vector2f(guis[i].getPosition().x - gui.getScale().x + 10,
							guis[i].getPosition().y + 15),
					15, 7, 20, new Vector3f(1, 1, 1), true, assets);
			fonts[i] = font;
		}
	}
	
	public void createEmptyGUI() {
		for (int i = 0; i < guis.length; i++) {
			Gui gui = new Gui(assets.playAssets.squareTex.getTextureID(),
					new Vector2f(position.x + guiScale.x,
							position.y - guiScale.y * (2 * i + 1)),
					new Vector2f(0, 0), new Vector2f(guiScale.x, guiScale.y), 0.75f,
					new Vector3f(.15f, .15f, .15f), true, false, assets);
			guis[i] = gui;

			String msg;

			switch (i) {
			case 0:
				msg = name;
				gui.setComponent(new ButtonBuilding(i, gui, this));
				break;
			case 1:
				msg = "CREATE";
				gui.setComponent(new ButtonBuilding(5, gui, this));
				break;
			case 2:
				msg = "IDK";
				gui.setComponent(new ButtonBuilding(i, gui, this));
				break;
			case 3:
				msg = "Collect";
				gui.setComponent(new ButtonBuilding(i, gui, this));
				break;
			case 4:
				msg = "Destroy";
				gui.setComponent(new ButtonBuilding(i, gui, this));
				break;
			default:
				msg = "Error";
				gui.setComponent(new ButtonBuilding(i, gui, this));
				break;
			}

			Font font = new Font(assets.introAssets.fontTex.getTextureID(), "res/font/fnt.txt", msg,
					new Vector2f(guis[i].getPosition().x - gui.getScale().x + 10,
							guis[i].getPosition().y + 15),
					15, 7, 20, new Vector3f(1, 1, 1), true, assets);
			fonts[i] = font;
		}
	}
	
	

	public abstract void update();

	public abstract void reset();

	public boolean checkForCollisions() {
		if (Window.getInput().getMousePos().x - Configs.WIDTH / 2 < bounding.getMax().x
				- assets.playAssets.camera.getPosition().x
				&& Window.getInput().getMousePos().x - Configs.WIDTH / 2 > bounding.getMin().x
						- assets.playAssets.camera.getPosition().x
				&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 < -bounding.getMin().y
						+ assets.playAssets.camera.getPosition().y
				&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 > -bounding.getMax().y
						+ assets.playAssets.camera.getPosition().y) {
			return true;
		}
		return false;
	}
	
	public int getType() {
		return type;
	}

}
