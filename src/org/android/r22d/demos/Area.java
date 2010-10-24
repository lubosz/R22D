package org.android.r22d.demos;

import java.util.Map;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import org.android.r22d.scene.Sprite;
import org.android.r22d.scene.SpriteTypeEnum;

public class Area extends GameObject{
	public Area(Map<SpriteTypeEnum,Sprite> sprites){
		super("Area", new Vector3f(0,0,0), sprites, new Vector2f(8,8));
	}

}
