package Items;

import javax.swing.ImageIcon;
import java.io.Serializable;

public class Equipment implements Serializable
{
	public static final int TYPE_NONE = -1; //talking/using key/other
	public static final int TYPE_SWORD = 0;
	public static final int TYPE_BOW = 1;
	public static final int TYPE_TORCH = 2;
	public static final int TYPE_POKINGSTICK = 3;
	public static final int TYPE_HAMMER = 4;
	public static final int TYPE_SHOVEL = 5;
	public static final int TYPE_MIRROR = 6;

	private int type;
	private int level;

	public Equipment(int type)
	{
		this.type = type;
		level = 1;
	}

	public void setLevel(int newLevel)
	{
		level = newLevel;
	}

	public int getType()
	{
		return type;
	}

	public boolean equals(Object obj)
	{
		Equipment other = (Equipment) obj;
		return (getType() == other.getType());
	}

	public ImageIcon getImage()
	{
		String fp = "images/Items/";
		if (type == TYPE_NONE)
			fp+="none";
		else if (type == TYPE_SWORD)
		{
			if (level == 2)
				fp += "MasterSword";
			else
				fp+="StarterSword";
		}
		else if (type == TYPE_BOW)
			fp+="Bow";
		else if (type == TYPE_TORCH)
			fp+="Torch";
		else if (type == TYPE_POKINGSTICK)
			fp+="PokingStick";
		else if (type == TYPE_HAMMER)
			fp+="Hammer";
		else if (type == TYPE_SHOVEL)
			fp+="Shovel";
		else if (type == TYPE_MIRROR)
			fp+="Mirror";


		fp += ".png";
		return new ImageIcon(fp);
	}
}
