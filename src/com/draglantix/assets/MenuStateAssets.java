package com.draglantix.assets;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.engine.Engine;
import com.draglantix.font.Font;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.models.Texture;

public class MenuStateAssets {

	private Assets assets;
	public Texture menuTex;
	public Gui menu;
	public Font font, title;

	public MenuStateAssets(Assets assets) {
		this.assets = assets;
	}

	public void init() {
		menuTex = new Texture("res/textures/menu.png");
		menu = new Gui(menuTex.getTextureID(), new Vector2f(0, 0), new Vector2f(0, 0),
				new Vector2f(Configs.WIDTH / 2, Configs.HEIGHT / 2), 1, Configs.COLOUR_NULL, false, true, assets);
		font = new Font(assets.introAssets.fontTex.getTextureID(), "res/font/fnt.txt", "Press SPACE to begin",
				new Vector2f(-230, -230), 30, 7, 20, new Vector3f(1, 1, 1), false, assets);
		title = new Font(assets.introAssets.fontTex.getTextureID(), "res/font/fnt.txt", "Some Cool Title",
				new Vector2f(-180, 290), 30, 7, 20, new Vector3f(1, 1, 1), false, assets);
		Engine.addGuis(menu);
		Engine.addFonts(font, title);
	}

	public void cleanUp() {
		Engine.removeGuis(menu);
		Engine.removeFonts(font, title);
	}

}
