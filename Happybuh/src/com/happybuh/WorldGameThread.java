package com.happybuh;

import android.graphics.Canvas;
import android.util.Log;

public class WorldGameThread extends Thread {
	private WorldGameSurfaceView view;
	static final long FPS = 30;
    private boolean running = false;
   
    public WorldGameThread(WorldGameSurfaceView view) {
          this.view = view;
    }
    
    public boolean isRunning() {
    	return running;
    }
    
    public void setRunning(boolean run) {
          running = run;
    }

    @Override
	public void run() {
    	long ticksPS = 1000/FPS;
    	long startTime;
    	long sleepTime;
    	//fps checker
    	long contms=0;
    	long lasttimecheck = System.currentTimeMillis();
    	int fps=0;
		while (running) {
			long time =  System.currentTimeMillis();
			if(contms>1000) {
				Log.v("FPS GAME",String.valueOf(fps));
				contms=time-lasttimecheck;
				fps=1;
			}
			else {
				fps++;
				contms+=time-lasttimecheck;
			}
			lasttimecheck = time;
			
			Canvas c = null;
			startTime =time;
			try {
				c = view.getHolder().lockCanvas();
				synchronized (view.getHolder()) {
					view.onDraw(c);
				}
			} finally {
				if (c != null) {
					view.getHolder().unlockCanvasAndPost(c);
				}
			}
			sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
			try {
                if (sleepTime > 10)
                       sleep(sleepTime);
                else {
                	sleep(10);
                }
			} catch (Exception e) {}
		}
	}
}
