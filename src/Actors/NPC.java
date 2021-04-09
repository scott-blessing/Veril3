package Actors;


//ALL THIS CLASS DOES IS SEPERATE VERIL FROM OTHER ACTOR TYPES


import Actors.Actor;
import Rooms.*;
import javax.swing.ImageIcon;

public abstract class NPC extends Actor
{

	public NPC(int d, int h, int a, Location roomLoc, Location square, World w)
	{
		super(d,h,a,roomLoc,square,w);
	}


	public abstract ImageIcon getImage();
}