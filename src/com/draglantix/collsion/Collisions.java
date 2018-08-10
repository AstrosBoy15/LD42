package com.draglantix.collsion;

import java.lang.reflect.Array;

import org.joml.Vector2f;

public class Collisions {

	public static Vector2f testCollision(Polygon A, Polygon B) {
		// Get the array of both axes to project the shapes on
		Vector2f[] axes = concatenate(A.getAxes(), B.getAxes());

		Vector2f smallest = new Vector2f();
		float overlap = 1000;

		for(Vector2f axis : axes) {
			// Project both Shapes onto the axis
			Projection pA = project(A, axis);
			Projection pB = project(B, axis);
			if(!pA.overlap(pB)) {
				// If they don't overlap, the shapes don't either so return null
				return null;
			} else {
				float o;
				if(pB.getMax() > pA.getMax()) {
					o = pA.getMax() - pB.getMin();
				} else {
					o = pA.getMin() - pB.getMax();
				}
				if(Math.abs(o) < Math.abs(overlap)) {
					overlap = o;
					smallest = axis;
				}
			}
		}

		// All the projections overlap: the polygons collide
		float x = (float) (overlap * -smallest.x / getMagnitude(smallest));
		float y = (float) (overlap * -smallest.y / getMagnitude(smallest));
		return new Vector2f(x, y);
	}

	private static Projection project(Polygon a, Vector2f axis) {
		// Project the shapes along the axis
		float min = axis.dot(a.getPoint(0));
		float max = min;
		for(int i = 1; i < a.getNumOfPoints(); i++) {
			float p = axis.dot(a.getPoint(i));
			if(p < min) {
				min = p;
			} else if(p > max) {
				max = p;
			}
		}
		return new Projection(min, max);
	}

	public static Vector2f[] concatenate(Vector2f[] a, Vector2f[] b) {
		// Combine the two arrays of points
		int aLen = a.length;
		int bLen = b.length;

		Vector2f[] c = (Vector2f[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);

		return c;
	}

	private static float getMagnitude(Vector2f mag) {
		return (float) Math.sqrt(Math.pow(mag.x, 2) + Math.pow(mag.y, 2));
	}

}
