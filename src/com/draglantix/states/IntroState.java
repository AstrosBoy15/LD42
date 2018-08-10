package com.draglantix.states;

import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.window.Window;

public class IntroState extends GameState {

	private boolean increase = true;
	public static boolean changeState = false;
	private boolean hasInit = false;

	public IntroState(GameStateManager gsm, Assets assets) {
		super(gsm, assets);
	}

	public void init() {
		if(!hasInit) {
			assets.introAssets.init();
			hasInit = true;
		}
	}

	protected void tick() {

		if(changeState || Window.getInput().isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			gsm.setState(State.PLAY);
		}
		update();
		Engine.tickIntro();
	}

	protected void renderScene() {
		Engine.renderIntro();

	}

	protected void update() {
		if(increase) {
			assets.introAssets.introSplash.setAlpha(assets.introAssets.introSplash.getAlpha() + 0.01f);
		} else {
			assets.introAssets.introSplash.setAlpha(assets.introAssets.introSplash.getAlpha() - 0.01f);
		}

		if(assets.introAssets.introSplash.getAlpha() >= 1) {
			assets.introAssets.introSplash.setAlpha(1);
			increase = false;
		}

		if(assets.introAssets.introSplash.getAlpha() <= 0) {
			assets.introAssets.introSplash.setAlpha(0);
			if(!increase) {
				changeState = true;
			}
			increase = true;
		}
	}
	
	protected void cleanUp() {
		assets.introAssets.cleanUp();
	}

}
