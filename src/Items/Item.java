package Items;

import Rooms.*;
import Actors.*;
import GUI.*;
import java.util.ArrayList;
import Items.Equipment;
import Shortcuts.EnemyAdder;
import javax.swing.ImageIcon;
import Shortcuts.Finder;

public class Item extends VerilBaseObject
{
	//Common Items
	public static final int TYPE_LOVI = 0;
	public static final int TYPE_HEART_SMALL = 1;
	public static final int TYPE_HEART_PIECE = 2;
	public static final int TYPE_HEART_FULL = 3;
	public static final int TYPE_ARROW = 4;
	public static final int TYPE_KEY = 5;
	public static final int TYPE_ARMOR = 6;
	//Temple Items
	public static final int TYPE_SHOVEL = 7;
	public static final int TYPE_SWORD = 8;
	public static final int TYPE_BOW = 9;
	public static final int TYPE_TORCH = 10;
	public static final int TYPE_POKINGSTICK = 11;
	public static final int TYPE_FASTSHOES = 12;
	public static final int TYPE_HAMMER = 13;
	public static final int TYPE_SWIMLESSONS = 14;
	//Shop Stuff
	public static final int SHOP_BASICMAP = 15;      //Grid Map
	public static final int SHOP_EPICMAP = 16;       //Detailed Map
	public static final int SHOP_QUIVEREXT = 17;     //+20 Quiver Space
	public static final int SHOP_MAGICQUIVER = 18;   //+80 Quiver Space
	public static final int SHOP_OIL = 19;           //Longer Torch Burn Time
	public static final int SHOP_IRONARROWS = 20;    //Arrows do more damage
	public static final int SHOP_FIREARROWS = 21;    //Arrows create fire on target
	public static final int SHOP_POTION = 22;        //Refills health
	public static final int SHOP_LOSTISLANDTOUR = 23;//Brings you to lost Island (ridiculously expensive)
	//Temple Ender
	public static final int TYPE_CODESNIPPET = 24;
	//Misc
	public static final int TYPE_FLIPPER = 25;
	public static final int TYPE_MIRROR = 26;
	public static final int TYPE_SWORD2 = 27;
	public static final int TYPE_SAIL = 28; //Quest
	public static final int TYPE_ARTIFACT = 29; //Quest
	public static final int TYPE_HAT = 30; //Quest
	public static final int TYPE_CMD = 31;

	private int itemType;
	private int val;

	//used by Chests, Underground, and People
	public Item(int type)
	{
		super();
		itemType = type;
		val = 0;
	}
	public Item(int type, int val)
	{
		super();
		itemType = type;
		this.val = val;
	}

	//Visible on Map
	public Item(int type, Location room, Location square, World w)
	{
		super(room, square, w);
		itemType = type;
		val = 0;
	}
	public Item(int type, int val, Location room, Location square, World w)
	{
		super(room, square, w);
		itemType = type;
		this.val = val;
	}


	public void setType(int newType)
	{
		itemType = newType;
	}
	public void setVal(int newVal)
	{
		val = newVal;
	}

	public int getType()
	{
		return itemType;
	}
	public int getVal()
	{
		return val;
	}

	public void removeSelf()
	{
		getRoom().removeObject(this);
	}

	public void pickUp(Veril v)
	{
		if (itemType == TYPE_LOVI)
		{
			v.getInventory().addMoney(val);
		}
		if (itemType == TYPE_HEART_SMALL)
		{
			v.addHealth(5);
		}
		if (itemType == TYPE_HEART_PIECE)
		{
			v.getInventory().increaseMaxHealth(5);
		}
		if (itemType == TYPE_HEART_FULL)
		{
			v.getInventory().increaseMaxHealth(15);
		}
		if (itemType == TYPE_ARROW)
		{
			v.getInventory().addArrows(val);
		}
		//EQUIPMENTS
		if (itemType == TYPE_SWORD)
		{
			Equipment e = new Equipment(Equipment.TYPE_SWORD);
			if (v.hasGoodSword())
				e.setLevel(2);
			v.getInventory().addEquipment(e);
			if (v.getWorld().getWorldNum() == 0)
			{
				Person k = Finder.findPerson("Kris");
				k.setLocation(new Location(6,4));
				k.setDirection(Actor.DIRECTION_DOWN);
				ArrayList<String> l = new ArrayList<String>();
				l.add("Ah. You have a sword.");
				l.add("But be wary, what you find past this point has been coded solely to kill you.");
				l.add("Good Luck Veril.");
				k.setLines(l);
				k = Finder.findPerson("Village Elder");
				l = new ArrayList<String>();
				l.add("Do you know what dwells in this cave?");
				l.add("It is said that 'Ryan the Graphics Guy' draws horrid enemies in there.");
				l.add("He is the root of all chaos in the world, and he must be stopped.");
				l.add("Stop him Veril, and save Loviant.");
				k.setLines(l);
				k.removeSelf();
				k.setRoomLoc(new Location(4,11));
				k.setLocation(new Location(5,8));
				v.getWorld().add(k, k.getRoomLoc());
			}

		}
		if (itemType == TYPE_BOW)
		{
			v.getInventory().addEquipment(new Equipment(Equipment.TYPE_BOW));
			v.getInventory().addArrows(50);
		}
		if (itemType == TYPE_TORCH)
		{
			v.getInventory().addEquipment(new Equipment(Equipment.TYPE_TORCH));
		}
		if (itemType == TYPE_POKINGSTICK)
		{
			v.getInventory().addEquipment(new Equipment(Equipment.TYPE_POKINGSTICK));
		}
		if (itemType == TYPE_FASTSHOES)
		{
			v.giveFastShoes();
			if (v.getWorld().getWorldNum() == 4)
			{
				Room r = Veril3GameScreenFrame.world[0].getRoom(new Location(10,10));
				r.removeObjectAt(new Location(15,4));
				r.removeObjectAt(new Location(15,5));
			}

		}
		if (itemType == TYPE_HAMMER)
		{
			v.getInventory().addEquipment(new Equipment(Equipment.TYPE_HAMMER));
		}
		if (itemType == TYPE_SWIMLESSONS)
		{
			v.setCanSwim(true);
		}
		if (itemType == TYPE_SHOVEL)
		{
			v.getInventory().addEquipment(new Equipment(Equipment.TYPE_SHOVEL));
		}
		if (itemType == TYPE_MIRROR)
		{
			v.getInventory().addEquipment(new Equipment(Equipment.TYPE_MIRROR));
		}
		if (itemType == TYPE_ARMOR)
		{
			v.giveArmor();
		}
		if (itemType == TYPE_SWORD2)
		{
			v.giveGoodSword();
		}
		else if (itemType == SHOP_BASICMAP)
			v.getInventory().giveMap();
		else if (itemType == SHOP_EPICMAP)
			v.getInventory().giveEpicMap();
		else if (itemType == SHOP_QUIVEREXT)
			v.getInventory().increaseQuiverSize(20);
		else if (itemType == SHOP_MAGICQUIVER)
			v.getInventory().increaseQuiverSize(80);
		else if (itemType == SHOP_OIL)
			v.getInventory().setTorchTime(10);
		else if (itemType == SHOP_IRONARROWS)
			v.getInventory().setArrowDamage(2);
		else if (itemType == SHOP_FIREARROWS)
			v.getInventory().giveFireArrows();
		else if (itemType == SHOP_POTION)
			v.setHealth(v.getInventory().getMaxHealth());
		else if (itemType == SHOP_LOSTISLANDTOUR)
		{
			v.setRoomLoc(new Location(6,14));
			v.setLocation(new Location(10,3));
		}

		if (itemType == TYPE_CMD)
		{
			v.setRoomLoc(1,3);
			v.setLocation(12,4);
			v.setDirection(Actor.DIRECTION_DOWN);
			v.setWorld(Veril3GameScreenFrame.world[11]);
		}
		if (itemType == TYPE_FLIPPER)
		{
			v.getRoom().changeTo("22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 26 29 29 29 11 11 11 11 11 11 11 22 22 22 22 22 29 29 11 11 17 21 21 21 19 11 11 11 29 30 22 22 29 11 11 17 21 21 21 21 21 19 20 11 11 29 22 22 29 11 11 21 21 21 21 21 21 21 11 11 11 29 22 22 29 11 20 21 21 21 21 21 21 21 11 11 11 29 22 22 28 11 11 15 21 21 21 21 21 13 11 11 11 29 22 22 26 29 11 11 11 11 11 11 11 11 11 11 11 29 22 22 29 29 29 11 11 11 11 11 20 11 11 11 11 29 22 22 28 30 29 29 29 29 29 11 11 11 11 11 29 29 22 22 22 28 29 29 29 29 29 29 29 11 11 11 30 27 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 ");
			EnemyAdder EA = new EnemyAdder(v.getWorld());
                        EA.addStoneTurret(3,Enemy.DIRECTION_LEFT,  new Location( 2 ,2 ), new Location( 15 ,6 ));
                        EA.addStoneTurret(4,Enemy.DIRECTION_LEFT,  new Location( 2 ,2 ), new Location( 15 ,5 ));
                        EA.addStoneTurret(3,Enemy.DIRECTION_LEFT,  new Location( 2 ,2 ), new Location( 15 ,4 ));
                        EA.addStoneTurret(4,Enemy.DIRECTION_LEFT,  new Location( 2 ,2 ), new Location( 15 ,3 ));
                        EA.addStoneTurret(3,Enemy.DIRECTION_LEFT,  new Location( 2 ,2 ), new Location( 15 ,2 ));
                        EA.addStoneTurret(4,Enemy.DIRECTION_RIGHT, new Location( 2 ,2 ), new Location( 0 ,2 ));
                        EA.addStoneTurret(3,Enemy.DIRECTION_RIGHT, new Location( 2 ,2 ), new Location( 0 ,3 ));
                        EA.addStoneTurret(4,Enemy.DIRECTION_RIGHT, new Location( 2 ,2 ), new Location( 0 ,4 ));
                        EA.addStoneTurret(3,Enemy.DIRECTION_RIGHT, new Location( 2 ,2 ), new Location( 0 ,5 ));
                        EA.addStoneTurret(4,Enemy.DIRECTION_UP,    new Location( 2 ,2 ), new Location( 4 ,11 ));
                        EA.addStoneTurret(3,Enemy.DIRECTION_UP,    new Location( 2 ,2 ), new Location( 5 ,11 ));
                        EA.addStoneTurret(4,Enemy.DIRECTION_UP,    new Location( 2 ,2 ), new Location( 6 ,11 ));
                        EA.addStoneTurret(3,Enemy.DIRECTION_UP,    new Location( 2 ,2 ), new Location( 7 ,11 ));
                        EA.addStoneTurret(4,Enemy.DIRECTION_UP,    new Location( 2 ,2 ), new Location( 8 ,11 ));
                        EA.addStoneTurret(3,Enemy.DIRECTION_UP,    new Location( 2 ,2 ), new Location( 9 ,11 ));
                        EA.addStoneTurret(4,Enemy.DIRECTION_UP,    new Location( 2 ,2 ), new Location( 10 ,11 ));
                        v.setT6Respawn();
		}

		if (getWorld() != null)
			removeSelf();
	}

	public String getMessage()
	{
		if (itemType == TYPE_LOVI)
			return "You got " + val + " Lovi!";
		if (itemType == TYPE_HEART_SMALL)
			return "You got some health";
		if (itemType == TYPE_HEART_PIECE)
			return "You got a Heart Piece!\nYour max health has increased by 5!";
		if (itemType == TYPE_HEART_FULL)
			return "You got a Full Heart!\nYour max health has increased by 15!";
		if (itemType == TYPE_SWORD)
			return "You got a sword!\nYou can now attack enemies.";
		if (itemType == TYPE_ARROW)
			return "You got " + val + " arrows";
		if (itemType == TYPE_BOW)
			return "You got the Bow!\nYou can now shoot far off foes and magic crystals.";
		if (itemType == TYPE_TORCH)
			return "You got the Torch!\nYou can now burn small bushes.";
		if (itemType == TYPE_POKINGSTICK)
			return "You got the Magic Wand!\nOh wait...\nYou got a stick. Poke stuff with it.";
		if (itemType == TYPE_FASTSHOES)
			return "You got the Golden Sandels! You can now walk over ice without slipping and your Speed has increased by 2 points.";
		if (itemType == TYPE_HAMMER)
			return "You got the Mountain Hammer!\nYou can now smash rocks and shake the ground to hurt nearby enemies.";
		if (itemType == TYPE_SWIMLESSONS)
			return "You have recieved Swim Lessons!\nYou can now swim.";
		if (itemType == TYPE_SHOVEL)
			return "You got the Shovel!\nYou can now dig up buried treasures.";
		if (itemType == SHOP_BASICMAP)
			return "You now have a Map!\nIt can be seen in your inventory by pressing UP.";
		if (itemType == SHOP_EPICMAP)
			return "You got an Epic Map!\nYou can now view a colorful and highly accurate map by pressing UP.";
		if (itemType == SHOP_QUIVEREXT)
			return "You got a Quiver Extension!\nYou can now carry 20 more arrows.";
		if (itemType == SHOP_MAGICQUIVER)
			return "You got your Quiver magically enchanted!\nIt can now store 80 more arrows.";
		if (itemType == SHOP_OIL)
			return "You got some Oil!\nYour torch now burns twice as long.";
		if (itemType == SHOP_IRONARROWS)
			return "You got Iron Arrows!\nArrows now do double damage.";
		if (itemType == SHOP_FIREARROWS)
			return "Your arrows have been magically enchanted!\nEnemies hit temporarily catch fire.";
		if (itemType == SHOP_POTION)
			return "After drinking this potion you feel completely refreshed and ready for action.";
		if (itemType == SHOP_LOSTISLANDTOUR)
			return "Welcome to The Lost Island!\n\nThat's it for the tour.";
		if (itemType == TYPE_CODESNIPPET)
			return "You got a code snippet!";
		if (itemType == TYPE_ARMOR)
			return "You got the legendary Symbol Armor!\n\nYour defense has increased and you look snazzy!";
		if (itemType == TYPE_FLIPPER)
			return "You got some flippers!\n\nIf only you could swim...";
		if (itemType == TYPE_SWORD2)
			return "You got Vaslan's mighty sword!\n\nBear it proundly to smite your foes!";
		if (itemType == TYPE_MIRROR)
			return "You got the Magic Mirror!\n\nGaze deeply into it's smooth surface when you're outside.";
		if (itemType == TYPE_SAIL)
			return "You found an ancient sail!\n\nI wonder if anyone needs one of these?";
		if (itemType == TYPE_ARTIFACT)
			return "You found an ancient artifact!\n\nHow convenient that it was in an unburied chest!";
		if (itemType == TYPE_HAT)
			return "You found a ratty old hat!\n\nSnazzy!";

		return "";
	}

	public ImageIcon getImage()
	{
		String fp = "images/Items/";
		if (itemType == TYPE_LOVI)
			fp += "Lovis/" + val;
		else if (itemType == TYPE_HEART_SMALL)
			fp += "Hearts/Heart";
		else if (itemType == TYPE_HEART_PIECE)
			fp += "Hearts/HeartPiece";
		else if (itemType == TYPE_HEART_FULL)
			fp += "Hearts/HeartContainer";
		else if (itemType == TYPE_ARROW)
			fp += "Projectiles/"+val;
		else if (itemType == TYPE_KEY)
			fp += "Keys/"+val;
		else if (itemType == TYPE_SWORD)
			fp += "StarterSword";
		else if (itemType == TYPE_BOW)
			fp += "Bow";
		else if (itemType == TYPE_TORCH)
			fp += "Torch";
		else if (itemType == TYPE_POKINGSTICK)
			fp += "PokingStick";
		else if (itemType == TYPE_FASTSHOES)
			fp += "LightShoes";
		else if (itemType == TYPE_HAMMER)
			fp += "Hammer";
		else if (itemType == TYPE_SWIMLESSONS)
			fp += "SwimLessons";
		else if (itemType == TYPE_SHOVEL)
			fp += "Shovel";
		else if (itemType == SHOP_BASICMAP)
			fp += "BasicMap";
		else if (itemType == SHOP_EPICMAP)
			fp += "EpicMap";
		else if (itemType == SHOP_QUIVEREXT)
			fp += "QuiverExt";
		else if (itemType == SHOP_MAGICQUIVER)
			fp += "MagicQuiver";
		else if (itemType == SHOP_OIL)
			fp += "Oil";
		else if (itemType == SHOP_IRONARROWS)
			fp += "IronArrows";
		else if (itemType == SHOP_FIREARROWS)
			fp += "FireArrows";
		else if (itemType == SHOP_POTION)
			fp += "Potion";
		else if (itemType == SHOP_LOSTISLANDTOUR)
			fp += "IslandTour";
		else if (itemType == TYPE_CODESNIPPET)
			fp += "CodeSnippet/"+val;
		else if (itemType == TYPE_FLIPPER)
			fp += "Flippers";
		else if (itemType == TYPE_ARMOR)
			fp += "Armor";
		else if (itemType == TYPE_MIRROR)
			fp += "Mirror";
		else if (itemType == TYPE_SWORD2)
			fp += "MasterSword";
		else if (itemType == TYPE_SAIL)
			fp += "Sail";
		else if (itemType == TYPE_ARTIFACT)
			fp += "Artifact";
		else if (itemType == TYPE_HAT)
			fp += "Hat";
		else if (itemType == TYPE_CMD)
			fp += "CMD";

		fp += ".png";
		return new ImageIcon(fp);
	}
}
