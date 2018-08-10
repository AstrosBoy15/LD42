package com.draglantix.engine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.entities.Player;
import com.draglantix.main.Configs;
import com.draglantix.window.Window;

public class Camera {

	private Vector2f position;
	private float roll, lerp, zoom;

	private Player player;

	public Camera(Vector2f position, float roll, float zoom, float lerp, Player player) {
		this.position = position;
		this.roll = roll;
		this.zoom = zoom;
		this.lerp = lerp;
		this.player = player;
	}

	public void update() {
		move();
	}

	public void move() {
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_R)) {
			roll += 1f;
		}

//		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_Q) && zoom < 2) {
//			zoom += 0.01f;
//		}
//
//		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_E) && zoom > -3) {
//			zoom -= 0.01f;
//		}

		position.lerp(player.getPosition(), lerp);
		correctCamera();
	}

	public Matrix4f createViewMatrix() {
		Matrix4f view = new Matrix4f();
		view.scale((float) Math.exp(zoom));
		view.rotate((float) (Math.toRadians(roll)), 0, 0, 1);
		view.translate(-position.x, -position.y, 0);
		return view;
	}

	private void correctCamera() {
		if(position.x > Configs.worldWidth * Configs.worldScale - Window.getWidth() / 2)
			position.x = Configs.worldWidth * Configs.worldScale - Window.getWidth() / 2;
		if(position.x < -Configs.worldWidth * Configs.worldScale + Window.getWidth() / 2)
			position.x = -Configs.worldWidth * Configs.worldScale + Window.getWidth() / 2;
		if(position.y > Configs.worldHeight * Configs.worldScale - Window.getHeight() / 2)
			position.y = Configs.worldHeight * Configs.worldScale - Window.getHeight() / 2;
		if(position.y < -Configs.worldHeight * Configs.worldScale + Window.getHeight() / 2)
			position.y = -Configs.worldHeight * Configs.worldScale + Window.getHeight() / 2;

	}

	public Vector2f getPosition() {
		return position;
	}

	public float getZoom() {
		return (float) Math.exp(zoom);
	}

}
