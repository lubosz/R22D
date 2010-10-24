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
	
	Vector3f position;
	public Map<SpriteTypeEnum, Sprite> sprites = new HashMap<SpriteTypeEnum, Sprite>();

	public GameObject(String name, Vector3f position, Map<SpriteTypeEnum,Sprite> sprites, Vector2f scale){
		this.name = name;
		this.position = position;
		this.sprites = sprites;
		for (Entry<SpriteTypeEnum, Sprite> sprite : sprites.entrySet()) {
			//shouldnt be in sprite, should be in gameobject (position, ?scale?)
			sprite.getValue().setPosition(position);
			sprite.getValue().setScale(scale);
		}
	}
	
	public void draw(Vector2f move) {
		if(move.length() != 0){
			sprites.get(SpriteTypeEnum.MOVING).draw(move);
			return;
		}
		
		sprites.get(SpriteTypeEnum.STANDING).draw();
	}
	
	public void initTextures() {
		for (Entry<SpriteTypeEnum, Sprite> sprite : sprites.entrySet()) {
			sprite.getValue().initTextures();
		}
	}
}
