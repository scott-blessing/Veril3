package Actors.Enemies;

import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;


public class Raccoon extends Enemy
{
	public static final int BASE_HEALTH = 5;
	public static final int BASE_ATTACK = 2;

	public Raccoon(int strength, Location roomLoc, World w)
	{
		super((int)Math.random()*4, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 10, roomLoc, false, w);
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
		int r = (int) (Math.random() * 8);
		if (r < 4)
			setDirection(r);
		else if (canMove())
			move();
	}


	public ImageIcon getImage()
	{
		return getImage("Raccoon");
	}
}
