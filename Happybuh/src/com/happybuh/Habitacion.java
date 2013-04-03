package com.happybuh;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

public class Habitacion extends Activity {

	private ViewFlipper vf;
	private float old, now;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitacion);
        vf = (ViewFlipper) findViewById(R.id.viewFlipper1);

		vf.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		vf.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		
		Button nextButton = (Button) Habitacion.this.findViewById(R.id.Button01); 
		nextButton.setOnClickListener(new OnClickListener() 
		{ 
			public void onClick(View v) { 
				vf.setInAnimation(Habitacion.this, R.anim.slide_left_in); 
				vf.setOutAnimation(Habitacion.this, R.anim.slide_left_out); 
				vf.showNext(); 

			}
		}); 
		
		Button previousButton = (Button) Habitacion.this.findViewById(R.id.Button02); 
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
	    
	    EditText editText = (EditText) findViewById(R.id.user_name);
	    editText.setOnEditorActionListener(new OnEditorActionListener() {
	        @Override
	        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
	            boolean handled = false;
	            if (actionId == EditorInfo.IME_ACTION_DONE) {
	                //GUARDAREMOS EN BBDD EL NOMBRE DE USUARIO
	                handled = true;
	            }
	            return handled;
	        }
	    });
	    
	    Button cambiar = (Button)findViewById(R.id.change_name);
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