package com.draglantix.window;

import org.lwjgl.glfw.GLFW;

public class WindowHandler {

	public static void handle(Window window) {

		window.update();

//		if(Window.hasResized()) {
//			GL11.glViewport(0, 0, Window.getWidth(), Window.getHeight());
//		}

		if(Window.getInput().isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			GLFW.glfwSetWindowShouldClose(Window.getWindow(), true);
		}

	}

}
