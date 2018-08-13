package com.draglantix.states;

import com.draglantix.assets.Assets;
import com.draglantix.window.Window;
import com.draglantix.window.WindowHandler;

public class GameStateManager {

	private State currentState;

	private GameState currentStateClass;

	private IntroState introState;
	private MenuState menuState;
	private PlayState playState;
	private PauseState pauseState;

	public Assets assets;

	private Window window;

	public GameStateManager(State startState, Window window, Assets assets) {
		this.assets = assets;
		this.window = window;

		introState = new IntroState(this, assets);
		menuState = new MenuState(this, assets);
		playState = new PlayState(this, assets);
		pauseState = new PauseState(this, assets);

		currentState = startState;
		setState(currentState);
	}

	public void update() {
		WindowHandler.handle(window);
		currentStateClass.tick();
		currentStateClass.render();
	}

	public void setState(State state) {
		currentState = state;

		switch(currentState) {
		case INTRO:
			if(currentStateClass != null)
				currentStateClass.cleanUp();
			currentStateClass = introState;
			introState.init();
			break;
		case PLAY:
			currentStateClass.cleanUp();
			currentStateClass = playState;
			playState.init();
			break;
		case PAUSE:
			currentStateClass.cleanUp();
			currentStateClass = pauseState;
			pauseState.init();
			break;
		case MENU:
			currentStateClass.cleanUp();
			currentStateClass = menuState;
			menuState.init();
			break;
		default:
			currentStateClass.cleanUp();
			currentStateClass = null;
			break;
		}
	}

	public State getState() {
		return currentState;
	}

	public Window getWindow() {
		return window;
	}
}
