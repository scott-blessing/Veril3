package GUI;

/*                  LL
 *                  LL
 *	            LL
 *   		    LL      CCCCC
 *                  LL      CCCCC
 *		    LL      CC
 *		    LLLLLLL CCCCC
 *		    LLLLLLL CCCCC
 *	      Limited Creativity Studios
 *		      Presents
 *
 *
 * VVVV      VVVV                           33333
 *  VV        VV                           3333333
 *  VVV      VVV                          33     33
 *   VV      VV    The Legend of                33
 *   VVV    VVV   EEEEE RRRR  IIIII L         333
 *    VV    VV    E     R   R   I   L        333
 *    VVV  VVV    EEEE  RRRR    I   L         333
 *     VV  VV     E     R  R    I   L           33
 *     VVVVVV     EEEEE R   R IIIII LLLLL 33     33
 *      VVVV                 Death to the  3333333
 *       VV                  Programmers    33333
 *
 *
 *	       +-----------------------+
 *             |Coded By Scott Blessing|
 *             |Artwork by Ryan Haskell|
 *             +-----------------------+
 *2011-2012
 */


import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import Quests.Quest;
import Actors.*;
import Actors.Enemies.Bosses.Boss;
import Rooms.*;
import GUI.*;
import Obstacles.*;
import Items.*;
import MassCoding.Loader;
import Shortcuts.Finder;

public class Veril3GameScreenFrame extends JFrame
{
	//Constants
	public int FULL_SCREEN_WIDTH;
	public int FULL_SCREEN_HEIGHT;
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 700;
	public int XOFFSET;
	public int YOFFSET;

	public static final int BAR_LENGTH = 200;

	//Instance Fields
//	private OverlapPanel p;					//Panel-tastic
	private JComponent blackBG;				//The Black Background
	private JLayeredPane content;		    //allows stacking JLabels

	public static World[] world;			//Our beautiful, beautiful worlds

	private Veril veril;				    //VERIL

	private JLabel[][] bgs;                 //bg tiles
	private JLabel vLab;                    //Veril drawn
	private ArrayList<JLabel> otherLabels;  //items, enemies, obstacles
	private JLabel flash;					//flashes on room change
	private Timer flashTimer;				//Causes flash to disappear
	private Timer NPCTimer;				    //runs people and enemies

	private ArrayList<JLabel> arrowLabels;  //arrows
	private Timer arrowTimer;			    //runs arrows

	private JLabel statBar;				    //status bar at bottom of screen
	private JLabel item1;				    //Item equipped to LEFT
	private JLabel item2;				    //Item equipped to RIGHT
	private JLabel healthBar;			    //Shows health
	private JLabel healthLabel;			    //Shows health numbers
	private JLabel EXPBar;				    //Shows EXP
	private JLabel EXPLabel;			    //Shows EXP numbers
	private JLabel moneyLabel;			    //displays Lovi's
	private JLabel arrowLabel;				//displays num Arrows
	private JLabel keyLabel;				//displays keys
	private JLabel curLevel;			    //displays level
	private JLabel[] statBlips;			    //green blips in stat window

	private JLabel[] bossHealthFrame;		//Frames Boss Health
	private JLabel[] bossHealthBar;			//Shows boss health

	private boolean inventoryOpen;		    //is inv screen up or down?
	private boolean inventoryOpening;	    //prevents player movement while inv screen is opening
	private JLabel invScreen;			    //inventory screen that scrolls up
	private JLabel[] invSlots;			    //slots in the inventory screen
	private JLabel[][] mapTiles;			//Inv screen Map Tiles
	private JLabel invSelector;			    //shows what you have selected
	private int sectionInInv;		   	    //section in inventory
	private int itemInInv;		  		    //item selector is over in section
	private JLabel[] questNames;			//Names of quests
	private JLabel[] questTasks;			//Current task in the quest
	private JLabel invVeril;				//Image of Veril with Items
	private JLabel invVArmor;				////Armor
	private JLabel invVShovel;				////Shovel
	private JLabel invVSword;				////L2 Sword
	private JLabel codeSnippets;			//Displays code snippets
	private Timer invTimer;				    //raises and lowers le inventory

	private boolean shopOpen;				//is shop open? The world may never know!
	private boolean shopOpening;			//same as inventoryOpening but for shop
	private int selectionInShop;			//What you've selected
	private JLabel shopScreen;				//Shop Screen
	private JLabel[] shopPics;				//Item pictures
	private JLabel[] shopNames;				//Item Names
	private JLabel[] shopDescs;				//Item desc
	private JLabel[] shopPrices;			//Item Prices
	private JLabel shopSelector;			//Shows your selection
	private Timer shopTimer;				//slides shop screen

	private Timer moveTimer;				//animates veril movement

	private JLabel night;					//Creates night effect
	private Timer nightTimer;				//Night Timer

	private JLabel notification;			//Label that displays important info
	private JLabel noteOverlay;				//Does cool overlay effect
	private Timer notificationTimer;		//animates notification
	private String note;					//What is the notification?

	private JLabel death;					//black overlay
	private Timer deathTimer;				//Creates death fade effect

	private JLabel levUpScreen;				//Level up screen
	private JLabel[] skillLevels;			//Yo skittles
	private Timer levUpTimer;				//animates levUp
	private boolean levUpOpen;				//a thing
	private boolean levUpOpening;			//a thingie

	private boolean promptOpen;				//is Prompt screen open
	private JLabel controls;				//displays controls
	private JLabel promptScreen;			//displays prompts/dialogue
	private JTextArea dialogue;				//What they says
	private JLabel promptItem;				//What you gets
	private int promptType;					//(1:Save,2:QuitSave,3:Dialogue,4:ItemDialogue)
	private ActionResult promptData;		//Text & Pics

	//private boolean u,d,l,r;				//movement
	private KeyEvent curKey;				//a KeyEvent
	private Timer keyTimer;					//Updates movement

	private Timer itemAnimationTimer;		//Animates Item usage

	private boolean mapOpen;		  	  	//is map screen up or down?
	private boolean mapOpening;	    		//prevents player movement while map screen is opening
	private JLabel mapScreen;			    //map screen that scrolls down
	private JLabel[][] mapTiles2;			//map screen Map Tiles
	private JLabel mapSelecter;				//Shows warp location name
	private JLabel[] mapOptions;			//List of warpable places
	private Timer mapTimer;					//Opens map screen
	private Location[][] mapLocs;			//Array of town locs
	private int mapSelection;				//Currently selected map loc

	private String saveFile;				//Save filepath
	private boolean commandMode;			//FOR TESTING ONLY

//Initialization
	public Veril3GameScreenFrame(boolean newGame, String fp)
	{
		super("Veril 3: Death to the Programmers");

		setSize(SCREEN_WIDTH+10, SCREEN_HEIGHT+30);

		setResizable(false);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/Extra/v3.png"));
		setForeground(Color.BLACK);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((screenSize.width / 2) - (SCREEN_WIDTH / 2), (screenSize.height / 2) - (SCREEN_HEIGHT / 2)-40));

		content = new JLayeredPane();
		this.setContentPane(content);
		content.setBounds((FULL_SCREEN_WIDTH / 2) - (SCREEN_WIDTH / 2), (FULL_SCREEN_HEIGHT / 2) - (SCREEN_HEIGHT / 2)-10, SCREEN_WIDTH, SCREEN_HEIGHT);
		addKeyListener(new GameKeyListener());
		setLayout(null);

		blackBG = new JPanel();
		blackBG.setForeground(Color.BLACK);
		blackBG.setBackground(Color.BLACK);
		blackBG.setOpaque(true);
		blackBG.setBounds(0,0,FULL_SCREEN_WIDTH,FULL_SCREEN_HEIGHT);
		add(blackBG,0);
		content.setLayer(blackBG, 0);

		Finder.clearList();

		inventoryOpen = false;
		inventoryOpening = false;
		shopOpen = false;
		shopOpening = false;
		itemInInv = 0;

		loadEverything(); //Starts new game

		if (!newGame)
			saveFile = fp;

		setUpAllLabels();

		drawGameScreen();
		save2();
		setVisible(true);

		commandMode = false;

		Audio.playMusic("Overworld.mid");
		Audio.loop();
	}
	private void loadEverything()
	{
		veril = new Veril(Actor.DIRECTION_DOWN, 15, 1, new Location(1, 14), new Location(11, 4));
		world = Loader.loadWorlds(veril);
		veril.setWorld(world[0]);
		veril.getRoom().setExplored(true);
		Loader.loadObstacles(world);
		Loader.loadPeople(world);
		Loader.loadEnemies(world);
		Loader.loadFurniture(world);
		Loader.loadDoors(world);
		Loader.loadItems(world);
		Loader.loadRoomMessages(world);
	}

//Saving
	private void save()
	{
		startPrompt(1);
	}
	public void save2()
	{
		if (inTemple())
		{
			startPrompt(new ActionResult("You cannot save in Temples except on Save Tiles"));
			return;
		}

		if (saveFile == null)
		{
			while (saveFile == null || saveFile.equals(""))
			{
				saveFile = JOptionPane.showInputDialog("What's your name?");
			}
		}

		if (saveFile.length() > 4 && saveFile.substring(saveFile.length()-4,saveFile.length()).equals(".ver"))
			saveFile = saveFile.substring(0,saveFile.length()-4);

	    FileOutputStream fos = null;
     	ObjectOutputStream out = null;
     	try
     	{
       		fos = new FileOutputStream("SAVES/"+saveFile+".ver");
       		out = new ObjectOutputStream(fos);
       		out.writeObject(world);
       		out.close();
       		startNotification("e");
     	}
     	catch(IOException ex)
    	{
      		ex.printStackTrace();
    	}
	}

	private boolean inTemple()
	{
		int num = veril.getWorld().getWorldNum();
		boolean IT = (num >= 1 && num <= 7);

		VerilBaseObject vbo = veril.getRoom().getNonVerilObjectAt(veril.getLocation());
		if (vbo instanceof Obstacle)
		{
			Obstacle O = (Obstacle) vbo;
			if (O.getType() == Obstacle.TYPE_SAVETILE);
				IT = false;
		}

		return IT;
	}

//Loading
	public void load(World[] w)
	{
		world = w;
		veril = world[0].getRoom(0,0).getVeril();
		Finder.clearList();
		for (int i = 0; i <= 12; i++)
		{
			World W = world[i];
			for (int j = 0; j < W.getCols(); j++)
				for (int k = 0; k < W.getRows(); k++)
					for (int l = 0; l < 16; l++)
						for (int m = 0; m < 12; m++)
							if (W.getRoom(k,j).getObjectAt(new Location(l,m)) instanceof Person)
								Finder.addPerson((Person) W.getRoom(k,j).getObjectAt(new Location(l,m)));
		}

		drawGameScreen();
		Audio.playSong(veril.getRoom().getMusicNum());
		Audio.loop();
	}

//Death
	private void beginDeathAnimation()
	{
		deathTimer.start();
	}
	private void die()
	{
            if (veril.isT6Death())
            {
                veril.setRoomLoc(new Location(1,1));
                veril.setLocation(new Location(2,4));
                veril.setHealth(87);
                veril.getInventory().loseEquipment(new Equipment(Equipment.TYPE_SWORD));
                veril.getInventory().loseEquipment(new Equipment(Equipment.TYPE_BOW));
                veril.getInventory().loseEquipment(new Equipment(Equipment.TYPE_HAMMER));
                veril.getInventory().loseEquipment(new Equipment(Equipment.TYPE_TORCH));
                veril.getInventory().loseEquipment(new Equipment(Equipment.TYPE_POKINGSTICK));
                veril.takeFastShoes();
                drawGameScreen();
                updateStatBar();
            }
            else
            {

                try
                {
                    FileInputStream fileIn = new FileInputStream("saves/"+saveFile+".ver");
                    ObjectInputStream in = new ObjectInputStream(fileIn);

                    World[] w = (World[])in.readObject();

                    in.close();
                    fileIn.close();

                    load(w);
                }
                catch (ClassNotFoundException e) {}
                catch(FileNotFoundException e) {}
                catch (IOException e) {}
                
                
            }
	}

//Prompts
	private void startPrompt(ActionResult a)
	{
		promptData = a;
		if (a == null)
			return;

		if (a.hasPic())
			startPrompt(4);
		else
			startPrompt(3);
	}
	private void startPrompt(int type)
	{
		promptType = type;
		promptOpen = true;
		NPCTimer.stop();
		arrowTimer.stop();
		nightTimer.stop();
		drawPromptScreen();
	}
	private void closePrompt()
	{
		promptOpen = false;
		promptType = 0;
		drawPromptScreen();
		NPCTimer.start();
		arrowTimer.start();
		nightTimer.start();
	}

//Closing the Frame
	public void dispose()
	{
		NPCTimer.stop();
		invTimer.stop();
		arrowTimer.stop();
		moveTimer.stop();
		nightTimer.stop();
		shopTimer.stop();
		//keyTimer.stop();
		Audio.stopMusic();
		super.dispose();
	}

//Create and position JLabels and Timers
	public void setUpAllLabels()
	{
		setBGLocs();
		setVerilLabel();
		setObjectLabels();
		setInvLabels();
		setStatBarLabels();
		setShopLabels();
		setTimerz();
		setNotification();
		setDeathLabels();
		setLevelUp();
		setControlScreen();
		setPromptScreen();
		setMapScreen();
	}
	public void setBGLocs()
	{
		bgs = new JLabel[12][16];
		for (int i = 0; i < bgs.length; i++)
		{
			for (int j = 0; j < bgs[i].length; j++)
			{
				bgs[i][j] = new JLabel();
				bgs[i][j].setLocation(j*Square.WIDTH, i*Square.HEIGHT);
				bgs[i][j].setSize(Square.WIDTH, Square.HEIGHT);
				content.setLayer(bgs[i][j], 1);
				this.add(bgs[i][j], 1);
			}
		}

		night = new JLabel();
		night.setIcon(new ImageIcon("images/Extra/Night/0.png"));
		night.setSize(800, 600);
		night.setLocation(0,0);
		night.setVisible(false);
		content.setLayer(night, 4);
		this.add(night, 4);

		flash = new JLabel();
		flash.setIcon(new ImageIcon("images/Extra/Flash.png"));
		flash.setSize(800, 600);
		flash.setLocation(0,0);
		flash.setVisible(false);
		content.setLayer(flash, 100);
		this.add(flash, 100);
	}
	public void setVerilLabel()
	{
		vLab = new JLabel();
		vLab.setSize(50,50);
		vLab.setOpaque(false);
		vLab.setVisible(true);
		vLab.setLocation(veril.getLocation().getX()*Square.WIDTH, veril.getLocation().getY()*Square.HEIGHT);
		content.setLayer(vLab, 2);
		this.add(vLab, 2);
	}
	public void setObjectLabels()
	{
		otherLabels = new ArrayList<JLabel>();
		arrowLabels = new ArrayList<JLabel>();
	}
	public void setStatBarLabels()
	{
		statBar = new JLabel();
		statBar.setIcon(new ImageIcon("images/Inventory/StatusBar.png"));
		statBar.setLocation(0,600);
		statBar.setSize(800, 100);
		content.setLayer(statBar, 10);
		this.add(statBar, 10);

		item1 = new JLabel();
		item1.setSize(50,50);
		item1.setLocation(640, 625);
		content.setLayer(item1, 11);
		this.add(item1, 11);

		item2 = new JLabel();
		item2.setSize(50,50);
		item2.setLocation(715, 625);
		content.setLayer(item2, 11);
		this.add(item2, 11);

		healthBar = new JLabel();
		healthBar.setIcon(new ImageIcon("images/Inventory/HealthBar.png"));
		healthBar.setLocation(381, 611);
		healthBar.setSize(200,13);
		healthBar.setOpaque(false);
		this.add(healthBar, 11);
		content.setLayer(healthBar,11);

		healthLabel = new JLabel(veril.getHealth()+"/"+veril.getInventory().getMaxHealth());
		healthLabel.setLocation(461, 611);
		healthLabel.setSize(90,13);
		healthLabel.setForeground(Color.WHITE);
		content.setLayer(healthLabel, 12);
		this.add(healthLabel, 12);

		EXPBar = new JLabel();
		EXPBar.setIcon(new ImageIcon("images/Inventory/EXPBar.png"));
		EXPBar.setLocation(381, 631);
		EXPBar.setSize(200,13);
		EXPBar.setOpaque(false);
		this.add(EXPBar, 11);
		content.setLayer(EXPBar,11);

		EXPLabel = new JLabel(veril.getInventory().getEXP()+"/"+veril.getInventory().getLevelUpVal());
		EXPLabel.setLocation(461, 631);
		EXPLabel.setSize(90,13);
		EXPLabel.setForeground(Color.WHITE);
		content.setLayer(EXPLabel, 12);
		this.add(EXPLabel, 12);

		moneyLabel = new JLabel();
		moneyLabel.setFont(new Font("Myriad Pro",1,25));
		moneyLabel.setSize(75,23);
		moneyLabel.setLocation(310, 615);
		moneyLabel.setOpaque(false);
		moneyLabel.setForeground(Color.WHITE);
		this.add(moneyLabel, 11);
		content.setLayer(moneyLabel,11);

		arrowLabel = new JLabel();
		arrowLabel.setFont(new Font("",1,25));
		arrowLabel.setSize(75,23);
		arrowLabel.setLocation(310, 660);
		arrowLabel.setOpaque(false);
		arrowLabel.setForeground(Color.WHITE);
		this.add(arrowLabel, 11);
		content.setLayer(arrowLabel,11);

		keyLabel = new JLabel();
		keyLabel.setFont(new Font("",1,25));
		keyLabel.setSize(150,23);
		keyLabel.setLocation(425, 660);
		keyLabel.setOpaque(false);
		keyLabel.setForeground(Color.WHITE);
		this.add(keyLabel, 11);
		content.setLayer(keyLabel,11);

		curLevel = new JLabel(""+veril.getInventory().getLevel());
		curLevel.setSize(70, 40);
		curLevel.setFont(new Font("Arial",1,40));
		curLevel.setForeground(Color.WHITE);
		curLevel.setLocation(80,630);
		content.setLayer(curLevel,11);
		this.add(curLevel, 11);

		statBlips = new JLabel[30];
		for (int i = 0; i < 30; i++)
		{
			JLabel j = new JLabel();
			j.setSize(4,8);
			j.setLocation((i%10)*5+201, (i/10)*21+624);
			j.setIcon(new ImageIcon("images/Inventory/StatBlip.png"));
			j.setVisible(false);
			statBlips[i] = j;
			this.add(j, 11);
			content.setLayer(j,11);
		}

		bossHealthFrame = new JLabel[2];
		bossHealthBar = new JLabel[2];
		for (int i = 0; i < 2; i++)
		{
			JLabel f = new JLabel();
			JLabel b = new JLabel();
			f.setIcon(new ImageIcon("images/Inventory/BossHealthFrame.png"));
			b.setIcon(new ImageIcon("images/Inventory/BossHealthBar.png"));
			f.setBounds(595,5+(i*17),201,15);
			b.setBounds(595,5+(i*17),201,15);
			f.setVisible(false);
			b.setVisible(false);
			this.add(f, 3);
			this.add(b, 4);
			content.setLayer(f, 3);
			content.setLayer(b, 4);
			bossHealthFrame[i] = f;
			bossHealthBar[i] = b;
		}
	}
	public void setShopLabels()
	{
		shopScreen = new JLabel();
		shopScreen.setIcon(new ImageIcon("images/Shop/ShopScreen.png"));
		shopScreen.setSize(400,600);
		shopScreen.setLocation(-425, 0);
		shopScreen.setVisible(true);
		content.setLayer(shopScreen, 20);
		this.add(shopScreen, 20);

		shopPics = new JLabel[5];
		shopNames = new JLabel[5];
		shopDescs = new JLabel[5];
		shopPrices = new JLabel[5];
		for (int i = 0; i < 5; i++)
		{
			JLabel j = new JLabel();
			j.setSize(50,50);
			j.setLocation(-200,0);
			shopPics[i] = j;
			content.setLayer(j, 22);
			this.add(j, 22);

			j = new JLabel();
			j.setSize(200, 25);
			j.setLocation(-200,0);
			j.setFont(new Font("", 1, 20));
			j.setForeground(Color.WHITE);
			shopNames[i] = j;
			content.setLayer(j,22);
			this.add(j, 22);

			j = new JLabel();
			j.setSize(200, 25);
			j.setLocation(-200,0);
			j.setForeground(Color.WHITE);
			j.setFont(new Font("", Font.ITALIC, 15));
			shopDescs[i] = j;
			content.setLayer(j, 22);
			this.add(j, 22);

			j = new JLabel();
			j.setSize(60,60);
			j.setLocation(-200,0);
			j.setForeground(Color.WHITE);
			j.setFont(new Font("", 1, 30));
			j.setHorizontalAlignment((int)JLabel.CENTER_ALIGNMENT);
			j.setVerticalAlignment((int)JLabel.CENTER_ALIGNMENT);
			shopPrices[i] = j;
			content.setLayer(j,22);
			this.add(j, 22);
		}

		shopSelector = new JLabel();
		shopSelector.setSize(311,60);
		shopSelector.setLocation(-400,0);
		shopSelector.setIcon(new ImageIcon("images/Shop/Selector.png"));
		content.setLayer(shopSelector, 21);
		this.add(shopSelector, 21);
	}
	public void setTimerz()
	{
		NPCTimer = new Timer(100, new NPCTimerListener());
		invTimer = new Timer(5, new InvTimerListener());
		arrowTimer = new Timer(20, new ArrowTimerListener());
		moveTimer = new Timer(31, new MoveTimerListener());
		nightTimer = new Timer(1000, new NightTimerListener());
		shopTimer = new Timer(5, new ShopTimerListener());
		mapTimer = new Timer(5, new MapTimerListener());
		flashTimer = new Timer(75, new FlashTimerListener());
		//keyTimer = new Timer(70, new KeyTimerListener());
		//keyTimer.start();
		nightTimer.start();
		NPCTimer.start();
		itemAnimationTimer = new Timer(241, new ItemTimerListener());
	}
	public void setInvLabels()
	{
		invScreen = new JLabel();
		invScreen.setIcon(new ImageIcon("images/Inventory/InvScreen.png"));
		invScreen.setLocation(0,600);
		invScreen.setSize(800,600);
		content.setLayer(invScreen,5);
		this.add(invScreen, 5);

		invSlots = new JLabel[6];
		for (int i = 0; i < 6; i++)
		{
			JLabel j = new JLabel();
			j.setSize(50,50);
			content.setLayer(j,7);
			this.add(j, 7);
			invSlots[i] = j;
		}

		invSelector = new JLabel();
		invSelector.setSize(60,60);
		invSelector.setLocation(900,900);
		invSelector.setIcon(new ImageIcon("images/Inventory/Selector.png"));
		content.setLayer(invSelector,6);
		this.add(invSelector, 6);

		mapTiles = new JLabel[15][15];
		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				JLabel jl = new JLabel();
				jl.setSize(27,20);
				jl.setIcon(new ImageIcon("images/Inventory/GrayMap.png"));
				jl.setVisible(true);
				mapTiles[i][j] = jl;
				this.add(jl, 6);
				content.setLayer(jl,6);
			}
		}

		questNames = new JLabel[7];
		questTasks = new JLabel[7];
		for (int i = 0; i < 7; i++)
		{
			JLabel j = new JLabel();
			questNames[i] = j;
			j.setSize(200, 25);
			j.setLocation(0,800);
			j.setFont(new Font("", Font.BOLD, 20));
			j.setForeground(Color.WHITE);
			content.setLayer(j,6);
			this.add(j, 6);

			j = new JLabel();
			questTasks[i] = j;
			j.setSize(300, 20);
			j.setLocation(0,800);
			j.setFont(new Font("", Font.ITALIC, 15));
			j.setForeground(Color.WHITE);
			content.setLayer(j,6);
			this.add(j, 6);
		}

		invVeril = new JLabel(new ImageIcon("images/Inventory/VerilForInv/0.png"));
		invVeril.setBounds(30, 800, 200, 200);
		content.setLayer(invVeril, 6);
		this.add(invVeril, 6);

		invVShovel = new JLabel(new ImageIcon("images/Inventory/VerilForInv/Shovel.png"));
		invVShovel.setBounds(30, 800, 200, 200);
		invVShovel.setVisible(false);
		content.setLayer(invVShovel, 7);
		this.add(invVShovel, 7);

		invVArmor = new JLabel(new ImageIcon("images/Inventory/VerilForInv/Armor.png"));
		invVArmor.setBounds(30, 800, 200, 200);
		invVArmor.setVisible(false);
		content.setLayer(invVArmor, 7);
		this.add(invVArmor, 7);

		invVSword = new JLabel(new ImageIcon("images/Inventory/VerilForInv/Sword.png"));
		invVSword.setBounds(30, 800, 200, 200);
		invVSword.setVisible(false);
		content.setLayer(invVSword, 7);
		this.add(invVSword, 7);

		codeSnippets = new JLabel("");
		codeSnippets.setSize(500,40);
		codeSnippets.setLocation(900,900);
		codeSnippets.setFont(new Font("", Font.BOLD, 20));
		codeSnippets.setForeground(Color.WHITE);
		content.setLayer(codeSnippets,6);
		this.add(codeSnippets, 6);
	}
	public void setNotification()
	{
		notification = new JLabel();
		notification.setSize(200, 100);
		notification.setLocation(500, 5);
		notification.setVisible(false);
		content.setLayer(notification, 80);
		this.add(notification, 30);

		noteOverlay = new JLabel();
		noteOverlay.setSize(200, 100);
		noteOverlay.setLocation(500, 5);
		noteOverlay.setVisible(false);
		content.setLayer(noteOverlay, 81);
		this.add(noteOverlay, 31);

		notificationTimer = new Timer(100, new NotificationTimerListener());
	}
	public void setDeathLabels()
	{
		death = new JLabel();
		death.setLocation(0,0);
		death.setSize(800, 700);
		death.setVisible(false);
		this.add(death, 40);
		content.setLayer(death, 100);

		deathTimer = new Timer(300, new DeathTimerListener());

	}
	public void setLevelUp()
	{
		levUpScreen = new JLabel(new ImageIcon("images/Notifications/LevUp.png"));
		levUpScreen.setSize(800,200);
		levUpScreen.setLocation(800, 400);
		content.setLayer(levUpScreen, 50);
		this.add(levUpScreen, 45);

		skillLevels = new JLabel[3];
		for (int i = 0; i < 3; i++)
		{
			JLabel j = new JLabel();
			j.setSize(100,50);
			j.setLocation(800 + (200 * i), 530);
			j.setFont(new Font("", Font.BOLD, 30));
			j.setForeground(Color.WHITE);
			skillLevels[i] = j;
			content.setLayer(j,51);
			this.add(j, 46);
		}

		levUpTimer = new Timer(10, new LevelUpTimerListener());
	}
	public void setControlScreen()
	{
		controls = new JLabel();
		controls.setIcon(new ImageIcon("images/Notifications/Controls.png"));
		controls.setBounds(100,0,600,600);
		content.setLayer(controls, 100);
		this.add(controls);
	}
	public void setPromptScreen()
	{
		promptScreen = new JLabel();
		promptScreen.setBounds(200, 200, 400, 200);
		promptScreen.setVisible(false);
		content.setLayer(promptScreen, 70);
		this.add(promptScreen);

		dialogue = new JTextArea();
		dialogue.setBounds(230,230,340,140);
		dialogue.setVisible(false);
		dialogue.setForeground(Color.GREEN);
		dialogue.setFont(new Font("Arial",1,15));
		dialogue.setLineWrap(true);
		dialogue.setWrapStyleWord(true);
		dialogue.setEnabled(false);
		dialogue.setBackground(null);
		dialogue.setOpaque(false);
		content.setLayer(dialogue, 71);
		this.add(dialogue);

		promptItem = new JLabel();
		promptItem.setBounds(520,275,50,50);
		promptItem.setVisible(false);
		content.setLayer(promptItem, 72);
		this.add(promptItem);
	}
	public void setMapScreen()
	{
		mapScreen = new JLabel(new ImageIcon("images/Inventory/MapScreen.png"));
		mapScreen.setSize(772,323);
		mapScreen.setLocation(14,-350);
		mapScreen.setVisible(true);
		content.setLayer(mapScreen, 55);
		this.add(mapScreen);

		mapTiles2 = new JLabel[15][15];
		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				JLabel jl = new JLabel();
				jl.setSize(27,20);
				jl.setLocation(0,-50);
				jl.setIcon(new ImageIcon("images/Inventory/GrayMap.png"));
				jl.setVisible(true);
				mapTiles2[i][j] = jl;
				this.add(jl, 56);
				content.setLayer(jl,56);
			}
		}

		mapSelecter = new JLabel(new ImageIcon("images/Inventory/MapSelecter.png"));
		mapSelecter.setSize(313,30);
		mapSelecter.setLocation(490,-500);
		mapSelecter.setVisible(true);
		content.setLayer(mapSelecter, 57);
		this.add(mapSelecter);

		mapOptions = new JLabel[7];
		for (int i = 0; i < 7; i++)
		{
			JLabel jl = new JLabel();
			jl.setSize(300,30);
			jl.setFont(new Font("Arial",1,20));
			jl.setForeground(Color.WHITE);
			jl.setVisible(false);
			mapOptions[i] = jl;
			content.setLayer(jl, 58);
			this.add(jl);
		}
		mapLocs = new Location[7][2];
		mapOptions[0].setText("Jangbert Village");       mapLocs[0][0] = new Location(1,13); mapLocs[0][1] = new Location(7,7);
		mapOptions[1].setText("Pierport");               mapLocs[1][0] = new Location(7,10); mapLocs[1][1] = new Location(7,5);
		mapOptions[2].setText("Pinewatch Hollow");       mapLocs[2][0] = new Location(0,7);  mapLocs[2][1] = new Location(5,9);
		mapOptions[3].setText("Padady Ruins");           mapLocs[3][0] = new Location(6,5);  mapLocs[3][1] = new Location(7,7);
		mapOptions[4].setText("Summerville");            mapLocs[4][0] = new Location(3,1);  mapLocs[4][1] = new Location(7,7);
		mapOptions[5].setText("Mountain Hermit's Home"); mapLocs[5][0] = new Location(8,2);  mapLocs[5][1] = new Location(5,7);
		mapOptions[6].setText("Treasure Hunter's Guild");mapLocs[6][0] = new Location(14,14);mapLocs[6][1] = new Location(8,9);

		mapSelection = 0;
	}

//Drawing the GAME SCREEN
	public void drawGameScreen()
	{
		Location room = veril.getRoomLoc();
		drawBG(room);
		drawVeril(0);
		drawObjects(room);
		drawArrows(room);
		updateStatBar();
		drawInventory();

		//Buffer
		this.add(new JLabel("buffer"));
		repaint();
	}
	public void drawBG(Location room)
	{
		ImageIcon[][] imgs = veril.getWorld().getRoom(room).getImages();
		for (int i =0; i < bgs.length; i++)
			for (int j = 0; j < bgs[i].length; j++)
				bgs[i][j].setIcon(imgs[i][j]);

		if (veril.getWorld() != world[0] && veril.getWorld() != world[2])
			night.setVisible(false);
	}
	public void drawVeril(int step)
	{
		vLab.setIcon(veril.getImage(step));
		vLab.setLocation(veril.getLocation().getX()*Square.WIDTH, veril.getLocation().getY()*Square.HEIGHT);
	}
	public void drawObjects(Location room)
	{
		clearPastObjects(room);
		JLabel jl = null;
		ImageIcon pic = null;
		ArrayList<VerilBaseObject> vbos = veril.getRoom().getAllObjects();
		for (int i = vbos.size()-1; i >= 0; i--)
		{
			VerilBaseObject obj = vbos.get(i);
			pic = obj.getImage();
			jl = new JLabel(pic);
			jl.setLocation(obj.getLocation().getX()*50, obj.getLocation().getY()*50);
			if (pic != null)
			{
				jl.setSize(50,50);
				jl.setVisible(true);
				jl.setOpaque(false);
				content.setLayer(jl, 2);
				otherLabels.add(jl);
				this.add(jl, 2);
			}
		}
	}
	public void drawArrows(Location room)
	{
		clearPastArrows(room);
		Room r = veril.getWorld().getRoom(room);
		ArrayList<Arrow> arrows = r.getArrows();
		for (Arrow A : arrows)
		{
			JLabel jl = new JLabel();
			jl.setLocation(A.getX(), A.getY());
			jl.setSize(A.getWidth(), A.getHeight());
			jl.setIcon(A.getImage());
			jl.setVisible(true);
			content.setLayer(jl, 3);
			this.add(jl, 3);
			arrowLabels.add(jl);
		}
	}
	public void startNotification(String what)
	{
		note = what;

		notificationTimer.start();
		if (what.equals("a"))
			new AePlayWave("Music/Clips/LevUp.wav").start();
		else if (what.equals("b"))
			new AePlayWave("Music/Clips/QNew.wav").start();
		else if (what.equals("c"))
			new AePlayWave("Music/Clips/QUpdate.wav").start();
		else if (what.equals("d"))
			new AePlayWave("Music/Clips/QDone.wav").start();
		else if (what.equals("e"))
			new AePlayWave("Music/Clips/Save.wav").start();
	}
	public void drawPromptScreen()
	{
		boolean isVisible = promptType != 0;
		promptScreen.setVisible(isVisible);
		dialogue.setVisible(isVisible);
		promptItem.setVisible(isVisible);

		if (promptType == 1) //Save?
		{
			promptScreen.setIcon(new ImageIcon("images/Notifications/Save.png"));
			dialogue.setText("");
			promptItem.setIcon(null);
		}
		else if (promptType == 2) //Quit/Save
		{
			promptScreen.setIcon(new ImageIcon("images/Notifications/Quit.png"));
			dialogue.setText("");
			promptItem.setIcon(null);
		}
		else if (promptType == 3) //Dialogue
		{
			promptScreen.setIcon(new ImageIcon("images/Notifications/Dialogue.png"));
			dialogue.setText(promptData.getText());
			promptItem.setIcon(null);
		}
		else if (promptType == 4) //Dialogue w/ Items
		{
			promptScreen.setIcon(new ImageIcon("images/Notifications/Dialogue.png"));
			dialogue.setText(promptData.getText());
			promptItem.setIcon(promptData.getPic());
		}
	}

//Clearing the GAME SCREEN
	public void clearPastObjects(Location room)
	{
		for (int i = 0; i < otherLabels.size(); i++)
		{
			otherLabels.get(i).setVisible(false);
		}

		for (int i = 0; i < otherLabels.size(); i++)
		{
			this.remove(otherLabels.get(i));
		}
		otherLabels = new ArrayList<JLabel>();
	}
	public void clearPastArrows(Location room)
	{
		for (int i = 0; i < arrowLabels.size(); i++)
		{
			arrowLabels.get(i).setVisible(false);
			this.remove(arrowLabels.get(i));
		}
		arrowLabels = new ArrayList<JLabel>();
	}
	public void removeDugSpots()
	{
		veril.getRoom().removeDugSpots();
	}

//Drawing EXTRA PANELS
	public void updateStatBar()
	{
		if (veril.getWorld() == world[11])
			statBar.setIcon(new ImageIcon("images/Inventory/Codeland/StatusBar.png"));
		else
			statBar.setIcon(new ImageIcon("images/Inventory/StatusBar.png"));

		Inventory i = veril.getInventory();

		double segLength = (double) BAR_LENGTH / i.getMaxHealth();
		healthBar.setSize((int) (segLength * veril.getHealth()), 13);
		healthLabel.setText(veril.getHealth()+"/"+i.getMaxHealth());

		segLength = (double) BAR_LENGTH / (i.getLevelUpVal()-i.getPrevLevelUpVal());
		EXPBar.setSize((int) (segLength * (i.getEXP()-i.getPrevLevelUpVal())), 13);
		if (i.getLevel() < Inventory.LEVEL_CAP)
			EXPLabel.setText(i.getEXP()+"/"+i.getLevelUpVal());
		else
			EXPLabel.setText("MAX LEVEL");

		moneyLabel.setText(""+i.getMoney());
		arrowLabel.setText(""+i.getNumArrows());
		keyLabel.setText(i.getKeyAmount(Key.CIRCLE)+"   "+i.getKeyAmount(Key.TRIANGLE)+"   "+
						 i.getKeyAmount(Key.SQUARE)+"   "+i.getKeyAmount(Key.GATE));

		item1.setIcon(i.getEquipment(0).getImage());
		item2.setIcon(i.getEquipment(1).getImage());

		curLevel.setText(""+veril.getInventory().getLevel());

		for (int k = 0; k < 3; k++)
		{
			for (int j = 0; j < 10; j++)
			{
				JLabel jl = statBlips[k*10+j];
				if (k == 0 && j+1 <= veril.getInventory().getAttackBonus())
					jl.setVisible(true);
				if (k == 1 && j+1 <= veril.getInventory().getSpeedBonus())
					jl.setVisible(true);
				if (k == 2 && j+1 <= veril.getInventory().getDefenseBonus())
					jl.setVisible(true);
			}
		}

		if (veril.getRoom().getBossInRoom() != null)
		{
			Boss b = veril.getRoom().getBossInRoom();
			bossHealthFrame[0].setVisible(true);
			bossHealthBar[0].setVisible(true);
			segLength = (double) BAR_LENGTH / b.getBaseHealth();
			bossHealthBar[0].setSize((int) (segLength * b.getHealth()), 15);
		}
		else
		{
			bossHealthFrame[0].setVisible(false);
			bossHealthBar[0].setVisible(false);
		}
	}
	public void drawInventory()
	{
		String c = "";
		if (veril.getWorld() == world[11])
			c = "Codeland/";

		invScreen.setIcon(new ImageIcon("images/Inventory/"+c+"invScreen.png"));
		invSelector.setIcon(new ImageIcon("images/Inventory/"+c+"Selector.png"));

		int baseY = invScreen.getY();
		int slot = 2;
		for (int i = 0; i < invSlots.length; i++)
		{
			JLabel j = invSlots[i];
			j.setLocation(589+(65*(i%3)),baseY+((i/3)*65)+461);
			j.setIcon(veril.getInventory().getEquipment(slot++).getImage());

			if (veril.getWorld() == world[11]) j.setVisible(false);
			else							   j.setVisible(true);
		}

		invSelector.setLocation(invSlots[itemInInv].getX()-5,invSlots[itemInInv].getY()-5);

		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				mapTiles[i][j].setLocation(17+(27*i),baseY+48+(20*j));
				if (veril.getWorld() == world[0] || !veril.getWorld().isMappable())
				{
					mapTiles[i][j].setIcon(new ImageIcon("images/Inventory/"+c+"GrayMap.png"));
					if (veril.getInventory().hasEpicMap())
					{
						if (world[0].getRoom(j,i).isExplored())
							mapTiles[i][j].setVisible(false);
						if (world[0].getRoom(j,i) == veril.getRoom())
						{
							mapTiles[i][j].setVisible(true);
							mapTiles[i][j].setIcon(new ImageIcon("images/Inventory/"+c+"VerilMap.png"));
						}
					}
					else if (veril.getInventory().hasMap())
					{
						mapTiles[i][j].setIcon(new ImageIcon("images/Inventory/"+c+"GrayMap2.png"));
						if (world[0].getRoom(j,i).isExplored())
							mapTiles[i][j].setIcon(new ImageIcon("images/Inventory/"+c+"BlueMap.png"));
						if (world[0].getRoom(j,i) == veril.getRoom())
						{
							mapTiles[i][j].setVisible(true);
							mapTiles[i][j].setIcon(new ImageIcon("images/Inventory/"+c+"VerilMap2.png"));
						}
					}

				}
				else
				{
					World w = veril.getWorld();
					mapTiles[i][j].setIcon(new ImageIcon("images/Inventory/"+c+"GrayMap.png"));
					mapTiles[i][j].setVisible(true);
					final int BASEX = 4;
					final int BASEY = 5;

					if (i-BASEX < w.getCols() && i-BASEX >= 0 && j-BASEY < w.getRows() && j-BASEY >= 0)
					{
						mapTiles[i][j].setIcon(new ImageIcon("images/Inventory/"+c+"GrayMap2.png"));
						if (w.getRoom(j-BASEY,i-BASEX).isExplored())
							mapTiles[i][j].setIcon(new ImageIcon("images/Inventory/"+c+"BlueMap.png"));
						if (w.getRoom(j-BASEY,i-BASEX) == veril.getRoom())
							mapTiles[i][j].setIcon(new ImageIcon("images/Inventory/"+c+"VerilMap2.png"));
					}
				}
			}
		}

		ArrayList<Quest> q = veril.getInventory().getNonCompletedQuests();
		int s = q.size();
		s = Math.min(s,8);
		for (int i = 0; i < s; i++)
		{
			questNames[i].setText(q.get(i).getName());
			questTasks[i].setText(q.get(i).getCurTask());
			questNames[i].setLocation(500,baseY+50+(i*50));
			questTasks[i].setLocation(470,baseY+70+(i*50));
		}

		invVeril.setLocation(45, baseY + 390);
		invVShovel.setLocation(45, baseY + 390);
		invVSword.setLocation(49, baseY + 390);
		invVArmor.setLocation(49, baseY + 390);
		int Z = 0;
		Inventory i = veril.getInventory();
		if (i.hasEquipment(Equipment.TYPE_HAMMER)) Z=6;
		else if (veril.hasFastShoes()) Z=5;
		else if (i.hasEquipment(Equipment.TYPE_POKINGSTICK)) Z=4;
		else if (i.hasEquipment(Equipment.TYPE_TORCH)) Z=3;
		else if (i.hasEquipment(Equipment.TYPE_BOW)) Z=2;
		else if (i.hasEquipment(Equipment.TYPE_SWORD)) Z=1;
		invVeril.setIcon(new ImageIcon("images/Inventory/VerilForInv/"+c+Z+".png"));
		if (veril.hasGoodSword() && veril.getWorld() != world[11]) invVSword.setVisible(true);
		if (veril.hasArmor() && veril.getWorld() != world[11]) invVArmor.setVisible(true);
		if (veril.getInventory().hasEquipment(Equipment.TYPE_SHOVEL) && veril.getWorld() != world[11]) invVShovel.setVisible(true);

		codeSnippets.setLocation(320, 365 + baseY);
		codeSnippets.setText(veril.getInventory().getCodeSnippetMessage());
	}
	public void drawShopScreen()
	{
		int baseX = shopScreen.getX();
		Shop s = veril.getShop();
		for (int i = 0; i < 5; i++)
		{
			shopPics[i].setLocation(baseX + 40, 145 + (i*80));
			shopNames[i].setLocation(baseX + 90, 150 + (i*80));
			shopDescs[i].setLocation(baseX + 90, 175 + (i*80));
			shopPrices[i].setLocation(baseX + 291, 142 + (i*80));

			ShopItem si = s.getItem(i);
			if (si != null)
			{
				shopPics[i].setIcon(si.getImage());
				shopNames[i].setText(si.getName());
				shopDescs[i].setText(si.getDesc());
				shopPrices[i].setText(""+si.getPrice());
			}
			else
			{
				shopPics[i].setIcon(null);
				shopNames[i].setText("");
				shopDescs[i].setText("");
				shopPrices[i].setText("");
			}

		}
		shopSelector.setLocation(baseX + 39, 143 + (selectionInShop*80));
	}
	public void drawLevUp()
	{
		skillLevels[0].setText(""+veril.getInventory().getAttackBonus());
		skillLevels[1].setText(""+veril.getInventory().getSpeedBonus());
		skillLevels[2].setText(""+veril.getInventory().getDefenseBonus());
		if (veril.getInventory().getAttackBonus() == 10)
			skillLevels[0].setForeground(Color.YELLOW);
		if (veril.getInventory().getSpeedBonus() == 10)
			skillLevels[1].setForeground(Color.YELLOW);
		if (veril.getInventory().getDefenseBonus() == 10)
			skillLevels[2].setForeground(Color.YELLOW);
		for (int i = 0; i < 3; i++)
		{
			skillLevels[i].setLocation(levUpScreen.getX() + (300*i) + 50, 530);
		}
	}
	public void drawMapScreen()
	{
		int Y = mapScreen.getY();

		for (int i = 0; i < 15; i++)
		{
			for (int j = 0; j < 15; j++)
			{
				mapTiles2[i][j].setLocation(21+(27*i),Y+10+(20*j));
				mapTiles2[i][j].setIcon(new ImageIcon("images/Inventory/GrayMap.png"));
				mapTiles2[i][j].setVisible(true);
				if (world[0].getRoom(j,i).isExplored())
					mapTiles2[i][j].setVisible(false);
				if (new Location(i,j).equals(mapLocs[mapSelection][0]))
				{
					mapTiles2[i][j].setVisible(true);
					mapTiles2[i][j].setIcon(new ImageIcon("images/Inventory/YellowMap.png"));
				}
			}
		}
		for (int i = 0; i < 7; i++)
		{
			mapOptions[i].setLocation(465, Y+9+33*i);
			if (world[0].getRoom(mapLocs[i][0]).isExplored())
				mapOptions[i].setVisible(true);
		}
		mapSelecter.setLocation(460, Y+9+33*mapSelection);


	}

//Key Listener
	class GameKeyListener implements KeyListener
	{
		public void keyPressed(KeyEvent ke)
		{
			curKey = ke;
			//////////////////////////////////////////////////////////////
			if (ke != null)
			{
				//Removes Controls at start of game
				if (controls.isVisible() == true)
					controls.setVisible(false);

				if (!moveTimer.isRunning() && !itemAnimationTimer.isRunning())
				{
					if (!inventoryOpen && !inventoryOpening && !shopOpen && !shopOpening && !levUpOpen && !levUpOpening && !promptOpen && !mapOpen && !mapOpening && veril.getHealth() > 0)
						doGameActions(ke);
					else if (promptOpen)
						doPromptActions(ke);
					else if (inventoryOpen)
						doInvActions(ke);
					else if (shopOpen)
						doShopActions(ke);
					else if (levUpOpen)
						doLevUpActions(ke);
					else if (mapOpen)
						doMapActions(ke);
				}
			}
		}
		public void keyTyped(KeyEvent ke){}
		public void keyReleased(KeyEvent ke)
		{
			curKey = null;
		}
	}

	public void doGameActions(KeyEvent ke)
        {
                int key = ke.getKeyCode();
                boolean changeRoom = false;
                if (!veril.isTalking())
                {
                        int oldDir = veril.getDirection();
                        Location oldLoc = new Location(veril.getLocation().getX(), veril.getLocation().getY());
                        Location oldRoom = new Location(veril.getRoomLoc().getX(), veril.getRoomLoc().getY());
                        World oldWorld = veril.getWorld();

                        //WASD Movement
                        if (key == KeyEvent.VK_W)
                                changeRoom = veril.act(Actor.DIRECTION_UP);
                        if (key == KeyEvent.VK_S)
                                changeRoom = veril.act(Actor.DIRECTION_DOWN);
                        if (key == KeyEvent.VK_A)
                                changeRoom = veril.act(Actor.DIRECTION_LEFT);
                        if (key == KeyEvent.VK_D)
                                changeRoom = veril.act(Actor.DIRECTION_RIGHT);

                        //Loads new Veril info
                        int newDir = veril.getDirection();
                        Location newLoc = veril.getLocation();

                        //Draw and move Veril
                        vLab.setIcon(veril.getImage());
                        if ((key == KeyEvent.VK_W || key == KeyEvent.VK_A || key == KeyEvent.VK_S || key == KeyEvent.VK_D) && !oldLoc.equals(newLoc))
                                moveTimer.start();

                        //Checks for room change
                        if (changeRoom)
                        {
                                drawBG(veril.getRoomLoc());
                                if (!veril.getRoomLoc().equals(oldRoom))
                                {
                                        removeDugSpots();
                                        Audio.playSong(veril.getRoom().getMusicNum());

                                        Boss b = veril.getRoom().getBossInRoom();
                                        if (b != null && b.getHealth() > 0)
                                                startPrompt(new ActionResult(b.getInLine()));

                                        ActionResult a = veril.getRoom().getMessage();
                                        if (a != null) startPrompt(a);
                                }
                                drawObjects(veril.getRoomLoc());
                        }
                }

                //SPACE, LEFT, RIGHT (USING ITEMS AND INTERACTING)
                ActionResult result = new ActionResult();
                if (key == KeyEvent.VK_SPACE)
                        result = veril.getInventory().useEquipment(-1);
                if (key == KeyEvent.VK_LEFT)
                        result = veril.getInventory().useEquipment(0);
                if (key == KeyEvent.VK_RIGHT)
                        result = veril.getInventory().useEquipment(1);

                if (result != null && result.hasEquip())
                {
                        drawVeril(3);
                        if (result.getEquip().getType() == Equipment.TYPE_MIRROR && veril.getWorld().getWorldNum() == 0)
                        {
                                mapOpen = false;
                                mapOpening = true;
                                mapTimer.start();
                                NPCTimer.stop();
                                arrowTimer.stop();
                                nightTimer.stop();
                        }
                        else
                                itemAnimationTimer.start();
                }


                //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
                ///////////////////////////TESTING PURPOSES ONLY////////////////////////////////
                if (key == KeyEvent.VK_V)
                {
                        String CMD = JOptionPane.showInputDialog("Is it a secret to everyone?");
                        if (CMD.equals("iseekithtocheat"))
                                commandMode = true;
                        else
                                JOptionPane.showMessageDialog(null, "You are not in on the secret");
                }
                if (commandMode == true)
                {
                        if (key == KeyEvent.VK_O)
                        {
                                new Item(Item.TYPE_SWORD).pickUp(veril);
                                new Item(Item.TYPE_MIRROR).pickUp(veril);
                                new Item(Item.TYPE_BOW).pickUp(veril);
                                //new Item(Item.TYPE_SWIMLESSONS).pickUp(veril);
                                new Item(Item.TYPE_SHOVEL).pickUp(veril);
                                new Item(Item.TYPE_TORCH).pickUp(veril);
                                new Item(Item.TYPE_POKINGSTICK).pickUp(veril);
                                new Item(Item.TYPE_HAMMER).pickUp(veril);
                                veril.giveFastShoes();
                        }
                        if (key == KeyEvent.VK_P)
                        {
                                veril.getInventory().addEXP(5);
                        }
                        if (key == KeyEvent.VK_I)
                        {
                                String s = JOptionPane.showInputDialog("Password?");
                                if (s.equalsIgnoreCase("lovis"))
                                        new Item(Item.TYPE_LOVI, 500).pickUp(veril);
                                if (s.equalsIgnoreCase("health"))
                                        veril.getInventory().setMaxHealth(300);
                                else if (s.equalsIgnoreCase("field"))
                                        veril.setRoomLoc(new Location(6,10));
                                else if (s.equalsIgnoreCase("forest"))
                                        veril.setRoomLoc(new Location(0,7));
                                else if (s.equalsIgnoreCase("desert"))
                                        veril.setRoomLoc(new Location(8,6));
                                else if (s.equalsIgnoreCase("snow1"))
                                        veril.setRoomLoc(new Location(3,3));
                                else if (s.equalsIgnoreCase("snow2"))
                                        veril.setRoomLoc(new Location(0,0));
                                else if (s.equalsIgnoreCase("swamp"))
                                        veril.setRoomLoc(new Location(11,10));
                                else if (s.equalsIgnoreCase("mountain"))
                                        veril.setRoomLoc(new Location(10,2));
                                else if (s.equalsIgnoreCase("island"))
                                        veril.setRoomLoc(new Location(8,14));
                                else if (s.equalsIgnoreCase("square"))
                                {
                                        int x = Integer.parseInt(JOptionPane.showInputDialog("X?"));
                                        int y = Integer.parseInt(JOptionPane.showInputDialog("Y?"));
                                        veril.setLocation(new Location(x,y));
                                }
                                else if (s.equalsIgnoreCase("room"))
                                {
                                        int x = Integer.parseInt(JOptionPane.showInputDialog("X?"));
                                        int y = Integer.parseInt(JOptionPane.showInputDialog("Y?"));
                                        veril.setRoomLoc(new Location(x,y));
                                }
                                else if (s.equalsIgnoreCase("world"))
                                {
                                        int w = Integer.parseInt(JOptionPane.showInputDialog("World Num?"));
                                        veril.setWorld(world[w]);
                                }
                                else if (s.equalsIgnoreCase("people"))
                                {
                                        System.out.println(Finder.getFullList());
                                }
                                else
                                        JOptionPane.showMessageDialog(null,"Too bad");
                                drawGameScreen();
                        }
                        if (key == KeyEvent.VK_U)
                        {
                                for (int i = 0; i < 10; i++)
                                {
                                        veril.getInventory().addToAttack();
                                        veril.getInventory().addToSpeed();
                                        veril.getInventory().addToDefense();
                                }
                                veril.getInventory().setLevel(25);
                        }
                }
                //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\
                //\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\


                //Check Arrows
                if (veril.getRoom().getArrows().size() > 0)
                        arrowTimer.start();

                //Check for Boss death
                Boss b = veril.getRoom().getBossInRoom();
                if (b != null && b.getHealth() <= 0)
                {
                        ActionResult r = new ActionResult(b.getOutLine());
                        if (r.hasText())
                                startPrompt(r);
                }


                //Check for Quest Update
                Quest q = veril.getInventory().getUpdatedQuest();
                if (q != null)
                {
                        if (q.curTask() == 0)
                                startNotification("b"); //New Quest
                        else if (q.isCompleted())
                                startNotification("d"); //Quest Completed
                        else
                                startNotification("c"); //Quest Updated
                }

                //Result Label
                if (!result.hasEquip() && result.hasText())
                {
                        startPrompt(result);
                        drawObjects(veril.getRoomLoc());
                }

                //Check for UP (Inventory Open)
                if (key == KeyEvent.VK_UP)
                {
                        invTimer.start();
                        inventoryOpening = true;
                        NPCTimer.stop();
                        arrowTimer.stop();
                        nightTimer.stop();
                }

                //Check for DOWN/ESC (Saving)
                if (key == KeyEvent.VK_DOWN)
                        save();
                if (key == KeyEvent.VK_ESCAPE)
                        startPrompt(2);

                //check If Level Up
                if (veril.getInventory().isLeveledUp())
                {
                        startNotification("a");  //Level Up
                        NPCTimer.stop();
                        arrowTimer.stop();
                        nightTimer.stop();
                        levUpOpen = false;
                        levUpOpening = true;
                        levUpTimer.start();
                }

                if (veril.getRoom().isChanged())
                {
                        drawBG(veril.getRoomLoc());
                }


                //Update timer intervals
                moveTimer.setDelay(21-veril.getInventory().getSpeedBonus()*2);
                itemAnimationTimer.setDelay(101-veril.getInventory().getSpeedBonus()*10);

                //Check for DEATH
                if (veril.getHealth() == 0) beginDeathAnimation();
        }
	public void doInvActions(KeyEvent ke)
	{
			int key = ke.getKeyCode();
			String keyPressed = KeyEvent.getKeyText(ke.getKeyCode());

			if (key == KeyEvent.VK_W)
			{
				if (itemInInv >2)
					itemInInv-=3;
			}
			if (key == KeyEvent.VK_S)
			{
				if (itemInInv <3)
					itemInInv+=3;
			}
			if (key == KeyEvent.VK_A)
			{
				if (itemInInv % 3 != 0)
					itemInInv--;
			}
			if (key == KeyEvent.VK_D)
			{
				if (itemInInv % 3 != 2)
					itemInInv++;
			}

			if (key == KeyEvent.VK_LEFT)
			{
				if (sectionInInv == 0)
					veril.getInventory().swapEquipment(0,itemInInv+2);
			}
			if (key == KeyEvent.VK_RIGHT)
			{
				if (sectionInInv == 0)
					veril.getInventory().swapEquipment(1,itemInInv+2);
			}

			if (key == KeyEvent.VK_UP)
			{
				invTimer.start();
			}

			drawInventory();
			updateStatBar();
	}
	public void doShopActions(KeyEvent ke)
	{
			int key = ke.getKeyCode();
			//WS
			if (key == KeyEvent.VK_W)
			{
				selectionInShop--;
				if (selectionInShop < 0)
					selectionInShop = veril.getShop().getStockSize()-1;
			}
			if (key == KeyEvent.VK_S)
			{
				selectionInShop++;
				if (selectionInShop == veril.getShop().getStockSize())
					selectionInShop = 0;
			}
			//SPACE
			String k = KeyEvent.getKeyText(ke.getKeyCode());
			if (key == KeyEvent.VK_SPACE)
			{
				Shop s = veril.getShop();
				ShopItem si = s.getItem(selectionInShop);
				if (veril.getInventory().canAfford(si.getPrice()))
				{
					s.buyItem(veril, si);
					startPrompt(new ActionResult(si.getItem().getMessage(), si.getItem().getImage()));
					if (selectionInShop >= s.getStockSize())
						selectionInShop--;
				}
				else
					startPrompt(new ActionResult("You don't have enough Lovi's"));
			}
			//LEFT
			if (key == KeyEvent.VK_LEFT || veril.getShop().getStockSize() == 0)
			{
				shopTimer.start();
			}


			drawGameScreen();
			drawShopScreen();
			updateStatBar();
		}
	public void doLevUpActions(KeyEvent ke)
	{
		if (!levUpTimer.isRunning())
		{
			String kp = KeyEvent.getKeyText(ke.getKeyCode());
			boolean goodChoice = false;
			if (kp.equalsIgnoreCase("A"))
				goodChoice = veril.getInventory().addToAttack();
			if (kp.equalsIgnoreCase("S"))
				goodChoice = veril.getInventory().addToSpeed();
			if (kp.equalsIgnoreCase("D"))
				goodChoice = veril.getInventory().addToDefense();

			if (goodChoice)
				levUpTimer.start();
		}
	}
	public void doPromptActions(KeyEvent ke)
	{
		if (promptType == 1)
		{
			if (ke.getKeyCode() == KeyEvent.VK_LEFT)
			{
				closePrompt();
				save2();
			}
			else if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
				closePrompt();
		}
		else if (promptType == 2)
		{
			if (ke.getKeyCode() == KeyEvent.VK_LEFT)
			{
				closePrompt();
				this.dispose();
			}
			else if (ke.getKeyCode() == KeyEvent.VK_DOWN)
			{
				closePrompt();
				save2();
				this.dispose();
			}
			else if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
				closePrompt();
		}
		else if (promptType >= 3)
		{
			if (ke.getKeyCode() == KeyEvent.VK_SPACE)
			{
				closePrompt();
				Boss b = veril.getRoom().getBossInRoom();
				if (b != null)
				{
					ActionResult r = new ActionResult();

					if (b.getHealth() > 0)
						r.setText(b.getInLine());
					else
						r.setText(b.getOutLine());

					if (r.hasText())
					{
						startPrompt(r);
					}
				}
				else if (veril.isTalking())
				{
					ActionResult r = veril.getInventory().useEquipment(-1);
					if (r.hasText())
						startPrompt(r);
				}

				//Check if Shopping
				if (veril.inShop() && !shopOpen)
				{
					NPCTimer.stop();
					arrowTimer.stop();
					nightTimer.stop();
					shopOpening = true;
					shopTimer.start();
				}
			}
		}
		drawGameScreen();
		updateStatBar();
	}
	public void doMapActions(KeyEvent ke)
	{
		boolean selected = false;
		int key = ke.getKeyCode();
		if (key == KeyEvent.VK_W)
		{
			while (!selected)
			{
				mapSelection--;
				if (mapSelection == -1)
					mapSelection = 6;
				if (world[0].getRoom(mapLocs[mapSelection][0]).isExplored())
					selected = true;
			}
		}
		if (key == KeyEvent.VK_S) //(world[0].getRoom(mapLocs[i][0]).isExplored())
		{
			while (!selected)
			{
				mapSelection++;
				if (mapSelection == 7)
					mapSelection = 0;
				if (world[0].getRoom(mapLocs[mapSelection][0]).isExplored())
					selected = true;
			}
		}
		if (key == KeyEvent.VK_SPACE)
		{
			Location r = mapLocs[mapSelection][0];
			Location s = mapLocs[mapSelection][1];
			veril.setRoomLoc(r);
			veril.setLocation(s);
			mapTimer.start();
		}
		//UP
		if (key == KeyEvent.VK_UP)
			mapTimer.start();

		drawGameScreen();
		drawMapScreen();
		updateStatBar();
	}


//Timerz

	//moves People and Enemies
	class NPCTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			veril.getRoom().moveNPCs();
			drawObjects(veril.getRoomLoc());
			updateStatBar();

			if (veril.getRoom().getArrows().size() > 0)
				arrowTimer.start();

			if (veril.getHealth() == 0) beginDeathAnimation();
		}
	}

	//Creates Inventory scroll effect
	class InvTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (inventoryOpen)
			{
				invScreen.setLocation(invScreen.getX(), invScreen.getY() + 25);
				if (invScreen.getY() == 625)
				{
					invTimer.stop();
					inventoryOpen = false;
					NPCTimer.start();
					arrowTimer.start();
					nightTimer.start();
				}
			}
			if (!inventoryOpen)
			{
				invScreen.setLocation(invScreen.getX(), invScreen.getY() - 25);
				if (invScreen.getY() == 0)
				{
					invTimer.stop();
					inventoryOpen = true;
					inventoryOpening = false;
				}
			}
			drawInventory();
		}
	}

	//Moves arrows
	class ArrowTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			veril.getRoom().moveArrows();
			drawArrows(veril.getRoomLoc());
			updateStatBar();

			if (veril.getRoom().isChanged())
			{
				drawBG(veril.getRoomLoc());
				flash.setVisible(true);
				flashTimer.start();
			}


			if (veril.getRoom().getArrows().size() == 0)
				arrowTimer.stop();
		}
	}

	//Creates Veril walking animation
	class MoveTimerListener implements ActionListener
	{
		int position = 10;

		public void actionPerformed(ActionEvent e)
		{
			int d = veril.getDirection();

			if (d == Actor.DIRECTION_UP)
				vLab.setLocation(veril.getActualX(),veril.getActualY()+position*5);
			else if (d == Actor.DIRECTION_DOWN)
				vLab.setLocation(veril.getActualX(),veril.getActualY()-position*5);
			else if (d == Actor.DIRECTION_LEFT)
				vLab.setLocation(veril.getActualX()+position*5,veril.getActualY());
			else
				vLab.setLocation(veril.getActualX()-position*5,veril.getActualY());

			int step;
			if (position > 5)
				step = 1;
			else if (position > 0)
				step = 2;
			else
				step = 0;

			vLab.setIcon(veril.getImage(step));

			position--;
			if (position < 0)
			{
				position = 10;

				moveTimer.stop();
				drawVeril(step);

				if (veril.getRoom().getSquare(veril.getLocation()).isIce())
				{
					if ((!veril.hasFastShoes() && veril.canMove()) || veril.getLocation().nextSquare(veril.getDirection()) == null)
					{
						boolean cr = veril.act(veril.getDirection());
						moveTimer.start();
						if (cr) drawBG(veril.getRoomLoc());
					}
				}

			}
		}

	}

	//Simulates night/day
	class NightTimerListener implements ActionListener
	{
		int time = 0;
		boolean up = false;

		public void actionPerformed(ActionEvent e)
		{
			if (up)
				time++;
			else
				time--;

			if (time > 80)
				up = false;
			else if (time < -200)
				up = true;

			night.setIcon(new ImageIcon("images/Extra/Night/"+time+".png"));
			night.setVisible(true);
			if (time < 0)
				night.setVisible(false);
			else if (time > 5)
				night.setIcon(new ImageIcon("images/Extra/Night/"+5+".png"));

			if (veril.getWorld() == world[2])
			{
				night.setVisible(true);
				night.setIcon(new ImageIcon("images/Extra/Night/"+2+".png"));
			}
			else if (veril.getWorld() != world[0])
				night.setVisible(false);

		}
	}

	//Creates scrolling shop window effect
	class ShopTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (shopOpen)
			{
				shopScreen.setLocation(shopScreen.getX() - 25, shopScreen.getY());
				if (shopScreen.getX() == -425)
				{
					shopTimer.stop();
					shopOpen = false;
					veril.leaveShop();
					NPCTimer.start();
					arrowTimer.start();
					nightTimer.start();
				}
			}
			if (!shopOpen)
			{
				shopScreen.setLocation(shopScreen.getX() + 25, shopScreen.getY());
				if (shopScreen.getX() == 0)
				{
					shopTimer.stop();
					selectionInShop = 0;
					shopOpen = true;
					shopOpening = false;
				}
			}
			if (veril.getShop() != null)
				drawShopScreen();
		}
	}

	//Animates notifications
	class NotificationTimerListener implements ActionListener
	{
		int step = 0;

		public void actionPerformed(ActionEvent e)
		{
			String fp = "images/Notifications/";
			if (step < 4) fp += step;
			if (step >= 4) fp += "4" + note;
			notification.setIcon(new ImageIcon(fp+".png"));
			notification.setVisible(true);

			if (step == 4)
			{
				noteOverlay.setVisible(true);
				noteOverlay.setIcon(new ImageIcon("images/Notifications/OverlayA.png"));
			}
			if (step == 5)
				noteOverlay.setIcon(new ImageIcon("images/Notifications/OverlayB.png"));
			if (step == 6)
				noteOverlay.setVisible(false);


			if (step > 15)
			{
				step = 0;
				notification.setVisible(false);
				notificationTimer.stop();
			}

			step++;
		}
	}

	//Animates Death screen
	class DeathTimerListener implements ActionListener
	{
		int time = 0;
		boolean up = true;

		public void actionPerformed(ActionEvent e)
		{
			if (time == 0 && up)
				new AePlayWave("Music/Clips/Death.wav").start();

			if (up)
				time++;
			else
				time--;

			if (time > 8)
			{
				up = false;
				die();
			}
			else if (time < 0)
			{
				deathTimer.stop();
				up = true;
			}


			death.setIcon(new ImageIcon("images/Extra/Death/"+time+".png"));
			death.setVisible(true);
			if (time < 0)
				death.setVisible(false);
			else if (time > 5)
				death.setIcon(new ImageIcon("images/Extra/Death/"+5+".png"));
		}
	}

	//Scrolls level up bar
	class LevelUpTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (levUpOpen)
			{
				levUpScreen.setLocation(levUpScreen.getX() + 25, levUpScreen.getY());
				if (levUpScreen.getX() == 850)
				{
					levUpTimer.stop();
					levUpOpen = false;
					NPCTimer.start();
					arrowTimer.start();
					nightTimer.start();
				}
			}
			if (!levUpOpen)
			{
				levUpScreen.setLocation(levUpScreen.getX()-25, levUpScreen.getY());
				if (levUpScreen.getX() == 0)
				{
					levUpTimer.stop();
					levUpOpen = true;
					levUpOpening = false;
				}
			}
			drawLevUp();
		}
	}

	//Animates equipment usage
	class ItemTimerListener implements ActionListener
	{
		private boolean step = true;
		public void actionPerformed(ActionEvent e)
		{

			if (step)
			{
				drawVeril(3);
				if (veril.getInventory().getEquipLastUsed() == Equipment.TYPE_BOW)
					veril.getInventory().shootArrow();
				step = false;
			}
			else
			{
				drawVeril(0);
				step = true;
				itemAnimationTimer.stop();
			}
		}
	}

	//Creates magic crystal flash effect
	class FlashTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			flash.setVisible(false);
			flashTimer.stop();
		}
	}

	//Animates map drop-down
	class MapTimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (mapOpen)
			{
				mapScreen.setLocation(mapScreen.getX(), mapScreen.getY() - 25);
				if (mapScreen.getY() == -500)
				{
					mapTimer.stop();
					mapOpen = false;
					NPCTimer.start();
					arrowTimer.start();
					nightTimer.start();
				}
			}
			if (!mapOpen)
			{
				mapScreen.setLocation(mapScreen.getX(), mapScreen.getY() + 25);
				if (mapScreen.getY() == 0)
				{
					mapTimer.stop();
					mapOpen = true;
					mapOpening = false;
				}
			}
			drawMapScreen();
		}
	}
}


/*	class KeyTimerListener implements ActionListener
	{
		private int counter = 0;

		public void actionPerformed(ActionEvent e)
		{
			counter++;
			KeyEvent ke = curKey;
			if (ke != null)
			{
				//Removes Controls at start of game
				if (controls.isVisible() == true)
					controls.setVisible(false);

				if (!moveTimer.isRunning())
				{
					if (!inventoryOpen && !inventoryOpening && !shopOpen && !shopOpening && !levUpOpen && !levUpOpening && !promptOpen)
						doGameActions(ke);
					else if (inventoryOpen)
						doInvActions(ke);
					else if (shopOpen)
						doShopActions(ke);
					else if (levUpOpen)
						doLevUpActions(ke);
					else if (promptOpen)
						doPromptActions(ke);
				}
			}

			if (!inventoryOpen && !inventoryOpening && !shopOpen && !shopOpening && !levUpOpen && !levUpOpening && !promptOpen)
				keyTimer.setDelay(100);
			else
				keyTimer.setDelay(500);

			if (counter > 5)
			{
				drawObjects(veril.getRoomLoc());
				drawArrows(veril.getRoomLoc());
				updateStatBar();
				counter = 0;
			}
		}
	}*/

	//MASS CODING
	/*
	 *Dialog
	 *Quests
	 *Bosses
	 */

	//ENGINE
	/*
	 *T6 "death"
	 *Credits
	 */
