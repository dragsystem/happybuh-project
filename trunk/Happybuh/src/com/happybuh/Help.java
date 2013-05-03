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
	    texto.setText(Html.fromHtml("<p><b>Happybuh</b> es una aplicaci�n formada por una peque�a colecci�n de mini-juegos.</p>"+
"<p>Jugando obtendr�s dinero y experiencia para poder personalizar a tu buh cambi�ndole el color, poni�ndole barba o incluso haciendo que luzca barba.</p>"+
"<p>Los juegos se describir�n m�s adelante, en la secci�n pertinente a cada juego.</p>"+
"<p><b><font color='red'>Sobre el uso de esta aplicaci�n:</font></b></p>"+
"<p>Para ver las distintas secciones de la pantalla principal, el usuario puede o bien desplazarse mediante los botones que hay a ambos lados de la pantalla, o bien puede hacerlo deslizando el dedo hac�a la derecha o hac�a la izquierda .De este modo se puede acceder a tres secciones (principal, opciones y perfil)</p>"+
"<p>En el men� <b>Opciones</b> se puede apagar o encender la m�sica de la aplicaci�n, as� como cambiar el nombre de Buh si la primera vez no ha habido tiempo de pensar en uno mejor.</p>"+
"<p>En el men� <b>Perfil</b> se puede comprobar el estado actual de tu <b>Buh</b>, as� como el nombre, nivel, porcentaje de experiencia actual o incluso el dinero.</p>"+
"<p>La pantalla principal contiene dos botones que muestran una peque�a informaci�n de su contenido al hacer un clic sencillo sobre �stos. El que tiene forma de armario lleva al men� de personalizaci�n de Buh y el que tiene forma de mando de videoconsola lleva al men� de juegos.</p>"+
"<p>En el men� de <b>Personalizaci�n </b>hay tres secciones, una para cambiar el color del cuerpo, otra para aplicar gafas y otra para aplicar barba. Primero se deber�n cumplir los requisitos (ya sean de nivel o monetarios), sin� el usuario no podr� comprar nada, aunque si podr� visualizarlos e informarse de lo que necesita para obtener dicho objeto. </p>"+
"<p>Para que el usuario pueda aplicar el objeto, primero deber� comprarlo y una vez hecho, deber� indicar que desea aplicarlo, as� se evita sobreescribir un aspecto ya elegido antes.</p>"+
"<p>En el men� de <b>Juegos </b>hay tres secciones, que pertenecen a los tres juegos que componen (por ahora) la aplicaci�n. Para poder elegir un juego u otro el usuario deber� deslizarse hac�a la derecha o hac�a la izquierda con el dedo.</p>"+
"<p>Dentro de cada juego se encuentran dos botones. Uno para jugar y otro que nos indica las instrucciones de juego y una breve descripci�n de �ste.</p>"+
"<p>&nbsp;</p>"+
"<p>En un futuro se a�adir�n nuevos juegos y modificaciones.</p>" ));
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
