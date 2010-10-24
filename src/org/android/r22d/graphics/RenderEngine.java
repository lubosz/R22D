/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.android.r22d.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.vecmath.Vector2f;

import org.android.r22d.R;
import org.android.r22d.demos.GameObject;
import org.android.r22d.geometry.Quad;
import org.android.r22d.scene.Camera;
import org.android.r22d.scene.Sprite;
import org.android.r22d.scene.SpriteTypeEnum;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

public class RenderEngine implements GLSurfaceView.Renderer{

    public Quad mQuad;
    public Context mContext;
    private Sprite mMap;
    private int frameCounter;
    Vector2f move;
    //, position;
    public GL10 gl;
    List<Sprite> sprites;
    public List<GameObject> gameObjects = new ArrayList<GameObject>();
    public Camera camera;
	
    public RenderEngine() {
        
        mQuad = new Quad();
        
        frameCounter = 0;
        move = new Vector2f(0,0);
        //position = new Vector2f(0,0);
       
    }
    
    public void setContext(Context context){
    	mContext = context;
    }
    
    private static RenderEngine ref;

    public static RenderEngine getSingletonObject()
    {
      if (ref == null)
          // it's ok, we can call this constructor
          ref = new RenderEngine();		
      return ref;
    }

    
    public void setMove(Vector2f move){
    	this.move = move;
    	move.negate();
    	//position.add(move);
    	//Log.d("position",positionX + " "+ positionY);
        //TODO: Shorter syntax?
        //Vector2f inversePosition = position;
        //inversePosition.negate();
        //camera.setPosition(position);
    	camera.move(move);
    	
    }
    
    private void initOpenGL(){
    	//Optimization
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        
        //Background color
        gl.glClearColor(.5f, .5f, .5f, 1);
        
        //Depth Order
        gl.glEnable(GL10.GL_DEPTH_TEST);
        
        //Transparency
        gl.glEnable (GL10.GL_BLEND); 
        gl.glBlendFunc (GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        //Does not seem to be needed
        //gl.glShadeModel(GL10.GL_SMOOTH);
        //gl.glEnable(GL10.GL_TEXTURE_2D);
        
        //Enable client states for meshes
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    	this.gl = gl;
    	camera = new Camera(gl);
    	initOpenGL();
    
        
        for (GameObject gameObject : gameObjects) {
			gameObject.initTextures();
		}
        
        mMap = new Sprite(new int[] {R.raw.map});
        mMap.setScale(new Vector2f(8,8));
        mMap.initTextures();

    }

    public void onDrawFrame(GL10 gl) {
    	

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        
        camera.updateViewMatrix();

//        int time = (int)(SystemClock.uptimeMillis() % 4000L) / 200;


        //mMap.setPosition(new Vector3f(positionX, positionY,0));
        mMap.draw();
        
        for (GameObject gameObject : gameObjects) {
        	
        	//animation speed of megaman MOVING animation dependent on moveX/moveY vector
        	if(gameObject.name.equals("megaman"))
        		for (Entry<SpriteTypeEnum, Sprite> sprite : gameObject.sprites.entrySet()) {
        			sprite.getValue().move(move);
        			if(sprite.getValue().textures.size()>1 && move.length()>0)
        				//sprite.getValue().animationDelay = (int)((500-(movelength*50000))>100?(1000-(movelength*10000)):100);
        				sprite.getValue().animationDelay = (int)(800-(move.length()*20000)); //max 0.03 min 0,003
        		}
        	
        	
			gameObject.draw(move);
		}
        frameCounter++;

    }

    public void onSurfaceChanged(GL10 gl, int w, int h) {
    	float ratio = (float) w/h;
    	Log.d("ratio", ratio + "");
    	Log.d("metrics", w + " " + h);
        camera.setViewPort(w, h);
        camera.setPerspective(ratio);
    }

}

