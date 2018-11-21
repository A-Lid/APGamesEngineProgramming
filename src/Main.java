import processing.core.PApplet;
import simple_platformer.Launcher;
public class Main extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Main");
	}
	Launcher launcher;
	public void setup() {
		rectMode(CENTER);
		background(0);
		launcher = new Launcher(this);
		launcher.StartGame();

	}
	public void draw() {
		launcher.UpdateAll();
	}
	public void settings() {
		size(640,480);
	}

	public void keyPressed()
	{
		launcher.KeyPressed(key, keyCode);
	}
	public void keyReleased()
	{
		launcher.KeyReleased(key, keyCode);
	}
	public void mouseClicked()
	{
		launcher.mouseClicked(mouseX, mouseY);
	}
}
