package game_engine2D;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;

public abstract class GameObject extends ProcessingEntity {
	public GameObject(PApplet p) {
		super(p);
		
	}
	//An Abstract Class for game objects.
	public String name;
	public String Collided;
	public String tag;

	public Transform transform = new Transform();
	public Physics2D physics2D = new Physics2D();

	public abstract void start();
	public abstract void update();
	public abstract void render();

	public void KeyPressed(char key, int keyCode){ System.out.println(key);}
	public void KeyReleased(char key, int keyCode){}

	public String ToString() {
		return this.name;
	}


}
