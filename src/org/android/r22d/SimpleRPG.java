package org.android.r22d;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

public class SimpleRPG extends Activity {

	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        mGLSurfaceView = new TouchSurfaceView(this);
	        setContentView(mGLSurfaceView);
	        
	        DisplayMetrics metrics = new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(metrics);
	        Log.d("Metrics", metrics.widthPixels + " " + metrics.heightPixels);
	    }
	  
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
