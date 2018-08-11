package com.draglantix.buildings;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.assets.Assets;
import com.draglantix.buttonComponents.ButtonTest;
import com.draglantix.collsion.Polygon;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.util.ObjectData;
import com.draglantix.util.Timer;
import com.draglantix.window.Window;

public abstract class Building extends ObjectData {

	protected Timer timer;
	protected final static int NUM_OF_GUIS = 5;
	protected Gui[] guis;
	protected Vector2f guiScale = new Vector2f(75, 100);

	public Building(int texture, int numAnimations, Vector2f position, Vector2f rotation, Vector2f scale,
			Polygon bounding, Assets assets) {
		super(texture, numAnimations, position, rotation, scale.mul(Configs.worldScale), bounding, assets);
	guis = new Gui[NUM_OF_GUIS];
	}

	public void tick() {
		if (animations.size() != 0) {
			setTexture(getAnimation(currentAnimation).getTexture().getTextureID());
		}
	}
	
	public void createMultiGUI() {
		for(int i = 0; i < guis.length; i++) {
			Gui gui = new Gui(assets.playAssets.squareTex.getTextureID(),
					new Vector2f(position.x + guiScale.x / 2 * Configs.worldScale,
							position.y - guiScale.y / 2 * Configs.worldScale / guis.length * (2*i + 1)),
					new Vector2f(0, 0), new Vector2f(guiScale.x, guiScale.y / guis.length), 0.75f, new Vector3f(.15f*i, .15f*i/2, .15f),
					true, true, assets);
			gui.setComponent(new ButtonTest(i));
			guis[i] = gui;
		}
	}

	public abstract void update();
	public abstract void removeGui();

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

}
