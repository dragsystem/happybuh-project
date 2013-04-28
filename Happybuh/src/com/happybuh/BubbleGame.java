package com.happybuh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BubbleGame extends Activity {
	
	public Handler handler;
	public RelativeLayout rl, rl_pause;
	public TextView tv;
	public Button b;
	public Typeface type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_game);
        GV.Activities.bubblegame = this;
        GV.Instancies.bubbleview = (BubbleGameSurfaceView) findViewById(R.id.bubblegameview);
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		GV.Screen.wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GameOnLock");
		
		type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
		editar_estilo();
		
		rl_pause = (RelativeLayout)findViewById(R.id.ventana_pause);
		
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 1) resta_vida();
				if(msg.what == 2) gana_coins();
				if(msg.what == 3) game_over();
			}
			
		};
    }
    
    public void game_over() {
    	User_Info.actualizar(getApplicationContext(), GV.puntuacio_bubble.get_exp, GV.puntuacio_bubble.coins);
    	 rl = (RelativeLayout)findViewById(R.id.ventana_gameover);
    	rl.setVisibility(View.VISIBLE);
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

	public void retry (View v) {
		rl.setVisibility(v.GONE);
		try{
			Intent i = new Intent("com.happybuh.BUBBLEGAME");
			startActivity(i);
		}
		finally{
			finish();
		}
	}
	
	public void cancel (View v) {
		rl.setVisibility(v.GONE);
		finish();
	}
	
	public void pause(View v) {
		rl_pause.setVisibility(v.VISIBLE);
		GV.puntuacio_bubble.pause = 1;
	}
	
	public void continua(View v) {
		rl_pause.setVisibility(v.GONE);
		GV.puntuacio_bubble.pause = 0;
	}
	
	public void exit(View v) {
		User_Info.actualizar(getApplicationContext(), GV.puntuacio_bubble.get_exp, GV.puntuacio_bubble.coins);
		finish();
	}
	public void editar_estilo() {
		tv = (TextView)findViewById(R.id.bubble_coins);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.bubble_getcoins);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.bubble_lives);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.bubble_getlives);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.tv_gameover);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.texto_pause);
		tv.setTypeface(type);
		b = (Button)findViewById(R.id.b_gameover_retry);
		b.setTypeface(type);
		b = (Button)findViewById(R.id.b_gameover_cancel);
		b.setTypeface(type);
		b = (Button)findViewById(R.id.boton_cont);
		b.setTypeface(type);
		b = (Button)findViewById(R.id.boton_exit);
		b.setTypeface(type);
	}
    
}
