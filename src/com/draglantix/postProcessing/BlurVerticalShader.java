package com.draglantix.postProcessing;

import com.draglantix.engine.ShaderProgram;

public class BlurVerticalShader extends ShaderProgram {

	private int location_targetHeight;

	public BlurVerticalShader(String key) {
		super(key);
	}

	public void loadTargetHeight(float height) {
		super.loadFloat(location_targetHeight, height);
	}

	@Override
	protected void getAllUniformLocations() {
		location_targetHeight = super.getUniformLocation("targetHeight");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
}
