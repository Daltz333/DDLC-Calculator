package Calculator.Logic;

import Calculator.Logic.Hacks;

public class Threaded implements Runnable {

    private boolean stop = false;
    
    Hacks hacker = new Hacks();

    public Threaded() {
        
    }

    public void run() {
    	while (!stop) {
    		if (!hacker.isPlayingMusic()) {
    			hacker.playMusic();
    			
    		} else {
    			
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
