package com.draglantix.buildings;

import org.joml.Vector2f;

import com.draglantix.assets.Assets;
import com.draglantix.collsion.Polygon;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.util.ObjectData;
import com.draglantix.util.Timer;
import com.draglantix.window.Window;

public abstract class Building extends ObjectData {

	protected Timer timer;
	protected Gui gui;
	protected Vector2f guiScale = new Vector2f(75, 100);

	public Building(int texture, int numAnimations, Vector2f position, Vector2f rotation, Vector2f scale,
			Polygon bounding, Assets assets) {
		super(texture, numAnimations, position, rotation, scale.mul(Configs.worldScale), bounding, assets);
	}

	public void tick() {
		if (animations.size() != 0) {
			setTexture(getAnimation(currentAnimation).getTexture().getTextureID());
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
	
	public Gui getGui() {
		return gui;
	}

}
