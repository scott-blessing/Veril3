package Items;

import Actors.*;
import Rooms.*;
import Obstacles.*;
import javax.swing.ImageIcon;
import java.io.Serializable;
import GUI.AePlayWave;

public class Arrow implements Serializable
{
	public static final int ARROW_MOVE_DISTANCE = 10;
	public static final int SIDE = 20;

	public static final String ARROW = "Arrows";
	public static final String FIRE = "Fireballs";
	public static final String BOLT = "Bolts";
	public static final String PENCIL = "Pencils";
	public static final String FIREARROW = "FireArrows";
	public static final String SPEAR = "Spears";
	public static final String ICE = "Ice";

	private int x;
	private int y;
	private int damage;
	private int direction;
	private Room room;
	private String type;

	private boolean done;

	public Arrow(Location startLoc, int dir, int dam, Room r, String type)
	{
		int X = startLoc.getX() * 50;
		if (dir == Actor.DIRECTION_RIGHT)
			X += 50;
		if (dir == Actor.DIRECTION_UP || dir == Actor.DIRECTION_DOWN)
			X += 20;
		if (dir == Actor.DIRECTION_LEFT)
			X -= 20;
		x = X;
		int Y = startLoc.getY() * 50;
		if (dir == Actor.DIRECTION_DOWN)
			Y += 50;
		if (dir == Actor.DIRECTION_RIGHT || dir == Actor.DIRECTION_LEFT)
			Y += 20;
		if (dir == Actor.DIRECTION_UP)
			Y -= 20;
		y = Y;
		direction = dir;
		damage = dam;
		room = r;
		done = false;
		this.type = type;
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getDamage()
	{
		return damage;
	}
	public int getDirection()
	{
		return direction;
	}
	public int getWidth()
	{
		return SIDE;
	}
	public int getHeight()
	{
		return SIDE;
	}
	public boolean isVertical()
	{
		return (direction == Actor.DIRECTION_UP || direction == Actor.DIRECTION_DOWN);
	}
	public boolean isDone()
	{
		return done;
	}

	public void setX(int newX)
	{
		x = newX;
	}
	public void setY(int newY)
	{
		y = newY;
	}
	public void setDamage(int newDam)
	{
		damage = newDam;
	}
	public void setDirection(int newDir)
	{
		direction = newDir;
	}



	public void move()
	{
		if (direction == Actor.DIRECTION_UP)
		{
			y -= ARROW_MOVE_DISTANCE;
		}
		else if (direction == Actor.DIRECTION_DOWN)
		{
			y += ARROW_MOVE_DISTANCE;
		}
		else if (direction == Actor.DIRECTION_LEFT)
		{
			x -= ARROW_MOVE_DISTANCE;
		}
		else if (direction == Actor.DIRECTION_RIGHT)
		{
			x += ARROW_MOVE_DISTANCE;
		}
		if (checkForOutOfMap() || checkForHit())
			done = true;
	}



	private boolean checkForOutOfMap()
	{
		if (x < 0 || x > 800 || y < 0 || y > 600)
			return true;
		return false;
	}
	private boolean checkForHit()
	{
		VerilBaseObject vbo = room.getObjectAt(getAproxLocation());
		if (vbo == null)
			return false;

		if (vbo instanceof Actor)
		{
			Actor a = (Actor) vbo;
                        if (a instanceof Enemy)
                            ((Enemy) a).hit(damage);
                        else
                            a.loseHealth(damage);
			if (type.equals(FIREARROW))
			{
				vbo.getWorld().add(new Fire(5, true, vbo.getRoomLoc(), getAproxLocation(), vbo.getWorld()), vbo.getRoomLoc());
				new AePlayWave("Music/Clips/Torch-Miss.wav").start();
			}
			else if (type.equals(FIRE))
			{
				vbo.getWorld().add(new Fire(5, false, vbo.getRoomLoc(), getAproxLocation(), vbo.getWorld()), vbo.getRoomLoc());
				new AePlayWave("Music/Clips/Torch-Miss.wav").start();
			}
			else if (type.equals(ICE) && a instanceof Veril)
			{
				((Veril) a).becomeFrozen();
			}
			return true;
		}
		else if (vbo instanceof Obstacle)
		{
			if (vbo instanceof Switch)
			{
				((Switch) vbo).interact();
			}
			return !((Obstacle) vbo).isWalkable();
		}
		return false;
	}


	private Location getAproxLocation()
	{
		int locx = x / 50;
		int locy = y / 50;
		return new Location(locx, locy);
	}


	public void removeSelf()
	{
		room.removeArrow(this);
	}

	public ImageIcon getImage()
	{
		String fp = "images/Items/Projectiles/"+type+"/";
		if (direction == Actor.DIRECTION_UP)
			fp += "U";
		if (direction == Actor.DIRECTION_DOWN)
			fp += "D";
		if (direction == Actor.DIRECTION_LEFT)
			fp += "L";
		if (direction == Actor.DIRECTION_RIGHT)
			fp += "R";

		fp+=".png";
		return new ImageIcon(fp);
	}
}
