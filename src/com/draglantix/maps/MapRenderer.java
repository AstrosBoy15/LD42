package com.draglantix.maps;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.util.Maths;
import com.draglantix.util.ObjectData;
import com.draglantix.window.Window;

public class MapRenderer {

	private boolean fgRequest;

	private MapShader shader;
	private Assets assets;

	public MapRenderer(MapShader shader, Assets assets) {
		this.shader = shader;
		this.assets = assets;
	}

	public void renderMaps(List<Map> maps, boolean fgRequest) {
		this.fgRequest = fgRequest;
		Engine.prepare(shader);
		render(maps);
		Engine.finish(shader);
	}

	protected void render(List<Map> maps) {
		for(Map map : maps) {
			if(map.isForeground() == fgRequest) {
				GL13.glActiveTexture(GL13.GL_TEXTURE0);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, map.getTexture());
				loadUniforms(map);
				GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, Engine.quad.getVertexCount());
			}
		}
	}

	private void loadUniforms(ObjectData map) {
		Maths.createProjectionMatrix(Window.getWidth(), Window.getHeight());
		map.updateTransformationMatrix(map.getPosition(), map.getRotation(), map.getScale());
		shader.loadTransformationMatrix(Maths.getFinalMatrix(map.getTransformationMatrix(), assets, true));
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
