package com.draglantix.guis;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.util.Maths;
import com.draglantix.window.Window;

public class GuiRenderer {

	private GuiShader shader;
	private Assets assets;

	public GuiRenderer(GuiShader shader, Assets assets) {
		this.shader = shader;
		this.assets = assets;
	}

	public void renderGuis(List<Gui> guis) {
		Engine.prepare(shader);
		render(guis);
		Engine.finish(shader);
	}

	private void render(List<Gui> guis) {
		for(Gui gui : guis) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture());
			loadUniforms(gui);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, Engine.quad.getVertexCount());
		}
	}

	protected void loadUniforms(Gui gui) {
		Maths.createProjectionMatrix(Window.getWidth(), Window.getHeight());
		gui.updateTransformationMatrix(gui.getPosition(), gui.getRotation(), gui.getScale());
		shader.loadTransformationMatrix(Maths.getFinalMatrix(gui.getTransformationMatrix(), assets, !gui.isStatic()));
		shader.loadFade(gui.getAlpha());
		shader.loadColour(gui.getColour());
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
