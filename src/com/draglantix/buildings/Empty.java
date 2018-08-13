package com.draglantix.buildings;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.assets.Assets;
import com.draglantix.buttonComponents.ButtonCreate;
import com.draglantix.collsion.Polygon;
import com.draglantix.engine.Engine;
import com.draglantix.font.Font;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;

public class Empty extends Building {

	private static final int NUM_ANIMATIONS = 0;
	protected final static int NUM_OF_GUIS = 2;

	private Gui[] guis2;
	private Font[] fonts2;

	public boolean isCreateSelected = false;

	private boolean isSelected = false;

	private boolean hasInit = false;

	public Empty(int texture, Vector2f position, Vector2f rotation, Vector2f scale, Assets assets) {
		super(texture, NUM_ANIMATIONS, position, rotation, scale, new Polygon(
				new float[] { position.x + scale.x * Configs.worldScale, position.x - scale.x * Configs.worldScale,
						position.x - scale.x * Configs.worldScale },
				new float[] { position.y - scale.y * Configs.worldScale, position.y - scale.y * Configs.worldScale,
						position.y + scale.y * Configs.worldScale },
				false), "Empty", EMPTY, assets);
		
		guis = new Gui[NUM_OF_GUIS];
		fonts = new Font[NUM_OF_GUIS];
		createEmptyGUI();
		guis2 = new Gui[Building.TYPES_OF_BUILDINGS];
		fonts2 = new Font[Building.TYPES_OF_BUILDINGS];
		createMultipleGUI();
	}

	@Override
	public void tick() {
		if (isSelected) {
			for (int i = 0; i < guis.length; i++) {
				guis[i].tick();
				if (guis[i].getCurrentColour() != Configs.COLOUR_NULL) {
					fonts[i].setColor(guis[i].getCurrentColour());
				} else {
					fonts[i].setColor(new Vector3f(1, 1, 1));
				}
			}

		}
		if (isCreateSelected) {
			if (!hasInit) {
				Engine.addGuis(guis2);
				Engine.addFonts(fonts2);
			}
			for (int i = 0; i < guis2.length; i++) {
				guis2[i].tick();
				if (guis2[i].getCurrentColour() != Configs.COLOUR_NULL) {
					fonts2[i].setColor(guis2[i].getCurrentColour());
				} else {
					fonts2[i].setColor(new Vector3f(1, 1, 1));
				}
			}
			hasInit = true;
		}
	}

	public void update() {
		Engine.addGuis(guis);
		Engine.addFonts(fonts);
		isSelected = true;
	}

	public void reset() {
		Engine.removeGuis(guis);
		Engine.removeFonts(fonts);
		Engine.removeGuis(guis2);
		Engine.removeFonts(fonts2);
		hasInit = false;
		isSelected = false;
		isCreateSelected = false;
	}

	public void createMultipleGUI() {
		for (int i = 0; i < Building.TYPES_OF_BUILDINGS; i++) {
			Gui gui = new Gui(assets.playAssets.squareTex.getTextureID(),
					new Vector2f(guis[0].getPosition().x + guiScale.x * 2,
							guis[0].getPosition().y - guiScale.y * (2 * i)),
					new Vector2f(0, 0), new Vector2f(guiScale.x, guiScale.y), 0.75f, new Vector3f(.15f, .15f, .15f),
					true, false, assets);
			gui.setComponent(new ButtonCreate(i, gui, this));
			guis2[i] = gui;

			String msg;

			switch (i) {
			case Building.MINE:
				msg = "Mine";
				break;
			case Building.HOUSE:
				msg = "House";
				break;
			case Building.MILL:
				msg = "Mill";
				break;
			case Building.ROAD:
				msg = "Road";
				break;
			default:
				msg = "Create";
				break;
			}

			Font font = new Font(gui.getAssets().introAssets.fontTex.getTextureID(), "res/font/fnt.txt", msg,
					new Vector2f(guis2[i].getPosition().x - gui.getScale().x + 10, guis2[i].getPosition().y + 15), 15,
					7, 20, new Vector3f(1, 1, 1), true, gui.getAssets());
			fonts2[i] = font;
		}
	}
}
