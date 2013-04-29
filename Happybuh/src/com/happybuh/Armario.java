package com.happybuh;


import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Armario extends Activity {
	private Button b;
	private TextView tv;
	private Typeface type;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_armario);
		 
		 type = Typeface.createFromAsset(this.getAssets(), "neuropol.ttf");
		 
		 b = (Button)findViewById(R.id.armario_b_color);
		 b.setTypeface(type);
		 b = (Button)findViewById(R.id.armario_b_gafas);
		 b.setTypeface(type);
		 b = (Button)findViewById(R.id.armario_b_barba);
		 b.setTypeface(type);
		 
		 tv = (TextView)findViewById(R.id.armario_titulo);
		 tv.setTypeface(type);
		 
		 tv.setText("�Qu� deseas cambiar de " +User_Info.name + "?");
	}
	
	public void ChangeColor(View v) {
		Intent i = new Intent("com.happybuh.CHANGECOLOR");
		startActivity(i);
	}
	public void ChangeGafas(View v) {
		Intent i = new Intent("com.happybuh.CHANGEGLASSES");
		startActivity(i);
	}
	public void ChangeBarba(View v) {
		Intent i = new Intent("com.happybuh.CHANGEBEARD");
		startActivity(i);
	}
	public void volver(View v) {
		User_Info.inicializar(getApplicationContext());
		GV.Activities.habitacion.handler.sendEmptyMessage(1);
		Random rand = new Random();
		int num = rand.nextInt(2);
		ImageView aux = GV.Activities.habitacion.iv_buh;
		GV.Activities.habitacion.ChangeBuhAppearance(aux, num, 1);
		aux = GV.Activities.habitacion.iv_gafas;
		GV.Activities.habitacion.ChangeBuhAppearance(aux, num, 2);
		aux = GV.Activities.habitacion.iv_barba;
		GV.Activities.habitacion.ChangeBuhAppearance(aux, num, 3);
    	finish();
	}
	@Override
	public void onBackPressed() {
		User_Info.inicializar(getApplicationContext());
		GV.Activities.habitacion.handler.sendEmptyMessage(1);
		Random rand = new Random();
		int num = rand.nextInt(2);
		ImageView aux = GV.Activities.habitacion.iv_buh;
		GV.Activities.habitacion.ChangeBuhAppearance(aux, num, 1);
		aux = GV.Activities.habitacion.iv_gafas;
		GV.Activities.habitacion.ChangeBuhAppearance(aux, num, 2);
		aux = GV.Activities.habitacion.iv_barba;
		GV.Activities.habitacion.ChangeBuhAppearance(aux, num, 3);
    	finish();
		//super.onBackPressed();
	}
	
	
}