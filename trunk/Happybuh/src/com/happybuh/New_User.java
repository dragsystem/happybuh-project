package com.happybuh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class New_User extends Activity {
	Typeface type;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
        ImageView myImage = (ImageView) findViewById(R.id.imagen_presentacion);
        myImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.new_user_anim));
        TextView tv = (TextView)findViewById(R.id.textView1);
        tv.setTypeface(type);
        EditText et = (EditText)findViewById(R.id.new_user);
        et.setTypeface(type);
        
        ImageButton b = (ImageButton)findViewById(R.id.b_new_user);        
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText user = (EditText)findViewById(R.id.new_user);
		    	String username = user.getText().toString();
		    	if(username.length() < 5) {
		    		Toast t = Toast.makeText(getApplicationContext(), "El usuario debe contener al menos 5 carácteres", Toast.LENGTH_LONG);
		    		t.show();
		    	}
		    	else {
		    		try {
			    		VG_Database entry = new VG_Database(New_User.this);
			    		entry.open();
			    		Long n = entry.createEntry_userinfo(username);
			    		entry.close();
			    		crear_entrada_color();
			    		crear_entrada_glass();
			    		crear_entrada_beard();
			    		Intent i = new Intent("com.happybuh.HABITACION");
						startActivity(i);
		    		} catch (Exception e) {
						e.printStackTrace();
					}finally {
						finish();
					}
		    	}
			}

			private void crear_entrada_color() {
				// TODO Auto-generated method stub
				VG_Database db = new VG_Database(New_User.this);
				db.open();
				Long n = db.Insert_color("blue", 0, 0, 1);
				Log.v("INSERTO AZUL" , "" + n);
				Long d = db.Insert_color("red", 100, 5, 0);
				Log.v("INSERTO rojo" , "" + d);
				Long a = db.Insert_color("yellow", 200, 7, 0);
				Log.v("INSERTO AZUL" , "" + a);
				Long b = db.Insert_color("green", 300, 9, 0);
				Log.v("INSERTO AZUL" , "" + b);
				Long c = db.Insert_color("black", 400, 20, 0);
				Log.v("INSERTO AZUL" , "" + c);
				db.close();
			}
			
			private void crear_entrada_glass() {
				// TODO Auto-generated method stub
				VG_Database db = new VG_Database(New_User.this);
				db.open();
				db.Insert_glass(0, 0, 0, 0, 1);
				db.Insert_glass(1, 0, 100, 2, 0);
				db.Insert_glass(1, 1, 200, 4, 0);
				db.Insert_glass(1, 2, 300, 8, 0);
				db.Insert_glass(1, 3, 400, 10, 0);
				db.Insert_glass(1, 4, 500, 14, 0);
				db.Insert_glass(2, 0, 800, 15, 0);
				db.Insert_glass(2, 1, 1000, 17, 0);
				db.Insert_glass(2, 2, 1200, 19, 0);
				db.Insert_glass(2, 3, 1400, 20, 0);
				db.Insert_glass(2, 4, 1600, 23, 0);
				db.Insert_glass(3, 0, 2000, 25, 0);
				db.Insert_glass(3, 1, 2300, 27, 0);
				db.Insert_glass(3, 2, 2600, 30, 0);
				db.Insert_glass(3, 3, 2900, 34, 0);
				db.Insert_glass(3, 4, 3000, 36, 0);
				db.close();
			}
			
			private void crear_entrada_beard() {
				// TODO Auto-generated method stub
				VG_Database db = new VG_Database(New_User.this);
				db.open();
				db.Insert_beard(0, 0, 0, 0, 1);
				db.Insert_beard(1, 0, 100, 2, 0);
				db.Insert_beard(1, 1, 200, 4, 0);
				db.Insert_beard(1, 2, 300, 8, 0);
				db.Insert_beard(1, 3, 400, 10, 0);
				db.Insert_beard(1, 4, 500, 14, 0);
				db.Insert_beard(2, 0, 800, 15, 0);
				db.Insert_beard(2, 1, 1000, 17, 0);
				db.Insert_beard(2, 2, 1200, 19, 0);
				db.Insert_beard(2, 3, 1400, 20, 0);
				db.Insert_beard(2, 4, 1600, 23, 0);
				db.Insert_beard(3, 0, 2000, 25, 0);
				db.Insert_beard(3, 1, 2300, 27, 0);
				db.Insert_beard(3, 2, 2600, 30, 0);
				db.Insert_beard(3, 3, 2900, 34, 0);
				db.Insert_beard(3, 4, 3000, 36, 0);
				db.close();
			}
			
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_user, menu);
        return true;
    }

}
