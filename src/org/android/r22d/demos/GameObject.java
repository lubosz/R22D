package org.android.r22d.demos;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.android.r22d.scene.Sprite;
import org.android.r22d.scene.SpriteTypeEnum;

public class GameObject {

	float position[] = {0,0,0};
	Map<SpriteTypeEnum, Sprite> sprites = new HashMap<SpriteTypeEnum, Sprite>();

	public GameObject(float[] position, Map<SpriteTypeEnum,Sprite> sprites, float[] scale){
		this.position = position;
		this.sprites = sprites;
		for (Entry<SpriteTypeEnum, Sprite> sprite : sprites.entrySet()) {
			//shouldnt be in sprite, should be in gameobject (position, ?scale?)
			sprite.getValue().setPosition(position);
			sprite.getValue().setScale(scale);
		}
	}
	
	public void draw(float moveX, float moveY) {
		if(moveX != 0 || moveY != 0){
			sprites.get(SpriteTypeEnum.MOVING).draw(moveX, moveY);
			return;
		}
		
		sprites.get(SpriteTypeEnum.STANDING).draw();
	}
	
	public void bindTextures() {
		for (Entry<SpriteTypeEnum, Sprite> sprite : sprites.entrySet()) {
			sprite.getValue().bindTexture();
		}
	}
}
