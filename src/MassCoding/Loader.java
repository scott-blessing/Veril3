package MassCoding;

import Rooms.*;
import Actors.*;
import Actors.Enemies.*;
import Actors.Enemies.Bosses.*;
import Obstacles.*;
import Shortcuts.*;
import Quests.*;
import Items.*;
import GUI.Shop;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Loader
{
//###################################################################################################################################
//####################################  Worlds  #####################################################################################
//#################################################################################################################################*/
	public static World[] loadWorlds(Veril v)
	{
		World[] worlds = new World[13];
		worlds[0] = new World(new File("MAPS/Overworld.dat"), v, 0, true, 0);
		worlds[1] = new World(new File("MAPS/T1.dat"), v, 1, true, 3);
		worlds[2] = new World(new File("MAPS/T2.dat"), v, 2, true, 3);
		worlds[3] = new World(new File("MAPS/T3.dat"), v, 3, true, 3);
		worlds[4] = new World(new File("MAPS/T4.dat"), v, 4, true, 3);
		worlds[5] = new World(new File("MAPS/T5.dat"), v, 5, true, 3);
		worlds[6] = new World(new File("MAPS/T6.dat"), v, 6, true, 3);
		worlds[7] = new World(new File("MAPS/T7.dat"), v, 7, false, 3);
		worlds[8] = new World(new File("MAPS/Houses.dat"), v, 8, false, 2);
		worlds[9] = new World(new File("MAPS/Caves.dat"), v, 9, false, 1);
		worlds[10] = new World(new File("MAPS/HiddenValley.dat"), v, 10, false, 0);
		worlds[11] = new World(new File("MAPS/CodeLand.dat"), v, 11, false, 0);
		worlds[12] = new World(new File("MAPS/Narnia.dat"), v, 12, false, 0);
		return worlds;
	}

//###################################################################################################################################
//####################################  Obstacles  ##################################################################################
//#################################################################################################################################*/
	public static void loadObstacles(World[] world)
	{
		ObstacleAdder OA;
		OA = new ObstacleAdder(world[0]);
		loadOverworldObs(OA);
		OA = new ObstacleAdder(world[1]);
		loadT1Obs(OA, world[1]);
		OA = new ObstacleAdder(world[2]);
		loadT2Obs(OA);
		OA = new ObstacleAdder(world[3]);
		loadT3Obs(OA);
		OA = new ObstacleAdder(world[4]);
		loadT4Obs(OA);
		OA = new ObstacleAdder(world[5]);
		loadT5Obs(OA);
		OA = new ObstacleAdder(world[6]);
		loadT6Obs(OA);
		OA = new ObstacleAdder(world[7]);
		loadT7Obs(OA, world[7]);
		OA = new ObstacleAdder(world[8]);
		loadHouseObs(OA);
		OA = new ObstacleAdder(world[9]);
		loadCaveObs(OA, world[9]);
		OA = new ObstacleAdder(world[12]);
		loadNarniaObs(OA, world[12]);
	}

	public static void loadOverworldObs(ObstacleAdder OA)
	{
		OA.addRock(new Location( 0 ,3 ), new Location( 6 ,6 ));
		OA.addRock(new Location( 5 ,1 ), new Location( 12 ,4 ));
		OA.addBoulder(new Location( 5 ,3 ), new Location( 2 ,8 ));
		OA.addBoulder(new Location( 9 ,3 ), new Location( 5 ,7 ));
		OA.addBoulder(new Location( 9 ,4 ), new Location( 7 ,4 ));
		OA.addBoulder(new Location( 7 ,4 ), new Location( 10 ,3 ));
		OA.addBush(new Location( 7 ,7 ), new Location( 8 ,4 ));
		OA.addGate('a',new Location( 5 ,7 ), new Location( 6 ,4 )); //HH
		OA.addBoulder(new Location( 3 ,6 ), new Location( 9 ,8 ));
		OA.addBush(new Location( 0 ,5 ), new Location( 5 ,8 ));
		OA.addBush(new Location( 0 ,9 ), new Location( 4 ,9 ));
		OA.addBush(new Location( 0 ,9 ), new Location( 3 ,9 ));
		OA.addBush(new Location( 2 ,8 ), new Location( 6 ,9 ));
		OA.addBush(new Location( 3 ,9 ), new Location( 9 ,9 ));
		OA.addRock(new Location( 11 ,8 ), new Location( 9 ,2 ));
		OA.addRock(new Location( 11 ,9 ), new Location( 6 ,6 ));
		OA.addRock(new Location( 13 ,5 ), new Location( 7 ,6 ));
		OA.addRock(new Location( 12 ,1 ), new Location( 10 ,2 ));
		OA.addRock(new Location( 10 ,2 ), new Location( 2 ,6 ));
		OA.addRock(new Location( 8 ,1 ), new Location( 13 ,5 ));
		OA.addRock(new Location( 9 ,1 ), new Location( 7 ,2 ));
		OA.addRock(new Location( 1 ,7 ), new Location( 9 ,7 ));
		OA.addBoulder(new Location( 7 ,11 ), new Location( 13 ,5 ));
		OA.addBoulder(new Location( 7 ,11 ), new Location( 13 ,6 ));
		OA.addBoulder(new Location( 7 ,11 ), new Location( 13 ,7 ));
		OA.addSwitch(new Location(4,13), new Location(4,10), "01 01 01 01 00 00 00 00 02 00 00 00 01 01 01 01 01 01 01 00 00 00 00 00 02 00 00 00 01 01 01 01 01 01 00 00 01 00 03 02 07 00 01 00 01 01 01 01 01 01 00 00 00 00 02 00 00 00 00 01 01 01 01 00 01 01 00 01 00 00 02 00 00 00 01 01 01 00 00 00 01 00 00 00 00 00 02 00 00 00 00 01 01 00 00 00 01 00 01 00 00 00 02 00 00 00 00 00 00 00 00 00 01 00 00 00 00 00 09 02 05 00 00 00 00 00 00 00 01 01 00 00 01 00 00 00 09 02 02 02 02 02 02 02 01 01 01 01 01 01 00 00 00 00 00 00 00 00 00 00 01 01 01 01 00 01 01 00 00 00 00 00 00 00 00 00 01 01 01 01 01 01 01 01 00 00 00 00 00 00 00 00 ");
		OA.addSwitch(new Location(1,9), new Location(12,10), "01 01 01 01 01 00 01 01 01 00 01 00 01 00 01 01 01 01 01 01 01 00 00 00 01 00 01 00 01 01 01 01 01 01 01 00 01 01 01 00 01 00 01 00 01 01 00 00 01 01 00 00 01 01 01 00 01 00 01 00 01 01 00 01 01 00 00 00 00 01 01 00 01 00 00 00 01 00 00 01 00 00 00 00 00 00 01 00 01 01 01 01 01 00 01 01 00 00 00 01 00 00 01 00 00 00 00 00 01 00 01 01 01 00 00 00 00 00 01 01 01 01 01 00 01 00 00 00 01 01 00 00 00 00 00 01 01 01 01 00 01 01 01 01 01 01 00 00 00 00 01 01 01 01 01 01 01 01 01 01 01 01 00 00 01 01 01 01 01 01 01 01 00 01 01 01 01 01 01 01 01 01 01 01 01 01 01 01 01 01 01 01 ");
		OA.addSwitch(new Location(3,9), new Location(2,2), "01 00 01 01 00 01 01 01 00 01 01 01 01 01 01 01 01 00 01 01 00 00 00 01 00 01 01 01 01 01 01 01 00 00 00 01 01 01 00 00 00 01 00 01 00 00 01 01 01 01 01 01 01 01 01 00 01 01 00 00 00 00 01 01 00 00 00 00 01 01 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 00 00 01 01 00 01 00 00 00 00 00 00 00 01 01 00 01 01 01 01 00 00 00 00 00 00 01 01 00 01 01 00 00 00 01 01 01 00 00 00 00 01 01 00 00 01 01 01 01 00 01 01 00 00 00 01 01 01 01 01 01 01 01 01 01 00 01 01 00 01 01 01 01 01 01 00 00 00 00 00 00 00 01 00 00 00 01 01 01 01 01 01 01 01 01 01 01 01 01 00 00 00 01 01 01 01 01 ");
		OA.addBush(new Location(4,14), new Location(6, 4));
		OA.addGate('b',new Location(3,14), new Location(12, 4)); //JB
		OA.addGate('c',new Location(8,9 ), new Location(13,10)); //PP
		OA.addRock(new Location( 7 ,3 ), new Location( 9 ,7 ));
		OA.addRubble(new Location(6,0), new Location(7,5));
		OA.addRubble(new Location(6,0), new Location(5,6));
		OA.addRubble(new Location(6,0), new Location(6,6));
		OA.addRubble(new Location(6,0), new Location(7,7));
		OA.addRubble(new Location(6,0), new Location(8,7));
		OA.addRubble(new Location(6,0), new Location(5,8));
		OA.addRubble(new Location(6,0), new Location(6,8));
		OA.addRubble(new Location(6,0), new Location(6,9));
		OA.addRubble(new Location(6,0), new Location(8,9));

		OA.addDigSpot(new Location( 4 ,5 ), new Location( 2 ,4 ),new Item(Item.TYPE_LOVI, 25));
		OA.addDigSpot(new Location( 8 ,12), new Location( 7 ,3 ),new Key(Key.SYMBOL));
		OA.addDigSpot(new Location( 2 ,10), new Location( 7 ,5 ),new Key(Key.SYMBOL));
		OA.addDigSpot(new Location( 2 ,2 ), new Location( 8 ,4 ),new Key(Key.SYMBOL));
		OA.addDigSpot(new Location( 10 ,4), new Location( 6 ,4 ),new Key(Key.SYMBOL));
		OA.addDigSpot(new Location( 13 ,7), new Location( 5 ,4 ),new Key(Key.SYMBOL));
		OA.addDigSpot(new Location( 8 ,9 ), new Location( 3 ,4 ),new Key(Key.GATE, 'a')); //HH
		OA.addDigSpot(new Location(1,11), new Location(6,2), new Key(Key.GATE,'b'));      //JB
		OA.addDigSpot(new Location( 1 ,12), new Location( 11,9 ),new Item(Item.TYPE_LOVI,200));
		OA.addDigSpot(new Location( 8 ,7 ), new Location( 13,7 ),new Item(Item.TYPE_LOVI, 25));
	}
	public static void loadT1Obs(ObstacleAdder OA, World w)
	{
		//SaveTiles
		OA.addSaveTile(new Location(1,3), new Location(7,5));
		//Switches
		OA.addSwitch(new Location(3,0), new Location(7,1) , "22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 29 22 22 22 26 29 29 25 22 22 22 22 22 22 22 22 22 22 26 29 29 29 29 29 22 22 22 22 22 22 22 22 22 26 29 29 29 29 29 29 22 29 29 29 29 25 22 22 26 29 29 29 29 29 29 27 22 29 29 29 29 29 29 29 29 29 29 29 29 29 27 22 22 99 29 29 29 29 29 29 29 29 29 29 29 29 22 22 22 99 99 99 29 29 29 29 29 29 29 29 29 29 22 22 22 99 99 99 99 29 29 29 29 29 29 29 29 29 22 22 22 99 99 99 99 29 29 29 29 29 29 29 29 27 22 22 22 99 99 99 99 22 22 22 28 29 29 29 29 22 22 22 22 99 99 99 99 99 22 22 22 28 29 29 29 22 22 22 22 ");
		OA.addSwitch(new Location(2,2), new Location(2,5) , "22 22 22 22 22 22 29 29 29 22 22 22 22 22 22 22 22 22 22 22 22 22 29 29 29 22 99 99 22 22 22 22 22 22 22 22 99 99 29 29 29 99 99 99 99 22 22 22 22 22 22 99 99 99 29 29 29 99 99 99 99 99 22 22 22 22 22 99 99 99 29 29 29 99 99 99 29 29 22 22 22 22 29 99 99 99 29 29 29 99 99 29 29 29 29 29 22 22 22 99 99 99 29 29 29 99 99 29 29 29 29 29 22 22 22 99 99 99 29 29 29 99 99 99 29 29 22 22 22 22 22 99 99 99 29 29 29 99 99 99 99 22 22 22 22 22 22 99 99 99 29 29 29 99 99 99 22 22 22 22 22 22 22 22 99 99 29 29 29 99 99 22 22 22 22 22 22 22 22 22 22 22 29 29 29 22 22 22 22 22 22 22 ");
		OA.addSwitch(new Location(1,1), new Location(11,1), "22 22 22 22 22 26 29 29 25 22 22 22 22 22 22 22 22 22 22 22 26 29 29 29 29 29 25 29 22 22 22 22 22 22 22 29 29 29 29 29 29 29 29 29 29 25 22 22 22 22 99 29 29 29 29 29 29 29 29 29 29 29 25 22 22 29 99 99 99 29 29 99 99 99 29 29 29 29 29 25 22 22 99 99 99 99 99 99 99 99 99 29 29 29 29 29 22 22 99 99 99 99 99 99 99 99 99 99 29 29 29 29 22 22 99 99 99 99 99 99 99 99 99 99 99 99 99 22 22 29 29 99 99 99 29 29 29 29 99 99 99 99 99 22 22 29 29 29 99 22 22 28 29 29 29 29 99 99 99 22 29 29 29 29 22 22 22 22 22 28 29 29 29 99 22 22 22 22 22 22 22 22 22 22 22 22 28 29 29 22 22 22 ");
		OA.addSwitch(new Location(1,1), new Location(1,4) , "22 22 22 22 22 26 29 29 22 22 22 22 22 22 22 22 22 22 22 22 26 29 29 29 99 99 22 29 22 22 22 22 22 22 26 29 29 29 29 29 99 99 99 29 29 25 22 22 22 22 29 29 29 29 29 99 99 99 29 29 29 29 25 22 22 29 29 29 29 29 29 99 99 99 29 29 29 29 29 25 22 22 29 29 29 29 29 29 99 99 99 29 29 29 29 29 22 22 29 29 29 29 29 29 99 99 99 99 29 29 29 29 22 26 29 29 29 29 29 29 29 99 99 99 99 99 99 22 22 29 29 29 29 29 29 29 29 29 99 99 99 99 99 22 22 29 29 29 29 27 22 28 29 29 29 29 99 99 99 22 29 29 29 29 27 22 22 22 22 28 29 29 29 99 22 22 22 22 22 22 22 22 22 22 22 22 28 29 29 22 22 22 ");
		//Chests
		OA.addChest(new Location(2,0), new Location(2,3), new Item(Item.TYPE_BOW));
		OA.addChest(new Location(1,0), new Location(13,2), new Key(Key.CIRCLE));
		OA.addChest(new Location(3,0), new Location(13,1), new Item(Item.TYPE_LOVI, 10));
		OA.addChest(new Location(3,1), new Location(2,2), new Key(Key.TRIANGLE));
		OA.addChest(new Location(0,3), new Location(2,8), new Item(Item.TYPE_LOVI, 10));
		//KeyBlocks
		OA.addKeyBlock(KeyDoor.CIRCLE, new Location(1,1), new Location(0,10));
		OA.addKeyBlock(KeyDoor.TRIANGLE, new Location(3,1), new Location(10,3));
		//Dispensers
		OA.addDispenser(new Location(2,1), new Location(8,1));
		OA.addDispenser(new Location(0,1), new Location(1,9));
		//Triggers
		OA.addTrigger(22, new Location(0,1), new Location(14, 10));

		new PersonAdder(w).addSign(new Location(1,3), new Location(8,5), "This is a Save Tile.\n\nYou can only save in Temples while standing on it.");
		new PersonAdder(w).addSign(new Location(2,1), new Location(9,1), "This is a Dispenser.\n\nPress SPACE while facing it to grab arrows");
	}
	public static void loadT2Obs(ObstacleAdder OA)
	{
		//Save Tiles
		OA.addSaveTile(new Location(0,2),new Location(4,1));
		OA.addSaveTile(new Location(4,3),new Location(4,6));
		OA.addSaveTile(new Location(4,1),new Location(3,3));
		//Chests
		OA.addChest(new Location( 1 ,3 ), new Location( 6 ,1 ), new Key(Key.TRIANGLE));
		OA.addChest(new Location( 1 ,0 ), new Location( 2 ,2 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 2 ,0 ), new Location( 13 ,1 ), new Item(Item.TYPE_LOVI, 10));
		OA.addChest(new Location( 4 ,0 ), new Location( 7 ,1 ), new Item(Item.TYPE_TORCH));
		OA.addChest(new Location( 3 ,2 ), new Location( 13 ,2 ), new Item(Item.TYPE_LOVI, 25));
		OA.addChest(new Location( 3 ,3 ), new Location( 12 ,2 ), new Key(Key.CIRCLE));
		//Bushes
		OA.addBush(new Location( 1 ,3 ), new Location( 6 ,8 ));
		OA.addBush(new Location( 1 ,0 ), new Location( 5 ,9 ));
		OA.addBush(new Location( 3 ,2 ), new Location( 11 ,8 ));
		//KeyBlocks
		OA.addKeyBlock(KeyDoor.TRIANGLE,new Location( 2 ,1 ), new Location( 5 ,2 ));
		OA.addKeyBlock(KeyDoor.CIRCLE,new Location( 3 ,1 ), new Location( 5 ,2 ));
		OA.addKeyBlock(KeyDoor.SQUARE,new Location( 2 ,3 ), new Location( 13 ,7 ));
		//Switches
		OA.addSwitch(new Location(0,2), new Location(11,1), "92 23 22 24 92 92 92 92 92 92 92 92 92 92 92 92 92 22 39 22 00 92 92 92 12 11 14 00 92 92 92 92 92 00 00 00 00 00 12 11 11 11 11 14 92 92 92 92 92 00 00 12 55 11 11 11 11 11 11 11 11 14 92 92 92 92 12 11 55 11 11 11 11 20 11 11 11 16 00 92 92 92 18 11 55 11 11 20 11 11 11 11 11 00 00 92 92 92 92 18 55 11 11 11 11 11 11 11 11 00 00 92 92 92 92 92 00 00 18 11 11 11 11 11 16 92 00 92 92 92 92 00 00 00 00 92 18 11 92 00 92 92 00 00 92 92 92 00 00 00 00 92 92 11 92 00 92 92 92 92 92 92 92 92 00 00 92 12 11 16 92 00 00 00 00 00 92 92 92 92 00 00 92 11 92 92 92 92 92 92 92 92 ");
	}
	public static void loadT3Obs(ObstacleAdder OA)
	{
		//SaveTiles
		OA.addSaveTile(new Location( 2 ,4 ), new Location( 8 ,5 ));
		//Chests
		OA.addChest(new Location( 2 ,3 ), new Location( 6 ,3 ), new Item(Item.TYPE_POKINGSTICK));
		OA.addChest(new Location( 0 ,3 ), new Location( 5 ,2 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 1 ,3 ), new Location( 14 ,1 ), new Item(Item.TYPE_LOVI, 25));
		OA.addChest(new Location( 0 ,2 ), new Location( 11 ,2 ), new Key(Key.CIRCLE));
		OA.addChest(new Location( 0 ,0 ), new Location( 5 ,2 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 4 ,0 ), new Location( 6 ,1 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 4 ,2 ), new Location( 12 ,4 ), new Key(Key.CIRCLE));
		OA.addChest(new Location( 4 ,3 ), new Location( 10 ,2 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 3 ,3 ), new Location( 8 ,6 ), new Key(Key.TRIANGLE));
		//KeyBlocks
		OA.addKeyBlock(Key.SQUARE,new Location( 2 ,3 ), new Location( 7 ,8 ));
		OA.addKeyBlock(Key.SQUARE,new Location( 2 ,3 ), new Location( 7 ,7 ));
		OA.addKeyBlock(Key.SQUARE,new Location( 2 ,3 ), new Location( 8 ,2 ));
		OA.addKeyBlock(Key.SQUARE,new Location( 2 ,3 ), new Location( 8 ,1 ));
		OA.addKeyBlock(Key.TRIANGLE,new Location( 0 ,4 ), new Location( 5 ,1 ));
		OA.addKeyBlock(Key.TRIANGLE,new Location( 0 ,1 ), new Location( 5 ,1 ));
		OA.addKeyBlock(Key.CIRCLE,new Location( 4 ,4 ), new Location( 10 ,1 ));
		OA.addKeyBlock(Key.CIRCLE,new Location( 4 ,0 ), new Location( 2 ,6 ));
		//Boulders
		OA.addBoulder(new Location( 0 ,4 ), new Location( 5 ,5 ));
		OA.addBoulder(new Location( 1 ,3 ), new Location( 8 ,3 ));
		OA.addBoulder(new Location( 1 ,1 ), new Location( 8 ,4 ));
		OA.addBoulder(new Location( 2 ,0 ), new Location( 6 ,7 ));
		OA.addBoulder(new Location( 4 ,2 ), new Location( 2 ,7 ));
		OA.addBoulder(new Location( 3 ,3 ), new Location( 8 ,1 ));
		OA.addBoulder(new Location( 4 ,0 ), new Location( 2 ,4 ));
		//Furniture
		OA.addSuitOfArmor(new Location( 0 ,4 ), new Location( 4 ,2 ));
		OA.addSuitOfArmor(new Location( 0 ,4 ), new Location( 6 ,2 ));
		OA.addSuitOfArmor(new Location( 0 ,4 ), new Location( 5 ,6 ));
		OA.addSuitOfArmor(new Location( 1 ,3 ), new Location( 3 ,2 ));
		OA.addBed(new Location( 1 ,3 ), new Location( 10 ,7 ));
		OA.addBed(new Location( 1 ,3 ), new Location( 13 ,7 ));
		OA.addBed(new Location( 1 ,3 ), new Location( 10 ,1 ));
		OA.addBed(new Location( 1 ,3 ), new Location( 13 ,1 ));
		OA.addDresser(new Location( 0 ,2 ), new Location( 1 ,1 ));
		OA.addDresser(new Location( 0 ,2 ), new Location( 7 ,1 ));
		OA.addMagicshelf(new Location( 0 ,2 ), new Location( 3 ,1 ));
		OA.addMagicshelf(new Location( 0 ,2 ), new Location( 6 ,1 ));
		OA.addDresser(new Location( 0 ,2 ), new Location( 4 ,1 ));
		OA.addDrawer(new Location( 0 ,2 ), new Location( 1 ,4 ), new Item(Item.TYPE_LOVI, 5));
		OA.addDrawer(new Location( 0 ,2 ), new Location( 8 ,4 ), new Item(Item.TYPE_HEART_SMALL));
		OA.addSuitOfArmor(new Location( 1 ,1 ), new Location( 4 ,3 ));
		OA.addSuitOfArmor(new Location( 1 ,1 ), new Location( 6 ,3 ));
		OA.addSuitOfArmor(new Location( 1 ,1 ), new Location( 12 ,3 ));
		OA.addSuitOfArmor(new Location( 1 ,1 ), new Location( 10 ,3 ));
		OA.addSuitOfArmor(new Location( 1 ,1 ), new Location( 11 ,4 ));
		OA.addSuitOfArmor(new Location( 1 ,1 ), new Location( 5 ,4 ));
		OA.addSuitOfArmor(new Location( 1 ,1 ), new Location( 8 ,5 ));
		OA.addBed(new Location( 1 ,0 ), new Location( 1 ,1 ));
		OA.addBed(new Location( 1 ,0 ), new Location( 3 ,1 ));
		OA.addBed(new Location( 1 ,0 ), new Location( 5 ,1 ));
		OA.addBookshelf(new Location( 1 ,0 ), new Location( 2 ,1 ));
		OA.addBookshelf(new Location( 1 ,0 ), new Location( 4 ,1 ));
		OA.addBookshelf(new Location( 1 ,0 ), new Location( 6 ,1 ));
		OA.addDresser(new Location( 1 ,0 ), new Location( 1 ,4 ));
		OA.addSuitOfArmor(new Location( 1 ,0 ), new Location( 1 ,8 ));
		OA.addMagicshelf(new Location( 1 ,0 ), new Location( 8 ,1 ));
		OA.addMagicshelf(new Location( 1 ,0 ), new Location( 14 ,1 ));
		OA.addDresser(new Location( 1 ,0 ), new Location( 9 ,1 ));
		OA.addDresser(new Location( 1 ,0 ), new Location( 12 ,1 ));
		OA.addDrawer(new Location( 1 ,0 ), new Location( 11 ,1 ), new Item(Item.TYPE_LOVI, 10));
		OA.addSuitOfArmor(new Location( 2 ,0 ), new Location( 5 ,1 ));
		OA.addSuitOfArmor(new Location( 2 ,0 ), new Location( 7 ,1 ));
		OA.addDresser(new Location( 2 ,0 ), new Location( 3 ,1 ));
		OA.addDrawer(new Location( 2 ,0 ), new Location( 2 ,1 ), null);
		OA.addDrawer(new Location( 2 ,0 ), new Location( 10 ,3 ), new Key(Key.TRIANGLE));
		OA.addMagicshelf(new Location( 2 ,0 ), new Location( 1 ,5 ));
		OA.addFireplace(new Location( 3 ,3 ), new Location( 8 ,3 ));
		OA.addSuitOfArmor(new Location( 3 ,3 ), new Location( 4 ,5 ));
		OA.addSuitOfArmor(new Location( 3 ,3 ), new Location( 12 ,5 ));
		OA.addSuitOfArmor(new Location( 3 ,3 ), new Location( 4 ,9 ));
		OA.addSuitOfArmor(new Location( 3 ,3 ), new Location( 12 ,9 ));
		OA.addSuitOfArmor(new Location( 3 ,3 ), new Location( 12 ,3 ));
		OA.addSuitOfArmor(new Location( 3 ,3 ), new Location( 4 ,3 ));
		OA.addSuitOfArmor(new Location( 4 ,4 ), new Location( 8 ,2 ));
		OA.addSuitOfArmor(new Location( 3 ,4 ), new Location( 12 ,1 ));
		OA.addSuitOfArmor(new Location( 3 ,4 ), new Location( 14 ,2 ));
		OA.addSuitOfArmor(new Location( 3 ,4 ), new Location( 3 ,1 ));
		OA.addSuitOfArmor(new Location( 3 ,4 ), new Location( 1 ,2 ));
		OA.addSuitOfArmor(new Location( 3 ,4 ), new Location( 1 ,1 ));
		OA.addSuitOfArmor(new Location( 2 ,4 ), new Location( 3 ,2 ));
		OA.addSuitOfArmor(new Location( 2 ,4 ), new Location( 2 ,3 ));
		OA.addSuitOfArmor(new Location( 2 ,4 ), new Location( 12 ,2 ));
		OA.addSuitOfArmor(new Location( 2 ,4 ), new Location( 13 ,3 ));
		OA.addSuitOfArmor(new Location( 2 ,4 ), new Location( 2 ,9 ));
		OA.addSuitOfArmor(new Location( 2 ,4 ), new Location( 13 ,9 ));
		OA.addSuitOfArmor(new Location( 2 ,3 ), new Location( 4 ,3 ));
		OA.addSuitOfArmor(new Location( 2 ,3 ), new Location( 11 ,3 ));
		OA.addFireplace(new Location( 1 ,3 ), new Location( 12 ,0 ));
		OA.addFireplace(new Location( 1 ,3 ), new Location( 11 ,6 ));
		OA.addSuitOfArmor(new Location( 1 ,1 ), new Location( 12 ,8 ));
		OA.addFireplace(new Location( 1 ,0 ), new Location( 3 ,0 ));
		OA.addFireplace(new Location( 2 ,0 ), new Location( 6 ,0 ));
		//Triggers
		OA.addTrigger(49, new Location(2,2), new Location(8,10));
	}
	public static void loadT4Obs(ObstacleAdder OA)
	{
		//Save Tiles
		OA.addSaveTile(new Location( 2 ,4 ), new Location( 9 ,9 ));
		OA.addSaveTile(new Location( 1 ,2 ), new Location( 5 ,2 ));
		OA.addSaveTile(new Location( 3 ,2 ), new Location( 11 ,4 ));
		//Chests
		OA.addChest(new Location( 0 ,0 ), new Location( 2 ,2 ),new Item(Item.TYPE_FASTSHOES));
		OA.addChest(new Location( 0 ,2 ), new Location( 9 ,6 ),new Key(Key.TRIANGLE));
		OA.addChest(new Location( 3 ,4 ), new Location( 7 ,3 ),new Key(Key.CIRCLE));
		OA.addChest(new Location( 4 ,1 ), new Location( 6 ,6 ),new Key(Key.CIRCLE));
		OA.addChest(new Location( 4 ,2 ), new Location( 13 ,2 ),new Key(Key.SQUARE));
		OA.addChest(new Location( 1 ,4 ), new Location( 13 ,1 ),new Item(Item.TYPE_LOVI, 25));
		//Key Blocks
		OA.addKeyBlock(Key.CIRCLE,new Location( 3 ,3 ), new Location( 15 ,8 ));
		OA.addKeyBlock(Key.SQUARE,new Location( 2 ,2 ), new Location( 7 ,0 ));
		OA.addKeyBlock(Key.CIRCLE,new Location( 0 ,4 ), new Location( 9 ,7 ));
		OA.addKeyBlock(Key.TRIANGLE,new Location( 0 ,0 ), new Location( 4 ,4 ));
		//Switches
		OA.addSwitch(new Location(4,2), new Location(14,2), "04 04 04 04 04 04 04 04 04 04 04 04 04 04 04 04 04 58 58 58 58 58 58 58 58 58 58 58 58 58 58 04 04 58 04 04 04 04 04 04 04 04 04 04 58 58 58 04 04 58 04 04 04 04 04 04 04 04 04 04 58 58 58 04 04 58 04 04 04 04 04 04 04 04 04 04 04 04 04 04 04 58 04 04 04 04 04 04 04 04 04 04 04 04 04 04 04 58 04 04 04 04 04 04 04 04 04 04 04 04 04 04 04 58 04 04 04 04 04 04 04 04 04 04 04 04 04 04 04 58 04 04 04 04 04 04 04 04 04 04 04 04 04 04 04 58 04 04 04 04 04 04 04 04 04 04 04 04 04 04 04 58 04 58 04 58 04 58 04 58 04 58 04 58 04 04 04 58 04 58 04 58 04 58 04 58 04 58 04 58 04 04 ");
		//Triggers
		OA.addTrigger(4, new Location(2,1), new Location(7,10));
	}
	public static void loadT5Obs(ObstacleAdder OA)
	{
		//SaveTiles
		OA.addSaveTile(new Location( 1 ,4 ), new Location( 7 ,5 ));
		OA.addSaveTile(new Location( 4 ,3 ), new Location( 8 ,6 ));
		//Rocks
		OA.addRock(new Location( 0 ,4 ), new Location( 3 ,8 ));
		OA.addRock(new Location( 2 ,0 ), new Location( 7 ,7 ));
		OA.addRock(new Location( 2 ,2 ), new Location( 6 ,7 ));
		OA.addRock(new Location( 3 ,1 ), new Location( 7 ,4 ));
		//Boulders
		OA.addBoulder(new Location( 2 ,4 ), new Location( 10 ,6 ));
		OA.addBoulder(new Location( 0 ,3 ), new Location( 8 ,4 ));
		OA.addBoulder(new Location( 0 ,3 ), new Location( 8 ,5 ));
		OA.addBoulder(new Location( 2 ,2 ), new Location( 7 ,7 ));
		OA.addBoulder(new Location( 2 ,2 ), new Location( 10 ,7 ));
		OA.addBoulder(new Location( 2 ,1 ), new Location( 6 ,3 ));
		OA.addBoulder(new Location( 4 ,2 ), new Location( 8 ,7 ));
		//Chests
		OA.addChest(new Location( 2 ,4 ), new Location( 11 ,1 ), new Key(Key.TRIANGLE));
		OA.addChest(new Location( 0 ,4 ), new Location( 2 ,6 ), new Item(Item.TYPE_LOVI,25));
		OA.addChest(new Location( 0 ,2 ), new Location( 13 ,1 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 2 ,0 ), new Location( 13 ,2 ), new Item(Item.TYPE_HAMMER));
		OA.addChest(new Location( 3 ,1 ), new Location( 2 ,2 ), new Key(Key.CIRCLE));
		OA.addChest(new Location( 3 ,0 ), new Location( 2 ,3 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 4 ,2 ), new Location( 7 ,1 ), new Key(Key.TRIANGLE));
		OA.addChest(new Location( 4 ,2 ), new Location( 8 ,1 ), new Item(Item.TYPE_LOVI,5));
		OA.addChest(new Location( 3 ,4 ), new Location( 2 ,5 ), new Item(Item.TYPE_LOVI,5));
		OA.addChest(new Location( 5 ,4 ), new Location( 12 ,3 ), new Key(Key.TRIANGLE));
		OA.addChest(new Location( 3 ,3 ), new Location( 4 ,5 ), new Key(Key.SQUARE));
		//KeyBlocks
		OA.addKeyBlock(KeyDoor.SQUARE,new Location( 5 ,2 ), new Location( 8 ,1 ));
		OA.addKeyBlock(KeyDoor.SQUARE,new Location( 5 ,2 ), new Location( 8 ,3 ));
		OA.addKeyBlock(KeyDoor.TRIANGLE,new Location( 3 ,2 ), new Location( 5 ,5 ));
		OA.addKeyBlock(KeyDoor.TRIANGLE,new Location( 3 ,2 ), new Location( 9 ,2 ));
		OA.addKeyBlock(KeyDoor.SQUARE,new Location( 1 ,2 ), new Location( 9 ,1 ));
		OA.addKeyBlock(KeyDoor.TRIANGLE,new Location( 0 ,3 ), new Location( 8 ,3 ));
		OA.addKeyBlock(KeyDoor.CIRCLE,new Location( 2 ,3 ), new Location( 4 ,3 ));
		//Switches
		OA.addSwitch(new Location(2,3), new Location(11,3), "22 22 22 22 22 46 46 48 22 22 22 22 22 22 22 22 22 22 22 22 22 46 46 46 22 22 22 22 22 22 22 22 22 22 22 29 29 48 46 46 29 29 29 22 22 22 22 22 22 22 29 29 29 46 46 46 46 29 29 29 22 22 22 22 22 29 29 29 29 46 46 46 48 29 29 29 29 29 22 22 47 47 29 29 29 29 55 55 55 29 29 29 29 29 29 29 47 47 47 47 29 29 48 46 46 29 29 29 29 29 29 29 47 47 47 47 47 47 46 46 46 29 29 29 29 29 29 22 47 47 47 47 47 47 47 46 46 46 29 29 29 22 22 22 22 22 22 22 47 47 47 47 46 46 29 29 29 22 22 22 22 22 22 22 22 22 47 47 48 46 22 22 22 22 22 22 22 22 22 22 22 22 22 46 46 46 22 22 22 22 22 22 ");
		OA.addSwitch(new Location(4,0), new Location(1,1), "22 22 22 22 48 48 48 48 48 22 22 22 22 22 22 22 22 29 29 47 48 48 47 47 47 29 30 25 22 22 22 22 22 47 47 47 47 47 47 47 29 29 29 29 25 22 22 22 47 47 47 47 47 47 47 47 29 29 29 29 29 25 22 22 47 47 47 47 47 47 47 27 22 28 29 29 29 29 22 22 47 47 47 47 47 47 29 25 22 22 28 29 29 29 22 22 47 47 47 47 47 47 29 29 30 25 22 29 29 29 22 22 47 47 47 47 47 47 29 29 29 29 22 30 29 29 22 22 47 47 47 47 47 29 29 29 29 29 22 28 29 29 25 22 48 47 47 47 47 29 29 29 29 29 22 22 29 29 29 22 48 48 48 48 47 29 29 29 29 30 22 22 28 29 29 22 48 48 48 48 48 48 29 29 29 29 22 22 22 29 29 22 ");
		//Triggers
		OA.addTrigger(46, new Location(5,1), new Location(8,9));
		OA.addTrigger(46, new Location(5,1), new Location(8,10));
	}
	public static void loadT6Obs(ObstacleAdder OA)
	{
		//SaveTiles
		OA.addSaveTile(new Location( 1 ,1 ), new Location( 2 ,4 ));
		OA.addSaveTile(new Location( 2 ,3 ), new Location( 14 ,9 ));
		OA.addSaveTile(new Location( 4 ,4 ), new Location( 12 ,5 ));
		//Switches
		OA.addSwitch(new Location(2,3), new Location(1,10), "22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 26 29 29 29 29 29 22 22 22 22 22 22 22 22 26 30 29 29 29 29 29 29 22 22 22 22 22 22 22 26 29 29 29 29 29 29 29 29 22 22 22 22 22 22 22 30 29 29 29 29 29 29 29 29 22 22 22 22 22 22 22 29 29 29 29 29 29 30 29 29 22 29 22 22 22 22 22 29 29 29 30 27 22 22 22 22 22 22 22 22 22 22 22 29 29 27 22 22 22 22 22 22 ");
		OA.addSwitch(new Location(0,3), new Location(4,9) , "22 22 22 22 22 22 26 29 29 25 22 22 22 22 22 22 22 22 22 22 26 29 29 29 29 29 29 25 22 22 22 22 22 22 26 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 11 11 29 29 29 29 29 29 29 29 29 11 11 11 22 22 11 11 11 11 11 55 11 11 11 11 11 11 11 11 22 22 11 20 11 11 11 55 11 11 11 20 11 11 11 11 22 11 11 11 11 11 11 55 11 11 11 11 11 11 11 11 22 11 11 11 29 11 11 55 11 11 11 11 11 11 20 11 22 11 20 11 11 11 11 55 11 11 20 11 11 11 11 11 22 11 11 11 11 11 11 55 11 11 11 11 11 11 11 11 ");
		OA.addSwitch(new Location(0,1), new Location(1,1) , "22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 61 61 61 61 61 61 61 61 61 61 61 61 61 61 22 22 22 22 22 22 22 22 22 22 22 22 61 22 22 22 22 ");
		OA.addSwitch(new Location(1,1), new Location(13,2), "22 22 22 22 22 22 63 22 22 22 35 35 22 22 22 22 22 22 22 22 22 22 63 22 22 22 35 35 22 22 22 22 22 22 22 26 29 29 29 29 29 11 35 35 11 29 22 22 22 26 29 29 29 29 29 29 29 11 36 36 11 11 22 22 22 29 29 29 29 29 29 29 29 11 11 11 11 11 11 11 22 29 29 29 29 29 29 29 29 11 11 11 11 11 11 11 22 29 29 29 29 29 29 29 29 29 11 11 11 11 11 11 22 29 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 28 29 29 29 29 29 29 29 29 29 29 29 29 29 29 22 22 28 29 29 29 29 29 29 29 29 29 29 27 22 22 22 22 22 22 28 29 29 29 29 29 29 27 22 22 22 22 22 22 22 22 22 28 29 29 29 27 22 22 22 22 22 22 ");
		//Chests
		OA.addChest(new Location( 4 ,1 ), new Location( 6 ,2 ), new Key(Key.CIRCLE));
		OA.addChest(new Location( 2 ,2 ), new Location( 6 ,3 ), new Item(Item.TYPE_FLIPPER));
		OA.addChest(new Location( 2 ,0 ), new Location( 11 ,1 ), new Item(Item.TYPE_BOW));
		OA.addChest(new Location( 0 ,4 ), new Location( 2 ,3 ), new Item(Item.TYPE_TORCH));
		OA.addChest(new Location( 0 ,0 ), new Location( 7 ,2 ), new Item(Item.TYPE_FASTSHOES));
		OA.addChest(new Location( 0 ,1 ), new Location( 3 ,1 ), new Item(Item.TYPE_POKINGSTICK));
		OA.addChest(new Location( 1 ,2 ), new Location( 12 ,2 ), new Item(Item.TYPE_SWIMLESSONS));
		OA.addChest(new Location( 1 ,0 ), new Location( 12 ,1 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 2 ,4 ), new Location( 13 ,1 ), new Item(Item.TYPE_SWORD));
		OA.addChest(new Location( 5 ,4 ), new Location( 3 ,7 ), new Key(Key.TRIANGLE));
		OA.addChest(new Location( 4 ,0 ), new Location( 6 ,1 ), new Item(Item.TYPE_HAMMER));
		//Keyblocks
		OA.addKeyBlock(KeyDoor.CIRCLE,new Location( 3 ,4 ), new Location( 11 ,8 ));
		OA.addKeyBlock(KeyDoor.TRIANGLE,new Location( 3 ,2 ), new Location( 8 ,0 ));
		OA.addKeyBlock(KeyDoor.SQUARE,new Location( 5 ,2 ), new Location( 11 ,10 ));
		//Misc
		OA.addRock(new Location( 5 ,2 ), new Location( 11 ,9 ));
		OA.addBush(new Location( 0 ,0 ), new Location( 6 ,3 ));
		OA.addBoulder(new Location( 1 ,2 ), new Location( 12 ,8 ));
                OA.addDispenser(new Location( 1 ,1 ), new Location( 3 ,3 ));
                OA.addRock(new Location( 4 ,1 ), new Location( 6 ,4 ));
                OA.addRock(new Location( 4 ,1 ), new Location( 7 ,6 ));
                OA.addRock(new Location( 4 ,1 ), new Location( 6 ,9 ));
	}
	public static void loadT7Obs(ObstacleAdder OA, World w)
	{
		//SaveTiles
		OA.addSaveTile(new Location( 0 ,15 ), new Location( 8 ,10 ));
		OA.addSaveTile(new Location( 2 ,13 ), new Location( 9 ,10 ));
		OA.addSaveTile(new Location( 0 ,11 ), new Location( 1 ,10 ));
		OA.addSaveTile(new Location( 2 ,9 ), new Location( 14 ,10 ));
		OA.addSaveTile(new Location( 0 ,7 ), new Location( 1 ,10 ));
		OA.addSaveTile(new Location( 2 ,5 ), new Location( 10 ,10 ));
		OA.addSaveTile(new Location( 0 ,3 ), new Location( 4 ,4 ));
		//Switches
		OA.addSwitch(new Location(1,14), new Location(11,4), "49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 99 99 99 99 99 99 99 22 22 22 22 22 22 22 22 22 99 99 99 99 99 99 99 99 22 22 22 22 22 22 22 26 29 99 99 99 99 99 99 99 29 22 22 22 22 29 29 29 29 29 99 99 99 99 99 99 22 26 29 29 29 29 29 29 29 29 99 99 99 99 99 29 29 29 29 29 29 22 22 28 29 99 99 99 99 29 29 29 29 29 29 30 27 22 22 22 99 99 99 99 29 29 29 29 29 29 29 27 22 22 22 22 99 99 99 29 29 29 29 29 29 29 27 22 22 22 22 22 22 22 22 29 29 29 29 29 30 27 22 22 22 22 22 22 22 22 22 28 29 29 29 29 27 22 22 22 22 ");
		OA.addSwitch(new Location(2,15), new Location(2,1), "22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 29 22 29 22 29 22 29 22 29 22 29 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 29 49 22 22 29 29 29 29 29 22 22 22 29 29 29 22 22 49 22 29 29 29 29 29 29 22 22 22 29 29 29 22 22 49 29 29 29 29 29 29 29 22 22 22 29 29 29 22 22 49 29 29 29 29 29 29 29 22 22 22 29 29 29 22 22 49 29 29 22 22 22 22 22 22 22 22 22 22 22 22 22 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 ");
		OA.addSwitch(new Location(2,15), new Location(4,1), "22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 29 22 29 22 29 22 29 22 29 22 29 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 29 49 22 22 29 29 29 29 29 22 29 29 29 22 29 22 22 49 22 22 29 29 29 29 29 22 29 29 29 22 29 22 22 49 29 22 29 29 29 29 29 22 29 29 29 22 29 22 22 49 29 22 29 29 29 29 29 22 29 29 29 22 29 22 22 49 29 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 ");
		OA.addSwitch(new Location(2,15), new Location(6,1), "22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 29 22 29 22 29 22 29 22 29 22 29 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 29 49 22 22 22 22 29 29 29 29 29 22 29 29 29 29 22 49 22 22 22 22 29 29 29 29 29 22 29 29 29 29 22 49 29 22 22 22 29 29 29 29 29 22 29 29 29 29 22 49 29 22 22 22 29 29 29 29 29 22 29 29 29 29 22 49 29 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 ");
		OA.addSwitch(new Location(2,15), new Location(8,1), "22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 29 22 29 22 29 22 29 22 29 22 29 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 29 49 22 22 29 22 29 29 29 22 29 29 29 29 29 29 22 49 22 22 29 22 29 29 29 22 29 29 29 29 29 29 22 49 29 22 29 22 29 29 29 22 29 29 29 29 29 29 22 49 29 22 29 22 29 29 29 22 29 29 29 29 29 29 22 49 29 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 ");
		OA.addSwitch(new Location(2,15), new Location(10,1), "22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 29 22 29 22 29 22 29 22 29 22 29 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 29 49 22 22 22 22 22 22 22 22 22 22 29 29 29 29 29 49 22 29 22 22 22 22 22 22 22 22 29 29 29 29 29 49 29 29 22 22 22 22 22 22 22 22 29 29 29 29 29 49 29 29 22 22 22 22 22 22 22 22 29 29 29 29 29 49 29 29 22 22 22 22 22 22 22 22 29 29 29 29 29 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 ");
		OA.addSwitch(new Location(2,15), new Location(12,1), "22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 29 22 29 22 29 22 29 22 29 22 29 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 22 22 22 22 22 22 22 22 22 22 22 22 22 22 29 49 22 22 29 29 29 22 29 29 29 29 29 29 29 29 22 49 22 22 29 29 29 22 29 29 29 29 29 29 29 29 22 49 29 22 29 29 29 22 29 29 29 29 29 29 29 29 22 49 29 22 29 29 29 22 29 29 29 29 29 29 29 29 22 49 29 22 22 22 22 22 22 22 22 22 22 22 22 22 22 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 49 ");
		//Ice Floor
		OA.addIceBlock(new Location(2,8), new Location(4,2));
		OA.addIceBlock(new Location(2,8), new Location(4,3));
		OA.addIceBlock(new Location(0,8), new Location(7,10));
		OA.addIceBlock(new Location(0,8), new Location(8,10));
		OA.addIceBlock(new Location(0,8), new Location(9,10));
		OA.addIceCrack(new Location(2,8), new Location(10,2));
		OA.addIceCrack(new Location(0,8), new Location(4,1));
		//Chests+Digspots
		OA.addChest(new Location( 2 ,15 ), new Location( 14 ,5 ), new Key(Key.CIRCLE));
		OA.addChest(new Location( 2 ,8 ), new Location( 10 ,1 ), new Key(Key.TRIANGLE));
		OA.addChest(new Location( 0 ,6 ), new Location( 2 ,1 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 0 ,3 ), new Location( 11 ,4 ), new Item(Item.SHOP_POTION));
		OA.addDigSpot(new Location( 0 ,5 ), new Location( 3 ,7 ), new Item(Item.TYPE_HEART_PIECE));
		//KeyBlocks
		OA.addKeyBlock(KeyDoor.CIRCLE,new Location( 0 ,16 ), new Location( 7 ,2 ));
		OA.addKeyBlock(KeyDoor.SQUARE,new Location( 0 ,16 ), new Location( 7 ,1 ));
		OA.addKeyBlock(KeyDoor.TRIANGLE,new Location( 0 ,16 ), new Location( 7 ,0 ));
		OA.addKeyBlock(KeyDoor.CIRCLE,new Location( 2 ,14 ), new Location( 11 ,0 ));
		OA.addKeyBlock(KeyDoor.TRIANGLE,new Location( 1 ,9 ), new Location( 1 ,6 ));
		OA.addKeyBlock(KeyDoor.SQUARE,new Location( 2 ,6 ), new Location( 13 ,0 ));
		OA.addKeyBlock(KeyDoor.SQUARE,new Location( 2 ,10 ), new Location( 12 ,0 ));
		OA.addKeyBlock(KeyDoor.TRIANGLE,new Location( 2 ,10 ), new Location( 12 ,1 ));
		OA.addKeyBlock(KeyDoor.CIRCLE,new Location( 2 ,10 ), new Location( 12 ,2 ));
		//Triggers

		//Signs
		new PersonAdder(w).addSign(new Location( 0 ,16 ), new Location( 6 ,1 ),"The keys you seek can be found only where the water is most pure.");
	}
	public static void loadHouseObs(ObstacleAdder o)
	{
		//HH
		o.addChest(new Location( 9 ,2 ), new Location( 1 ,1 ), new Key(Key.SQUARE));
		o.addKeyBlock(KeyDoor.SQUARE,new Location( 9 ,2 ), new Location( 11 ,5 ));
		o.addKeyBlock(KeyDoor.TRIANGLE,new Location( 10 ,1 ), new Location( 1 ,6 ));
		o.addChest(new Location( 11 ,3 ), new Location( 4 ,4 ), new Key(Key.TRIANGLE));
		o.addSwitch(new Location(10,2), new Location(12,5),"73 73 73 73 73 73 73 70 70 73 73 73 73 73 73 73 73 73 73 73 73 73 70 70 70 70 73 73 73 73 73 73 73 73 73 73 73 70 70 70 70 70 70 73 73 73 73 73 73 73 73 73 73 70 70 70 70 70 70 73 73 73 73 73 73 73 73 73 73 49 49 70 70 70 70 73 73 73 73 73 70 70 70 70 70 70 49 70 70 70 70 70 70 70 70 70 70 70 70 70 70 70 49 70 70 70 70 70 70 70 70 70 73 73 73 73 73 49 49 70 70 70 70 73 73 73 73 73 73 73 73 73 73 70 70 70 70 70 70 73 73 73 73 73 73 73 73 73 73 70 70 70 70 70 70 73 73 73 73 73 73 73 73 73 73 73 70 70 70 70 73 73 73 73 73 73 73 73 73 73 73 73 73 73 73 73 73 73 73 73 73 73 ");
		o.addTrigger(73,new Location( 9 ,0 ), new Location( 8 ,10 ));
		o.addTrigger(73,new Location( 8 ,1 ), new Location( 8 ,1 ));
		o.addTrigger(73,new Location( 10 ,3 ), new Location( 7 ,10 ));
		o.addChest(new Location( 8 ,0 ), new Location( 8 ,1 ), new Item(Item.TYPE_MIRROR));
		//TH
		o.addKeyBlock(KeyDoor.SYMBOL,new Location( 8 ,3 ), new Location( 8 ,2 ));
		o.addKeyBlock(KeyDoor.SYMBOL,new Location( 8 ,3 ), new Location( 8 ,3 ));
		o.addKeyBlock(KeyDoor.SYMBOL,new Location( 8 ,3 ), new Location( 8 ,4 ));
		o.addKeyBlock(KeyDoor.SYMBOL,new Location( 8 ,3 ), new Location( 8 ,5 ));
		o.addKeyBlock(KeyDoor.SYMBOL,new Location( 8 ,3 ), new Location( 8 ,6 ));
		o.addChest(new Location( 8 ,3 ), new Location( 8 ,1 ), new Item(Item.TYPE_ARMOR));
	}
	public static void loadCaveObs(ObstacleAdder OA, World w)
	{
		OA.addIceCrack(new Location( 1 ,2 ), new Location( 7 ,2 ));
		OA.addIceBlock(new Location( 1 ,2 ), new Location( 6 ,9 ));
		OA.addIceBlock(new Location( 1 ,2 ), new Location( 2 ,2 ));
		OA.addIceCrack(new Location( 1 ,3 ), new Location( 12 ,0 ));
		OA.addIceBlock(new Location( 1 ,3 ), new Location( 4 ,4 ));
		OA.addIceCrack(new Location( 2 ,4 ), new Location( 12 ,0 ));
		OA.addIceBlock(new Location( 2 ,4 ), new Location( 3 ,2 ));
		OA.addIceBlock(new Location( 2 ,4 ), new Location( 8 ,7 ));
		OA.addIceBlock(new Location( 2 ,3 ), new Location( 3 ,8 ));
		OA.addIceBlock(new Location( 2 ,3 ), new Location( 4 ,3 ));
		OA.addIceBlock(new Location( 2 ,3 ), new Location( 13 ,2 ));
		OA.addIceCrack(new Location( 2 ,3 ), new Location( 3 ,0 ));
		OA.addIceCrack(new Location( 2 ,2 ), new Location( 13 ,3 ));
		OA.addIceBlock(new Location( 2 ,2 ), new Location( 7 ,4 ));
		OA.addIceBlock(new Location( 2 ,2 ), new Location( 9 ,4 ));
		OA.addIceBlock(new Location( 2 ,2 ), new Location( 10 ,9 ));
		OA.addIceBlock(new Location( 2 ,2 ), new Location( 12 ,9 ));
		OA.addIceCrack(new Location( 2 ,2 ), new Location( 3 ,2 ));
		OA.addChest(new Location( 1 ,2 ), new Location( 7 ,1 ), new Item(Item.TYPE_HEART_PIECE));
		OA.addChest(new Location( 2 ,2 ), new Location( 13 ,1 ), new Item(Item.TYPE_HEART_PIECE));
		OA.addChest(new Location( 2 ,2 ), new Location( 12 ,1 ), new Item(Item.TYPE_LOVI,50));
		OA.addChest(new Location( 2 ,2 ), new Location( 14 ,1 ), new Item(Item.TYPE_LOVI,50));
		OA.addChest(new Location( 5 ,6 ), new Location( 12 ,1 ), new Key(Key.SQUARE));
		OA.addChest(new Location( 4 ,6 ), new Location( 12 ,4 ), new Key(Key.CIRCLE));
		OA.addChest(new Location( 3 ,6 ), new Location( 8 ,1 ), new Key(Key.TRIANGLE));

		new PersonAdder(w).addSign(new Location( 1 ,4 ), new Location( 6 ,1 ),"Easy Course");
		new PersonAdder(w).addSign(new Location( 1 ,4 ), new Location( 14 ,5 ),"Hard Course");

	}
	public static void loadNarniaObs(ObstacleAdder OA, World w)
	{
		OA.addChest(new Location( 2 ,1 ), new Location( 8 ,1 ), new Key(Key.CIRCLE));
		OA.addChest(new Location( 0 ,0 ), new Location( 8 ,2 ), new Key(Key.TRIANGLE));
		OA.addKeyBlock(KeyDoor.CIRCLE,new Location( 1 ,0 ), new Location( 13 ,6 ));
		OA.addKeyBlock(KeyDoor.TRIANGLE,new Location( 1 ,0 ), new Location( 14 ,6 ));
		OA.addDispenser(new Location( 2 ,0 ), new Location( 13 ,3 ));
		OA.addTrigger(4,new Location( 3 ,0 ), new Location( 1 ,9 ));
		OA.addChest(new Location(2,2), new Location(5,2), new Item(Item.TYPE_SWORD2));
	}

//###################################################################################################################################
//####################################  People  #####################################################################################
//#################################################################################################################################*/
	public static void loadPeople(World[] world)
	{
		PersonAdder PA = new PersonAdder(world[0]); //Overworld
		PersonAdder PA8 = new PersonAdder(world[8]); //Houses
		PersonAdder PA10 = new PersonAdder(world[10]);//HV
		loadJangbertPeople(PA, PA8);
		loadFieldPeople(PA);
		loadPierPeople(PA, PA8);
		loadForestPeople(PA, PA8);
		loadDesertPeople(PA, PA8);
		loadValleyPeople(PA10);
		loadSnowPeople(PA,PA8);
		loadOtherPeople(PA,PA8); //Swamp, Mountain, Island
		loadNarniaPeople(world[12]);
	}

	public static void loadJangbertPeople(PersonAdder p, PersonAdder p2)
	{
		p.addSign(new Location(1,14), new Location(3,1), "Don't forget to save - Simply press DOWN");
		p.addSign(new Location(1,14), new Location(4,1), "You can open your inventory by pressing UP.\n\nYou can find your map, quests, and equipment on this screen");
		p.addSign(new Location(1,14), new Location(5,1), "Don't forget to talk to people you meet - They may have a quest for you");
		p.addSign(new Location(1,14), new Location(6,1), "Any equipment you earn can be assigned to either LEFT or RIGHT for instant use");
		p.addSign(new Location(2,14), new Location(2,6), "< Apple Orchard");
		p.addSign(new Location(1,13), new Location(3,8), "Veril's House");
		p.addSign(new Location(1,12), new Location(8,3), "Map Shop");
		p.addSign(new Location(4,13), new Location(9,7), "^ Jangbert Village\n> West Beach");
		p.addSign(new Location(4,11), new Location(7,8), "Warning, this cave is scary and dangerous.  Do not send small children in here alone");
		p.addSign(new Location(2,13), new Location(13,5),"Gary's House");
		p.addSign(new Location( 0 ,11 ), new Location( 9 ,8 ),"How to use a sword:\n1) Equip the sword to RIGHT or LEFT using the inventory (UP)\n2)Press RIGHT or LEFT while facing an enemy to attack it\nTry it out on these cute innocent bunnies");

		ArrayList<String> L = new ArrayList<String>();
		//Steve
		L.add("Hello, my name is Steve!");
		L.add("I happen to be pacing back and forth for no reason.");
		L.add("There is a reason.");
		L.add("I'm showing off our nifty new movement system.");
		L.add("It's truely revolutionary!");
		L.add("This feature is only one of the amazing new things you'll find in Loviant!");
		L.add("Keep your eyes out for moving people like me!");
		p.addVillageMan(Actor.DIRECTION_DOWN, new Location(1,13), new Location(6,5), "Steve", Person.MOVEMENT_LINEAR, 5, L);
		L = new ArrayList<String>();
		//Kris
		L.add("Trying to get through here Veril?");
		L.add("You look ill-equipped for what's out there.");
		L.add("I've seen it: All of the beautiful lands the programmers have created.");
		L.add("Yet they've ravenged it, covered it in enemies and puzzles and traps.  Coded solely to make our live's more difficult.");
		L.add("You're going to need something more than your fists to fend off what's past this bridge.");
		p.addVillageMan(Actor.DIRECTION_LEFT, new Location(3,12), new Location(8,5), "Kris", Person.MOVEMENT_STILL, 0, L);
		L = new ArrayList<String>();
		//Benny
		L.add("I've always been an avid explorer, but since I've become older, I haven't been able to go out much.");
		L.add("I would be incredibly grateful if you could bring a map back to me when you've filled it out more.");
		L.add("I want to remember all the beautiful places I've been to throughout my life.");
		p2.addVillageMan(Actor.DIRECTION_DOWN, new Location(0,0), new Location(9,5), "Benny", Person.MOVEMENT_CIRCLE, 1, L);
		L = new ArrayList<String>();
		Finder.findPerson("Benny").giveQuest(new CartographerQuest());
		//Shopkeep
		L.add("Hello Veril!");
		L.add("Here to get a map?");
		Shop s = new Shop();
		s.add(new ShopItem(new Item(Item.SHOP_BASICMAP), 5, "Map", "Know the land", true));
		s.add(new ShopItem(new Item(Item.SHOP_EPICMAP), 30, "Detailed Map", "See the land", true));
		s.add(new ShopItem(new Item(Item.TYPE_HEART_SMALL), 5, "Small Heart", "Instant health benefits", false));
		s.add(new ShopItem(new Item(Item.TYPE_ARROW, 5), 10, "5 Arrows", "Pointy Projectiles", false));
		p2.addShopkeep(Actor.DIRECTION_DOWN, Person.TYPE_VILLAGER, true, new Location(0,0), new Location(5,3), s, "Kevin", L);
		L = new ArrayList<String>();
		//Katy
		L.add("Good morning Veril!");
		L.add("What, your sword?");
		L.add("No, I haven't seen it.");
		L.add("Actually, I think I saw Gary with it yesterday.");
		L.add("You should go ask him; He lives in the southeast corner of town.");
		L.add("But of course you knew that! You've lived here your whole life!");
		p.addVillageWoman(Actor.DIRECTION_DOWN, new Location(1,13), new Location(13,3), "Katy", Person.MOVEMENT_STILL, 0, L);
		//Fiona
		L = new ArrayList<String>();
		L.add("...");
		L.add("Oh!  Hi Veril.");
		L.add("You snuck up on me there.");
		L.add("I'm just gazing out on this beautiful pond.");
		L.add("It's so... serene.");
		L.add("...");
		L.add("Go away.");
		p.addVillageWoman(Person.DIRECTION_LEFT,new Location( 2 ,13 ), new Location( 3 ,1 ),"Fiona", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Chris
		L.add("Good afternoon Veril!");
		L.add("Isn't it such a beautiful day here in Jangbert Village?");
		L.add("It's the kind of day that everyone should enjoy.");
		L.add("Now run along and have fun!");
		p.addVillageMan(Person.DIRECTION_DOWN,new Location( 1 ,12 ), new Location( 7 ,6 ),"Chris", Person.MOVEMENT_LINEAR,3,L);
		L = new ArrayList<String>();
		//Lynn
		L.add("I love this apple tree.");
		L.add("It's one of the few still growing inside the town.");
		L.add("All the rest of them are in the orchard just south of here.");
		L.add("You should go there sometime; It's very relaxing.");
		L.add("There should be a sign near the end of the river that leads you there.");
		p.addVillageWoman(Person.DIRECTION_DOWN,new Location( 2 ,12 ), new Location( 5 ,5 ),"Lynn", Person.MOVEMENT_CIRCLE,2,L);
		L = new ArrayList<String>();
		//Jake
		L.add("Do you see it?  Over on the other side of this river?");
		L.add("I think it may be a Heart Piece!");
		L.add("...");
		L.add("You don't know what a Heart Piece is?");
		L.add("Basically, it's a magical gem that grants extra health to the first person to touch it.");
		L.add("I've always wanted to find one...");
		L.add("But even better would be a Full Heart!");
		L.add("... Really?");
		L.add("You don't know what a Full Heart is either...");
		L.add("*sigh*");
		L.add("A Full Heart acts the same as a Heart Piece but is three times as powerful.");
		L.add("It is said to have a glowing white light surrounding it.");
		L.add("Yet the only places to find them are in the hands of the programmers.");
		L.add("...");
		L.add("You don't know who the programmers are?");
		L.add("Go ask someone else.");
		p.addVillageMan(Person.DIRECTION_RIGHT,new Location( 2 ,14 ), new Location( 8 ,6 ),"Jake", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Gary
		L.add("Hello Veril.");
		L.add("Nice of you to drop by.");
		L.add("At my magnificently large house!");
		L.add("Compared to your shack, my house is Loviant Castle!");
		L.add("HAHAHA!");
		L.add("You look so angry - if only you had a weapon to use on me!");
		L.add("But wait.  You don't have your sword, do you?");
		L.add("HAHAHA!");
		L.add("You'll never find it either!");
		L.add("I've hidden it far away, in a desolate place where no one will ever find it!");
		L.add("Specifically, that clearing after the apple orchard!");
		L.add("...");
		L.add("Damn.");
		p2.addGary(Person.DIRECTION_DOWN,new Location( 1 ,1 ), new Location( 11 ,7 ),"Gary", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Village Elder
		L.add("You look like you have a long road ahead of you.");
		L.add("The kind of road many would turn around on.");
		L.add("Good luck, young man.");
		p2.addOldMan(Person.DIRECTION_DOWN,new Location( 1 ,0 ), new Location( 9 ,4 ),"Village Elder", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Rebecca
		L.add("Psst. Veril.");
		L.add("I learned something cool just now.");
		L.add("Apparently, Gary keeps all of his money in a drawer in his house.");
		L.add("Anyone could just walk in and take all of his money.");
		L.add("  ");
		L.add("I hope you don't use that knowledge for evil.");
		p.addVillageWoman(Person.DIRECTION_DOWN,new Location( 3 ,13 ), new Location( 2 ,3 ),"Rebecca", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Mickaal
		L.add("Do you ever wonder why there's more people living in this village than there are houses and beds?");
		L.add("Me neither.");
		p.addVillageMan(Person.DIRECTION_DOWN,new Location( 2 ,11 ), new Location( 5 ,4 ),"Mickaal", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Zach
		L.add("I haven't seen you out here recently.");
		L.add("Your sword?");
		L.add("I think I might have seen something shiny just north of here.");
		L.add("You should go check it out - and try not to get lost!");
		p.addVillageMan(Person.DIRECTION_DOWN,new Location( 0 ,14 ), new Location( 6 ,3 ),"Zach", Person.MOVEMENT_CIRCLE,4,L);
		//Gary's Sister
		L = new ArrayList<String>();
		L.add("Oh hey Veril, you look lost!");
		L.add("Did you know that you can buy a map?");
		L.add("There's a Map Shop in the northwest corner of the town!");
		L.add("He sells two different kinds of maps.");
		L.add("The Basic Map is dirt cheap, but at least you get a general idea of where you are.");
		L.add("However, the Detailed Map was drawn by an ancient cartographer and is accurate to every tree, rock, and flower.");
		L.add("Once you buy one, your map will appear in your inventory.");
		L.add("Which you can open at any time by pressing UP.");
		L.add("Remember that: Inventory = UP.");
		p2.addVillageWoman(Person.DIRECTION_DOWN,new Location( 1 ,1 ), new Location( 7 ,2 ),"Gary's Sister", Person.MOVEMENT_STILL,0,L);
	}
	public static void loadFieldPeople(PersonAdder p)
	{
		p.addSign(new Location( 6 ,10), new Location( 8 ,5 ),"^ Padady Desert\n< Forest\n> Pierport");
		p.addSign(new Location( 8 ,7 ), new Location( 3 ,3 ),"Padady Desert");
		p.addSign(new Location( 5 ,7 ), new Location( 5 ,5 ),"PRIVATE PROPERTY\nNO TRESSPASSING");
		p.addSign(new Location( 5 ,7 ), new Location( 7 ,5 ),"PRIVATE PROPERTY\nNO TRESSPASSING");
		p.addSign(new Location( 5 ,7 ), new Location( 2 ,5 ),"PRIVATE PROPERTY\nNO TRESSPASSING");
		p.addSign(new Location( 5 ,7 ), new Location( 3 ,7 ),"PRIVATE PROPERTY\nNO TRESSPASSING");
		p.addSign(new Location( 5 ,7 ), new Location( 10,5 ),"PRIVATE PROPERTY\nNO TRESSPASSING");
		p.addSign(new Location( 5 ,7 ), new Location( 12,7 ),"PRIVATE PROPERTY\nNO TRESSPASSING");
		p.addSign(new Location( 5 ,7 ), new Location( 7 ,8 ),"PRIVATE PROPERTY\nNO TRESSPASSING");
		p.addSign(new Location( 5 ,7 ), new Location( 9 ,7 ),"PRIVATE PROPERTY\nNO TRESSPASSING");
		p.addSign(new Location( 5 ,7 ), new Location( 4 ,9 ),"PRIVATE PROPERTY\nNO TRESSPASSING");
		p.addSign(new Location( 11 ,10 ), new Location( 7 ,3 ),"^ Mountains\n < Pierport\n > Nolocimes Swamp");
		p.addSign(new Location( 14 ,14 ), new Location( 9 ,7 ),"Treasure Hunters Guild");

		ArrayList<String> L = new ArrayList<String>();
		//Phil on Island (NEEDS QUEST)
		L.add("Thank Heavens!");
		L.add("Someone's finally come to rescue me!");
		L.add("...");
		L.add("You swam here?");
		L.add("THEN HOW WILL I GET TO SHORE YOU TWAT!!!");
		L.add("GO FIND A WAY TO GET ME TO SHORE!");
		p.addDesertMan(Person.DIRECTION_DOWN,new Location( 5 ,14 ), new Location( 10 ,6 ),"Phil", Person.MOVEMENT_CIRCLE,1,L);
		L = new ArrayList<String>();
		//William the Fisherman
		L.add("Why hello there Sonny!");
		L.add("This is my favorite fishing spot.");
		L.add("It's nice and serene.");
		L.add("And there's no enemies in this area!");
		p.addTownMan(Person.DIRECTION_DOWN,new Location( 6 ,9 ), new Location( 2 ,2 ),"William", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Guard 1
		L.add("Welcome traveler.");
		L.add("This town is called Pierport.");
		L.add("Pierport is the largest town in all of Loviant Kingdom.");
		L.add("It is also the trading hub for all the land.");
		p.addGuard(Person.DIRECTION_LEFT,new Location( 7 ,10 ), new Location( 5 ,4 ),"Guard", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Guard 2
		L.add("I used to be an adventurer like you...");
		L.add("but then I decided to be a guard instead.");
		p.addGuard(Person.DIRECTION_LEFT,new Location( 7 ,10 ), new Location( 5 ,8 ),"Guard", Person.MOVEMENT_STILL,0,L);
	}
	public static void loadForestPeople(PersonAdder p, PersonAdder p2)
	{
		p.addSign(new Location( 3 ,5 ), new Location( 11 ,1 ),"Theevz Den\n\nSTAY OUT!");
		ArrayList<String> L = new ArrayList<String>();
		//Hank
		L.add("A visitor?");
		L.add("We hardly see people out here in the woods.");
		L.add("For some reason people always seem to get lost coming out here.");
		L.add("But anyways, welcome to Pinewatch Hallow!");
		p.addForestMan(Person.DIRECTION_DOWN,new Location( 0 ,7 ), new Location( 5 ,7 ),"Hank", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Veronica
		L.add("Our village has been suffering.");
		L.add("Months ago, the village was almost four times as large.");
		L.add("We even had a king: King Harry!");
		L.add("But then...");
		L.add("King Harry befriended a magician, one said to have spoken with the Programmer himself!");
		L.add("And then Harry changed.");
		L.add("The magician left, heading towards Loviant Castle.");
		L.add("But Harry became obsessed with the insatiable desire to beat things with sticks.");
		L.add("He knocked over more than thirty houses in a single night, testing every log's beating potential.");
		L.add("Yet none of them were good enough, and Harry fled deep into the forest.");
		L.add("He's last been seen in the Dark Grove, a section of the forest perpetually cloaked in night.");
		L.add("You can go looking for him, but be wary.");
		L.add("Beyond our village, you can't even trust the trees.");
		p2.addForestWoman(Person.DIRECTION_DOWN,new Location( 0 ,2 ), new Location( 9 ,4 ),"Veronica", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		//Kevin
		L.add("Do you believe me?");
		L.add(" ");
		L.add(" ");
		L.add("You do!");
		L.add("Everyone here thinks I'm crazy!");
		L.add("I'M NOT CRAZY!!!");
		L.add(" ");
		L.add("Now you're looking at me funny too!");
		L.add("Why will no one believe me?");
		L.add("It's the trees!");
		L.add("I was out the other day when I saw it...");
		L.add("A moving tree!");
		L.add("Not just swaying in the wind, this tree was walking around!");
		p2.addVillageMan(Person.DIRECTION_RIGHT,new Location( 1 ,2 ), new Location( 6 ,6 ),"Kevin", Person.MOVEMENT_CIRCLE,3,L);

	}
	public static void loadPierPeople(PersonAdder p, PersonAdder p2)
	{
		ArrayList<String> L = new ArrayList<String>();

		/////////////SHOPS/////////////////

		Shop s = new Shop();
		//Arrow Shop
		L.add("Well, looks like you've got yourself a Quiver and Bow.");
		L.add("That's good, 'cause everything I sell is for the fine sport of archery!");
		s.add(new ShopItem(new Item(Item.SHOP_QUIVEREXT), 50, "Quiver Extension", "Hold more Arrows!", true));
		s.add(new ShopItem(new Item(Item.SHOP_MAGICQUIVER), 150, "Magic Quiver", "Hold a lot more Arrows!", true));
		s.add(new ShopItem(new Item(Item.SHOP_IRONARROWS), 50, "Iron Arrows", "Pack more punch", true));
		s.add(new ShopItem(new Item(Item.SHOP_FIREARROWS), 100, "Fire Arrows", "Ignite your foes", true));
		s.add(new ShopItem(new Item(Item.TYPE_ARROW, 20), 25, "20 Arrows", "Sharp and Aerodynamic", false));
		p2.addShopkeep(Actor.DIRECTION_DOWN, Person.TYPE_TOWN, true, new Location(3,0), new Location(7,5), s, "Archie", L);
		L = new ArrayList<String>();
		//Gear Shop
		L.add("You're not here to rob me, are you?");
		L.add("...You're not?");
		L.add("...You want to buy something?");
		L.add("Phew.");
		L.add("A few days ago I was robbed by a group of thieves.");
		L.add("They stole all of the merchandise they could get their hands on.");
		L.add("I normally have tons of cool, unique items that do amazing things with epic animations and sound...");
		L.add("But unfortunately, this is all I have now:");
		s = new Shop();
		s.add(new ShopItem(new Item(Item.SHOP_OIL), 75, "Oil", "Highly Flammable", true));
		s.add(new ShopItem(new Item(Item.TYPE_ARROW, 10), 20, "10 Arrows", "Don't miss", false));
		p2.addShopkeep(Actor.DIRECTION_DOWN, Person.TYPE_TOWN, true, new Location(3,2), new Location(8,3), s, "Smith", L);
		L = new ArrayList<String>();
		Finder.findPerson("Smith").giveQuest(new ShovelQuest());
		//Health Shop
		L.add("Ahhhh. You are here to try out Madame Cusaire's Non-Herbal Medicine, I presume?");
		L.add("Of course you are! My products are legendary!");
		L.add("One sip of my special brew and you'll feel better than you've ever felt before!");
		L.add("Or, if you're already in peak condition, you can sample some of my Hearts.");
		L.add("They're all freshly ripped out of the chests of dead monsters!");
		s = new Shop();
		s.add(new ShopItem(new Item(Item.TYPE_HEART_PIECE), 75, "Heart Piece", "Feel the love", true));
		s.add(new ShopItem(new Item(Item.SHOP_POTION), 30, "Potion", "Feel young again", false));
		s.add(new ShopItem(new Item(Item.TYPE_HEART_SMALL), 5, "Small Heart", "Feel a faint tingling", false));
		p2.addShopkeep(Actor.DIRECTION_DOWN, Person.TYPE_TOWN, false, new Location(4,2), new Location(12,2), s, "Madame Cusaire", L);
		L = new ArrayList<String>();
		//Island Tours
		L.add("Yar! Ye be wishin' ter go to sea?");
		L.add("I off'r tours er da dreaded Lost Island!");
		L.add("Unfortunately, me sail's been torn by the horn o' da scourge o' these seas: da Victory Manatee!");
		L.add("Until me gets a new sail, me tour price be steep.");
		s = new Shop();
		s.add(new ShopItem(new Item(Item.SHOP_LOSTISLANDTOUR), 999, "Lost Island Tour", "Visit a mysterious land", false));
		p.addShopkeep(Actor.DIRECTION_DOWN, Person.TYPE_TOWN, true, new Location(9,12), new Location(2,5), s, "Cap'n", L);
		Finder.findPerson("Cap'n").giveQuest(new SailQuest());

		/////////////QUESTS////////////////
		L = new ArrayList<String>();
		L.add("Okay, don't panic.");
		L.add("Don't panic...");
		L.add("AHHHHH!");
		L.add("...");
		L.add("..Oh, you snuck up on me there.");
		L.add("I can't sit still, I can't stop shaking.");
		L.add("Ghosts!");
		L.add("I saw ghosts with my own two eyes!");
		L.add("...");
		L.add("I was at my new house I just bought.");
		L.add("Its the large house out in Loviant field.");
		L.add("Anyways, I recently bought it for an amazing low price from the man who was living there before.");
		L.add("But as soon as I walked in, an eerie voice wispered through the house!");
		L.add("At first, I thought it was the wind, but then the lights dimmed and a pale ghost walked through the wall towards me!");
		L.add("I fled as fast as I could back here.");
		L.add("I can't go back to that house, and to make sure no one else could, I buried the gate key in Pierport Park.");
		p2.addTownWoman(Person.DIRECTION_RIGHT,new Location( 4 ,1 ), new Location( 3 ,4 ),"Lisa", Person.MOVEMENT_LINEAR,3,L);
		Finder.findPerson("Lisa").giveQuest(new HauntedHouseQuest());


		/////////////MISC//////////////////

		L = new ArrayList<String>();
		L.add("This is the Mayor's Office.");
		L.add("Only people with official business may enter.");
		p.addGuard(Person.DIRECTION_DOWN,new Location( 10 ,9 ), new Location( 11 ,5 ),"Guard", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("I'm sorry, but I can't let you pass through here.");
		L.add("This area is known as Nolocines Swamp.");
		L.add("Its earth is trecherous and bog-like.  Anyone without proper footware will get swallowed by the muck.");
		p.addGuard(Person.DIRECTION_LEFT,new Location( 10 ,10 ), new Location( 15 ,4 ),"Guard", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Sorry citizen, but this area is off-limits.");
		p.addGuard(Person.DIRECTION_LEFT,new Location( 10 ,10 ), new Location( 15 ,5 ),"Guard", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("My boat!");
		L.add("Who could have done this to my boat?");
		p.addTownMan(Person.DIRECTION_DOWN,new Location( 9 ,10 ), new Location( 10 ,7 ),"Paul", Person.MOVEMENT_LINEAR,3,L);
		L = new ArrayList<String>();
		L.add("Oh, I love the fresh air and flowers here in the park.");
		L.add("I like to come here to escape the hustle and bustle of the port.");
		p.addTownWoman(Person.DIRECTION_DOWN,new Location( 8 ,9 ), new Location( 5 ,8 ),"Janet", Person.MOVEMENT_CIRCLE,2,L);
		L = new ArrayList<String>();
		L.add("Wazzup?");
		p.addGuard(Person.DIRECTION_UP,new Location( 9 ,8 ), new Location( 9 ,10 ),"Guard", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("It's my duty to keep Pierport safe from animals and bandits.");
		L.add("...");
		L.add("Stop distracting me.");
		p.addGuard(Person.DIRECTION_UP,new Location( 9 ,8 ), new Location( 5 ,10 ),"Guard", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("There used to be a bridge here.");
		L.add("It made walking around town so much easier.");
		L.add("But then we discovered that boats could no longer get through, so we tore it down.");
		L.add("It was definately worth it.");
		p.addTownWoman(Person.DIRECTION_RIGHT,new Location( 10 ,11 ), new Location( 6 ,2 ),"Wanda", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Have you been to Madame Cusaire's Non-Herbal Health Enhancers yet?");
		L.add("I don't know how, but she always seems to have a remedy for anything that gets me down.");
		p.addTownMan(Person.DIRECTION_DOWN,new Location( 10 ,11 ), new Location( 10 ,6 ),"Lance", Person.MOVEMENT_CIRCLE,3,L);
		L = new ArrayList<String>();
		L.add("There's always something going on here in Pierport!");
		p.addTownWoman(Person.DIRECTION_UP,new Location( 9 ,10 ), new Location( 6 ,8 ),"Karen", Person.MOVEMENT_CIRCLE,4,L);
		L = new ArrayList<String>();
		L.add("Who do we want?");
		L.add("THE MAYOR!");
		L.add("When do we want him?");
		L.add("NOW!");
		p.addTownMan(Person.DIRECTION_LEFT,new Location( 10 ,9 ), new Location( 12 ,8 ),"Bob", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("There have been more bandit sightings now than ever before.");
		L.add("Yet the mayor refuses to leave his office, keeping a guard outside twenty-four hours a day.");
		p.addTownWoman(Person.DIRECTION_RIGHT,new Location( 10 ,9 ), new Location( 6 ,9 ),"Michelle", Person.MOVEMENT_LINEAR,3,L);
		L = new ArrayList<String>();
		L.add("I used to run a bait shop for all of the fishermen, but since these bandits have shown up, none of my shipments have arrived!");
		L.add("The mayor needs to do something - and fast!");
		p.addTownMan(Person.DIRECTION_UP,new Location( 10 ,9 ), new Location( 11 ,10 ),"Jon", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Nothing beats the salty air o' the sea, me boy!");
		p.addTownMan(Person.DIRECTION_RIGHT,new Location( 9 ,12 ), new Location( 8 ,5 ),"Skipper", Person.MOVEMENT_LINEAR,5,L);
		L = new ArrayList<String>();
		L.add("Let's see..");
		L.add("Rope, check!");
		L.add("Boat, check!");
		L.add("Sailors, check!");
		L.add("Rum...");
		L.add("We can't go to sea without rum!");
		p.addTownWoman(Person.DIRECTION_LEFT,new Location( 9 ,12 ), new Location( 12 ,8 ),"Lila", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("It's a shame what happened to the Gear Shop.");
		L.add("If you haven't heard, a local gang of thieves stole his goods.");
		L.add("Last I've heard, they've been hiding somewhere in the forest.");
		p.addTownWoman(Person.DIRECTION_LEFT,new Location( 9 ,11 ), new Location( 8 ,2 ),"Hilary", Person.MOVEMENT_LINEAR,5,L);
		L = new ArrayList<String>();
		L.add("Would you like to know more about the wonderful world of gardening?");
		L.add("I can tell by your reluctant expression that you do!");
		L.add("I am a professional gardener.");
		L.add("Ususally, people need me when there's a Bubu Bush they can't get rid of.");
		L.add("Most people think I use advanced tools to remove them, when actually I just set them on fire.");
		L.add("One small blaze and they're gone!");
		L.add("Most people don't know this, but the origin of the Bubu Bush's name is quite profound.");
		L.add("The scientist who discovered its flammable properties wanted to call it a \"Burn me! Burn me!\" Bush, but everyone thought he was high.");
		L.add("So we shortened it to Bubu.");
		L.add("Gardening is the job of kings!");
		p.addTownWoman(Person.DIRECTION_RIGHT,new Location( 8 ,11 ), new Location( 6 ,9 ),"Sarah", Person.MOVEMENT_LINEAR,6,L);
		L = new ArrayList<String>();
		L.add("I'm so envious of the McRichpockets.  They're the only one's in town who can afford to have a yard.");
		p.addTownMan(Person.DIRECTION_UP,new Location( 8 ,10 ), new Location( 13 ,3 ),"Kole", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Ring around the rosie!");
		p.addTownMan(Person.DIRECTION_DOWN,new Location( 9 ,11 ), new Location( 10 ,4 ),"Chad", Person.MOVEMENT_CIRCLE,2,L);
		L = new ArrayList<String>();
		L.add("Pockets full of posie!");
		p.addTownWoman(Person.DIRECTION_RIGHT,new Location( 9 ,11 ), new Location( 10 ,6 ),"Chadrica", Person.MOVEMENT_CIRCLE,2,L);
		L = new ArrayList<String>();
		L.add("Ashes! Ashes!");
		p.addTownMan(Person.DIRECTION_UP,new Location( 9 ,11 ), new Location( 12 ,6 ),"Chadd", Person.MOVEMENT_CIRCLE,2,L);
		L = new ArrayList<String>();
		L.add("We all fall down!");
		p.addTownWoman(Person.DIRECTION_LEFT,new Location( 9 ,11 ), new Location( 12 ,4 ),"Chadette", Person.MOVEMENT_CIRCLE,2,L);
		L = new ArrayList<String>();
		L.add("Some thieves broke into my home!");
		L.add("They probably took my stuff back to their hideout in the northern forest.");
		p.addTownMan(Person.DIRECTION_DOWN,new Location( 10 ,10 ), new Location( 13 ,9 ),"Sal", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Tag!  You're it!");
		L.add("No tag backs!");
		p.addTownMan(Person.DIRECTION_RIGHT,new Location( 9 ,9 ), new Location( 10 ,8 ),"Vinny", Person.MOVEMENT_LINEAR,3,L);
		L = new ArrayList<String>();
		L.add("Oh good, you've got the proper footware.");
		L.add("Those sandals look light enough for you to simply hover over this ugly mudpit.");
		p.addGuard(Person.DIRECTION_RIGHT,new Location( 11 ,10 ), new Location( 2 ,2 ),"Guard", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Try not to get lost out here.");
		L.add("We've built a few bridges, but some of them are very hard to find.");
		p.addGuard(Person.DIRECTION_RIGHT,new Location( 11 ,10 ), new Location( 2 ,7 ),"Guard", Person.MOVEMENT_STILL,0,L);
		p.addSign(new Location( 10 ,10 ), new Location( 7 ,6 ),"Archeology Society Headquarters");
		L = new ArrayList<String>();
		L.add("You new in town?");
		L.add("I'm Scot.");
		L.add("I run the main dock here in Pierport.");
		L.add("If you have any boat problems, come see me.");
		p2.addTownMan(Person.DIRECTION_RIGHT,new Location( 4 ,0 ), new Location( 6 ,5 ),"Scot", Person.MOVEMENT_LINEAR,4,L);
		L = new ArrayList<String>();
		L.add("What are you doing in my home?");
		L.add("Get out, you miscreant!");
		p2.addTownMan(Person.DIRECTION_DOWN,new Location( 2 ,0 ), new Location( 8 ,4 ),"Vladamire McRichpockets", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Daddy!  Another homeless bum is in my room!");
		L.add("And this one smells!");
		p2.addTownWoman(Person.DIRECTION_RIGHT,new Location( 2 ,0 ), new Location( 3 ,5 ),"Pinelope McRichpockets", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Welcome to the Archaeology Society Headquarters.");
		L.add("We travel far across the lands of Loviant to learn more about the ancient cultures that used to live here.");
		L.add("For instance, we recently discovered a buried town just east of Nolocines Swamp.");
		L.add("One noticable discovery was that their brick houses had gray roofs.");
		L.add("Gray roofs!");
		L.add("I don't know what they did without our wonderful blue roofs!");
		p2.addSnowMan(Person.DIRECTION_DOWN,new Location( 3 ,1 ), new Location( 3 ,7 ),"Indy", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("I am Error.");
		p2.addForestMan(Person.DIRECTION_DOWN,new Location( 2 ,1 ), new Location( 10 ,2 ),"Error", Person.MOVEMENT_STILL,0,L);;
		L = new ArrayList<String>();
		L.add("I'm planning a vacation to the northern beach town of Summerville.");
		L.add("I've heard that Summerville is home to some of the greatest resorts in the land.");
		L.add("If you ever find yourself in Summerville, don't stay at the Motel.");
		L.add("Apparently, the owner of the Motel is a jerk.");
		p2.addTownWoman(Person.DIRECTION_RIGHT,new Location( 2 ,1 ), new Location( 4 ,7 ),"Erin", Person.MOVEMENT_LINEAR,6,L);
		L = new ArrayList<String>();
		L.add("So, you're from Jangbert Village?");
		L.add("I used to be from there too.");
		L.add("Jangbert was too boring; I came to the city seeking fun and excitement.");
		L.add("...");
		L.add("...Yep...");
		L.add("...Sure is exciting here alone in my house...");
		p2.addTownMan(Person.DIRECTION_DOWN,new Location( 2 ,2 ), new Location( 5 ,7 ),"Ike", Person.MOVEMENT_STILL,0,L);
	}
	public static void loadDesertPeople(PersonAdder p, PersonAdder p2)
	{
		ArrayList<String> L = new ArrayList<String>();
		L.add("...?");
		L.add("A person?");
		L.add("No, I've been out here too long, it must be an apparition...");
		p.addDesertMan(Person.DIRECTION_RIGHT,new Location( 10 ,6 ), new Location( 8 ,5 ),"Issac", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Hungry...  So hungry...");
		L.add("You look so...");
		L.add("Edible...");
		p2.addDesertMan(Person.DIRECTION_DOWN,new Location( 0 ,3 ), new Location( 5 ,4 ),"Sid", Person.MOVEMENT_LINEAR,4,L);
		L = new ArrayList<String>();
		L.add("We will never leave this forsaken desert.");
		L.add("Oh, we've tried...");
		L.add("But the wizard who took our castle won't let us leave.");
		L.add("Whenever we try to go to Pierport or Summerville, we get lost and end up right back at these caves.");
		p2.addDesertWoman(Person.DIRECTION_LEFT,new Location( 1 ,3 ), new Location( 13 ,3 ),"Mindy", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Have you heard what has happened to these lands?");
		L.add("This endless desert was once part of Loviant field.");
		L.add("On a hill in the center, we had a castle.");
		L.add("Loviant Castle!  The greatest castle in all the lands!");
		L.add("But then, a man came from the forest with a message for the king.");
		L.add("He claimed he had spoken with the programmer, when in fact this was one of the programmer's minions.");
		L.add("This man used his magical powers to take over the castle...");
		L.add("which he then destroyed.");
		L.add("Ever since, these lands have become dry and arid, creating the Padady Desert.");
		p2.addDesertMan(Person.DIRECTION_DOWN,new Location( 2 ,3 ), new Location( 2 ,2 ),"Bob", Person.MOVEMENT_CIRCLE,1,L);
		L = new ArrayList<String>();
		L.add("It's dangerous to go alone!");
		L.add("Take thi-...");
		L.add("Oh, you already have a sword.");
		p2.addDesertMan(Person.DIRECTION_DOWN,new Location( 3 ,3 ), new Location( 7 ,4 ),"Benjamin", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("I'm too thirsty to stay inside.");
		L.add("But it's too hot out to reach the river.");
		p2.addDesertWoman(Person.DIRECTION_DOWN,new Location( 4 ,3 ), new Location( 11 ,3 ),"Lila", Person.MOVEMENT_STILL,0,L);
	}
	public static void loadSnowPeople(PersonAdder p, PersonAdder p2)
	{
		ArrayList<String> L = new ArrayList<String>();
		L.add("Do you see that cave?");
		L.add("It's never been there before!");
		L.add("Just before all of Summerville froze over I saw this cave rise out of the ground!");
		L.add("No one dares to go near it because of the chilling winds gushing out of it.");
		L.add("But I think that cave is the source of all this cold.");
		L.add("...");
		L.add("By the way, isn't it cool how we can see the cave through these trees?");
		p.addSnowMan(Person.DIRECTION_UP,new Location( 2 ,0 ), new Location( 11 ,10 ),"Patrick", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Wee!");
		L.add("I can't think of anything more fun than sliding on ice!");
		p.addSnowWoman(Person.DIRECTION_RIGHT,new Location( 4 ,0 ), new Location( 6 ,8 ),"Janet", Person.MOVEMENT_LINEAR,7,L);
		L = new ArrayList<String>();
		L.add("Welcome traveler!");
		L.add("This is the site of the archeology society's most recent expedition.");
		L.add("We recently discovered proof of the \"Great Veril 2 Flood\" on top of this mountain! ");
		L.add("Unfortunately, only members of the archeology society can visit the site. ");
		L.add("Please come back if you become a member.");
		p.addSnowMan(Person.DIRECTION_DOWN,new Location( 6 ,2 ), new Location( 10 ,9 ),"Zachary", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("This path used to lead to Summerville's massive beach resort.");
		L.add("But recent avalanches have covered the path in rocks.");
		L.add("It's too bad, before the cold wave this was the most beutiful area in all of Loviant.");
		p.addSnowMan(Person.DIRECTION_UP,new Location( 3 ,0 ), new Location( 3 ,2 ),"Ian", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("I just finished this snowman!");
		L.add("I love making snowmen, I do this all the time!");
		L.add("Someday, I hope one of them will wake up and talk to me, but first I need a magical hat.");
		p.addSnowWoman(Person.DIRECTION_RIGHT,new Location( 4 ,1 ), new Location( 3 ,4 ),"Alexandra", Person.MOVEMENT_STILL,0,L);
		Finder.findPerson("Alexandra").giveQuest(new NarniaQuest());

		p.addSign(new Location( 3 ,3 ), new Location( 7 ,4 ),"^ Summerville \n\n> Padady Desert");
		L = new ArrayList<String>();
		L.add("This is some of the worst weather we've ever had here in Summerville!");
		L.add("Say, where is your coat?");
		L.add("You must be freezing!");
		L.add("If you ever feel so cold you're nearly frozen, just remember to move to warm yourself back up.");
		p.addSnowMan(Person.DIRECTION_DOWN,new Location( 3 ,1 ), new Location( 10 ,7 ),"Quincy", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Buenas tardes senor!");
		L.add("Bienvenidos a mi casa mignifisimo!");
		L.add("Querias alguna cocina?");
		L.add("Que?");
		L.add("No puedes comprender nada?");
		L.add("Dios mio...");
		L.add("Nadie! Nadie nunca sabe casi un poco de espanol!");
		L.add("Va! Nunca quiero se ver!");
		p2.addSnowMan(Person.DIRECTION_DOWN,new Location( 5 ,1 ), new Location( 6 ,2 ),"Rodrigo", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Oh my!");
		L.add("You caught me completely unprepared!");
		L.add("I've weren't far all this strange weather I'd be completely prepared to help out random strangers that walk into my home.");
		p2.addSnowWoman(Person.DIRECTION_RIGHT,new Location( 5 ,0 ), new Location( 3 ,4 ),"Olivia", Person.MOVEMENT_LINEAR,8,L);
		L = new ArrayList<String>();
		L.add("Do you have a reservation?");
		L.add("You don't!");
		L.add("This is the best motel in all of Summerville and you didn't think to reserve a room!");
		L.add("Does it look like I've got any beds to spare to a nomad like you?");
		p2.addSnowMan(Person.DIRECTION_DOWN,new Location( 6 ,0 ), new Location( 10 ,3 ),"Luke", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Welcome to Summerville young man.");
		L.add("We're having a few weather problems right now, but normally this whole area is a gigantic summer beach resort.");
		L.add("In fact, the snow seemed to start just a few days ago and hasn't let up since.");
		L.add("It almost feels like the whole land has been cursed.");
		p2.addSnowWoman(Person.DIRECTION_DOWN,new Location( 6 ,1 ), new Location( 4 ,3 ),"Scarlett", Person.MOVEMENT_CIRCLE,2,L);
		L = new ArrayList<String>();
		L.add("You new here in Summerville?");
		L.add("No doubt you've heard about our little weather problems.");
		L.add("Not ten days ago it was a perfectly warm day to hang out on the beach.");
		L.add("But now everyone is taking shelter near their fires.");
		L.add("This weather can't possibly be natural.");
		L.add("Yet I guess I'll never be able to find out where this cold wind has come from.");
		p2.addSnowMan(Person.DIRECTION_LEFT,new Location( 5 ,3 ), new Location( 9 ,5 ),"Joseph", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		p2.addSnowman(Person.DIRECTION_DOWN,new Location(5,2), new Location(6,3),"Snowman", Person.MOVEMENT_STILL,0,L);
	}
	public static void loadValleyPeople(PersonAdder PA)
	{
		ArrayList<String> l = new ArrayList<String>();
		PA.addSign(new Location( 3 ,4 ), new Location( 3 ,4 ),"Loviant Castle ^\n\"Where Schwing is King!\"\n\nOfficial business only");
		PA.addSign(new Location( 3 ,1 ), new Location( 9 ,9 ),"Only servants of King Jake may enter the castle\n\nAll tresspassers will be killed");
		l = new ArrayList<String>();
		l.add("Oh my, a visitor!");
		l.add("We haven't had a visitor here for ages.");
		l.add("Where are you?");
		l.add("Why, you're here in Hidden Valley!");
		PA.addTownMan(Person.DIRECTION_DOWN,new Location( 0 ,3 ), new Location( 6 ,5 ),"Liam", Person.MOVEMENT_CIRCLE,3,l);
		l = new ArrayList<String>();
		l.add("You look new here sonny.");
		l.add("Welcome to Hidden Valley.");
		l.add("I'm Trent, and I run the one and only ranch you'll find down here.");
		l.add("So this is the one and only Hidden Valley Ranch!");
		PA.addForestMan(Person.DIRECTION_RIGHT,new Location( 4 ,2 ), new Location( 5 ,6 ),"Trent", Person.MOVEMENT_LINEAR,5,l);
		l = new ArrayList<String>();
		l.add("I love to sit here and watch the cattle.");
		l.add("They're so docile and peaceful.");
		l.add("...");
		l.add("Go away.");
		PA.addTownWoman(Person.DIRECTION_UP,new Location( 2 ,3 ), new Location( 7 ,10 ),"Irene", Person.MOVEMENT_STILL,0,l);
		l = new ArrayList<String>();
		l.add("Welcome to the ranch, stranger!");
		l.add("I'm Vera.");
		l.add("My husband Trent and I run the Hidden Valley Ranch.");
		l.add("We look after all the cattle here so we can have meat and milk.");
		l.add("Comically, though, these cattle are dumb enough to eat anything.");
		l.add("Quite often when a cow is slaughtered we'll find a missing Lovi.");
		l.add("...");
		l.add("Now that I think about it, that wasn't very comical...");
		PA.addForestWoman(Person.DIRECTION_DOWN,new Location( 2 ,2 ), new Location( 4 ,4 ),"Vera", Person.MOVEMENT_CIRCLE,3,l);
		l = new ArrayList<String>();
		l.add("Shh. Don't give me away.");
		l.add("I'm playing hide and seek with my friends.");
		PA.addVillageWoman(Person.DIRECTION_DOWN,new Location( 0 ,4 ), new Location( 7 ,5 ),"Brenda", Person.MOVEMENT_STILL,0,l);
		l = new ArrayList<String>();
		l.add("Have you seen any other kids hiding?");
		l.add("I've been looking for hours and I can't find them!");
		PA.addVillageMan(Person.DIRECTION_DOWN,new Location( 1 ,4 ), new Location( 5 ,1 ),"Mike", Person.MOVEMENT_CIRCLE,4,l);
		l = new ArrayList<String>();
		l.add("Shh. Go away.");
		l.add("I'm trying to hide!");
		PA.addTownMan(Person.DIRECTION_RIGHT,new Location( 2 ,4 ), new Location( 10 ,1 ),"Fred", Person.MOVEMENT_STILL,0,l);
		l = new ArrayList<String>();
		l.add("AM I THE ONLY ONE HERE WHO ISN'T INSANE!!!");
		l.add("AM I???");
		l.add("...");
		l.add("...");
		l.add("Oh god, now I'm seeing people.");
		l.add("Who are you, apparition?");
		l.add("...");
		l.add("You're not a ghost?");
		l.add("Thank heavens! Someone's finally come from the surface!");
		l.add("This valley is completely underground, yet no one here seems to notice.");
		l.add("The sky's always shining with an artificial light, and we're surrounded by great walls of stone.");
		l.add("But I'm the only one who remembers this.");
		l.add("...");
		l.add("How?  The real question is \"who?\"");
		l.add("It was the wizard!  He goes by the name of Jake.");
		l.add("He uses magical songs to bewitch people into evil!");
		l.add("Jake came to Loviant Castle from the forest, enchanting every soldier in his path.");
		l.add("Then, the whole valley began to sink.");
		l.add("And the sky closed up with stone, leaving the surface a barren desert.");
		l.add("And I'm the only one that remembers...");
		l.add("If only the wizard were killed!  Then hopefully everything will return to normal.");
		PA.addTownWoman(Person.DIRECTION_DOWN,new Location( 4 ,3 ), new Location( 5 ,6 ),"Monique", Person.MOVEMENT_STILL,0,l);
		l = new ArrayList<String>();
		l.add("Have you seen my children anywhere?");
		l.add("They're so find of playing games it's sometimes hard to keep up with them.");
		l.add("At least they can't go to far, the great walls will stop them.");
		PA.addTownMan(Person.DIRECTION_DOWN,new Location( 4 ,4 ), new Location( 10 ,2 ),"Travis", Person.MOVEMENT_STILL,0,l);
		l = new ArrayList<String>();
		l.add("Have you met Monique?");
		l.add("What a riot!");
		l.add("Always babbling about evil music and \"The Surface.\"");
		l.add("I'm suprised our wonderful King Jake the Magnificant hasn't done anything about her yet.");
		PA.addTownWoman(Person.DIRECTION_DOWN,new Location( 1 ,1 ), new Location( 13 ,5 ),"Karen", Person.MOVEMENT_LINEAR,3,l);

	}
	public static void loadOtherPeople(PersonAdder p, PersonAdder p2)
	{
		ArrayList<String> L = new ArrayList<String>();
		L.add("Welcome to the magic and mysterious \"Lost Island.\"");
		L.add("Once home to exotic wildlife, this island now houses some of the most unique trees in all of Loviant.");
		L.add("I think...");
		L.add("Anyways, if you look to the southwest, you can see some palm trees.");
		L.add("They're exactly the same as the ones back on the mainland.");
		L.add("Whopdeedo...");
		L.add("Also, behind me you can see water.");
		L.add("Just like anywhere else.");
		L.add("I really have no idea why we even advertise this as a tour.");
		L.add("I'm not going anywhere near the island jungle because of all the animals thaat could attack me.");
		L.add("...");
		L.add("By the way, the return price for the mainlnd is 7,624 Lovis.");
		L.add("I hope you enjoy your time on the island!");
		p.addTownMan(Person.DIRECTION_DOWN,new Location( 6 ,14 ), new Location( 12 ,5 ),"Tour Guide", Person.MOVEMENT_STILL,0,L);
		L = new ArrayList<String>();
		L.add("Alo'! I be the mountain hermit!");
		L.add("I've lived up here on the mountain for the last fifty years.");
		L.add("I know every nook and cranny of these mountains like the back of my hand.");
		L.add("...You seek the rogue coder?");
		L.add("I know who he is.");
		L.add("He goes by many names: The Rogue Coder, The Flaming Man, Firepubes...");
		L.add("But his true name is Kelch.");
		L.add("He lives in the Eastern Twinpeak Volcano, on an island in a desolate sea of lava and brimstone.");
		L.add("But be warned, he's been punished by the programmer for trying to usurp his power.");
		L.add("The programmer took his soul.");
		L.add("He may not be willing to help you, now that he's a flaming demon!");
		p2.addDesertMan(Person.DIRECTION_DOWN,new Location( 6 ,3 ), new Location( 7 ,4 ),"Hermit", Person.MOVEMENT_STILL,0,L);
                L = new ArrayList();
                L.add("Wow, we haven't had a visitor here in ages.");
                L.add("Only the truely adventurous seem to visit Treasure Island.");
                p2.addVillageMan(Person.DIRECTION_DOWN,new Location( 8 ,2 ), new Location( 11 ,6 ),"Oliver", Person.MOVEMENT_STILL,0,L);
                L = new ArrayList();
                L.add("I just got back from the jungles of Alazaria.");
                L.add("My party and I found an ancient temple that was full of treasure.");
                L.add("Unfortunately, we also triggered a trap that shot arrows at us from every direction.");
                L.add("I was the only one to survive...");
                L.add("But I did get this sweet grill made of pure gold!");
                p2.addDesertMan(Person.DIRECTION_RIGHT,new Location( 8 ,2 ), new Location( 2 ,8 ),"Wenston", Person.MOVEMENT_LINEAR,2,L);
                L = new ArrayList();
                L.add("Welcome fellow Treasure Hunter!");
                L.add("This is the Treasure Hunter's Guild.");
                L.add("We're an organization dedicated to helping adventurers like you find shiny treasure.");
                L.add("We used to be based in Pierport, but we moved to Treasure Island because...");
                L.add("..of what's downstairs...");
                L.add("...");
                L.add("... the greatest treasure in all of Loviant.");
                p2.addTownMan(Person.DIRECTION_DOWN,new Location( 7 ,2 ), new Location( 7 ,6 ),"Vernan", Person.MOVEMENT_CIRCLE,3,L);
                p2.addSign(new Location( 7 ,3 ), new Location( 14 ,6 ),"Treasure Room");
                p2.addSign(new Location( 8 ,3 ), new Location( 4 ,2 ),"1: Across the land, the treasures lie");
                p2.addSign(new Location( 8 ,3 ), new Location( 13 ,8 ),"2: Beneath the dirt, hidden from eye");
                p2.addSign(new Location( 8 ,3 ), new Location( 4 ,8 ),"3: Find the symbol across the land");
                p2.addSign(new Location( 8 ,3 ), new Location( 13 ,2 ),"4: And find the keys, shovel in hand");

	}
	public static void loadNarniaPeople(World w)
	{
		ArrayList<String> l = new ArrayList<String>();
		PersonAdder PA = new PersonAdder(w);
		l.add("What 'cho lookin' at?");
		l.add("Yea, I'm a talkin' badger!");
		l.add("You think it's wierd?");
		l.add("Animals talk all the time here in Varnia.");
		l.add("...");
		l.add("You've never heard of Varnia before?");
		l.add("It's a wonderful land normally filled with happiness.");
		l.add("Except now the White Witch rules the land from her crystal palace.");
		l.add("If you're planning on going there, be prepared.");
		l.add("It's a long journey.");
		PA.addHB(Person.DIRECTION_DOWN,new Location( 0 ,3 ), new Location( 3 ,8 ),"Honey the Badger", Person.MOVEMENT_STILL,0,l);
		PA.addSign(new Location( 1 ,2 ), new Location( 8 ,5 ),"The White Witch's Crystal Palace\n\nNo Solicitors");
		PA.addSign(new Location( 3 ,2 ), new Location( 8 ,5 ),"What do you know! The sign says something different now! Good for you, you curious son of a bitch!");
		l = new ArrayList<String>();
		l.add("Look at you, mister big-shot hero of Varnia.");
		l.add("Honey the Badger don't give a shit!");
		PA.addHB(Person.DIRECTION_DOWN,new Location( 2 ,3 ), new Location( 3 ,8 ),"Honey the Badger", Person.MOVEMENT_STILL,0,l);
		l = new ArrayList<String>();
		l.add("Good job, my child.");
		l.add("I am Vaslan, the true king of Varnia.");
		l.add("For your courage in facing and defeating the White Witch, I grant you my sword.");
		l.add("May it serve you well.");
		l.add("If you wish to return to your world, simply walk through the Vardrobe.");
		PA.addLion(Person.DIRECTION_DOWN,new Location( 2 ,2 ), new Location( 7 ,3 ),"Vaslan", Person.MOVEMENT_STILL,0,l);

	}

//###################################################################################################################################
//####################################  Enemies  ####################################################################################
//#################################################################################################################################*/
	public static void loadEnemies(World[] world)
	{
		EnemyAdder EA = new EnemyAdder(world[0]);
		loadVillageEnemies(EA);
		loadBeachEnemies(EA);
		loadFieldEnemies(EA);
		loadForestEnemies(EA);
		loadDesertEnemies(EA);
		loadHVEnemies(new EnemyAdder(world[10]));
		loadSnowEnemies(EA);
		loadSwampEnemies(EA);
		loadMountainEnemies(EA);
		loadIslandEnemies(EA);

		loadTemple1(world[1]);
		loadTemple2(world[2]);
		loadTemple3(world[3]);
		loadTemple4(world[4]);
		loadTemple5(world[5]);
		loadTemple6(world[6]);
		loadTemple7(world[7]);

		loadHHEnemies(world[8]);
		loadCaveEnemies(new EnemyAdder(world[9]));
		loadNarniaEnemies(world[12]);
	}

	public static void loadVillageEnemies(EnemyAdder e)
	{
		e.addSheep(2,new Location(1,11));
		e.addSheep(1,new Location(2,11));
		e.addSheep(1,new Location(3,11));
		e.addSheep(2,new Location(3,13));
		e.addSheep(1,new Location(3,14));
		e.addRaccoon(1,new Location(4,12));
		e.addRaccoon(1,new Location(4,12));
		e.addGoblin(1,new Location(4,12));
		e.addRabbit(1,new Location( 0 ,11 ));
		e.addRabbit(2,new Location( 0 ,11 ));
		e.addRabbit(3,new Location( 0 ,11 ));
		e.addRabbit(4,new Location( 0 ,11 ));
		e.addRabbit(5,new Location( 0 ,11 ));
		e.addRabbit(6,new Location( 0 ,11 ));
	}
	public static void loadBeachEnemies(EnemyAdder e)
	{
		e.addGoblin(3,new Location(5,13));
		e.addCrab(2,new Location(5,13));
		e.addCrab(3,new Location(5,12));
		e.addCrab(2,new Location(5,12));
		e.addCrab(4,new Location(5,12));
		e.addCrab(3,new Location(6,12));
		e.addCrab(4,new Location(6,12));
		e.addCrab(4,new Location(7,12));
		e.addCrab(5,new Location(7,12));
		e.addCrab(5,new Location(8,12));
		e.addCrab(30,new Location( 10 ,12 ));
	}
	public static void loadFieldEnemies(EnemyAdder e)
	{
		e.addRaccoon(3, new Location(6,11));
		e.addRaccoon(4, new Location(6,11));
		e.addGoblin(5, new Location(7,11));
		e.addBandit(3, new Location(5,10));
		e.addBandit(3, new Location(5,10));
		e.addBandit(3, new Location(4,10));
		e.addBandit(4, new Location(4,10));
		e.addMarksman(2, new Location(4,10));
		e.addBandit(3, new Location(4,9));
		e.addMarksman(3, new Location(4,9));
		e.addMarksman(3, new Location(4,8));
		e.addMarksman(3, new Location(4,8));
		e.addBear(2, new Location(7,8));
		e.addBear(3, new Location(7,9));
		e.addRaccoon(7,new Location( 6 ,10 ));
		e.addRaccoon(5,new Location( 5 ,9 ));
		e.addRaccoon(9,new Location( 5 ,9 ));
		e.addRaccoon(11,new Location( 5 ,9 ));
		e.addRaccoon(3,new Location( 5 ,9 ));
		e.addRaccoon(6,new Location( 5 ,9 ));
		e.addBandit(9,new Location( 5 ,8 ));
		e.addBandit(6,new Location( 5 ,8 ));
		e.addMarksman(13,new Location( 5 ,8 ));
		e.addMarksman(8,new Location( 5 ,8 ));
		e.addBear(9,new Location( 6 ,8 ));
		e.addRaccoon(10,new Location( 6 ,8 ));
		e.addRabbit(4,new Location( 6 ,8 ));
		e.addRabbit(3,new Location( 6 ,9 ));
		e.addRabbit(5,new Location( 6 ,9 ));
		e.addRabbit(5,new Location( 6 ,10 ));
		e.addBear(9,new Location( 8 ,8 ));
		e.addBear(8,new Location( 8 ,8 ));
		e.addBear(13,new Location( 7 ,8 ));
		e.addRaccoon(10,new Location( 6 ,7 ));
		e.addSheep(8,new Location( 6 ,7 ));
		e.addRaccoon(14,new Location( 6 ,7 ));
		e.addBandit(3,new Location( 4 ,7 ));
		e.addBandit(6,new Location( 4 ,7 ));
		e.addBandit(8,new Location( 4 ,7 ));
		e.addMarksman(6,new Location( 4 ,7 ));
		e.addMarksman(8,new Location( 4 ,7 ));
		e.addMarksman(13,new Location( 4 ,7 ));
		e.addBandit(16,new Location( 4 ,6 ));
		e.addBandit(13,new Location( 4 ,6 ));
		e.addMarksman(9,new Location( 4 ,6 ));
		e.addMarksman(11,new Location( 4 ,6 ));
		e.addMarksman(14,new Location( 4 ,6 ));
		e.addSheep(4,new Location( 8 ,7 ));
	}
	public static void loadForestEnemies(EnemyAdder e)
	{
		e.addBear(9,new Location( 3 ,10 ));
		e.addRaccoon(10,new Location( 3 ,10 ));
		e.addRaccoon(6,new Location( 2 ,10 ));
		e.addTreep(7,new Location( 2 ,10 ));
		e.addTreep(5,new Location( 2 ,10 ));
		e.addTreep(6,new Location( 2 ,10 ));
		e.addTreep(9,new Location( 2 ,10 ));
		e.addTreep(4,new Location( 2 ,10 ));
		e.addTreep(6,new Location( 2 ,10 ));
		e.addTreep(5,new Location( 1 ,10 ));
		e.addTreep(7,new Location( 1 ,10 ));
		e.addTreep(4,new Location( 1 ,10 ));
		e.addBear(8,new Location( 1 ,10 ));
		e.addRaccoon(3,new Location( 1 ,10 ));
		e.addRaccoon(5,new Location( 0 ,10 ));
		e.addRaccoon(6,new Location( 0 ,10 ));
		e.addBear(3,new Location( 0 ,10 ));
		e.addTreep(7,new Location( 0 ,10 ));
		e.addRaccoon(6,new Location( 0 ,9 ));
		e.addRaccoon(4,new Location( 0 ,9 ));
		e.addBear(4,new Location( 0 ,8 ));
		e.addBear(5,new Location( 0 ,8 ));
		e.addRaccoon(3,new Location( 0 ,8 ));
		e.addBear(6,new Location( 1 ,7 ));
		e.addBear(7,new Location( 1 ,7 ));
		e.addTreep(3,new Location( 1 ,6 ));
		e.addTreep(4,new Location( 1 ,6 ));
		e.addTreep(2,new Location( 1 ,6 ));
		e.addTreep(3,new Location( 1 ,6 ));
		e.addTreep(5,new Location( 2 ,6 ));
		e.addTreep(6,new Location( 2 ,6 ));
		e.addTreep(7,new Location( 2 ,6 ));
		e.addTreep(8,new Location( 2 ,6 ));
		e.addTreep(9,new Location( 2 ,6 ));
		e.addTreep(10,new Location( 2 ,6 ));
		e.addTreep(12,new Location( 2 ,6 ));
		e.addTreep(11,new Location( 2 ,6 ));
		e.addTreep(7,new Location( 0 ,6 ));
		e.addTreep(5,new Location( 0 ,6 ));
		e.addTreep(6,new Location( 0 ,6 ));
		e.addTreep(4,new Location( 0 ,6 ));
		e.addTreep(3,new Location( 0 ,6 ));
		e.addTreep(6,new Location( 0 ,5 ));
		e.addTreep(7,new Location( 1 ,5 ));
		e.addRaccoon(5,new Location( 0 ,4 ));
		e.addRaccoon(3,new Location( 0 ,4 ));
		e.addThief(4,new Location( 3 ,4 ));
		e.addThief(5,new Location( 3 ,4 ));
		e.addThief(7,new Location( 3 ,4 ));
		e.addThief(8,new Location( 3 ,4 ));
		e.addThief(6,new Location( 3 ,4 ));
		e.addThief(5,new Location( 4 ,4 ));
		e.addThief(6,new Location( 4 ,4 ));
		e.addThief(7,new Location( 4 ,4 ));
		e.addThief(8,new Location( 4 ,4 ));
		e.addThief(9,new Location( 4 ,4 ));
		e.addThief(10,new Location( 4 ,4 ));
		e.addThief(11,new Location( 4 ,4 ));
		e.addThief(6,new Location( 4 ,5 ));
		e.addThief(7,new Location( 4 ,5 ));
		e.addThief(8,new Location( 4 ,5 ));
		e.addThief(9,new Location( 4 ,5 ));
		e.addThief(10,new Location( 4 ,5 ));
		e.addThief(11,new Location( 4 ,5 ));
		e.addThief(12,new Location( 4 ,5 ));
		e.addThief(13,new Location( 4 ,5 ));
		e.addTreep(10,new Location( 3 ,5 ));
		e.addTreep(12,new Location( 2 ,5 ));
		e.addTreep(15,new Location( 2 ,5 ));
		e.addTreep(7,new Location( 2 ,5 ));

	}
	public static void loadDesertEnemies(EnemyAdder EA)
	{
		EA.addGoblin(7,new Location( 8 ,6 ));
		EA.addGoblin(12,new Location( 8 ,6 ));
		EA.addOrc(7,new Location( 7 ,6 ));
		EA.addOrc(10,new Location( 7 ,6 ));
		EA.addOrc(5,new Location( 7 ,6 ));
		EA.addGoblin(11,new Location( 6 ,6 ));
		EA.addOrc(5,new Location( 6 ,6 ));
		EA.addOrc(7,new Location( 6 ,6 ));
		EA.addOrc(10,new Location( 7 ,5 ));
		EA.addOrc(12,new Location( 7 ,5 ));
		EA.addGoblin(10,new Location( 5 ,4 ));
		EA.addGoblin(12,new Location( 5 ,4 ));
		EA.addGoblin(10,new Location( 6 ,3 ));
		EA.addScorpion(12,new Location( 6 ,3 ));
		EA.addScorpion(8,new Location( 6 ,3 ));
		EA.addScorpion(12,new Location( 7 ,4 ));
		EA.addScorpion(9,new Location( 7 ,4 ));
		EA.addScorpion(9,new Location( 7 ,3 ));
		EA.addScorpion(9,new Location( 7 ,3 ));
		EA.addScorpion(9,new Location( 7 ,3 ));
		EA.addScorpion(12,new Location( 8 ,5 ));
		EA.addScorpion(12,new Location( 8 ,5 ));
		EA.addScorpion(15,new Location( 9 ,6 ));
		EA.addScorpion(9,new Location( 9 ,6 ));
		EA.addScorpion(5,new Location( 9 ,6 ));
		EA.addScorpion(4,new Location( 9 ,5 ));
		EA.addScorpion(8,new Location( 9 ,5 ));
		EA.addScorpion(13,new Location( 9 ,5 ));
		EA.addOrc(12,new Location( 9 ,5 ));
		EA.addOrc(7,new Location( 9 ,5 ));
		EA.addScorpion(10,new Location( 10 ,5 ));
		EA.addGoblin(10,new Location( 10 ,5 ));
		EA.addGoblin(15,new Location( 10 ,5 ));
		EA.addScorpion(4,new Location( 10 ,4 ));
		EA.addScorpion(4,new Location( 10 ,4 ));
		EA.addScorpion(4,new Location( 10 ,4 ));
		EA.addScorpion(4,new Location( 10 ,4 ));
		EA.addOrc(6,new Location( 10 ,4 ));
		EA.addOrc(3,new Location( 10 ,4 ));
		EA.addOrc(4,new Location( 10 ,4 ));
		EA.addOrc(10,new Location( 10 ,3 ));
		EA.addOrc(14,new Location( 10 ,3 ));
		EA.addScorpion(13,new Location( 9 ,3 ));
		EA.addScorpion(6,new Location( 9 ,4 ));
		EA.addScorpion(7,new Location( 9 ,4 ));
		EA.addScorpion(9,new Location( 9 ,4 ));
		EA.addScorpion(11,new Location( 8 ,4 ));
		EA.addScorpion(13,new Location( 8 ,4 ));
		EA.addScorpion(17,new Location( 8 ,4 ));
		EA.addScorpion(15,new Location( 8 ,3 ));
		EA.addScorpion(17,new Location( 8 ,3 ));
		EA.addScorpion(13,new Location( 8 ,3 ));
		EA.addScorpion(10,new Location( 5 ,3 ));
	}
        public static void loadSnowEnemies(EnemyAdder EA)
        {
            EA.addPolarBear(8,new Location( 0 ,0 ));
            EA.addPolarBear(6,new Location( 0 ,0 ));
            EA.addPenguin(5,new Location( 0 ,0 ));
            EA.addPolarBear(5,new Location( 0 ,1 ));
            EA.addPolarBear(3,new Location( 0 ,1 ));
            EA.addPenguin(4,new Location( 0 ,1 ));
            EA.addPolarBear(9,new Location( 0 ,2 ));
            EA.addPenguin(6,new Location( 0 ,2 ));
            EA.addPolarBear(9,new Location( 0 ,3 ));
            EA.addPolarBear(7,new Location( 0 ,3 ));
            EA.addPenguin(5,new Location( 0 ,3 ));
            EA.addPolarBear(5,new Location( 1 ,3 ));
            EA.addPenguin(4,new Location( 2 ,1 ));
            EA.addPenguin(3,new Location( 2 ,1 ));
            EA.addPenguin(6,new Location( 2 ,1 ));
            EA.addPenguin(5,new Location( 2 ,2 ));
            EA.addPenguin(5,new Location( 2 ,2 ));
            EA.addPenguin(5,new Location( 2 ,2 ));
            EA.addPenguin(5,new Location( 2 ,2 ));
            EA.addPenguin(5,new Location( 2 ,2 ));
            EA.addPenguin(5,new Location( 2 ,2 ));
            EA.addPenguin(4,new Location( 3 ,3 ));
            EA.addPenguin(6,new Location( 3 ,2 ));
            EA.addPenguin(7,new Location( 3 ,2 ));
            EA.addPenguin(8,new Location( 4 ,2 ));
            EA.addPenguin(10,new Location( 5 ,2 ));
            EA.addPenguin(10,new Location( 5 ,2 ));
            EA.addPenguin(7,new Location( 5 ,1 ));
            EA.addPenguin(8,new Location( 5 ,1 ));
            EA.addPenguin(7,new Location( 5 ,0 ));
            EA.addPolarBear(10,new Location( 5 ,0 ));
            EA.addPolarBear(8,new Location( 5 ,0 ));
        }
        public static void loadSwampEnemies(EnemyAdder EA)
        {
            EA.addSlime(5,new Location( 11 ,9 ));
            EA.addSlime(6,new Location( 11 ,9 ));
            EA.addRaccoon(9,new Location( 11 ,9 ));
            EA.addRaccoon(12,new Location( 11 ,9 ));
            EA.addSlime(10,new Location( 11 ,8 ));
            EA.addSlime(10,new Location( 11 ,8 ));
            EA.addSlime(8,new Location( 11 ,8 ));
            EA.addBear(5,new Location( 11 ,8 ));
            EA.addRaccoon(10,new Location( 11 ,8 ));
            EA.addSlime(5,new Location( 11 ,11 ));
            EA.addSlime(5,new Location( 11 ,11 ));
            EA.addSlime(5,new Location( 11 ,11 ));
            EA.addSlime(5,new Location( 11 ,11 ));
            EA.addRaccoon(7,new Location( 11 ,11 ));
            EA.addRaccoon(7,new Location( 11 ,11 ));
            EA.addCrab(10,new Location( 11 ,12 ));
            EA.addCrab(10,new Location( 11 ,12 ));
            EA.addCrab(10,new Location( 11 ,12 ));
            EA.addCrab(10,new Location( 12 ,11 ));
            EA.addSlime(6,new Location( 12 ,11 ));
            EA.addSlime(7,new Location( 12 ,11 ));
            EA.addSlime(8,new Location( 12 ,10 ));
            EA.addSlime(8,new Location( 12 ,10 ));
            EA.addSlime(8,new Location( 12 ,10 ));
            EA.addSlime(8,new Location( 12 ,10 ));
            EA.addBear(6,new Location( 12 ,10 ));
            EA.addBear(9,new Location( 12 ,10 ));
            EA.addBear(4,new Location( 12 ,10 ));
            EA.addCrab(10,new Location( 13 ,11 ));
            EA.addCrab(10,new Location( 13 ,11 ));
            EA.addCrab(10,new Location( 13 ,11 ));
            EA.addCrab(10,new Location( 13 ,10 ));
            EA.addCrab(10,new Location( 13 ,10 ));
            EA.addSlime(10,new Location( 13 ,10 ));
            EA.addSlime(10,new Location( 13 ,10 ));
            EA.addGoblin(6,new Location( 13 ,10 ));
            EA.addGoblin(5,new Location( 13 ,10 ));
            EA.addCrab(10,new Location( 14 ,10 ));
            EA.addCrab(10,new Location( 14 ,10 ));
            EA.addCrab(10,new Location( 14 ,10 ));
            EA.addCrab(10,new Location( 14 ,10 ));
            EA.addSlime(6,new Location( 13 ,9 ));
            EA.addSlime(7,new Location( 13 ,9 ));
            EA.addSlime(5,new Location( 13 ,9 ));
            EA.addOrc(6,new Location( 13 ,9 ));
            EA.addOrc(8,new Location( 13 ,9 ));
            EA.addOrc(5,new Location( 13 ,9 ));
            EA.addGoblin(5,new Location( 13 ,9 ));
            EA.addSlime(6,new Location( 12 ,9 ));
            EA.addSlime(6,new Location( 12 ,9 ));
            EA.addOrc(6,new Location( 13 ,8 ));
            EA.addOrc(6,new Location( 13 ,8 ));
            EA.addOrc(6,new Location( 13 ,8 ));
            EA.addOrc(10,new Location( 12 ,9 ));
            EA.addOrc(5,new Location( 12 ,8 ));
            EA.addOrc(6,new Location( 12 ,8 ));
            EA.addOrc(8,new Location( 12 ,8 ));
            EA.addOrc(3,new Location( 12 ,8 ));
            EA.addOrc(5,new Location( 12 ,8 ));
            EA.addGoblin(5,new Location( 12 ,8 ));
            EA.addGoblin(7,new Location( 12 ,8 ));
            EA.addOrc(10,new Location( 12 ,7 ));
            EA.addOrc(10,new Location( 12 ,7 ));
            EA.addSlime(6,new Location( 11 ,7 ));
            EA.addSlime(4,new Location( 11 ,7 ));
            EA.addSlime(7,new Location( 11 ,7 ));
            EA.addSlime(8,new Location( 11 ,7 ));
            EA.addSlime(2,new Location( 11 ,7 ));
            EA.addRaccoon(12,new Location( 11 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 10 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addTreep(10,new Location( 9 ,7 ));
            EA.addSlime(6,new Location( 11 ,6 ));
            EA.addSlime(7,new Location( 11 ,6 ));
            EA.addSlime(8,new Location( 11 ,6 ));
            EA.addSlime(9,new Location( 11 ,6 ));
            EA.addBear(6,new Location( 11 ,6 ));
            EA.addBear(7,new Location( 11 ,6 ));
            EA.addBear(4,new Location( 11 ,5 ));
            EA.addBear(7,new Location( 11 ,5 ));
            EA.addSlime(10,new Location( 12 ,6 ));
            EA.addSlime(10,new Location( 12 ,6 ));
            EA.addRaccoon(10,new Location( 12 ,6 ));
            EA.addRaccoon(10,new Location( 12 ,6 ));
            EA.addSlime(10,new Location( 13 ,6 ));
            EA.addSlime(10,new Location( 13 ,6 ));
            EA.addSlime(10,new Location( 13 ,6 ));
            EA.addBear(10,new Location( 13 ,5 ));
            EA.addBear(10,new Location( 13 ,5 ));
            EA.addOrc(6,new Location( 13 ,8 ));
            EA.addSavage(10,new Location( 14 ,8 ));
            EA.addSavage(10,new Location( 14 ,8 ));
            EA.addSavage(10,new Location( 14 ,8 ));
            EA.addSavage(10,new Location( 14 ,8 ));
            EA.addSavage(10,new Location( 14 ,8 ));
            EA.addSavage(10,new Location( 14 ,8 ));
            EA.addSavage(10,new Location( 14 ,8 ));
            EA.addSavage(10,new Location( 14 ,7 ));
            EA.addSavage(10,new Location( 14 ,7 ));
            EA.addSavage(10,new Location( 14 ,7 ));
            EA.addSavage(10,new Location( 14 ,7 ));
            EA.addSavage(10,new Location( 14 ,7 ));
            EA.addSavage(10,new Location( 14 ,7 ));
            EA.addSavage(10,new Location( 14 ,7 ));
            EA.addSavage(10,new Location( 14 ,7 ));
            EA.addSavage(10,new Location( 13 ,7 ));
            EA.addSavage(10,new Location( 13 ,7 ));
            EA.addSavage(10,new Location( 13 ,7 ));
            EA.addSavage(10,new Location( 13 ,7 ));
            EA.addSavage(10,new Location( 14 ,7 ));
        }
        public static void loadMountainEnemies(EnemyAdder EA)
        {
            EA.addNorog(4,new Location( 12 ,1 ));
            EA.addNorog(5,new Location( 12 ,1 ));
            EA.addNorog(4,new Location( 12 ,0 ));
            EA.addNorog(6,new Location( 12 ,0 ));
            EA.addNorog(7,new Location( 12 ,0 ));
            EA.addNorog(10,new Location( 11 ,0 ));
            EA.addNorog(6,new Location( 11 ,1 ));
            EA.addNorog(6,new Location( 11 ,1 ));
            EA.addNorog(5,new Location( 11 ,2 ));
            EA.addNorog(5,new Location( 11 ,2 ));
            EA.addNorog(5,new Location( 11 ,2 ));
            EA.addNorog(5,new Location( 11 ,2 ));
            EA.addNorog(6,new Location( 10 ,2 ));
            EA.addNorog(6,new Location( 10 ,2 ));
            EA.addOrc(4,new Location( 10 ,2 ));
            EA.addOrc(5,new Location( 10 ,2 ));
            EA.addOrc(5,new Location( 10 ,1 ));
            EA.addOrc(5,new Location( 10 ,1 ));
            EA.addOrc(5,new Location( 10 ,1 ));
            EA.addGoblin(5,new Location( 10 ,1 ));
            EA.addGoblin(5,new Location( 10 ,1 ));
            EA.addGoblin(5,new Location( 10 ,1 ));
            EA.addOrc(10,new Location( 10 ,0 ));
            EA.addOrc(10,new Location( 10 ,0 ));
            EA.addGoblin(1,new Location( 10 ,0 ));
            EA.addGoblin(1,new Location( 10 ,0 ));
            EA.addGoblin(1,new Location( 10 ,0 ));
            EA.addGoblin(1,new Location( 10 ,0 ));
            EA.addGoblin(1,new Location( 10 ,0 ));
            EA.addGoblin(1,new Location( 10 ,0 ));
            EA.addFireRaccoon(5,new Location( 9 ,1 ));
            EA.addFireRaccoon(5,new Location( 9 ,1 ));
            EA.addFireRaccoon(5,new Location( 9 ,2 ));
            EA.addFireRaccoon(5,new Location( 9 ,2 ));
            EA.addFireRaccoon(4,new Location( 9 ,2 ));
            EA.addFireRaccoon(6,new Location( 8 ,1 ));
            EA.addFireRaccoon(6,new Location( 8 ,1 ));
            EA.addNorog(10,new Location( 7 ,2 ));
            EA.addNorog(10,new Location( 7 ,2 ));
        }
	public static void loadIslandEnemies(EnemyAdder EA)
	{
		EA.addBear(20,new Location( 8 ,14 ));
		EA.addBear(11,new Location( 8 ,14 ));
		EA.addBear(13,new Location( 8 ,14 ));
		EA.addBear(4,new Location( 8 ,14 ));
		EA.addTreep(6,new Location( 8 ,14 ));
		EA.addTreep(8,new Location( 8 ,14 ));
		EA.addRaccoon(7,new Location( 8 ,14 ));
		EA.addRaccoon(20,new Location( 8 ,14 ));
		EA.addRaccoon(13,new Location( 8 ,14 ));
		EA.addGoblin(10,new Location( 8 ,14 ));
		EA.addGoblin(7,new Location( 8 ,14 ));
		EA.addOrc(6,new Location( 8 ,14 ));
		EA.addOrc(12,new Location( 9 ,14 ));
		EA.addOrc(8,new Location( 9 ,14 ));
		EA.addOrc(9,new Location( 9 ,14 ));
		EA.addGoblin(5,new Location( 9 ,14 ));
		EA.addGoblin(12,new Location( 9 ,14 ));
		EA.addGoblin(13,new Location( 9 ,14 ));
		EA.addTreep(3,new Location( 9 ,14 ));
		EA.addTreep(6,new Location( 9 ,14 ));
		EA.addTreep(5,new Location( 9 ,14 ));
		EA.addTreep(9,new Location( 9 ,14 ));
		EA.addTreep(3,new Location( 9 ,14 ));
		EA.addPineTreep(15,new Location( 9 ,14 ));
		EA.addBear(4,new Location( 9 ,14 ));
		EA.addBear(6,new Location( 9 ,14 ));
		EA.addRaccoon(4,new Location( 9 ,14 ));
		EA.addRaccoon(9,new Location( 9 ,14 ));
		EA.addPineTreep(7,new Location( 9 ,14 ));
		EA.addPineTreep(9,new Location( 9 ,14 ));
		EA.addPineTreep(8,new Location( 9 ,14 ));
		EA.addPineTreep(3,new Location( 9 ,14 ));
	}
	public static void loadHVEnemies(EnemyAdder EA)
	{
		EA.addEvilGuard(3,new Location( 3 ,3 ));
		EA.addEvilGuard(4,new Location( 3 ,2 ));
		EA.addEvilGuard(5,new Location( 3 ,1 ));
		EA.addEvilGuard(4,new Location( 3 ,1 ));
		EA.addEvilGuard(5,new Location( 3 ,0 ));
		EA.addEvilGuard(6,new Location( 3 ,0 ));
		EA.addEvilGuard(5,new Location( 4 ,0 ));
		EA.addEvilGuard(4,new Location( 4 ,0 ));
		EA.addEvilGuard(5,new Location( 2 ,0 ));
		EA.addEvilGuard(4,new Location( 2 ,0 ));
		EA.addCow(2,new Location( 1 ,2 ));
		EA.addCow(3,new Location( 1 ,2 ));
		EA.addCow(5,new Location( 1 ,2 ));
		EA.addCow(4,new Location( 2 ,2 ));
		EA.addCow(7,new Location( 2 ,2 ));
		EA.addCow(1,new Location( 2 ,2 ));
		EA.addCow(6,new Location( 2 ,3 ));
		EA.addCow(3,new Location( 2 ,3 ));
		EA.addCow(2,new Location( 2 ,3 ));
		EA.addCow(5,new Location( 1 ,3 ));
		EA.addCow(3,new Location( 1 ,3 ));
		EA.addCow(4,new Location( 1 ,3 ));
	}

	public static void loadTemple1(World w)
	{
		EnemyAdder e = new EnemyAdder(w);
		e.addBat(4,new Location(1,0));
		e.addBat(2,new Location(1,0));
		e.addBat(2,new Location(3,0));
		e.addBat(2,new Location(3,0));
		e.addBat(3,new Location(1,1));
		e.addBat(3,new Location(1,1));
		e.addBat(2,new Location(2,1));
		e.addBat(1,new Location(2,1));
		e.addBat(3,new Location(2,1));
		e.addBat(1,new Location(0,2));
		e.addBat(1,new Location(0,2));
		e.addBat(2,new Location(1,2));
		e.addBat(1,new Location(1,2));
		e.addBat(1,new Location(2,2));
		e.addBat(1,new Location(2,3));
		e.addBat(2,new Location(3,3));
		e.addBat(1,new Location(3,3));
		//TURRETS
		e.addTurret(8,Actor.DIRECTION_UP    ,new Location(2,0),new Location(5,9));
		e.addTurret(10,Actor.DIRECTION_UP   ,new Location(2,0),new Location(7,9));
		e.addTurret(12,Actor.DIRECTION_UP   ,new Location(2,0),new Location(8,10));
		e.addTurret(14,Actor.DIRECTION_UP   ,new Location(2,0),new Location(10,8));
		e.addTurret(16,Actor.DIRECTION_UP   ,new Location(2,0),new Location(12,8));
		e.addTurret(10,Actor.DIRECTION_LEFT ,new Location(1,0),new Location(13,7));
		e.addTurret(10,Actor.DIRECTION_RIGHT,new Location(1,0),new Location(2,6));
		e.addTurret(10,Actor.DIRECTION_LEFT ,new Location(3,2),new Location(10,4));
		e.addTurret(14,Actor.DIRECTION_UP   ,new Location(3,1),new Location(5,9));
		e.addTurret(10,Actor.DIRECTION_UP   ,new Location(3,1),new Location(4,9));
		e.addTurret(20,Actor.DIRECTION_UP   ,new Location(0,3),new Location(4,10));
		//BOSS
		w.add(new Haskell1(new Location(0,1), new Location(3,2), w), new Location(0,1));
	}
	public static void loadTemple2(World w)
	{
		EnemyAdder EA = new EnemyAdder(w);
		//Turrets
		EA.addStoneTurret(13,Enemy.DIRECTION_RIGHT,new Location( 4 ,0 ), new Location( 4 ,2 ));
		EA.addStoneTurret(15,Enemy.DIRECTION_LEFT,new Location( 4 ,0 ), new Location( 10 ,3 ));
		EA.addStoneTurret(14,Enemy.DIRECTION_LEFT,new Location( 4 ,0 ), new Location( 10 ,5 ));
		EA.addStoneTurret(21,Enemy.DIRECTION_LEFT,new Location( 4 ,0 ), new Location( 10 ,7 ));
		EA.addStoneTurret(18,Enemy.DIRECTION_LEFT,new Location( 4 ,0 ), new Location( 10 ,9 ));
		EA.addStoneTurret(17,Enemy.DIRECTION_RIGHT,new Location( 4 ,0 ), new Location( 4 ,4 ));
		EA.addStoneTurret(22,Enemy.DIRECTION_RIGHT,new Location( 4 ,0 ), new Location( 4 ,6 ));
		EA.addStoneTurret(16,Enemy.DIRECTION_RIGHT,new Location( 4 ,0 ), new Location( 4 ,8 ));
		EA.addStoneTurret(19,Enemy.DIRECTION_RIGHT,new Location( 4 ,2 ), new Location( 1 ,4 ));
		EA.addStoneTurret(17,Enemy.DIRECTION_RIGHT,new Location( 4 ,2 ), new Location( 1 ,6 ));
		EA.addStoneTurret(15,Enemy.DIRECTION_LEFT,new Location( 4 ,2 ), new Location( 14 ,5 ));
		//Enemies
		EA.addBear(4,new Location( 3 ,1 ));
		EA.addBear(3,new Location( 3 ,1 ));
		EA.addBear(5,new Location( 2 ,1 ));
		EA.addBear(2,new Location( 2 ,1 ));
		EA.addBear(3,new Location( 2 ,1 ));
		EA.addBear(4,new Location( 2 ,2 ));
		EA.addBear(6,new Location( 2 ,2 ));
		EA.addBear(2,new Location( 2 ,2 ));
		EA.addBear(1,new Location( 3 ,2 ));
		EA.addBear(5,new Location( 3 ,2 ));
		EA.addPineTreep(2,new Location( 3 ,3 ));
		EA.addPineTreep(1,new Location( 3 ,3 ));
		EA.addPineTreep(3,new Location( 3 ,3 ));
		EA.addPineTreep(5,new Location( 3 ,3 ));
		EA.addPineTreep(4,new Location( 3 ,3 ));
		EA.addPineTreep(6,new Location( 3 ,3 ));
		EA.addPineTreep(1,new Location( 3 ,3 ));
		EA.addPineTreep(3,new Location( 3 ,3 ));
		EA.addPineTreep(5,new Location( 3 ,3 ));
		EA.addPineTreep(4,new Location( 3 ,3 ));
		EA.addRaccoon(5,new Location( 2 ,3 ));
		EA.addRaccoon(6,new Location( 2 ,3 ));
		EA.addRaccoon(3,new Location( 2 ,3 ));
		EA.addBear(5,new Location( 1 ,3 ));
		EA.addBear(7,new Location( 0 ,3 ));
		EA.addBear(4,new Location( 0 ,3 ));
		EA.addRaccoon(7,new Location( 0 ,3 ));
		EA.addPineTreep(3,new Location( 0 ,3 ));
		EA.addPineTreep(4,new Location( 0 ,3 ));
		EA.addPineTreep(6,new Location( 2 ,0 ));
		EA.addPineTreep(4,new Location( 2 ,0 ));
		EA.addPineTreep(3,new Location( 2 ,0 ));
		EA.addTurret(2,Enemy.DIRECTION_DOWN, new Location( 1 ,0 ),new Location( 3 ,2 ));
		EA.addPineTreep(4,new Location( 1 ,0 ));
		EA.addBear(10,new Location( 1 ,0 ));
		EA.addPineTreep(6,new Location( 1 ,1 ));
		EA.addPineTreep(5,new Location( 1 ,1 ));
		EA.addPineTreep(4,new Location( 1 ,1 ));
		EA.addPineTreep(3,new Location( 1 ,1 ));
		EA.addPineTreep(7,new Location( 1 ,2 ));
		EA.addPineTreep(6,new Location( 1 ,2 ));
		EA.addPineTreep(5,new Location( 1 ,2 ));
		EA.addPineTreep(4,new Location( 1 ,2 ));
		EA.addPineTreep(3,new Location( 1 ,2 ));
		EA.addPineTreep(8,new Location( 3 ,0 ));
		EA.addPineTreep(1,new Location( 3 ,0 ));
		EA.addPineTreep(2,new Location( 3 ,0 ));
		EA.addPineTreep(2,new Location( 3 ,0 ));
		EA.addPineTreep(4,new Location( 3 ,0 ));
		//Harry
		w.add(new Harry(new Location(0,1), new Location(3,2), w), new Location(0,1));
	}
	public static void loadTemple3(World w)
	{
		EnemyAdder EA = new EnemyAdder(w);
		//Turrets
		EA.addTurret(5,Actor.DIRECTION_LEFT, new Location( 4 ,2 ), new Location( 14 ,8 ));
		EA.addTurret(7,Actor.DIRECTION_LEFT, new Location( 4 ,2 ), new Location( 14 ,9 ));
		EA.addTurret(11,Actor.DIRECTION_LEFT, new Location( 4 ,2 ), new Location( 14 ,10));
		//Non-Turrets
		EA.addWizard(1,new Location(0,0));
		EA.addWizard(1,new Location(0,0));
		EA.addWizard(1,new Location(0,0));
		EA.addWizard(2,new Location(4,3));
		EA.addWizard(2,new Location(4,3));
		EA.addWizard(2,new Location(4,3));
		EA.addWizard(3,new Location(4,0));
		EA.addWizard(3,new Location(4,0));
		EA.addWizard(3,new Location(4,0));
		EA.addWizard(4,new Location(0,3));
		EA.addWizard(4,new Location(0,3));
		EA.addWizard(4,new Location(0,3));
		EA.addEvilGuard(2,new Location(1,4));
		EA.addEvilGuard(2,new Location(3,4));
		EA.addEvilSuitOfArmor(1, Actor.DIRECTION_RIGHT, new Location( 1 ,3 ), new Location( 3 ,9 ));
		EA.addEvilSuitOfArmor(1,Enemy.DIRECTION_DOWN,new Location( 4 ,4 ), new Location( 12 ,2 ));
		EA.addEvilSuitOfArmor(1,Enemy.DIRECTION_RIGHT,new Location( 3 ,3 ), new Location( 4 ,7 ));
		EA.addEvilSuitOfArmor(1,Enemy.DIRECTION_LEFT,new Location( 3 ,3 ), new Location( 12 ,7 ));
		EA.addEvilSuitOfArmor(2,Enemy.DIRECTION_LEFT,new Location( 1 ,2 ), new Location( 13 ,7 ));
		EA.addEvilSuitOfArmor(2,Enemy.DIRECTION_LEFT,new Location( 1 ,2 ), new Location( 13 ,5 ));
		EA.addEvilSuitOfArmor(3,Enemy.DIRECTION_DOWN,new Location( 0 ,4 ), new Location( 5 ,2 ));
		EA.addEvilSuitOfArmor(1,Enemy.DIRECTION_LEFT,new Location( 1 ,1 ), new Location( 12 ,6 ));
		EA.addEvilSuitOfArmor(1,Enemy.DIRECTION_DOWN,new Location( 0 ,1 ), new Location( 8 ,2 ));
		EA.addKnight(3,new Location( 4 ,1 ));
		EA.addKnight(5,new Location( 4 ,1 ));
		EA.addKnight(2,new Location( 3 ,0 ));
		EA.addKnight(4,new Location( 3 ,0 ));
		EA.addKnight(3,new Location( 3 ,1 ));
		EA.addKnight(2,new Location( 3 ,2 ));
		EA.addKnight(2,new Location( 3 ,2 ));
		EA.addKnight(1,new Location( 0 ,1 ));
		EA.addKnight(1,new Location( 0 ,1 ));
		EA.addEvilGuard(2,new Location( 0 ,4 ));
		EA.addEvilGuard(4,new Location( 0 ,4 ));
		EA.addEvilGuard(1,new Location( 1 ,4 ));
		EA.addEvilGuard(1,new Location( 1 ,4 ));
		EA.addEvilGuard(3,new Location( 3 ,4 ));
		EA.addEvilGuard(2,new Location( 3 ,4 ));
		EA.addEvilGuard(2,new Location( 4 ,4 ));
		EA.addEvilGuard(2,new Location( 4 ,4 ));
		EA.addEvilGuard(2,new Location( 3 ,3 ));
		EA.addEvilGuard(5,new Location( 0 ,2 ));
		EA.addEvilGuard(5,new Location( 0 ,2 ));
		EA.addEvilGuard(6,new Location( 1 ,0 ));
		EA.addEvilGuard(4,new Location( 1 ,0 ));
		EA.addEvilGuard(3,new Location( 1 ,0 ));
		EA.addEvilGuard(7,new Location( 2 ,0 ));
		EA.addEvilGuard(7,new Location( 2 ,0 ));


		w.add(new Michal(new Location(2,2), new Location(3,2), w), new Location(2,2));
	}
	public static void loadTemple4(World w)
	{
            EnemyAdder EA = new EnemyAdder(w);
            EA.addIceTurret(8,Enemy.DIRECTION_RIGHT, new Location( 3 ,4 ), new Location( 3 ,8 ));
            EA.addIceTurret(7,Enemy.DIRECTION_LEFT, new Location( 3 ,4 ), new Location( 12 ,4 ));
            EA.addIceTurret(20,Enemy.DIRECTION_RIGHT, new Location( 3 ,4 ), new Location( 4 ,1 ));
            EA.addIceTurret(12,Enemy.DIRECTION_RIGHT, new Location( 3 ,4 ), new Location( 9 ,9 ));
            EA.addIceTurret(10,Enemy.DIRECTION_LEFT, new Location( 3 ,4 ), new Location( 14 ,7 ));
            EA.addPenguin(10,new Location( 3 ,0 ));
            EA.addPenguin(10,new Location( 3 ,0 ));
            EA.addPenguin(10,new Location( 3 ,0 ));
            EA.addPenguin(10,new Location( 3 ,0 ));
            EA.addPenguin(10,new Location( 3 ,0 ));
            EA.addPenguin(10,new Location( 3 ,0 ));
            EA.addPenguin(10,new Location( 3 ,0 ));
            EA.addPenguin(10,new Location( 3 ,0 ));
            EA.addIceTurret(7,Enemy.DIRECTION_LEFT,new Location( 4 ,0 ), new Location( 14 ,6 ));
            EA.addIceTurret(8,Enemy.DIRECTION_LEFT, new Location( 4 ,0 ), new Location( 14 ,7 ));
            EA.addIceTurret(9,Enemy.DIRECTION_LEFT, new Location( 4 ,0 ), new Location( 14 ,8 ));
            EA.addIceTurret(10,Enemy.DIRECTION_UP, new Location( 4 ,0 ), new Location( 5 ,9 ));
            EA.addIceTurret(10,Enemy.DIRECTION_UP, new Location( 4 ,0 ), new Location( 6 ,9 ));
            EA.addIceTurret(7,Enemy.DIRECTION_UP, new Location( 4 ,1 ), new Location( 8 ,7 ));
            EA.addIceTurret(7,Enemy.DIRECTION_RIGHT, new Location( 4 ,1 ), new Location( 7 ,3 ));
            EA.addIceTurret(8,Enemy.DIRECTION_RIGHT, new Location( 4 ,1 ), new Location( 1 ,8 ));
            EA.addIceTurret(9,Enemy.DIRECTION_RIGHT, new Location( 4 ,1 ), new Location( 4 ,4 ));
            EA.addIceTurret(8,Enemy.DIRECTION_DOWN, new Location( 4 ,1 ), new Location( 5 ,3 ));
            EA.addIceTurret(6,Enemy.DIRECTION_DOWN, new Location( 4 ,1 ), new Location( 12 ,3 ));
            EA.addIceTurret(6,Enemy.DIRECTION_LEFT, new Location( 4 ,1 ), new Location( 14 ,5 ));
            EA.addIceTurret(7,Enemy.DIRECTION_UP, new Location( 4 ,1 ), new Location( 6 ,10 ));
            EA.addPolarBear(10,new Location( 2 ,3 ));
            EA.addPolarBear(10,new Location( 2 ,3 ));
            EA.addPolarBear(10,new Location( 2 ,3 ));
            EA.addPenguin(10,new Location( 4 ,3 ));
            EA.addPenguin(10,new Location( 4 ,3 ));
            EA.addPenguin(10,new Location( 4 ,4 ));
            EA.addPenguin(10,new Location( 4 ,4 ));
            EA.addPenguin(10,new Location( 4 ,4 ));
            EA.addPenguin(10,new Location( 4 ,3 ));

            w.add(new Nick(new Location(2,1), new Location(3,2), w), new Location(2,1));
	}
	public static void loadTemple5(World w)
	{
		EnemyAdder EA = new EnemyAdder(w);
		//Turrets
		EA.addStoneTurret(16,Enemy.DIRECTION_DOWN,new Location( 4 ,2 ), new Location( 5 ,1 ));
		EA.addStoneTurret(16,Enemy.DIRECTION_DOWN,new Location( 4 ,2 ), new Location( 4 ,1 ));
		EA.addStoneTurret(13,Enemy.DIRECTION_DOWN,new Location( 4 ,2 ), new Location( 10 ,1 ));
		EA.addStoneTurret(13,Enemy.DIRECTION_DOWN,new Location( 4 ,2 ), new Location( 11 ,1 ));
		EA.addStoneTurret(16,Enemy.DIRECTION_DOWN,new Location( 4 ,2 ), new Location( 6 ,6 ));
		EA.addStoneTurret(16,Enemy.DIRECTION_DOWN,new Location( 4 ,2 ), new Location( 9 ,6 ));
		EA.addStoneTurret(40,Enemy.DIRECTION_LEFT,new Location( 4 ,2 ), new Location( 12 ,7 ));
		EA.addStoneTurret(40,Enemy.DIRECTION_RIGHT,new Location( 4 ,2 ), new Location( 3 ,7 ));//fixed
		EA.addStoneTurret(30,Enemy.DIRECTION_LEFT,new Location( 4 ,2 ), new Location( 13 ,3 ));
		EA.addStoneTurret(40,Enemy.DIRECTION_RIGHT,new Location( 4 ,2 ), new Location( 2 ,3 ));
		EA.addStoneTurret(25,Enemy.DIRECTION_RIGHT,new Location( 4 ,2 ), new Location( 6 ,9 ));
		EA.addStoneTurret(25,Enemy.DIRECTION_LEFT,new Location( 4 ,2 ), new Location( 9 ,9 ));
		//Non-Turrets
                EA.addMelter(3, new Location(1,0));
                EA.addMelter(3, new Location(1,0));
                EA.addMelter(3, new Location(1,0));
                EA.addMelter(3, new Location(1,0));
                EA.addMelter(3, new Location(1,0));
                EA.addMelter(3, new Location(1,0));
                EA.addMelter(3, new Location(1,0));
                EA.addMelter(3, new Location(1,0));
                EA.addMelter(3, new Location(1,0));
                EA.addBlazingBat(3, new Location(0,0));
                EA.addBlazingBat(3, new Location(0,0));
                EA.addFireRaccoon(3, new Location(0,0));
                EA.addFireRaccoon(3, new Location(0,0));
                EA.addMelter(10,new Location( 0 ,0 ));
                EA.addMelter(10,new Location( 0 ,0 ));
                EA.addMelter(10,new Location( 2 ,0 ));
                EA.addMelter(10,new Location( 2 ,0 ));
                EA.addMelter(10,new Location( 2 ,0 ));
                EA.addMelter(10,new Location( 2 ,0 ));
                EA.addMelter(10,new Location( 2 ,0 ));
                EA.addMelter(10,new Location( 2 ,0 ));
                EA.addMelter(10,new Location( 2 ,0 ));
                EA.addMelter(10,new Location( 2 ,1 ));
                EA.addMelter(10,new Location( 2 ,1 ));
                EA.addMelter(10,new Location( 2 ,1 ));
                EA.addMelter(10,new Location( 2 ,1 ));
                EA.addMelter(10,new Location( 2 ,1 ));
                EA.addMelter(10,new Location( 1 ,1 ));
                EA.addFireRaccoon(5,new Location( 1 ,1 ));
                EA.addFireRaccoon(5,new Location( 1 ,1 ));
                EA.addFireRaccoon(7,new Location( 1 ,1 ));
                EA.addFireRaccoon(6,new Location( 0 ,1 ));
                EA.addFireRaccoon(6,new Location( 0 ,1 ));
                EA.addFireRaccoon(5,new Location( 0 ,1 ));
                EA.addBlazingBat(7,new Location( 0 ,1 ));
                EA.addBlazingBat(4,new Location( 0 ,1 ));
                EA.addMelter(10,new Location( 0 ,1 ));
                EA.addMelter(10,new Location( 0 ,1 ));
                EA.addMelter(10,new Location( 0 ,1 ));
                EA.addMelter(10,new Location( 1 ,2 ));
                EA.addMelter(10,new Location( 1 ,2 ));
                EA.addBlazingBat(8,new Location( 1 ,2 ));
                EA.addBlazingBat(8,new Location( 1 ,2 ));
                EA.addMelter(10,new Location( 2 ,2 ));
                EA.addMelter(10,new Location( 2 ,2 ));
                EA.addMelter(10,new Location( 1 ,3 ));
                EA.addMelter(10,new Location( 1 ,3 ));
                EA.addMelter(10,new Location( 1 ,3 ));
                EA.addMelter(10,new Location( 1 ,3 ));
                EA.addMelter(10,new Location( 0 ,2 ));
                EA.addMelter(10,new Location( 0 ,2 ));
                EA.addFireRaccoon(7,new Location( 0 ,2 ));
                EA.addFireRaccoon(5,new Location( 0 ,2 ));
                EA.addMelter(10,new Location( 0 ,4 ));
                EA.addMelter(10,new Location( 0 ,4 ));
                EA.addMelter(10,new Location( 0 ,4 ));
                EA.addBlazingBat(5,new Location( 0 ,4 ));
                EA.addBlazingBat(4,new Location( 0 ,4 ));
                EA.addBlazingBat(6,new Location( 0 ,4 ));
                EA.addMelter(10,new Location( 3 ,3 ));
                EA.addMelter(10,new Location( 3 ,3 ));
                EA.addMelter(10,new Location( 3 ,3 ));
                EA.addFireRaccoon(10,new Location( 3 ,3 ));
                EA.addMelter(10,new Location( 3 ,4 ));
                EA.addMelter(10,new Location( 3 ,4 ));
                EA.addBlazingBat(10,new Location( 3 ,4 ));
                EA.addFireRaccoon(8,new Location( 3 ,4 ));
                EA.addFireRaccoon(8,new Location( 3 ,4 ));
                EA.addFireRaccoon(7,new Location( 3 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,4 ));
                EA.addMelter(10,new Location( 5 ,3 ));
                EA.addMelter(10,new Location( 5 ,3 ));
                EA.addMelter(10,new Location( 5 ,3 ));
                EA.addMelter(10,new Location( 5 ,3 ));
                EA.addBlazingBat(9,new Location( 3 ,2 ));
                EA.addMelter(10,new Location( 3 ,1 ));
                EA.addMelter(10,new Location( 3 ,1 ));
                EA.addMelter(10,new Location( 3 ,1 ));
                EA.addMelter(10,new Location( 3 ,1 ));
                EA.addMelter(10,new Location( 3 ,1 ));
                EA.addFireRaccoon(6,new Location( 3 ,1 ));
                EA.addMelter(10,new Location( 4 ,1 ));
                EA.addFireRaccoon(4,new Location( 4 ,1 ));
                EA.addFireRaccoon(7,new Location( 4 ,1 ));
                EA.addFireRaccoon(5,new Location( 4 ,1 ));
                EA.addFireRaccoon(2,new Location( 4 ,1 ));
                EA.addFireRaccoon(5,new Location( 4 ,1 ));
                EA.addBlazingBat(5,new Location( 4 ,0 ));
                EA.addBlazingBat(6,new Location( 4 ,0 ));
                EA.addBlazingBat(8,new Location( 4 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addMelter(10,new Location( 3 ,0 ));
                EA.addFireRaccoon(5,new Location( 3 ,0 ));
                EA.addFireRaccoon(5,new Location( 3 ,0 ));
                EA.addFireRaccoon(5,new Location( 3 ,0 ));
                EA.addFireRaccoon(5,new Location( 3 ,0 ));
                EA.addFireRaccoon(5,new Location( 3 ,0 ));
                EA.addFireRaccoon(5,new Location( 3 ,0 ));
		//Boss (Kelch)
		w.add(new Kelch(new Location(5,1), new Location(7,3), w), new Location(5,1));
	}
	public static void loadTemple6(World w)
	{
		EnemyAdder EA = new EnemyAdder(w);

                EA.addOrc(10,new Location( 4 ,3 ));
                EA.addOrc(10,new Location( 4 ,3 ));
                EA.addOrc(10,new Location( 4 ,3 ));
                EA.addShark(10,new Location( 4 ,3 ));
                EA.addBat(10,new Location( 4 ,3 ));
                EA.addBat(10,new Location( 4 ,3 ));
                EA.addBat(10,new Location( 4 ,2 ));
                EA.addBat(10,new Location( 4 ,2 ));
                EA.addBat(10,new Location( 4 ,2 ));
                EA.addBat(10,new Location( 4 ,2 ));
                EA.addBat(10,new Location( 4 ,2 ));
                EA.addBat(10,new Location( 4 ,2 ));
                EA.addShark(10,new Location( 4 ,2 ));
                EA.addShark(10,new Location( 4 ,2 ));
                EA.addShark(10,new Location( 4 ,1 ));
                EA.addBear(10,new Location( 4 ,1 ));
                EA.addBear(10,new Location( 4 ,1 ));
                EA.addBear(10,new Location( 4 ,1 ));
                EA.addShark(10,new Location( 5 ,1 ));
                EA.addShark(10,new Location( 5 ,1 ));
                EA.addShark(10,new Location( 5 ,1 ));
                EA.addShark(10,new Location( 5 ,1 ));
                EA.addShark(10,new Location( 5 ,1 ));
                EA.addBat(10,new Location( 5 ,0 ));
                EA.addBat(10,new Location( 5 ,0 ));
                EA.addBat(10,new Location( 5 ,0 ));
                EA.addBat(10,new Location( 5 ,0 ));
                EA.addShark(10,new Location( 4 ,0 ));
                EA.addShark(10,new Location( 4 ,0 ));
                EA.addShark(10,new Location( 4 ,0 ));
                EA.addShark(10,new Location( 4 ,0 ));
                EA.addShark(10,new Location( 4 ,0 ));
                EA.addShark(10,new Location( 4 ,0 ));
                EA.addOrc(10,new Location( 5 ,2 ));
                EA.addOrc(10,new Location( 5 ,2 ));
                EA.addOrc(10,new Location( 5 ,2 ));
                EA.addOrc(10,new Location( 5 ,2 ));
                EA.addOrc(10,new Location( 5 ,2 ));
                EA.addTreep(10,new Location( 5 ,4 ));
                EA.addTreep(10,new Location( 5 ,4 ));
                EA.addTreep(10,new Location( 5 ,4 ));
                EA.addTreep(10,new Location( 5 ,4 ));
                EA.addTreep(10,new Location( 5 ,4 ));
                EA.addRaccoon(10,new Location( 5 ,4 ));
                EA.addRaccoon(10,new Location( 5 ,4 ));
                EA.addTurret(5,Enemy.DIRECTION_RIGHT, new Location( 3 ,3 ),new Location( 4 ,7 ));
                EA.addTurret(4,Enemy.DIRECTION_RIGHT, new Location( 3 ,3 ),new Location( 5 ,8 ));
                EA.addTurret(4,Enemy.DIRECTION_RIGHT, new Location( 3 ,3 ),new Location( 4 ,9 ));
                EA.addBear(7,new Location( 3 ,4 ));
                EA.addBear(7,new Location( 3 ,4 ));
                EA.addBear(7,new Location( 3 ,4 ));
                EA.addBear(7,new Location( 3 ,4 ));
                EA.addShark(10,new Location( 3 ,2 ));
                EA.addShark(10,new Location( 3 ,2 ));
                EA.addShark(10,new Location( 3 ,2 ));
                EA.addShark(1,new Location( 1 ,4 ));
                EA.addShark(1,new Location( 1 ,4 ));
                EA.addShark(1,new Location( 1 ,4 ));
                EA.addShark(1,new Location( 1 ,4 ));
                EA.addShark(1,new Location( 1 ,4 ));
                EA.addShark(1,new Location( 1 ,4 ));
                EA.addShark(1,new Location( 0 ,4 ));
                EA.addShark(1,new Location( 0 ,4 ));
                EA.addOrc(1,new Location( 0 ,4 ));
                EA.addBear(1,new Location( 0 ,4 ));
                EA.addShark(1,new Location( 1 ,3 ));
                EA.addShark(1,new Location( 1 ,3 ));
                EA.addBat(1,new Location( 1 ,2 ));
                EA.addBat(2,new Location( 1 ,2 ));
                EA.addBat(1,new Location( 1 ,2 ));
                EA.addBat(1,new Location( 1 ,0 ));
                EA.addBat(1,new Location( 1 ,0 ));
                EA.addBat(2,new Location( 1 ,0 ));
                EA.addBat(2,new Location( 1 ,0 ));
                EA.addTreep(10,new Location( 0 ,0 ));
                EA.addTreep(10,new Location( 0 ,0 ));
                EA.addTreep(10,new Location( 0 ,0 ));
                EA.addTreep(10,new Location( 0 ,0 ));
                EA.addTreep(10,new Location( 0 ,0 ));
                EA.addTreep(10,new Location( 0 ,0 ));
                EA.addBat(1,new Location( 2 ,0 ));
                EA.addBat(1,new Location( 2 ,0 ));
                EA.addBat(2,new Location( 2 ,0 ));
                EA.addIceTurret(10,Enemy.DIRECTION_RIGHT, new Location( 0 ,2 ), new Location( 3 ,4 ));
                EA.addIceTurret(10,Enemy.DIRECTION_RIGHT, new Location( 0 ,2 ), new Location( 3 ,5 ));
                EA.addIceTurret(10,Enemy.DIRECTION_LEFT,  new Location( 0 ,2 ), new Location( 14 ,4 ));
                EA.addIceTurret(10,Enemy.DIRECTION_LEFT,  new Location( 0 ,2 ), new Location( 14 ,5 ));
                EA.addIceTurret(20,Enemy.DIRECTION_DOWN,  new Location( 0 ,1 ), new Location( 9 ,1 ));
                EA.addIceTurret(20,Enemy.DIRECTION_DOWN,  new Location( 0 ,1 ), new Location( 13 ,1 ));
                EA.addIceTurret(20,Enemy.DIRECTION_DOWN,  new Location( 0 ,1 ), new Location( 5 ,1 ));
                EA.addIceTurret(20,Enemy.DIRECTION_DOWN,  new Location( 0 ,1 ), new Location( 7 ,1 ));
                EA.addIceTurret(6,Enemy.DIRECTION_UP,     new Location( 0 ,1 ), new Location( 3 ,10 ));
                EA.addIceTurret(30,Enemy.DIRECTION_UP,    new Location( 0 ,1 ), new Location( 6 ,10 ));
                EA.addIceTurret(10,Enemy.DIRECTION_UP,    new Location( 0 ,1 ), new Location( 11 ,9 ));
                EA.addIceTurret(10,Enemy.DIRECTION_UP,    new Location( 0 ,1 ), new Location( 14 ,10 ));
                EA.addShark(1,new Location( 2 ,4 ));
                EA.addShark(1,new Location( 2 ,4 ));
                EA.addBat(2,new Location( 2 ,4 ));
                EA.addBat(2,new Location( 2 ,4 ));
		

		w.add(new Scott1(new Location(3,1), new Location(3,2), w), new Location(3,1));
	}
        public static void loadTemple7(World w)
        {
            EnemyAdder EA = new EnemyAdder(w);
            EA.addStoneTurret(8,Enemy.DIRECTION_DOWN,new Location( 0 ,14 ), new Location( 5 ,1 ));
            EA.addStoneTurret(7,Enemy.DIRECTION_DOWN, new Location( 0 ,14 ), new Location( 6 ,1 ));
            EA.addStoneTurret(6,Enemy.DIRECTION_DOWN, new Location( 0 ,14 ), new Location( 7 ,1 ));
            EA.addStoneTurret(5,Enemy.DIRECTION_DOWN, new Location( 0 ,14 ), new Location( 8 ,1 ));
            EA.addStoneTurret(10,Enemy.DIRECTION_RIGHT, new Location( 0 ,14 ), new Location( 1 ,4 ));
            EA.addStoneTurret(9,Enemy.DIRECTION_UP, new Location( 0 ,14 ), new Location( 3 ,9 ));
            EA.addStoneTurret(5,Enemy.DIRECTION_DOWN, new Location( 0 ,14 ), new Location( 11 ,2 ));
            EA.addStoneTurret(7,Enemy.DIRECTION_LEFT, new Location( 0 ,14 ), new Location( 13 ,7 ));
            EA.addBat(10,new Location( 1 ,14 ));
            EA.addBat(10,new Location( 1 ,14 ));
            EA.addBat(7,new Location( 1 ,15 ));
            EA.addBat(7,new Location( 1 ,15 ));
            EA.addBat(7,new Location( 1 ,15 ));
            EA.addBat(7,new Location( 1 ,15 ));
            EA.addStoneTurret(21,Enemy.DIRECTION_UP, new Location( 2 ,15 ), new Location( 2 ,2 ));
            EA.addStoneTurret(31,Enemy.DIRECTION_UP, new Location( 2 ,15 ), new Location( 4 ,2 ));
            EA.addStoneTurret(41,Enemy.DIRECTION_UP, new Location( 2 ,15 ), new Location( 6 ,2 ));
            EA.addStoneTurret(27,Enemy.DIRECTION_UP, new Location( 2 ,15 ), new Location( 8 ,2 ));
            EA.addStoneTurret(37,Enemy.DIRECTION_UP, new Location( 2 ,15 ), new Location( 10 ,2) );
            EA.addStoneTurret(47,Enemy.DIRECTION_UP, new Location( 2 ,15 ), new Location( 12 ,2) );
            EA.addBat(10,new Location( 2 ,14 ));
            EA.addBat(10,new Location( 2 ,14 ));
            EA.addBat(10,new Location( 2 ,14 ));
            EA.addBat(10,new Location( 2 ,14 ));
            EA.addPineTreep(10,new Location( 2 ,13 ));
            EA.addPineTreep(10,new Location( 2 ,13 ));
            EA.addPineTreep(10,new Location( 2 ,13 ));
            EA.addPineTreep(10,new Location( 2 ,13 ));
            EA.addPineTreep(10,new Location( 2 ,13 ));
            EA.addPineTreep(10,new Location( 2 ,13 ));
            EA.addPineTreep(10,new Location( 2 ,13 ));
            EA.addPineTreep(10,new Location( 2 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,13 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 1 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 2 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,12 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addPineTreep(10,new Location( 0 ,13 ));
            EA.addScorpion(10,new Location( 0 ,11 ));
            EA.addScorpion(10,new Location( 0 ,11 ));
            EA.addScorpion(10,new Location( 0 ,10 ));
            EA.addScorpion(10,new Location( 0 ,10 ));
            EA.addKeyp(10,new Location( 0 ,10 ));
            EA.addKeyp(10,new Location( 0 ,10 ));
            EA.addKeyp(10,new Location( 0 ,10 ));
            EA.addKeyp(10,new Location( 0 ,10 ));
            EA.addScorpion(10,new Location( 1 ,10 ));
            EA.addScorpion(10,new Location( 1 ,10 ));
            EA.addScorpion(10,new Location( 1 ,10 ));
            EA.addKeyp(10,new Location( 1 ,10 ));
            EA.addKeyp(10,new Location( 1 ,10 ));
            EA.addKeyp(10,new Location( 1 ,10 ));
            EA.addKeyp(10,new Location( 1 ,10 ));
            EA.addScorpion(10,new Location( 1 ,11 ));
            EA.addScorpion(10,new Location( 1 ,11 ));
            EA.addOrc(10,new Location( 1 ,11 ));
            EA.addOrc(10,new Location( 1 ,11 ));
            EA.addKeyp(10,new Location( 1 ,11 ));
            EA.addKeyp(10,new Location( 1 ,11 ));
            EA.addKeyp(10,new Location( 1 ,11 ));
            EA.addKeyp(10,new Location( 1 ,11 ));
            EA.addOrc(10,new Location( 2 ,11 ));
            EA.addOrc(10,new Location( 2 ,11 ));
            EA.addOrc(10,new Location( 2 ,11 ));
            EA.addOrc(10,new Location( 2 ,11 ));
            EA.addKeyp(10,new Location( 2 ,11 ));
            EA.addKeyp(10,new Location( 2 ,11 ));
            EA.addKeyp(10,new Location( 2 ,11 ));
            EA.addKeyp(10,new Location( 2 ,11 ));
            EA.addScorpion(10,new Location( 2 ,10 ));
            EA.addOrc(10,new Location( 2 ,10 ));
            EA.addIceTurret(10,Enemy.DIRECTION_DOWN,  new Location( 2 ,9 ), new Location( 3 ,1 ));
            EA.addIceTurret(10,Enemy.DIRECTION_DOWN,  new Location( 2 ,9 ), new Location( 4 ,1 ));
            EA.addIceTurret(10,Enemy.DIRECTION_RIGHT, new Location( 1 ,9 ), new Location( 2 ,7 ));
            EA.addIceTurret(13,Enemy.DIRECTION_RIGHT, new Location( 1 ,9 ), new Location( 3 ,8 ));
            EA.addIceTurret(10,Enemy.DIRECTION_DOWN,  new Location( 1 ,9 ), new Location( 6 ,1 ));
            EA.addIceTurret(8,Enemy.DIRECTION_DOWN,   new Location( 1 ,9 ), new Location( 5 ,2 ));
            EA.addIceTurret(9,Enemy.DIRECTION_DOWN,   new Location( 1 ,9 ), new Location( 4 ,3 ));
            EA.addPolarBear(12,new Location( 1 ,8 ));
            EA.addPolarBear(12,new Location( 1 ,8 ));
            EA.addPenguin(13,new Location( 1 ,8 ));
            EA.addIceTurret(8,Enemy.DIRECTION_DOWN,   new Location( 0 ,9 ) , new Location( 2 ,4 ));
            EA.addIceTurret(10,Enemy.DIRECTION_DOWN,  new Location( 0 ,9 ) , new Location( 3 ,3 ));
            EA.addIceTurret(7,Enemy.DIRECTION_DOWN,   new Location( 0 ,9 ) , new Location( 5 ,2 ));
            EA.addIceTurret(6,Enemy.DIRECTION_DOWN,   new Location( 0 ,9 ) , new Location( 6 ,1 ));
            EA.addIceTurret(15,Enemy.DIRECTION_RIGHT, new Location( 0 ,9 ) , new Location( 1 ,8 ));
            EA.addBlazingBat(10,new Location( 0 ,7 ));
            EA.addBlazingBat(10,new Location( 0 ,7 ));
            EA.addFireRaccoon(10,new Location( 1 ,7 ));
            EA.addFireRaccoon(10,new Location( 1 ,7 ));
            EA.addFireRaccoon(10,new Location( 1 ,7 ));
            EA.addFireRaccoon(10,new Location( 1 ,7 ));
            EA.addBlazingBat(10,new Location( 1 ,7 ));
            EA.addBlazingBat(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addMelter(10,new Location( 1 ,7 ));
            EA.addFireRaccoon(10,new Location( 2 ,7 ));
            EA.addFireRaccoon(10,new Location( 2 ,7 ));
            EA.addFireRaccoon(10,new Location( 2 ,7 ));
            EA.addFireRaccoon(10,new Location( 2 ,7 ));
            EA.addFireRaccoon(10,new Location( 2 ,7 ));
            EA.addFireRaccoon(10,new Location( 2 ,7 ));
            EA.addBlazingBat(10,new Location( 2 ,7 ));
            EA.addBlazingBat(10,new Location( 2 ,7 ));
            EA.addBlazingBat(10,new Location( 2 ,7 ));
            EA.addMelter(10,new Location( 2 ,7 ));
            EA.addMelter(10,new Location( 2 ,7 ));
            EA.addMelter(10,new Location( 2 ,7 ));
            EA.addMelter(10,new Location( 2 ,7 ));
            EA.addMelter(10,new Location( 2 ,7 ));
            EA.addMelter(10,new Location( 2 ,7 ));
            EA.addMelter(10,new Location( 2 ,6 ));
            EA.addMelter(10,new Location( 2 ,6 ));
            EA.addMelter(10,new Location( 2 ,6 ));
            EA.addMelter(10,new Location( 2 ,6 ));
            EA.addMelter(10,new Location( 2 ,6 ));
            EA.addMelter(10,new Location( 2 ,6 ));
            EA.addMelter(10,new Location( 2 ,6 ));
            EA.addMelter(10,new Location( 2 ,6 ));
            EA.addMelter(10,new Location( 2 ,6 ));
            EA.addFireRaccoon(10,new Location( 2 ,6 ));
            EA.addFireRaccoon(10,new Location( 2 ,6 ));
            EA.addFireRaccoon(10,new Location( 2 ,6 ));
            EA.addBlazingBat(10,new Location( 2 ,6 ));
            EA.addBlazingBat(10,new Location( 2 ,6 ));
            EA.addBlazingBat(10,new Location( 2 ,6 ));
            EA.addBlazingBat(10,new Location( 1 ,6 ));
            EA.addBlazingBat(10,new Location( 1 ,6 ));
            EA.addBlazingBat(10,new Location( 1 ,6 ));
            EA.addBlazingBat(10,new Location( 1 ,6 ));
            EA.addBlazingBat(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 1 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addMelter(10,new Location( 0 ,6 ));
            EA.addShark(15,new Location( 2 ,5 ));
            EA.addShark(15,new Location( 2 ,5 ));
            EA.addShark(15,new Location( 2 ,5 ));
            EA.addShark(15,new Location( 2 ,5 ));
            EA.addShark(15,new Location( 2 ,4 ));
            EA.addShark(15,new Location( 2 ,4 ));
            EA.addShark(15,new Location( 2 ,4 ));
            EA.addShark(15,new Location( 1 ,4 ));
            EA.addShark(15,new Location( 1 ,4 ));
            EA.addShark(15,new Location( 1 ,4 ));
            EA.addShark(15,new Location( 1 ,5 ));
            EA.addShark(15,new Location( 1 ,5 ));
            EA.addShark(15,new Location( 1 ,5 ));
            EA.addShark(15,new Location( 1 ,5 ));
            EA.addShark(15,new Location( 1 ,5 ));
            EA.addShark(15,new Location( 1 ,5 ));
            EA.addShark(15,new Location( 0 ,5 ));
            EA.addShark(15,new Location( 0 ,5 ));
            EA.addShark(15,new Location( 0 ,5 ));
            EA.addRaccoon(20,new Location( 0 ,5 ));
            EA.addCrab(20,new Location( 0 ,4 ));
            EA.addShark(15,new Location( 0 ,4 ));
            EA.addShark(15,new Location( 0 ,4 ));
            EA.addShark(15,new Location( 0 ,4 ));
            EA.addShark(15,new Location( 0 ,4 ));
            EA.addStoneTurret(5,Enemy.DIRECTION_DOWN, new Location( 2 ,16 ), new Location( 1 ,2 ) );
            EA.addStoneTurret(5,Enemy.DIRECTION_DOWN, new Location( 2 ,16 ), new Location( 6 ,2  ));
            EA.addStoneTurret(6,Enemy.DIRECTION_UP,   new Location( 2 ,16 ), new Location( 5 ,9 ));
            EA.addStoneTurret(7,Enemy.DIRECTION_DOWN, new Location( 2 ,16 ), new Location( 3 ,1 ));
            EA.addStoneTurret(7,Enemy.DIRECTION_UP,   new Location( 2 ,16 ), new Location( 3 ,8 ));
            EA.addStoneTurret(8,Enemy.DIRECTION_DOWN, new Location( 1 ,16 ), new Location( 13 ,1 ));
            EA.addStoneTurret(7,Enemy.DIRECTION_DOWN, new Location( 1 ,16 ), new Location( 12 ,1 ));
            EA.addStoneTurret(6,Enemy.DIRECTION_DOWN, new Location( 1 ,16 ), new Location( 11 ,1 ));
            EA.addStoneTurret(5,Enemy.DIRECTION_DOWN, new Location( 1 ,16 ), new Location( 10 ,1 ));
            EA.addStoneTurret(5,Enemy.DIRECTION_DOWN, new Location( 1 ,16 ), new Location( 8 ,2 ) );
            EA.addStoneTurret(9,Enemy.DIRECTION_RIGHT,new Location( 1 ,16 ), new Location( 6 ,6 ) );
            EA.addStoneTurret(8,Enemy.DIRECTION_RIGHT,new Location( 1 ,16 ), new Location( 7 ,9 ) );
            EA.addStoneTurret(7,Enemy.DIRECTION_LEFT, new Location( 1 ,16 ), new Location( 12 ,10 ));
        }

        public static void loadHHEnemies(World w)
        {
            EnemyAdder EA = new EnemyAdder(w);
            
        }
	public static void loadCaveEnemies(EnemyAdder EA)
	{
		EA.addStoneTurret(20,Enemy.DIRECTION_DOWN,new Location( 1 ,1 ), new Location( 7 ,1 ));
		EA.addStoneTurret(19,Enemy.DIRECTION_DOWN,new Location( 1 ,1 ), new Location( 8 ,1 ));
		EA.addStoneTurret(18,Enemy.DIRECTION_DOWN,new Location( 1 ,1 ), new Location( 9 ,1 ));
		EA.addStoneTurret(17,Enemy.DIRECTION_DOWN,new Location( 1 ,1 ), new Location( 10 ,1 ));
		EA.addStoneTurret(17,Enemy.DIRECTION_UP,new Location( 1 ,1 ), new Location( 7 ,6 ));
		EA.addStoneTurret(18,Enemy.DIRECTION_UP,new Location( 1 ,1 ), new Location( 8 ,6 ));
		EA.addStoneTurret(19,Enemy.DIRECTION_UP,new Location( 1 ,1 ), new Location( 9 ,6 ));
		EA.addStoneTurret(20,Enemy.DIRECTION_UP,new Location( 1 ,1 ), new Location( 10 ,6 ));
		EA.addBat(7,new Location( 0 ,0 ));
		EA.addBat(6,new Location( 0 ,0 ));
		EA.addBat(5,new Location( 0 ,0 ));
		EA.addBat(4,new Location( 0 ,1 ));
		EA.addBat(8,new Location( 1 ,0 ));
		EA.addBat(5,new Location( 1 ,0 ));
		EA.addBat(4,new Location( 1 ,0 ));
		EA.addBat(6,new Location( 1 ,0 ));
		EA.addBat(10,new Location( 3 ,0 ));
		EA.addBat(12,new Location( 3 ,0 ));
		EA.addBat(10,new Location( 0 ,2 ));
		EA.addBat(11,new Location( 0 ,2 ));
		EA.addBat(9,new Location( 0 ,3 ));
		EA.addBat(8,new Location( 0 ,3 ));
		EA.addBat(7,new Location( 0 ,4 ));
		EA.addBat(6,new Location( 0 ,4 ));
		EA.addBat(9,new Location( 0 ,5 ));
		EA.addBat(5,new Location( 0 ,5 ));
		EA.addBat(6,new Location( 0 ,6 ));
		EA.addBat(4,new Location( 1 ,5 ));
		EA.addBat(4,new Location( 2 ,5 ));
		EA.addShark(10,new Location( 3 ,6 ));
		EA.addShark(10,new Location( 4 ,6 ));
		EA.addShark(10,new Location( 5 ,6 ));
		EA.addBat(5,new Location( 5 ,5 ));
		EA.addBat(4,new Location( 5 ,5 ));
		EA.addGoblin(7,new Location( 4 ,5 ));
		EA.addGoblin(9,new Location( 4 ,5 ));
		EA.addBat(4,new Location( 4 ,5 ));
		EA.addOrc(11,new Location( 3 ,5 ));
		EA.addGoblin(5,new Location( 3 ,5 ));
		EA.addGoblin(4,new Location( 3 ,5 ));
		EA.addBat(6,new Location( 3 ,5 ));
		EA.addBat(8,new Location( 5 ,4 ));
		EA.addStoneTurret(15,Enemy.DIRECTION_LEFT,new Location( 5 ,3 ), new Location( 9 ,4 ));
		EA.addStoneTurret(15,Enemy.DIRECTION_RIGHT,new Location( 5 ,3 ), new Location( 0 ,3 ));
		EA.addBat(6,new Location( 4 ,3 ));
		EA.addBat(7,new Location( 4 ,3 ));
		EA.addBat(4,new Location( 4 ,3 ));
		EA.addBat(10,new Location( 4 ,4 ));
		EA.addBat(10,new Location( 4 ,4 ));
		EA.addBat(15,new Location( 3 ,4 ));
		EA.addStoneTurret(10,Enemy.DIRECTION_LEFT,new Location( 3 ,3 ), new Location( 11 ,4 ));
		EA.addStoneTurret(10,Enemy.DIRECTION_DOWN,new Location( 3 ,2 ), new Location( 12 ,1 ));
		EA.addBat(5,new Location( 3 ,2 ));
		EA.addBat(4,new Location( 3 ,2 ));
		EA.addTurret(10,Enemy.DIRECTION_RIGHT,new Location( 4 ,2 ), new Location( 2 ,6 ));
		EA.addBat(4,new Location( 4 ,2 ));
		EA.addBat(3,new Location( 4 ,2 ));
		EA.addBat(6,new Location( 4 ,2 ));
		EA.addStoneTurret(10,Enemy.DIRECTION_UP,new Location( 5 ,2 ), new Location( 7 ,10 ));
		EA.addGoblin(7,new Location( 5 ,1 ));
		EA.addGoblin(7,new Location( 5 ,1 ));
		EA.addBat(5,new Location( 5 ,1 ));
		EA.addBat(4,new Location( 5 ,1 ));
		EA.addStoneTurret(10,Enemy.DIRECTION_DOWN,new Location( 3 ,1 ), new Location( 9 ,1 ));
		EA.addOrc(6,new Location( 3 ,1 ));
		EA.addGoblin(5,new Location( 4 ,1 ));
		EA.addBat(8,new Location( 4 ,1 ));
		EA.addStoneTurret(10,Enemy.DIRECTION_UP,new Location( 4 ,0 ), new Location( 10 ,6 ));
		EA.addStoneTurret(15,Enemy.DIRECTION_DOWN,new Location( 4 ,0 ), new Location( 11 ,0 ));
		EA.addStoneTurret(17,Enemy.DIRECTION_UP,new Location( 4 ,0 ), new Location( 12 ,6 ));
		EA.addBat(6,new Location( 4 ,0 ));
		EA.addBat(4,new Location( 4 ,0 ));
		EA.addTurret(10,Enemy.DIRECTION_DOWN,new Location( 5 ,0 ), new Location( 4 ,3 ));
		EA.addTurret(12,Enemy.DIRECTION_RIGHT,new Location( 5 ,0 ), new Location( 3 ,7 ));
		EA.addBat(7,new Location( 5 ,0 ));
	}
        public static void loadNarniaEnemies(World w)
        {
            EnemyAdder EA = new EnemyAdder(w);
            
        }

//###################################################################################################################################
//####################################  Doors  ######################################################################################
//#################################################################################################################################*/
 	public static void loadDoors(World[] world)
 	{
		loadJangbertDoors(world);
		loadPortDoors(world);
		loadForestDoors(world);
		loadDesertDoors(world);
		loadSnowDoors(world);
		loadMountainDoors(world);
		loadIslandDoors(world);
		loadOtherDoors(world); //swamp-field cave, hauntedHouse, T7 Cave, Narnia
 	}

 	public static void loadJangbertDoors(World[] world)
 	{
 		DoorAdder DA = new DoorAdder(world[0]);
 		DA.addTwoDoor(new Location(1,12), new Location(7,3), new Location(0,0), new Location(8,9), world[8]);//Shop
 		DA.addTwoDoor(new Location(2,12), new Location(6,3), new Location(1,0), new Location(7,7), world[8]);//Old Man's House
 		DA.addTwoDoor(new Location(1,13), new Location(2,8), new Location(0,1), new Location(8,7), world[8]);//Veril's House
 		DA.addTwoDoor(new Location(2,13), new Location(11,5), new Location(1,1), new Location(10,11), world[8]);//Gary's House
 		DA.addTwoDoor(new Location(4,11), new Location(6,7), new Location(1,3), new Location(8,10), world[1]);//TEMPLE 1 Ent
 	}
 	public static void loadPortDoors(World[] world)
 	{
 		DoorAdder d = new DoorAdder(world[0]);
 		d.addTwoDoor(new Location(8,9),new Location(10,8), new Location(2,0), new Location(10,10), world[8]);//UL
 		d.addTwoDoor(new Location(8,10),new Location(10,3), new Location(3,0), new Location(7,10), world[8]);//Arrow Shop
 		d.addTwoDoor(new Location(8,10),new Location(5,10), new Location(4,0), new Location(11,8), world[8]);//L House
 		d.addTwoDoor(new Location(9,10),new Location(3,1), new Location(2,1), new Location(7,10), world[8]);//C House
 		d.addTwoDoor(new Location(10,10),new Location(6,6), new Location(3,1), new Location(4,9), world[8]);//R House
 		d.addTwoDoor(new Location(8,11),new Location(6,4), new Location(4,1), new Location(3,8), world[8]);//BL House 1
 		d.addTwoDoor(new Location(8,11),new Location(9,7), new Location(2,2), new Location(7,10), world[8]);//BL House 2
 		d.addTwoDoor(new Location(9,11),new Location(5,6), new Location(3,2), new Location(8,8), world[8]);//Gear Shop
 		d.addTwoDoor(new Location(10,11),new Location(13,3), new Location(4,2), new Location(12,6), world[8]);//Health Shop

 		d.addTwoDoor(new Location(10,9),new Location(11,4), new Location(7,1), new Location(8,9), world[8]);//MAYORS HOUSE
 	}
 	public static void loadForestDoors(World[] world)
 	{
 		DoorAdder d = new DoorAdder(world[0]);
 		d.addTwoDoor(new Location(0,7),new Location(12,6), new Location(0,2), new Location(9,7), world[8]);//Forest House 1
 		d.addTwoDoor(new Location(0,7),new Location( 3,2), new Location(1,2), new Location(7,8), world[8]);//Forest House 2
 		d.addTwoDoor(new Location(0,6),new Location( 3,8), new Location(0,1), new Location(7,10), world[9]);//Cave Enter
 		d.addTwoDoor(new Location(1,5),new Location(12,3), new Location(1,0), new Location(11,10), world[9]);//Cave Exit
 		d.addTwoDoor(new Location(1,4),new Location( 9,4), new Location(4,3), new Location(4,11), world[2]);//Temple 2 Enter
 		d = new DoorAdder(world[2]);
 		d.addTwoDoor(new Location(4,2),new Location( 3,9), new Location(4,1), new Location(11,3), world[2]);//Entrance Cave
 		d.addTwoDoor(new Location(3,0),new Location( 4,2), new Location(4,0), new Location(7,11), world[2]);//Torch Cave
 		d.addOneDoor(new Location(0,2),new Location( 2,1), new Location(0,1), new Location(7,10), world[2]);//Boss
 	}
 	public static void loadDesertDoors(World[] world)
 	{
 		//Surface
 		DoorAdder DA = new DoorAdder(world[0]);
		DA.addTwoDoor(new Location(5,5), new Location(6,7), new Location(0,3), new Location(3,9), world[8]); //Cave 1
		DA.addTwoDoor(new Location(5,5), new Location(11,2), new Location(1,3), new Location(10,6), world[8]); //Cave 2
		DA.addTwoDoor(new Location(6,5), new Location(2,2), new Location(2,3), new Location(4,5), world[8]); //Cave 3
		DA.addTwoDoor(new Location(6,5), new Location(4,9), new Location(3,3), new Location(6,10), world[8]); //Cave 4
		DA.addTwoDoor(new Location(6,5), new Location(10,5), new Location(4,3), new Location(12,8), world[8]); //Cave 5
 		DA.addTwoDoor(new Location(8,3), new Location(7,2), new Location(1,0), new Location(8,10), world[10]);//Hidden Valley Entrance
 		//Hidden Valley
		DA = new DoorAdder(world[10]);
		DA.addTwoDoor(new Location(3,0), new Location(7,1), new Location(2,4), new Location(7,10), world[3]);//T3
		DA.addTwoDoor(new Location(0,3), new Location(14,6), new Location(4,2), new Location(2,9), world[10]);//Ranch in
		DA.addTwoDoor(new Location(1,3), new Location(3,6), new Location(4,2), new Location(13,9), world[10]);//Ranch out
		DA.addTwoDoor(new Location(0,4), new Location(3,7), new Location(4,3), new Location(2,9), world[10]);//House 1
		DA.addTwoDoor(new Location(1,4), new Location(12,3), new Location(4,4), new Location(12,5), world[10]);//House 2
		DA.addTwoDoor(new Location(2,4), new Location(11,8), new Location(1,1), new Location(12,10), world[10]);//House 3
 	}
 	public static void loadSnowDoors(World[] world)
 	{
 		DoorAdder d = new DoorAdder(world[0]);

 		d.addTwoDoor(new Location(3,0), new Location(12,8), new Location(5,0), new Location(7,10), world[8]);//UL Igloo
 		d.addTwoDoor(new Location(4,0), new Location(6,4), new Location(6,0), new Location(10,9), world[8]);//UR UR Igloo
 		d.addTwoDoor(new Location(4,0), new Location(2,7), new Location(6,1), new Location(5,7), world[8]);//UR BL Igloo
 		d.addTwoDoor(new Location(4,1), new Location(3,2), new Location(5,1), new Location(5,7), world[8]);//BR Igloo
 		d.addTwoDoor(new Location(3,1), new Location(12,2), new Location(5,2), new Location(8,11), world[8]);//BL UR Igloo
 		d.addTwoDoor(new Location(3,1), new Location(6,5), new Location(5,3), new Location(8,10), world[8]);//BL BL Igloo
 		d.addTwoDoor(new Location(5,0), new Location(8,5), new Location(1,4), new Location(7,11), world[9]);//Ice Cave
 		d.addTwoDoor(new Location(6,2), new Location(10,8), new Location(0,4), new Location(11,8), world[9]);//Mountain Cave
 		d.addTwoDoor(new Location(2,0), new Location(7,3), new Location(2,4), new Location(7,11), world[4]);//T4
 	}
 	public static void loadMountainDoors(World[] world)
 	{
 		DoorAdder d = new DoorAdder(world[0]);
 		//Cave Maze
 		d.addTwoDoor(new Location(12,4), new Location(4,5), new Location(4,1), new Location(12,11), world[9]);//A in
 		d.addTwoDoor(new Location(11,3), new Location(2,1), new Location(3,1), new Location(3,11), world[9]);//A out
 		d.addTwoDoor(new Location(11,4), new Location(7,6), new Location(3,3), new Location(4,8), world[9]);//B in
 		d.addTwoDoor(new Location(12,4), new Location(2,1), new Location(4,2), new Location(12,11), world[9]);//B out
 		d.addTwoDoor(new Location(12,3), new Location(8,6), new Location(5,0), new Location(11,11), world[9]);//C in
 		d.addTwoDoor(new Location(11,3), new Location(8,8), new Location(4,0), new Location(6,10), world[9]);//C out
 		d.addTwoDoor(new Location(12,3), new Location(11,6), new Location(4,3), new Location(12,6), world[9]);//D in
 		d.addTwoDoor(new Location(11,4), new Location(5,1), new Location(3,4), new Location(5,11), world[9]);//D out
 		d.addTwoDoor(new Location(11,3), new Location(4,7), new Location(1,5), new Location(11,11), world[9]);//E left
 		d.addTwoDoor(new Location(11,3), new Location(11,7), new Location(2,5), new Location(4,11), world[9]);//E right
 		d.addTwoDoor(new Location(11,4), new Location(12,1), new Location(5,2), new Location(4,11), world[9]);//F in
 		d.addTwoDoor(new Location(11,3), new Location(11,2), new Location(5,1), new Location(3,7), world[9]);//F out
 		d.addTwoDoor(new Location(12,3), new Location(13,1), new Location(0,6), new Location(3,10), world[9]);//G in
 		d.addTwoDoor(new Location(12,2), new Location(2,2), new Location(0,5), new Location(12,8), world[9]);//G out
 		d.addTwoDoor(new Location(12,2), new Location(3,6), new Location(5,5), new Location(5,10), world[9]);//H in
 		d.addTwoDoor(new Location(11,2), new Location(12,4), new Location(3,5), new Location(3,11), world[9]);//H out
 		d.addTwoDoor(new Location(12,2), new Location(11,5), new Location(5,3), new Location(2,5), world[9]);//I in
 		d.addTwoDoor(new Location(12,3), new Location(2,7), new Location(5,4), new Location(7,10), world[9]);//I out
 		d.addTwoDoor(new Location(13,5), new Location(7,4), new Location(2,6), new Location(12,11), world[9]);//T>B bottom
 		d.addTwoDoor(new Location(11,0), new Location(3,7), new Location(1,6), new Location(2,4), world[9]);//T>B top
 		//Other
 		d.addTwoDoor(new Location(8,2), new Location(4,5), new Location(6,3), new Location(8,11), world[8]);//Hermit in
 		d.addTwoDoor(new Location(7,0), new Location(12,3), new Location(6,2), new Location(3,6), world[8]);//Hermit out
 		d.addTwoDoor(new Location(6,0), new Location(2,2), new Location(0,2), new Location(3,8), world[9]);//Snow>Mountain top
 		d.addTwoDoor(new Location(8,0), new Location(7,4), new Location(1,4), new Location(8,10), world[5]);//T5 left
 		d.addTwoDoor(new Location(9,0), new Location(7,4), new Location(4,4), new Location(8,11), world[5]);//T5 right
 		d.addTwoDoor(new Location(13,1), new Location(3,4), new Location(3,6), new Location(7,11), world[9]);//Waterfall 1
 		d.addTwoDoor(new Location(14,1), new Location(5,7), new Location(4,6), new Location(7,11), world[9]);//Waterfall 2
 		d.addTwoDoor(new Location(14,1), new Location(13,5), new Location(5,6), new Location(7,11), world[9]);//Waterfall 3
 		d.addTwoDoor(new Location(14,0), new Location(8,6), new Location(0,16), new Location(7,11), world[7]);//T7
 	}
 	public static void loadIslandDoors(World[] world)
 	{
 		DoorAdder d = new DoorAdder(world[0]);
 		d.addTwoDoor(new Location(11,14), new Location(7,6), new Location(4,4), new Location(9,10), world[6]);//T6
 		d.addTwoDoor(new Location(14,14), new Location(8,7), new Location(8,2), new Location(9,11), world[8]);//TH guild
 	}
 	public static void loadOtherDoors(World[] world)
 	{
 		DoorAdder d = new DoorAdder(world[0]);
 		d.addTwoDoor(new Location(9,7), new Location(3,3), new Location(3,0), new Location(11,10), world[9]);//Swamp Cave
 		d.addTwoDoor(new Location(7,8), new Location(3,3), new Location(1,1), new Location(4,10), world[9]);//Field Cave
 		d.addOneTimeDoor(new Location(5,6),new Location(4,4), new Location(10,3), new Location(7,11), world[8]);//Haunted House In
 		d = new DoorAdder(world[8]);
 		d.addTwoDoor(new Location(8,1), new Location(7,11), new Location(5,6),new Location(4,4), world[0]);//Haunted House Out

 		//Temple 7
		d = new DoorAdder(world[7]);
 		d.addTwoDoor(new Location(2,4), new Location(2,5), new Location(2,16), new Location(9,11), world[7]);//T7 Cave In
 		d.addTwoDoor(new Location(1,4), new Location(11,6), new Location(1,16), new Location(9,11), world[7]);//T7 Cave Out
 		d.addTwoDoor(new Location(1,0),new Location(7,11), new Location(1,1), new Location(12,5), world[7]);//T7 Towertop

 		//Narnia
 		d = new DoorAdder(world[12]);
 		d.addOneDoor(new Location(2,2), new Location(11,2), new Location(5,2), new Location(11,2), world[8]);//From Narnia
 		d.addTwoDoor(new Location(1,2), new Location(7,5), new Location(1,1), new Location(7,11), world[12]);//Ice palace in
 		d.addTwoDoor(new Location(3,1), new Location(7,11), new Location(3,2), new Location(7,5), world[12]);//Ice palace out
 	}

//###################################################################################################################################
//####################################  Furniture  ##################################################################################
//#################################################################################################################################*/
	public static void loadFurniture(World[] world)
	{
		ObstacleAdder OA = new ObstacleAdder(world[8]);
		loadJangbertFurniture(OA);
		loadForestFurniture(OA);
		loadPierFurniture(OA);
		loadValleyFurniture(new ObstacleAdder(world[10]));
		loadDesertFurniture(OA);
		loadSnowFurniture(OA);
		loadOtherFurniture(OA);
	}

	public static void loadJangbertFurniture(ObstacleAdder o)
	{
		//Map Shop
		o.addShopCounters(new Location(0,0), new Location(4,4),8);
		//Veril's House
		o.addBed(new Location(0,1), new Location(7,5));
		o.addDrawer(new Location(0,1), new Location(8,5), new Item(Item.TYPE_LOVI, 5));
		//Gary's House
		o.addBed(new Location(1,1), new Location(3,1));
		o.addBed(new Location(1,1), new Location(10,1));
		o.addBookshelf(new Location(1,1), new Location(2,1));
		o.addBookshelf(new Location(1,1), new Location(13,6));
		o.addDresser(new Location(1,1), new Location(5,5));
		o.addDresser(new Location(1,1), new Location(6,1));
		o.addDrawer(new Location(1,1), new Location(2,5), new Item(Item.TYPE_LOVI, 1));
		//Old Man's House
		o.addBed(new Location(1,0), new Location(11,3));
		o.addDrawer(new Location(1,0), new Location(7,3), new Item(Item.TYPE_HEART_SMALL));
		o.addSuitOfArmor(new Location(1,0), new Location(5,6));
	}
	public static void loadForestFurniture(ObstacleAdder o)
	{
		o.addBed(new Location( 0 ,2 ), new Location( 6 ,2 ));
		o.addDresser(new Location( 0 ,2 ), new Location( 10 ,4 ));
		o.addBookshelf(new Location( 0 ,2 ), new Location( 8 ,4 ));
		o.addDrawer(new Location( 0 ,2 ), new Location( 5 ,2 ), new Item(Item.TYPE_LOVI, 5));
		o.addBed(new Location( 1 ,2 ), new Location( 4 ,2 ));
		o.addBed(new Location( 1 ,2 ), new Location( 12 ,2 ));
		o.addBookshelf(new Location( 1 ,2 ), new Location( 3 ,2 ));
		o.addBookshelf(new Location( 1 ,2 ), new Location( 13 ,2 ));
		o.addDresser(new Location( 1 ,2 ), new Location( 7 ,2 ));
		o.addDrawer(new Location( 1 ,2 ), new Location( 10 ,2 ), null);
		o.addDrawer(new Location( 1 ,2 ), new Location( 11 ,5 ), new Item(Item.TYPE_HEART_SMALL));
	}
	public static void loadPierFurniture(ObstacleAdder o)
	{
		o.addBed(new Location( 2 ,0 ), new Location( 3 ,3 ));
		o.addDresser(new Location( 2 ,0 ), new Location( 4 ,3 ));
		o.addDresser(new Location( 2 ,0 ), new Location( 7 ,7 ));
		o.addDresser(new Location( 2 ,0 ), new Location( 10 ,2 ));
		o.addDrawer(new Location( 2 ,0 ), new Location( 8 ,2 ), new Key(Key.GATE, 'c'));
		o.addBookshelf(new Location( 2 ,0 ), new Location( 7 ,2 ));
		o.addBookshelf(new Location( 2 ,0 ), new Location( 9 ,2 ));
		o.addBookshelf(new Location( 2 ,0 ), new Location( 11 ,5 ));
		o.addShopCounters(new Location( 3 ,0 ), new Location( 6 ,6 ),3);
		o.addBed(new Location( 3 ,0 ), new Location( 13 ,6 ));
		o.addDresser(new Location( 3 ,0 ), new Location( 3 ,3 ));
		o.addDresser(new Location( 3 ,0 ), new Location( 5 ,3 ));
		o.addDresser(new Location( 3 ,0 ), new Location( 7 ,3 ));
		o.addDresser(new Location( 3 ,0 ), new Location( 9 ,3 ));
		o.addBookshelf(new Location( 3 ,0 ), new Location( 11 ,3 ));
		o.addBookshelf(new Location( 3 ,0 ), new Location( 12 ,3 ));
		o.addDrawer(new Location( 3 ,0 ), new Location( 13 ,3 ), new Item(Item.TYPE_HEART_SMALL));
		o.addMagicshelf(new Location( 3 ,0 ), new Location( 10 ,6 ));
		o.addBed(new Location( 4 ,0 ), new Location( 2 ,4 ));
		o.addBed(new Location( 4 ,0 ), new Location( 4 ,4 ));
		o.addBookshelf(new Location( 4 ,0 ), new Location( 3 ,4 ));
		o.addDrawer(new Location( 4 ,0 ), new Location( 5 ,2 ), null);
		o.addDrawer(new Location( 4 ,0 ), new Location( 12 ,2 ), new Item(Item.TYPE_LOVI, 1));
		o.addDresser(new Location( 4 ,0 ), new Location( 8 ,2 ));
		o.addBed(new Location( 2 ,1 ), new Location( 11 ,2 ));
		o.addMagicshelf(new Location( 2 ,1 ), new Location( 8 ,2 ));
		o.addDrawer(new Location( 2 ,1 ), new Location( 3 ,5 ), new Item(Item.TYPE_ARROW, 5));
		o.addDresser(new Location( 2 ,1 ), new Location( 10 ,5 ));
		o.addBookshelf(new Location( 2 ,1 ), new Location( 6 ,5 ));
		o.addBookshelf(new Location( 2 ,1 ), new Location( 7 ,5 ));
		o.addBed(new Location( 3 ,1 ), new Location( 6 ,4 ));
		o.addMagicshelf(new Location( 3 ,1 ), new Location( 6 ,6 ));
		o.addMagicshelf(new Location( 3 ,1 ), new Location( 3 ,4 ));
		o.addMagicshelf(new Location( 3 ,1 ), new Location( 4 ,4 ));
		o.addDresser(new Location( 3 ,1 ), new Location( 3 ,6 ));
		o.addBed(new Location( 4 ,1 ), new Location( 6 ,2 ));
		o.addDrawer(new Location( 4 ,1 ), new Location( 7 ,2 ), null);
		o.addDrawer(new Location( 4 ,1 ), new Location( 5 ,2 ), new Item(Item.TYPE_HEART_SMALL));
		o.addDresser(new Location( 4 ,1 ), new Location( 2 ,2 ));
		o.addBookshelf(new Location( 4 ,1 ), new Location( 2 ,5 ));
		o.addBed(new Location( 2 ,2 ), new Location( 6 ,3 ));
		o.addBed(new Location( 2 ,2 ), new Location( 9 ,3 ));
		o.addDresser(new Location( 2 ,2 ), new Location( 7 ,3 ));
		o.addBookshelf(new Location( 2 ,2 ), new Location( 5 ,3 ));
		o.addBookshelf(new Location( 2 ,2 ), new Location( 10 ,3 ));
		o.addDrawer(new Location( 2 ,2 ), new Location( 3 ,6 ), new Item(Item.TYPE_LOVI, 5));
		o.addDrawer(new Location( 2 ,2 ), new Location( 12 ,6 ), null);
		o.addMagicshelf(new Location( 2 ,2 ), new Location( 2 ,6 ));
		o.addMagicshelf(new Location( 2 ,2 ), new Location( 13 ,6 ));
		o.addBookshelf(new Location( 2 ,2 ), new Location( 4 ,6 ));
		o.addBookshelf(new Location( 2 ,2 ), new Location( 11 ,6 ));
		o.addShopCounters(new Location( 3 ,2 ), new Location( 6 ,4 ),4);
		o.addSuitOfArmor(new Location( 3 ,2 ), new Location( 6 ,5 ));
		o.addSuitOfArmor(new Location( 3 ,2 ), new Location( 9 ,5 ));
		o.addBed(new Location( 3 ,2 ), new Location( 3 ,4 ));
		o.addBookshelf(new Location( 3 ,2 ), new Location( 3 ,2 ));
		o.addBookshelf(new Location( 3 ,2 ), new Location( 4 ,2 ));
		o.addDresser(new Location( 3 ,2 ), new Location( 7 ,2 ));
		o.addDresser(new Location( 3 ,2 ), new Location( 11 ,2 ));
		o.addMagicshelf(new Location( 3 ,2 ), new Location( 12 ,4 ));
		o.addShopCounters(new Location( 4 ,2 ), new Location( 11 ,3 ),3);
		o.addMagicshelf(new Location( 4 ,2 ), new Location( 13 ,4 ));
		o.addMagicshelf(new Location( 4 ,2 ), new Location( 11 ,4 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 4 ,0 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 4 ,2 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 4 ,4 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 4 ,6 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 4 ,8 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 12 ,8 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 12 ,6 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 12 ,4 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 12 ,2 ));
		o.addSuitOfArmor(new Location( 7 ,1 ), new Location( 12 ,0 ));
		o.addSuitOfArmor(new Location( 7 ,0 ), new Location( 4 ,11 ));
		o.addSuitOfArmor(new Location( 7 ,0 ), new Location( 12 ,11 ));
		o.addSuitOfArmor(new Location( 7 ,0 ), new Location( 12 ,9 ));
		o.addSuitOfArmor(new Location( 7 ,0 ), new Location( 4 ,9 ));
		o.addFireplace(new Location( 7 ,0 ), new Location( 8 ,1 ));
		o.addPlant(new Location( 7 ,0 ), new Location( 7 ,2 ));
		o.addPlant(new Location( 7 ,0 ), new Location( 9 ,2 ));
		o.addPlant(new Location( 7 ,0 ), new Location( 6 ,3 ));
		o.addPlant(new Location( 7 ,0 ), new Location( 10 ,3 ));
		o.addBookshelf(new Location( 7 ,0 ), new Location( 2 ,2 ));
		o.addBookshelf(new Location( 7 ,0 ), new Location( 14 ,2 ));
		o.addMagicshelf(new Location( 7 ,0 ), new Location( 12 ,2 ));
		o.addMagicshelf(new Location( 7 ,0 ), new Location( 4 ,2 ));
		o.addSuitOfArmor(new Location( 7 ,0 ), new Location( 14 ,8 ));
		o.addSuitOfArmor(new Location( 7 ,0 ), new Location( 2 ,8 ));
		o.addPlant(new Location( 4 ,2 ), new Location( 11 ,2 ));
		o.addPlant(new Location( 4 ,2 ), new Location( 13 ,2 ));
	}
	public static void loadValleyFurniture(ObstacleAdder FA)
	{
		FA.addBed(new Location( 1 ,1 ), new Location( 12 ,4 ));
		FA.addBed(new Location( 1 ,1 ), new Location( 14 ,4 ));
		FA.addDresser(new Location( 1 ,1 ), new Location( 9 ,6 ));
		FA.addDrawer(new Location( 1 ,1 ), new Location( 8 ,6 ),new Item(Item.TYPE_HEART_SMALL));
		FA.addPlant(new Location( 1 ,1 ), new Location( 14 ,9 ));
		FA.addPlant(new Location( 1 ,1 ), new Location( 8 ,9 ));
		FA.addBookshelf(new Location( 1 ,1 ), new Location( 13 ,4 ));
		FA.addDresser(new Location( 4 ,2 ), new Location( 2 ,4 ));
		FA.addDresser(new Location( 4 ,2 ), new Location( 12 ,4 ));
		FA.addBed(new Location( 4 ,2 ), new Location( 14 ,4 ));
		FA.addFireplace(new Location( 4 ,2 ), new Location( 8 ,3 ));
		FA.addPlant(new Location( 4 ,2 ), new Location( 1 ,8 ));
		FA.addPlant(new Location( 4 ,2 ), new Location( 14 ,8 ));
		FA.addDrawer(new Location( 4 ,2 ), new Location( 5 ,4 ),new Item(Item.TYPE_LOVI, 5));
		FA.addBed(new Location( 4 ,3 ), new Location( 6 ,4 ));
		FA.addBookshelf(new Location( 4 ,3 ), new Location( 1 ,4 ));
		FA.addMagicshelf(new Location( 4 ,3 ), new Location( 2 ,4 ));
		FA.addBookshelf(new Location( 4 ,3 ), new Location( 3 ,4 ));
		FA.addMagicshelf(new Location( 4 ,3 ), new Location( 4 ,4 ));
		FA.addPlant(new Location( 4 ,3 ), new Location( 6 ,8 ));
		FA.addDresser(new Location( 4 ,3 ), new Location( 1 ,6 ));
		FA.addBed(new Location( 4 ,4 ), new Location( 14 ,1 ));
		FA.addBed(new Location( 4 ,4 ), new Location( 9 ,1 ));
		FA.addBed(new Location( 4 ,4 ), new Location( 9 ,3 ));
		FA.addBed(new Location( 4 ,4 ), new Location( 14 ,3 ));
		FA.addBookshelf(new Location( 4 ,4 ), new Location( 10 ,1 ));
		FA.addBookshelf(new Location( 4 ,4 ), new Location( 13 ,1 ));
		FA.addDresser(new Location( 4 ,4 ), new Location( 11 ,1 ));
		FA.addDresser(new Location( 4 ,4 ), new Location( 11 ,3 ));
	}
	public static void loadDesertFurniture(ObstacleAdder FA)
	{
		FA.addCampfire(new Location( 0 ,3 ), new Location( 3 ,5 ));
		FA.addCampfire(new Location( 1 ,3 ), new Location( 10 ,3 ));
		FA.addCampfire(new Location( 2 ,3 ), new Location( 4 ,2 ));
		FA.addCampfire(new Location( 3 ,3 ), new Location( 9 ,6 ));
		FA.addCampfire(new Location( 3 ,3 ), new Location( 5 ,6 ));
		FA.addCampfire(new Location( 4 ,3 ), new Location( 9 ,4 ));
	}
	public static void loadSnowFurniture(ObstacleAdder FA)
	{
		FA.addBed(new Location( 5 ,1 ), new Location( 7 ,2 ));
		FA.addBed(new Location( 5 ,1 ), new Location( 3 ,2 ));
		FA.addFireplace(new Location( 5 ,1 ), new Location( 5 ,0 ));
		FA.addPlant(new Location( 5 ,1 ), new Location( 4 ,5 ));
		FA.addPlant(new Location( 5 ,1 ), new Location( 6 ,5 ));
		FA.addBookshelf(new Location( 5 ,1 ), new Location( 4 ,1 ));
		FA.addBookshelf(new Location( 5 ,1 ), new Location( 6 ,1 ));
		FA.addBed(new Location( 5 ,0 ), new Location( 5 ,2 ));
		FA.addBed(new Location( 5 ,0 ), new Location( 9 ,2 ));
		FA.addFireplace(new Location( 5 ,0 ), new Location( 3 ,2 ));
		FA.addFireplace(new Location( 5 ,0 ), new Location( 11 ,2 ));
		FA.addPlant(new Location( 5 ,0 ), new Location( 6 ,8 ));
		FA.addPlant(new Location( 5 ,0 ), new Location( 8 ,8 ));
		FA.addMagicshelf(new Location( 5 ,0 ), new Location( 6 ,1 ));
		FA.addDrawer(new Location( 5 ,0 ), new Location( 7 ,1 ),new Item(Item.TYPE_LOVI, 5));
		FA.addBookshelf(new Location( 5 ,0 ), new Location( 8 ,1 ));
		FA.addDresser(new Location( 5 ,0 ), new Location( 3 ,5 ));
		FA.addDresser(new Location( 5 ,0 ), new Location( 10 ,5 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 7 ,2 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 8 ,1 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 9 ,1 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 10 ,1 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 11 ,1 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 12 ,1 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 13 ,2 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 7 ,5 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 8 ,5 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 9 ,5 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 11 ,5 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 12 ,5 ));
		FA.addBed(new Location( 6 ,0 ), new Location( 13 ,5 ));
		FA.addBed(new Location( 6 ,1 ), new Location( 5 ,1 ));
		FA.addDrawer(new Location( 6 ,1 ), new Location( 4 ,1 ),new Item(Item.TYPE_HEART_SMALL));
		FA.addDrawer(new Location( 6 ,1 ), new Location( 6 ,1 ),null);
		FA.addPlant(new Location( 6 ,1 ), new Location( 3 ,2 ));
		FA.addBookshelf(new Location( 6 ,1 ), new Location( 7 ,2 ));
		FA.addBed(new Location( 5 ,3 ), new Location( 7 ,2 ));
		FA.addBed(new Location( 5 ,3 ), new Location( 9 ,2 ));
		FA.addFireplace(new Location( 5 ,3 ), new Location( 8 ,1 ));
		FA.addPlant(new Location( 5 ,3 ), new Location( 5 ,6 ));
		FA.addPlant(new Location( 5 ,3 ), new Location( 11 ,6 ));
		FA.addDresser(new Location( 5 ,3 ), new Location( 6 ,6 ));
		FA.addDresser(new Location( 5 ,3 ), new Location( 9 ,6 ));
		FA.addMagicshelf(new Location( 5 ,3 ), new Location( 6 ,3 ));
		FA.addMagicshelf(new Location( 5 ,3 ), new Location( 10 ,3 ));
		FA.addBookshelf(new Location( 5 ,3 ), new Location( 5 ,4 ));
		FA.addBookshelf(new Location( 5 ,3 ), new Location( 11 ,4 ));
	}
	public static void loadOtherFurniture(ObstacleAdder o)
	{
		//HH
		o.addSuitOfArmor(new Location( 9 ,2 ), new Location( 4 ,6 ));
		o.addSuitOfArmor(new Location( 9 ,2 ), new Location( 11 ,6 ));
		o.addSuitOfArmor(new Location( 10 ,2 ), new Location( 11 ,5 ));
		o.addSuitOfArmor(new Location( 10 ,2 ), new Location( 4 ,5 ));
		//TH Guild
		o.addBed(new Location( 7 ,2 ), new Location( 5 ,4 ));
		o.addBed(new Location( 7 ,2 ), new Location( 7 ,4 ));
		o.addBed(new Location( 7 ,2 ), new Location( 9 ,4 ));
		o.addBed(new Location( 7 ,2 ), new Location( 11 ,4 ));
		o.addFireplace(new Location( 7 ,2 ), new Location( 6 ,3 ));
		o.addFireplace(new Location( 7 ,2 ), new Location( 8 ,3 ));
		o.addFireplace(new Location( 7 ,2 ), new Location( 10 ,3 ));
		o.addPlant(new Location( 7 ,2 ), new Location( 8 ,10 ));
		o.addPlant(new Location( 7 ,2 ), new Location( 10 ,10 ));
		o.addMagicshelf(new Location( 7 ,2 ), new Location( 12 ,4 ));
		o.addDrawer(new Location( 7 ,2 ), new Location( 13 ,4 ), new Item(Item.TYPE_LOVI, 5));
		o.addDresser(new Location( 7 ,2 ), new Location( 5 ,7 ));
		o.addPlant(new Location( 8 ,2 ), new Location( 7 ,5 ));
		o.addPlant(new Location( 8 ,2 ), new Location( 5 ,6 ));
		o.addPlant(new Location( 8 ,2 ), new Location( 6 ,8 ));
		o.addPlant(new Location( 8 ,2 ), new Location( 8 ,7 ));
		o.addSuitOfArmor(new Location( 8 ,2 ), new Location( 13 ,4 ));
		o.addSuitOfArmor(new Location( 8 ,2 ), new Location( 13 ,10 ));
		o.addFireplace(new Location( 8 ,2 ), new Location( 3 ,3 ));
		o.addBookshelf(new Location( 8 ,2 ), new Location( 2 ,4 ));
		o.addBookshelf(new Location( 8 ,2 ), new Location( 4 ,4 ));
		o.addDresser(new Location( 8 ,2 ), new Location( 11 ,4 ));
		o.addDresser(new Location( 8 ,2 ), new Location( 9 ,4 ));
		o.addMagicshelf(new Location( 8 ,2 ), new Location( 13 ,7 ));
		//Hermit
		o.addCampfire(new Location( 6 ,3 ), new Location( 8 ,6 ));
	}

 //###################################################################################################################################
 //####################################  Items  ######################################################################################
 //#################################################################################################################################*/
	public static void loadItems(World[] world)
	{
		//OVERWORLD
		ItemAdder IA0 = new ItemAdder(world[0]);
		IA0.addSword(new Location(0,12), new Location(7,3));
		IA0.addHeartPiece(new Location(2 ,14), new Location(13,7 ));
		IA0.addHeartPiece(new Location(5 ,11), new Location(3 ,2 ));
		IA0.addHeartPiece(new Location(4 ,14), new Location(4 ,7 ));
		IA0.addHeartPiece(new Location(0 , 3), new Location(2 ,7 ));
		IA0.addHeartPiece(new Location(5 , 2), new Location(10,7 ));
		IA0.addHeartPiece(new Location(9 , 4), new Location(4 ,6 ));
		IA0.addHeartPiece(new Location(10, 6), new Location(14,6 ));
		IA0.addHeartPiece(new Location(7 , 8), new Location(4 ,6 ));
		IA0.addHeartPiece(new Location(7 ,11), new Location(12,2 ));
		IA0.addHeartPiece(new Location(10,12), new Location(8 ,2 ));
		IA0.addHeartPiece(new Location(10,14), new Location(11,8 ));
		IA0.addHeartPiece(new Location(12, 9), new Location(3 ,8 ));
		IA0.addHeartPiece(new Location(14, 9), new Location(8 ,5 ));
		IA0.addHeartPiece(new Location(12, 3), new Location(3 ,9 ));
		IA0.addHeartPiece(new Location(13, 0), new Location(9 ,3 ));
		IA0.addHeartPiece(new Location(2 , 8), new Location(6 ,5 ));
		IA0.addHeartPiece(new Location(1 , 7), new Location(12,9 ));
		IA0.addHeartPiece(new Location(0 ,10), new Location(2 ,8 ));
		IA0.addHeartPiece(new Location(8 ,10), new Location(13,1 ));
		world[0].add(new Item(Item.TYPE_SAIL, new Location(6,0), new Location(7,8), world[0]), new Location(6,0));

		//T1
		ItemAdder IA1 = new ItemAdder(world[1]);
		IA1.addCodeSnippet(new Location(0,0), new Location(7, 2), new Location(4, 11), new Location(6,10), world[0], 1);
		//T2
		ItemAdder IA2 = new ItemAdder(world[2]);
		IA2.addCodeSnippet(new Location(0,0), new Location(7, 3), new Location(1, 4), new Location(9,6), world[0], 2);
		//T3
		ItemAdder IA3 = new ItemAdder(world[3]);
		IA3.addCodeSnippet(new Location(2,1), new Location(7, 1), new Location(8, 3), new Location(7,4), world[0], 3);
		//T4
		ItemAdder IA4 = new ItemAdder(world[4]);
		IA4.addCodeSnippet(new Location(2,0), new Location(9, 1), new Location(2, 0), new Location(7,6), world[0], 4);
		//T5
		ItemAdder IA5 = new ItemAdder(world[5]);
		IA5.addCodeSnippet(new Location(5,0), new Location(7, 4), new Location(9, 0), new Location(7,8), world[0], 5);
		//T6
		ItemAdder IA6 = new ItemAdder(world[6]);
		IA6.addCodeSnippet(new Location(3,0), new Location(7, 3), new Location(11, 14), new Location(8,9), world[0], 6);
		//T7
		ItemAdder IA7 = new ItemAdder(world[7]);
		IA7.addKey(Key.SQUARE, new Location(0,10), new Location(3,4));
		IA7.addKey(Key.CIRCLE, new Location(1,10), new Location(7,3));
		IA7.addKey(Key.TRIANGLE, new Location(2,11), new Location(13,9));
		IA7.addCMD(new Location(2,0), new Location(7,5));
		//Caves

		//Codeland
		ItemAdder IA11 = new ItemAdder(world[11]);
		IA11.addCodeSnippet(new Location(0,1), new Location(7, 3), new Location(1, 14), new Location(7,7), world[0], 7);
	}

 //###################################################################################################################################
 //####################################  Messages  ###################################################################################
 //#################################################################################################################################*/
	public static void loadRoomMessages(World[] world)
	{
		//Temples
		world[1].getRoom(new Location(1,3)).setMessage("TEMPLE 1:\n\nGraphics Grotto");
		world[2].getRoom(new Location(4,3)).setMessage("TEMPLE 2:\n\nDark Grove");
		world[3].getRoom(new Location(2,4)).setMessage("TEMPLE 3:\n\nWizard's Keep");
		world[4].getRoom(new Location(2,4)).setMessage("TEMPLE 4:\n\nFrozen Fortress");
		world[5].getRoom(new Location(1,4)).setMessage("TEMPLE 5:\n\nTwinpeak Volcanos");
		world[5].getRoom(new Location(4,4)).setMessage("TEMPLE 5:\n\nTwinpeak Volcanos");
		world[6].getRoom(new Location(4,4)).setMessage("TEMPLE 6:\n\nSubmerged Cavern");
		world[7].getRoom(new Location(0,16)).setMessage("TEMPLE 7:\n\nProgrammer's Tower");
		//T7 Floors
		world[7].getRoom(new Location(0,15)).setMessage("FLOOR 1:\n\n\"Oh cool!  This floor's a cave!\"");
		world[7].getRoom(new Location(2,13)).setMessage("FLOOR 2:\n\n\"Woah, how'd they get trees in here?\"");
		world[7].getRoom(new Location(0,11)).setMessage("FLOOR 3:\n\n\"Now I'll get sand in my boots...\"");
		world[7].getRoom(new Location(2,9)).setMessage("FLOOR 4:\n\n\"Goddammit it's ice!\"");
		world[7].getRoom(new Location(0,7)).setMessage("FLOOR 5:\n\n\"What the?  Who put's lava in their home?\"");
		world[7].getRoom(new Location(2,5)).setMessage("FLOOR 6:\n\n\"This place must have cost a fortune!\"");
		world[7].getRoom(new Location(0,3)).setMessage("FLOOR 7:\n\n\"Uh-Oh, this floor looks official.\"");
		world[7].getRoom(new Location(0,0)).setMessage("FLOOR 10:\n\n\"What happened to floors 8 and 9?\"");
		world[7].getRoom(new Location(1,1)).setMessage("BALCONY:\n\n\"I can see my house from here!\"");
		world[7].getRoom(new Location(2,1)).setMessage("STILL THE BALCONY:\n\n\"Alright, these titles are getting annoying...\"");
		//Haunted House
		world[8].getRoom(new Location(10,3)).setMessage("Why have you entered my home?");
		world[8].getRoom(new Location(9,3)).setMessage("This house has been claimed by the dead");
		world[8].getRoom(new Location(9,2)).setMessage("Do you plan to rob us?");
		world[8].getRoom(new Location(10,2)).setMessage("What can you take?  All we have left are our cursed souls");
		world[8].getRoom(new Location(10,1)).setMessage("This house is home to some of Loviant's bloodiest secrets");
		world[8].getRoom(new Location(10,0)).setMessage("Yet you persist.  The only escape from here is death.");
		world[8].getRoom(new Location(11,0)).setMessage("But I cannot escape");
		world[8].getRoom(new Location(11,1)).setMessage("Which is why I'll bind you to my fate");
		world[8].getRoom(new Location(11,2)).setMessage("You will never leave this house, alive or dead.");
		world[8].getRoom(new Location(11,3)).setMessage("I will drag you deeper into the darkness");
		world[8].getRoom(new Location(9,1)).setMessage("You have been warned");
	}

 //###################################################################################################################################
 //####################################  Music  ######################################################################################
 //#################################################################################################################################*/
	public static void setMusic(World[] world)
	{
		//BOSSES
		world[1].getRoom(new Location(0,1)).setMusicNum(4);
		world[2].getRoom(new Location(0,1)).setMusicNum(4);
		world[3].getRoom(new Location(2,2)).setMusicNum(4);
		world[4].getRoom(new Location(2,1)).setMusicNum(4);
		world[5].getRoom(new Location(5,1)).setMusicNum(4);
		world[6].getRoom(new Location(3,1)).setMusicNum(4);
		world[7].getRoom(new Location(0,2)).setMusicNum(4);
		world[7].getRoom(new Location(1,3)).setMusicNum(4);
		world[8].getRoom(new Location(9,0)).setMusicNum(4);
		world[12].getRoom(new Location(3,0)).setMusicNum(4);
		//TOWNS
	}

}
