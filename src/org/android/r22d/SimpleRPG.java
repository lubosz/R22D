package org.android.r22d;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

public class SimpleRPG extends Activity {
	//PhysicsWorld mWorld;

	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //mGLSurfaceView = new GLSurfaceView(this);
	       
	        /*
	        if (detectOpenGLES20()) {
	            // Tell the surface view we want to create an OpenGL ES 2.0-compatible
	            // context, and set an OpenGL ES 2.0-compatible renderer.
	            mGLSurfaceView.setEGLContextClientVersion(2);
	            mGLSurfaceView.setRenderer(new GLES20TriangleRenderer(this));
	        } else {
	            // Set an OpenGL ES 1.x-compatible renderer. In a real application
	            // this renderer might approximate the same output as the 2.0 renderer.
	            mGLSurfaceView.setRenderer(new TriangleRenderer(this));
	        }
	        */
	        
	        /*
	        mWorld = new PhysicsWorld();
	        mWorld.create();

	        // Add 50 Balls
	        for (int i=0; i<50; i++) {
	            mWorld.addBall();
	        }
	        */
	        mGLSurfaceView = new TouchSurfaceView(this);
	        setContentView(mGLSurfaceView);
	        
	        DisplayMetrics metrics = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(metrics);
	        Log.d("Metrics", metrics.widthPixels + " " + metrics.heightPixels);
	    }
	  

	  
/*
	    private boolean detectOpenGLES20() {
	        ActivityManager am =
	            (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
	        ConfigurationInfo info = am.getDeviceConfigurationInfo();
	        return (info.reqGlEsVersion >= 0x20000);
	    }
*/
	    @Override
	    protected void onResume() {
	        // Ideally a game should implement onResume() and onPause()
	        // to take appropriate action when the activity looses focus
	        super.onResume();
	        mGLSurfaceView.onResume();
	    }

	    @Override
	    protected void onPause() {
	        // Ideally a game should implement onResume() and onPause()
	        // to take appropriate action when the activity looses focus
	        super.onPause();
	        mGLSurfaceView.onPause();
	    }

	    private GLSurfaceView mGLSurfaceView;
}
