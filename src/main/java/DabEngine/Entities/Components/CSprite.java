package DabEngine.Entities.Components;

import org.joml.Vector4f;

import DabEngine.Resources.Textures.*;
import DabEngine.Utils.Color;

public class CSprite extends Component {
	
	/**
	 * Sprite Component
	 */
	private static final long serialVersionUID = 183306832993313802L;
	/**
	 * Texture field
	 */
	public transient Texture texture;
	/**
	 * Texture region
	 */
	public transient TextureRegion region;
}
