package GUI;

// VERIL 3 LOCATION FINDER!
//     Scott Blessing
//          2011

import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import Actors.*;
import Rooms.*;
import GUI.*;
import Obstacles.*;
import Items.*;
import MassCoding.Loader;

public class Veril3LocationFinder extends JFrame
{
	//Constants
	public static final int SCREEN_WIDTH = 808;
	public static final int SCREEN_HEIGHT = 670;

	private JLayeredPane content;		    //allows stacking JLabels
	private World[] world;				    //Our beautiful, beautiful worlds
	private Veril veril;					//I need one for some reason
	private JLabel[][] bgs;                 //bg tiles
	private JLabel[][] locs;				//locs
	private JLabel worldNum;				//world num
	private JLabel roomLoc;					//room loc
	private JButton[] worldBtn;				//World Buttons

	public Veril3LocationFinder()
	{
		super("Veril 3 Location Finder");
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setResizable(false);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((screenSize.width / 2) - (SCREEN_WIDTH / 2), (screenSize.height / 2) - (SCREEN_HEIGHT / 2)));


		content = new JLayeredPane();
		setContentPane(content);
		addKeyListener(new GameKeyListener());
		setLayout(null);

		setBGLocs();
		loadStuff();

		drawGameScreen();
		setVisible(true);
	}
	private void loadStuff()
	{
		world = new World[10];
		veril = new Veril(Actor.DIRECTION_DOWN, 15, 1, new Location(1, 14), new Location(13, 3));
		world = Loader.loadWorlds(veril);
		veril.setWorld(world[0]);
	}

//Labels and Images
	private void setBGLocs()
	{
		bgs = new JLabel[12][16];
		locs = new JLabel[12][16];
		for (int i = 0; i < bgs.length; i++)
		{
			for (int j = 0; j < bgs[i].length; j++)
			{
				bgs[i][j] = new JLabel();
				locs[i][j] = new JLabel();
				bgs[i][j].setLocation(j*Square.WIDTH, i*Square.HEIGHT);
				locs[i][j].setLocation(j*Square.WIDTH+12, i*Square.HEIGHT);
				bgs[i][j].setSize(Square.WIDTH, Square.HEIGHT);
				locs[i][j].setSize(Square.WIDTH, Square.HEIGHT);
				content.setLayer(bgs[i][j], 0);
				content.setLayer(locs[i][j],1);
				locs[i][j].setText("("+j+","+i+")");
				locs[i][j].setForeground(Color.BLACK);
				add(bgs[i][j]);
				add(locs[i][j]);
			}
		}
		worldNum = new JLabel();
		worldNum.setLocation(20, 600);
		worldNum.setSize(300, 20);
		worldNum.setText("World Num: 0");
		content.setLayer(worldNum, 2);
		this.add(worldNum);

		roomLoc = new JLabel();
		roomLoc.setLocation(20, 620);
		roomLoc.setSize(300, 20);
		roomLoc.setText("Room: (1,14)");
		content.setLayer(roomLoc, 2);
		this.add(roomLoc);

		worldBtn = new JButton[12];
		for (int i = 0; i < 12; i++)
		{
			JButton j = new JButton();
			j.setText(""+i);
			j.setFont(new Font("Myriad Pro",1,12));
			j.setSize(50,35);
			j.setLocation(120+(i*50),600);
			j.addActionListener(new WorldBtnListener());
			j.setFocusable(false);
			worldBtn[i] = j;
			this.add(j);
		}
	}


//Drawing the GAME SCREEN
	public void drawGameScreen()
	{
		if (veril.getWorld() != null)
		{
			Location room = veril.getRoomLoc();
			drawBG(room);

			this.add(new JLabel("buffer"));
			repaint();
		}
	}
	public void drawBG(Location room)
	{
		World w = veril.getWorld();
		if (w != null)
		{
			ImageIcon[][] imgs = veril.getWorld().getRoom(room).getImages();
			for (int i =0; i < bgs.length; i++)
				for (int j = 0; j < bgs[i].length; j++)
					bgs[i][j].setIcon(imgs[i][j]);

			worldNum.setText("World Num: " + veril.getWorld().getWorldNum());
			roomLoc.setText("Room Loc: "+veril.getRoomLoc().toString());
		}
		else
			worldNum.setText("WORLD FILE NOT FOUND");

	}

//Key Listener

	class GameKeyListener implements KeyListener
	{
		public void keyPressed(KeyEvent ke)
		{
			if (veril.getWorld() != null)
			{
				int k = ke.getKeyCode();
				if (k == KeyEvent.VK_UP)
					changeRoom(Actor.DIRECTION_UP);
				else if (k == KeyEvent.VK_DOWN)
					changeRoom(Actor.DIRECTION_DOWN);
				else if (k == KeyEvent.VK_LEFT)
					changeRoom(Actor.DIRECTION_LEFT);
				else if (k == KeyEvent.VK_RIGHT)
					changeRoom(Actor.DIRECTION_RIGHT);


				String keyPressed = KeyEvent.getKeyText(ke.getKeyCode());
				if (keyPressed.equalsIgnoreCase("W"))
				{
					int w = veril.getWorld().getWorldNum()-1;
					if (w == -1) w = world.length-1;
					World W = world[w];
					if (W != null)
					{
						veril.setWorld(world[w]);
						veril.setRoomLoc(new Location(0,0));
					}
				}
				else if (keyPressed.equalsIgnoreCase("E"))
				{
					int w = veril.getWorld().getWorldNum()+1;
					if (w == world.length) w = 0;
					World W = world[w];
					if (W != null)
					{
						veril.setWorld(world[w]);
						veril.setRoomLoc(new Location(0,0));
					}
				}
				else if (keyPressed.equalsIgnoreCase("A"))
					changeLabelColor(Color.WHITE);
				else if (keyPressed.equalsIgnoreCase("S"))
					changeLabelColor(Color.BLACK);
				else if (keyPressed.equalsIgnoreCase("D"))
					changeLabelColor(Color.RED);
				else if (keyPressed.equalsIgnoreCase("E"))
					changeLabelColor(Color.BLUE);
				else if (keyPressed.equalsIgnoreCase("Q"))
					setLocsVisibility(false);
				drawGameScreen();
			}
			//Set up Nums

		}

		public void changeRoom(int dir)
		{
			Location locInFront = veril.getRoomLoc().nextRoom(dir);
			if (locInFront != null)
				if (veril.getWorld().getRoom(locInFront) != null)
					veril.setRoomLoc(locInFront);
		}
		public void changeLabelColor(Color c)
		{
			setLocsVisibility(true);
			for (int i = 0; i < 12; i++)
				for (int j = 0; j < 16; j++)
					locs[i][j].setForeground(c);
		}
		public void setLocsVisibility(boolean visible)
		{
			for (int i = 0; i < 12; i++)
				for (int j = 0; j < 16; j++)
					locs[i][j].setVisible(visible);
		}

		public void keyTyped(KeyEvent ke){}
		public void keyReleased(KeyEvent ke) {}
	}

	class WorldBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton j = (JButton) e.getSource();
			for (int i = 0; i < 12; i++)
			{
				if (j == worldBtn[i])
				{
					veril.setWorld(world[i]);
					veril.setRoomLoc(new Location(0,0));
				}
			}
			drawGameScreen();
		}
	}
}
