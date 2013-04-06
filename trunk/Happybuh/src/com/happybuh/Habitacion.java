package com.happybuh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitacion);
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
				vf.setInAnimation(Habitacion.this, R.anim.slide_left_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_left_out); 
				vf.showNext(); 

			}
		}); 
		
		ImageButton previousButton = (ImageButton) Habitacion.this.findViewById(R.id.b_help_der); 
		previousButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_right_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_right_out); 
				vf.showPrevious(); 
			} 
		});
		
		ImageButton nextButton2 = (ImageButton) Habitacion.this.findViewById(R.id.b_help_izq); 
		nextButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_left_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_left_out); 
				vf.showNext(); 

			}
		}); 
		
		ImageButton previousButton2 = (ImageButton) Habitacion.this.findViewById(R.id.b_room_der); 
		previousButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_right_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_right_out); 
				vf.showPrevious(); 
			} 
		});
		
		ImageButton nextButton3 = (ImageButton) Habitacion.this.findViewById(R.id.b_room_izq); 
		nextButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_left_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_left_out); 
				vf.showNext(); 

			}
		}); 
		
		ImageButton previousButton3 = (ImageButton) Habitacion.this.findViewById(R.id.b_opt_der); 
		previousButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_right_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_right_out); 
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
				
				//poner el dialog alert del tutorial
				AlertDialog.Builder alerta = new AlertDialog.Builder(v.getContext());
				LayoutInflater inflater = getLayoutInflater();
				alerta.setView(inflater.inflate(R.layout.dialog_change_user, null))
					// set title
				//alerta.setTitle("Your Title");
		 
					// set dialog message
				//alerta.setMessage("Click yes to exit!")
						//.setCancelable(false)
						.setPositiveButton("summit",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								Habitacion.this.finish();
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
	    
	    TextView texto = (TextView)findViewById(R.id.desc); 
	    texto.setText(Html.fromHtml("<h1>HAPPYBUH Help Menu</h1><br><p>HappyBuh esta formado por una coleccion de mini-juegos</p>" +
	    		"<p><i>Ahora</i><b> Haremos </b> pruebas varias con <font color='red'>los colores </font></p>" +
	    		""));
		
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
				Intent i = new Intent("com.habbybuh.ARMARIO");
				startActivity(i);
				return false;
			}
		});
		
		boton_armario = (ImageButton) findViewById(R.id.b_armario);
		 
		boton_armario.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			   Toast.makeText(Habitacion.this,"Entra!! Descubrirás nuevos looks para tu Buuh!", Toast.LENGTH_SHORT).show();
 
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