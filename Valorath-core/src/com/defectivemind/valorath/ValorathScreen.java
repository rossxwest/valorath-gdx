package com.defectivemind.valorath;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ValorathScreen implements Screen {
	OrthographicCamera camera;
	SpriteBatch sb;
    PlayerCharacter player;
    float fullDelta = 0;
	
	public ValorathScreen () {
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        
        sb = new SpriteBatch();
        
        player = new PlayerCharacter();
        
        Gdx.input.setInputProcessor(player);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
    	Gdx.gl.glClearColor(0, 0.5f, 0, 1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        camera.update();
        
        fullDelta += delta;
         
        if (fullDelta >= 1/60) {
        	player.update(fullDelta);
        	fullDelta = 0;
        }
        
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
