package com.happybuh;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
			    		crear_entrada_color(entry);
			    		crear_entrada_glass(entry);
			    		crear_entrada_beard(entry);
			    		entry.createEntry_userinfo(username);
			    		entry.close();
			    		Intent i = new Intent("com.happybuh.HABITACION");
						startActivity(i);
		    		} catch (Exception e) {
						e.printStackTrace();
					}finally {
						finish();
					}
		    	}
			}

			private void crear_entrada_color(VG_Database db) {
				// TODO Auto-generated method stub
				db.Insert_color("Blue", 0, 0, 1);
				db.Insert_color("Red", 100, 5, 0);
				db.Insert_color("Yellow", 200, 7, 0);
				db.Insert_color("Green", 300, 9, 0);
				db.Insert_color("Black", 400, 20, 0);
			}
			
			private void crear_entrada_glass(VG_Database db) {
				// TODO Auto-generated method stub
				db.Insert_glass(0, 0, 0, 0, 1);
				db.Insert_glass(1, 1, 100, 2, 0);
				db.Insert_glass(1, 2, 200, 4, 0);
				db.Insert_glass(1, 3, 300, 8, 0);
				db.Insert_glass(1, 4, 400, 10, 0);
				db.Insert_glass(1, 5, 500, 14, 0);
				db.Insert_glass(2, 1, 800, 15, 0);
				db.Insert_glass(2, 2, 1000, 17, 0);
				db.Insert_glass(2, 3, 1200, 19, 0);
				db.Insert_glass(2, 4, 1400, 20, 0);
				db.Insert_glass(2, 5, 1600, 23, 0);
				db.Insert_glass(3, 1, 2000, 25, 0);
				db.Insert_glass(3, 2, 2300, 27, 0);
				db.Insert_glass(3, 3, 2600, 30, 0);
				db.Insert_glass(3, 4, 2900, 34, 0);
				db.Insert_glass(3, 5, 3000, 36, 0);
				
			}
			
			private void crear_entrada_beard(VG_Database db) {
				// TODO Auto-generated method stub
				db.Insert_beard(0, 0, 0, 0, 1);
				db.Insert_beard(1, 1, 100, 2, 0);
				db.Insert_beard(1, 2, 200, 4, 0);
				db.Insert_beard(1, 3, 300, 8, 0);
				db.Insert_beard(1, 4, 400, 10, 0);
				db.Insert_beard(1, 5, 500, 14, 0);
				db.Insert_beard(2, 1, 800, 15, 0);
				db.Insert_beard(2, 2, 1000, 17, 0);
				db.Insert_beard(2, 3, 1200, 19, 0);
				db.Insert_beard(2, 4, 1400, 20, 0);
				db.Insert_beard(2, 5, 1600, 23, 0);
				db.Insert_beard(3, 1, 2000, 25, 0);
				db.Insert_beard(3, 2, 2300, 27, 0);
				db.Insert_beard(3, 3, 2600, 30, 0);
				db.Insert_beard(3, 4, 2900, 34, 0);
				db.Insert_beard(3, 5, 3000, 36, 0);
			}
			
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_user, menu);
        return true;
    }

}
