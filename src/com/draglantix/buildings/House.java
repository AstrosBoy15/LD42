package com.draglantix.buildings;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.assets.Assets;
import com.draglantix.collsion.Polygon;
import com.draglantix.engine.Engine;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;

public class House extends Building {

	private static final int NUM_ANIMATIONS = 0;

	public House(int texture, Vector2f position, Vector2f rotation, Vector2f scale, Assets assets) {
		super(texture, NUM_ANIMATIONS, position, rotation, scale, new Polygon(
				new float[] { position.x + scale.x * Configs.worldScale, position.x - scale.x * Configs.worldScale,
						position.x - scale.x * Configs.worldScale },
				new float[] { position.y - scale.y * Configs.worldScale, position.y - scale.y * Configs.worldScale,
						position.y + scale.y * Configs.worldScale },
				false), assets);
		gui = new Gui(assets.playAssets.squareTex.getTextureID(),
				new Vector2f(position.x + guiScale.x / 2 * Configs.worldScale,
						position.y - guiScale.y / 2 * Configs.worldScale),
				new Vector2f(0, 0), guiScale, 0.75f, new Vector3f(.15f, .15f, .15f), false, true, assets);
	}

	public void update() {
		Engine.addGuis(gui);
	}

	public void removeGui() {
		Engine.removeGuis(gui);
	}
}
