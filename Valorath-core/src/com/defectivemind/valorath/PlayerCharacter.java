package com.defectivemind.valorath;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerCharacter implements GameObject {
	TextureRegion[] knightMoving;
	int textureIndex = 0;
	
	float speed = 250f;
	float currDelta = 0;
	
	Vector2 position;
	float rotation = 0.0f;
	
	int height = 96;
	int width = 96;
	
	boolean isMoving = false;
	Vector2 moveTarget = new Vector2();
	
	GameMap map;
	
	public PlayerCharacter () {
		create();
	}
	
	// Set the player moving to a position that puts the sprite in the middle of the mouse
	public void moveTo (Vector2 pos) {
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
	
	@Override
	public void render (SpriteBatch sb) {
		sb.draw(getCurrentTextureRegion()[textureIndex], position.x, position.y, width / 2, height / 2, width, height, 1, 1, rotation);
	}

	@Override
	public void update (float delta) {
		currDelta += delta;

		if (currDelta > (10 / speed)) {
			currDelta = 0;
			incrementTextureIndex();
		}
		
		if (isMoving) {
			Vector2 moveVect = moveTarget.cpy().sub(position).clamp(0, speed * delta);
			
			if (position == moveTarget || map.checkCollision(getRectangle(moveVect))) {
				isMoving = false;
			} else {
				position.add(moveVect);
			}
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
	
	// Get rectangle for collision checking
	public Rectangle getRectangle() {
		return new Rectangle(position.x, position.y, width, height);
	}
	
	public Rectangle getRectangle(Vector2 add) {
		return new Rectangle(position.x + add.x, position.y + add.y, width, height);
	}
	
	public void setMap (GameMap m) {
		map = m;
	}
	
	// Show next animation frame
	private void incrementTextureIndex () {
		textureIndex++;
		
		if (textureIndex == getCurrentTextureRegion().length) {
			textureIndex = 0;
		} 
	}
	
	// Get the current animation
	private TextureRegion[] getCurrentTextureRegion () {
		return knightMoving;
	}
}
