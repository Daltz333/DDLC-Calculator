package Calculator.Logic;

import Calculator.Logic.Hacks;

public class MusicPlayer implements Runnable {

    private boolean stop = false;
    
    Hacks hacker = new Hacks();
    
    public MusicPlayer() {
    	System.out.println("Buffering Music");
    	hacker.bufferMusic();
    	
    }

    public void run() {
    	System.out.println("Initialization of Player");
    	while (!stop) {
    		if (!hacker.isPlayingMusic()) {
    			System.out.println("Play Music");
    			hacker.playMusic();
    			
    		} 
	    	
    	} 
        
    }
    
    public boolean isThreadRunning() {
    	return !stop;
    	
    }
    
    public void stop() {
    	stop = true;
    	hacker.stopMusic();
    	
    }

}
