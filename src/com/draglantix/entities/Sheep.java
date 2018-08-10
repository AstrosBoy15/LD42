package com.draglantix.entities;

import org.joml.Vector2f;

import com.draglantix.assets.Assets;
import com.draglantix.collsion.Polygon;
import com.draglantix.main.Configs;

public class Sheep extends Entity {

	public Sheep(int texture, Vector2f position, Vector2f rotation, Vector2f scale, float maxSpeed, Assets assets) {
		super(texture, 0, position, rotation, scale, maxSpeed, new Polygon(
				new float[] { position.x + scale.x * Configs.worldScale, position.x - scale.x * Configs.worldScale,
						position.x - scale.x * Configs.worldScale },
				new float[] { position.y - scale.y * Configs.worldScale, position.y - scale.y * Configs.worldScale,
						position.y + scale.y * Configs.worldScale },
				false), assets);
	}

	@Override
	public void tick() {
		super.tick();
		// move(new Vector2f(Maths.rand().nextFloat() * 10, Maths.rand().nextFloat() *
		// 10));
		currentSpeed = new Vector2f(0, 0);
	}

}
