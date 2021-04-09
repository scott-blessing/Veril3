package Actors;

import Actors.*;
import Rooms.*;
import GUI.Inventory;
import Items.Arrow;
import javax.swing.ImageIcon;

public abstract class Enemy extends NPC
{
	private int strength;
	private int baseHealth;
	private int moveCount;
	private int moveTime;
	private int moveLimit;
	private boolean waterBased;

	public Enemy(int dir, int heal, int att, int str, int moveLim, Location roomLoc, boolean water, World w)
	{
		super(dir,heal,att,roomLoc,w.getOpenLocation(roomLoc, water),w);
		waterBased = water;
		strength = str;
		baseHealth = heal;
		moveCount = 0;
		moveLimit = moveLim;
		moveTime = moveLimit;
	}
	public Enemy(int dir, int heal, int att, int str, int moveLim, Location loc, Location roomLoc, boolean water, World w)
	{
		super(dir,heal,att,roomLoc,loc,w);
		waterBased = water;
		strength = str;
		baseHealth = heal;
		moveCount = 0;
		moveLimit = moveLim;
		moveTime = moveLimit;
	}
        public Enemy(int dir, int heal, int att, int str, int moveLim, Location roomLoc, int tileType, World w)
	{
		super(dir,heal,att,roomLoc,w.getOpenLocationOfTile(roomLoc, tileType),w);
		waterBased = false;
		strength = str;
		baseHealth = heal;
		moveCount = 0;
		moveLimit = moveLim;
		moveTime = moveLimit;
	}

	public int getStrength()
	{
		return strength;
	}
	public int getAttack()
	{
		if (super.getAttack() == 0)
			return 0;
		else
			return super.getAttack();
	}
	public int getEXPValue()
	{
		int exp = (baseHealth + getAttack()) / 4;
		return exp;
	}
	public int getBaseHealth()
	{
		return baseHealth;
	}

	public void setStrength(int str)
	{
		strength = str;
	}
	public void setWaterBased(boolean water)
	{
		waterBased = water;
	}

////////MOVEMENT///////////////////////////////////////////////

	public void act()
	{
		moveCount++;
	}

	public void countUp()
	{
		moveCount++;
	}
	public int getCount()
	{
		return moveCount;
	}
	public void reset()
	{
		moveCount = 0;
		moveTime = (int)(Math.random() * 3) + moveLimit - 1;
		if (moveTime < 1)
			moveTime = 1;
	}
	public int getMoveTime()
	{
		return moveTime;
	}

	public void setMoveLimit(int newLimit)
	{
		moveLimit = newLimit;
	}

	public boolean canAttack()
	{
		Location loc = getLocation().nextSquare(getDirection());
		if (loc != null)
		{
			VerilBaseObject v = getWorld().getObjectAt(getRoomLoc(), loc);
			if (v instanceof Veril)
				return true;
		}
		return false;
	}
	public void attack(Veril v)
	{
		int baseDamage = getAttack();
		int vHealth = v.getHealth();
		v.loseHealth(baseDamage - v.getInventory().getDefenseBonus());
	}

	public boolean canSeeVeril()
	{
		Veril v = getWorld().getRoom(getRoomLoc()).getVeril();
		if (getDirection() == Actor.DIRECTION_UP)
			return (v.getLocation().getX() == getLocation().getX() && v.getLocation().getY() < getLocation().getY());
		else if (getDirection() == Actor.DIRECTION_DOWN)
			return (v.getLocation().getX() == getLocation().getX() && v.getLocation().getY() > getLocation().getY());
		else if (getDirection() == Actor.DIRECTION_LEFT)
			return (v.getLocation().getX() < getLocation().getX() && v.getLocation().getY() == getLocation().getY());
		else
			return (v.getLocation().getX() > getLocation().getX() && v.getLocation().getY() == getLocation().getY());
	}
	public void shootBow(String type)
	{
		getRoom().addArrow(  getLocation(), getDirection(), getAttack(), type );
	}
	public void shootBow(String type, int dam)
	{
		getRoom().addArrow(  getLocation(), getDirection(), dam, type );
	}

	public abstract void doMove();

	public boolean within(int amt)
	{
		Location L = getRoom().getVeril().getLocation();
		int x = Math.abs(L.getX() - getLocation().getX());
		int y = Math.abs(L.getY() - getLocation().getY());
		return (x <= amt && y <= amt);
	}
	public int directionTowardsVeril()
	{
		Location L = getRoom().getVeril().getLocation();
		int x = L.getX() - getLocation().getX();
		int y = L.getY() - getLocation().getY();
		int X = Math.abs(x);
		int Y = Math.abs(y);
		if (X >= Y)
		{
			if (x < 0)
				return Actor.DIRECTION_LEFT;
			else
				return Actor.DIRECTION_RIGHT;
		}
		else
		{
			if (y < 0)
				return Actor.DIRECTION_UP;
			else
				return Actor.DIRECTION_DOWN;
		}
	}

////////HIT///////////////////////////////////////////////////////

	public void hitByFire()
	{
		loseHealth(1);
	}
	public void hit(int dam)
	{
		loseHealth(dam);
	}

////////RANDOMIZATION/////////////////////////////////////////////

	public void randomizeLoc()
	{
		setLocation(getWorld().getOpenLocation(getRoomLoc(), waterBased));
	}

////////IMAGES////////////////////////////////////////////////////
	public abstract ImageIcon getImage();

	public ImageIcon getImage(String name)
	{
		String fp = "images/Enemy/"+name+"/";
		if (getDirection() == Actor.DIRECTION_UP)
			fp += "Up";
		else if (getDirection() == Actor.DIRECTION_LEFT)
			fp += "Left";
		else if (getDirection() == Actor.DIRECTION_DOWN)
			fp += "Down";
		else
			fp += "Right";

		//fp += "/0";

		fp += ".png";

		return new ImageIcon(fp);
	}
}
