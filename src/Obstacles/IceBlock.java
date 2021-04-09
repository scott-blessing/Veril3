package Obstacles;

import Rooms.*;
import Actors.*;
import GUI.*;
import Items.Equipment;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class IceBlock extends Obstacle
{
	private IceBlock IB;
	private Timer T;

	private Location initLoc;
	private int dir;

	public IceBlock(Location room, Location square, World w)
	{
		super(Obstacle.TYPE_ICE_BLOCK, room, square, w);
		initLoc = new Location(square.getX(), square.getY());
		T = new Timer(100, new SlideListener());
		IB = this;
	}

	public void resetLoc()
	{
		setLocation(initLoc);
	}

	public boolean isWalkable()
	{
		return false;
	}

	public ActionResult interact(Equipment e, Veril v)
	{
		if (e.getType() == Equipment.TYPE_POKINGSTICK)
		{
			dir = v.getDirection();
			T.start();
		}
		return null;
	}

	class SlideListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			boolean walkableO = false;

			Location newLoc = getLocation().nextSquare(dir);
			if (newLoc != null)
			{
				VerilBaseObject o = getRoom().getObjectAt(newLoc);
				if (o != null)
				{
					if (o instanceof Obstacle)
					{
						Obstacle obs = (Obstacle) o;
						walkableO = obs.isWalkable();
						if (obs.getType() == Obstacle.TYPE_ICE_HOLE_OPEN)
						{
							obs.setType(Obstacle.TYPE_ICE_HOLE_CLOSED);
							removeSelf();
						}
					}
				}

				Square s = getRoom().getSquare(newLoc);

				if (s.isIce() && (o == null || walkableO))
					setLocation(newLoc);
				else
					T.stop();

			}

		}
	}

	public ImageIcon getImage()
	{
		return new ImageIcon("images/Obstacle/IceBlock.png");
	}

}

