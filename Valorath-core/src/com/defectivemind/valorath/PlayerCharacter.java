package com.defectivemind.valorath;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PlayerCharacter implements InputProcessor, GameObject {
	TextureRegion[] knightMoving;
	float speed = 250f;
	
	Vector2 position;
	float rotation = 0.0f;
	
	int height = 96;
	int width = 96;
	
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
		moveTarget.x = pos.x - width / 2;
		moveTarget.y = pos.y - height / 2;
		
		faceTarget(moveTarget);
	}
	
	public void stopMoving () {
		isMoving = false;
	}
	
	public void faceTarget (Vector2 target) {
		Vector2 moveVect = target.cpy().sub(position);
		
		// 1 radian = ~57.3 degrees
		rotation = (float)Math.atan2(moveVect.y, moveVect.x) * 57.3f + 90;
	}
	
	public Vector2 getPosition () {
		return new Vector2(position.x + width / 2, position.y + height / 2);
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
		sb.draw(knightMoving[0], position.x, position.y, width / 2, height / 2, width, height, 1, 1, rotation);
	}

	@Override
	public void update (float delta) {
		if (isMoving) {
			Vector2 moveVect = moveTarget.cpy().sub(position).clamp(0, speed * delta);
			
			position.add(moveVect);
			
			if (position == moveVect) isMoving = false;
		}
	}
	
	@Override
	public void destroy () {
		
	}
	
	@Override
	public void create () {
		knightMoving = Assets.regions.get("knightMoving");
		position = new Vector2(500, 500);
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
