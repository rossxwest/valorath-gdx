package com.defectivemind.valorath;


import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {
	private static boolean loaded = false;
	
    public static AssetManager manager = new AssetManager();
    public static Skin menuSkin;

    public static Map<String, Texture> textures = new HashMap<String, Texture>();
    public static Map<String, TextureRegion[]> regions = new HashMap<String, TextureRegion[]>();
    
    // In here we'll put everything that needs to be loaded in this format:
    // manager.load("file location in assets", fileType.class);
    // 
    // libGDX AssetManager currently supports: Pixmap, Texture, BitmapFont,
    //     TextureAtlas, TiledAtlas, TiledMapRenderer, Music and Sound.
    public static void queueLoading () {
        manager.load("skins/menuSkin.pack", TextureAtlas.class);
        manager.load("tank.png", Texture.class);
        manager.load("knight_move.png", Texture.class);
    }
    
    // Load textures into texture array
    public static void loadTextures () {
        textures.put("player", manager.get("tank.png", Texture.class));
        
        Texture knightTexture = manager.get("knight_move.png", Texture.class);
        TextureRegion[] knightRegions = new TextureRegion[19];
        knightRegions[0] = new TextureRegion(knightTexture, 60, 16, 96, 96);
        
        regions.put("knightMoving", knightRegions);
    }
    
    // In here we'll create our skin, so we only have to create it once.
    public static void setMenuSkin () {
        if (menuSkin == null)
            menuSkin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
                    manager.get("skins/menuSkin.pack", TextureAtlas.class));
    }
    
    // This function gets called every render() and the AssetManager pauses the loading each frame
    // so we can still run menus and loading screens smoothly
    public static boolean update() {
    	boolean done = manager.update();
    	
    	if (done && !loaded) {
	    	Assets.setMenuSkin(); // uses files to create menuSkin
	        Assets.loadTextures(); // Load textures
	        
	        loaded = true;
    	}
    	
        return done;
    }
}
