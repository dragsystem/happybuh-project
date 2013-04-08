package com.happybuh;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangeColor extends Activity {
	private TextView tv;
	private ImageButton ib;
	private ImageView iv;
	private Button bt;
	private Typeface type;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
        
        //APLICO TIPOLOGIA A LOS TEXTVIEW
        tv = (TextView)findViewById(R.id.titulo_body);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.glass_lvlreq);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.glass_lvlreq_et);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.glass_price);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.glass_price_et);
        tv.setTypeface(type);
        
        //APLICO TIPOLOGIA A LOS BOTONES
        bt = (Button)findViewById(R.id.body_boton_azul);
        bt.setTypeface(type);
        bt = (Button)findViewById(R.id.body_boton_amarillo);
        bt.setTypeface(type);
        bt = (Button)findViewById(R.id.body_boton_negro);
        bt.setTypeface(type);
        bt = (Button)findViewById(R.id.body_boton_rojo);
        bt.setTypeface(type);
        bt = (Button)findViewById(R.id.body_boton_verde);
        bt.setTypeface(type);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_change_color, menu);
        return true;
    }

    public void cambio_color_azul() {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    }
    
    public void cambio_color_rojo() {
    	
    }
    
    public void cambio_color_verde() {
    	
    }
    public void cambio_color_amarillo() {
    	
    }
    public void cambio_color_negro() {
    	
    }
    
    public void volver() {
    	moveTaskToBack(true);
    	finish();
    }
}
