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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeGlasses extends Activity {
	private TextView tv, tv2;
	private ImageButton ib;
	private ImageView iv, iv2, iv3, iv4;
	private Button bt;
	private LinearLayout ll;
	private Typeface type;
	private int u_lvl;
	private int u_coins;
	private int u_color;
	private int u_glass;
	private int comprado, comprar; 
	private int nonreq;
	private int precio_req;
	private int level_req;
	private VG_Database db;
	private int sel_glass;
	private String num_gafas, col_gafas;
	private Long lc;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_glasses);
        
        sel_glass = 0;
        //User_Info.color_name = "blue";
       //User_Info.col_beard = "0";
        
        
        db = new VG_Database(getApplicationContext());
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");

        db.open();
	    	User_Info.color_name = db.getUserColor();
	    db.close();
        
        //RECOJO LA ID DEL BOTON COMPRAR / APLICAR
        iv4 = (ImageView)findViewById(R.id.glass_buy_bought);
        
        //TEXTO CUANDO NO CUMPLE REQUISITOS
        tv2 = (TextView)findViewById(R.id.requisitos);
        tv2.setTypeface(type);
        //APLICO TIPOLOGIA A LOS TEXTVIEW
        tv = (TextView)findViewById(R.id.titulo_body);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.glass_lvlreq);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.glass_lvlreq_et);
        tv.setTypeface(type);
        lc = Long.parseLong("" +User_Info.color);
        db.open();
	    	tv.setText(db.getColorLvl(lc));
	    	comprado = db.getColorBought(lc);
	    	if(comprado == 1) iv4.setImageResource(R.drawable.aplicar);
	    	else iv4.setImageResource(R.drawable.comprar);
	    db.close();
        tv = (TextView)findViewById(R.id.glass_price);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.glass_price_et);
        tv.setTypeface(type);
        db.open();
        	tv.setText(db.getColorPrice(lc));
        db.close();
        tv = (TextView)findViewById(R.id.glass_coins);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.glass_coins_et);
        tv.setTypeface(type);
        	tv.setText(""+User_Info.coins);
        //tv.setText(User_Info.coins);
        tv = (TextView)findViewById(R.id.glass_lvl);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.glass_lvl_et);
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
        String cuerpo = "buh_" + User_Info.color_name;
	    iv3 = (ImageView)findViewById(R.id.buh_body_change);
	    iv3.setImageResource(this.getResources().getIdentifier("drawable/" + cuerpo, null, this.getPackageName()));
	    
	    //COLOCO LA BARBA DE BUH
	    int numb = Integer.parseInt(User_Info.num_beard);
	    if (numb > 0) {
		    String barba = "barba_" + User_Info.num_beard + "_" + User_Info.col_beard;
		    iv2 = (ImageView)findViewById(R.id.buh_glasses_beard);
		    iv2.setImageResource(this.getResources().getIdentifier("drawable/" + barba, null, this.getPackageName()));
		    iv2.setVisibility(View.VISIBLE);
	    }
	    
        iv = (ImageView)findViewById(R.id.buh_glasses_change);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_change_glasses, menu);
        return true;
    }

    public void cambio_gafas1(View v) {
    	sel_glass = 1;
    	User_Info.num_glasses = "1";
    	carga_gafas();
    	carga_interficie("1", "0");
    	num_gafas = "1";
    	db.open();
    		lc = db.getIndexGlasses(num_gafas, "0");
    	db.close();
    }
    
	public void cambio_gafas2(View v) {
		sel_glass = 1;
		User_Info.num_glasses = "2";
	    carga_interficie("2", "0");
	    carga_gafas();
	    num_gafas = "2";
	    db.open();
			lc = db.getIndexGlasses(num_gafas, "0");
		db.close();
	}

	public void cambio_gafas3(View v) {
		sel_glass = 1; 
		User_Info.num_glasses = "3";
	    carga_gafas();
	    carga_interficie("3", "0");
	    num_gafas = "3";
	    db.open();
			lc = db.getIndexGlasses(num_gafas, "0");
		db.close();
	}
	
	public void cambio_color_azul(View v){
		if(sel_glass == 1) {
			User_Info.col_glasses = "0";
		    carga_interficie(User_Info.num_glasses,"0");
		    carga_color();
		    col_gafas = "0";
		    db.open();
				lc = db.getIndexGlasses(num_gafas, "0");
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	

	public void cambio_color_rojo(View v){
		if(sel_glass == 1) {
			User_Info.col_glasses = "1";
		    carga_interficie(User_Info.num_glasses,"1");
		    carga_color();
		    col_gafas = "1";
		    db.open();
				lc = db.getIndexGlasses(num_gafas, "1");
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	
	public void cambio_color_amarillo(View v){
		if(sel_glass == 1) {
			User_Info.col_glasses = "2";
			carga_interficie(User_Info.num_glasses,"2");
		    carga_color();
		    col_gafas = "2";
		    db.open();
				lc = db.getIndexGlasses(num_gafas, "2");
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	
	public void cambio_color_verde(View v){
		if(sel_glass == 1) {
			User_Info.col_glasses = "3";
			carga_interficie(User_Info.num_glasses,"3");
		    carga_color();
		    col_gafas = "3";
		    db.open();
				lc = db.getIndexGlasses(num_gafas, "3");
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	
	public void cambio_color_negro(View v){
		if(sel_glass == 1) {
			User_Info.col_glasses = "4";
			carga_interficie(User_Info.num_glasses,"4");
		    carga_color();
		    col_gafas = "4";
		    db.open();
				lc = db.getIndexGlasses(num_gafas, "4");
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	
	public void volver(View v) {
    	//moveTaskToBack(true);
    	User_Info.inicializar(getApplicationContext());
    	finish();
    }
	
	private void carga_gafas() {
		String imagen = "gafas_" + User_Info.num_glasses + "_0"; 
	    iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
	    iv.setVisibility(View.VISIBLE);
	}
	
	private void carga_color() {
		String imagen = "gafas_" + User_Info.num_glasses + "_" + User_Info.col_glasses; 
	    iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
	}
	
	private void carga_interficie(String num, String color) {
		tv = (TextView)findViewById(R.id.glass_lvlreq_et);
        db.open();
        	Long lc = Long.parseLong(db.getGlassIndex(num, color));
        	Log.v("indice gafa seleccionada" , ""+lc);
	        level_req = Integer.parseInt(db.getGlassLvl(lc));
	    	precio_req = Integer.parseInt(db.getGlassPrice(lc));
	    	tv.setText(""+db.getGlassLvl(lc));
	    	tv = (TextView)findViewById(R.id.glass_price_et);
	    	tv.setText(""+db.getGlassPrice(lc));
	    	comprado = db.getGlassBought(lc);
	    	Log.v("COMPRADO - APLICAR" , ""+comprado);
	    db.close();
	    if(User_Info.level < level_req || User_Info.coins < precio_req) {
    		tv2.setVisibility(View.VISIBLE);
    		iv4.setVisibility(View.GONE);
    	}
    	else if(comprado == 1) {
    		tv2.setVisibility(View.GONE);
    		iv4.setImageResource(R.drawable.aplicar);
    		iv4.setVisibility(View.VISIBLE);
    	}
    	else if(comprado == 0) {
    		tv2.setVisibility(View.GONE);
    		iv4.setImageResource(R.drawable.comprar);
    		iv4.setVisibility(View.VISIBLE);
    	}
	}
	public void comprar_aplicar(View v) {
		Log.v("COMPRAR ", "HE ENTRADO EN LA FUNCION");
    	db.open();
    		if(comprado == 0) {
	    		User_Info.coins -= precio_req;
	    		db.setUserCoins(User_Info.coins);
	    		db.setGlassesBought(lc);
	    		comprado = db.getGlassBought(lc);
	    		tv = (TextView)findViewById(R.id.glass_coins_et);
	    		tv.setText(""+User_Info.coins);
	    		iv4.setImageResource(R.drawable.aplicar);
	    		Toast.makeText(getApplicationContext(), "Acabas de comprar unas gafas", Toast.LENGTH_LONG).show();
    		}
    		else Toast.makeText(getApplicationContext(), "Objeto Aplicado", Toast.LENGTH_LONG).show();
			db.setUserGlasses(lc);
    		User_Info.actualizar_user(getApplicationContext());
    	db.close();
    }
}
