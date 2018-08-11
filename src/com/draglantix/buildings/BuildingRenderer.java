package com.draglantix.buildings;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.util.Maths;
import com.draglantix.window.Window;

public class BuildingRenderer {

	private BuildingShader shader;
	private Assets assets;

	public BuildingRenderer(BuildingShader shader, Assets assets) {
		this.shader = shader;
		this.assets = assets;
	}

	public void renderBuildings(List<Building> buildings) {
		Engine.prepare(shader);
		render(buildings);
		Engine.finish(shader);
	}

	private void render(List<Building> buildings) {
		for(Building building : buildings) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, building.getTexture());
			loadUniforms(building);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, Engine.quad.getVertexCount());
		}
	}

	protected void loadUniforms(Building building) {
		Maths.createProjectionMatrix(Window.getWidth(), Window.getHeight());
		building.updateTransformationMatrix(building.getPosition(), building.getRotation(), building.getScale());
		shader.loadTransformationMatrix(Maths.getFinalMatrix(building.getTransformationMatrix(), assets, true));
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
