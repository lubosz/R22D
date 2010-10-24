package org.android.r22d.scene;

import javax.microedition.khronos.opengles.GL10;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

import org.android.r22d.graphics.RenderEngine;

import android.opengl.GLU;

public class Camera {
	
	public Vector3f position;
	Vector3f up;
	Vector3f center;
	GL10 gl;
	
	public Camera(GL10 gl){
		this.gl = gl;
		up = new Vector3f(0,1,0);
		center = new Vector3f(0,0,0);
		position = new Vector3f(0,0,-5);
		
	}
	
	public void setPosition(Vector2f position2D){
		position.x = position2D.x;
		position.y = position2D.y;
		
		center.x = position2D.x;
		center.y = position2D.y;
	}
	
	public void move(Vector2f move){
		this.position.x += move.x;
		this.position.y += move.y;
		
		this.center.x += move.x;
		this.center.y += move.y;
	}

	public void updateViewMatrix(){
		GLU.gluLookAt(
				RenderEngine.getSingletonObject().gl, 
				position.x, position.y, position.z, 
				center.x, center.y, center.z, 
				up.x, up.y, up.z
		);
		
	}
	
	public void setPerspective(float ratio){
        /*
	        * Set our projection matrix. This doesn't have to be done
	        * each time we draw, but usually a new projection needs to
	        * be set when the viewport is resized.
	     */
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);
	}
	
	public void setViewPort(int width, int height){
		gl.glViewport(0, 0, width, height);
	}
	
}
