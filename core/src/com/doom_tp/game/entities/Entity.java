package com.doom_tp.game.entities;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntIntMap;
import com.doom_tp.game.world.GameMap;


public abstract class Entity {
	protected Vector2 pos;
	protected EntityType type;
	protected float velocityY = 0;
	protected GameMap map;
	protected boolean grounded = false;
	private boolean direction = false;
	private boolean attackDirection = false;
	private boolean initialAttack = false;
	
	public Entity(float x,float y,EntityType type, GameMap map) {
		this.pos = new Vector2(x,y);
		this.type = type;
		this.map = map;
	}
	
	public void update (float deltaTime, float gravity) {
		float newY = pos.y;
		this.velocityY += gravity * deltaTime * getWeight();
		newY += this.velocityY * deltaTime;
		if (map.collideMap(pos.x, newY, getWidth(), getHeight())) {
			if (velocityY < 0) {
				this.pos.y = (float) Math.floor(pos.y);
				grounded = true;
			}
			this.velocityY = 0;
		}
		else {
			this.pos.y = newY;
			grounded = false;
		}
		
	}
	
	public abstract void render (SpriteBatch batch);

	protected void moveX(float amount) {
		float newX = pos.x + amount;
		if (!map.collideMap(newX, pos.y, getWidth(), getHeight())) {
			this.pos.x = newX;
		}
		
	}

	protected void moveEnemy1(float amount) {
		if(pos.x > 1506) {
			direction = true;
		}else if(pos.x < 1086) {
			direction = false;
		}
		
		if(!direction) {
			float newX = pos.x + amount;
			if (!map.collideMap(newX, pos.y, getWidth(), getHeight())) {
				this.pos.x = newX;
			}else {
				this.pos.y += 5;
				if(map.collideMap(this.pos.x-0.5f, this.pos.y+5, getWidth(), getHeight())) {
					direction = true;
				}
			}
		}else if(direction) {
			float newX = pos.x - amount;
			if (!map.collideMap(newX, pos.y, getWidth(), getHeight())) {
				this.pos.x = newX;
			}else {
				this.pos.y += 5;
				if(map.collideMap(this.pos.x+0.5f, this.pos.y+5, getWidth(), getHeight())) {
					direction = false;
				}
			}
		}
	}
	protected void moveEnemy2(float amount) {
		if(!direction) {
			float newX = pos.x + amount;
			float newY = pos.y + 16;
			float oldY = pos.y;
			if(!map.collideMap(newX, pos.y, getWidth(), getHeight())) {//Can it go forward?
				pos.x = newX;
				return;
			}else if(!map.collideMap(pos.x, newY, getWidth(), getHeight())) {//Can it go up?
				pos.y = newY;
				if(!map.collideMap(newX, pos.y, getWidth(), getHeight())) {
					pos.x = newX;
					return;
				}else{
					pos.y = oldY;
					direction = true;
				}
			}else{
				direction = true;
			}
		}else if(direction) {
			float newX = pos.x - amount;
			float newY = pos.y + 16;
			float oldY = pos.y;
			if(!map.collideMap(newX, pos.y, getWidth(), getHeight())) {//Can it go forward?
				pos.x = newX;
				return;
			}else if(!map.collideMap(pos.x, newY, getWidth(), getHeight())) {//Can it go up?
				pos.y = newY;
				if(!map.collideMap(newX, pos.y, getWidth(), getHeight())) {
					pos.x = newX;
					return;
				}else{
					pos.y = oldY;
					direction = false;
				}
			}else{
				direction = false;
			}
		}
	}
	protected void moveEnemy3(float amount) {
		if(!direction) {
			float newX = pos.x + amount;
			if(!map.collideMap(newX, pos.y, getWidth(), getHeight())) {//Can it go forward?
				pos.x = newX;
			}else {
				float newY = pos.y+10;
				if(!map.collideMap(pos.x, newY, getWidth(), getHeight())) {//Can it go up?
					pos.y = newY;
					if(!map.collideMap(newX, newY, getWidth(), getHeight())) {//Up and forward?
						pos.x = newX;
						pos.y = newY;
					}
				}else {
					direction = true;
				}
			}
		}else if(direction) {
			float newX = pos.x - amount;
			if(!map.collideMap(newX, pos.y, getWidth(), getHeight())) {//Can it go forward?
				pos.x = newX;
			}else {
				float newY = pos.y+10;
				if(!map.collideMap(pos.x, newY, getWidth(), getHeight())) {//Can it go up?
					pos.y = newY;
					if(!map.collideMap(newX, newY, getWidth(), getHeight())) {//Up and forward?
						pos.x = newX;
						pos.y = newY;
					}
				}else {
					direction = false;
				}
			}
		}
		
		/*if(!direction) {
			float newX = pos.x + amount;
			if (!map.collideMap(newX, pos.y, getWidth(), getHeight())) {
				this.pos.x = newX;
			}else {
				this.pos.y += 11;
				if(map.collideMap(newX+1, this.pos.y+5, getWidth(), getHeight())) {
					direction = true;
				}
			}
		}else if(direction) {
			float newX = pos.x - amount;
			if (!map.collideMap(newX, pos.y, getWidth(), getHeight())) {
				this.pos.x = newX;
			}else {
				this.pos.y += 11;
				if(map.collideMap(newX-2, this.pos.y+5, getWidth(), getHeight())) {
					direction = false;
				}
			}
		}*/
	}
	protected void moveEnemy4(float amount) {
		float newY = pos.y - amount;
		if(pos.x > map.getPixelWidth()) {
			pos.x = 0;
		}
		if(!map.collideMap(pos.x, newY, getWidth(), getHeight())) {
			this.pos.y = newY;
		}else {
			pos.x = pos.x + 50;
			pos.y = 1500;
		}
	}
	protected void moveEnemy5(float amount) {
		if(pos.y > 1500) {
			pos.y = 0;
			pos.x -= 100;
			if(pos.x < 0) {
				pos.x = 1500;
			}
		}else {
			pos.y += 12;
		}
	}
	
	protected void moveAttack(float amount) {
		if(attackDirection) {
			moveAttackLeft(amount);
		}else if(!attackDirection) {
			moveAttackRight(amount);
		}
		
		if (Gdx.input.isKeyPressed(Keys.S)) {
			attackDirection = false;//Right
			this.pos.y = map.getPlayerY();
			this.pos.x = map.getPlayerX();
		}else if(Gdx.input.isKeyPressed(Keys.A)){
			attackDirection = true;//Left
			this.pos.y = map.getPlayerY();
			this.pos.x = map.getPlayerX()-7;
		}
	}
	
	private void moveAttackRight(float amount) {
		if(map.collideMap(this.pos.x, this.pos.y, getWidth(), getHeight())) {
 			this.pos.y = 2000;
			this.pos.x = 1500;
		}else {
			pos.x += amount;
		}
	}
	private void moveAttackLeft(float amount) {
		if(map.collideMap(this.pos.x-7, this.pos.y, getWidth(), getHeight())) {
 			this.pos.y = 2000;
			this.pos.x = 1500;
		}else {
			pos.x -= amount;
		}
	}
	
	public Vector2 getPos() {
		return pos;
	}

	public EntityType getType() {
		return type;
	}

	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}
	
	public boolean isGrounded() {
		return grounded;
	}
	
	public int getWidth() {
		return type.getWidth();
	}
	
	public int getHeight() {
		return type.getHeight();
	}
	
	public float getWeight() {
		return type.getWeight();
	}
	
}
