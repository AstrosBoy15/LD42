package com.draglantix.util;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import com.draglantix.assets.Assets;
import com.draglantix.collsion.Polygon;
import com.draglantix.models.Animation;

public abstract class ObjectData {

	protected int texture;
	protected Vector2f position, rotation, scale;

	protected List<Animation> animations;

	protected int currentAnimation;

	protected Polygon bounding;

	protected Assets assets;

	protected Matrix4f transformation;

	public ObjectData(int texture, int numAnimations, Vector2f position, Vector2f rotation, Vector2f scale,
			Polygon bounding, Assets assets) {
		this.texture = texture;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.bounding = bounding;
		this.assets = assets;
		this.animations = new ArrayList<Animation>(numAnimations);
		currentAnimation = 0;
		updateTransformationMatrix(position, rotation, scale);
	}

	public abstract void tick();

	public int getTexture() {
		return texture;
	}

	public void setTexture(int texture) {
		this.texture = texture;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void addAnimation(Animation animation, int index) {
		animations.add(index, animation);
	}

	public Animation getAnimation(int index) {
		return animations.get(index);
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getRotation() {
		return rotation;
	}

	public void setRotation(Vector2f rotation) {
		this.rotation = rotation;
	}

	public Vector2f getScale() {
		return scale;
	}

	public Matrix4f updateTransformationMatrix(Vector2f translation, Vector2f rot, Vector2f scale) {
		Matrix4f transformation = new Matrix4f();
		transformation.translate(translation.x, translation.y, 0);
		transformation.rotate((float) Math.toRadians(rot.x), 0.0f, 0.0f, 1.0f);
		transformation.rotate((float) Math.toRadians(rot.y), 0.0f, 1.0f, 0.0f);
		transformation.scale(scale.x, scale.y, 0);

		this.transformation = transformation;

		return transformation;
	}

	public Matrix4f getTransformationMatrix() {
		return transformation;
	}

	public Polygon getBounding() {
		return bounding;
	}
}
