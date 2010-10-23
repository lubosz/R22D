package org.android.r22d;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.content.res.Resources;

public class Sprite {
	
	List<Texture> textures;
	float position[] = {0,0,0};
	float rotation;
	float scale[] = {1,1};
	
	public Sprite(int assetFrames[]){
		textures = new ArrayList<Texture>();
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
		GL10 gl = RenderEngine.getSingletonObject().gl;
		textures.get(0).bind();
        gl.glPushMatrix();
        	gl.glTranslatef(position[0], position[1],position[2]);
	        gl.glScalef(scale[0], scale[1], 1);
	        RenderEngine.getSingletonObject().mQuad.draw(gl);
        gl.glPopMatrix();
	}
	
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
