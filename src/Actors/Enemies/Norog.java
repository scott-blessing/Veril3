package Actors.Enemies;


import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;


public class Norog extends Enemy
{
	public static final int BASE_HEALTH = 15;
	public static final int BASE_ATTACK = 3;

	public Norog(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 7, roomLoc, false, w);
	}

	public void act()
	{
		setMoveLimit(7);
		if (canAttack())
			attack(getRoom().getVeril());
		else if (canSeeVeril())
		{
			setMoveLimit(1);
			if (canMove()) move();
		}
		else
			doMove();
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


	public ImageIcon getImage()
	{
		return getImage("Norog");
	}
}
