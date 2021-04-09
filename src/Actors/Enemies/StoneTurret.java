package Actors.Enemies;

import Actors.Enemies.Turret;
import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;

public class StoneTurret extends Turret
{
	public StoneTurret(int wait, int direction, Location loc, Location roomLoc, World w)
	{
		super(wait, direction, loc, roomLoc, w);
		setHealth(Actor.INVULNERABLE);
	}

	public int getEXPValue()
	{
		return 0;
	}

	public ImageIcon getImage()
	{
		return getImage("StoneTurret");
	}

}
