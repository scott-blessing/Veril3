package Actors.Enemies;


import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;
import Items.Arrow;


public class Wizard extends Enemy
{
	public static final int BASE_HEALTH = 10;
	public static final int BASE_ATTACK = 2;

	public Wizard(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 4, roomLoc, false, w);
	}

	public void act()
	{
		if (canSeeVeril())
			shootBow(Arrow.FIRE);
		else
		{
			int r = (int)(Math.random() * 10);
			if (r==0)
				shootBow(Arrow.FIRE);
			else
				doMove();
		}
	}

	public void doMove()
	{
		if (within(1))
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


	public ImageIcon getImage()
	{
		return getImage("Wizard");
	}
}
