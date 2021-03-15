package com.doom_tp.game.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.doom_tp.game.entities.Entity;
import com.doom_tp.game.entities.Player;
import com.doom_tp.game.entities.Enemy1;
import com.doom_tp.game.entities.Enemy2;
import com.doom_tp.game.entities.Enemy3;
import com.doom_tp.game.entities.Enemy4;
import com.doom_tp.game.entities.Enemy5;

public abstract class GameMap {

	protected ArrayList<Entity> entities;
	
	
	public GameMap() {
		entities = new ArrayList<Entity>();
		entities.add(new Player(10,1000,this));
		entities.add(new Enemy1(1150,950,this));
		entities.add(new Enemy2(12,1000,this));
		entities.add(new Enemy3(20,100,this));
		entities.add(new Enemy4(0,1500,this));
		entities.add(new Enemy5(1500,0,this));
	}
	
	public void render(OrthographicCamera camera, SpriteBatch batch) {
		for (Entity entity :  entities) {
			entity.render(batch);
		}
		
		if(Gdx.input.isTouched()) {
			camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			camera.update();
		}
		
		
	}
	
	public void update(float delta) {
		for (Entity entity :  entities) {
			entity.update(delta, -9.8f);
		}
	}
	
	public abstract void dispose();
	
	/**
	 * This method gets a tile by the pixel position within the game world at a specified layer.
	 * @param layer
	 * @param x
	 * @param y
	 * @return
	 */
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int)(x / TileType.TILE_SIZE), (int)(y / TileType.TILE_SIZE));
	}
	
	/**
	 * This method gets a tile at its coordinate within the map at a specified layer.
	 * @param layer
	 * @param col
	 * @param row
	 * @return
	 */
	public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

	public boolean collideMap(float x, float y, int width, int height) {
		if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight())
			return true;
		
		for (int row = (int)(y / TileType.TILE_SIZE); row < Math.ceil((y + height) / TileType.TILE_SIZE); row++) {
			for (int col = (int)(x / TileType.TILE_SIZE); col < Math.ceil((x + width) / TileType.TILE_SIZE); col++) {
				for (int layer = 0; layer < getLayers(); layer++) {
					TileType type = getTileTypeByCoordinate(layer, col, row);
					if (type != null && type.isCollidable())
						return true;
				
				}
			}
		}
		
		return false;
		
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
	
	public int getPixelWidth() {
		return this.getWidth() * TileType.TILE_SIZE;
	}
	
	public int getPixelHeight() {
		return this.getHeight() * TileType.TILE_SIZE;
	}
	
}
