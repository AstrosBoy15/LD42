package com.draglantix.statistics;

import com.draglantix.util.Timer;

public class Statistics {

	public static float gold = 0;
	public static float food = 30;
	public static float population = 5;
	public static float popularity = 0;
	public static float sheltered = 5;
	public static float diseased = 0;
	public static float hungary = 0;

	public static Timer timer = new Timer();
	public static float elapsedTime = 0;

	public static void update() {
		elapsedTime += timer.getDelta();
		if (elapsedTime > 1) {
			food -= population;
			if (food < 0) {
				hungary = -food;
				food = 0;
			} else {
				hungary = 0;
			}
			popularity = 100 * (population - hungary) / population;
			elapsedTime = 0;
		}
	}

	public static void addGold(int num) {
		gold += num;
	}

	public static void addFood(int num) {
		food += num;
	}

	public static void addPopulation(int num) {
		population += num;
	}

	public static void addSheltered(int num) {
		sheltered += num;
	}

	public static void addDiseased(int num) {
		diseased += num;
	}

}
