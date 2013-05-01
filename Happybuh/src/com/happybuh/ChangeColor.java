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
import android.widget.Toast;

public class ChangeColor extends Activity {
	private TextView tv, tv2;
	private ImageButton ib;
	private ImageView iv, iv2, iv3, iv4;
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
	private Long lc;
	private String color_name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);
        
        
        db = new VG_Database(getApplicationContext());
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");

        
        //RECOJO LA ID DEL BOTON COMPRAR / APLICAR
        iv3 = (ImageView)findViewById(R.id.color_buy_bought);
        
        //TEXTO CUANDO NO CUMPLE REQUISITOS
        tv2 = (TextView)findViewById(R.id.requisitos);
        tv2.setTypeface(type);
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
	    	if(comprado == 1) iv3.setImageResource(R.drawable.aplicar);
	    	else iv3.setImageResource(R.drawable.comprar);
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
    	tv.setText(""+User_Info.coins);
        String a = ""+User_Info.coins;
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
        
        
        //COLOCO LAS GAFAS DE BUH
        db.open();
    	lc = db.getUserGlasses();
    	Log.v("INDEX GLASSES CHANGE COLOR", ""+lc);
    	if(lc > 1) {
		    String gafas = "gafas_" + db.getGlassNum(lc) + "_" + db.getGlassColor(lc);
		    iv2 = (ImageView)findViewById(R.id.buh_color_glasses);
		    iv2.setImageResource(this.getResources().getIdentifier("drawable/" + gafas, null, this.getPackageName()));
		    iv2.setVisibility(View.VISIBLE);
    	}
	    db.close();
	    
	    //COLOCO LA BARBA DE BUH
	    db.open();
    	lc = db.getUserBeard();
    	Log.v("INDEX BEARD CHANGE COLOR", ""+lc);
    	if(lc > 1) {
		    String barba = "barba_" + db.getBeardNum(lc) + "_" + db.getBeardColor(lc);
		    iv4 = (ImageView)findViewById(R.id.buh_color_beard);
		    iv4.setImageResource(this.getResources().getIdentifier("drawable/" + barba, null, this.getPackageName()));
		    iv4.setVisibility(View.VISIBLE);
		    
    	}
	    db.close();
	    
	    //CARLO EL VIEW DE BUH
        iv = (ImageView)findViewById(R.id.buh_body_change);
    
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
    	iv.setImageResource(R.drawable.buh_blue);
    	lc = Long.parseLong("" +1);
    	carga_color(lc);
    	color_name = "blue";
    }
    
    

	public void cambio_color_rojo(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
		iv.setImageResource(R.drawable.buh_red);
    	lc = Long.parseLong("" + 2);
    	carga_color(lc);
    	color_name = "red";
    }
    
    public void cambio_color_verde(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	iv.setImageResource(R.drawable.buh_green);
    	lc = Long.parseLong("" +4);
    	carga_color(lc);
    	color_name = "green";
    }
    public void cambio_color_amarillo(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	iv.setImageResource(R.drawable.buh_yellow);
    	lc = Long.parseLong("" +3);
    	carga_color(lc);
    	color_name = "yellow";
    }
    public void cambio_color_negro(View v) {
    	//CAMBIO LVL_REQ
    	//CAMBIO PRECIO
    	//CAMBIO BOTON COMPRAR
    	//CAMBIO ASPECTO
    	iv.setImageResource(R.drawable.buh_black);
    	lc = Long.parseLong("" +5);
    	carga_color(lc);
    	color_name = "black";
    }

    public void volver(View v) {
    	User_Info.inicializar(getApplicationContext());
    	finish();
    }
    
    @Override
	public void onBackPressed() {
		User_Info.inicializar(getApplicationContext());
    	finish();
		//super.onBackPressed();
	}
    
    private void carga_color(Long lc_aux) {
    	tv = (TextView)findViewById(R.id.color_lvlreq_et);
        db.open();
	    	level_req = Integer.parseInt(db.getColorLvl(lc_aux));
	    	precio_req = Integer.parseInt(db.getColorPrice(lc_aux));
        	tv.setText(""+db.getColorLvl(lc_aux));
	    	tv = (TextView)findViewById(R.id.color_price_et);
	    	tv.setText(""+db.getColorPrice(lc_aux));
	    	comprado = db.getColorBought(lc_aux);
	    db.close();
    	if(User_Info.level < level_req || User_Info.coins < precio_req) {
    		tv2.setVisibility(View.VISIBLE);
    		iv3.setVisibility(View.GONE);
    	}
    	else if(comprado == 1) {
    		tv2.setVisibility(View.GONE);
    		iv3.setImageResource(R.drawable.aplicar);
    		iv3.setVisibility(View.VISIBLE);
    	}
    	else if(comprado == 0) {
    		tv2.setVisibility(View.GONE);
    		iv3.setImageResource(R.drawable.comprar);
    		iv3.setVisibility(View.VISIBLE);
    	}
	}
    
    public void comprar_aplicar(View v) {
    	db.open();
    		if(comprado == 0) {
	    		User_Info.coins -= precio_req;
	    		db.setUserCoins(User_Info.coins);
	    		db.setColorBought(lc);
	    		comprado = db.getColorBought(lc);
	    		tv = (TextView)findViewById(R.id.color_coins_et);
	    		tv.setText(""+User_Info.coins);
	    		iv3.setImageResource(R.drawable.aplicar);
	    		Toast.makeText(getApplicationContext(), "Acabas de comprar un color de cuerpo", Toast.LENGTH_SHORT).show();
    		}
    		else {
    			Toast.makeText(getApplicationContext(), "Objeto aplicado", Toast.LENGTH_SHORT).show();
    			db.setUserColor(lc);
    			User_Info.color = lc.intValue();
    			User_Info.color_name = color_name;
    		}
    	db.close();
    }
}
