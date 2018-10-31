package game_engine2D;
import processing.core.PApplet;

import java.util.ArrayList;

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

	public boolean ColCheck(GameObject check, ArrayList<BoundingBox> collisions)
	{
		boolean retVal = false;
		BoundingBox bb1 = check.transform.WorldBoundingBox();
			for (BoundingBox bb2: collisions) {

				System.out.println(bb1.right + " " + bb2.left + " " + bb1.left + " " + bb2.right);
				if (bb1.right > bb2.left && bb1.left < bb2.right)
				{
					System.out.println("Gone through the middle");
					if (bb1.bottom > bb2.top && bb1.top > bb2.top)
					{
						check.transform.position.y = bb2.top + check.transform.size.y/2;


						System.out.println("Consider yourself collided with top of  " + check.name);
						retVal = true;
					}
					else if (bb1.top < bb2.bottom && bb1.top > bb2.top)
					{
						check.transform.position.y = bb2.bottom;
						System.out.println("Consider yourself collided with bottom of " + check.name);
						retVal = true;
					}
					//Aled Continue Writing this
				}
			}


		return retVal;
	}
}
