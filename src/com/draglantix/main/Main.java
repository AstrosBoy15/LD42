package com.draglantix.main;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.engine.PhysicsEngine;
import com.draglantix.states.GameStateManager;
import com.draglantix.states.State;
import com.draglantix.window.Window;

public class Main {

	private Window window;
	private GameStateManager gsm;

	private Assets assets;

	private FPSHandler fpsHandler;

	public Main() {
		init();
		run();
	}

	private void init() {
		window = new Window(Configs.WIDTH, Configs.HEIGHT, Configs.TITLE + Configs.VERSION);
		assets = new Assets();
		PhysicsEngine.init();
		Engine.init(assets);
		gsm = new GameStateManager(State.INTRO, window, assets);
		fpsHandler = new FPSHandler(Configs.PRINT_FPS);

	}

	private void run() {
		while(!window.shouldClose()) {
			fpsHandler.sync(Configs.FPS_CAP);
			gsm.update();

		}
		CleanUpMaster.cleanUp();
	}

	public static void main(String[] args) {
		new Main();
	}
}
