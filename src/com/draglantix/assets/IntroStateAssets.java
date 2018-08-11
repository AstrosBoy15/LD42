package com.draglantix.assets;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.engine.Engine;
import com.draglantix.font.Font;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.models.Texture;

public class IntroStateAssets {

	public Texture draglantixTex, fontTex;

	public Assets assets;

	public Gui introSplash;

	public Font msg;

	public IntroStateAssets(Assets assets) {
		this.assets = assets;
	}

	public void init() {
		draglantixTex = new Texture("res/textures/draglantix.png");
		fontTex = new Texture("res/font/glyphSheet.png");
		introSplash = new Gui(assets.loader.loadTexture(draglantixTex), new Vector2f(0, 0), new Vector2f(0, 0),
				new Vector2f(Configs.WIDTH / 2 * .85f, Configs.HEIGHT / 2 * .1f), 0, new Vector3f(1, 1, 1), false,
				false, assets);
		Engine.addGuis(introSplash);
	}

	public void cleanUp() {
		Engine.removeGuis(introSplash);
	}

}
