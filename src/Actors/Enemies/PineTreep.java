package Actors.Enemies;

import Actors.Enemies.Treep;
import Rooms.*;
import javax.swing.ImageIcon;

public class PineTreep extends Treep
{
	public static final int BASE_HEALTH = 6;
	public static final int BASE_ATTACK = 4;

	public PineTreep(int strength, Location roomLoc, World w)
	{
		super(strength, roomLoc, w);
		setHealth(BASE_HEALTH + (strength*2));
		setAttack(BASE_ATTACK);
	}

	public ImageIcon getImage()
	{
		return getImage("PineTreep");
	}
}
