package game_engine2D;
import processing.core.PApplet;

public abstract class GameObject extends ProcessingEntity {
	public GameObject(PApplet p) {
		super(p);
		
	}
	//An Abstract Class for game objects.
	public String name;
	public String tag;

	public Transform transform = new Transform();
	public abstract void start();
	public abstract void update();
	public abstract void render();
	public String ToString() {
		return this.name;
	}
}
