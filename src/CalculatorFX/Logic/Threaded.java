package CalculatorFX.Logic;

public class Threaded implements Runnable {

    private int var;
    private boolean stop = false;
    
    Hacks hacker = new Hacks();

    public Threaded(int var) {
        this.var = var;
        
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
