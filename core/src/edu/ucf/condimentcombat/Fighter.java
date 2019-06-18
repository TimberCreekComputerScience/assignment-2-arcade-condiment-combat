package edu.ucf.condimentcombat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

enum Direction {
    left, right;
}

public class Fighter {
    Sprite sprite;
    Rectangle hitbox;
    int health;
    private Vector2 velocity = new Vector2(0, 0);
    float acceleration = 25;
    int width = 90;
    int height = 250;
    boolean isJumping = false;
    float counter = 0;
    Bullet bullet;
    Projectile projectile;

    public Fighter(int x, int y, String img) {
        sprite = new Sprite(new Texture(img));
        hitbox = new Rectangle(x, y, width, height);
        sprite.setPosition(x, y);
        sprite.setSize(width, height);
        health = 10;
    }

    public void draw(SpriteBatch b) {
        sprite.draw(b);
    }

    public void update() {
        sprite.setPosition(hitbox.x, hitbox.y);

        if (isJumping) {
            jump(Gdx.graphics.getDeltaTime());
        }
    }

    public boolean doesHit(Rectangle check) {
        return check.overlaps(hitbox);
    }

    public void move(Direction dir) {
        if (dir == Direction.left) {
            hitbox.x-= 300 * Gdx.graphics.getDeltaTime();
        } else {
            hitbox.x+= 300 * Gdx.graphics.getDeltaTime();
        }
    }

    public void jump(float deltaTime) {
        counter += Gdx.graphics.getDeltaTime();

        float velocity;

        velocity = acceleration * (1 - deltaTime);

        if (counter > 0.3) {
            velocity *= -1;
        }

        if (hitbox.y <= 99) {
            hitbox.y = 100;
            isJumping = false;
            counter = 0;
        }

        hitbox.y += velocity;
    }

}
