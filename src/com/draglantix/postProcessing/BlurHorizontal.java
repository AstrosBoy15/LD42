package com.draglantix.postProcessing;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class BlurHorizontal {

	private BlurHorizontalShader shader;

	public BlurHorizontal(int targetFboWidth) {
		shader = new BlurHorizontalShader("filters/blurHorizontal");
		shader.start();
		shader.loadTargetWidth(targetFboWidth);
		shader.stop();
	}

	public void render(int texture) {
		shader.start();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		shader.stop();
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
