/**
 * 
 */
package simple_platformer;

import game_engine2D.Sprite;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author Rod Martin r.martin1@salford.ac.uk
 *
 */
public class Player extends Sprite {
	float speed = 3f;
	float gravity = 0.1f;
	// PVectors for Velocity and Size of Player
	private PVector velocity = new PVector(0, 0);
	private PVector size = new PVector(12,12);
	//colouring in.
	public int stroke = parent.color(120,120,255);
	public int fill = parent.color(255);
	/**
	 * @param p
	 */
	public Player(PApplet p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	 public Player(PApplet p, float x, float y, float w, float h, String n) {
	        super(p);
	        this.name = n;
	        speed = 0.2f;
	    }
	 public void start() {
	    //start in center of parent
		 this.transform.position.x = parent.width / 2;
		 this.transform.position.y = parent.height / 2;
	 }
	/* (non-Javadoc)
	 * @see game_engine2D.Sprite#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub
        //move this to physics soonish Aled.
		velocity.y += gravity;
		this.transform.position.x += velocity.x;
		this.transform.position.y += velocity.y;

	}
	@Override
	public void render(){
	    //renders the player in the right place.
		parent.fill(this.fill);
		parent.stroke(this.stroke);
		parent.rect(this.transform.position.x, this.transform.position.y, this.size.x, this.size.y);

	}
}
