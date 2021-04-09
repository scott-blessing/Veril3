package Items;

import Items.Item;
import Rooms.*;
import Actors.Veril;
import GUI.Inventory;
import GUI.Veril3CreditsFrame;
import javax.swing.JOptionPane;

public class CodeSnippet extends Item
{
	private World dWorld;
	private Location dRoom;
	private Location dLoc;

	public CodeSnippet(Location room, Location square, World w, Location dRoom, Location dLoc, World dWorld, int num)
	{
		super(Item.TYPE_CODESNIPPET, num, room, square, w);
		this.dRoom = dRoom;
		this.dLoc = dLoc;
		this.dWorld = dWorld;
	}

	public void pickUp(Veril v)
	{
		if (getVal() == 1)
			JOptionPane.showMessageDialog(null, "You found a Code Snippet!\nI wonder if anyone knows what it does?", "Code Snippet Get!", JOptionPane.PLAIN_MESSAGE, getImage());
		else if (getVal() == 6)
			JOptionPane.showMessageDialog(null, "You found all 6 Code Snippets!\nNow you can face the programmer in his tower!", "Code Snippet Get!", JOptionPane.PLAIN_MESSAGE, getImage());
		else if (getVal() == 7)
		{
			JOptionPane.showMessageDialog(null, "You found the Semi-colon!\nNow the code is sure to compile!", "Code Snippet Get!", JOptionPane.PLAIN_MESSAGE, getImage());
			endGame(v);
		}
		else
			JOptionPane.showMessageDialog(null, "You collected a Code Snippet!\nYou only need " + (6-getVal()) + " more to complete the line of code!", "Code Snippet Get!", JOptionPane.PLAIN_MESSAGE, getImage());

		v.getInventory().addCodeSnippet();
		v.setRoomLoc(dRoom);
		v.setLocation(dLoc);
		v.setWorld(dWorld);
		removeSelf();
	}

	public Location getDRoom()
	{
		return dRoom;
	}
	public Location getDLoc()
	{
		return dLoc;
	}
	public World getDWorld()
	{
		return dWorld;
	}



	//////END GAME//////
	private void endGame(Veril v)
	{
		int m = v.getInventory().getMaxHealth(); //max 315
		if (v.hasArmor()) m += 20;
		double percent = (((double) m)/335)*100;
		new Veril3CreditsFrame(percent);
	}


	////////////////////
}
