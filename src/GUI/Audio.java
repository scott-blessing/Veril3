package GUI;


import java.applet.*;

public class Audio
{
    static AudioClip bgMusic;
    static boolean isPlaying;
    static String lastPlayed;

	public static void playSong(int num)
	{
			playMusic(getSongName(num));
	}

	private static String getSongName(int n)
	{
		switch (n)
		{
			case 0:
				return "Overworld.mid";
			case 1:
				return "Cave.mid";
			case 2:
				return "House.mid";
			case 3:
				return "Temple.mid";
			case 4:
				return "Boss.mid";
			default:
				return "Overworld.mid";
		}

	}


    public static void playMusic(String file)
    {
    	if (!file.equals(lastPlayed))
    	{
    		if (isPlaying) stopMusic();
    		try
    		{
	    		bgMusic = Applet.newAudioClip(Audio.class.getResource("/Music/"+file));
	    		bgMusic.loop();
    		}
    		finally{}
    	}
        lastPlayed = file;
        isPlaying = true;
    }

    public static void loop()
    {
    	bgMusic.loop();
    }

    public static void stopMusic()
    {
        bgMusic.stop();
        isPlaying = false;
    }

    public static void toggleMute()
    {
        if(isPlaying) stopMusic();
        else playMusic(lastPlayed);
    }

    public static void playSound(String file)
    {
        Applet.newAudioClip(Audio.class.getResource("/Music/Clips/"+file+".wav")).play();
    }
}