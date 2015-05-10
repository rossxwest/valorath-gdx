package com.defectivemind.valorath;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class PlayerInput implements InputProcessor {
	PlayerCharacter player;
	OrthographicCamera camera;
	
	boolean mouse0Down = false;
	boolean mouse1Down = false;
	
	public PlayerInput (OrthographicCamera c, PlayerCharacter p) {
		camera = c;
		player = p;
	}
	
	@Override
	public boolean keyDown (int keycode) {
		return false;
	}

	@Override
	public boolean keyUp (int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		return false;
	}

	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		if (button == 0) {
			mouse0Down = true;
			player.moveTo(toWorld(new Vector2(screenX, screenY)));
		} else if (button == 1) {
			mouse1Down = true;
			player.faceTarget(toWorld(new Vector2(screenX, screenY)));
			player.stopMoving();
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
			player.moveTo(toWorld(new Vector2(screenX, screenY)));
		} else if (mouse1Down) {
			player.faceTarget(toWorld(new Vector2(screenX, screenY)));
			player.stopMoving();
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved (int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		return false;
	}
	
	// Used to translate screen coords to world coords
	private Vector2 toWorld (Vector2 v) {
		Vector3 tmp = new Vector3(v.x, v.y, 0);
		camera.unproject(tmp);
		
		return new Vector2 (tmp.x, tmp.y);
	}
}
