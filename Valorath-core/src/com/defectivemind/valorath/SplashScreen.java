package com.defectivemind.valorath;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreen implements Screen {
	private Texture texture;
    private Image splashImage;
    private Stage stage = new Stage();
    private Screen next;

    public boolean animationDone = false;
    
	SplashScreen (Texture t) {
    	texture = t;
    	splashImage = new Image(texture);
    }
    
    @Override
    public void render(float delta) {    
    	Gdx.gl.glClearColor(0,0,0,1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
        stage.draw(); //draw all actors on the Stage.getBatch()
        
        if (Assets.update()) { // check if all files are loaded
            if (animationDone) { // when the animation is finished, go to MainMenu()
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
            }
        }
    }

    @Override
    public void resize(int width, int height) {    
    	
    }

    @Override
    public void show() {       
        splashImage.setHeight(Valorath.HEIGHT);
        splashImage.setWidth(Valorath.WIDTH);
    	stage.addActor(splashImage); //adds the image as an actor to the stage
    	
    	splashImage.addAction(
    		Actions.sequence(
    			Actions.alpha(0),
    			Actions.delay(1),
    			Actions.fadeIn(0.5f),
    			Actions.delay(2),
    			Actions.fadeOut(0.5f),
    			Actions.run(
    				new Runnable() {
    					@Override
    					public void run() {
    						animationDone = true;
    					}
    				}
    			)
    		)
    	);
    	
    	Assets.queueLoading(); 
    }

    @Override
    public void hide() {       
    	dispose();
    }

    @Override
    public void pause() {     
    	
    }

    @Override
    public void resume() {    
    	
    }

    @Override
    public void dispose() {    
        texture.dispose();
        stage.dispose();
    }
    
    public Screen getNext() {
		return next;
	}

	public void setNext(Screen n) {
		next = n;
	}
}
