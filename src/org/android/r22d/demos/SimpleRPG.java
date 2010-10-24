package org.android.r22d.demos;

import java.util.HashMap;
import java.util.Map;

import org.android.r22d.R;
import org.android.r22d.graphics.RenderEngine;
import org.android.r22d.input.TouchSurfaceView;
import org.android.r22d.scene.Sprite;
import org.android.r22d.scene.SpriteTypeEnum;

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
		
		
		Map<SpriteTypeEnum, Sprite> sprites = new HashMap<SpriteTypeEnum, Sprite>();
		sprites.put(SpriteTypeEnum.STANDING, new Sprite(new int[] {R.raw.megamanstand}));
		sprites.put(SpriteTypeEnum.MOVING, new Sprite(new int[] {
        		R.raw.megaman0,
        		R.raw.megaman1,
        		R.raw.megaman2,
        		R.raw.megaman3
        }, 100));
		GameObject megaMan = new GameObject(
				"megaman",
				new float[] {0,0, -1.1f},
				sprites,
				new float[] {.2f, .2f});
		
		RenderEngine.getSingletonObject().gameObjects.add(megaMan);
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
