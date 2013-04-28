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
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitacion);
        //v = li.inflate(com.happybuh.R.layout.activity_habitacion, null);
        inicializar_user();
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
								if (et.getText().toString().isEmpty()) {
									Toast t = Toast.makeText(getApplicationContext(), "a = " + a.name + "es distinta de " + User_Info.name, Toast.LENGTH_LONG);
									t.show();
								}
								else {
									VG_Database db = new VG_Database(getApplicationContext());
									db.open();
									db.setUser(db.getUserName(), et.getText().toString());
									db.close();
									tv = (TextView)findViewById(R.id.user_name);
									tv.setText(et.getText());
									tv = (TextView)findViewById(R.id.user_name2);
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
	    TextView t_armario = (TextView)findViewById(R.id.t_armario);
	    t_armario.setTypeface(type);
	    TextView t_mando = (TextView)findViewById(R.id.t_mando);
	    t_mando.setTypeface(type);
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
    
    private void inicializar_user() {
		// TODO Auto-generated method stub
    	
    	VG_Database db = new VG_Database(Habitacion.this);
	    db.open();
		    ArrayList<String> a = new ArrayList<String>();
	        a = db.info_user();
	        User_Info.name = a.get(1);
	        User_Info.level = Integer.parseInt(a.get(2));
	        User_Info.coins = Integer.parseInt(a.get(3));
	        User_Info.color = Integer.parseInt(a.get(4));
	        User_Info.glasses = Integer.parseInt(a.get(5));
	        User_Info.beard = Integer.parseInt(a.get(6));
	        
	        Long lc, lg, lb;
	        lc = Long.parseLong((String) a.get(4));
	        Log.v("INDICE COLO", lc.toString());
	        User_Info.color_name = db.getColorName(lc);
	        lg = Long.parseLong((String) a.get(5));
	        User_Info.num_glasses = db.getNumGlasses(lg);
	        User_Info.col_glasses = db.getColGlasses(lg);
	        lb = Long.parseLong((String) a.get(6));
	        User_Info.num_beard = db.getNumBeard(lb);
	        User_Info.col_beard = db.getColBeard(lb);
	    db.close();
	}

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
    
    static void ChangeBuhAppearance(ImageView a, int num, int objeto) {
    	/*iv_buh = (ImageView)v.findViewById(com.happybuh.R.id.buh);
    	iv_gafas = (ImageView)v.findViewById(com.happybuh.R.id.buh_gafas);
    	iv_barba = (ImageView)v.findViewById(com.happybuh.R.id.buh_barba);*/
    	
    	if(objeto == 1) {
    		/*if(User_Info.color_name.equals("blue")) a.setImageResource(R.drawable.buh_blue);
    		else if(User_Info.color_name.equals("red")) a.setImageResource(R.drawable.buh_red);
    		else if(User_Info.color_name.equals("yellow")) a.setImageResource(R.drawable.buh_yellow);
    		else if(User_Info.color_name.equals("green")) a.setImageResource(R.drawable.buh_green);
    		else if(User_Info.color_name.equals("black")) a.setImageResource(R.drawable.buh_black);*/
    		String cuerpo = "buh_" + User_Info.color_name;
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

}