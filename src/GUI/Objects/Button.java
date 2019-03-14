package GUI.Objects;

import Entities.Components.CSprite;
import Entities.Components.CTransform;
import GUI.GUIObject;
import GUI.Components.CClickable;
import Graphics.Batch.Polygon;
import Input.InputHandler;
import Input.KeyEvent;
import Input.MouseEvent;
import Observer.Event;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector4f;

public class Button extends GUIObject {
	
	public Polygon poly;
	public String label;
	public Vector4f label_color;

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
		if(hover && e.getButton() == GLFW_MOUSE_BUTTON_LEFT) {
			action();
		}
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onHover() {
		// TODO Auto-generated method stub
		hover = true;
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		hover = false;
	}
	
	public void action() {
		
	}
	
	
}