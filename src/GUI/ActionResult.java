package GUI;

import javax.swing.ImageIcon;
import Items.Equipment;

public class ActionResult
{
	private String message;
	private ImageIcon pic;
	private Equipment equipmentUsed;

	public ActionResult()
	{
		message = "";
		pic = null;
		equipmentUsed = null;
	}
	public ActionResult(String text)
	{
		message = text;
		pic = null;
		equipmentUsed = null;
	}
	public ActionResult(String text, ImageIcon image)
	{
		message = text;
		pic = image;
		equipmentUsed = null;
	}
	public ActionResult(Equipment e)
	{
		message = "";
		pic = null;
		equipmentUsed = e;
	}

	public void setText(String text)
	{
		message = text;
	}
	public void setPic(ImageIcon image)
	{
		pic = image;
	}
	public void setEquip(Equipment e)
	{
		equipmentUsed = e;
	}

	public boolean hasText()
	{
		return (message != null && !message.equals(""));
	}
	public boolean hasPic()
	{
		return pic != null;
	}
	public boolean hasEquip()
	{
		return equipmentUsed != null;
	}

	public String getText()
	{
		return message;
	}
	public ImageIcon getPic()
	{
		return pic;
	}
	public Equipment getEquip()
	{
		return equipmentUsed;
	}
}
