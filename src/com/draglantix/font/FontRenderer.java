package com.draglantix.font;

import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import com.draglantix.assets.Assets;
import com.draglantix.engine.Engine;
import com.draglantix.main.Configs;
import com.draglantix.util.Maths;
import com.draglantix.window.Window;

public class FontRenderer {

	private FontShader shader;
	private Assets assets;

	public FontRenderer(FontShader shader, Assets assets) {
		this.shader = shader;
		this.assets = assets;
	}

	public void renderFonts(List<Font> msgs) {
		Engine.prepare(shader);
		render(msgs);
		Engine.finish(shader);
	}

	public void render(List<Font> msgs) {
		for(Font msg : msgs) {
			shader.loadNumberOfRows(msg.getRows());
			shader.loadFontColor(new Vector3f(msg.getColor().x, msg.getColor().y, msg.getColor().z));
			float cursor = 0;
			float returnLength = 0;
			int currentWord = 0;
			int currentLineIndex = 0;

			for(int i = 0; i < msg.getTextureIndex().length; i++) {
				GL13.glActiveTexture(GL13.GL_TEXTURE0);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, msg.getTexture());

				if(msg.getTextureIndex()[i] == 39) {
					if(currentWord < msg.getWordLengths().size() - 1) {
						currentWord++;
					}
				}

				if(i != 0) {
					cursor += (float) (msg.getImageWidth(msg.getTextureIndex()[i - 1]) * msg.getScale().x / 0.5f)
							/ Configs.WIDTH;
				}

				if(currentLineIndex + msg.getWordLengths().get(currentWord) > msg.getMaxCharLength() - 1) {
					if(i - 1 < msg.getTextureIndex().length) {
						if(msg.getTextureIndex()[i - 1] == 39) {
							returnLength -= 0.1f * msg.getScale().x / 0.5f;
							currentLineIndex = 0;
							cursor = 0;
						}
					}
				}
				Maths.createProjectionMatrix(Window.getWidth(), Window.getHeight());
				Vector2f finalPos = new Vector2f(msg.getPosition().x + cursor * Configs.WIDTH / 80 + msg.getScale().x,
						msg.getPosition().y + returnLength * 10 - msg.getScale().y);
				msg.updateTransformationMatrix(finalPos, msg.getRotation(), msg.getScale());
				shader.loadOffset(msg.getTextureXOffset(i), msg.getTextureYOffset(i));
				shader.loadTransformationMatrix(Maths.getFinalMatrix(msg.getTransformationMatrix(), assets, false));
				GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, Engine.quad.getVertexCount());

				currentLineIndex++;
			}
		}
	}

	public void cleanUp() {
		shader.cleanUp();
	}

}
