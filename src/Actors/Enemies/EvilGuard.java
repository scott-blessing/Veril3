package Actors.Enemies;


import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;


public class EvilGuard extends Enemy
{
	public static final int BASE_HEALTH = 12;
	public static final int BASE_ATTACK = 1;

	public EvilGuard(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 6, roomLoc, false, w);
	}

	public void act()
	{
		setMoveLimit(6);
		if (canAttack())
			attack(getRoom().getVeril());
		else if (canSeeVeril())
		{
			setMoveLimit(2);
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
		return getImage("EvilGuard");
	}
}
