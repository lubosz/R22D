package org.android.r22d.graphics;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class Texture {

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

        //GL_NEAREST for sharp retro pixels
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE);

		Bitmap bitmap;
		try {
			bitmap = BitmapFactory.decodeStream(is);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// Ignore.
			}
		}

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		bitmap.recycle();

		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
	}
	
	void activate() {
		gl.glActiveTexture(glId);
	}

	public void bind() {
		gl.glBindTexture(GL10.GL_TEXTURE_2D, glHandle);

	}
}
