package com.draglantix.assets;

import com.draglantix.guis.Gui;
import com.draglantix.postProcessing.PostProcessing;
import com.draglantix.util.FBO;
import com.draglantix.window.Window;

public class PauseStateAssets {

	public FBO buffer, buffer2;

	public PostProcessing processing;

	public Gui blur;

	public Assets assets;

	public PauseStateAssets(Assets assets) {
		this.assets = assets;
	}

	public void init() {
		buffer = new FBO(Window.getWidth(), Window.getHeight());
		buffer2 = new FBO(Window.getWidth(), Window.getHeight());
		processing = new PostProcessing(assets.loader);
	}

	public void cleanUp() {
		buffer.cleanUp();
		buffer2.cleanUp();
	}

}
