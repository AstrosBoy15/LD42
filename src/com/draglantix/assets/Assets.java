package com.draglantix.assets;

import com.draglantix.models.Loader;

public class Assets {

	public Loader loader;

	public IntroStateAssets introAssets;
	public MenuStateAssets menuAssets;
	public PlayStateAssets playAssets;
	public PauseStateAssets pauseAssets;

	public Assets() {

		System.out.println("Loading...");

		loader = new Loader();

		introAssets = new IntroStateAssets(this);
		menuAssets = new MenuStateAssets(this);
		playAssets = new PlayStateAssets(this);
		pauseAssets = new PauseStateAssets(this);
	}
}