package com.defectivemind.valorath;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenu implements Screen {

    private Stage stage = new Stage();
    private Table table = new Table();

    private Skin skin = Assets.menuSkin;

    private TextButton buttonPlay;
    private TextButton buttonOptions;
    private TextButton buttonExit;

    public MainMenu () {
    	buttonPlay = new TextButton("Play", skin);
    	buttonOptions = new TextButton("Options", skin);
    	buttonExit = new TextButton("Exit", skin);
    	
    	buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	((Game)Gdx.app.getApplicationListener()).setScreen(new ValorathScreen());
            }
        });
    	
    	buttonOptions.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	((Game)Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());
            }
        });
    	
    	buttonExit.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    	
    }

    @Override
    public void show() {
        //The elements are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        table.add(buttonPlay).row();
        table.add(buttonOptions).row();
        table.add(buttonExit).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
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
        stage.dispose();
    }

}
