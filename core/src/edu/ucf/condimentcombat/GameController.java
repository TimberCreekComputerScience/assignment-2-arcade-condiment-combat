package edu.ucf.condimentcombat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Iterator;

public class GameController extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Sprite background;

	Fighter ketchup;
	Fighter mustard;

	ArrayList<Bullet> bullets;
	ArrayList<Projectile> projectiles;

	Sprite hbar1, hbar2;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 600);


		background = new Sprite(new Texture("table.jpg"));

		ketchup = new Fighter(200, 100, "mrketchup.png");

		mustard = new Fighter(800, 100, "mrsmustard.png");

		bullets = new ArrayList<Bullet>();
		projectiles = new ArrayList<Projectile>();

		hbar1 = new Sprite(new Texture("bullet.png"));
		hbar2 = new Sprite(new Texture("bullet.png"));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		background.draw(batch);

		hbar1.setPosition(10, 10);
		hbar1.setSize(ketchup.health * 50, 75);
		hbar1.draw(batch);

		hbar2.setPosition(10, 10);
		hbar2.setSize(mustard.health * 50, 75);
		hbar2.draw(batch);

		ketchup.draw(batch);
		mustard.draw(batch);

		for (Bullet b : bullets) {
			b.draw(batch);
		}

		for (Projectile p : projectiles) {
			p.draw(batch);
		}

		batch.end();

		ketchup.update();
		mustard.update();

		if (ketchup.health == 0) {
			ketchup.sprite.setAlpha(0f);
		}

		if (mustard.health == 0) {
			mustard.sprite.setAlpha(0f);
		}

		checkBullets: for (int j=0; j<bullets.size(); j++) {
			bullets.get(j).update(Gdx.graphics.getDeltaTime());

			if (ketchup.doesHit(bullets.get(j).hitBox) && bullets.get(j) != ketchup.bullet) {
				bullets.remove(j);
				mustard.bullet = null;
				ketchup.health--;
				System.out.println("HILJF");
				j--;
				continue checkBullets;
			}
			if (mustard.doesHit(bullets.get(j).hitBox) && bullets.get(j) != mustard.bullet) {
				bullets.remove(j);
				ketchup.bullet = null;
				mustard.health--;
				System.out.println("HILJF");
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

		checkProjectiles: for (int j=0; j<projectiles.size(); j++) {
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

		// Ketchup Keys
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
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			ketchup.isJumping = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			if (ketchup.bullet == null && !ketchup.isJumping && ketchup.projectile == null) {
				Bullet bullet = new Bullet((int)ketchup.hitbox.x, (int)ketchup.hitbox.y + ketchup.height / 3,"bullet.png", new Vector2(1050, 0));
				ketchup.bullet = bullet;
				bullets.add(bullet);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			if (ketchup.projectile == null && !ketchup.isJumping && ketchup.bullet == null) {
				int size = (int) (Math.random()*50) + 25;
				float push = mustard.hitbox.x - ketchup.hitbox.x;
				Projectile projectile = new Projectile((int)ketchup.hitbox.x, (int)ketchup.hitbox.y + ketchup.height / 3, size, "bullet.png", push);
				ketchup.projectile = projectile;
				projectiles.add(projectile);
			}
		}

		// Mustard Keys
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (mustard.hitbox.x > Gdx.graphics.getWidth() / 2) {
				mustard.move(Direction.left);

			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			if (mustard.hitbox.x < Gdx.graphics.getWidth() - mustard.width) {
				mustard.move(Direction.right);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			mustard.isJumping = true;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
			if (mustard.bullet == null && !mustard.isJumping && mustard.projectile == null) {
				Bullet bullet = new Bullet((int)mustard.hitbox.x, (int)mustard.hitbox.y + mustard.height / 3,"bullet.png", new Vector2(-50, 0));
				mustard.bullet = bullet;
				bullets.add(bullet);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
			if (mustard.bullet == null && !mustard.isJumping && mustard.projectile == null) {
				int size = (int) (Math.random()*50) + 25;
				float push = ketchup.hitbox.x - mustard.hitbox.x;
				Projectile projectile = new Projectile((int)mustard.hitbox.x, (int)mustard.hitbox.y + mustard.height / 3, size, "bullet.png", push);
				mustard.projectile = projectile;
				projectiles.add(projectile);
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
