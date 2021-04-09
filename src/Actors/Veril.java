package Actors;

import Rooms.*;
import GUI.Inventory;
import GUI.Shop;
import Items.*;
import Obstacles.*;
import javax.swing.*;
import java.util.ArrayList;

public class Veril extends Actor
{
	private Inventory inv;

	private Shop curShop;

	private boolean canSwim;
	private boolean hasFastShoes;
	private boolean hasGoodSword;
	private boolean hasArmor;

	private boolean frozen;
	private int freezeCount;

	private boolean talking;
        
        private boolean T6Death = false;

	public Veril(int dir, int heal, int att, Location roomLoc, Location square)
	{
		super(dir,heal, att,roomLoc,square,new World());
		inv = new Inventory(this);
		canSwim = false;
		hasFastShoes = false;
		hasGoodSword = false;
		hasArmor = false;
		curShop = null;
		talking = false;
		frozen = false;
		freezeCount = 0;
	}

//INVENTORY
	public Inventory getInventory()
	{
		return inv;
	}


//HEALTH
	public void addHealth(int amt)
	{
		setHealth(getHealth() + amt);
		if (getHealth() >= inv.getMaxHealth())
			setHealth(inv.getMaxHealth());
	}
	public void loseHealth(int amt)
	{
		//new AePlayWave("Music/Clips/VerilHurt.wav").start();
		int damage = amt - inv.getDefenseBonus();
		if (damage <= 0)
			damage = 1;
		setHealth(getHealth() - damage);
		if (getHealth() <= 0)
		{
			setHealth(0);
		}

	}

	public int getAttack()
	{
		return super.getAttack() + inv.getAttackBonus();
	}


//MOVEMENT
	public boolean act(int dir)
	{
		if (frozen)
		{
			unfreeze();
			return false;
		}

		boolean updateScreen = false;
		if (dir == getDirection()) //Moving forward
		{
			Location locInFront = getLocation().nextSquare(dir);
			if (locInFront == null)
			{
				getRoom().resetLines();
				changeRoom(dir);
				updateScreen = true;
				if (getRoom().getSquare(getLocation()).isIce() && !hasFastShoes)
					act(dir);
			}
			else
			{
				Square sqInFront = getRoom().getSquare(locInFront);
				VerilBaseObject o = getRoom().getObjectAt(locInFront);
				if (canMove() || (sqInFront.isWater() && canSwim() && !(o instanceof Enemy)))
				{
					move();

					if (sqInFront.isDoor())
					{
						enterDoor();
						updateScreen = true;
					}
					if (o instanceof DoorBlockTrigger)
					{
						((DoorBlockTrigger) o).trigger(this);
					}
				}
			}
		}
		else  //turning
		{
			setDirection(dir);
			if (dir == Actor.DIRECTION_DOWN && getRoom().getSquare(getLocation()).isOutDoor())
			{
				enterDoor();
				updateScreen = true;
			}
			else if (dir == Actor.DIRECTION_UP && getRoom().getSquare(getLocation()).isInDoor())
			{
				enterDoor();
				updateScreen = true;
			}
		}

		updateScreen = updateScreen || checkForItems();

		return updateScreen;
	}

	public boolean checkForItems()
	{
		VerilBaseObject obj = getRoom().getNonVerilObjectAt(getLocation());
		if (obj != null)
		{
			if (obj instanceof Item)
			{
				Item i = (Item) obj;
				i.pickUp(this);
				i.removeSelf();
				inv.checkQuests(i);
				return true;
			}
		}
		return false;
	}

	public boolean canSwim()
	{
		return canSwim;
	}
	public boolean hasFastShoes()
	{
		return hasFastShoes;
	}

	public void enterDoor()
	{
		Door d = getRoom().getDoorAt(getLocation());
		if (d != null)
		{
			getRoom().resetArrows();
			getRoom().resetLines();
			d.enter(this);
			getRoom().setExplored(true);
			inv.checkQuests(getRoom());
		}
	}

	public void changeRoom(int dir)
	{
		getRoom().resetArrows();
		Location newRoomLoc = getRoomLoc().nextRoom(dir);
		Room newRoom = getWorld().getRoom(newRoomLoc);
		inv.checkQuests(newRoom);
		newRoom.reset();
		setRoomLoc(newRoomLoc);
		getWorld().getRoom(getRoomLoc()).setExplored(true);
		if (dir == Actor.DIRECTION_UP)
			setLocation(new Location(getLocation().getX(), 11));
		if (dir == Actor.DIRECTION_DOWN)
			setLocation(new Location(getLocation().getX(), 0));
		if (dir == Actor.DIRECTION_LEFT)
			setLocation(new Location(15, getLocation().getY()));
		if (dir == Actor.DIRECTION_RIGHT)
			setLocation(new Location(0, getLocation().getY()));
	}

	public void becomeFrozen()
	{
		frozen = true;
		freezeCount = 0;
	}
	public boolean isFrozen()
	{
		return frozen;
	}
	public void unfreeze()
	{
		freezeCount++;
		if (freezeCount >= 3)
		{
			frozen = false;
			freezeCount = 0;
		}
	}
        
        public void setT6Respawn()
        {
            T6Death = true;     
        }
        public boolean isT6Death()
        {
            if (T6Death)
            {
                T6Death = false;
                return true;
            }
            return false;
        }

//UPGRADES
	public void giveFastShoes()
	{
		hasFastShoes = true;
		if (getWorld().getWorldNum() == 4)
		{
			inv.addToSpeed();
			inv.addToSpeed();
		}

	}
        public void takeFastShoes()
        {
            hasFastShoes = false;
        }
	public void setCanSwim(boolean ICanNow)
	{
		canSwim = ICanNow;
	}
	public void giveGoodSword()
	{
		hasGoodSword = true;
		inv.addToAttack();
		inv.addToAttack();
		for (int i = 0; i < 7; i++)
		{
			Equipment e = inv.getEquipment(i);
			if (e.getType() == Equipment.TYPE_SWORD)
				e.setLevel(2);
		}
	}
	public void giveArmor()
	{
		hasArmor = true;
		inv.addToDefense();
		inv.addToDefense();
	}
	public boolean hasGoodSword()
	{
		return hasGoodSword;
	}
	public boolean hasArmor()
	{
		return hasArmor;
	}

//TALKING
	public void startTalking()
	{
		talking = true;
	}
	public boolean isTalking()
	{
		return talking;
	}
	public void stopTalking()
	{
		talking = false;
	}


//SHOPPING
	public void setShop(Shop s)
	{
		curShop = s;
	}
	public Shop getShop()
	{
		return curShop;
	}
	public boolean inShop()
	{
		return curShop != null;
	}
	public void leaveShop()
	{
		curShop = null;
	}


//IMAGE
	public ImageIcon getImage()
	{
		return getImage(0);
	}

	public ImageIcon getImage(int step)
	{
		if (getWorld().getWorldNum() == 11)
			return new ImageIcon("images/Veril/Codeland.png");

		String fp = "images/Veril/";

		if (frozen)
		{
			fp += "Frozen/";
			if (getDirection() == Actor.DIRECTION_UP)
				fp += "Up";
			else if (getDirection() == Actor.DIRECTION_RIGHT)
				fp += "Right";
			else if(getDirection() == Actor.DIRECTION_DOWN)
				fp += "Down";
			else
				fp += "Left";
			fp+=".png";
			return new ImageIcon(fp);
		}


		if (!hasArmor) {fp += "Red";}
		else           {fp += "Armor";}

		fp += "/";

		if (getDirection() == Actor.DIRECTION_UP)
			fp += "Up/";
		else if (getDirection() == Actor.DIRECTION_RIGHT)
			fp += "Right/";
		else if(getDirection() == Actor.DIRECTION_DOWN)
			fp += "Down/";
		else
			fp += "Left/";

		fp += step;

		if (getRoom().getSquare(getLocation()).isWater())
		{
			VerilBaseObject v = getRoom().getNonVerilObjectAt(getLocation());
			if (v == null || v instanceof Door || v instanceof Whirlpool || v instanceof Fire)
				fp += "s";
		}
		else
		{
			int e = inv.getEquipLastUsed();
			if (e == Equipment.TYPE_SWORD){fp+="a";}
			else if (e == Equipment.TYPE_BOW){fp+="b";}
			else if (e == Equipment.TYPE_TORCH){fp+="c";}
			else if (e == Equipment.TYPE_POKINGSTICK){fp+="d";}
			else if (e == Equipment.TYPE_HAMMER){fp+="e";}
			else if (e == Equipment.TYPE_SHOVEL){fp+="f";}
		}


		fp += ".png";

		ImageIcon i = new ImageIcon(fp);
		return i;
	}
}