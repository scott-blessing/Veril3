package Rooms;

import Actors.*;
import Rooms.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class World implements Serializable
{
	private int worldNum;
	private int numRows;
	private int numCols;
	private boolean mappable;

	private Room[][] rooms;

	public World()
	{
	}

	public World(File filePath, Veril v, int num, boolean map, int defaultMusic)
	{
		Scanner in = new Scanner(System.in);
		try
		{
			in = new Scanner(filePath);
			numRows = in.nextInt();
			numCols = in.nextInt();
			in.nextLine();
			rooms = new Room[numRows][numCols];
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "File \"MapStrings.dat\" could not be found");
		}
		finally
		{
			for (int i = 0; i < numRows; i++)
			{
				in.nextLine();  //Buffers before every change in row
				for (int j = 0; j < numCols; j++)
				{
					rooms[i][j] = new Room(in.nextLine(), v, this, defaultMusic);
				}
			}
			in.close();
		}
		worldNum = num;
		mappable = map;
	}

	public Room getRoom(int row, int col)
	{
		return rooms[row][col];
	}
	public Room getRoom(Location roomLoc)
	{
		if (numCols > roomLoc.getX() && numRows > roomLoc.getY())
			return rooms[roomLoc.getY()][roomLoc.getX()];
		return null;
	}

	public Location getLocOfRoom(Room r)
	{
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numCols; j++)
				if (rooms[i][j] == r)
					return new Location(j,i);
		return null;
	}

	public void add(VerilBaseObject obj, Location locRoom)
	{
		getRoom(locRoom).add(obj);
	}

	public void removeObjectAt(Location room, Location square)
	{
		getRoom(room).removeObjectAt(square);
	}
	public VerilBaseObject getObjectAt(Location room, Location square)
	{
		return getRoom(room).getObjectAt(square);
	}
	public VerilBaseObject getNonVerilObjectAt(Location room, Location square)
	{
		return getRoom(room).getNonVerilObjectAt(square);
	}

	public int getWorldNum()
	{
		return worldNum;
	}

	public boolean isMappable()
	{
		return mappable;
	}
	public int getRows()
	{
		return numRows;
	}
	public int getCols()
	{
		return numCols;
	}

	public Location getOpenLocation(Location room, boolean water)
	{
            boolean open = false;
            Location tempLoc = new Location(0,0);
            while (!open)
            {
                tempLoc = new Location(getRandX(), getRandY());
                if (getObjectAt(room,tempLoc) == null)
                {
                    if (water && getRoom(room).getSquare(tempLoc).isWater())
                        open = true;
                    else if (!water && getRoom(room).getSquare(tempLoc).isWalkable())
                        open = true;
                }

            }
            return tempLoc;
	}
        
        public Location getOpenLocationOfTile(Location room, int tile)
	{
            boolean open = false;
            Location tempLoc = new Location(0,0);
            while (!open)
            {
                tempLoc = new Location(getRandX(), getRandY());
                if (getObjectAt(room,tempLoc) == null)
                    if (getRoom(room).getSquare(tempLoc).getNum() == tile)
                        open = true;

            }
            return tempLoc;
	}

	private int getRandX()
	{
		return (int) (Math.random() * 14) + 1;
	}
	private int getRandY()
	{
		return (int) (Math.random() * 10) + 1;
	}
}
