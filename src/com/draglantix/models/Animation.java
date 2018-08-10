package com.draglantix.models;

import com.draglantix.util.Timer;

public class Animation {
	private Texture[] frames;
	private int pointer;

	private double elapsedTime;
	private double fps;

	private Timer timer;

	public Animation(int amount, int fps, String filename) {
		this.pointer = 0;
		this.elapsedTime = 0;
		this.fps = 1.0 / (double) fps;
		timer = new Timer();
		this.frames = new Texture[amount];
		for(int i = 0; i < amount; i++) {
			this.frames[i] = new Texture(filename + i + ".png");
		}
	}

	public Texture getTexture() {
		elapsedTime += timer.getDelta();

		if(elapsedTime >= fps) {
			elapsedTime = 0;
			pointer++;
		}

		if(pointer >= frames.length)
			pointer = 0;

		return frames[pointer];
	}
}