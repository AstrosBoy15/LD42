package com.draglantix.states;

import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.window.Window;

public class PlayState extends GameState {

	private boolean hasInit = false;

	public PlayState(GameStateManager gsm, Assets assets) {
		super(gsm, assets);
	}

	public void init() {
		if(!hasInit) {
			assets.playAssets.init();
			assets.playAssets.world.init();
			hasInit = true;
		}
	}

	protected void tick() {
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_P)) {
			gsm.setState(State.PAUSE);
		}
		assets.playAssets.world.tick();
		Engine.tickPlay();
	}

	protected void renderScene() {
		assets.playAssets.worldBuffer.bindFrameBuffer();
		Engine.renderPlay();
		assets.playAssets.worldBuffer.unbindFrameBuffer();
		assets.playAssets.worldBuffer.resolveToScreen();
	}

	protected void cleanUp() {
		assets.playAssets.cleanUp();
	}

}
