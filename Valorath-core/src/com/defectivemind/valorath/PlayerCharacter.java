package com.defectivemind.valorath;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PlayerCharacter implements InputProcessor, GameObject {
	Sprite playerSprite;
	float speed = 250f;
	
	boolean isMoving = false;
	Vector2 moveTarget = new Vector2();
	
	public PlayerCharacter () {
		create();
	}
	
	// Set the player moving to a position that puts the sprite in the middle of the mouse
	public void moveTo(int x, int y) {
		isMoving = true;
		moveTarget.x = x - playerSprite.getWidth() / 2;
		moveTarget.y = y - playerSprite.getHeight() / 2;
	}
	
	@Override
	public void render (SpriteBatch sb) {
		playerSprite.draw(sb);
	}

	@Override
	public void update (float delta) {
		if (isMoving) {
			Vector2 playerPos = new Vector2(playerSprite.getX(), playerSprite.getY());
			Vector2 moveVect = moveTarget.cpy().sub(playerPos).clamp(0, speed * delta);
			
			playerSprite.translate(moveVect.x, moveVect.y);
			
			if (playerPos.equals(moveTarget)) isMoving = false;
		}
	}
	
	@Override
	public void destroy () {
		
	}
	
	@Override
	public void create () {
		playerSprite = new Sprite(Assets.textures.get("player"));
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		moveTo(screenX, Gdx.graphics.getHeight() - screenY);
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		moveTo(screenX, Gdx.graphics.getHeight() - screenY);
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
