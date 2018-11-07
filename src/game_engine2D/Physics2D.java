package game_engine2D;

import java.util.ArrayList;



public class Physics2D {

    private float gravity = 0.01f;


    public boolean ColCheck(GameObject check, ArrayList<BoundingBox> collisions)
    {
        boolean retVal = false;
        System.out.println(check.transform.position.y);
        BoundingBox bb1 = check.transform.WorldBoundingBox();
        for (BoundingBox bb2: collisions) {



            //System.out.println(bb1.right + " " + bb2.left + " " + bb1.left + " " + bb2.right);
            if (bb1.right > bb2.left && bb1.left < bb2.right)
            {
                //System.out.println("Gone through the middle");
                if (bb1.bottom > bb2.top && bb1.top < bb2.top)
                {
                    check.Collided = "TOP";
                    check.transform.velocity.y = 0;
                    //check.transform.position.y = (bb1.top -((check.transform.size.y/2) +(bb2.objectSize.y/2) ));
                    //System.out.println(bb2.top + " " + check.transform.size + " " + bb1.top + " " + bb1.bottom);
                    check.transform.position.y = (bb2.top - (check.transform.size.y/2));
                    //System.out.println(check.transform.position.y);


                    System.out.println("Consider yourself collided with top of  " + check.name);
                    retVal = true;
                }
                else if (bb1.top < bb2.bottom && bb1.top > bb2.top)
                {
                    check.Collided = "BOTTOM";
                    check.transform.position.y = bb2.bottom;
                    System.out.println("Consider yourself collided with bottom of " + check.name);
                    retVal = true;
                }
                //Aled Continue Writing this
            }
            else check.Collided = "NONE";
        }


        return retVal;
    }
    public void Gravity(GameObject affectedObject)
    {
        if (affectedObject.Collided != "TOP") affectedObject.transform.velocity.y += gravity;
        affectedObject.transform.position.x += affectedObject.transform.velocity.x;
        affectedObject.transform.position.y += affectedObject.transform.velocity.y;
    }
}
