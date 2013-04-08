package com.happybuh;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

public class Habitacion extends Activity {

	private ViewFlipper vf;
	private float old, now;
	ImageButton boton_armario;
	ImageButton boton_mando;
	Typeface type;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitacion);
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
        
        addListenerOnButton();
        
        vf = (ViewFlipper) findViewById(R.id.viewFlipper1);

		vf.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		vf.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		
		ImageButton nextButton = (ImageButton) Habitacion.this.findViewById(R.id.b_opt_izq); 
		nextButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_right_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_right_out); 
				vf.showNext(); 

			}
		}); 
		
		ImageButton previousButton = (ImageButton) Habitacion.this.findViewById(R.id.b_help_der); 
		previousButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_left_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_left_out); 
				vf.showPrevious(); 
			} 
		});
		
		ImageButton nextButton2 = (ImageButton) Habitacion.this.findViewById(R.id.b_help_izq); 
		nextButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_right_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_right_out); 
				vf.showNext(); 

			}
		}); 
		
		ImageButton previousButton2 = (ImageButton) Habitacion.this.findViewById(R.id.b_room_der); 
		previousButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_left_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_left_out); 
				vf.showPrevious(); 
			} 
		});
		
		ImageButton nextButton3 = (ImageButton) Habitacion.this.findViewById(R.id.b_room_izq); 
		nextButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_right_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_right_out); 
				vf.showNext(); 

			}
		}); 
		
		ImageButton previousButton3 = (ImageButton) Habitacion.this.findViewById(R.id.b_opt_der); 
		previousButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_left_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_left_out); 
				vf.showPrevious(); 
			} 
		});
		
		RelativeLayout browseByTheme = (RelativeLayout) Habitacion.this.findViewById(R.id.Relativelayout01); 
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
	            		vf.setInAnimation(Habitacion.this, R.anim.slide_right_in); 
	    				vf.setOutAnimation(Habitacion.this, R.anim.slide_right_out); 
	    				vf.showPrevious(); 
	            	}
	            	else if (now > old+100){
	            		vf.setInAnimation(Habitacion.this, R.anim.slide_left_in); 
	    				vf.setOutAnimation(Habitacion.this, R.anim.slide_left_out); 
	    				vf.showNext(); 
	            	}
	                return true;
	            }
	            return false;
	        }
	    });
	    
	    
	    ImageButton cambiar = (ImageButton)findViewById(R.id.change_name);
	    cambiar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ALERTDIALOG CON EL NOMBRE DE USUARIO PARA CAMBIAR
				AlertDialog.Builder alerta = new AlertDialog.Builder(v.getContext());
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.dialog_change_user, null);
				final EditText et = (EditText)layout.findViewById(R.id.username);
				alerta.setView(layout)
				// set title
				//alerta.setTitle("Your Title");
		 
					// set dialog message
				//alerta.setMessage("Click yes to exit!")
						//.setCancelable(false)
						.setPositiveButton("summit",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								if (et.getText().toString().isEmpty()) {
									Toast t = Toast.makeText(getApplicationContext(), "cagarruta no lee", Toast.LENGTH_LONG);
									t.show();
								}
								else {
									VG_Database db = new VG_Database(getApplicationContext());
									db.open();
									db.setUser(db.getUserName(), et.getText().toString());
									db.close();
								}
							}
						  })
						.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alerta.create();
		 
						// show it
						alertDialog.show();
					}
				});
	    
	    TextView info_tit = (TextView)findViewById(R.id.info_title);
	    info_tit.setTypeface(type);
	    info_tit.setText(Html.fromHtml("<h1>HAPPYBUH Help Menu</h1><br>"));
	    TextView texto = (TextView)findViewById(R.id.desc); 
	    texto.setMovementMethod(new ScrollingMovementMethod());
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
	    
	    try {
	    	TextView name = (TextView)findViewById(R.id.user_name);
	    	TextView name2 = (TextView)findViewById(R.id.user_name2);
		    VG_Database db = new VG_Database(Habitacion.this);
		    db.open();
		    	String user_name = db.getUserName();
		    db.close();
		    name.setTypeface(type);
		    name.setText(user_name);
		    name2.setTypeface(type);
		    name2.setText(user_name);
		    
		    ToggleButton tb = (ToggleButton)findViewById(R.id.sonido);
		    tb.setTypeface(type);
	    }catch(Exception e) {
	    	e.getMessage();
	    }
    }
    
    public void addListenerOnButton() {
   	 
		boton_armario = (ImageButton) findViewById(R.id.b_armario);
 
		boton_armario.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			   Toast.makeText(Habitacion.this,"Entra!! Descubrirás nuevos looks para tu Buuh!", Toast.LENGTH_SHORT).show();
 
			}
		});
		
		boton_armario.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("com.happybuh.ARMARIO");
				startActivity(i);
				return false;
			}
		});
		
		boton_mando = (ImageButton) findViewById(R.id.b_mando);
		 
		boton_mando.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			   Toast.makeText(Habitacion.this,"Entra!! Y empieza a ganar experiencia y coins", Toast.LENGTH_SHORT).show();
 
			}
		});
		
		boton_mando.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				/*Intent i = new Intent("com.habbybuh.ARMARIO");
				startActivity(i);*/
				//COLOCAR ACTIVIDAD DE LOS JUEGOS
				return false;
			}
		});
 
	}
    
    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton)view).isChecked();
        
        if (on) {
            // Enable vibrate
        	
        } else {
            // Disable vibrate
        }
    }
    
    public void next(View v) {
    	vf.showNext();
    }
    public void prev(View v) {
    	vf.showPrevious();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_habitacion, menu);
        return true;
    }

}