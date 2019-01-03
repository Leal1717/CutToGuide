package com.kunal.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kunal.AllVariables;
import com.kunal.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.width = AllVariables.WIDTH;
	    config.height = AllVariables.HEIGHT;
	    new LwjglApplication(new MainGame(), config);
	}
}
