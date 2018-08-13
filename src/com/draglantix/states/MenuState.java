package com.draglantix.states;

import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.window.Window;

public class MenuState extends GameState {

	public MenuState(GameStateManager gsm, Assets assets) {
		super(gsm, assets);
	}
	
	protected void init() {
		assets.menuAssets.init();
	}

	protected void tick() {
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			gsm.setState(State.PLAY);
		}
		Engine.tickMenu();
	}

	protected void renderScene() {
		Engine.renderMenu();
	}
	
	protected void cleanUp() {
		assets.menuAssets.cleanUp();
	}

}
