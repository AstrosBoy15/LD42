package com.draglantix.font;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.assets.Assets;
import com.draglantix.util.ObjectData;
import com.draglantix.util.Reader;

public class Font extends ObjectData {

	private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,: %*/?!()-+";

	private Vector3f color;

	private int[] textureIndex;
	private int rows;

	private int maxCharLength;

	String file;
	private String[] tokens;

	private List<Integer> wordLengths = new ArrayList<Integer>();

	public Font(int texture, String fntPath, String msg, Vector2f position, float scale, int rows, int maxCharLength,
			Vector3f color, Assets assets) {
		super(texture, 0, position, new Vector2f(0, 0), new Vector2f(scale), null, assets);
		this.rows = rows;
		this.color = color;
		this.maxCharLength = maxCharLength;

		textureIndex = new int[msg.length()];

		int currentWord = 0;

		msg = msg.toUpperCase();
		int length = msg.length();
		for(int i = 0; i < length; i++) {
			int c = letters.indexOf(msg.charAt(i));
			if(c < 0)
				continue;
			textureIndex[i] = c;
			if(c == 39) {
				wordLengths.add(currentWord + 1);
				currentWord = 0;
			}
			currentWord++;
		}

		file = Reader.loadFileAsString(fntPath);
		tokens = file.split("\\s+");
	}

	public void tick() {

	}

	public List<Integer> getWordLengths() {
		return wordLengths;
	}

	public int getImageWidth(int i) {
		return Reader.parseInt(tokens[i]);
	}

	public int getMaxCharLength() {
		return maxCharLength;
	}

	public int[] getTextureIndex() {
		return textureIndex;
	}

	public float getTextureXOffset(int i) {
		int column = textureIndex[i] % rows;
		return (float) column / (float) rows;
	}

	public float getTextureYOffset(int i) {
		int row = textureIndex[i] / rows;
		return (float) row / (float) rows;
	}

	public int getRows() {
		return rows;
	}

	public Vector3f getColor() {
		return color;
	}

}
