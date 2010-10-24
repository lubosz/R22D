package org.android.r22d.scene;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import org.android.r22d.graphics.RenderEngine;
import org.android.r22d.graphics.Texture;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.SystemClock;

public class Sprite {
	
	List<Texture> textures = new ArrayList<Texture>();
	int[] assetFrames;
	//TODO: 2D + 3D Vector classes!
	float position[] = {0,0,0};
	float rotation;
	float scale[] = {1,1};
	int animationDelay;
	
	public Sprite(int assetFrames[]){
		this.assetFrames = assetFrames;
	}
	
	public Sprite(int assetFrames[], int animationDelay){
		this.assetFrames = assetFrames;
		this.animationDelay = animationDelay;
	}
	
	public void bindTexture(){
		Resources recources = RenderEngine.getSingletonObject().mContext.getResources();
		for (int asset : assetFrames){
			InputStream is = recources.openRawResource(asset);
			textures.add(new Texture(is)); 
		}
	}
	
	public void setPosition(float[] position) {
		this.position = position;
	}


	public void setRotation(float rotation) {
		this.rotation = rotation;
	}


	public void setScale(float[] scale) {
		this.scale = scale;
	}

	public void draw(){
		draw(0,0);
	}
	
	public void draw(float moveX, float moveY){

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
    	gl.glTranslatef(position[0], position[1],position[2]);
        gl.glScalef(scale[0], scale[1], 1);
        if (moveX < 0) gl.glRotatef(180, 0, 1.0f, 0);
        RenderEngine.getSingletonObject().mQuad.draw(gl);
        gl.glPopMatrix();
	}
	
	@Deprecated
	public void animate(int frameCounter, float moveX, float moveY){
		GL10 gl = RenderEngine.getSingletonObject().gl;
		textures.get(frameCounter%textures.size()).bind();
       
        gl.glPushMatrix();
    	gl.glTranslatef(position[0], position[1],position[2]);
        gl.glScalef(scale[0], scale[1], 1);
    	if (moveX < 0) gl.glRotatef(180, 0, 1.0f, 0);
    	RenderEngine.getSingletonObject().mQuad.draw(gl);
        gl.glPopMatrix();
	}
}
