package com.gamedeuce.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gamedeuce.util.Constants;
import com.gamedeuce.util.InputHandler;
import com.gamedeuce.world.GameRenderer;
import com.gamedeuce.world.GameWorld;

/**
 * Created by Scott Cao on 2015/5/14.
 */
public class GameScreen implements Screen {

    private GameWorld world;

    private GameRenderer renderer;

    private float runTime;

    private Viewport viewport;

    private Camera camera;

    public GameScreen() {
//        float screenWidth = Gdx.graphics.getWidth();
//        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = Constants.APP_WIDTH;
        float gameHeight = Constants.APP_HEIGHT;

//        Gdx.app.log("screenWidth", ""+screenWidth);
//        Gdx.app.log("screenHeight", ""+screenHeight);
//        Gdx.app.log("gameWidth", ""+gameWidth);
//        Gdx.app.log("gameHeight", ""+gameHeight);

        runTime = 0;

        world = new GameWorld(gameWidth, gameHeight);
        renderer = new GameRenderer(world, gameWidth, gameHeight);

        camera = renderer.getCamera();

        viewport = new FitViewport(gameWidth, gameHeight, renderer.getCamera());
        viewport.apply();

        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);

        Gdx.input.setInputProcessor(new InputHandler(gameWidth, gameHeight, world));
    }

    @Override
    public void render(float delta) {
//        Gdx.app.log("GameScreen", "render");
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
