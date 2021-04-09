package Actors.Enemies;

import Actors.*;
import Rooms.*;
import Items.Arrow;
import javax.swing.ImageIcon;

public class Turret extends Enemy
{
	public static final int BASE_HEALTH = 5;
	public static final int BASE_ATTACK = 1;
	private int waitTime;
	private int timeWaited;

	public Turret(int wait, int direction, Location loc, Location roomLoc, World w)
	{
		super(direction, BASE_HEALTH, BASE_ATTACK, 0, 0, loc, roomLoc, false, w);
		waitTime = wait;
		timeWaited = 0;
	}

	public void act()
	{
		timeWaited++;
		if (timeWaited == waitTime)
		{
			shootBow(Arrow.ARROW);
			timeWaited = 0;
		}
	}

	public void doMove(){}

	public int getEXPValue()
	{
		return 2;
	}


	public ImageIcon getImage()
	{
		return getImage("Turret");
	}

}
