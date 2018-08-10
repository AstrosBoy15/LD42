package com.draglantix.states;

import com.draglantix.assets.Assets;

public class MenuState extends GameState {

	public MenuState(GameStateManager gsm, Assets assets) {
		super(gsm, assets);
	}
	
	protected void init() {
		assets.menuAssets.init();
	}

	protected void tick() {

	}

	protected void renderScene() {

	}
	
	protected void cleanUp() {
		assets.menuAssets.cleanUp();
	}

}
