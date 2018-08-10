package com.draglantix.entities;

import org.joml.Vector2f;

import com.draglantix.assets.Assets;
import com.draglantix.collsion.Collisions;
import com.draglantix.collsion.Polygon;
import com.draglantix.engine.Engine;
import com.draglantix.engine.PhysicsEngine;
import com.draglantix.main.Configs;
import com.draglantix.util.ObjectData;
import com.draglantix.util.Timer;

public abstract class Entity extends ObjectData {

	protected float speed;
	protected Vector2f currentSpeed = new Vector2f();
	private float lastDirX = -1;
	protected Timer timer;

	public Entity(int texture, int numAnimations, Vector2f position, Vector2f rotation, Vector2f scale, float speed,
			Polygon bounding, Assets assets) {
		super(texture, numAnimations, position, rotation, scale.mul(Configs.worldScale), bounding, assets);
		this.speed = speed;
	}

	public void tick() {
		// Add jump stuff and gravity to y
		if(animations.size() != 0) {
			setTexture(getAnimation(currentAnimation).getTexture().getTextureID());
		}
		move(currentSpeed);
		currentSpeed = new Vector2f(0, 0);
	}

	private void move(Vector2f dir) {
		if(bounding != null) {
			bounding.add(dir);
			checkForCollisions();
			position.set(bounding.getPosition());
		} else {
			position.add(dir);
		}
	}

	protected void jump() {
		// set jump variables
	}

	protected void flip(float dirX) {
		float facing;
		if(dirX == 0) {
			facing = 0;
		} else {
			facing = dirX / Math.abs(dirX);
		}
		if(lastDirX != facing && facing != 0) {
			PhysicsEngine.flip(this);
			lastDirX = facing;
		}
	}

	protected void checkForCollisions() {
		if(bounding.getIsMoveable()) {
			for(ObjectData entity : Engine.getEntities()) {
				if(!this.equals(entity) && entity.getBounding() != null) {
					Vector2f dir = Collisions.testCollision(bounding, entity.getBounding());
					if(dir != null)
						bounding.add(dir);
				}
			}
			for(ObjectData tile : Engine.getCurrentTiles(assets.playAssets.tileMap, position)) {
				if(tile.getBounding() != null) {
					Vector2f dir = Collisions.testCollision(bounding, tile.getBounding());
					if(dir != null)
						bounding.add(dir);
				}
			}

			if(bounding.getPosition().x + bounding.getWidth() / 2 >= Configs.worldWidth * Configs.worldScale) {
				bounding.setPosition(new Vector2f(Configs.worldWidth * Configs.worldScale - bounding.getWidth() / 2,
						bounding.getPosition().y));
			} else if(bounding.getPosition().x - bounding.getWidth() / 2 <= -Configs.worldWidth * Configs.worldScale) {
				bounding.setPosition(new Vector2f(-Configs.worldWidth * Configs.worldScale + bounding.getWidth() / 2,
						bounding.getPosition().y));
			}
			if(bounding.getPosition().y + bounding.getHeight() / 2 >= Configs.worldHeight * Configs.worldScale) {
				bounding.setPosition(new Vector2f(bounding.getPosition().x,
						Configs.worldHeight * Configs.worldScale - bounding.getHeight() / 2));
			} else if(bounding.getPosition().y - bounding.getHeight() / 2 <= -Configs.worldHeight
					* Configs.worldScale) {
				bounding.setPosition(new Vector2f(bounding.getPosition().x,
						-Configs.worldHeight * Configs.worldScale + bounding.getHeight() / 2));
			}

		}

	}

}
