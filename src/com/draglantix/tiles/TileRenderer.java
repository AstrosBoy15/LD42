package com.draglantix.tiles;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.util.Maths;
import com.draglantix.util.ObjectData;
import com.draglantix.window.Window;

public class TileRenderer {

	private TileShader shader;
	private Assets assets;

	public TileRenderer(TileShader shader, Assets assets) {
		this.shader = shader;
		this.assets = assets;
	}

	public void renderTiles(List<Tile> tiles) {
		Engine.prepare(shader);
		render(tiles);
		Engine.finish(shader);
	}

	private void render(List<Tile> tiles) {
		for(ObjectData tile : tiles) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, tile.getTexture());
			loadUniforms(tile);
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, Engine.quad.getVertexCount());
		}
	}

	private void loadUniforms(ObjectData tile) {
		Maths.createProjectionMatrix(Window.getWidth(), Window.getHeight());
		tile.updateTransformationMatrix(tile.getPosition(), tile.getRotation(), tile.getScale());
		shader.loadTransformationMatrix(Maths.getFinalMatrix(tile.getTransformationMatrix(), assets, true));
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
