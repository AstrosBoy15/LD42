package com.draglantix.collsion;

public class Projection {
	private float min;
	private float max;

	public Projection(float min, float max) {
		this.min = min;
		this.max = max;
	}

	public boolean overlap(Projection p2) {
		return (!(p2.max < this.min || this.max < p2.min));
	}

	public float getMin() {
		return min;
	}

	public float getMax() {
		return max;
	}
}
