package Calculator.Logic;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

public class Hacks {
    URL url;
    URLConnection con;
    DataInputStream dis;
    FileOutputStream fos;
    byte[] fileData;

    //gets the current path of the file system
    private String getCurrentPath() {
        return new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath();

    }
    
    public void downloadResources() {
    	File monika = new File(getCurrentPath() + "\\" + "Monika.png");
    	File music = new File(getCurrentPath() + "\\" + "Sayori.mp3");
    	
    	System.out.println("Check if resources exist");
    	if (!monika.exists()) {
	    	System.out.println("Retrieve Wallpaper Image");
	    	retrieveFile("https://i.redd.it/zpilkwhfpy901.jpg", "Monika.png");
	    	
    	}
    	
    	if (!music.exists()) {
    		System.out.println("Retrieve Music");
        	retrieveFile("http://daltz-network.softether.net/Sayori.mp3", "Sayori.mp3");
        	
    	}
   
    }

    private void retrieveFile(String fileToGet, String name) {
        String filePath = getCurrentPath() + "\\" + name;
        System.out.println("Current Path to paste is: " + filePath);
        try {
            url = new URL(fileToGet); //File Location goes here
            System.out.println("Download URL: " + url.toString());
            con = url.openConnection(); // open the url connection.
            dis = new DataInputStream(con.getInputStream());
            fileData = new byte[con.getContentLength()];
            for (int q = 0; q < fileData.length; q++) {
                fileData[q] = dis.readByte();
            }
            dis.close(); // close the data input stream
            System.out.println("Write File");
            fos = new FileOutputStream(new File(filePath)); //FILE Save Location goes here
            fos.write(fileData);  // write out the file we want to save.
            fos.close(); // close the output stream writer

        } catch(Exception m) {
            System.out.println(m);

        }

    }

    public void changeWallpaper() {
        String filePath = getCurrentPath() + "\\" + "Monika.png";
        String path = filePath;

        SPI.INSTANCE.SystemParametersInfo(
                new UINT_PTR(SPI.SPI_SETDESKWALLPAPER),
                new UINT_PTR(0),
                path,
                new UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));

    }

    MediaPlayer mediaPlayer;
    boolean isPlaying = false;
    public void playMusic() {
    	System.out.println("Playing Music");
        mediaPlayer.play();
        isPlaying = true;

    }
    
    public void bufferMusic() {
    	System.out.println("Buffering Music");
        String filePath = getCurrentPath() + "\\" + "Sayori.mp3";
        Media hit = new Media(new File(filePath).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        mediaPlayer.pause();
    }

    public void stopMusic() {
        isPlaying = false;
        mediaPlayer.stop();

    }

    public boolean isPlayingMusic() {
        return isPlaying;

    }

    public void showDesktop() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_WINDOWS);

    }

    private interface SPI extends StdCallLibrary {
        long SPI_SETDESKWALLPAPER = 20;
        long SPIF_UPDATEINIFILE = 0x01;
        long SPIF_SENDWININICHANGE = 0x02;

        SPI INSTANCE = Native.loadLibrary("user32", SPI.class, new HashMap<String, Object>() {
			private static final long serialVersionUID = (long) 0.0;

		{
            put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
            put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
        }
        });

        boolean SystemParametersInfo(
                UINT_PTR uiAction,
                UINT_PTR uiParam,
                String pvParam,
                UINT_PTR fWinIni
        );

    }

}