package com.draglantix.main;

import com.draglantix.engine.Engine;
import com.draglantix.window.Window;

public class CleanUpMaster {

	public static void cleanUp() {
		Window.cleanUp();
		Engine.cleanUp();
	}

}
