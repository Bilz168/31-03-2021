package com.doom_tp.game.entities;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.doom_tp.game.world.GameMap;

@SuppressWarnings("rawtypes")
public enum EntityType {
	PLAYER("player", Player.class, 14, 32, 40),
	Enemy1("Enemy1",Enemy1.class,30,30,40),
	Enemy2("Enemy2",Enemy2.class,100,100,40),
	Enemy3("Enemy3",Enemy3.class,100,100,40),
	Enemy4("Enemy4",Enemy4.class,100,100,40),
	Enemy5("Enemy5",Enemy5.class,100,100,40),
	Attack("Attack",Attack.class,30,30,40);
	
	private String id;
	private Class loaderClass;
	private int width, height;
	private float weight;
	
	private EntityType(String id, Class loaderClass, int width, int height, float weight) {
		this.id = id;
		this.loaderClass = loaderClass;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getWeight() {
		return weight;
	}

	private static HashMap<String, EntityType> entityTypes; 

	static {
		entityTypes = new HashMap<String, EntityType>();
		for (EntityType type : EntityType.values())
			entityTypes.put(type.id, type);
	}

}
