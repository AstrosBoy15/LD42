package com.draglantix.engine;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWScrollCallback;

import com.draglantix.main.Configs;
import com.draglantix.window.Window;

public class Camera {

	private Vector2f position;
	private float roll, lerp, zoom;
	private Vector2f lastMousePosition;
	private boolean isMouseClicked = false;
	private float ypos;

	public Camera(Vector2f position, float roll, float zoom, float lerp) {
		this.position = position;
		this.roll = roll;
		this.zoom = zoom;
		this.lerp = lerp;
	}

	public void update() {
		move();
	}

	public void move() {

		//updateZoom();

		Vector2f delta = new Vector2f();
		if (Window.getInput().isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			if (!isMouseClicked) {
				lastMousePosition = Window.getInput().getMousePos();
				isMouseClicked = true;
			}
			Vector2f currentPos = Window.getInput().getMousePos();
			delta.x = lastMousePosition.x - currentPos.x;
			delta.y = currentPos.y - lastMousePosition.y;
			lastMousePosition = currentPos;
		} else {
			isMouseClicked = false;
		}

		Vector2f tempPosition = position.add(delta, new Vector2f());

		position.lerp(tempPosition, lerp);
		correctCamera();
	}

	public Matrix4f createViewMatrix() {
		Matrix4f view = new Matrix4f();
		view.scale((float) Math.exp(zoom));
		view.rotate((float) (Math.toRadians(roll)), 0, 0, 1);
		view.translate(-position.x, -position.y, 0);
		return view;
	}

	private void updateZoom() {
		ypos = 0;
		GLFW.glfwSetScrollCallback(Window.getWindow(), GLFWScrollCallback.create((window, xoffset, yoffset) -> {
			ypos = (float) yoffset;
			zoom += ypos * 0.075f;
		}));
		if (zoom < -1) {
			zoom = -1;
		} else if (zoom > 1) {
			zoom = 1;
		}
	}

	private void correctCamera() {
		if (position.x > Configs.worldWidth * Configs.worldScale - Window.getWidth() / 2)
			position.x = Configs.worldWidth * Configs.worldScale - Window.getWidth() / 2;
		if (position.x < -Configs.worldWidth * Configs.worldScale + Window.getWidth() / 2)
			position.x = -Configs.worldWidth * Configs.worldScale + Window.getWidth() / 2;
		if (position.y > Configs.worldHeight * Configs.worldScale - Window.getHeight() / 2)
			position.y = Configs.worldHeight * Configs.worldScale - Window.getHeight() / 2;
		if (position.y < -Configs.worldHeight * Configs.worldScale + Window.getHeight() / 2)
			position.y = -Configs.worldHeight * Configs.worldScale + Window.getHeight() / 2;

	}

	public Vector2f getPosition() {
		return position;
	}

	public float getZoom() {
		return (float) Math.exp(-zoom);
	}

}
