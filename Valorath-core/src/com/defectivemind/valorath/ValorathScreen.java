package com.defectivemind.valorath;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class ValorathScreen implements Screen {
	OrthographicCamera camera;
	SpriteBatch sb;
    PlayerCharacter player;
    float fullDelta = 0;
    
    GameMap map;
	
	public ValorathScreen () {
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        
        sb = new SpriteBatch();
        
        player = new PlayerCharacter();
        player.setCamera(camera);
        
        Gdx.input.setInputProcessor(player);
        
        map = new GameMap(camera);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {        
    	Gdx.gl.glClearColor(0, 0, 0, 1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
		
        Vector3 camPos = camera.position;
        Vector2 playerPos = player.getPosition();
        camera.translate(playerPos.x - camPos.x, playerPos.y - camPos.y);
        
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        
        fullDelta += delta;
         
        if (fullDelta >= 1/60) {
        	player.update(fullDelta);
        	fullDelta = 0;
        }
        
        map.render(sb);
        
        sb.begin();
        player.render(sb);
        sb.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
