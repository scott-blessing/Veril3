package Actors.Enemies;

import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;
import Items.Arrow;


public class FireRaccoon extends Enemy
{
    public static final int BASE_HEALTH = 15;
    public static final int BASE_ATTACK = 7;

    public FireRaccoon(int strength, Location roomLoc, World w)
    {
            super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 7, roomLoc, false, w);
    }

    public void act()
    {
        if (canAttack())
            attack(getWorld().getRoom(getRoomLoc()).getVeril());
        else if (canSeeVeril())
            shootBow(Arrow.FIRE);
        else
            doMove();
    }

    public void doMove()
    {
        if (within(5))
        {
            int dir = directionTowardsVeril();
            if (getDirection() != dir)
                setDirection(dir);
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
            return getImage("FireRaccoon");
    }
}