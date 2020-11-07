package com.kingsman.dungeon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.Playscreen;

public class Dungeon extends Game {
	public SpriteBatch batch;
	public static boolean Win = false;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Playscreen(this));
	}

	@Override
	public void render () {
		super.render();
		
	}
	

}
