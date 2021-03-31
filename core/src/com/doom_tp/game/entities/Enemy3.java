package com.doom_tp.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doom_tp.game.world.GameMap;



public class Enemy3 extends Entity{
	private static final int SPEED = 10;//80
	private static final int JUMP_VELOCITY = 1;
	Texture image;
	private boolean direction = false;
	protected GameMap map;
	
	public Enemy3(float x, float y, GameMap map) {
		super(x, y, EntityType.PLAYER, map);
		image = new Texture("Enemy.png");//Enemy Image
	}

	@Override //Movement
	public void update(float deltaTime, float gravity) {
		super.update(deltaTime, -21);
		move();
	}
	public void move(){
		moveEnemy3(1);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
	}

	
	
}
