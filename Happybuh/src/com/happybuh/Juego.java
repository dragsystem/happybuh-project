package com.happybuh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Juego extends Activity {
	private ViewFlipper vf;
	private float old, now;
	private TextView tv1, tv2, tv3;
	private Typeface type;
	private Button b1, b2, b3, bfinal;
	private int mapa;
	private VG_Database db;
	private LinearLayout ll; 
	private WebView webview,webview2,webview3;
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
		db = new VG_Database(getApplicationContext());
		
		ll = (LinearLayout)findViewById(R.id.pantallas);
		
		//tv1 = (TextView)findViewById(R.id.texto_info1);
		//tv1.setTypeface(type);
		
		b1 = (Button)findViewById(R.id.pantalla1);
		b1.setTypeface(type);
		b2 = (Button)findViewById(R.id.pantalla2);
		b2.setTypeface(type);
		b3 = (Button)findViewById(R.id.pantalla3);
		b3.setTypeface(type);
		b3 = (Button)findViewById(R.id.inicio_help3);
		b3.setTypeface(type);
		bfinal = (Button)findViewById(R.id.pantalla4);
		bfinal.setTypeface(type);
		
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

    public void pantalla1(View v) {
    	GV.mapa.pant = 1;
    	Intent ourIntent = new Intent("com.happybuh.WORLDGAME");
		startActivity(ourIntent);
    }
    public void pantalla2(View v) {
    	actualiza_mapa();
    	if(mapa >= 2) {
    		GV.mapa.pant = 2;
	    	Intent ourIntent = new Intent("com.happybuh.WORLDGAME");
			startActivity(ourIntent);
    	}
    	else Toast.makeText(getApplicationContext(), "Todavía no puedes acceder a este mapa", Toast.LENGTH_SHORT);
    }
    public void pantalla3(View v) {
    	actualiza_mapa();
    	if(mapa >= 3) {
    		GV.mapa.pant = 3;
	    	Intent ourIntent = new Intent("com.happybuh.WORLDGAME");
			startActivity(ourIntent);
    	}else Toast.makeText(getApplicationContext(), "Todavía no puedes acceder a este mapa", Toast.LENGTH_SHORT);
    }
    public void pantalla4(View v) {
    	actualiza_mapa();
    	if(mapa >= 4) {
    		GV.mapa.pant = 4;
	    	Intent ourIntent = new Intent("com.happybuh.WORLDGAME");
			startActivity(ourIntent);
    	}else Toast.makeText(getApplicationContext(), "Todavía no puedes acceder a este mapa", Toast.LENGTH_SHORT);
    }
    
    public void actualiza_mapa() {
    	db.open();
		mapa  = db.getUserMap();
		db.close();
    }
    
    public void inicio_juego1(View v) {
			Intent ourIntent = new Intent("com.happybuh.BUBBLEGAME");
			startActivity(ourIntent);
    }
    
    public void inicio_juego2(View v) {
		Intent ourIntent = new Intent("com.happybuh.JUMPGAME");
		startActivity(ourIntent);
    }
    
    
    public void inicio_help1(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego1);
    	iv.setVisibility(v.VISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego1);
    	ib.setVisibility(v.VISIBLE);
    	/*ScrollView sv = (ScrollView)findViewById(R.id.scroll_info1);
    	sv.setVisibility(v.VISIBLE);
    	tv1.setText(Html.fromHtml("<p><b>Happybuh</b> es una aplicación formada por una pequeña colección de mini-juegos.</p>"+
    			"<p>Jugando obtendrás dinero y experiencia para poder personalizar a tu buh cambiándole el color, poniéndole barba o incluso haciendo que luzca barba.</p>"+
    			"<p>Los juegos se describirán más adelante, en la sección pertinente a cada juego.</p>"+
    			"<p><b><font color='red'>Sobre el uso de esta aplicación:</font></b></p>"+
    			"<p>Para ver las distintas secciones de la pantalla principal, el usuario puede o bien desplazarse mediante los botones que hay a ambos lados de la pantalla, o bien puede hacerlo deslizando el dedo hacía la derecha o hacía la izquierda .De este modo se puede acceder a tres secciones (principal, opciones y perfil)</p>"+
    			"<p>En el menú <b>Opciones</b> se puede apagar o encender la música de la aplicación, así como cambiar el nombre de Buh si la primera vez no ha habido tiempo de pensar en uno mejor.</p>"+
    			"<p>En el menú <b>Perfil</b> se puede comprobar el estado actual de tu <b>Buh</b>, así como el nombre, nivel, porcentaje de experiencia actual o incluso el dinero.</p>"+
    			"<p>La pantalla principal contiene dos botones que muestran una pequeña información de su contenido al hacer un clic sencillo sobre éstos. El que tiene forma de armario lleva al menú de personalización de Buh y el que tiene forma de mando de videoconsola lleva al menú de juegos.</p>"+
    			"<p>En el menó de <b>Personalización </b>hay tres secciones, una para cambiar el color del cuerpo, otra para aplicar gafas y otra para aplicar barba. Primero se deberán cumplir los requisitos (ya sean de nivel o monetarios), sinó el usuario no podrá comprar nada, aunque si podrá visualizarlos e informarse de lo que necesita para obtener dicho objeto. </p>"+
    			"<p>Para que el usuario pueda aplicar el objeto, primero deberá comprarlo y una vez hecho, deberá indicar que desea aplicarlo, así se evita sobreescribir un aspecto ya elegido antes.</p>"+
    			"<p>En el menú de <b>Juegos </b>hay tres secciones, que pertenecen a los tres juegos que componen (por ahora) la aplicación. Para poder elegir un juego u otro el usuario deberá deslizarse hacía la derecha o hacía la izquierda con el dedo.</p>"+
    			"<p>Dentro de cada juego se encuentran dos botones. Uno para jugar y otro que nos indica las instrucciones de juego y una breve descripción de éste.</p>"+
    			"<p>&nbsp;</p>"+
    			"<p>En un futuro se añadirán nuevos juegos y modificaciones.</p>" ));*/
    	webview = (WebView) findViewById(R.id.web_info1);
    	webview.loadUrl("file:///android_asset/www/index.html");
    	webview.setBackgroundColor(0x00000000);
    	//webview.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
    	webview.setVisibility(v.VISIBLE);
    }
    
    public void inicio_help2(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego2);
    	iv.setVisibility(v.VISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego2);
    	ib.setVisibility(v.VISIBLE);
    	webview2 = (WebView) findViewById(R.id.web_info2);
    	webview2.loadUrl("file:///android_asset/www/index2.html");
    	webview2.setBackgroundColor(0x00000000);
    	//webview.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
    	webview2.setVisibility(v.VISIBLE);
    }
    
    public void inicio_help3(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego3);
    	iv.setVisibility(v.VISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego3);
    	ib.setVisibility(v.VISIBLE);
    	webview3 = (WebView) findViewById(R.id.web_info3);
    	webview3.loadUrl("file:///android_asset/www/index3.html");
    	webview3.setBackgroundColor(0x00000000);
    	//webview.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
    	webview3.setVisibility(v.VISIBLE);
    }
    
    public void cerrar_info_juego1(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego1);
    	iv.setVisibility(v.INVISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego1);
    	ib.setVisibility(v.INVISIBLE);
    	//ScrollView sv = (ScrollView)findViewById(R.id.scroll_info1);
    	//sv.setVisibility(v.INVISIBLE);
    	webview.setVisibility(v.INVISIBLE);
    }
    
    public void cerrar_info_juego2(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego2);
    	iv.setVisibility(v.INVISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego2);
    	ib.setVisibility(v.INVISIBLE);
    	webview2.setVisibility(v.INVISIBLE);
    }    
    public void cerrar_info_juego3(View v) {
    	ImageView iv = (ImageView)findViewById(R.id.info_juego3);
    	iv.setVisibility(v.INVISIBLE);
    	ImageButton ib = (ImageButton)findViewById(R.id.cerrar_info_juego3);
    	ib.setVisibility(v.INVISIBLE);
    	webview3.setVisibility(v.INVISIBLE);
    }

	@Override
	public void onBackPressed() {
		User_Info.actualizar(getApplicationContext(), GV.puntuacio_bubble.get_exp, GV.puntuacio_bubble.coins);
		User_Info.actualizar(getApplicationContext(), GV.puntuacio_jump.get_exp, GV.puntuacio_jump.coins);
		User_Info.actualizar(getApplicationContext(), GV.puntuacio_world.get_exp, GV.puntuacio_world.coins);
		GV.Activities.habitacion.handler.sendEmptyMessage(1);
		finish();
	}
    
    
}
