package DabEngine.GUI.Objects;

import DabEngine.GUI.GUIObject;
import DabEngine.Graphics.Graphics;
import DabEngine.Input.KeyEvent;
import DabEngine.Input.MouseEvent;
import DabEngine.Observer.Event;
import DabEngine.Utils.Color;
import DabEngine.Graphics.OpenGL.Textures.Texture;

public class Image extends GUIObject {

	public Texture image;

	@Override
	public void onNotify(Event e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMousePress(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawTexture(image, null, pos.x, pos.y, pos.z, size.x, size.y, 0, 0, 0, color);
	}

	@Override
	public void update() {

	}

}
