package com.draglantix.font;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.engine.ShaderProgram;

public class FontShader extends ShaderProgram {

	private int location_transformationMatrix;
	private int location_numberOfRows;
	private int location_offset;
	private int location_fontColor;

	public FontShader(String key) {
		super(key);
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_numberOfRows = super.getUniformLocation("numberOfRows");
		location_offset = super.getUniformLocation("offset");
		location_fontColor = super.getUniformLocation("fontColor");
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}

	public void loadFontColor(Vector3f color) {
		super.loadVector(location_fontColor, color);
	}

	public void loadNumberOfRows(int numberOfRows) {
		super.loadFloat(location_numberOfRows, numberOfRows);
	}

	public void loadOffset(float x, float y) {
		super.load2DVector(location_offset, new Vector2f(x, y));
	}

	@Override
	protected void bindAttributes() {
		// TODO Auto-generated method stub

	}

}
