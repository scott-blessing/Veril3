package Actors.Enemies;


import Items.Arrow;
import Rooms.Location;
import Rooms.World;
import javax.swing.ImageIcon;


public class IceTurret extends Turret
{
	public static final int BASE_HEALTH = 5;
	public static final int BASE_ATTACK = 1;
        private boolean frozen;

	public IceTurret(int wait, int dir, Location loc, Location roomLoc, World w)
	{
		super(wait, dir, loc, roomLoc, w);
                frozen = true;
	}

	public void shootBow(String type)
	{
            super.shootBow(Arrow.ICE);
	}

        public void hit(int amt)
        {
            if (!frozen)
                loseHealth(amt);
        }
        
	public void hitByFire()
	{
            frozen = false;
	}
        
        public void removeSelf()
        {
            frozen = true;
            super.removeSelf();
        }

	public ImageIcon getImage()
	{
            if (frozen)
                return getImage("IceTurret/Frozen");
            else
                return getImage("IceTurret/Normal");
	}
}