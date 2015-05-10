package com.defectivemind.valorath;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Rectangle;

public class GameMap implements GameObject {
    TiledMap map;
    MapLayers layers;
    TiledMapRenderer tiledMapRenderer;
    OrthographicCamera cam;
    
    Cell groundCell;
    Cell wallCell;
    
    Texture groundTexture;
    Texture wallTexture;
    
    TextureRegion groundRegion;
    TextureRegion wallRegion;
    
    int width = 64;
    int height = 64;
    
    int tileWidth = 64;
    int tileHeight = 64;
    
	public GameMap (OrthographicCamera camera) {
		cam = camera;
		create();
	}
	
	@Override
	public void render(SpriteBatch sb) {
        tiledMapRenderer.setView(cam);
        tiledMapRenderer.render();
	}

	@Override
	public void update(float delta) {
				
	}

	@Override
	public void destroy() {

	}

	@Override
	public void create() {
		map = new TiledMap();
		layers = map.getLayers();
		
		TiledMapTileLayer ground = new TiledMapTileLayer(width * tileWidth, height * tileHeight, tileWidth, tileHeight);
		TiledMapTileLayer walls = new TiledMapTileLayer(width * tileWidth, height * tileHeight, tileWidth, tileHeight);
		
		groundTexture = new Texture(Gdx.files.internal("grass.png"));
		groundRegion = new TextureRegion(groundTexture, 0, 0, 64, 64);
		
		wallTexture = new Texture(Gdx.files.internal("water.png"));
		wallRegion = new TextureRegion(wallTexture, 0, 0, 64, 64);
		
		Cell groundCell = new Cell();
		groundCell.setTile(new StaticTiledMapTile(groundRegion));
		
		Cell wallCell = new Cell();
		wallCell.setTile(new StaticTiledMapTile(wallRegion));
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				ground.setCell(i,  j, groundCell);
				
				if (i == 0 || j == 0 || i == width - 1 || j == height - 1) {
					walls.setCell(i,  j, wallCell);
				}
			}
		}
		
		layers.add(ground);
		layers.add(walls);
		
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
	}

	public boolean checkCollision(Rectangle rect) {
		// figure out which tiles to check for collision
		int lowerX = (int)rect.x / tileWidth;
		int lowerY = (int)rect.y / tileHeight;
		
		int upperX = (int)(rect.x + rect.width)/ tileWidth;
		int upperY = (int)(rect.y + rect.height)/ tileHeight;
		
		TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(1);
		
		for (int x = lowerX; x < upperX; x++) {
			for (int y = lowerY; y < upperY; y++) {
				if (layer.getCell(x, y) != null) {
					return true;
				}
			}
		}
		
		return false;
	}
}
