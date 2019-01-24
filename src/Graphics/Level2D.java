package Graphics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Entities.Components.CCollision;
import Entities.Components.CRender;
import Entities.Components.CTransform;
import Entities.Components.CPhysics.BodyType;
import Graphics.Models.Texture;
import Graphics.Models.Tiles;
import Utils.ResourceManager;

/*
 * In each level file, their is a <header> section and a 
 * the actual level data.
 * Everything between the header is all the meta-data like 
 * width and height and textures to use.
 */

public class Level2D {
	
	public float levelwidth;
	public float levelheight;
	public float tilewidth, tileheight;
	private char spawn_point;
	public Vector2f spawnpos;
	private HashMap<Character, String[]> info = new HashMap<>();
	private char[][] level_info;
	private Tiles[][] tiles;
	private ArrayList<Background> backgrounds = new ArrayList<>();
	private boolean loaded = false;
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private String levelname;
	
	private File findLevel(File level_dir) {
		for(File file : level_dir.listFiles()) {
			if(file.isFile() && file.toString().contains(".del")) {
				return file;
			}
		}
		return null;
	}
	
	public void load(File level_dir) {
		loaded = false;
		ArrayList<ArrayList<Character>> raw_info = new ArrayList<>();
		File level = findLevel(level_dir);
		if(level_dir.isDirectory()) {
			try(BufferedReader in = new BufferedReader(new FileReader(level))) {
				String s;
				String header = null;
				while((s = in.readLine()) != null) {
					s = s.replace(" ", "");
					if(s.equals("[General]")) {
						header = s;
						continue;
					}
					else if(s.equals("[Backgrounds]")) {
						header = s;
						continue;
					}
					else if(s.equals("[TileInfo]")) {
						header = s;
						continue;
					}
					else if(s.equals("[TileMap]")) {
						header = s;
						continue;
					}
					else if(s.isBlank()) {
						continue;
					}
					if(header.equals("[General]")) {
						String[] map = s.split(":");
						String stringval = map[1];
						switch(map[0]) {
							case "TileWidth":
								tilewidth = Float.parseFloat(map[1]);
								break;
							case "TileHeight":
								tileheight = Float.parseFloat(map[1]);
								break;
							case "LevelWidth": 
								levelwidth = Float.parseFloat(map[1]);
								break;
							case "LevelHeight":
								levelheight = Float.parseFloat(map[1]);
								break;
						}
					}
					else if(header.equals("[Backgrounds]")) {
						String[] map = s.split(":");
						switch(map[0]) {
							case "BackgroundFilename":
								backgrounds.add
								(
										new Background() 
										{{
											texture = new Texture(level_dir.toPath().resolve(map[1]).toString());
										}}
								);
								break;
						}
					}
					else if(header.equals("[TileInfo]")) {
						String[] map = s.split(":");
						String[] t_info = map[1].split(",");
						info.put(map[0].charAt(0), t_info);
					}
					else if(header.equals("[TileMap]")) {
						ArrayList<Character> row = new ArrayList<>();
						char[] chars = s.toCharArray();
						for(char c : chars) {
							row.add(c);
						}
						raw_info.add(row);
					}
				}
			} catch(IOException e) {
			LOGGER.log(Level.SEVERE, "Level Load Error", e);
			}
		}
		level_info = convertIntegers(raw_info);
		tiles = new Tiles[level_info.length][level_info[0].length];
		loaded = true;
		LOGGER.log(Level.INFO, "Level '" + level_dir.toString() + "' loaded");
	}
	
	public void init(Texture spawn_texture) {
		int height = level_info.length;
		int width = level_info[0].length;
		
		if(tilewidth == 0 && tileheight == 0) {
			tilewidth = levelwidth / (float) width; 
			tileheight = levelheight / (float) height;
		} else if(levelwidth == 0 && levelheight == 0) {
			levelwidth = tilewidth * (float) width;
			levelheight = tileheight * (float) height;
		}
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				for(Map.Entry<Character, String[]> entry : info.entrySet()) {
					if(level_info[y][x] == entry.getKey()) {
						float posx = tilewidth * x;
						float posy = tileheight * y;
						if(entry.getValue()[0].equals("Tile")) {
							Tiles tile = new Tiles(
									ResourceManager.getTexture(entry.getValue()[1]),
									posx,
									posy,
									tilewidth,
									tileheight,
									new Vector4f(1, 1, 1, 1),
									false,
									false,
									BodyType.STATIC);
							if(entry.getValue()[2].equals("1")) {
								tile.addComponent(new CCollision());
								tile.getComponent(CCollision.class).correctBounds();
							}
							tiles[y][x] = tile;
						} else if(entry.getValue()[0].equals("Trigger")) {
							
						}
						else if(entry.getValue()[0].equals("SpawnPoint")) {
							spawnpos = new Vector2f(posx, posy);
						}
					}
				}
			}
		}
	}
	
	public void render(SpriteBatch batch) {
		for(int i = 0; i < backgrounds.size(); i++) {
			Background bg = backgrounds.get(i);
			bg.x = 0;
			bg.y = 0;
			bg.z = i * 5;
			bg.width = levelwidth;
			bg.height = levelheight;
			bg.color = new Vector4f(1);
			bg.draw(batch);
		}
		for(int y = 0; y < tiles.length; y++) {
			for(int x = 0; x < tiles[0].length; x++) {
				if(tiles[y][x] != null) {
					tiles[y][x].getComponent(CRender.class).render(batch);
				}
			}
		}
	}
	
	public Tiles[][] getTiles() {
		return tiles;
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	
	private char[][] convertIntegers(ArrayList<ArrayList<Character>> integers)
	{
	    char[][] ret = new char[integers.size()][integers.get(0).size()];
	    for (int i=0; i < ret.length; i++)
	    {
	    	for(int j=0; j < ret[0].length; j++)
	    	{
	    		ret[i][j] = integers.get(i).get(j).charValue();
	    	}
	    }
	    return ret;
	}

	public String getLevelName() {
		return levelname.split(".")[0];
	}

	public void setLevelName(String levelname) {
		this.levelname = levelname;
	}
}
