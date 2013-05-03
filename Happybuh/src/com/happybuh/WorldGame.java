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
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WorldGame extends Activity {
	public Handler handler;
	public RelativeLayout rl, rl_pause, rl_final;
	public TextView tv, tv_resultado;
	public Button b;
	private Typeface type;
	public VG_Database db;
	private SoundPool sndPool;
	private float rate = 1.0f;
	private float masterVolume = 1.0f;
	private float leftVolume = 1.0f;
	private float rightVolume = 1.0f;
    private float balance = 0.5f;
	public ImageView izquierda, derecha, salto;
	public LinearLayout ll;
	public Chronometer cc;
	public int sec, min;
	private Long lastPause; 
	private int para_contador;
    
    
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
		
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 1) resta_vida();
				if(msg.what == 2) gana_coins();
				if(msg.what == 3) game_over();
				if(msg.what == 4) moneda_sound();
				if(msg.what == 5) llega_casa();
			}

		};
		tv_resultado = (TextView)findViewById(R.id.resultado);
		rl_final = (RelativeLayout)findViewById(R.id.rl_final);
		rl_pause = (RelativeLayout)findViewById(R.id.ventana_pause);
		izquierda = (ImageView) findViewById(R.id.botonizq);
		derecha = (ImageView) findViewById(R.id.botonder);	
		ll = (LinearLayout)findViewById(R.id.ll_worldgame);
		salto = (ImageView)findViewById(R.id.salto);
		cc = (Chronometer)findViewById(R.id.contador);
		cc.setTypeface(type);
		cc.setBase(SystemClock.elapsedRealtime());
		cc.start();
		sec = 0;
		min = 0;
		para_contador = 0;
		cc.setOnChronometerTickListener(new OnChronometerTickListener() {
			
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				if(para_contador == 0){
					++sec;
					if(sec == 60){
						min++;
						sec = 0;
					}
				}
			}
		});
		editar_estilo();
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            GV.World_mov.izq_right = izquierda.getRight();
            GV.World_mov.der_left = derecha.getLeft();
            GV.World_mov.ll_bot = ll.getBottom();
            GV.World_mov.ll_left = ll.getLeft();
            GV.World_mov.ll_right = ll.getRight();
            GV.World_mov.ll_top = ll.getTop();
            GV.World_mov.s_left = salto.getLeft();
    
            GV.World_mov.s_right = salto.getRight();
            GV.World_mov.s_bot = salto.getBottom();
            GV.World_mov.s_top = salto.getTop();
            
        }
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
    	lastPause = SystemClock.elapsedRealtime();
    	cc.stop();
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
	
	public void llega_casa() {
		para_contador = 1;
		lastPause = SystemClock.elapsedRealtime();
    	cc.stop();
    	GV.puntuacio_world.get_exp = 2 +(GV.puntuacio_world.num_monedas - min);
    	rl_final.setVisibility(View.VISIBLE);
    	String res = "¡¡" + min + " minutos y " + sec + " segundos!!";
    	tv_resultado.setText(res);
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
    	ll.setVisibility(View.GONE);
    	salto.setVisibility(View.GONE);
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
		if (para_contador == 0)rl.setVisibility(v.GONE);
		finish();
	}
	
	public void pause(View v) {
		if(GV.puntuacio_world.gameover == 0){
			rl_pause.setVisibility(v.VISIBLE);
			GV.puntuacio_world.pause = 1;
			lastPause = SystemClock.elapsedRealtime();
	    	cc.stop();
		}
	}
	
	public void continua(View v) {
		rl_pause.setVisibility(v.GONE);
		GV.puntuacio_world.pause = 0;
		cc.setBase(cc.getBase() + SystemClock.elapsedRealtime() - lastPause);
		cc.start();
	}
	
	public void exit(View v) {
		finish();
	}

	public void editar_estilo() {
		tv = (TextView)findViewById(R.id.bubble_coins);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.tv_final);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.bubble_getcoins);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.bubble_lives);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.bubble_getlives);
		tv.setTypeface(type);
		tv = (TextView)findViewById(R.id.tv_gameover);
		tv.setTypeface(type);
		tv_resultado.setTypeface(type);
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
		b = (Button)findViewById(R.id.boton_final_1);
		b.setTypeface(type);
		b = (Button)findViewById(R.id.boton_final_2);
		b.setTypeface(type);
	}
	@Override
	public void onBackPressed() {
		if(GV.puntuacio_world.gameover == 0) {
			rl_pause.setVisibility(View.VISIBLE);
			GV.puntuacio_world.pause = 1;
		}
	}
}
