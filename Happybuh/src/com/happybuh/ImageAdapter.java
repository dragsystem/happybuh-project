package com.happybuh;

import java.util.Random;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Random rand;

    public ImageAdapter(Context c) {
        mContext = c;
        rand = new Random();
        GV.puntuacio_search.index = rand.nextInt(3);
    }

    public int getCount() {
        return GV.ristras.mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        

        switch(GV.puntuacio_search.index) {
	        case 0:
	        	imageView.setImageResource(GV.ristras.mThumbIds[position]);
	        	break;
	        case 1:
	        	imageView.setImageResource(GV.ristras.mThumbIds2[position]);
	        	break;
	        case 2:
	        	imageView.setImageResource(GV.ristras.mThumbIds3[position]);
	        	break;
	        case 3:
	        	imageView.setImageResource(GV.ristras.mThumbIds4[position]);
	        	break;
        }
        /*Integer[] aux = new Integer[GV.ristras.mThumbIds.length];
        int pos = 0;
        while (aux.length != pos) {
        	index = rand.nextInt(GV.ristras.mThumbIds.length-1);
        	boolean trobat = false;
        	for (int i = 0; i < pos; ++i) {
        		if(aux[i] == GV.ristras.mThumbIds[index]) {
        			trobat = true;
        			break;
        		}
        	}
        	if (!trobat) {
        		aux[pos] = GV.ristras.mThumbIds[index];
        		++pos;
        	}
        }*/
     
        
        
        return imageView;
    }

    // references to our images
    
}
