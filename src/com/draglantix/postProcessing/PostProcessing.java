package com.draglantix.postProcessing;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.draglantix.engine.Engine;
import com.draglantix.models.Loader;
import com.draglantix.util.FBO;
import com.draglantix.window.Window;

public class PostProcessing {

	private BlurHorizontal hBlur;
	public BlurVertical vBlur;

	public PostProcessing(Loader loader) {
		hBlur = new BlurHorizontal(Window.getWidth() / 2);
		vBlur = new BlurVertical(Window.getHeight() / 2);
	}

	public void doBlurHorizontal(FBO src, FBO dest) {
		dest.bindFrameBuffer();
		start();
		hBlur.render(src.getColorTexture());
		end();
		dest.unbindFrameBuffer();
	}

	public void doBlurVertical(FBO src, FBO dest) {
		dest.bindFrameBuffer();
		start();
		vBlur.render(src.getColorTexture());
		end();
		dest.unbindFrameBuffer();
	}

	public void cleanUp() {
		hBlur.cleanUp();
		vBlur.cleanUp();
	}

	private void start() {
		GL30.glBindVertexArray(Engine.quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	private void end() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

}
