package com.defectivemind.valorath;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PlayerCharacter implements InputProcessor, GameObject {
	Sprite playerSprite;
	float speed = 250f;
	
	boolean isMoving = false;
	Vector2 moveTarget = new Vector2();
	
	boolean mouse0Down = false;
	boolean mouse1Down = false;
	
	Camera camera;
	
	public PlayerCharacter () {
		create();
	}
	
	// Set the player moving to a position that puts the sprite in the middle of the mouse
	public void moveTo(Vector2 pos) {
		isMoving = true;
		moveTarget.x = pos.x - playerSprite.getWidth() / 2;
		moveTarget.y = pos.y - playerSprite.getHeight() / 2;
		
		faceTarget(moveTarget);
	}
	
	public void stopMoving () {
		isMoving = false;
	}
	
	public void faceTarget (Vector2 target) {
		Vector2 playerPos = new Vector2(playerSprite.getX(), playerSprite.getY());
		Vector2 moveVect = target.cpy().sub(playerPos);
		
		// 1 radian = ~57.3 degrees
		float rot = (float)Math.atan2(moveVect.y, moveVect.x) * 57.3f;
		
		playerSprite.setRotation(rot - 90);
	}
	
	public Vector2 getPosition () {
		return new Vector2(playerSprite.getX() + playerSprite.getWidth() / 2, playerSprite.getY() + playerSprite.getHeight() / 2);
	}
	
	public void setCamera (Camera cam) {
		camera = cam;
	}
	
	// Used to translate screen coords to world coords
	public Vector2 toWorld (Vector2 v) {
		Vector3 tmp = new Vector3(v.x, v.y, 0);
		camera.unproject(tmp);
		
		return new Vector2 (tmp.x, tmp.y);
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
		playerSprite.setPosition(500, 500);
	}
	
	@Override
	public boolean keyDown (int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp (int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		if (button == 0) {
			mouse0Down = true;
			moveTo(toWorld(new Vector2(screenX, screenY)));
		} else if (button == 1) {
			mouse1Down = true;
			faceTarget(toWorld(new Vector2(screenX, screenY)));
			stopMoving();
		}
		
		return false;
	}

	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		if (button == 0) {
			mouse0Down = false;
		} else if (button == 1) {
			mouse1Down = false;
		}
		
		return false;
	}

	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		if (mouse0Down) {
			moveTo(toWorld(new Vector2(screenX, screenY)));
		} else if (mouse1Down) {
			faceTarget(toWorld(new Vector2(screenX, screenY)));
			stopMoving();
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved (int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
