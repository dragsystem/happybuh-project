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

public class ChangeBeard extends Activity {
	private TextView tv, tv2;
	private ImageButton ib;
	private ImageView iv, iv2, iv3, iv4;
	private Button bt;
	private LinearLayout ll;
	private Typeface type;
	private int u_lvl;
	private int u_coins;
	private int u_color;
	private int u_glasses;
	private int u_beard;
	private int comprado, comprar; 
	private int nonreq;
	private int precio_req;
	private int level_req;
	private VG_Database db;
	private int sel_barba;
	private String num_barba, col_barba;
	private Long lc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_beard);
        
        sel_barba = 0;
        num_barba = User_Info.num_beard;
        col_barba = User_Info.col_beard;
       //User_Info.col_beard = "0";
        
        db = new VG_Database(getApplicationContext());
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");

        db.open();
	    	User_Info.color_name = db.getUserColorName();
	    db.close();
        
        
        //RECOJO LA ID DEL BOTON COMPRAR / APLICAR
        iv4 = (ImageView)findViewById(R.id.beard_buy_bought);
        
        //TEXTO CUANDO NO CUMPLE REQUISITOS
        tv2 = (TextView)findViewById(R.id.requisitos);
        tv2.setTypeface(type);
        //APLICO TIPOLOGIA A LOS TEXTVIEW
        tv = (TextView)findViewById(R.id.titulo_body);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.beard_lvlreq);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.beard_lvlreq_et);
        tv.setTypeface(type);
        Long lc = Long.parseLong("" +User_Info.color);
        db.open();
	    	tv.setText(db.getColorLvl(lc));
	    	comprado = db.getColorBought(lc);
	    	if(comprado == 1) iv4.setImageResource(R.drawable.aplicar);
	    	else iv4.setImageResource(R.drawable.comprar);
	    db.close();
        tv = (TextView)findViewById(R.id.beard_price);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.beard_price_et);
        tv.setTypeface(type);
        db.open();
        	tv.setText(db.getColorPrice(lc));
        db.close();
        tv = (TextView)findViewById(R.id.beard_coins);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.beard_coins_et);
        tv.setTypeface(type);
        tv.setText(""+User_Info.coins);
        tv = (TextView)findViewById(R.id.beard_lvl);
        tv.setTypeface(type);
        tv = (TextView)findViewById(R.id.beard_lvl_et);
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
        db.open();
        String cuerpo = "buh_" + User_Info.color_name.toLowerCase();
	    iv3 = (ImageView)findViewById(R.id.buh_body_change);
	    iv3.setImageResource(this.getResources().getIdentifier("drawable/" + cuerpo, null, this.getPackageName()));
	    db.close();

	    //COLOCO LAS GAFAS DE BUH
	    	db.open();
	    	lc = db.getUserGlasses();
	    	if(lc > 1) {
			    String gafas = "gafas_" + db.getGlassNum(lc) + "_" + db.getGlassColor(lc);
			    iv2 = (ImageView)findViewById(R.id.buh_beard_glasses);
			    iv2.setImageResource(this.getResources().getIdentifier("drawable/" + gafas, null, this.getPackageName()));
			    iv2.setVisibility(View.VISIBLE);
	    	}
		    db.close();
	    
        iv = (ImageView)findViewById(R.id.buh_beard_change);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_change_beard, menu);
        return true;
    }

    public void cambio_barba1(View v) {
    	sel_barba = 1;
    	num_barba = "1";
    	col_barba = "0";
    	carga_barba();
    	carga_interficie("1", "0");
    	db.open();
			lc = db.getBeardIndex(num_barba, col_barba);
		db.close();
    }
    
	public void cambio_barba2(View v) {
		sel_barba = 1;
    	num_barba = "2";
    	col_barba = "0";
    	carga_barba();
    	carga_interficie("2", "0");
    	db.open();
			lc = db.getBeardIndex(num_barba, col_barba);
		db.close();
	}

	public void cambio_barba3(View v) {
		sel_barba = 1;
    	num_barba = "3";
    	col_barba = "0";
    	carga_barba();
    	carga_interficie("3", "0");
    	db.open();
			lc = db.getBeardIndex(num_barba, col_barba);
		db.close();
	}
	
	public void cambio_color_azul(View v){
		if(sel_barba == 1) {
			col_barba = "0";
		    carga_interficie(num_barba,col_barba);
		    carga_color();
		    db.open();
				lc = db.getBeardIndex(num_barba, col_barba);
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	

	public void cambio_color_rojo(View v){
		if(sel_barba == 1) {
			col_barba = "1";
		    carga_interficie(num_barba,col_barba);
		    carga_color();
		    db.open();
				lc = db.getBeardIndex(num_barba, col_barba);
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	
	public void cambio_color_amarillo(View v){
		if(sel_barba == 1) {
			col_barba = "2";
		    carga_interficie(num_barba,col_barba);
		    carga_color();
		    db.open();
				lc = db.getBeardIndex(num_barba, col_barba);
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	
	public void cambio_color_verde(View v){
		if(sel_barba == 1) {
			col_barba = "3";
		    carga_interficie(num_barba,col_barba);
		    carga_color();
		    db.open();
				lc = db.getBeardIndex(num_barba, col_barba);
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	
	public void cambio_color_negro(View v){
		if(sel_barba == 1) {
			col_barba = "4";
		    carga_interficie(num_barba,col_barba);
		    carga_color();
		    db.open();
				lc = db.getBeardIndex(num_barba, col_barba);
			db.close();
		}
		else Toast.makeText(getApplicationContext(), "Debes elegir un modelo antes", Toast.LENGTH_LONG).show();
	}
	
	public void volver(View v) {
    	//moveTaskToBack(true);
    	User_Info.inicializar(getApplicationContext());
    	finish();
    }
	@Override
	public void onBackPressed() {
		User_Info.inicializar(getApplicationContext());
    	finish();
		//super.onBackPressed();
	}
	
	private void carga_barba() {
		String imagen = "barba_" + num_barba + "_0"; 
	    iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
	    iv.setVisibility(View.VISIBLE);
	}
	
	private void carga_color() {
		String imagen = "barba_" + num_barba + "_" + col_barba; 
	    iv.setImageResource(this.getResources().getIdentifier("drawable/" + imagen, null, this.getPackageName()));
	}
	
	private void carga_interficie(String num, String color) {
		tv = (TextView)findViewById(R.id.beard_lvlreq_et);
        db.open();
        	Long lc = db.getBeardIndex(num, color);
	        level_req = Integer.parseInt(db.getBeardLvl(lc));
	    	precio_req = Integer.parseInt(db.getBeardPrice(lc));
	    	tv.setText(""+db.getBeardLvl(lc));
	    	tv = (TextView)findViewById(R.id.beard_price_et);
	    	tv.setText(""+db.getBeardPrice(lc));
	    	comprado = db.getBeardBought(lc);
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
    	db.open();
    		if(comprado == 0) {
	    		User_Info.coins -= precio_req;
	    		db.setUserCoins(User_Info.coins);
	    		db.setBeardBought(lc);
	    		comprado = db.getBeardBought(lc);
	    		tv = (TextView)findViewById(R.id.beard_coins_et);
	    		tv.setText(""+User_Info.coins);
	    		iv4.setImageResource(R.drawable.aplicar);
	    		Toast.makeText(getApplicationContext(), "Acabas de comprar un modelo de barba", Toast.LENGTH_SHORT).show();
    		}
    		else {
    			lc = db.getBeardIndex(num_barba, col_barba);
    			db.setUserBeard(lc);
    			Log.v("num_barba ", num_barba);
    			Log.v("col_barba", col_barba);
    			Log.v("indice guardado", ""+lc.intValue());
    			User_Info.beard = lc.intValue();
    			User_Info.num_beard = num_barba;
    			User_Info.col_beard = col_barba;
    			Toast.makeText(getApplicationContext(), "Objeto Aplicado", Toast.LENGTH_SHORT).show();
    		}
			
    	db.close();
    }
}
