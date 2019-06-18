package edu.ucf.condimentcombat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame implements Screen
{
    SpriteBatch batch;
    GameController myGame;
    boolean changing;

public MainGame(GameController g){

    myGame = g;


}
    @Override
    public void show() {
    batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

        batch.begin();
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
