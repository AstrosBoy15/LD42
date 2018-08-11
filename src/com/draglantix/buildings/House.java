package com.draglantix.buildings;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.assets.Assets;
import com.draglantix.buttonComponents.ButtonTest;
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
		createMultiGUI();
	}

	public void update() {
		Engine.addGuis(guis);
	}

	public void removeGui() {
		Engine.removeGuis(guis);
	}
}
