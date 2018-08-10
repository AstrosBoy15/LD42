package com.draglantix.main;

import com.draglantix.util.Timer;

public class FPSHandler {

	private long variableYieldTime, lastTime, lastFPS;
	private int fps;
	private Timer timer = new Timer();
	private boolean printFPS;

	public FPSHandler(boolean printFPS) {
		this.printFPS = printFPS;
	}

	/**
	 * An accurate sync method that adapts automatically to the system it runs on to
	 * provide reliable results.
	 * 
	 * @param fps The desired frame rate, in frames per second
	 * @author kappa (On the LWJGL Forums)
	 */
	public void sync(int fps) {
		if(fps <= 0)
			return;

		long sleepTime = 1000000000 / fps; // nanoseconds to sleep this frame
		// yieldTime + remainder micro & nano seconds if smaller than sleepTime
		long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000 * 1000));
		long overSleep = 0; // time the sync goes over by

		try {
			while(true) {
				long t = (long) (timer.getTime() * 1000000000 - lastTime);

				if(t < sleepTime - yieldTime) {
					Thread.sleep(1);
				} else if(t < sleepTime) {
					// burn the last few CPU cycles to ensure accuracy
					Thread.yield();
				} else {
					overSleep = t - sleepTime;
					break; // exit while loop
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lastTime = (long) (timer.getTime() * 1000000000 - Math.min(overSleep, sleepTime));

			// auto tune the time sync should yield
			if(overSleep > variableYieldTime) {
				// increase by 200 microseconds (1/5 a ms)
				variableYieldTime = Math.min(variableYieldTime + 200 * 1000, sleepTime);
			} else if(overSleep < variableYieldTime - 200 * 1000) {
				// decrease by 2 microseconds
				variableYieldTime = Math.max(variableYieldTime - 2 * 1000, 0);
			}
		}

		if(printFPS) {
			updateFPS();
		}
	}

	public void updateFPS() {
		if(timer.getTime() * 1000000000 - lastFPS > 1000000000) {
			System.out.println("FPS: " + fps);
			fps = 0;
			lastFPS += 1000000000;
		}
		fps++;
	}

}
