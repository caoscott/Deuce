package com.gamedeuce.game;

/**
 * Created by Avondale on 12/15/2015.
 */
import com.badlogic.gdx.Game;
import com.gamedeuce.screens.GameScreen;
import com.gamedeuce.util.AssetLoader;

public class Deuce extends Game {

	@Override
	public void create () {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
