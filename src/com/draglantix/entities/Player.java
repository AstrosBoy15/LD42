package com.draglantix.entities;

import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

import com.draglantix.assets.Assets;
import com.draglantix.collsion.Polygon;
import com.draglantix.main.Configs;
import com.draglantix.util.Timer;
import com.draglantix.window.Window;

public class Player extends Entity {

	private final static int NUMBER_OF_ANIMATIONS = 2;
	private final static int ANIMATION_WALK = 0;
	private final static int ANIMATION_IDLE = 1;

	public Player(int texture, Vector2f position, Vector2f rotation, Vector2f scale, float maxSpeed, Assets assets) {
		super(texture, NUMBER_OF_ANIMATIONS, position, rotation, scale, maxSpeed, new Polygon(
				new float[] { position.x + scale.x * Configs.worldScale, position.x + scale.x * Configs.worldScale,
						position.x - scale.x * Configs.worldScale, position.x - scale.x * Configs.worldScale },
				new float[] { position.y + scale.y * Configs.worldScale, position.y - scale.y * Configs.worldScale,
						position.y - scale.y * Configs.worldScale, position.y + scale.y * Configs.worldScale },
				true), assets);
		addAnimation(assets.playAssets.playerWalk, ANIMATION_WALK);
		addAnimation(assets.playAssets.playerIdle, ANIMATION_IDLE);
		timer = new Timer();
	}

	@Override
	public void tick() {
		super.tick();
		movePlayer();
	}

	private void movePlayer() {

		Vector2f direction = new Vector2f();
		float delta = timer.getDelta();
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_A)) {
			currentSpeed.x -= speed * delta * 100;
			direction.x--;
		}
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_W)) {
			currentSpeed.y += speed * delta * 100;
			direction.y++;
		}
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_S)) {
			currentSpeed.y -= speed * delta * 100;
			direction.y--;
		}
		if(Window.getInput().isKeyDown(GLFW.GLFW_KEY_D)) {
			currentSpeed.x += speed * delta * 100;
			direction.x++;
		}

		if(direction.x == 0 && direction.y == 0) {
			currentAnimation = ANIMATION_IDLE;
		} else {
			currentAnimation = ANIMATION_WALK;
		}

		flip(direction.x);
	}

}
