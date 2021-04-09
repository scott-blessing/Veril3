package Actors.Enemies;


import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;


public class Penguin extends Enemy
{
    public static final int BASE_HEALTH = 15;
    public static final int BASE_ATTACK = 1;

    private boolean sliding;

    public Penguin(int strength, Location roomLoc, World w)
    {
            super((int)(Math.random()*4), BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 1, w.getOpenLocation(roomLoc, false), roomLoc, false, w);
            sliding = false;
    }

    @Override
    public void act()
    {
        setMoveLimit(3);
        if (canAttack())
        {
            setMoveLimit(7);
            sliding = false;
            attack(getWorld().getRoom(getRoomLoc()).getVeril());
        }
        else if (canSeeVeril())
        {
            sliding = true;
            if (canMove()) move();
        }
        else
        {
            sliding = false;
            setMoveLimit(7);
            doMove();
        }
    }

    public void doMove()
    {
            if (within(5))
            {
                    int d = directionTowardsVeril();
                    if (getDirection() == d && canMove())
                            move();
                    else
                            setDirection(d);
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

    public void removeSelf()
    {
            super.removeSelf();
            sliding = false;
    }

    public ImageIcon getImage()
    {
        if (sliding)
            return getImage("Penguin/Sliding");
        else
            return getImage("Penguin/Standing");
    }
}
