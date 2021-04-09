package Shortcuts;

import Actors.*;
import Actors.Enemies.*;
import Rooms.*;

public class EnemyAdder
{
	private World world;

	public EnemyAdder(World w)
	{
		world = w;
	}

	//Basic
	public void addRaccoon     (int strength, Location roomLoc)
	{
		world.add(new Raccoon(strength, roomLoc, world), roomLoc);
	}
	public void addCrab        (int strength, Location roomLoc)
	{
		world.add(new Crab(strength, roomLoc, world), roomLoc);
	}
	public void addBat         (int strength, Location roomLoc)
	{
		world.add(new Bat(strength, roomLoc, world), roomLoc);
	}
	public void addTreep       (int strength, Location roomLoc)
	{
		world.add(new Treep(strength, roomLoc, world), roomLoc);
	}
	public void addPineTreep   (int strength, Location roomLoc)
	{
		world.add(new PineTreep(strength, roomLoc, world), roomLoc);
	}
	public void addGhostArcher (int strength, Location roomLoc)
	{
		world.add(new GhostArcher(strength, roomLoc, world), roomLoc);
	}
	public void addShark       (int strength, Location roomLoc)
	{
		world.add(new Shark(strength, roomLoc, world), roomLoc);
	}
	public void addGoblin	   (int strength, Location roomLoc)
	{
		world.add(new Goblin(strength, roomLoc, world), roomLoc);
	}
	public void addMarksman	   (int strength, Location roomLoc)
	{
		world.add(new Marksman(strength, roomLoc, world), roomLoc);
	}
	public void addThief	   (int strength, Location roomLoc)
	{
		world.add(new Thief(strength, roomLoc, world), roomLoc);
	}
	public void addBandit	   (int strength, Location roomLoc)
	{
		world.add(new Bandit(strength, roomLoc, world), roomLoc);
	}
	public void addBear  	   (int strength, Location roomLoc)
	{
		world.add(new Bear(strength, roomLoc, world), roomLoc);
	}
	public void addWizard      (int strength, Location roomLoc)
	{
		world.add(new Wizard(strength, roomLoc, world), roomLoc);
	}
	public void addSavage      (int strength, Location roomLoc)
	{
		world.add(new Savage(strength, roomLoc, world), roomLoc);
	}
	public void addOrc         (int strength, Location roomLoc)
	{
		world.add(new Orc(strength, roomLoc, world), roomLoc);
	}
	public void addEvilGuard   (int strength, Location roomLoc)
	{
		world.add(new EvilGuard(strength, roomLoc, world), roomLoc);
	}
	public void addKnight      (int strength, Location roomLoc)
	{
		world.add(new Knight(strength, roomLoc, world), roomLoc);
	}
	public void addPolarBear   (int strength, Location roomLoc)
	{
		world.add(new PolarBear(strength, roomLoc, world), roomLoc);
	}
	public void addKeyp        (int strength, Location roomLoc)
	{
		world.add(new Keyp(strength, roomLoc, world), roomLoc);
	}
	public void addGhost       (int strength, Location roomLoc)
	{
		world.add(new Ghost(strength, roomLoc, world), roomLoc);
	}
	public void addScorpion    (int strength, Location roomLoc)
	{
		world.add(new Scorpion(strength, roomLoc, world), roomLoc);
	}
        public void addFireRaccoon (int strength, Location roomLoc)
	{
		world.add(new FireRaccoon(strength, roomLoc, world), roomLoc);
	}
        public void addBlazingBat  (int strength, Location roomLoc)
	{
		world.add(new BlazingBat(strength, roomLoc, world), roomLoc);
	}
        public void addMelter      (int strength, Location roomLoc)
	{
		world.add(new Melter(strength, roomLoc, world), roomLoc);
	}
        public void addPenguin     (int strength, Location roomLoc)
	{
		world.add(new Penguin(strength, roomLoc, world), roomLoc);
	}
        public void addSlime       (int strength, Location roomLoc)
	{
		world.add(new Slime(strength, roomLoc, world), roomLoc);
	}
        public void addNorog       (int strength, Location roomLoc)
	{
		world.add(new Norog(strength, roomLoc, world), roomLoc);
	}
	//Directionals
	public void addTurret          (int wait, int direction, Location roomLoc, Location loc)
	{
		world.add(new Turret(wait, direction, loc, roomLoc, world), roomLoc);
	}
	public void addStoneTurret     (int wait, int direction, Location roomLoc, Location loc)
	{
		world.add(new StoneTurret(wait, direction, loc, roomLoc, world), roomLoc);
	}
	public void addIceTurret       (int wait, int direction, Location roomLoc, Location loc)
	{
		world.add(new IceTurret(wait, direction, loc, roomLoc, world), roomLoc);
	}
	public void addEvilSuitOfArmor (int strength, int direction, Location roomLoc, Location loc)
	{
		world.add(new EvilSuitOfArmor(direction, strength, loc, roomLoc, world), roomLoc);
	}
	//Passive
	public void addSheep  (int strength, Location roomLoc)
	{
		world.add(new Sheep(strength, roomLoc, world), roomLoc);
	}
	public void addCow    (int strength, Location roomLoc)
	{
		world.add(new Cow(strength, roomLoc, world), roomLoc);
	}
	public void addRabbit (int strength, Location roomLoc)
	{
		world.add(new Rabbit(strength, roomLoc, world), roomLoc);
	}

	private int getX()
	{
		return (int) (Math.random() * 14) + 1;
	}
	private int getY()
	{
		return (int) (Math.random() * 10) + 1;
	}
	private int getDir()
	{
		return (int) (Math.random() * 4);
	}
}
