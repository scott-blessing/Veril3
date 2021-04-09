package GUI;

import javax.swing.*;
import GUI.Veril3GameScreenFrame;
import GUI.AePlayWave;
import GUI.Audio;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.event.*;
import javax.swing.JOptionPane;
import Rooms.World;

public class Veril3MenuFrame extends JFrame
{
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;

	public static final int MENU_NEW_GAME = 0;
	public static final int MENU_LOAD_GAME = 1;
	public static final int MENU_CREDITS = 2;

	private JLayeredPane content;
	private int selectedItem;
	private JLabel bg;
	private String bgL;
	private JLabel lc; //small icon in bottom-right
	private JLabel lc1;//large icon
	private JLabel lc2;//text
	private Timer lcTimer;
	private Timer animation;

	public Veril3MenuFrame(String bgl)
	{
		super("Veril 3: Death to the Programmers");
		setBackground(Color.BLACK);
		bgL = bgl;
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setResizable(false);
		setDefaultLookAndFeelDecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/Extra/v3.png"));
		content = new JLayeredPane();
		setContentPane(content);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((screenSize.width / 2) - (SCREEN_WIDTH / 2), (screenSize.height / 2) - (SCREEN_HEIGHT / 2)));


		lc1 = new JLabel(new ImageIcon("images/LC/big.png"));
		lc1.setSize(500,500);
		lc1.setLocation(150,10);
		lc1.setVisible(true);
		content.setLayer(lc1,0);
		add(lc1);

		lc2 = new JLabel();
		lc2.setText("Limited Creativity Studios");
		lc2.setSize(500,60);
		lc2.setLocation(215,510);
		lc2.setForeground(Color.WHITE);
		lc2.setFont(new Font("Limited Creativity Studios",1,30));
		content.setLayer(lc2,1);
		add(lc2);

		setSize(SCREEN_WIDTH,SCREEN_HEIGHT+1);

		lcTimer = new Timer(100, new LCTimerListener());
		lcTimer.start();
		animation = new Timer(700, new LoadAnimationListener());

		Audio.playMusic("Menu.wav");
	}

	private void setUpBG()
	{
		selectedItem = MENU_NEW_GAME;

		bg = new JLabel();
		bg.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		bg.setLocation(0,0);
		this.add(bg);
		content.setLayer(bg, 2);

		lc = new JLabel(new ImageIcon("images/LC/small.png"));
		lc.setSize(25,25);
		lc.setLocation(740, 525);
		this.add(lc);
		content.setLayer(lc, 3);

		updateBG();
		addKeyListener(new menuKeyListener());
	}

//Menu Options
	private void chooseSelected()
	{
		if (selectedItem == MENU_NEW_GAME)
		{
			startNewGame();
		}
		else if (selectedItem == MENU_LOAD_GAME)
		{
			loadSavedGame();
		}
		else
		{
			launchCredits();
		}
	}

	private void startNewGame()
	{
		startLoadAnimation();
	}
	public void startGame()
	{
		Veril3GameScreenFrame v3gsf = new Veril3GameScreenFrame(true, "");
		dispose();
	}
	private void loadSavedGame()
	{
		try
		{
			String filename;
			JFileChooser fileChooser = new JFileChooser("saves");

			int returnVal = fileChooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				filename = file.getName();
			}
			else
				filename = "";

			FileInputStream fileIn = new FileInputStream("saves/"+filename);
	        ObjectInputStream in = new ObjectInputStream(fileIn);

	        World[] w = (World[])in.readObject();
	        Veril3GameScreenFrame V3GSF = new Veril3GameScreenFrame(false, filename);

	        in.close();
	        fileIn.close();
	        dispose();

	        V3GSF.load(w);
	        V3GSF.save2();
		}
		catch (ClassNotFoundException e)
		{
            JOptionPane.showMessageDialog(null, "WTF Happened?");
		}
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "That save file doesn't exist");
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Try picking a file next time");
        }
		Audio.stopMusic();
	}
	private void launchCredits()
	{
		JOptionPane.showMessageDialog(null, "Coding: Scott Blessing & Ryan Kelch\n" +
											"Artwork and Sound: Ryan Haskell\n" +
											"Music: Jake Schwinger\n" +
											"Testing: Nick Kirk\n\n" +
											"SPECIAL THANKS TO NINTENDO FOR CREATING \"The Legend of Zelda\"\n\n" +
											"2011-2012 Limited Creativity Studios",
										    "Please Give These People your Admiration (and Money! (Especially Scott))",
										    JOptionPane.PLAIN_MESSAGE,
										    new ImageIcon("images/LC/small.png"));
	}


	public void updateBG()
	{
		String fp = "images/Menu/";
		fp += "" + selectedItem + bgL;
		fp += ".png";
		bg.setIcon(new ImageIcon(fp));
	}

	public void startLoadAnimation()
	{
		bg.setVisible(false);
		lc.setVisible(false);
		lc1.setVisible(true);
		lc2.setSize(900,60);
		lc2.setLocation(160,310);
		lc2.setText("");
		lc1.setIcon(new ImageIcon("images/Veril/Red/Down/0.png"));
		this.setForeground(Color.WHITE);
		animation.start();
	}

//Key Listener
	class menuKeyListener implements KeyListener
	{
		public void keyPressed(KeyEvent ke)
		{
			if (!animation.isRunning())
			{
				if (ke.getKeyCode() == ke.VK_DOWN || ke.getKeyCode() == ke.VK_S)
					selectedItem++;
				else if (ke.getKeyCode() == ke.VK_UP || ke.getKeyCode() == ke.VK_W)
					selectedItem--;

				if (selectedItem < MENU_NEW_GAME)
					selectedItem = MENU_CREDITS;
				else if (selectedItem > MENU_CREDITS)
					selectedItem = MENU_NEW_GAME;

				if (ke.getKeyCode() == ke.VK_SPACE || ke.getKeyCode() == ke.VK_ENTER)
					chooseSelected();

				if (KeyEvent.getKeyText(ke.getKeyCode()).equalsIgnoreCase("V"))
				{
					Veril3LocationFinder v3lf = new Veril3LocationFinder();
					dispose();
					Audio.stopMusic();
				}

				if (KeyEvent.getKeyText(ke.getKeyCode()).equalsIgnoreCase("p"))
					startGame();

				updateBG();
			}
		}

		public void keyTyped(KeyEvent ke) {}
		public void keyReleased(KeyEvent ke) {}
	}

	class LCTimerListener implements ActionListener
	{
		private int i = 0;

		public void actionPerformed(ActionEvent e)
		{
			i++;

			lc1.setIcon(new ImageIcon("images/LC/big.png"));
			lc2.setText("Limited Creativity Studios");

			if (i == 25)
			{
				setUpBG();
				lcTimer.stop();
			}
		}
	}

	class LoadAnimationListener implements ActionListener
	{
		int c = 0;
		public void actionPerformed(ActionEvent e)
		{
			c++;
			if (c==1)
				lc2.setText("Loading Verily Stuffs");
			if (c==2)
				lc2.setText("Assembling Worlds");
			if (c==3)
				lc2.setText("Wreaking Havok");
			if (c==4)
				lc2.setText("Bribing Police");
			if (c==5)
				lc2.setText("Generating Enemies");
			if (c==6)
				lc2.setText("Adding 0's and 1's");
			if (c==7)
				lc2.setText("Drawing Veril");
			if (c==8)
				lc2.setText("Downloading Ads to Your Hard Drive");
			if (c==9)
				lc2.setText("Expecting Game to Load");
			if (c==10)
				lc2.setText("Adding Obstacles");
			if (c==11)
				lc2.setText("Randomizing Variables");
			if (c==12)
				lc2.setText("Unrandomizing Constants");
			if (c==13)
				lc2.setText("Watching TV");
			if (c==14)
				lc2.setText("Wondering When Veril will Actually Load");
			if (c==15)
				lc2.setText("Imbuing Enemies with Hatred");
			if (c==16)
				lc2.setText("Done!");
			if (c==17)
			{
				animation.stop();
				Audio.stopMusic();
				startGame();
			}

			lc1.setIcon(new ImageIcon("images/Veril/Red/Down/"+(c % 3)+".png"));
		}
	}

}
