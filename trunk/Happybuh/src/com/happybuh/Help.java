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
	    texto.setText(Html.fromHtml("<p><b>Happybuh</b> es una aplicación formada por una pequeña colección de mini-juegos.</p>"+
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
"<p>En un futuro se añadirán nuevos juegos y modificaciones.</p>" ));
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
