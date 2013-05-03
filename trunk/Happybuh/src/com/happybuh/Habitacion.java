package com.happybuh;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

public class Habitacion extends Activity {
	private LayoutInflater li;
	private ViewFlipper vf;
	private float old, now;
	ImageButton boton_armario;
	ImageButton boton_mando;
	TextView tv;
	Typeface type;
	static ImageView iv_buh, iv_gafas, iv_barba;
	static View v;
	User_Info a;
	static Random rand;
	static Context c;
	public Handler handler;
	private VG_Database db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        super.onCreate(savedInstanceState);
        GV.Activities.habitacion = this;
        setContentView(R.layout.activity_habitacion);
        
        
        handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 1) set_perfil_info();
			}
			
		};
        //v = li.inflate(com.happybuh.R.layout.activity_habitacion, null);
        User_Info.inicializar(getApplicationContext());
        rand = new Random();
        c = this;
        
        iv_buh = (ImageView)findViewById(R.id.buh);
    	iv_gafas = (ImageView)findViewById(R.id.buh_gafas);
    	iv_barba = (ImageView)findViewById(R.id.buh_barba);
    	int num = rand.nextInt(2); 
    	ChangeBuhAppearance(iv_buh, num, 1);
    	ChangeBuhAppearance(iv_gafas, num, 2);
    	ChangeBuhAppearance(iv_barba, num, 3);
        
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
        
        Button b = (Button)findViewById(R.id.boton_help);
        b.setTypeface(type);
        
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
	            		vf.setInAnimation(Habitacion.this, R.anim.slide_left_in); 
	    				vf.setOutAnimation(Habitacion.this, R.anim.slide_left_out); 
	    				vf.showPrevious(); 
	            	}
	            	else if (now > old+100){
	            		vf.setInAnimation(Habitacion.this, R.anim.slide_right_in); 
	    				vf.setOutAnimation(Habitacion.this, R.anim.slide_right_out); 
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
								if (et.getText().toString().length() < 5) {
									Toast t = Toast.makeText(getApplicationContext(), "El usuario debe contener entre 5 y 10 carácteres", Toast.LENGTH_SHORT);
									t.show();
								}
								else {
									VG_Database db = new VG_Database(getApplicationContext());
									db.open();
									db.setUser(db.getUserName(), et.getText().toString());
									db.close();
									User_Info.name = et.getText().toString();
									set_perfil_info();
									tv = (TextView)findViewById(R.id.user_name);
									tv.setText(et.getText());
									tv = (TextView)findViewById(R.id.user_name2);
									tv.setText(et.getText());
									tv = (TextView)findViewById(R.id.perfil_user_set);
									tv.setText(et.getText());
									
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
	    
	    //SET TYPEFACE DE LOS TEXTOS
	    TextView t_opciones = (TextView)findViewById(R.id.habitacion_titulo_opciones);
	    t_opciones.setTypeface(type);
	    TextView t_armario = (TextView)findViewById(R.id.t_armario);
	    t_armario.setTypeface(type);
	    TextView t_mando = (TextView)findViewById(R.id.t_mando);
	    t_mando.setTypeface(type);
	    
	    
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
	    
	    
	    set_perfil_info();
	    db = new VG_Database(getApplicationContext());
	    db.open();
	    Long lc = db.getUserBeard();
	    lc = db.getUserGlasses();
	    db.close();
    }
//--------------------------------------------------------INFORMACION DE PERFIL-------------------------------------------------    //
    public void set_perfil_info() {
    	tv = (TextView)findViewById(R.id.perfil_titulo);
    	tv.setTypeface(type);
    	tv = (TextView)findViewById(R.id.perfil_user);
    	tv.setTypeface(type);
    	tv = (TextView)findViewById(R.id.perfil_user_set);
    	tv.setTypeface(type);
    	tv.setText(User_Info.name);
    	tv = (TextView)findViewById(R.id.perfil_level);
    	tv.setTypeface(type);
    	tv = (TextView)findViewById(R.id.perfil_level_set);
    	tv.setTypeface(type);
    	tv.setText(""+User_Info.level);
    	tv = (TextView)findViewById(R.id.perfil_exp);
    	tv.setTypeface(type);
    	tv = (TextView)findViewById(R.id.perfil_exp_set);
    	tv.setTypeface(type);
    	String expe = ""+ User_Info.porcentaje_exp();
    	int index = expe.indexOf(".");
    	if(index == -1) expe = expe + ".0";
    	else expe = expe.substring(0, index+2);
    	tv.setText(expe);
    	tv = (TextView)findViewById(R.id.perfil_coins);
    	tv.setTypeface(type);
    	tv = (TextView)findViewById(R.id.perfil_coins_set);
    	tv.setTypeface(type);
    	tv.setText(""+User_Info.coins);
    	
    	int i = (int)User_Info.porcentaje_exp();
    	
    	ProgressBar pb = (ProgressBar)findViewById(R.id.perfil_exp_bar);
    	pb.setProgress(i);
    	
    }
  //-----------------------------------------------------------------------------------------------------------------------------    //

	public void addListenerOnButton() {
   	 
		boton_armario = (ImageButton) findViewById(R.id.b_armario);
 
		boton_armario.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			   Toast.makeText(Habitacion.this,"Mantén el dedo presionado!! Descubrirás nuevos looks para tu Buuh!", Toast.LENGTH_SHORT).show();
 
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
 
			   Toast.makeText(Habitacion.this,"Mantén el dedo presionado!! Y empieza a ganar experiencia y coins", Toast.LENGTH_SHORT).show();
 
			}
		});
		
		boton_mando.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("com.happybuh.JUEGO");
				startActivity(i);
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
    
    @Override
	public void onBackPressed() {
    	Toast.makeText(getApplicationContext(), "hola",  Toast.LENGTH_SHORT).show();
    	//super.onBackPressed();
	}

    
    static void ChangeBuhAppearance(ImageView a, int num, int objeto) {
    	if(objeto == 1) {
    		String cuerpo = "buh_" + User_Info.color_name.toLowerCase();
    		a.setImageResource(c.getResources().getIdentifier("drawable/" + cuerpo, null, c.getPackageName()));
    	}
    	else if (objeto == 2) {
    		int numg = Integer.parseInt(User_Info.num_glasses);
    	    if (numg > 0) {
    		    String gafas = "gafas_" + User_Info.num_glasses + "_" + User_Info.col_glasses;
    		    a.setImageResource(c.getResources().getIdentifier("drawable/" + gafas, null, c.getPackageName()));
    		    a.setVisibility(View.VISIBLE);
    	    }
    	    else a.setVisibility(View.INVISIBLE);
    	}
    	
    	else if (objeto == 3) {
    		int numb = Integer.parseInt(User_Info.num_beard);
    	    if (numb > 0) {
    		    String barba = "barba_" + User_Info.num_beard + "_" + User_Info.col_beard;
    		    a.setImageResource(c.getResources().getIdentifier("drawable/" + barba, null, c.getPackageName()));
    		    a.setVisibility(View.VISIBLE);
    	    }
    	    else a.setVisibility(View.INVISIBLE);
    	}
    	
    	if(a.getVisibility() == View.VISIBLE && num == 0) {
    		a.startAnimation(AnimationUtils.loadAnimation(c, R.anim.buh_move));
    	}
    	else if(a.getVisibility() == View.VISIBLE && num != 0) {
    		a.startAnimation(AnimationUtils.loadAnimation(c, R.anim.buh_desaparece));
    	}
//    	iv_buh.startAnimation(AnimationUtils.loadAnimation(this, R.anim.carga_fantasma));
    }
    
    public void help(View v) {
		Intent i = new Intent("com.happybuh.HELP");
		startActivity(i);
	}
}