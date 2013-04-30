package com.happybuh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class JumpGameSurfaceView extends SurfaceView implements SensorEventListener {
	//control
	private SurfaceHolder holder;
	private JumpGameThread thread;
	//sensors
	private SensorManager msensorManager;
	float ultimAccel[] = null;
	float ultimMF[] = null;
	float rotationMatrix[];
	float orientation[];
	float yAct;
	//joc
	private Plataformes plataforma;
	private Jugador jugador;
	//fons
	Paint fons;
	//control thread
	private boolean surfacecreated;
	private boolean threadrunning;
	
	private boolean entrar;
	
	public JumpGameSurfaceView(Context context) {
		super(context);
		init(context);
	}
	
	public JumpGameSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public JumpGameSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public void init(Context context) {
		//Vides joc i jugador
		GV.puntuacio_jump.vides = 2;
		GV.puntuacio_jump.gameover = 0;
		//dades globals
		GV.Instancies.jumpview = this;
		//control
		surfacecreated = false;
		threadrunning = false;
		//sensors
		msensorManager = (SensorManager) GV.Activities.jumpgame.getSystemService(Context.SENSOR_SERVICE);
		registerListeners();
		rotationMatrix = new float[9];
		orientation = new float[3];
		ultimAccel = new float[3];
		ultimMF = new float[3];
		
		//surfaceview
		holder = getHolder();
		holder.addCallback( new Callback() {
	
			public void surfaceDestroyed(SurfaceHolder holder) {
				Log.w("SURFACE GO DESTROYED","Surfacebackground destroyed, stopping threads");
				stopthread();
				surfacecreated = false;
			}
			
			
			public void surfaceCreated(SurfaceHolder holder) {
				holder.setFormat(PixelFormat.RGBA_8888);
				Log.w("SURFACE GO CREATED","Surfacebackground created");
				//inicialització estructures
				fons = new Paint(); 
				Shader shader = new SweepGradient(0,0,0xFFFFCC00, 0xFFFFCC33);
			    fons.setShader(shader);  
				plataforma = new Plataformes();
				jugador = new Jugador(0f);
				//avis de creació
				surfacecreated = true;
				GV.posiplataforma.modplataforma = false;
				GV.posiplataforma.idplat = 0;
				GV.puntuacio_jump.vides = 2;
				GV.puntuacio_jump.coins = 0;
				GV.puntuacio_jump.gameover = 0;
				GV.puntuacio_jump.pause = 0;
				GV.puntuacio_jump.get_exp = 0;
				GV.puntuacio_jump.termina = 0;
			}
			
			
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				Log.w("SURFACE GO CHANGED","Surfacebackground changed, starting threads");
				startthread();
			}
		});
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawRect(0,0,getWidth(),getHeight(), fons);
		if(GV.puntuacio_jump.gameover == 0) {
			if(GV.puntuacio_jump.pause == 0)  {
				jugador.actualitza(yAct, plataforma);
				plataforma.actualitza(jugador);
			}
			jugador.draw(canvas);
			plataforma.draw(canvas, jugador.getX()-100, getWidth());
		}
		else {
			jugador.draw(canvas);
			plataforma.actualitza_gameover();
			plataforma.draw(canvas, jugador.getX()-100, getWidth());
			if(GV.puntuacio_jump.termina == 1)
				GV.Activities.jumpgame.handler.sendEmptyMessage(3);
		}
		
		//GV.Activities.jumpgame.handler.sendEmptyMessage(0);
//		if (GV.posiplataforma.modplataforma) {
//			plataforma.actualitza(jugador);
//			//plataforma.draw(canvas);
//			plataforma.draw(canvas, jugador.getX()-100, getWidth());
//			GV.posiplataforma.modplataforma = false;
//		}
//		else {
//			plataforma.actualitza2();
//			plataforma.draw(canvas, jugador.getX()-100, getWidth());
//		}
	}
	
	
	
	private void registerListeners() {
        msensorManager.registerListener(this, msensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
        msensorManager.registerListener(this, msensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }
	
	private void unregisterListeners() {
        msensorManager.unregisterListener(this);
    }


	public void onAccuracyChanged(Sensor sensor, int accuracy) {		
	}


	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometer(event);
        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetic_field(event);
        }
    }
 
    private void accelerometer(SensorEvent event) {
    
        System.arraycopy(event.values, 0, ultimAccel, 0, 3);
 
        /* Podriem posar-ho pero suposa una sobrecàrrega ja que ens interessa mes el magnetic que no pass l'accel
            computeOrientation();
        */
    }
 
    private void magnetic_field(SensorEvent event) {
        System.arraycopy(event.values, 0, ultimMF, 0, 3);
        computeOrientation();
    }
	
	private void computeOrientation() {
		/* rotationMatrix té la informació dels sensors, i l'hem de adaptar segons la orientació de la pantalla (0º, 90º...), default 0º */
        if (SensorManager.getRotationMatrix(rotationMatrix, null, ultimAccel, ultimMF)) {
        	float orientedRotationMatrix[] = new float[9];
        	if(GV.Screen.Orientation == 0) System.arraycopy(rotationMatrix, 0, orientedRotationMatrix, 0, 9);	//0º, mateixa matriu
        	if(GV.Screen.Orientation == 1) SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, orientedRotationMatrix); //90º
            if(GV.Screen.Orientation == 2) SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_MINUS_X, SensorManager.AXIS_MINUS_Y, orientedRotationMatrix); //180º
            if(GV.Screen.Orientation == 3) SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X, orientedRotationMatrix); //270º
        	
        	SensorManager.getOrientation(orientedRotationMatrix, orientation);
            //estan en radians!
            float z = orientation[0];
            float x = orientation[1];
            float y = orientation[2];
            yAct = y;
        }
    }
	
	
	public void startthread() {
		if(!surfacecreated) return;
		if(threadrunning) return;
		thread = new JumpGameThread(this);
		thread.setRunning(true);
		thread.start();
		registerListeners();
		threadrunning = true;
	}
	public void stopthread() {
		if(!surfacecreated) return;
		if(!threadrunning) return;
		boolean retry = true;
		while (retry) {
			try {
				thread.setRunning(false);
				thread.join();
				threadrunning = false;
				retry = false;
				
			} catch (InterruptedException e) {}
		}
	}

}