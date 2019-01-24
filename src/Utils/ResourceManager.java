package Utils;

import java.util.HashMap;

import Graphics.Level2D;
import Graphics.Shaders;
import Graphics.Models.Texture;

public class ResourceManager {
	
	private static HashMap<String, Shaders> shaders;
	private static HashMap<String, Texture> textures;
	private static HashMap<String, Level2D> levels;
	
	private ResourceManager() {}
	
	static {
		shaders = new HashMap<>();
		textures = new HashMap<>();
		levels = new HashMap<>();
	}
	
	public static ResourceManager addShader(String name, Shaders shader) {
		shaders.put(name, shader);
		return null;
	}
	
	public static ResourceManager addTexture(String name, Texture texture) {
		textures.put(name, texture);
		return null;
	}
	
	public static ResourceManager addLevel(String name, Level2D level) {
		levels.put(name,  level);
		return null;
	}
	
	//does the exact same as add, only used for readability
	public static ResourceManager replaceShader(String nametoreplace, Shaders shader) {
		shaders.put(nametoreplace, shader);
		return null;
	}
	
	public static ResourceManager replaceTexture(String nametoreplace, Texture texture) {
		textures.put(nametoreplace, texture);
		return null;
	}
	
	public static ResourceManager replaceLevel(String nametoreplace, Level2D level) {
		levels.put(nametoreplace,  level);
		return null;
	}
	
	public static Shaders getShader(String name) {
		return shaders.get(name);
	}
	
	public static Texture getTexture(String name) {
		return textures.get(name);
	}
	
	public static Level2D getLevel(String name) {
		return levels.get(name);
	}
	
	public static HashMap<String, Shaders> getShaders() {
		return shaders;
	}
	
	public static HashMap<String, Texture> getTextures() {
		return textures;
	}
	
	public static HashMap<String, Level2D> getLevels() {
		return levels;
	}
}