/**
 * 
 */
package game_engine2D;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * @author rod martin r.martin1@salford.ac.uk
 *
 */
public class Tile extends Sprite {
	public int width;
	public int height;
	int strokeColour;
	int fillColour;
	
	public Tile(PApplet p, int x, int y, int w, int h, String n) {
		super(p, x, y);
		this.transform.size.x = w;
		this.transform.size.y = h;
		this.width = w;
		this.height = h;
		this.name = n;
		this.strokeColour = parent.color(255, 255, 255);
		this.fillColour = parent.color(0, 0, 0);
	}

	@Override
	public void start() {
		super.start();
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		super.render();
		parent.pushMatrix(); // reset the translation and rotation
		parent.translate(this.transform.position.x, this.transform.position.y);
		parent.stroke(this.strokeColour);
		parent.fill(this.fillColour);
		parent.rect(0, 0, this.width, this.height);
		parent.popMatrix();

		/*gameManager.fill(0,255,0);
		gameManager.rectMode(PConstants.CORNERS);
		gameManager.rect(this.transform.position.x + this.transform.boundingBox.left, this.transform.position.y + this.transform.boundingBox.top, this.transform.position.x + this.transform.boundingBox.right, this.transform.position.y + this.transform.boundingBox.bottom);
		gameManager.rectMode(PConstants.CENTER);*/
	}
}