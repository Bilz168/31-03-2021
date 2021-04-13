package com.doom_tp.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doom_tp.game.world.GameMap;



public class Enemy2 extends Entity{
	private static final int SPEED = 80;
	private static final int JUMP_VELOCITY = 6;
	Texture image;
	private boolean direction = false;
	protected GameMap map;
	
	public Enemy2(float x, float y, GameMap map) {
		super(x, y, EntityType.Enemy2, map);
		image = new Texture("Enemy.png");//Enemy Image
		
		
	}

	@Override //Movement
	public void update(float deltaTime, float gravity) {
		/*if(Gdx.input.isKeyPressed(Keys.SPACE) && grounded) {
			this.velocityY += JUMP_VELOCITY * getWeight();
		}*/
		
		super.update(deltaTime, gravity);
		move();
	}
	public void move(){
		moveEnemy2(1);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
	}

	
	
}
