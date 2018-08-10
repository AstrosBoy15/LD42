package com.draglantix.states;

import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.window.Window;

public class PauseState extends GameState {

	public PauseState(GameStateManager gsm, Assets assets) {
		super(gsm, assets);
	}

	public void init() {
		assets.pauseAssets.init();
		assets.pauseAssets.buffer.bindFrameBuffer();
		Engine.renderPause();
		assets.pauseAssets.buffer.unbindFrameBuffer();
		assets.pauseAssets.processing.doBlurHorizontal(assets.pauseAssets.buffer, assets.pauseAssets.buffer2);
		assets.pauseAssets.processing.doBlurVertical(assets.pauseAssets.buffer2, assets.pauseAssets.buffer);
	}

	protected void tick() {
		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_P)) {
			gsm.setState(State.PLAY);
		}
		Engine.tickPause();
	}

	protected void renderScene() {
		assets.pauseAssets.buffer.resolveToScreen();
	}

	protected void cleanUp() {
		assets.pauseAssets.cleanUp();
	}

}
