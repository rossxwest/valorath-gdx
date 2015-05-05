package com.defectivemind.valorath;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Resolutions {
	private static String[] options = {
		"1024 x 768",
		"1280 x 1024",
		"1366 x 768",
		"1440 x 900",
		"1600 x 900",
		"1680 x 1050",
		"1920 x 1080",
		"1920 x 1200"
	};
	
	public static String[] getOptions () {
		return options;
	}
	
	public static Vector2 toVector (String s) {
		String[] values = s.split(" x ");
		
		return new Vector2(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
	}
	
	public static void setResolution (Vector2 newRes, String displayMode) {
    	boolean fullscreen = false;
    	
    	if (displayMode.equals("Fullscreen")) {
    		fullscreen = true;
    	} else if (displayMode.equals("Windowed")) {
    		fullscreen = false;
    		System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
    	} else if (displayMode.equals("Borderless Windowed")) {
    		fullscreen = false;
    		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
    	}
    	
    	// If windowed or borderless windowed, refresh the resolution to refresh with border/borderless
    	if (!fullscreen) {
    		Gdx.app.getGraphics().setDisplayMode(1, 1, fullscreen);
    	}
    	
    	// Set the resolution
    	Gdx.app.getGraphics().setDisplayMode((int)newRes.x, (int)newRes.y, fullscreen);
	}
}
