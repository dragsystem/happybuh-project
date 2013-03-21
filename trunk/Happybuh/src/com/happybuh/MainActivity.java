package com.happybuh;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageView myImage = (ImageView) findViewById(R.id.buh);
        //myImage.setAlpha(127);
        myImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.carga_fantasma));
        ImageView myImage2 = (ImageView) findViewById(R.id.presenta);
        myImage2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.presentacion));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}