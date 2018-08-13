package com.draglantix.buttonComponents;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.draglantix.buildings.Building;
import com.draglantix.buildings.Empty;
import com.draglantix.engine.Engine;
import com.draglantix.guis.Gui;
import com.draglantix.main.Configs;
import com.draglantix.statistics.Statistics;
import com.draglantix.util.Timer;
import com.draglantix.window.Window;

public class ButtonBuilding extends ButtonComponent {

	private int num;
	private Gui gui;
	private Building building;
	private Vector3f color;

	public static final int BLANK = 0;
	public static final int UPGRADE = 1;
	public static final int COLLECT = 2;
	public static final int DESTROY = 3;
	public static final int BUILD = 5;

	private Timer timer;

	public ButtonBuilding(int num, Gui gui, Building building) {
		this.num = num;
		this.gui = gui;
		this.building = building;
		timer = new Timer();
	}

	public void tick() {
		if (checkCollision() && num != 0) {
			color = new Vector3f(0, 150f, 200f);
		} else {
			color = Configs.COLOUR_NULL;
		}
	}

	public void update() {
		switch (num) {
		case BLANK:
			break;
		case UPGRADE:
			System.out.println("Upgrade");
			building.reset();
			break;
		case COLLECT:
			if (building.getType() == Building.HOUSE) {
				Statistics.addGold((int) (timer.getDelta() * 5));

			} else if (building.getType() == Building.FARMLAND) {
				Statistics.addFood((int) (timer.getDelta() * 5));
			}
			building.reset();
			break;
		case DESTROY:
			Engine.addBuildings(new Empty(gui.getAssets().playAssets.squareTex.getTextureID(), building.getPosition(),
					new Vector2f(0, 0), new Vector2f(gui.getAssets().playAssets.squareTex.getWidth(),
							gui.getAssets().playAssets.squareTex.getHeight()),
					gui.getAssets()));
			Engine.removeBuildings(building);
			building.reset();
			break;
		case BUILD:
			((Empty) (building)).isCreateSelected = true;
			break;
		}
	}

	public Vector3f getColour() {
		return color;
	}

	private boolean checkCollision() {
		if (Window.getInput().getMousePos().x - Configs.WIDTH / 2 < gui.getBounding().getMax().x
				- gui.getAssets().playAssets.camera.getPosition().x
				&& Window.getInput().getMousePos().x - Configs.WIDTH / 2 > gui.getBounding().getMin().x
						- gui.getAssets().playAssets.camera.getPosition().x
				&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 < gui.getBounding().getMax().y
						+ gui.getAssets().playAssets.camera.getPosition().y
				&& Window.getInput().getMousePos().y - Configs.HEIGHT / 2 > gui.getBounding().getMin().y
						+ gui.getAssets().playAssets.camera.getPosition().y) {
			return true;
		}
		return false;
	}

}
