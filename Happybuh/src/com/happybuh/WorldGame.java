package com.happybuh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WorldGame extends Activity {
	public Handler handler;
	public RelativeLayout rl, rl_pause;
	public TextView tv;
	public Button b;
	private Typeface type;
	public VG_Database db;
	private SoundPool sndPool;
	private float rate = 1.0f;
	private float masterVolume = 1.0f;
	private float leftVolume = 1.0f;
	private float rightVolume = 1.0f;
    private float balance = 0.5f;
	public ImageButton izquierda, derecha;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GV.Activities.worldgame = this;
        setContentView(R.layout.activity_world_game);
        
        db = new VG_Database(getApplicationContext());
        
        GV.puntuacio_world.gameover = 0;
        GV.Instancies.worldview = (WorldGameSurfaceView) findViewById(R.id.worldgameview);
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		GV.Screen.wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GameOnLock");
				
		db = new VG_Database(getApplicationContext());
		sndPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 100);
		
		type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
		editar_estilo();
		
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 1) resta_vida();
				if(msg.what == 2) gana_coins();
				if(msg.what == 3) game_over();
				if(msg.what == 4) moneda_sound();
			}
		};
		
		rl_pause = (RelativeLayout)findViewById(R.id.ventana_pause);
		
		izquierda = (ImageButton) findViewById(R.id.botonizq);
		izquierda.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
					GV.puntuacio_world.control = 3;
					return true;
				}
				else if(event.getActionMasked() == MotionEvent.ACTION_UP) {
					GV.puntuacio_world.control = 0;
					return true;
				}
				return true;
			}
		});
		
		derecha = (ImageButton) findViewById(R.id.botonder);		
		derecha.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
//					GV.Instancies.desplazamiento = 10;
					GV.puntuacio_world.control = 1;
					return true;
				}
				else if(event.getActionMasked() == MotionEvent.ACTION_UP) {
					GV.puntuacio_world.control = 0;
//					GV.Instancies.desplazamiento = 0;
					return true;
				}
				return true;
			}
		});
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_world_game, menu);
        return true;
    }

    @Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		GV.Screen.wl.release();
    	GV.Instancies.worldview.stopthread();
    	super.onPause();
	}

	@Override
	protected void onResume() {
		GV.Screen.metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(GV.Screen.metrics);
		GV.Screen.wl.acquire();
		GV.Instancies.worldview.startthread();
		super.onPostResume();
	}
	
	
	public void mov_jump (View v) {
		if (GV.puntuacio_world.csalto == 0) {
			GV.puntuacio_world.salto = 1;
//			Log.v("Boton Salto", "activo salto"+GV.Instancies.salto);
			GV.puntuacio_world.csalto = 1;
		}
	}
	
	
	public void moneda_sound () {
		int id = sndPool.load(getApplicationContext(), R.raw.coin, 1);
		sndPool.play(id, leftVolume, rightVolume, 1, 0, rate);
	}
    public void game_over() {
    	rl = (RelativeLayout)findViewById(R.id.ventana_gameover);
    	rl.setVisibility(View.VISIBLE);
    }
    
    public void gana_coins() {
    	TextView tv = (TextView) findViewById(R.id.bubble_getcoins);
    	tv.setText(String.valueOf(GV.puntuacio_world.coins));
    }
    
    public void resta_vida() {
    	TextView tv = (TextView) findViewById(R.id.bubble_getlives);
    	tv.setText(String.valueOf(GV.puntuacio_world.vides));
    }

	public void retry (View v) {
		rl.setVisibility(v.GONE);
		try{
			Intent i = new Intent("com.happybuh.WORLDGAME");
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
		GV.puntuacio_world.pause = 1;
	}
	
	public void continua(View v) {
		rl_pause.setVisibility(v.GONE);
		GV.puntuacio_world.pause = 0;
	}
	
	public void exit(View v) {
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
	@Override
	public void onBackPressed() {
		rl_pause.setVisibility(View.VISIBLE);
		GV.puntuacio_world.pause = 1;
	}
}
