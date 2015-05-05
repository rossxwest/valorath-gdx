package com.defectivemind.valorath;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class OptionsScreen implements Screen {
    private Stage stage = new Stage();
    private Table table = new Table();

    private Skin skin = Assets.menuSkin;
    
    private TextButton buttonDisplay;
    private TextButton buttonResolution;
    private TextButton buttonApply;
    private TextButton buttonBack;

    private String[] displayModes = {"Fullscreen", "Windowed", "Borderless Windowed"};
    
    public OptionsScreen () {
    	SaveManager sm = ((Valorath) Gdx.app.getApplicationListener()).saveManager;
    	
    	String displayMode = "Fullscreen";
    	
    	if (sm.loadDataValue("displayMode", String.class) != null) {
    		displayMode = sm.loadDataValue("displayMode", String.class);
    	}
    	
    	String resolution = "1366 x 768";
    	
    	if (sm.loadDataValue("resolution", String.class) != null) {
    		resolution = sm.loadDataValue("resolution", String.class);
    	}
    	
    	buttonDisplay = new TextButton(displayMode, skin);
    	buttonResolution = new TextButton(resolution, skin);
    	buttonApply = new TextButton("Apply", skin);
    	buttonBack = new TextButton("Back", skin);
    	
    	buttonDisplay.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	String current = ((TextButton)actor).getText().toString();
            	
            	// Find the location of the current display mode in the array and change it to the next one
            	for (int i = 0; i < displayModes.length; i++) {
            		if (current.equals(displayModes[i])) {
            			if (i + 1 == displayModes.length) {
            				current = displayModes[0]; 
            			} else {
            				current = displayModes[i + 1];
            			}
            			
            			break;
            		}
            	}
            	
            	((TextButton)actor).setText(current);
            }
        });
    	
    	buttonResolution.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	String current = ((TextButton)actor).getText().toString();
            	
            	String[] resolutions = Resolutions.getOptions();
            	
            	// Find the location of the current resolution in the array and change it to the next one
            	for (int i = 0; i < resolutions.length; i++) {
            		if (current.equals(resolutions[i])) {
            			if (i + 1 == resolutions.length) {
            				current = resolutions[0]; 
            			} else {
            				current = resolutions[i + 1];
            			}
            			
            			break;
            		}
            	}
            	
            	((TextButton)actor).setText(current);
            }
        });
    	
    	buttonApply.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	OptionsScreen options = (OptionsScreen) ((Game)Gdx.app.getApplicationListener()).getScreen();
            	options.applySettings();
            }
        });
    	
    	buttonBack.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            	((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
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
    	table.add(buttonDisplay).row();
    	table.add(buttonResolution).row();
    	table.add(buttonApply).row();
        table.add(buttonBack).row();

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
    
    public void applySettings() {
    	// Get resolution
    	Vector2 newRes = Resolutions.toVector(buttonResolution.getText().toString());
    	
    	// Determine display mode
    	String displayMode = buttonDisplay.getText().toString();
    	
    	Resolutions.setResolution(newRes, displayMode);
    	
    	// Save options to file
    	SaveManager sm = ((Valorath) Gdx.app.getApplicationListener()).saveManager;
    	
    	sm.saveDataValue("displayMode", displayMode);
    	sm.saveDataValue("resolution", buttonResolution.getText().toString());
    	
    	// Redraw the options screen
    	((Game)Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());
    }
}
