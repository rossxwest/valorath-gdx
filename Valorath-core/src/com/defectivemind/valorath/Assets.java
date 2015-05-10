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
        
        Texture knightMovingTexture = manager.get("knight_move.png", Texture.class);
        TextureRegion[] knightMovingRegions = new TextureRegion[19];
        knightMovingRegions[0] = new TextureRegion(knightMovingTexture, 60, 16, 96, 96);
        knightMovingRegions[1] = new TextureRegion(knightMovingTexture, 264, 16, 96, 96);
        knightMovingRegions[2] = new TextureRegion(knightMovingTexture, 468, 16, 96, 96);
        knightMovingRegions[3] = new TextureRegion(knightMovingTexture, 672, 16, 96, 96);
        knightMovingRegions[4] = new TextureRegion(knightMovingTexture, 880, 16, 96, 96);
        knightMovingRegions[5] = new TextureRegion(knightMovingTexture, 60, 200, 96, 96);
        knightMovingRegions[6] = new TextureRegion(knightMovingTexture, 264, 200, 96, 96);
        knightMovingRegions[7] = new TextureRegion(knightMovingTexture, 468, 200, 96, 96);
        knightMovingRegions[8] = new TextureRegion(knightMovingTexture, 672, 200, 96, 96);
        knightMovingRegions[9] = new TextureRegion(knightMovingTexture, 880, 200, 96, 96);
        knightMovingRegions[10] = new TextureRegion(knightMovingTexture, 60, 384, 96, 96);
        knightMovingRegions[11] = new TextureRegion(knightMovingTexture, 264, 384, 96, 96);
        knightMovingRegions[12] = new TextureRegion(knightMovingTexture, 468, 384, 96, 96);
        knightMovingRegions[13] = new TextureRegion(knightMovingTexture, 672, 384, 96, 96);
        knightMovingRegions[14] = new TextureRegion(knightMovingTexture, 880, 384, 96, 96);
        knightMovingRegions[15] = new TextureRegion(knightMovingTexture, 60, 568, 96, 96);
        knightMovingRegions[16] = new TextureRegion(knightMovingTexture, 264, 568, 96, 96);
        knightMovingRegions[17] = new TextureRegion(knightMovingTexture, 468, 568, 96, 96);
        knightMovingRegions[18] = new TextureRegion(knightMovingTexture, 672, 568, 96, 96);
        
        regions.put("knightMoving", knightMovingRegions);
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
