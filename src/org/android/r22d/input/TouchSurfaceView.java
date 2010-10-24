package org.android.r22d.input;

import javax.vecmath.Vector2f;

import org.android.r22d.graphics.RenderEngine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class TouchSurfaceView extends GLSurfaceView {

    public TouchSurfaceView(Context context) {
        super(context);
        RenderEngine.getSingletonObject().setContext(context);
        setRenderer(RenderEngine.getSingletonObject());
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX()-getWidth()/2;
        float y = e.getY()-getHeight()/2;
        
        switch (e.getAction()) {
        	case MotionEvent.ACTION_DOWN:
        	case MotionEvent.ACTION_MOVE:
        		RenderEngine.getSingletonObject().setMove(new Vector2f(x*.0001f, y*.0001f));
	            break;
        	case MotionEvent.ACTION_UP:
        		RenderEngine.getSingletonObject().setMove(new Vector2f(0, 0));
	            break;
	        default:
	        	break;
	            
        }
        requestRender();
        return true;
    }
}