package edu.ucf.condimentcombat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import edu.ucf.condimentcombat.GameController;

public class MainMenu implements Screen {
    SpriteBatch batch;
    GameController myGame;
    Sprite sprite;
    Sprite player1;
    Sprite player2;
    OrthographicCamera camera;
    boolean changing;

    public MainMenu(GameController g)
    {
        myGame = g;

    }

    @Override
    public void show() {
      batch = new SpriteBatch();
     sprite = new Sprite(new Texture("mainmenu.jpg"));
     sprite.setSize(1000, 600);
     sprite.setPosition(0,0);
     player1 = new Sprite(new Texture("BUTTON.png"));
     player1.setSize(400, 100);
     player1.setPosition(80, 180);
     player2 = new Sprite(new Texture("BUTTON2.png"));
     player2.setSize(400, 100);
     player2.setPosition(550,180);
     camera = new OrthographicCamera();
     camera.setToOrtho(false, 1000, 600);
    }

    @Override
    public void render(float delta) {

       //batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
       player1.draw(batch);
       player2.draw(batch);
        batch.end();
        if(Gdx.input.justTouched()){
            changing = true;
            Vector3 touched = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touched);
            if(touched.x > 80 && touched.x < 480 && touched.y > 180 && touched.y < 280)
            {
                myGame.setScreen(new MainGame(myGame));
            }
        }

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
