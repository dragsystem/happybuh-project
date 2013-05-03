package com.happybuh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;



import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class CrearMapa {
	
	ArrayList<Bitmap> bitmaps;
	ArrayList<InputStream> pantallas;
	Resources resources;
	BitmapFactory.Options options;
	public boolean acabat;
	private Random rand;
	private int tp;
	private int surt = 0;
	public Protagonista prot;
	
	public CrearMapa(int pant) {
		GV.puntuacio_world.control = 0;
		GV.puntuacio_world.plataformes_mapa = new ArrayList<WorldObjecte>();
		GV.puntuacio_world.plataformes = new ArrayList<Plataforma>();
		GV.puntuacio_world.monstres = new ArrayList<Monster>();
		GV.puntuacio_world.lacasitos = new ArrayList<Lacasito>();
		GV.puntuacio_world.tramps = new ArrayList<Tramps>();
		GV.puntuacio_world.galleta_d = new ArrayList<Disparo>();
		pantallas = new ArrayList<InputStream>();
		bitmaps = new ArrayList<Bitmap>();
		options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		resources = GV.Activities.worldgame.getResources();
		rand = new Random();
		tp = 17;
		pantallas.add(resources.openRawResource(R.raw.pantalla2));
		loadbitmaps();
		nova_pantalla(pant);
	}
	
	public void nova_pantalla(int x){
		
		acabat = false;
		try {
			if(x < 1)load_stage(x);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load_stage(int p) throws IOException {
		String str="";
		StringBuffer buf = new StringBuffer();
		InputStream is = pantallas.get(p);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		if (is!=null) {							
			while ((str = reader.readLine()) != null) {	
				buf.append(str + "\n" );
			}				
		}		
		is.close();
		
		str = buf.toString();
		String[] sa = str.split(" ");
		int j = 16;
		int i = 0;
		for(int z=0; z<sa.length; z++) {
			if (!sa[z].equals("-1") && !sa[z].equals("\n")) {
				float tx = (float)(GV.Screen.metrics.widthPixels*0.075);
				float ty = (float)((GV.Screen.metrics.heightPixels/1.5)*0.075);
				float x  = tx*i;
				float y = (GV.Screen.metrics.heightPixels)-(ty*j);
				if(sa[z].equals("4")) {
					//SPRITE COCO
					y = 0;
					GV.puntuacio_world.monstres.add(new Monster(bitmaps.get( Integer.valueOf(sa[z]) ),x,y,tx,tx,10,0,2,1,2));
				}
				else if(sa[z].equals("5")) {
					//SPRITE LLAMAS
					y += ty;
					ty = (float)((GV.Screen.metrics.heightPixels/5));
					GV.puntuacio_world.tramps.add(new Tramps(bitmaps.get( Integer.valueOf(sa[z]) ),x,y,tx,ty,10,0,6,1,4));
				}
				else if(sa[z].equals("6")) {
					//SPRITE PINCHOS
					y += ty;
					ty = (float)((GV.Screen.metrics.heightPixels/5));
					GV.puntuacio_world.tramps.add(new Tramps(bitmaps.get( Integer.valueOf(sa[z]) ),x,y,tx,ty,10,0,4,1,3));
				}
				else if(sa[z].equals("7")) {
					//CASA FINAL
					GV.puntuacio_world.plataforma_casa = GV.puntuacio_world.plataformes_mapa.size()-1;
					y += ty;
					tx = (float)(GV.Screen.metrics.widthPixels*0.075)*2.5f;
					ty = (float)((GV.Screen.metrics.heightPixels/1.5)*0.075)*8f;
					y = y - ty;
					GV.puntuacio_world.plataformes_mapa.add(new WorldObjecte(Integer.valueOf(sa[z]),x, y, 0, 0, tx, ty, 0,bitmaps.get( Integer.valueOf(sa[z]) )) );
				}
				else if(sa[z].equals("9")) {
					//CHUPACHUPS
					y = (GV.Screen.metrics.heightPixels)-(ty*3.5f);
					ty = tx + tx*0.82f;					
					y -= ty;
					GV.puntuacio_world.plataformes_mapa.add(new WorldObjecte(Integer.valueOf(sa[z]),x, y, 0, 0, tx, ty, 0,bitmaps.get( Integer.valueOf(sa[z]) ) ) );
				}
				else if(sa[z].equals("8")) {
					//MONEDAS
					GV.puntuacio_world.num_monedas++;
					GV.puntuacio_world.lacasitos.add(new Lacasito(bitmaps.get( Integer.valueOf(sa[z]) ), x, y, tx, ty));
				}
				else if(sa[z].equals("3")) {
					//PLATAFORMA CARAMELO
					tx *= 2;
					ty *= 2;
					GV.puntuacio_world.plataformes.add(new Plataforma(bitmaps.get( Integer.valueOf(sa[z]) ), x, y, tx, ty));
				}
				else if(sa[z].equals("10")) {
					GV.puntuacio_world.plataforma_limite = GV.puntuacio_world.plataformes_mapa.size()-1;
					GV.puntuacio_world.plataformes_mapa.add(new WorldObjecte(Integer.valueOf(sa[z]),x, y, 0, 0, tx, ty, 0,bitmaps.get( Integer.valueOf(sa[z]) ) ) );
				}
				else GV.puntuacio_world.plataformes_mapa.add(new WorldObjecte(Integer.valueOf(sa[z]),x, y, 0, 0, tx, ty, 0,bitmaps.get( Integer.valueOf(sa[z]) )) );
			}
			++i;
			
			if (sa[z].equals("\n")) {
				if(j == 4) GV.puntuacio_world.pos0 = i;
				--j;
				i = 0;
			}
		}
	}
	
	public void plataforma_limit() {
		if(GV.puntuacio_world.control == 1) {
			//DERECHA  --> PLATAFORMA IZQUIERDA  FUNCIONA!
			int p = GV.puntuacio_world.plataformes_mapa.size()-1;
			float posicion = GV.puntuacio_world.plataformes_mapa.get(p).x;
			float piezax = GV.puntuacio_world.plataformes_mapa.get(p).x + GV.puntuacio_world.plataformes_mapa.get(p).tx;
			if ((piezax - GV.Screen.metrics.widthPixels) < GV.puntuacio_world.plataformes_mapa.get(p).tx && piezax > GV.Screen.metrics.widthPixels ) {
				GV.puntuacio_world.desplazamiento = - (piezax - GV.Screen.metrics.widthPixels)*0.25f;
			}
			else if (piezax == GV.Screen.metrics.widthPixels) GV.puntuacio_world.desplazamiento = 0;
			else GV.puntuacio_world.desplazamiento = -GV.widthpc(0.02f);
		}
		else if (GV.puntuacio_world.control == 0){
			GV.puntuacio_world.desplazamiento = 0;
		}
		else if (GV.puntuacio_world.control == 3){
			int index = GV.puntuacio_world.plataforma_limite;
			float posicionx = GV.puntuacio_world.plataformes_mapa.get(index).x;
			float posicionx_inicial = GV.puntuacio_world.plataformes_mapa.get(index).posicio_inicial;
			System.out.println("CREARMAPA posicion de la columna " + posicionx);
			if (posicionx >= posicionx_inicial) {
				GV.puntuacio_world.desplazamiento = 0;
			}
			else GV.puntuacio_world.desplazamiento = GV.widthpc(0.02f);
		}
		
		if(GV.puntuacio_world.prot.colisio_casa(GV.puntuacio_world.plataformes_mapa.get(GV.puntuacio_world.plataforma_casa))){
			GV.Activities.worldgame.handler.sendEmptyMessage(5);
		}
	}
	
	private void loadbitmaps() {
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.azucar, options)); 		// pos 0
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.bizcocho, options));		// pos 1
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.bizcocho2, options));	// pos 2
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.plataforma2, options));		// pos 3 PIRULETA GRANDE
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.galleta2, options));		// pos 4 COCO
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.llamas, options)); //pos 5 Llamas
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.pinchos, options)); //pos 6 Pinchos
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.casaf, options)); //Casita Final (7)
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.moneda_world, options)); //pos 8
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.chupachups, options)); //pos 9
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.bizcocho_borde, options)); //pos 10
/*		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.lacasito2, options)); //pos 9 
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.lacasito3, options)); //pos 10 
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.lacasito4, options)); //pos 11
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.lacasito5, options)); //pos 12 
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.lacasito6, options)); //pos 13 
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.lacasito7, options)); //pos 14 
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.lacasito8, options)); //pos 15
		bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.lacasito9, options)); //pos 16*/
		//bitmaps.add(BitmapFactory.decodeResource(resources, R.drawable.chupachups, options)); //pos 17
	}
	
	public void draw(Canvas canvas){
		for(int i = 0; i < GV.puntuacio_world.plataformes.size(); ++i) {
			if(GV.puntuacio_world.fast_die != 1) GV.puntuacio_world.plataformes.get(i).choque();
			GV.puntuacio_world.plataformes.get(i).actualitza(GV.puntuacio_world.desplazamiento);
			GV.puntuacio_world.plataformes.get(i).draw(canvas);
		}
		
		for (int i = 0; i < GV.puntuacio_world.plataformes_mapa.size(); ++i) {
				GV.puntuacio_world.plataformes_mapa.get(i).actualitza(GV.puntuacio_world.desplazamiento);
				GV.puntuacio_world.plataformes_mapa.get(i).draw(canvas);
		}
		
		for(int i = 0; i < GV.puntuacio_world.monstres.size(); ++i) {
				GV.puntuacio_world.monstres.get(i).actualitza(GV.puntuacio_world.desplazamiento);
				GV.puntuacio_world.monstres.get(i).draw(canvas);
		}
		
		for(int i = 0; i < GV.puntuacio_world.lacasitos.size(); ++i) {
			int destruccio = GV.puntuacio_world.lacasitos.get(i).choques(i);
			if(destruccio != -1){
				GV.puntuacio_world.lacasitos.remove(i);
				GV.puntuacio_world.coins += 2 + rand.nextInt(3);
				GV.Activities.worldgame.handler.sendEmptyMessage(2);
			}
			else {
				GV.puntuacio_world.lacasitos.get(i).actualitza(GV.puntuacio_world.desplazamiento);
				GV.puntuacio_world.lacasitos.get(i).draw(canvas);
			}
		}
		
		if(!GV.puntuacio_world.galleta_d.isEmpty()) {
			for (int i = 0; i < GV.puntuacio_world.galleta_d.size(); ++i) {
				float posy = GV.puntuacio_world.galleta_d.get(i).y; 
				Disparo aux = GV.puntuacio_world.galleta_d.get(i);
				int destruccio = GV.puntuacio_world.galleta_d.get(i).choque(i);
				if(posy > GV.Screen.metrics.heightPixels) {
					//GV.puntuacio_world.galleta_d.get(j).img.recycle();
					GV.puntuacio_world.galleta_d.remove(i);
					--i;
				}
				else if(destruccio != -1) GV.puntuacio_world.galleta_d.remove(i);
				else {
					GV.puntuacio_world.galleta_d.get(i).actualitza(GV.puntuacio_world.desplazamiento);
					GV.puntuacio_world.galleta_d.get(i).draw(canvas);
				}
			}
		}
		
		for(int i = 0; i < GV.puntuacio_world.tramps.size(); ++i) {
			GV.puntuacio_world.tramps.get(i).choque();
			GV.puntuacio_world.tramps.get(i).actualitza(GV.puntuacio_world.desplazamiento);
			GV.puntuacio_world.tramps.get(i).draw(canvas);
		}
	}
	
}
