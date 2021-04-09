package Shortcuts;

import Rooms.*;
import Obstacles.*;
import Items.Item;
import Actors.Actor;

public class ObstacleAdder
{
	private World world;

	public ObstacleAdder(World w)
	{
		world = w;
	}

	public void addSwitch(Location room, Location square, String newMapString)
	{
		Switch s = new Switch(room, square, world);
		world.add(s, room);
		s.setNewRoomString(newMapString);
	}

	public void addBush(Location room, Location square)
	{
		world.add(new Obstacle(Obstacle.TYPE_BUSH, room, square, world), room);
	}
	public void addBoulder(Location room, Location square)
	{
		world.add(new Obstacle(Obstacle.TYPE_BOULDER, room, square, world), room);
	}
	public void addRock(Location room, Location square)
	{
		world.add(new Obstacle(Obstacle.TYPE_ROCK, room, square, world), room);
	}
	public void addGate(char val, Location room, Location square)
	{
		world.add(new Gate(val, room, square, world), room);
	}
	public void addSaveTile(Location room, Location square)
	{
		world.add(new Obstacle(Obstacle.TYPE_SAVETILE, room, square, world), room);
	}
	public void addDispenser(Location room, Location square)
	{
		world.add(new Obstacle(Obstacle.TYPE_DISPENSER, room, square, world), room);
	}
	public void addIceBlock(Location room, Location square)
	{
		world.add(new IceBlock(room, square, world), room);
	}
	public void addIceCrack(Location room, Location square)
	{
		world.add(new Obstacle(Obstacle.TYPE_ICE_HOLE_OPEN, room, square, world), room);
	}
	public void addRubble(Location room, Location square)
	{
		world.add(new Obstacle(Obstacle.TYPE_RUBBLE, room, square, world), room);
	}

	public void addTrigger(int tileNum, Location room, Location square)
	{
		world.add(new DoorBlockTrigger(tileNum, room, square, world), room);
	}

	public void addKeyBlock(int shape, Location room, Location square)
	{
		world.add(new KeyDoor(shape, room, square, world), room);
	}

	public void addChest(Location room, Location square, Item contents)
	{
		world.add(new Chest(room, square, world, contents), room);
	}
	public void addDigSpot(Location room, Location square, Item contents)
	{
		world.add(new DigSpot(room, square, world, contents), room);
	}

	public void addDrawer(Location room, Location square, Item contents)
	{
		world.add(new Furniture(Furniture.DRAWER, contents, room, square, world), room);
	}
	public void addDresser(Location room, Location square)
	{
		world.add(new Furniture(Furniture.DRESSER_L, room, square, world), room);
		world.add(new Furniture(Furniture.DRESSER_R, room, square.nextSquare(Actor.DIRECTION_RIGHT), world), room);
	}
	public void addBed(Location room, Location square)
	{
		world.add(new Furniture(Furniture.BED_U, room, square, world), room);
		world.add(new Furniture(Furniture.BED_D, room, square.nextSquare(Actor.DIRECTION_DOWN), world), room);
	}
	public void addBookshelf(Location room, Location square)
	{
		world.add(new Furniture(Furniture.BOOKSHELF, room, square, world), room);
	}
	public void addMagicshelf(Location room, Location square)
	{
		world.add(new Furniture(Furniture.MAGICSHELF, room, square, world), room);
	}
	public void addSuitOfArmor(Location room, Location square)
	{
		world.add(new Furniture(Furniture.ARMOR, room, square, world), room);
	}
	public void addShopCounters(Location room, Location square, int num)
	{
		Location s = square;
		for (int i = 0; i < num; i++)
		{
			world.add(new Furniture(Furniture.SHOPCOUNTER, room, s, world), room);
			s = new Location(s.getX() + 1, s.getY());
		}
	}
	public void addPlant(Location room, Location square)
	{
		world.add(new Furniture(Furniture.PLANT, room, square, world), room);
	}
	public void addFireplace(Location room, Location square)
	{
		world.add(new Furniture(Furniture.FIREPLACE, room, square, world), room);
	}
	public void addCampfire(Location room, Location square)
	{
		world.add(new Furniture(Furniture.CAMPFIRE, room, square, world), room);
	}
}
