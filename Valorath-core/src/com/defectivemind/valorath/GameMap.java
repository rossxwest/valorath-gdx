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
		
		TiledMapTileLayer ground = new TiledMapTileLayer(4096, 4096, 64, 64);
		TiledMapTileLayer walls = new TiledMapTileLayer(4096, 4096, 64, 64);
		
		groundTexture = new Texture(Gdx.files.internal("grass.png"));
		groundRegion = new TextureRegion(groundTexture, 0, 0, 64, 64);
		
		wallTexture = new Texture(Gdx.files.internal("water.png"));
		wallRegion = new TextureRegion(wallTexture, 0, 0, 64, 64);
		
		Cell groundCell = new Cell();
		groundCell.setTile(new StaticTiledMapTile(groundRegion));
		
		Cell wallCell = new Cell();
		wallCell.setTile(new StaticTiledMapTile(wallRegion));
		
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				ground.setCell(i,  j, groundCell);
				
				if (i == 0 || j == 0 || i == 63 || j == 63) {
					walls.setCell(i,  j, wallCell);
				}
			}
		}
		
		layers.add(ground);
		layers.add(walls);
		
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
	}

}
