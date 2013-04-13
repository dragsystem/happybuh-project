package com.happybuh;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangeColor extends Activity {
	private TextView tv, tv2;
	private ImageButton ib;
	private ImageView iv, iv2;
	private Button bt;
	private Typeface type;
	private int u_lvl;
	private int u_coins;
	private int u_color;
	private int u_glasses;
	private int u_beard;
	private int comprado; 
	private int nonreq;
	private int precio_req;
	private int level_req;
	private VG_Database db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);
        
        db = new VG_Database(getApplicationContext());
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");

        
        //RECOJO LA ID DEL BOTON COMPRAR / APLICAR
        iv2 = (ImageView)findViewById(R.id.color_buy_bought);
        iv2.setClickable(false);
        
        //TEXTO CUANDO NO CUMPLE REQUISITOS
        tv2 = (TextView)findViewById(R.id.requisitos);
        
        //APLICO TIPOLOGIA A LOS TEXTVIEW
        tv = (TextView)findViewById(R.id.titulo_body);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.color_lvlreq);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.color_lvlreq_et);
        tv.setTypeface(type);
        Long lc = Long.parseLong("" +User_Info.color);
        db.open();
	    	tv.setText(db.getColorLvl(lc));
	    	comprado = db.getColorBought(lc);
	    	if(comprado == 1) iv2.setImageResource(R.drawable.aplicar);
	    	else iv2.setImageResource(R.drawable.comprar);
	    db.close();
        tv = (TextView)findViewById(R.id.color_price);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.color_price_et);
        tv.setTypeface(type);
        db.open();
        	tv.setText(db.getColorPrice(lc));
        db.close();
        tv = (TextView)findViewById(R.id.color_coins);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.color_coins_et);
        tv.setTypeface(type);
        String a = ""+User_Info.coins;
        Log.v("COINS ", a);
        //tv.setText(User_Info.coins);
        tv = (TextView)findViewById(R.id.color_lvl);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.color_lvl_et);
        tv.setTypeface(type);
        tv.setText(""+User_Info.level);
        
        
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
        
        
        
	    //COLOCO LA IMAGEN DE BUH
        String imagen = User_Info.color_name + "_" + User_Info.num_glasses + "_" + User_Info.col_glasses + "_" + User_Info.num_beard + "_" + User_Info.col_beard; 
	    iv = (ImageView)findViewById(R.id.buh_body_change);
	    iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
    
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
    	Long lc = Long.parseLong("" +1);
    	tv = (TextView)findViewById(R.id.color_lvlreq_et);
        db.open();
	    	level_req = Integer.parseInt(db.getColorLvl(lc));
	    	precio_req = Integer.parseInt(db.getColorPrice(lc));
        	tv.setText(""+db.getColorLvl(lc));
	    	tv = (TextView)findViewById(R.id.color_price_et);
	    	tv.setText(""+db.getColorPrice(lc));
	    	comprado = db.getColorBought(lc);
	    db.close();
    	String imagen = "blue_" + User_Info.num_glasses + "_" + User_Info.col_glasses + "_" + User_Info.num_beard + "_" + User_Info.col_beard;
    	iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
    	if(comprado == 1)iv2.setImageResource(R.drawable.aplicar);
    	else iv2.setImageResource(R.drawable.comprar);
    	if(User_Info.level < level_req || User_Info.coins < precio_req) tv2.setVisibility(View.VISIBLE);
    	else tv2.setVisibility(View.INVISIBLE);
    }
    
    public void cambio_color_rojo(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	Long lc = Long.parseLong("" + 2);
    	tv = (TextView)findViewById(R.id.color_lvlreq_et);
        db.open();
	        level_req = Integer.parseInt(db.getColorLvl(lc));
	    	precio_req = Integer.parseInt(db.getColorPrice(lc));
	    	tv.setText(""+db.getColorLvl(lc));
	    	tv = (TextView)findViewById(R.id.color_price_et);
	    	tv.setText(""+db.getColorPrice(lc));
	    	comprado = db.getColorBought(lc);
	    db.close();
    	String imagen = "red_" + User_Info.num_glasses + "_" + User_Info.col_glasses + "_" + User_Info.num_beard + "_" + User_Info.col_beard;
    	iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
    	if(comprado == 1)iv2.setImageResource(R.drawable.aplicar);
    	else iv2.setImageResource(R.drawable.comprar);
    	if(User_Info.level < level_req || User_Info.coins < precio_req) tv2.setVisibility(View.VISIBLE);
    	else tv2.setVisibility(View.INVISIBLE);
    }
    
    public void cambio_color_verde(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	Long lc = Long.parseLong("" +3);
    	tv = (TextView)findViewById(R.id.color_lvlreq_et);
        db.open();
	        level_req = Integer.parseInt(db.getColorLvl(lc));
	    	precio_req = Integer.parseInt(db.getColorPrice(lc));
        	tv.setText(""+db.getColorLvl(lc));
	    	tv = (TextView)findViewById(R.id.color_price_et);
	    	tv.setText(""+db.getColorPrice(lc));
	    	comprado = db.getColorBought(lc);
	    db.close();
    	String imagen = "green_" + User_Info.num_glasses + "_" + User_Info.col_glasses + "_" + User_Info.num_beard + "_" + User_Info.col_beard;
    	iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
    	if(comprado == 1)iv2.setImageResource(R.drawable.aplicar);
    	else iv2.setImageResource(R.drawable.comprar);
    	if(User_Info.level < level_req || User_Info.coins < precio_req) tv2.setVisibility(View.VISIBLE);
    	else tv2.setVisibility(View.INVISIBLE);
    }
    public void cambio_color_amarillo(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	Long lc = Long.parseLong("" +4);
    	tv = (TextView)findViewById(R.id.color_lvlreq_et);
        db.open();
	        level_req = Integer.parseInt(db.getColorLvl(lc));
	    	precio_req = Integer.parseInt(db.getColorPrice(lc));
	    	tv.setText(""+db.getColorLvl(lc));
	    	tv = (TextView)findViewById(R.id.color_price_et);
	    	tv.setText(""+db.getColorPrice(lc));
	    	comprado = db.getColorBought(lc);
	    db.close();
    	String imagen = "yellow_" + User_Info.num_glasses + "_" + User_Info.col_glasses + "_" + User_Info.num_beard + "_" + User_Info.col_beard;
    	iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
    	if(comprado == 1)iv2.setImageResource(R.drawable.aplicar);
    	else iv2.setImageResource(R.drawable.comprar);
    	if(User_Info.level < level_req || User_Info.coins < precio_req) tv2.setVisibility(View.VISIBLE);
    	else tv2.setVisibility(View.INVISIBLE);
    }
    public void cambio_color_negro(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	Long lc = Long.parseLong("" +5);
    	tv = (TextView)findViewById(R.id.color_lvlreq_et);
        db.open();
	        level_req = Integer.parseInt(db.getColorLvl(lc));
	    	precio_req = Integer.parseInt(db.getColorPrice(lc));
	    	tv.setText(""+db.getColorLvl(lc));
	    	tv = (TextView)findViewById(R.id.color_price_et);
	    	tv.setText(""+db.getColorPrice(lc));
	    	comprado = db.getColorBought(lc);
	    db.close();
    	String imagen = "black_" + User_Info.num_glasses + "_" + User_Info.col_glasses + "_" + User_Info.num_beard + "_" + User_Info.col_beard;
    	iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
    	if(comprado == 1)iv2.setImageResource(R.drawable.aplicar);
    	else iv2.setImageResource(R.drawable.comprar);
    	if(User_Info.level < level_req || User_Info.coins < precio_req) tv2.setVisibility(View.VISIBLE);
    	else tv2.setVisibility(View.INVISIBLE);
    }

    public void volver(View v) {
    	//moveTaskToBack(true);
    	finish();
    }
}
