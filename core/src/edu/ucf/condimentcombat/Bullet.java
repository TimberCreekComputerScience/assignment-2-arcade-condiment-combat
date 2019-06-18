package edu.ucf.condimentcombat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    Vector2 position, velocity;
    Sprite sprite;
    public Rectangle hitBox;
    int speed = 200;
    int width = 50;
    int height = 50;

    public Bullet(int x, int y, String img, Vector2 target) {
        position = new Vector2(x, y);
        sprite = new Sprite(new Texture(img));
        hitBox = new Rectangle(x, y, width, height);
        sprite.setPosition(x, y);
        sprite.setSize(width, height);

        float deltaX = target.x - position.x;

        velocity = new Vector2(deltaX / 2, 0);
    }

    public void update(float deltaTime) {
        position.x += velocity.x * deltaTime;
        sprite.setPosition(position.x, position.y);
        hitBox.x = (int)position.x;
    }

    public void draw(SpriteBatch b) {
        sprite.draw(b);
    }
}
