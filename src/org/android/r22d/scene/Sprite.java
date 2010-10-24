package org.android.r22d.scene;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import org.android.r22d.graphics.RenderEngine;
import org.android.r22d.graphics.Texture;

import android.content.res.Resources;
import android.os.SystemClock;

public class Sprite {
	
	public List<Texture> textures = new ArrayList<Texture>();
	int[] assetFrames;
	Vector3f position;
	float rotation;
	Vector2f scale;
	public int animationDelay;
	
	public Sprite(int assetFrames[]){
		this.assetFrames = assetFrames;
		scale = new Vector2f(1,1);
		position = new Vector3f(0,0,0);
		animationDelay = 1;
	}
	
	public Sprite(int assetFrames[], int animationDelay){
		this.assetFrames = assetFrames;
		this.animationDelay = animationDelay;
	}
	
	public void initTextures(){
		Resources recources = RenderEngine.getSingletonObject().mContext.getResources();
		for (int asset : assetFrames){
			InputStream is = recources.openRawResource(asset);
			textures.add(new Texture(is)); 
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

	public void draw(){
		draw(new Vector2f(0,0));
	}
	
	public void move(Vector2f move){
		this.position.x += move.x;
		this.position.y += move.y;
	}
	
	public void draw(Vector2f move){

		GL10 gl = RenderEngine.getSingletonObject().gl;
		if(textures.size() > 0) {
		if(textures.size() < 2)
			textures.get(0).bind();
		else{
	        int time = (int)(SystemClock.uptimeMillis() % 4000L) / animationDelay;
			textures.get(time%textures.size()).bind();
		}
		}
        gl.glPushMatrix();
	    	gl.glTranslatef(position.x, position.y,position.z);
	        gl.glScalef(scale.x, scale.y, 1);
	        if (move.x > 0) gl.glRotatef(180, 0, 1.0f, 0);
	        RenderEngine.getSingletonObject().mQuad.draw(gl);
        gl.glPopMatrix();
	}
}
