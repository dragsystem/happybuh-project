package com.happybuh;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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
	private int u_lvl;
	private int u_coins;
	private int u_color;
	private int u_glasses;
	private int u_beard;
	private VG_Database db;
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
        
        
        //RECOGEMOS VALORES DE LA BBDD
        db = new VG_Database(ChangeColor.this);
        db.open();
        ArrayList a = new ArrayList();
        a = db.info_user();
	        //u_lvl = (Integer)a.get(2);
	        u_lvl = Integer.parseInt((String) a.get(2));
	        u_coins = Integer.parseInt((String) a.get(3));
	        u_color = Integer.parseInt((String) a.get(4));
	        u_glasses = Integer.parseInt((String) a.get(5));
	        u_beard = Integer.parseInt((String) a.get(6));
	        db.close();
        
        //RECOGEMOS MAS DATOS DE BBDD COLOR
	        db.open();
	        db.getColorById(u_color);
	        db.close();
        //RECOGEMOS MAS DATOS DE BBDD GAFAS
	        db.open();
	        //String buh_ = db.getGlassesById(u_color);
	        db.close();
	    //RECOGEMOS MAS DATOS DE BBDD BARBA
	    //COLOCO LA IMAGEN DE BUH
	    iv = (ImageView)findViewById(R.id.buh_body_change);
	    iv.setImageResource(R.drawable.red_0_0_0_0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_change_color, menu);
        return true;
    }

    public void cambio_color_azul(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	iv.setImageResource(R.drawable.blue_0_0_0_0);
    }
    
    public void cambio_color_rojo(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	iv.setImageResource(R.drawable.red_0_0_0_0);
    }
    
    public void cambio_color_verde(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	iv.setImageResource(R.drawable.green_0_0_0_0);
    }
    public void cambio_color_amarillo(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	iv.setImageResource(R.drawable.yellow_0_0_0_0);
    }
    public void cambio_color_negro(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	iv.setImageResource(R.drawable.black_0_0_0_0);
    }
    
    public void cambio_gafas1(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	iv.setImageResource(R.drawable.armario_abierto);
    }
    
    public void cambio_gafas2(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    }
    
    public void cambio_gafas3(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    }
    
    public void volver(View v) {
    	//moveTaskToBack(true);
    	finish();
    }
}
