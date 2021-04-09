package Actors;

import Actors.VerilBaseObject;
import javax.swing.ImageIcon;
import Rooms.*;
import Obstacles.Obstacle;
import Items.*;
import Actors.Enemies.Bosses.Boss;

public abstract class Actor extends VerilBaseObject
{
	public static final int DIRECTION_UP = 0;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_DOWN = 2;
	public static final int DIRECTION_LEFT = 3;

	public static final int DIRECTION_CWISE = 0;
	public static final int DIRECTION_CCWISE = 1;

	public static final int INVULNERABLE = -1;

	private int direction;
	private int health;
	private int attack;

	public Actor(int dir, int heal, int att, Location currentRoom, Location curSquare, World w)
	{
		super(currentRoom, curSquare, w);
		direction = dir;
		health = heal;
		attack = att;
	}


	public void setDirection(int d)
	{
		direction = d;
	}
	public void setHealth(int h)
	{
		health = h;
	}
	public void setAttack(int att)
	{
		attack = att;
	}

	public int getDirection()
	{
		return direction;
	}
	public int getHealth()
	{
		return health;
	}
	public int getAttack()
	{
		return attack;
	}


//MOVEMENT
	public void act()
	{
		if (canMove())
			move();
		else
			turn(DIRECTION_CWISE);
	}

	public void turn(int rotation)
	{
		if (rotation == DIRECTION_CWISE)
			direction++;
		else
			direction--;


		if (direction > DIRECTION_LEFT)
			direction = DIRECTION_UP;
		if (direction < DIRECTION_UP)
			direction = DIRECTION_LEFT;
	}

	public boolean canMove()
	{
		Location locInFront = getLocation().nextSquare(direction);
		if (locInFront == null)
			return false;

		Square sqInFront = getWorld().getRoom(getRoomLoc()).getSquare(locInFront);
		if (sqInFront == null)
			return false;

		VerilBaseObject obj = getWorld().getRoom(getRoomLoc()).getObjectAt(getLocation().nextSquare(direction));

		if (obj == null)
			return sqInFront.isWalkable();

		if (obj instanceof Obstacle)
		{
			Obstacle o = (Obstacle) obj;
			if (o.getType() != Obstacle.TYPE_FIRE)
				return o.isWalkable();
			else
				return sqInFront.isWalkable();
		}
		if (obj instanceof Actor)
		{
			return false;
		}
		return sqInFront.isWalkable();
	}

	public void move()
	{
		setLocation(getLocation().nextSquare(direction));
	}



//HEALTH
	public void addHealth(int amt)
	{
		health += amt;
	}
	public void loseHealth(int amt)
	{
		if (health != INVULNERABLE)
		{
			health -= amt;

			if (this instanceof Boss)
			{
				Boss b = (Boss) this;
				if (getHealth() <= 0)
				{
					removeSelf();
					getWorld().getRoom(getRoomLoc()).getVeril().getInventory().addEXP(b.getEXPValue());
					getWorld().add(new Item(Item.TYPE_HEART_FULL, getRoomLoc(), getLocation(), getWorld()), getRoomLoc());
					getRoom().changeTo(b.getMapChange());
					b.causeDeathEffects();
				}
			}
			else if (this instanceof Enemy)
			{
				Enemy e = (Enemy) this;
				if (getHealth() <= 0)
				{
					removeSelf();
					getWorld().getRoom(getRoomLoc()).getVeril().getInventory().addEXP(e.getEXPValue());

					int r = (int)(Math.random()*30);
					if (r < 8)
						getWorld().add(new Item(Item.TYPE_LOVI, 1, getRoomLoc(), getLocation(), getWorld()), getRoomLoc());
					else if (r < 10)
						getWorld().add(new Item(Item.TYPE_ARROW, 5, getRoomLoc(), getLocation(), getWorld()), getRoomLoc());
					else if (r < 13)
						getWorld().add(new Item(Item.TYPE_ARROW, 5, getRoomLoc(), getLocation(), getWorld()), getRoomLoc());
					else if (r < 16)
						getWorld().add(new Item(Item.TYPE_LOVI, 5, getRoomLoc(), getLocation(), getWorld()), getRoomLoc());
					else if (r < 23)
						getWorld().add(new Item(Item.TYPE_HEART_SMALL, getRoomLoc(), getLocation(), getWorld()), getRoomLoc());
				}
			}
		}
	}



	public abstract ImageIcon getImage();

}
