package simple_platformer;


import game_engine2D.*;
import processing.core.PApplet;
import processing.core.PVector;


public class Launcher extends BaseLauncher{

	public Launcher(PApplet p) {
		super(p);
	}

	public void StartGame(){
		super.StartGame();
		//this sets up all the things that have to be in the game at the start. At the moment, this is players and platforms.
        Player player = new Player(parent, parent.width/2,parent.height/2,60, 60, "Player");
        player.start();
        player.transform.boundingBox.FromSize(player.transform.size);
        this.gameManager.addObject(player);
        //this.gameManager.addBoundingBox(player);
        this.gameManager.addCollidingObject(player);
   
        
        int platforms = 8;
        for(int i = 0; i < platforms; i++){
            Tile platform = new Tile(parent, 25 + i * 55, parent.height-50,50, 20, "platform");
            platform.start();
            platform.transform.boundingBox.FromSize(new PVector(platform.width, platform.height));
            this.gameManager.addObject(platform);
            this.gameManager.addBoundingBox(platform);
}
        //todo add platforms ...
    }
	  public void UpdateAll(){
	        super.UpdateAll();
	    }

}
