package com.happybuh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.Menu;
import android.widget.TextView;

public class BubbleGame extends Activity {
	
	public Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_game);
        GV.Activities.bubblegame = this;
        GV.Instancies.bubbleview = (BubbleGameSurfaceView) findViewById(R.id.bubblegameview);
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		GV.Screen.wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GameOnLock");
		
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 1) resta_vida();
				if(msg.what == 2) gana_coins();
			}
			
		};
    }
    
    public void gana_coins() {
    	TextView tv = (TextView) findViewById(R.id.bubble_getcoins);
    	tv.setText(String.valueOf(GV.puntuacio_bubble.coins));
    }
    
    public void resta_vida() {
    	TextView tv = (TextView) findViewById(R.id.bubble_getlives);
    	tv.setText(String.valueOf(GV.puntuacio_bubble.vides));
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
