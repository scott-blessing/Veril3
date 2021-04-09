package Rooms;

import java.util.ArrayList;
import Actors.*;
import Actors.Enemies.Turret;
import Actors.Enemies.EvilSuitOfArmor;
import Actors.Enemies.Bosses.Boss;
import Rooms.*;
import Obstacles.*;
import Items.*;
import javax.swing.*;
import java.io.Serializable;
import GUI.ActionResult;

public class Room implements Serializable
{
    public static final int NUM_ROWS = 12;
    public static final int NUM_COLS = 16;

    private Square[][] squares;
    private Veril veril;
    private World world;
    private boolean explored;
    private boolean changed = false;
    private int personMove = 0;
    private String message = "";
    private int musicNum;

    private Boss bossInRoom;


    public Room(String squareSequence, Veril v, World w, int music)
    {
    	if (squareSequence != null && squareSequence != "")
        	squares = convertToSquareArray(squareSequence);
        else
        	squares = null;
        veril = v;
        world = w;
        explored = false;
        musicNum = music;
    }
	private Square[][] convertToSquareArray(String s)
	{
		int cnt = 0;
		Square[][] sqs = new Square[NUM_ROWS][NUM_COLS];
		cnt = 0;
		for (int i = 0; i < NUM_ROWS; i++)
		{
			for (int j = 0; j < NUM_COLS; j++)
			{
				String num =  s.substring((cnt * 3) + 3, (cnt * 3) + 5);
				sqs[i][j] = new Square(Integer.parseInt(num));
				cnt++;
			}
		}

		return sqs;
	}


	public void changeTo(String newRoom)
	{
		int cnt = 0;
		cnt = 0;
		for (int i = 0; i < NUM_ROWS; i++)
		{
			for (int j = 0; j < NUM_COLS; j++)
			{
				String num =  newRoom.substring((cnt * 3), (cnt * 3) + 2);
				squares[i][j].setNum(Integer.parseInt(num));
				cnt++;
			}
		}
		changed = true;
	}
	public boolean isChanged()
	{
		if (changed)
		{
			changed = false;
			return true;
		}
		return false;
	}
	public void setChanged(boolean itIsNow)
	{
		changed = itIsNow;
	}

//setgets
	public Square getSquare(Location loc)
	{
		int x = loc.getX();
		int y = loc.getY();
		if (x >= 0 && x < NUM_COLS && y >= 0 && y < NUM_ROWS)
			return squares[y][x];
		return null;
	}
	public Square getSquare(int x, int y)
	{
		if (x >= 0 && x < NUM_COLS && y >= 0 && y < NUM_ROWS)
			return squares[y][x];
		return null;
	}
	public Veril getVeril()
	{
		return veril;
	}
	public World getWorld()
	{
		return world;
	}

	public void setExplored(boolean itIsNow)
	{
		explored = itIsNow;
	}
	public boolean isExplored()
	{
		return explored;
	}


//VerilBaseObjects
	private ArrayList<VerilBaseObject> objectsInRoom = new ArrayList<VerilBaseObject>();
	private ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();

	public void add(VerilBaseObject obj)
	{
		objectsInRoom.add(obj);
	}

	public void removeObjectAt(Location loc)
	{
		for (int i = 0; i < objectsInRoom.size(); i++)
		{
			if (objectsInRoom.get(i).getLocation().equals(loc))
			{
				VerilBaseObject v = objectsInRoom.get(i);
				objectsInRoom.remove(i);
				if (v instanceof Enemy)
					deadEnemies.add((Enemy) v);
				i--;
			}
		}
	}
	public void removeObject(VerilBaseObject vbo)
	{
		for (int i = 0; i < objectsInRoom.size(); i++)
		{
			if (objectsInRoom.get(i) == vbo)
			{
				objectsInRoom.remove(i);

				if (vbo instanceof Boss)
					bossInRoom = (Boss) vbo;
				else if (vbo instanceof Enemy)
					deadEnemies.add((Enemy) vbo);

				return;
			}
		}

	}

	public ArrayList<VerilBaseObject> getAllObjects()
	{
		return objectsInRoom;
	}

	public VerilBaseObject getObjectAt(Location loc)
	{
		if (veril.getLocation().equals(loc))
			return veril;

		for (int i = 0; i < objectsInRoom.size(); i++)
		{
			if (objectsInRoom.get(i).getLocation().equals(loc))
			{
				return objectsInRoom.get(i);
			}
		}

		return null;
	}
	public VerilBaseObject getNonVerilObjectAt(Location loc)
	{
		for (int i = 0; i < objectsInRoom.size(); i++)
		{
			if (objectsInRoom.get(i).getLocation().equals(loc))
			{
				return objectsInRoom.get(i);
			}
		}

		return null;
	}

	public Door getDoorAt(Location loc)
	{
		for (VerilBaseObject vbo : objectsInRoom)
		{
			if (vbo instanceof Door)
				if (vbo.getLocation().equals(loc))
					return (Door) vbo;
		}
		return null;
	}
	public void removeDugSpots()
	{
		for (int i = objectsInRoom.size()-1; i >= 0; i--)
		{
			if (objectsInRoom.get(i) instanceof Obstacle)
			{
				if (((Obstacle) objectsInRoom.get(i)).getType() == Obstacle.TYPE_DUG_SPOT)
					objectsInRoom.remove(i);
			}
			else if (objectsInRoom.get(i) instanceof Item)
			{
				int t = ((Item) objectsInRoom.get(i)).getType();
				if (t != Item.TYPE_HEART_PIECE && t != Item.TYPE_HEART_FULL && t < 5)
					objectsInRoom.remove(i);
			}
		}
	}

//Enemy Reset
	public void reset()
	{
		if (numEnemies() == 0)
		{
			for (Enemy e : deadEnemies)
			{
				if (!(e instanceof Turret) && !(e instanceof EvilSuitOfArmor))
					e.randomizeLoc();
				removeObject(e);
				e.setHealth(e.getBaseHealth());
				this.add(e);
			}
			deadEnemies = new ArrayList<Enemy>();
		}
		for (VerilBaseObject vbo : objectsInRoom)
			if (vbo instanceof IceBlock)
				((IceBlock) vbo).resetLoc();
	}
	public int numEnemies()
	{
		int cnt = 0;
		for (VerilBaseObject v : objectsInRoom)
		{
			if (v instanceof Enemy)
				cnt++;
		}
		return cnt;
	}
	public Boss getBossInRoom()
	{
		if (bossInRoom != null)
			return bossInRoom;

		for (VerilBaseObject o : objectsInRoom)
			if (o instanceof Boss)
				bossInRoom = (Boss) o;

		return bossInRoom;
	}

//NPC Specific
	public void moveNPCs()
	{
		personMove++;
		for (VerilBaseObject vbo : objectsInRoom)
		{
			if (vbo instanceof Person)
			{
				if (personMove > 5)
				{
					((Person) vbo).act();
				}
			}
			else if (vbo instanceof Enemy)
			{
				Enemy e = (Enemy) vbo;
				e.countUp();
				if (e.getCount()>=e.getMoveTime())
				{
					e.act();
					e.reset();
				}
			}
		}
		if (personMove > 5) personMove = 0;
		Boss b = getBossInRoom();
		if (b != null)
			b.doMovementActions();
	}

	public void resetLines()
	{
		for (VerilBaseObject vbo : objectsInRoom)
		{
			if (vbo instanceof Person)
			{
				((Person) vbo).setCurLine(0);
			}
		}
	}


//Arrows

	private ArrayList<Arrow> arrows = new ArrayList<Arrow>();

	public void addArrow(Location startLoc, int dir, int damage, String type)
	{
		arrows.add(new Arrow(startLoc, dir, damage, this, type));
	}
	public void moveArrows()
	{
		for (int i = 0; i < arrows.size(); i++)
		{
			Arrow a = arrows.get(i);
			a.move();
			if (a.isDone())
				removeArrow(a);
		}
	}
	public void removeArrow(Arrow a)
	{
		arrows.remove(a);
	}
	public void resetArrows()
	{
		arrows = new ArrayList<Arrow>();
	}
	public ArrayList<Arrow> getArrows()
	{
		return arrows;
	}

//Message
	public void setMessage(String s)
	{
		message = s;
	}
	public ActionResult getMessage()
	{
		if (message == null || message.equals(""))
			return null;

		String S = message;
		message = "";
		return new ActionResult(S);
	}

//Music
	public void setMusicNum(int num)
	{
		musicNum = num;
	}

	public int getMusicNum()
	{
		return musicNum;
	}

//bg images
	public ImageIcon[][] getImages()
	{
		ImageIcon[][] pics = new ImageIcon[NUM_ROWS][NUM_COLS];
		for (int i = 0; i < NUM_ROWS; i++)
		{
			for (int j = 0; j < NUM_COLS; j++)
			{
				pics[i][j] = getSquare(j,i).getImage();
			}
		}
		return pics;
	}
}
