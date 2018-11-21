import processing.core.PApplet;

public abstract class GameObject {

	//this is for the GameObject's Name
	public String name;
	
	//Sets Processing to the parent.
	PApplet parent;
	public GameObject(){}
	public GameObject(PApplet p){
		parent = p;
	}
	
	public abstract void update();
	public abstract void render();
	
	//abstract class means that it is only here for other classes to inherit from
}

