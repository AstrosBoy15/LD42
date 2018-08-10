package com.draglantix.util;

import java.util.Random;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import com.draglantix.assets.Assets;

public class Maths {

	private static Matrix4f projection;

	private static Random rand = new Random();

	public static Matrix4f createProjectionMatrix(int width, int height) {
		Matrix4f projection = new Matrix4f().setOrtho2D(-width / 2, width / 2, -height / 2, height / 2);

		Maths.projection = projection;

		return projection;
	}

	public static Matrix4f getFinalMatrix(Matrix4f transformationMatrix, Assets assets, boolean useCamera) {
		if(assets.playAssets.isCameraCreated && useCamera) {
			return (projection.mul(assets.playAssets.camera.createViewMatrix())).mul(transformationMatrix);
		} else {
			return (projection.mul(createViewMatrixWithoutCamera())).mul(transformationMatrix);
		}
	}

	public static Matrix4f createViewMatrixWithoutCamera() {
		Matrix4f view = new Matrix4f();
		view.rotate((float) (Math.toRadians(0)), 0, 0, 1);
		view.translate(0, 0, 0);
		return view;
	}

	public static Matrix4f createTransformationMatrix(Vector2f translation, float scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.translate(translation.x, translation.y, 0);
		matrix.scale(scale);
		return matrix;
	}

	public static Random rand() {
		return rand;
	}
}
