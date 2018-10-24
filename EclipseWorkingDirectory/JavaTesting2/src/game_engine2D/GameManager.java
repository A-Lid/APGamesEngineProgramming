package game_engine2D;
import processing.core.*;
import java.util.ArrayList;
/* Example of basic Launch processing applet*/
public class GameManager {
    public PApplet parent; // The parent PApplet that we will render ourselves onto
    public int background = 0;
    public GameManager(PApplet p){
        parent = p;
        gameObjects = new ArrayList<GameObject>();
        BoundingBoxs = new ArrayList<BoundingBox>();
    }

    // a list of all the game objects in the game (?)
    private ArrayList<GameObject> gameObjects;

    private ArrayList<BoundingBox> BoundingBoxs;

    //function for adding a game object to the list
    public void addObject(GameObject g){
        gameObjects.add(g);
    }

    public void addBoundingBox(GameObject b){BoundingBoxs.add(b.transform.WorldBoundingBox());}

    //function for removing a game object from the list
    public void removeObject(GameObject g){
        gameObjects.remove(gameObjects.lastIndexOf(g));
    }
    
    //Creates all of the game objects at the start by running through the list.
    public void StartAll() {
    	
        for(int i = 0; i < gameObjects.size(); i++){
            GameObject g = gameObjects.get(i);
            g.start();
        }
    }
    //updates the game object when it's called.
    public void UpdateAll() {

        for (BoundingBox bb1:BoundingBoxs) {
            for (BoundingBox bb2: BoundingBoxs) {
                if (bb1.bottom < bb2.top)
                {

                    //Aled Continue Writing this
                }
            }
        }
        
    	parent.background(background);
        for(int i = 0; i < gameObjects.size(); i++){
        	
            GameObject g = gameObjects.get(i);
            g.update();
            g.render();
        }
    }

}
