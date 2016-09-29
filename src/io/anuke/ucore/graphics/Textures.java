package io.anuke.ucore.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**Static utility class for loading textures as requested.
*/
public class Textures{
	private static TextureMap textures;
	
	public static void load(String path){
		if(textures != null) throw new GdxRuntimeException("Textures are already loaded! Did you forget to call Textures.dispose()?");
		textures = new TextureMap(path);
	}
	
	public static void repeatWrap(String... textures){
		Textures.textures.repeatWrap(textures);
	}
	
	public static Texture get(String name){
		return textures.get(name);
	}
	
	public static Drawable getDrawable(String name){
		return new TextureRegionDrawable(new TextureRegion(get(name)));
	}
	
	public static boolean has(String name){
		return textures.has(name);
	}
	
	public static void put(String name, Texture texture){
		textures.put(name, texture);
	}
	
	public static void dispose(){
		if(textures != null)
		textures.dispose();
		textures = null;
	}
	
}
