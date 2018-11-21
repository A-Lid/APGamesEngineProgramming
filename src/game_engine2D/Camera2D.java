package game_engine2D;

import processing.core.PApplet;
import processing.core.PVector;

public class Camera2D extends GameObject {
    public GameObject Following;
    public float OffsetLimit = 100;
    public  PVector CameraOffset = new PVector(0,0);
    public Camera2D(PApplet p, GameObject g)
    {
        super(p);
        Following = g;
    }
    public Camera2D(PApplet p, GameObject g, float limit)
    {
        super(p);
        Following = g;
        OffsetLimit = limit;
    }

    @Override
    public void start(){

    }
    @Override
    public void update(){

    }
    @Override
    public void render(){
        late_update_xy();
    }
    private void late_update_xy()
    {
        PVector virtualScreenCentre = GameManager.ScreenOffset.copy();
        virtualScreenCentre.add(GameManager.Offset);

        PVector virtualPlayer = GameManager.Offset.copy();
        virtualPlayer.add(this.Following.transform.position);
        float d = virtualScreenCentre.dist(virtualPlayer);
        if (d > OffsetLimit)
        {
            virtualScreenCentre.sub(virtualPlayer);

            virtualScreenCentre.add(this.CameraOffset);
            GameManager.Offset.lerp(virtualScreenCentre, 0.2f);
        }
    }

}
