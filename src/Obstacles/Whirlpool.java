package Obstacles;

import Rooms.*;
import Actors.*;
import GUI.*;
import Items.Equipment;
import Items.Key;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class Whirlpool extends Fire
{

	public static int PERMANENT = -1;

	public Whirlpool(int lastTime, Location room, Location square, World w)
	{
		super(lastTime, false, room, square, w);
		setType(Obstacle.TYPE_WHIRLPOOL);
	}


	public ImageIcon getImage()
	{
		return new ImageIcon("images/Obstacle/Whirlpool.gif");
	}

}
