package com.gamedeuce.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamedeuce.objects.Ball;
import com.gamedeuce.objects.Obstacle;
import com.gamedeuce.objects.ObstaclePair;
import com.gamedeuce.util.AssetLoader;
import com.gamedeuce.util.Constants;

/**
 * Created by Scott Cao on 2015/5/14.
 */
public class GameRenderer {

    private float gameWidth;
    private float gameHeight;

    private GameWorld world;

    // Project game in 3D space
    private OrthographicCamera camera;

    private SpriteBatch batch;

    private Ball ball1;
    private Ball ball2;

    private ObstaclePair[] obstaclePairs;

    // Assets
    private Texture blackBall;
    private Texture whiteBall;
    private Texture blackObstacle;
    private Texture whiteObstacle;
    private Texture ready;
    private Texture tapToStart;
    private Texture gameOver;
    private Texture highScore;
    private Texture retry;
    private TextureRegion logo;
    private Texture background;
    private BitmapFont font;
    private BitmapFont shadow;

    public GameRenderer(GameWorld w, float gw, float gh) {
        gameWidth = gw;
        gameHeight = gh;
        world = w;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        batch = new SpriteBatch();
        // Attach batch to camera
        batch.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    public void render(float runTime) {
        // Draw a black background. This prevents flickering.
        Gdx.gl.glClearColor(0 / 255f, 191 / 255f, 255 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin SpriteBatch
        batch.begin();

        batch.setProjectionMatrix(camera.combined);

        drawBackground();

        if (world.isReady()) {
            drawReady();
        } else {
            drawObstaclePairs();
            drawBalls();
            drawScore(gameHeight / 18);

            if (world.isGameOver()) {
                drawGameOver();
            }
        }

        // End SpriteBatch
        batch.end();
    }

    // Draw each pair of obstacles
    private void drawObstaclePairs() {
        for (ObstaclePair op : obstaclePairs) {
            if (!op.tooHigh()) {
                Obstacle obs1 = op.getObstacle1();
                Obstacle obs2 = op.getObstacle2();
                if (obs1.drawable(ball1)) {
                    drawObstacle(obs1);
                }
                if (obs2.drawable(ball2)) {
                    drawObstacle(obs2);
                }
            }
        }
    }

    private void drawString(String str, float y) {
        shadow.draw(batch, str, gameWidth / 2 - (4 * str.length()), y);
        font.draw(batch, str, gameWidth / 2 - (4 * str.length() - 1), y - 1);
    }

    private void drawReady() {
//        drawString("READY?", gameHeight / 2 - 200);
//        drawString("TAP TO START", gameHeight / 2 + 140);
        batch.draw(logo,
                (gameWidth - logo.getRegionWidth()) / 2, gameHeight / 4 - logo.getRegionHeight() / 2,
                logo.getRegionWidth(), logo.getRegionHeight());
        batch.draw(ready, gameWidth / 2 - ready.getWidth() / 2,
                gameHeight / 2 - ready.getHeight() / 2,
                ready.getWidth(), ready.getHeight());
        batch.draw(tapToStart, gameWidth / 2 - tapToStart.getWidth() / 2,
                3 * gameHeight / 4 - tapToStart.getHeight() / 2,
                tapToStart.getWidth(), tapToStart.getHeight());
    }

    private void drawGameOver() {
//        drawString("GAME OVER", gameHeight / 2 - 200);
//        drawString("HIGH SCORE: " + AssetLoader.getHighScore(), gameHeight /2 - 80);
//        drawString("TAP TO RESTART", gameHeight / 2 + 140);
//        batch.draw(logo, (gameWidth - logo.getWidth()) / 2, (gameHeight - logo.getHeight()) / 2,
//                logo.getWidth(), logo.getHeight());
        batch.draw(gameOver, gameWidth / 2 - gameOver.getWidth() / 2,
                2 * gameHeight / 11 - gameOver.getHeight() / 2,
                gameOver.getWidth(), gameOver.getHeight());
        batch.draw(highScore, gameWidth / 2 - highScore.getWidth() / 2 ,
                3 * gameHeight / 7 - highScore.getHeight() / 2,
                highScore.getWidth(), highScore.getHeight());
        batch.draw(retry, gameWidth / 2 - retry.getWidth() / 2,
                5 * gameHeight / 7 - retry.getHeight() / 2,
                retry.getWidth(), retry.getHeight());
        drawString("" + AssetLoader.getHighScore(), gameHeight / 2 - 15);
    }

//    private void drawLogo() {
//        batch.draw(logo, (gameWidth - logo.getWidth()) / 2, (gameHeight - logo.getHeight()) / 2,
//                logo.getWidth(), logo.getHeight());
//    }

    private void drawScore(float yPos) {
        drawString("" + world.getScore(), yPos);
    }

    // individual helper method for drawObstacles()
    private void drawObstacle(Obstacle obs) {
        if (obs.getType() == Constants.TYPE_BLACK) {
            batch.draw(blackObstacle, obs.getX(), obs.getY(), obs.getWidth(), obs.getHeight());
        } else {
            batch.draw(whiteObstacle, obs.getX(), obs.getY(), obs.getWidth(), obs.getHeight());
        }
    }

    // Draw both balls at their current coordinates
    private void drawBalls() {
        if (ball1.getType() == Constants.TYPE_BLACK) {
            drawBall(blackBall, ball1);
            drawBall(whiteBall, ball2);
        } else {
            drawBall(whiteBall, ball1);
            drawBall(blackBall, ball2);
        }
    }

    // individual helper method for drawBalls()
    private void drawBall(Texture texture, Ball ball) {
        batch.draw(texture, ball.getX(), ball.getY(), 2 * ball.getRadius(), 2 * ball.getRadius());
    }

    private void drawBackground() {
        batch.draw(background, 0, 0, gameWidth, gameHeight);
    }

    private void initGameObjects() {
        ball1 = world.getBall1();
        ball2 = world.getBall2();
        obstaclePairs = world.getScroller().getObstaclePairs();
    }

    private void initAssets() {
        blackBall = AssetLoader.blackBall;
        whiteBall = AssetLoader.whiteBall;
        blackObstacle = AssetLoader.blackObstacle;
        whiteObstacle = AssetLoader.whiteObstacle;
        logo = AssetLoader.logo;
        font = AssetLoader.font;
        shadow = AssetLoader.shadow;
        ready = AssetLoader.ready;
        tapToStart = AssetLoader.tapToStart;
        gameOver = AssetLoader.gameOver;
        highScore = AssetLoader.highScore;
        retry = AssetLoader.retry;
        background = AssetLoader.background;
    }

    public Camera getCamera() {
        return camera;
    }

}
