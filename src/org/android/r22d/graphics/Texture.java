package org.android.r22d.graphics;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class Texture {
	
	//final private int glTextureIDs[] = {GL10.GL_TEXTURE0, GL10.GL_TEXTURE1};
	

    private int glHandle;
    GL10 gl;
    int glId;

	public Texture(InputStream is){
		this.gl = RenderEngine.getSingletonObject().gl;
		//glId = glTextureIDs[id];
        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);

        glHandle = textures[0];
        gl.glBindTexture(GL10.GL_TEXTURE_2D, glHandle);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE);
        //gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        //gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
        
       // gl.glTexEnvf(GL10.GL_TEXTURE_ENV, GL10.GL_TEXTURE_ENV_MODE, GL10.GL_REPLACE);

        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch(IOException e) {
                // Ignore.
            }
        }

        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
        

    	

        
        gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
	}
	
	void activate(){
    	gl.glActiveTexture(glId);
	}
	

	
	void bind(){
        //
        gl.glBindTexture(GL10.GL_TEXTURE_2D, glHandle);

	}
}
