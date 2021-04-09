package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Veril3CreditsFrame extends JFrame
{
	private JLayeredPane content;
	private Timer creditTimer;
	private JLabel bg;
	private JLabel text;
	private JLabel text2;

	public Veril3CreditsFrame(double percent)
	{
		super("Veril 3: Death to the Programmers");
		setBackground(Color.BLACK);
		setSize(820,720);
		setResizable(false);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/Extra/v3.png"));
		setForeground(Color.BLACK);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(new Point((screenSize.width / 2) - (800 / 2), (screenSize.height / 2) - (700 / 2)-40));

		content = new JLayeredPane();
		this.setContentPane(content);
		content.setSize(800, 700);

		String s = "" + percent;
		if (s.length() >= 5)
			s = s.substring(0, 5);


		text = new JLabel("Your completion percentage: " + s + "%");
		text.setFont(new Font("", Font.BOLD, 40));
		text.setForeground(Color.WHITE);
		text.setBounds(50,100,700,100);
		text.setVisible(false);
		this.add(text, 0);
		content.setLayer(text, 2);

		text2 = new JLabel();
		s = "YOU ARE A VERIL GOD!!!";
		if (percent < 10) s = "No heart run?  Good job!";
		else if (percent < 20) s = "You screwed up a lot...";
		else if (percent < 30) s = "What is this? I don't even...";
		else if (percent < 40) s = "Just sad";
		else if (percent < 50) s = "Main quest only, huh?";
		else if (percent < 60) s = "At least you're past 50...";
		else if (percent < 70) s = "Adventurer";
		else if (percent < 80) s = "True Adventurer";
		else if (percent < 90) s = "Master Adventurer";
		else if (percent < 100) s = "Almost a Veril God";
		text2.setText(s);
		text2.setFont(new Font("", Font.BOLD, 40));
		text2.setForeground(Color.RED);
		text2.setBounds(50,150,700,100);
		text2.setVisible(false);
		this.add(text2, 0);
		content.setLayer(text2, 1);

		bg = new JLabel(new ImageIcon("images/Credits/0.png"));
		bg.setSize(800,700);
		bg.setVisible(true);
		this.add(bg, 1);
		content.setLayer(bg, 0);

		setVisible(true);

		creditTimer = new Timer(2000, new CreditTimerListener());
		creditTimer.start();
		Audio.playMusic("Menu.wav");
	}

	class CreditTimerListener implements ActionListener
	{
		private int cnt = 0;

		public void actionPerformed(ActionEvent e)
		{
			cnt++;
			if (cnt > 8)
				bg.setIcon(new ImageIcon("images/Credits/0.png"));
			else
				bg.setIcon(new ImageIcon("images/Credits/"+cnt+".png"));

			if (cnt == 10)
			{
				creditTimer.stop();
				text.setVisible(true);
				text2.setVisible(true);
			}
		}
	}


}
