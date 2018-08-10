package com.draglantix.collsion;

import org.joml.Vector2f;

import com.draglantix.main.Configs;

public class Polygon {

	private Vector2f[] points;

	private Vector2f min, max;

	private boolean isMoveable;

	private float width, height;

	public Polygon(float[] x, float[] y, boolean isMoveable) {
		this.isMoveable = isMoveable;
		points = new Vector2f[x.length];
		for(int i = 0; i < x.length; i++) {
			points[i] = new Vector2f(x[i], y[i]);
			if(i == 0) {
				min = new Vector2f(x[i], y[i]);
				max = new Vector2f(x[i], y[i]);
			}
			if(x[i] < min.x) {
				min.x = x[i];
			}
			if(x[i] > max.x) {
				max.x = x[i];
			}
			if(y[i] < min.y) {
				min.y = y[i];
			}
			if(y[i] > max.y) {
				max.y = y[i];
			}
		}
		width = max.x - min.x;
		height = max.y - min.y;
	}

	public Vector2f[] getAxes() {
		// Return the normal of every edge of the polygon
		Vector2f[] axes = new Vector2f[points.length];
		for(int i = 0; i < points.length; i++) {
			// Get the vector of the edge
			Vector2f vector = new Vector2f(points[i].x - points[i + 1 == points.length ? 0 : i + 1].x,
					points[i].y - points[i + 1 == points.length ? 0 : i + 1].y);
			// Get the normal of the unit vector of the edge
			axes[i] = vector.perpendicular().normalize();
		}
		return axes;
	}

	public Vector2f getPoint(int i) {
		return points[i];
	}

	public int getNumOfPoints() {
		return points.length;
	}

	public void add(Vector2f dir) {
		for(int i = 0; i < points.length; i++) {
			points[i].x += dir.x;
			points[i].y += dir.y;
			if(i == 0) {
				min = new Vector2f(points[i].x, points[i].y);
				max = new Vector2f(points[i].x, points[i].y);
			}
			if(points[i].x < min.x) {
				min.x = points[i].x;
			}
			if(points[i].x > max.x) {
				max.x = points[i].x;
			}
			if(points[i].y < min.y) {
				min.y = points[i].y;
			}
			if(points[i].y > max.y) {
				max.y = points[i].y;
			}
		}
	}

	public void setPosition(Vector2f pos) {
		Vector2f dir = pos.sub(getPosition(), new Vector2f());
		add(dir);
	}

	public Vector2f getPosition() {
		return new Vector2f(
				min.add(new Vector2f(max.x - min.x, max.y - min.y).mul(.5f * Configs.worldScale), new Vector2f()));
	}

	public boolean getIsMoveable() {
		return isMoveable;
	}

	public Vector2f getMin() {
		return min;
	}

	public Vector2f getMax() {
		return max;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}
