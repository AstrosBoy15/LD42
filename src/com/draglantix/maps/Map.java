package com.draglantix.maps;

import org.joml.Vector2f;

import com.draglantix.assets.Assets;
import com.draglantix.util.ObjectData;

public class Map extends ObjectData {

	private float paralax;

	private boolean foreground;

	public Map(int texture, Vector2f scale, float paralax, boolean foreground, Assets assets) {
		super(texture, 0, new Vector2f(0, 0), new Vector2f(0, 0), scale, null, assets);
		this.paralax = paralax;
		this.foreground = foreground;
	}

	@Override
	public void tick() {
		position = new Vector2f(assets.playAssets.camera.getPosition().x * paralax,
				assets.playAssets.camera.getPosition().y * paralax);
	}

	public boolean isForeground() {
		return foreground;
	}

}
