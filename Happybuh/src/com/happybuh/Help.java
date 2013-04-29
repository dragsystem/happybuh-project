package com.happybuh;

import java.util.Random;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Help extends Activity {
	
	private Typeface type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
        
        TextView info_tit = (TextView)findViewById(R.id.help_title);
	    info_tit.setTypeface(type);
	    //info_tit.setText(Html.fromHtml("<h1>HAPPYBUH Help Menu</h1><br>"));
	    TextView texto = (TextView)findViewById(R.id.info); 
	    //texto.setMovementMethod(new ScrollingMovementMethod());
	    texto.setTypeface(type);
	    texto.setText(Html.fromHtml("<p>HappyBuh esta formado por una coleccion de mini-juegos</p>" +
	    		"<p><i>Ahora</i><b> Haremos </b> pruebas varias con <font color='red'>los colores </font></p>" +
	    		"<p>Deberas subir niveles a medida que juegues y alcances nuevos Records a la vez que consiguas coins para poder personalizar a tu personaje</p>" +
	    		"<p>Tambien podras probar un nuevo juego que se desbloqueara al llegar al nivel 10</p>" + 
	    		"<p>Hay 3 tipos de juegos. DIVERTIDISIMOS!!</p>"+
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" +
	    		"<p>meeeeeeeeeerp</p>" ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_help, menu);
        return true;
    }
    
    @Override
	public void onBackPressed() {
    	finish();
		//super.onBackPressed();
	}
    
    public void volver(View v) {
    	finish();
    }

    
}
