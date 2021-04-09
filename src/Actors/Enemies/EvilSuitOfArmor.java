package Actors.Enemies;


import Actors.*;
import Rooms.*;
import javax.swing.ImageIcon;


public class EvilSuitOfArmor extends Enemy
{
	public static final int BASE_HEALTH = 15;
	public static final int BASE_ATTACK = 1;

	private boolean revealed;
	private Location initLoc;
	private int initDir;

	public EvilSuitOfArmor(int dir, int strength, Location square, Location roomLoc, World w)
	{
		super(dir, BASE_HEALTH + (strength*2), BASE_ATTACK, strength, 1, square, roomLoc, false, w);
		revealed = false;
		initLoc = square;
		initDir = dir;
	}

	public void act()
	{
		setMoveLimit(2);
		if (canAttack())
		{
			setMoveLimit(5);
			attack(getWorld().getRoom(getRoomLoc()).getVeril());
		}
		else if (canSeeVeril())
		{
			revealed = true;
			if (canMove()) move();
		}
		else if (revealed)
		{
			setMoveLimit(4);
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
		setLocation(initLoc);
		setDirection(initDir);
		revealed = false;
	}

	public ImageIcon getImage()
	{
		return getImage("EvilSuitOfArmor");
	}

	public ImageIcon getImage(String name)
	{
		String fp = "images/Enemy/"+name+"/";
		if (!revealed)
			fp += "Down";
		else
		{
			if (getDirection() == Actor.DIRECTION_UP)
				fp += "Up";
			else if (getDirection() == Actor.DIRECTION_LEFT)
				fp += "Left";
			else if (getDirection() == Actor.DIRECTION_DOWN)
				fp += "Down";
			else
				fp += "Right";
		}
		fp += ".png";

		return new ImageIcon(fp);
	}
}
