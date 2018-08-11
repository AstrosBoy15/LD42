package com.draglantix.guis;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.assets.Assets;
import com.draglantix.buttonComponents.ButtonComponent;
import com.draglantix.collsion.Polygon;
import com.draglantix.main.Configs;
import com.draglantix.util.ObjectData;
import com.draglantix.window.Window;

public class Gui extends ObjectData {

	private float alpha;

	private Vector3f colour;

	private ButtonComponent component;
	private boolean isStatic;

	private List<Polygon> boundingList;

	public Gui(int texture, Vector2f position, Vector2f rotation, Vector2f scale, float alpha, Vector3f colour,
			boolean bounding, boolean isStatic, Assets assets) {
		super(texture, 0, position, rotation, scale, bounding ? new Polygon(
				new float[] { position.x + scale.x, position.x + scale.x, position.x - scale.x, position.x - scale.x },
				new float[] { -position.y + scale.y, -position.y - scale.y, -position.y - scale.y,
						-position.y + scale.y },
				false) : null, assets);
		this.colour = colour;
		this.alpha = alpha;
		this.isStatic = isStatic;
		boundingList = new ArrayList<Polygon>();
		boundingList.add(super.bounding);
	}

	public void tick() {

	}

	public boolean checkForCollision() {
		if (isStatic) {
			if (Window.getInput().getMousePos().x - Configs.WIDTH / 2 < bounding.getMax().x
					&& Window.getInput().getMousePos().x - Configs.WIDTH / 2 > bounding.getMin().x
					&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 < bounding.getMax().y
					&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 > bounding.getMin().y) {
				return true;
			}
		} else {
			if (Window.getInput().getMousePos().x - Configs.WIDTH / 2 < bounding.getMax().x
					- assets.playAssets.camera.getPosition().x
					&& Window.getInput().getMousePos().x - Configs.WIDTH / 2 > bounding.getMin().x
							- assets.playAssets.camera.getPosition().x
					&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 < bounding.getMax().y
							+ assets.playAssets.camera.getPosition().y
					&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 > bounding.getMin().y
							+ assets.playAssets.camera.getPosition().y) {
				return true;
			}
		}
		return false;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public void setComponent(ButtonComponent component) {
		this.component = component;
	}

	public ButtonComponent getComponenet() {
		return component;
	}

	public void setColour(Vector3f colour) {
		this.colour = colour;
	}

	public Vector3f getColour() {
		return colour;
	}

	public boolean isStatic() {
		return isStatic;
	}

}
