/*
 *    Scott Blessing - Coding and general epicness
 *       Ryan Haskell - Artwork and other stuff
 *         VERIL 3 : Death to the Programmers
 *
 *          This here be where it launches
 *           But the main code be in the
 *              Veril3GameScreenFrame
 *
 *               Which be the JFrame
 *                 In case you're
 *                   Wondering
 *                     Bye!
 *
 *
 *       If you are looking at this, welcome to the
 *       Veril 3 source code.  My friends and I don't
 *       plan on selling this game, but if you do,
 *       I'll be angry but otherwise will have no legal
 *       power since this was never patented or
 *       copyrighted in any way
 *
 *		 Plus YOU will probably get sued for copying
 *       Nintendo!
 *
 *       Copyright 2011-2012 Limited Creativity Studios
 *       $25.00 US
 *		 Not for individual resale
 */

import GUI.Veril3MenuFrame;

public class Veril3
{
	public static void main(String[] args)
	{
		int r = (int) (Math.random() * 5);
		String s = "";
		if (r == 0) s = "a";
		if (r == 1) s = "b";
		if (r == 2) s = "c";
		if (r == 3) s = "d";
		if (r == 4) s = "e";
		Veril3MenuFrame menuScreen = new Veril3MenuFrame(s);
	}
}