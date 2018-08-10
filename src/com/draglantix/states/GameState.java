package com.draglantix.states;

import org.lwjgl.opengl.GL11;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.window.Window;

public abstract class GameState {

	protected GameStateManager gsm;

	protected Assets assets;

	public GameState(GameStateManager gsm, Assets assets) {
		this.assets = assets;
		this.gsm = gsm;
	}

	protected abstract void tick();

	protected void render() {
		prepare();
		renderScene();
		finish(gsm.getWindow());
	}

	protected void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(Engine.getClearColor().x, Engine.getClearColor().y, Engine.getClearColor().z, 1);
	}

	protected abstract void renderScene();

	protected void finish(Window window) {
		window.swapBuffers();
	}
	
	protected abstract void cleanUp();

}
