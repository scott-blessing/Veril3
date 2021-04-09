package Rooms;

import Actors.Actor;
import Rooms.*;
import java.io.Serializable;

public class Location implements Serializable
{
    private int x, y;


    public Location(int X, int Y)
    {
        x = X;
        y = Y;
    }

    public void setX(int newX)
    {
		x = newX;
	}

	public void setY(int newY)
	{
		y = newY;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}


	public void moveForward(int direction)
	{
		if (direction == Actor.DIRECTION_RIGHT)
			x++;
		if (direction == Actor.DIRECTION_LEFT)
			x--;
		if (direction == Actor.DIRECTION_UP)
			y--;
		if (direction == Actor.DIRECTION_DOWN)
			y++;
	}

	public Location nextSquare(int direction)
	{
		int newX = x;
		int newY = y;
		if (direction == Actor.DIRECTION_RIGHT)
			newX++;
		if (direction == Actor.DIRECTION_LEFT)
			newX--;
		if (direction == Actor.DIRECTION_UP)
			newY--;
		if (direction == Actor.DIRECTION_DOWN)
			newY++;

		if (newX >= 16 || newX < 0 || newY >= 12 || newY < 0)
			return null;

		return new Location(newX,newY);
	}

	public Location nextRoom(int direction)
	{
		int newX = x;
		int newY = y;
		if (direction == Actor.DIRECTION_RIGHT)
			newX++;
		if (direction == Actor.DIRECTION_LEFT)
			newX--;
		if (direction == Actor.DIRECTION_UP)
			newY--;
		if (direction == Actor.DIRECTION_DOWN)
			newY++;

		if (newX < 0 || newY < 0)
			return null;

		return new Location(newX,newY);
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;

		Location l2 = (Location) obj;
		if (l2.getX() == this.getX() && l2.getY() == this.getY())
			return true;
		return false;
	}

	public String toString()
	{
		return "("+x+", "+y+")";
	}
}

