package com.defectivemind.valorath;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {
	public void render (SpriteBatch sb);
	public void update (float delta);
	public void destroy ();
	public void create ();
}
