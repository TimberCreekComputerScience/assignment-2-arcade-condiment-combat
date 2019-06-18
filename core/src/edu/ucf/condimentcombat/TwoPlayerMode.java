package edu.ucf.condimentcombat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class TwoPlayerMode implements Screen {
    SpriteBatch batch;
    GameController myGame;
    Sprite background;
   Fighter ketchup;
    Fighter mustard;
    ArrayList<Bullet> bullets;
    ArrayList<Projectiles> projectiles;


    Sprite hbar1, hbar2;

    public TwoPlayerMode(GameController g) {
        myGame = g;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Sprite(new Texture("ENDGAME.jpg"));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

       ketchup = new Fighter(200, 100, "mrketchup.png");
       mustard = new Fighter( 700, 100, "msmustard.png");
        bullets = new ArrayList<Bullet>();
        projectiles = new ArrayList<Projectiles>();

        hbar1 = new Sprite(new Texture("FULLHEALTHBAR.png"));
        hbar2 = new Sprite(new Texture("FULLHEALTHBAR.png"));
    }

    @Override
    public void render(float delta) {
        batch.begin();
        background.draw(batch);
        hbar1.setPosition(70, 20);
        hbar1.setSize(ketchup.health * 30, 45);
        hbar1.draw(batch);

        hbar2.setPosition(650, 20);
        hbar2.setSize(mustard.health * 30, 45);
        hbar2.draw(batch);

        ketchup.draw(batch);
        mustard.draw(batch);

        for (Bullet b : bullets) {
            b.draw(batch);
        }

        for (Projectiles p : projectiles) {
            p.draw(batch);
        }

        batch.end();

        ketchup.update();
        mustard.update();
//health
        if (ketchup.health == 0) {
            ketchup.sprite.setAlpha(0f);
        }

        if (mustard.health == 0) {
            mustard.sprite.setAlpha(0f);
        }
        //bullets
        checkBullets:
        for (int j = 0; j < bullets.size(); j++) {
            bullets.get(j).update(Gdx.graphics.getDeltaTime());

            if (ketchup.doesHit(bullets.get(j).hitBox) && bullets.get(j) != ketchup.bullet) {
                bullets.remove(j);
                mustard.bullet = null;
                ketchup.health--;
                //System.out.println("HILJF");
                j--;
                continue checkBullets;
            }
            if (mustard.doesHit(bullets.get(j).hitBox) && bullets.get(j) != mustard.bullet) {
                bullets.remove(j);
                ketchup.bullet = null;
                mustard.health--;
                //System.out.println("HILJF");
                j--;
                continue checkBullets;
            }

            if (bullets.get(j).position.x > 1000) {
                bullets.remove(j);
                j--;
                ketchup.bullet = null;
                continue checkBullets;
            }

            if (bullets.get(j).position.x < 0) {
                bullets.remove(j);
                j--;
                mustard.bullet = null;
                continue checkBullets;
            }
        }
//projectiles
        checkProjectiles:
        for (int j = 0; j < projectiles.size(); j++) {
            projectiles.get(j).update(Gdx.graphics.getDeltaTime());

            if (ketchup.doesHit(projectiles.get(j).hitbox) && projectiles.get(j) != ketchup.projectile) {
                projectiles.remove(j);
                mustard.projectile = null;
                ketchup.health--;
                j--;
                continue checkProjectiles;
            }
            if (mustard.doesHit(projectiles.get(j).hitbox) && projectiles.get(j) != mustard.projectile) {
                projectiles.remove(j);
                ketchup.projectile = null;
                mustard.health--;
                j--;
                continue checkProjectiles;
            }

            if (projectiles.get(j).hitbox.x > 1000) {
                projectiles.remove(j);
                j--;
                ketchup.projectile = null;
                continue checkProjectiles;
            }

            if (projectiles.get(j).hitbox.x < 0) {
                projectiles.remove(j);
                j--;
                mustard.projectile = null;
                continue checkProjectiles;
            }
        }


        //ketchup
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (ketchup.hitbox.x > 0) {
                ketchup.move(Direction.left);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (ketchup.hitbox.x < (Gdx.graphics.getWidth() / 2 - ketchup.width)) {
                ketchup.move(Direction.right);
            }
        }
        if (Gdx.input.isKeyPressed(Input
                .Keys.UP)) {
            ketchup.isJumping = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (ketchup.bullet == null && !ketchup.isJumping && ketchup.projectile == null) {
                Bullet bullet = new Bullet((int) ketchup.hitbox.x, (int) ketchup.hitbox.y + ketchup.height / 3, "bullet.png", new Vector2(1050, 0));
                ketchup.bullet = bullet;
                bullets.add(bullet);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (ketchup.projectile == null && !ketchup.isJumping && ketchup.bullet == null) {
                int size = (int) (Math.random() * 50) + 25;
                float push = mustard.hitbox.x - ketchup.hitbox.x;
                Projectiles projectile = new Projectiles((int) ketchup.hitbox.x, (int) ketchup.hitbox.y + ketchup.height / 3, size, "bullet.png", push);
                ketchup.projectile = projectile;
                projectiles.add(projectile);
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
