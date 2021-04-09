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

public class Fire extends Obstacle
{
	private Fire F;
	private Timer T;
	private int time;
	private int timeLength;
	private boolean castByVeril;

	public Fire(int lastTime, boolean castByV, Location room, Location square, World w)
	{
		super(Obstacle.TYPE_FIRE, room, square, w);
		time = 0;
		timeLength = lastTime;
		T = new Timer(200, new FireListener());
		if (!castByVeril)
			T.setDelay(600);
		T.start();
		F = this;
		castByVeril = castByV;
	}

	public boolean isWalkable()
	{
		return true;
	}

	class FireListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (timeLength > 0)
				time++;

			if (castByVeril)
			{
				ArrayList<VerilBaseObject> vbos = getRoom().getAllObjects();
				for (int i = 0; i < vbos.size(); i++)
				{
					VerilBaseObject v = vbos.get(i);
					if (v.getLocation().equals(getLocation()))
					{
						if (v instanceof Enemy)
						{
							Enemy E = (Enemy) v;
							E.hitByFire();
						}
						else if (v instanceof Obstacle)
						{
							Obstacle o = (Obstacle) v;
							if (o.getType() == Obstacle.TYPE_BUSH || o.getType() == Obstacle.TYPE_RUBBLE)
								o.removeSelf();
						}
					}
				}
			}
			else
			{
				Veril v = getRoom().getVeril();
				if (v.getLocation().equals(getLocation()) && !v.isTalking())
					v.loseHealth(1);
			}

			if (time > timeLength)
			{
				T.stop();
				F.removeSelf();
			}

		}
	}

	public ImageIcon getImage()
	{
		return new ImageIcon("images/Obstacle/Fire.gif");
	}

}
