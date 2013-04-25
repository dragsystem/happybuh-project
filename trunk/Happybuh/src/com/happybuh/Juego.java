package com.happybuh;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Juego extends Activity {
	private ViewFlipper vf;
	private float old, now;
	private TextView tv1, tv2, tv3;
	private Typeface type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
        
        vf = (ViewFlipper) findViewById(R.id.viewFlipper2);

		vf.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		vf.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		
		tv1 = (TextView)findViewById(R.id.texto_info1);
		tv1.setTypeface(type);
		tv2 = (TextView)findViewById(R.id.texto_info2);
		tv2.setTypeface(type);
		tv3 = (TextView)findViewById(R.id.texto_info3);
		tv3.setTypeface(type);
		
		RelativeLayout browseByTheme = (RelativeLayout) Juego.this.findViewById(R.id.Relativelayout02); 
	    browseByTheme.setOnTouchListener(new OnTouchListener() {
	         
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	            switch(event.getAction())
	            {
	            case MotionEvent.ACTION_DOWN:
	            	old = event.getX();
	                return true;
	            case MotionEvent.ACTION_UP:
	            	now = event.getX();
	            	if(now < old-100) {
	            		vf.setInAnimation(getApplicationContext(), R.anim.slide_left_in); 
	    				vf.setOutAnimation(getApplicationContext(), R.anim.slide_left_out); 
	    				vf.showPrevious(); 
	            	}
	            	else if (now > old+100){
	            		vf.setInAnimation(getApplicationContext(), R.anim.slide_right_in); 
	    				vf.setOutAnimation(getApplicationContext(), R.anim.slide_right_out); 
	    				vf.showNext(); 
	            	}
	                return true;
	            }
	            return false;
	        }
	    });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_juego, menu);
        return true;
    }

    public void inicio_juego1(View v) {
    	
    }
    
    public void inicio_juego2(View v) {
    	
    }
    
    public void inicio_juego3(View v) {
    	
    }
    
    public void inicio_help1(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego1);
    	iv.setVisibility(v.VISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego1);
    	ib.setVisibility(v.VISIBLE);
    	ScrollView sv = (ScrollView)findViewById(R.id.scroll_info1);
    	sv.setVisibility(v.VISIBLE);
    }
    
    public void inicio_help2(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego2);
    	iv.setVisibility(v.VISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego2);
    	ib.setVisibility(v.VISIBLE);
    	ScrollView sv = (ScrollView)findViewById(R.id.scroll_info2);
    	sv.setVisibility(v.VISIBLE);
    }
    
    public void inicio_help3(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego3);
    	iv.setVisibility(v.VISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego3);
    	ib.setVisibility(v.VISIBLE);
    	ScrollView sv = (ScrollView)findViewById(R.id.scroll_info3);
    	sv.setVisibility(v.VISIBLE);
    }
    
    public void cerrar_info_juego1(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego1);
    	iv.setVisibility(v.INVISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego1);
    	ib.setVisibility(v.INVISIBLE);
    	ScrollView sv = (ScrollView)findViewById(R.id.scroll_info1);
    	sv.setVisibility(v.INVISIBLE);
    }
    
    public void cerrar_info_juego2(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego2);
    	iv.setVisibility(v.INVISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego2);
    	ib.setVisibility(v.INVISIBLE);
    	ScrollView sv = (ScrollView)findViewById(R.id.scroll_info2);
    	sv.setVisibility(v.INVISIBLE);
    }    
    public void cerrar_info_juego3(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego3);
    	iv.setVisibility(v.INVISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego3);
    	ib.setVisibility(v.INVISIBLE);
    	ScrollView sv = (ScrollView)findViewById(R.id.scroll_info3);
    	sv.setVisibility(v.INVISIBLE);
    }
}
