package com.happybuh;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Armario extends ListActivity {
	
	String classes[] = {"ChangeColor", "ChangeGlasses", "ChangeBeard"};
	//private ListView prueba = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_armario);
        setListAdapter(new ArrayAdapter<String>(Armario.this,android.R.layout.simple_expandable_list_item_1,classes));
        //Cogemos la view de la list
      //  prueba = (ListView)findViewById(R.id.listView1);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,classes);
        
        //prueba.setAdapter(adapter);
    }
	

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
    	super.onListItemClick(l, v, position, id);
    	String cheese = classes[position];
		try {
			Class ourClass = Class.forName("com.happybuh." + cheese);
			Intent ourIntent = new Intent(Armario.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
