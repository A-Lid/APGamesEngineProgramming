package game_engine2D;

import processing.core.PVector;

import java.security.PublicKey;
import java.util.ArrayList;



public class Physics2D {

    private float gravity = 0.1f;

    private float friction = 0.9f;
    private float frictionOverride = 1f;
    private float frictionNormal = 0.9f;
    public boolean ColCheck(GameObject check, ArrayList<BoundingBox> collisions)
    {
        check.IsGrounded = false;
        boolean retVal = false;
        //System.out.println(check.transform.position.y);
        BoundingBox bb1 = check.transform.WorldBoundingBox();
        for (BoundingBox bb2: collisions) {



            //System.out.println(bb1.right + " " + bb2.left + " " + bb1.left + " " + bb2.right);
            if (bb1.right > bb2.left && bb1.left < bb2.right)
            {
                //System.out.println("Gone through the middle");
                if (bb1.bottom > bb2.top && bb1.top < bb2.top)
                {
                    check.Collided = "TOP";
                    if (check.transform.velocity.y > 0) check.transform.velocity.y = 0;
                    //check.transform.position.y = (bb1.top -((check.transform.size.y/2) +(bb2.objectSize.y/2) ));
                    //System.out.println(bb2.top + " " + check.transform.size + " " + bb1.top + " " + bb1.bottom);
                    check.transform.position.y = (bb2.top - (check.transform.size.y/2));
                    //System.out.println(check.transform.position.y);


                    //System.out.println("Consider yourself collided with top of  " + check.name);
                    retVal = true;
                    check.IsGrounded = true;
                }
                else if (bb1.top < bb2.bottom && bb1.top > bb2.top)
                {
                    check.Collided = "BOTTOM";
                    check.transform.position.y = bb2.bottom;
                    check.transform.position.y = (bb2.bottom + 1+  (check.transform.size.y/2));
                    System.out.println("Consider yourself collided with bottom of " + check.name);
                    retVal = true;
                }
                //Aled Continue Writing this
            }
            else check.Collided = "NONE";
        }


        return retVal;
    }
    public void Gravity(GameObject g)
    {
        if (g.Collided != "TOP") g.transform.velocity.y += gravity;

    }

    public void UpdatePosition(GameObject g)
    {




        g.transform.position.x += g.transform.velocity.x;
        g.transform.position.y += g.transform.velocity.y;
    }

    public void AddForce(GameObject g, PVector Force)
    {

        g.transform.velocity.x += Force.x;
        g.transform.velocity.y += Force.y;
    }

    public void SetVelocity(GameObject g, PVector Force)
    {
        g.transform.velocity.x = Force.x;
        g.transform.velocity.y = Force.y;
    }

    public void ApplyFriction(GameObject g)
    {
        g.transform.velocity.x *= friction;
        /*if(g.transform.velocity.x > 0) g.transform.velocity.x -= friction;
        else if (g.transform.velocity.x < 0) g.transform.velocity.x += friction;*/
    }
    public void Move(GameObject g, float Force)
    {
        g.transform.velocity.x += Force;
        friction = frictionOverride;
    }
    public void ChangeFriction()
    {
        friction = frictionNormal;
    }
}
