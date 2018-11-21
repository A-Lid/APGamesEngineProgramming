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
        physicsGameObject = new ArrayList<GameObject>();
        playerGameObjects = new ArrayList<GameObject>();
    }



    // a list of all the game objects in the game (?)
    private ArrayList<GameObject> gameObjects;

    private ArrayList<BoundingBox> BoundingBoxs;

    private ArrayList<GameObject> physicsGameObject;

    private ArrayList<GameObject> playerGameObjects;

    //function for adding a game object to the list
    public void addObject(GameObject g){
        gameObjects.add(g);
    }

    public void addPhysicsObject(GameObject g) { physicsGameObject.add(g);};

    public void addBoundingBox(GameObject b){BoundingBoxs.add(b.transform.WorldBoundingBox());}

    public void addPlayerObject(GameObject g) { playerGameObjects.add(g);};

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

        for (GameObject g: physicsGameObject)
        {
            if(g.physics2D.ColCheck(g, BoundingBoxs));
            g.physics2D.Gravity(g);
            g.physics2D.UpdatePosition(g);
            if(g.IsGrounded)g.physics2D.ApplyFriction(g);
        }

        
    	parent.background(background);
        for(int i = 0; i < gameObjects.size(); i++){
        	
            GameObject g = gameObjects.get(i);
            g.update();
            g.render();
        }

    }
    public void KeyPressed(char key, int keyCode){
        for (GameObject player:playerGameObjects) {
            player.KeyPressed(key, keyCode);
        }
    }
    public void KeyReleased(char key, int keyCode){
        for (GameObject player:playerGameObjects) {
            player.KeyReleased(key, keyCode);
        }
    }

}
