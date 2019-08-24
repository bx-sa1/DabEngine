package DabEngine.GUI.Objects;

import java.util.ArrayList;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector3f;

import DabEngine.GUI.GUIObject;
import DabEngine.Graphics.Graphics;
import DabEngine.Input.InputHandler;
import DabEngine.Input.KeyEvent;
import DabEngine.Input.MouseEvent;
import DabEngine.Observer.Event;

public class Panel extends GUIObject {

	protected boolean moveable, scaleable;
	public final ArrayList<GUIObject> panel_objects = new ArrayList<>();

	public Panel(boolean moveable, boolean scaleable) {
		this.moveable = moveable;
		this.scaleable = scaleable;
	}

	public Panel(boolean moveable) {
		this(moveable, false);
	}

	public Panel() {
		this(false, false);
	}

	public void addToPanel(GUIObject g) {
		panel_objects.add(g);
		g.onAddedToPanel(this);
	}

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
		if (state.getState() == States.HOVER) {
			if(moveable){
				Vector2d m = InputHandler.INSTANCE.getMouseDelta();
				pos = new Vector3f().set((float)m.x, (float)m.y, pos.z);
			}
			state.setState(States.PRESSED);
		}
	}

	@Override
	public void onMouseRelease(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		for(var obj : panel_objects){
			obj.render(g);
		}
	}

	@Override
	public void update() {

	}
	
}
