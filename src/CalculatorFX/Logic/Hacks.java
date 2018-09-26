package CalculatorFX.Logic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT_PTR;
import com.sun.jna.win32.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Hacks {
    URL url;
    URLConnection con;
    DataInputStream dis; 
    FileOutputStream fos; 
    byte[] fileData;
    
    private String getCurrentPath() {
        return new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath();

    }
    
    public void retrieveFile(String fileToGet, String name) {
    	
    	System.out.println("Before get current path");
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
    	System.out.println("Before get file");
    	retrieveFile("https://i.redd.it/zpilkwhfpy901.jpg", "Monika.png");
    	System.out.println("After get file");
        //supply your own path instead of using this one
    	String filePath = getCurrentPath() + "\\Monika.png";
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
    	retrieveFile("http://daltz-network.softether.net/Sayori.mp3", "Sayori.mp3");
    	String filePath = getCurrentPath() + "\\Sayori.mp3";
    	String bip = filePath;
    	Media hit = new Media(new File(bip).toURI().toString());
    	mediaPlayer = new MediaPlayer(hit);
    	isPlaying = true;
    	mediaPlayer.play();
    	
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

    public interface SPI extends StdCallLibrary {
        //from MSDN article
        long SPI_SETDESKWALLPAPER = 20;
        long SPIF_UPDATEINIFILE = 0x01;
        long SPIF_SENDWININICHANGE = 0x02;

        SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<String, Object>() {{
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