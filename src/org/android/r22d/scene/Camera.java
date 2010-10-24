package org.android.r22d.scene;

import javax.vecmath.Vector3f;

import org.android.r22d.graphics.RenderEngine;

import android.opengl.GLU;

public class Camera {
	
	Vector3f position;
	Vector3f up;
	Vector3f center;
	
	public Camera(){
		up = new Vector3f(0,1,0);
		center = new Vector3f(0,0,0);
		position = new Vector3f(0,0,-5);
		
	}

	public void updateViewMatrix(){
		GLU.gluLookAt(
				RenderEngine.getSingletonObject().gl, 
				position.x, position.y, position.z, 
				center.x, center.y, center.z, 
				up.x, up.y, up.z
		);
		
	}
	
}
