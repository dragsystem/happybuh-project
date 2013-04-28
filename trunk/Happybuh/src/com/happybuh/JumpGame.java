package com.happybuh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.DisplayMetrics;

public class JumpGame extends Activity {
	public Handler handler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_game);
        GV.Activities.jumpgame = this;
        GV.Instancies.jumpview = (JumpGameSurfaceView) findViewById(R.id.jumpgameview);
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		GV.Screen.wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GameOnLock");
		
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
			}
		};
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		GV.Screen.wl.release();
    	GV.Instancies.jumpview.stopthread();
    	super.onPause();
	}

	@Override
	protected void onResume() {
		GV.Screen.metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(GV.Screen.metrics);
		GV.Screen.wl.acquire();
		GV.Instancies.jumpview.startthread();
		super.onPostResume();
	}

    

    
}
