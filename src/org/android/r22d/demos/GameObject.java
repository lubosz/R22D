package org.android.r22d.demos;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import org.android.r22d.scene.Sprite;
import org.android.r22d.scene.SpriteTypeEnum;

public class GameObject {

	public String name;
	
	public Vector3f position;
	float rotation;
	Vector2f scale;
	
	public Map<SpriteTypeEnum, Sprite> sprites = new HashMap<SpriteTypeEnum, Sprite>();

	public GameObject(String name, Vector3f position, Map<SpriteTypeEnum,Sprite> sprites, Vector2f scale){
		this.scale = new Vector2f(1,1);
		this.name = name;
		this.position = position;
		this.sprites = sprites;

	}
	
	public void draw(Vector2f move) {
		if(move.length() != 0){
			sprites.get(SpriteTypeEnum.MOVING).draw(move,position,scale);
			return;
		}
		
		sprites.get(SpriteTypeEnum.STANDING).draw(position,scale);
	}
	
	public void initTextures() {
		for (Entry<SpriteTypeEnum, Sprite> sprite : sprites.entrySet()) {
			sprite.getValue().initTextures();
		}
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setPosition(Vector2f position2d) {
		position.x = position2d.x;
		position.y = position2d.y;
	}


	public void setRotation(float rotation) {
		this.rotation = rotation;
	}


	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	
	public void move(Vector2f move){
		this.position.x += move.x;
		this.position.y += move.y;
	}
}
