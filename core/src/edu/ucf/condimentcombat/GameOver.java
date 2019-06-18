package edu.ucf.condimentcombat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class GameOver implements Screen {
    SpriteBatch batch;
    GameController myGame;
    Sprite background;
    Sprite gameover;
    Sprite player;
    Sprite numberOne;
    Sprite numberTwo;
    Sprite Wins;
    Sprite restart;
    OrthographicCamera camera;
    public GameOver(GameController g)
    {
        myGame = g;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Sprite(new Texture("ENDGAME.jpg"));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
player = new Sprite(new Texture("PLAYER.png"));
player.setPosition(300,400);
player.setSize(200,50);
numberOne = new Sprite(new Texture("one.png"));
numberTwo = new Sprite(new Texture("two.png"));
gameover = new Sprite(new Texture("OFFICIALGAMEOVER.png"));
gameover.setPosition(150, 400);
gameover.setSize(700, 200);
Wins = new Sprite(new Texture("WINS.png"));
Wins.setPosition(400,330);
Wins.setSize(200, 45);
restart = new Sprite(new Texture("officialplayagain.png"));
restart.setPosition(150, 200);
restart.setSize(700, 100);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 600);
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);

        if(Gdx.input.justTouched()) {
            Vector3 touched = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touched);
            if (touched.x > 150 && touched.x < 850 && touched.y > 200 && touched.y < 300) {
                myGame.setScreen(new MainMenu(myGame));
            }
        }
 batch.begin();
 background.draw(batch);
 player.draw(batch);
 Wins.draw(batch);
 gameover.draw(batch);
 restart.draw(batch);
 batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
