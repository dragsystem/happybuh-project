package com.happybuh;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Closer_Adapter extends BaseAdapter {
	
	private LayoutInflater li;
	private List<Closet_Info> info = new ArrayList<Closet_Info>();
	private Typeface type;
	private Context context;
	public Closer_Adapter (Context c, List<Closet_Info> items) {
		li = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(items != null) info = items;
		context = c;
	}
	
	@Override
	public int getCount() {
		return info.size();
	}

	@Override
	public Object getItem(int position) {
		return info.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		type = Typeface.createFromAsset(context.getAssets(), "neuropol.ttf");
        View v = convertView;
 
        final Closet_Info infos = info.get(position);
        
 
        //if View exists, then reuse... else create a new object.
        if (v == null) {
            v = li.inflate(com.happybuh.R.layout.closet_info_layout, null);
        }
 
        //reference to the ImageView component
        final ImageView image1 = (ImageView)v.findViewById(com.happybuh.R.id.closet_img1);
        //final ImageView image2 = (ImageView)v.findViewById(com.happybuh.R.id.closet_img2);
        final TextView closet_name = (TextView) v.findViewById(com.happybuh.R.id.closet_name);
        
        String name = infos.getName();
        if(name == "ChangeColor") {
        	image1.setImageResource(R.drawable.mando_p);
        	//image2.setImageResource(R.drawable.mando_p);
        	closet_name.setText("Body Color");
        }
        else if (name == "ChangeGlasses") {
        	image1.setImageResource(R.drawable.buh);
        	//image2.setImageResource(R.drawable.buh);
        	closet_name.setText("Fashion Glasses");
        }
        else if (name == "ChangeBeard") {
        	image1.setImageResource(R.drawable.armario_abierto);
        	//image2.setImageResource(R.drawable.armario_cerrado);
        	closet_name.setText("Beards");
        }
        
        closet_name.setTypeface(type);
        return v;
	}

}
