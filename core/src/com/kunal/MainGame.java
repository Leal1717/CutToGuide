package com.kunal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kunal.temp.temp;

public class MainGame extends Game {

	@Override
	public void create () {
	    AllVariables.batch = new SpriteBatch();
		AllVariables.inpM = (float)Gdx.graphics.getHeight()/AllVariables.HEIGHT;
		AllVariables.witdth_translation = (Gdx.graphics.getWidth() - ((Gdx.graphics.getHeight()*16)/9))/2;
		AllVariables.bitmapFont = new BitmapFont(Gdx.files.internal("font/font.fnt"));

		this.setScreen(new temp(this));

	}

	@Override
	public void render () {
	    super.render();
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	}

	@Override
	public void dispose () {
		AllVariables.batch.dispose();
	}
}
