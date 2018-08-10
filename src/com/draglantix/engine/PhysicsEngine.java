package com.draglantix.engine;

import org.joml.Vector2f;

import com.draglantix.util.ObjectData;

public class PhysicsEngine {

	public static void init() {
	}

	public static void flip(ObjectData obj) {
		float yRot;

		if(obj.getRotation().y == 180) {
			yRot = 0;
		} else {
			yRot = 180;
		}

		obj.setRotation(new Vector2f(obj.getRotation().x, yRot));
	}

	public static void rotate(ObjectData obj, float rot) {
		obj.setRotation(new Vector2f(obj.getRotation().x + rot, obj.getRotation().y));
	}

}
