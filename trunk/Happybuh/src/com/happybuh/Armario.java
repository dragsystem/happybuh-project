package com.happybuh;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Armario extends ListActivity {
	
	String classes[] = {"ChangeColor", "ChangeGlasses", "ChangeBeard"};
	//private ListView prueba = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_armario);
        
        
        
        //setListAdapter(new ArrayAdapter<String>(Armario.this,android.R.layout.simple_expandable_list_item_1,classes));
        
        
        //Cogemos la view de la list
      //  prueba = (ListView)findViewById(R.id.listView1);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,classes);
        
        //prueba.setAdapter(adapter);
        
        
        //PRUEBA FINAL
 
        List<Closet_Info> info = new ArrayList<Closet_Info>();
        info.add(new Closet_Info("ChangeColor"));
        info.add(new Closet_Info("ChangeGlasses"));
        info.add(new Closet_Info("ChangeBeard"));
        setListAdapter(new Closer_Adapter(this, info));
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
