package com.draglantix.entities;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.util.Maths;
import com.draglantix.window.Window;

public class EntityRenderer {

	private EntityShader shader;
	private Assets assets;

	public EntityRenderer(EntityShader shader, Assets assets) {
		this.shader = shader;
		this.assets = assets;
	}

	public void renderEntities(List<Entity> entities) {
		Engine.prepare(shader);
		render(entities);
		Engine.finish(shader);
	}

	private void render(List<Entity> entities) {
		for(Entity entity : entities) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, entity.getTexture());
			loadUniforms(entity);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, Engine.quad.getVertexCount());
		}
	}

	protected void loadUniforms(Entity entity) {
		Maths.createProjectionMatrix(Window.getWidth(), Window.getHeight());
		entity.updateTransformationMatrix(entity.getPosition(), entity.getRotation(), entity.getScale());
		shader.loadTransformationMatrix(Maths.getFinalMatrix(entity.getTransformationMatrix(), assets, true));
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
