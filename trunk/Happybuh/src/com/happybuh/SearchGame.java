package com.happybuh;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchGame extends Activity {
	public Handler handler;
	public RelativeLayout rl, rl2, rl_acabat, rl_pause;
	public TextView tv, tv_acabat, tv_acabat_tiempo;
	public Button b;
	public Typeface type;
	private Random rand;
	Thread logoTimer;
	boolean stopped;
	public Chronometer cc;
	public int sec, min;
	public int para_contador;
	private Long lastPause; 

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GV.Activities.searchgame = this;
        setContentView(R.layout.activity_search_game);
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
		editar_estilo();
		GV.puntuacio_search.vides = 3;
		GV.puntuacio_search.get_exp = 0;
    	GV.puntuacio_search.coins =0;
        
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 1) mostrar_actual();
			}



		};
		
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(SearchGame.this, "" + position, Toast.LENGTH_SHORT).show();
                if (position != GV.puntuacio_search.actual) {
                	GV.puntuacio_search.vides--;
                	resta_vida();
                	if (GV.puntuacio_search.vides == 0) {
                		GV.puntuacio_search.gameover = 1;
                		game_over();
                	}
                }
                else {
                	rl_acabat = (RelativeLayout) findViewById(R.id.rl_acabat);
                	rl_acabat.setVisibility(View.VISIBLE);
                	String tiempo = min+" minutos y " + sec + " segundos";
                	tv_acabat_tiempo.setText(tiempo);
                	GV.puntuacio_search.get_exp += 0.1;
                	GV.puntuacio_search.coins +=1;
                }
            }
        });
        
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
        
        int tam = gridview.getCount(); 
        rand = new Random();
        GV.puntuacio_search.actual = rand.nextInt(tam-1);
        
        ImageView iv = (ImageView) findViewById(R.id.iv_actual);
        
        
        switch(GV.puntuacio_search.index) {
        case 0:
        	iv.setImageResource(GV.ristras.mThumbIds[GV.puntuacio_search.actual]);
        	break;
        case 1:
        	iv.setImageResource(GV.ristras.mThumbIds2[GV.puntuacio_search.actual]);
        	break;
        case 2:
        	iv.setImageResource(GV.ristras.mThumbIds3[GV.puntuacio_search.actual]);
        	break;
        case 3:
        	iv.setImageResource(GV.ristras.mThumbIds4[GV.puntuacio_search.actual]);
        	break;
    }
 

        stopped = false;
        logoTimer = new Thread(){
			public void run(){
				try{
					Log.v("chivato_thread", "entro");
					sleep(2000);
					if(!stopped) {
						GV.Activities.searchgame.handler.sendEmptyMessage(1);

					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		};
		logoTimer.start();
        
        
        //Intent i = new Intent("com.happybuh.SEARCHMOSTRARACTUAL");
		//startActivity(i);
		//finishFromChild(GV.Activities.searchmostraractual);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_game, menu);
        return true;
    }
	public void mostrar_actual() {
		// TODO Auto-generated method stub
	    rl2 =(RelativeLayout) findViewById(R.id.rl_mostraractual);
		rl2.setVisibility(View.GONE);
	}
    public void game_over() {
    	rl = (RelativeLayout)findViewById(R.id.ventana_gameover);
    	rl.setVisibility(View.VISIBLE);
    }
    
    public void gana_coins() {
    	TextView tv = (TextView) findViewById(R.id.bubble_getcoins);
    	tv.setText(String.valueOf(GV.puntuacio_search.coins));
    }
    
    public void resta_vida() {
    	TextView tv = (TextView) findViewById(R.id.bubble_getlives);
    	tv.setText(String.valueOf(GV.puntuacio_search.vides));
    }

    @Override
	protected void onPause() {
    	lastPause = SystemClock.elapsedRealtime();
    	cc.stop();
    	super.onPause();
	}

	@Override
	protected void onResume() {
		super.onPostResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void retry (View v) {
		rl.setVisibility(v.GONE);
		try{
			Intent i = new Intent("com.happybuh.SEARCHGAME");
			startActivity(i);
		}
		finally{
			finish();
		}
	}
	
	public void retry2 (View v) {
		rl_acabat.setVisibility(v.GONE);
		try{
			Intent i = new Intent("com.happybuh.SEARCHGAME");
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
	public void cancel2 (View v) {
		rl_acabat.setVisibility(v.GONE);
		finish();
	}	
	public void pause(View v) {
		rl_pause.setVisibility(v.VISIBLE);
		GV.puntuacio_search.pause = 1;
		lastPause = SystemClock.elapsedRealtime();
    	cc.stop();
	}
	
	public void continua(View v) {
		rl_pause.setVisibility(v.GONE);
		GV.puntuacio_search.pause = 0;
		cc.setBase(cc.getBase() + SystemClock.elapsedRealtime() - lastPause);
		cc.start();
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
		tv_acabat = (TextView) findViewById(R.id.tv_acabat);
		tv_acabat.setTypeface(type);
		tv_acabat_tiempo = (TextView) findViewById(R.id.tv_acabat_tiempo);
		tv_acabat_tiempo.setTypeface(type);
		rl_pause = (RelativeLayout) findViewById(R.id.ventana_pause);
	}

	@Override
	public void onBackPressed() {
		rl_pause.setVisibility(View.VISIBLE);
		GV.puntuacio_search.pause = 1;
	}
	
    
}
