package game_engine2D;
import java.io.File;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;

import java.util.ArrayList;

public class ScreenManager{

    GameManager parent;
    ScreenManager(GameManager gm)
    {
        parent = gm;
    }

    public void SaveScreen()
    {
        File file = new File("C:/Users/Aled/Documents/GitHub/APGamesEngineProgrammingdata/test.json");
        if(!file.exists())
        {
            System.out.println("AAAAaaaAAAAAAaaA");
            if(file.mkdir())System.out.println("AAAAaaaAAAAAAaaA");;
        }
        if(file.mkdir())System.out.println("AAAAaaaAAAAAAaaA");
        else System.out.println("SAD");
        JSONObject currentScreen = new JSONObject();
        JSONArray data =  new JSONArray();
        //currentScreen.getJSONArray("gameObjects");
        int currentID = 0;
        for (GameObject g : parent.gameObjects) {
            data.setInt(0, g.ID);
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
            currentID++;

        }
        currentScreen.save(file, "");



    }
}
