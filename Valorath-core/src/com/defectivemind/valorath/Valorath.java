package com.defectivemind.valorath;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Valorath extends Game {
	public static final String TITLE = "Pong"; 
    public static final int WIDTH = 1366, HEIGHT = 768; 
    public static final boolean fullscreen = false;
    public SaveManager saveManager;
    
    @Override
    public void create() {
    	Texture t = new Texture(Gdx.files.internal("dmg-logo.png"));
    	SplashScreen dmg = new SplashScreen(t);
    	saveManager = new SaveManager(true);
    	
    	String displayMode = "Fullscreen";
    	
    	if (saveManager.loadDataValue("displayMode", String.class) != null) {
    		displayMode = saveManager.loadDataValue("displayMode", String.class);
    	}
    	
    	String resolution = "1366 x 768";
    	
    	if (saveManager.loadDataValue("resolution", String.class) != null) {
    		resolution = saveManager.loadDataValue("resolution", String.class);
    	}
    	
    	Vector2 newRes = Resolutions.toVector(resolution);
    	
    	Resolutions.setResolution(newRes, displayMode);
    	
        setScreen(dmg);
    }
}
