package Actors.Enemies;

import Actors.*;
import Items.Arrow;
import Rooms.*;
import Obstacles.*;
import javax.swing.ImageIcon;


public class Melter extends Enemy
{
	public static final int BASE_HEALTH = 4;
	public static final int BASE_ATTACK = 1;

	public Melter(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 4, roomLoc, 46 /*Lava*/, w);
                counter = (int) (Math.random() * 20);
        }
        
        private int state = 0; //0= Under lava, 1=Emerging, 2=Above Lava
        private int counter;
        private static final int OVERDUE = 20;

	public void act()
	{
            counter++;
            if (state == 2)
            {
                if (canSeeVeril())
                    shootBow(Arrow.FIRE);
                else if ((int)(Math.random()*4) == 0) //25% chance
                    shootBow(Arrow.FIRE);
            }
            else if (state == 1)
            {
                counter = 0;
                state = 2;
            }
            else if (state == 0)
            {
                setLocation(getWorld().getOpenLocationOfTile(getRoomLoc(),46));
            }
            
            if (counter == (int)(Math.random() * 5)+15 || counter == OVERDUE)
            {
                if (state == 0)
                {
                    state = 1;
                    setDirection(directionTowardsVeril());
                }
                else if (state == 2)
                    state = 0;
                
                counter = 0;
            }
	}

	public void doMove()
	{}
        
        public void hitByFire()
        {
            //Do nothing
        }

	public boolean canMove()
	{
            return true;
        }

	public ImageIcon getImage()
	{
            if (state == 1)
                return new ImageIcon("images/Enemy/Melter/Ripple.png");
            else if (state == 2)
                return getImage("Melter");
            else
                return null;
	}
}
