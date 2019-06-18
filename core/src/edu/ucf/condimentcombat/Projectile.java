package edu.ucf.condimentcombat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {

    private Sprite sprite;
    public Rectangle hitbox;
    private Vector2 velocity;
    private static float acceleration = -200;
    private float angle;

    public Projectile(int x, int y, int size, String img, float push) {
        hitbox = new Rectangle(x, y, size, size);
        sprite = new Sprite(new Texture(img));
        velocity = new Vector2(push, 200);
        sprite.setPosition(x, y);
        sprite.setSize(size, size);
        sprite.setOriginCenter();
        angle = (float)Math.random()*10-5;
    }

    public void update(float deltaTime) {
        hitbox.x += velocity.x * deltaTime;
        hitbox.y += velocity.y * deltaTime;

        velocity.y += acceleration * deltaTime;

        sprite.setPosition(hitbox.x, hitbox.y);

        sprite.rotate(angle);
    }

    public void draw(SpriteBatch b) {
        sprite.draw(b);
    }
}
