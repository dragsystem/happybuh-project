package com.happybuh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;

public class BubbleGame extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_game);
        GV.Instancies.bubbleview = (BubbleGameSurfaceView) findViewById(R.id.bubblegameview);
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		GV.Screen.wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GameOnLock");
    }

    @Override
	protected void onPause() {
    	GV.Screen.wl.release();
    	GV.Instancies.bubbleview.stopthread();
    	super.onPause();
	}

	@Override
	protected void onResume() {
		getWindowManager().getDefaultDisplay().getMetrics(GV.Screen.metrics);
		GV.Screen.wl.acquire();
		GV.Instancies.bubbleview.startthread();
		super.onPostResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bubble_game, menu);
        return true;
    }

    
}
