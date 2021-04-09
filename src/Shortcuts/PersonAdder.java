package Shortcuts;

import Rooms.*;
import Actors.*;
import java.util.ArrayList;
import GUI.Shop;

public class PersonAdder
{
	private World world;

	public PersonAdder(World w)
	{
		world = w;
	}

	public void addVillageMan(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, true, Person.TYPE_VILLAGER, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addVillageWoman(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, false, Person.TYPE_VILLAGER, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}

	public void addTownMan(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name,  true, Person.TYPE_TOWN, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addTownWoman(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, false, Person.TYPE_TOWN, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}

	public void addForestMan(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name,  true, Person.TYPE_FOREST, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addForestWoman(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, false, Person.TYPE_FOREST, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}

	public void addDesertMan(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name,  true, Person.TYPE_DESERT, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addDesertWoman(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, false, Person.TYPE_DESERT, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}

	public void addSnowMan(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name,  true, Person.TYPE_SNOW, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addSnowWoman(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, false, Person.TYPE_SNOW, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}

	public void addShopkeep(int dir, int type, boolean isMale, Location room, Location square, Shop s)
	{
		world.add(new Person(dir, Person.MOVEMENT_STILL, 0, "", isMale, type, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLine("SHOP");
		steve.giveShop(s);
	}
	public void addShopkeep(int dir, int type, boolean isMale, Location room, Location square, Shop s, String name, ArrayList<String> lines)
	{
		world.add(new Person(dir, Person.MOVEMENT_STILL, 0, name, isMale, type, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
		steve.addLine("SHOP");
		steve.giveShop(s);
	}

	public void addSign(Location room, Location square, String message)
	{
		world.add(new Person(Actor.DIRECTION_DOWN, Person.MOVEMENT_STILL, 0, "", true, Person.TYPE_SIGN, room, square, world), room);
		Person sign = (Person) world.getObjectAt(room,square);
		sign.addLine(message);
	}

	public void addGuard(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, true, Person.TYPE_GUARD, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addOldMan(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, true, Person.TYPE_OLDMAN, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addMayor(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, true, Person.TYPE_MAYOR, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addGary(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, true, Person.TYPE_GARY, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addHB(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, true, Person.TYPE_HB, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addLion(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, true, Person.TYPE_LION, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}
	public void addSnowman(int dir, Location room, Location square, String name, int moveType, int moveLen, ArrayList<String> lines)
	{
		world.add(new Person(dir, moveType, moveLen, name, true, Person.TYPE_SNOWMAN, room, square, world), room);
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.addLines(lines);
	}


	public void giveNewLines(int dir, Location room, Location square, ArrayList<String> newLines)
	{
		Person steve = (Person) world.getRoom(room).getObjectAt(square);
		steve.clearLines();
		steve.addLines(newLines);
	}
}
