package game_engine2D;
import java.io.File;
import java.util.ArrayList;

import processing.core.PVector;
import processing.data.JSONObject;
import processing.data.JSONArray;
import simple_platformer.Player;

public class ScreenManager{

    GameManager gameManager;
    ScreenManager(GameManager gm)
    {
        gameManager = gm;
    }

    public void SaveScreen()
    {
        File file = new File("C:/Users/Aled/Documents/GitHub/APGamesEngineProgramming/data/test.JSON");
        /*if(!file.exists())
        {
            System.out.println("AAAAaaaAAAAAAaaA");
            if(file.mkdir())System.out.println("AAAAaaaAAAAAAaaA");;
        }
        if(file.mkdir())System.out.println("AAAAaaaAAAAAAaaA");
        else System.out.println("SAD");
        */
        JSONObject currentScreen = new JSONObject();

        //currentScreen.getJSONArray("gameObjects");
        int currentID = 0;
        for (GameObject g : gameManager.gameObjects) {
            JSONArray data =  new JSONArray();
            System.out.println(g.transform.position + " and  " + g.transform.size);
            data.setFloat(0, g.transform.position.x);
            data.setFloat(1, g.transform.position.y);
            data.setFloat(2, g.transform.size.x);
            data.setFloat(3, g.transform.size.y);
            if (g.name == "platform")
            {
                currentScreen.setJSONArray("tile" + currentID , data);
                currentID++;
            }

            else if (g.name == "Player") currentScreen.setJSONArray(g.name, data);

            /*data.setInt(0, g.ID);
            data.setString(1, g.name);
            data.setString(2, g.tag);
            data.setInt(3, g.ID);
            data.setFloat(4, g.transform.position.x);
            data.setFloat(5, g.transform.position.y);
            data.setFloat(6,g.transform.size.x);
            data.setFloat(7,g.transform.size.y);
            data.setFloat(8, g.transform.MaxSpeed.x);
            data.setFloat(9, g.transform.MaxSpeed.y);
            data.setFloat(10, g.transform.rotation.x);
            data.setFloat(11, g.transform.rotation.y);
            data.setFloat(12, g.transform.scale.x);
            data.setFloat(13, g.transform.scale.y);
            currentScreen.setJSONArray(Integer.toString(currentID), data);
            currentID++;*/

        }
        gameManager.parent.saveJSONObject(currentScreen, "data/test.JSON", "");
        //currentScreen.save(file, "");
    }
    public /*ArrayList<GameObject>*/ void LoadScreen(String filePath)
    {

         ArrayList<GameObject> retVal = new ArrayList<>();

         if (filePath == "") filePath = "data/test.JSON";
         
         JSONObject nextScreen = gameManager.parent.loadJSONObject(filePath);

        ArrayList<GameObject> tempGameObjects = new ArrayList<>();
        ArrayList<BoundingBox> tempBoundingBoxs = new ArrayList<>();
        ArrayList<GameObject>  tempPhysicsObjects = new ArrayList<>();
        ArrayList<GameObject> tempPlayerObject = new ArrayList<>();


        /*gameManager.gameObjects = new ArrayList<>();
        gameManager.BoundingBoxs = new ArrayList<>();*/

        int currentID = 0;
        boolean tilesLeft = true;
        while(tilesLeft) {
                JSONArray data = nextScreen.getJSONArray("tile"+currentID);
                 currentID++;
                if(data == null) {
                    tilesLeft = false;
                    break;
                }
                PVector pos = new PVector(data.getFloat(0), data.getFloat(1));
                PVector size = new PVector(data.getFloat(2), data.getFloat(3));
                Tile platform = new Tile(gameManager.parent, (int)pos.x, (int)pos.y,(int)size.x, (int)size.y, "platform");
                platform.start();
                platform.transform.boundingBox.FromSize(new PVector(platform.width, platform.height));



                tempGameObjects.add(platform);
                tempBoundingBoxs.add(platform.transform.boundingBox);

        }

        for (GameObject g: gameManager.playerGameObjects
        ) {
            JSONArray data = nextScreen.getJSONArray(g.name);
            tempGameObjects.add(g);
             PVector pos = new PVector((int)data.getFloat(0), data.getFloat(1));
            g.transform.velocity = new PVector(0, 0);

            Player player = new Player(gameManager.parent,pos.x, pos.y ,60, 60, "Player");
            player.start();
            player.transform.boundingBox.FromSize(player.transform.size);
            tempPlayerObject.add(player);
            tempPlayerObject.add(player);

            tempPhysicsObjects.add(player);

            Camera2D camera = new Camera2D(gameManager.parent, player,99);
            camera.CameraOffset.y = 99;
            tempGameObjects.add(camera);
        }

        replaceLists(tempGameObjects, tempBoundingBoxs, tempPhysicsObjects, tempPlayerObject);
         //return retVal;
    }
    private void replaceLists(ArrayList<GameObject> go, ArrayList<BoundingBox> bb, ArrayList<GameObject> physgo, ArrayList<GameObject> playgo )
    {
        gameManager.gameObjects = go;

        gameManager.BoundingBoxs = bb;

        gameManager.physicsGameObject = physgo;

        gameManager.playerGameObjects = playgo;
    }
}
