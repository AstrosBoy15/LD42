package com.draglantix.postProcessing;

import com.draglantix.engine.ShaderProgram;

public class BlurHorizontalShader extends ShaderProgram {

	private int location_targetWidth;

	public BlurHorizontalShader(String key) {
		super(key);
	}

	public void loadTargetWidth(float width) {
		super.loadFloat(location_targetWidth, width);
	}

	protected void getAllUniformLocations() {
		location_targetWidth = super.getUniformLocation("targetWidth");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

}
