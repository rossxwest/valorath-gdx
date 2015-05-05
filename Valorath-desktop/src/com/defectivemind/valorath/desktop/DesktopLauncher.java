package com.defectivemind.valorath.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.defectivemind.valorath.Valorath;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Valorath.WIDTH; // sets window width
        config.height = Valorath.HEIGHT;  // sets window height
        config.resizable = false;
        config.fullscreen = Valorath.fullscreen;
        new LwjglApplication(new Valorath(), config);
	}
}
