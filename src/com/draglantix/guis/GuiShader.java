package com.draglantix.guis;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.draglantix.engine.ShaderProgram;

public class GuiShader extends ShaderProgram {

	private int location_alpha;
	private int location_transformationMatrix;
	private int location_colour;

	public GuiShader(String key) {
		super(key);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	protected void getAllUniformLocations() {
		location_alpha = super.getUniformLocation("alpha");
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_colour = super.getUniformLocation("colour");
	}

	public void loadFade(float alpha) {
		super.loadFloat(location_alpha, alpha);
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}

	public void loadColour(Vector3f colour) {
		super.loadVector(location_colour, colour);
	}

}
