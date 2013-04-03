package com.happybuh;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {
	Thread logoTimer;
	boolean stopped;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView myImage = (ImageView) findViewById(R.id.buh);
        //myImage.setAlpha(127);
        myImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.carga_fantasma));
        ImageView myImage2 = (ImageView) findViewById(R.id.presenta);
        myImage2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.presentacion));
        
        stopped = false;
		logoTimer = new Thread(){
			public void run(){
				try{
					sleep(4000);
					if(!stopped) {
						Intent i = new Intent("com.happybuh.HABITACION");
						startActivity(i);
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
}