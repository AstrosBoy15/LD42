package com.draglantix.buildings;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.assets.Assets;
import com.draglantix.collsion.Polygon;
import com.draglantix.engine.Engine;
import com.draglantix.font.Font;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;

public class Empty extends Building {

	private static final int NUM_ANIMATIONS = 0;
	protected final static int NUM_OF_GUIS = 2;

	private boolean isSelected = false;

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
	}

	public void update() {
		Engine.addGuis(guis);
		Engine.addFonts(fonts);
		isSelected = true;
	}

	public void reset() {
		Engine.removeGuis(guis);
		Engine.removeFonts(fonts);
		isSelected = false;
	}
}
