package Actors.Enemies;


import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;


public class Slime extends Enemy
{
    public static final int BASE_HEALTH = 7;
    public static final int BASE_ATTACK = 4;

    public Slime(int strength, Location roomLoc, World w)
    {
        super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 4, roomLoc, false, w);
    }

    public void act()
    {
        if (canAttack())
            attack(getWorld().getRoom(getRoomLoc()).getVeril());
        else
            doMove();
    }

    public void doMove()
    {
        if (within(5))
        {
            int d = directionTowardsVeril();
            if (getDirection() != d)
                setDirection(d);
            else if (canMove())
                move();    
        }
        else
        {
            int r = (int) (Math.random() * 8);
            if (r < 4)
                setDirection(r);
            else if (canMove())
                move();
        }
    }


    public ImageIcon getImage()
    {
        return getImage("Slime");
    }
}
