package com.draglantix.entities;

import org.joml.Matrix4f;

import com.draglantix.engine.ShaderProgram;

public class EntityShader extends ShaderProgram {

	private int location_transformationMatrix;

	public EntityShader(String key) {
		super(key);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}

}
