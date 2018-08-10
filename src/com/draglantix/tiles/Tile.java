package com.draglantix.tiles;

import org.joml.Vector2f;

import com.draglantix.assets.Assets;
import com.draglantix.collsion.Polygon;
import com.draglantix.main.Configs;
import com.draglantix.util.ObjectData;

public class Tile extends ObjectData {

	private TileData data;

	public Tile(TileData data, Vector2f position, Vector2f rotation, Vector2f scale, Assets assets) {
		super(data.getTexture().getTextureID(), 0, position, rotation, scale,
				data.getBounding() ? new Polygon(
						new float[] { position.x - scale.x * Configs.worldScale,
								position.x - scale.x * Configs.worldScale, position.x, position.x },
						new float[] { position.y - scale.y * Configs.worldScale, position.y, position.y,
								position.y - scale.y * Configs.worldScale },
						false) : null,
				assets);
		this.data = data;
	}

	@Override
	public void tick() {

	}

	public TileData getData() {
		return data;
	}

}
