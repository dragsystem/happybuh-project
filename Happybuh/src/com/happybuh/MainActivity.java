package com.happybuh;


import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Thread logoTimer;
	boolean stopped;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		GV.Screen.wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GameOnLock");
        
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        ImageView myImage = (ImageView) findViewById(R.id.buh);
        //myImage.setAlpha(127);
        myImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.carga_fantasma));
        ImageView myImage2 = (ImageView) findViewById(R.id.presenta);
        myImage2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.presentacion));
        
        stopped = false;
		logoTimer = new Thread(){
			public void run(){
				try{
					sleep(2000);
					if(!stopped) {
						final TextView user = (TextView)findViewById(R.id.new_user);
						
						VG_Database info = new VG_Database(MainActivity.this);
						info.open();
						ArrayList a = info.info_user();
						info.setUserCoins(10000);
						info.setUserLvl(30);
						info.close();
						if(a.isEmpty()) {
							//poner el dialog alert del tutorial
							Intent i = new Intent("com.happybuh.NEW_USER");
							startActivity(i);
						}
						else {
							Intent i = new Intent("com.happybuh.HABITACION");
							startActivity(i);
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally{
					finish();
				}
			}
		};
		logoTimer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
	protected void onStop() {
		super.onStop();
		stopped = true;
		//fer join del thread en el cas de que no estigues dormint
	}
	
	@Override
	public void onBackPressed() {
		finish();
		//activity transition animation
		super.onBackPressed();
	}
	
	@Override
	protected void onPause() {
		GV.Screen.wl.release();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		GV.Screen.metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(GV.Screen.metrics);
		GV.Screen.wl.acquire();
	}
}