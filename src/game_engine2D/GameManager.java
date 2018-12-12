package game_engine2D;
import processing.core.*;
import java.util.ArrayList;
/* Example of basic Launch processing applet*/
public class GameManager {
    public ScreenManager screenManager;
    public PApplet parent; // The gameManager PApplet that we will render ourselves onto
    public int background = 0;
    public static PVector Offset = new PVector(0,0);
    public static PVector ScreenOffset = new PVector(0,0);
    public GameManager(PApplet p){

        screenManager = new ScreenManager(this);

        parent = p;
        gameObjects = new ArrayList<GameObject>();
        BoundingBoxs = new ArrayList<BoundingBox>();
        physicsGameObject = new ArrayList<GameObject>();
        playerGameObjects = new ArrayList<GameObject>();
        ScreenOffset.x = parent.width/2;
        ScreenOffset.y = parent.height/2;

    }
    private boolean isMakingPlatform = false;
    private PVector mouseCoords = new PVector();





    // a list of all the game objects in the game (?)
    public ArrayList<GameObject> gameObjects;

    public ArrayList<BoundingBox> BoundingBoxs;

    public ArrayList<GameObject> physicsGameObject;

    public ArrayList<GameObject> playerGameObjects;

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
        parent.pushMatrix();
        parent.translate(Offset.x, Offset.y);
        for (GameObject g: physicsGameObject)
        {
            if(g.physics2D.ColCheck(g, BoundingBoxs));
            g.physics2D.Gravity(g);
            g.physics2D.UpdatePosition(g);
            if(g.IsGrounded)g.physics2D.ApplyFriction(g);
        }


        if(isMakingPlatform)makePlatform(mouseCoords);
        
    	parent.background(background);
        for(int i = 0; i < gameObjects.size(); i++){
        	
            GameObject g = gameObjects.get(i);
            g.update();
            g.render();
        }
        parent.popMatrix();

    }
    public void KeyPressed(char key, int keyCode){
        for (GameObject player:playerGameObjects) {
            player.KeyPressed(key, keyCode);
        }
        if (key == 's') SaveScreen();
        if (key == 'l') LoadScreen();
    }
    public void KeyReleased(char key, int keyCode){
        for (GameObject player:playerGameObjects) {
            player.KeyReleased(key, keyCode);
        }
    }
    public void mouseClicked(int mouseX, int mouseY)
    {
        mouseCoords = new PVector(mouseX,mouseY);
        isMakingPlatform = true;

    }
    public  void SaveScreen()
    {
        this.screenManager.SaveScreen();
    }
    public  void LoadScreen()
    {
        this.screenManager.LoadScreen("data/test.JSON");
    }
    private void makePlatform(PVector Coords)
    {
        isMakingPlatform = false;
        System.out.println(Coords + " " + mouseCoords);
        Tile platform = new Tile(parent, Math.round(Coords.x-Offset.x), Math.round(Coords.y-Offset.y) ,50, 20, "platform");
        platform.start();
        platform.transform.boundingBox.FromSize(new PVector(platform.width, platform.height));




        this.addObject(platform);
        this.addBoundingBox(platform);

    }

}
