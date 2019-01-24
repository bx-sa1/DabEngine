package Entities.Components;

import org.joml.Vector2f;

import Core.Engine;

public class CPhysics extends Component {
	
	public Vector2f velocity = new Vector2f();
	public float MASS;
	public float MAX_VELOCITY;
	public enum BodyType {
		DYNAMIC, STATIC
	}
	private BodyType bodytype;
	
	public CPhysics() {
		Engine.addToPhysics(this);
	}
	
	public BodyType getBodyType() {
		return bodytype;
	}

	public void setBodyType(BodyType bodytype) {
		this.bodytype = bodytype;
	}
	
	public void addVelocity(float x, float y) {
		velocity.add(x, y);
	}
}