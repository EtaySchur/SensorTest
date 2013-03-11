package sensors_QR_App;

import com.example.sensortest.R;
import com.example.sensortest.R.id;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class SensorsActivity extends Activity implements SensorEventListener{
	private SensorManager sensorManager = null ;
	private Sensor magneticSensor;
	private TextView xCord,yCord,zCord;
	private Button   magneticSensorButton;
	private Button 	 lightSensorButton;
	private Button 	 pressureSensorButton;
	private Button   accelSensorButton;
	private Button   azymuthSensorButton;
	private float[] orientVals = new float[3];
	float[] inR = new float[16];
	float[] I = new float[16];
	float[] outR= new float[16];
	float[] gravity ;
	float[] geomag ;
	final float pi = (float) Math.PI;
	final float rad2deg = 180/pi;  
	private boolean success = false;
	Context context;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_magnetic_sensor);
		context = this;
		magneticSensorButton = (Button) findViewById(id.MagneticSensorButton);
		lightSensorButton = (Button) findViewById(id.LightSensorButton);
		pressureSensorButton = (Button) findViewById(id.PressureButton);
		accelSensorButton = (Button) findViewById(id.startAccelSensorButton);
		azymuthSensorButton = (Button) findViewById(id.AzymuthSensorButton);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		xCord = (TextView) findViewById  (R.id.xCord);
		yCord = (TextView) findViewById  (R.id.yCord);
		zCord = (TextView) findViewById  (R.id.zCord);
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_linear_acceleretion, menu);
		return true;
	}
	 @Override
	    protected void onPause() {
	        // Unregister the listener
	        sensorManager.unregisterListener(this);
	        super.onPause();
	    }
	 @Override
	    protected void onStop() {
	        // Unregister the listener
	        sensorManager.unregisterListener(this);
	        super.onStop();
	    }
	 @Override
	    protected void onResume() {
	        super.onResume();

	 }
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent sensorEvent) {
       synchronized (this) {
    	   		
    	   		
            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD ) {
            	geomag = sensorEvent.values.clone();
                xCord.setText( "X Cordinate : " + Float.toString( sensorEvent.values[0]) + " Micro Tesla" );
                yCord.setText( "Y Cordinate : " + Float.toString( sensorEvent.values[1]) + " Micro Tesla" );
                zCord.setText( "Z Cordinate : " + Float.toString( sensorEvent.values[2]) + " Micro Tesla" );
                
            }
            
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER )
            {
            	gravity = sensorEvent.values.clone();
            	xCord.setText( "X Cordinate : " + Float.toString( sensorEvent.values[0]) + " Meter/Sec^2" );
                yCord.setText( "Y Cordinate : " + Float.toString( sensorEvent.values[1]) + " Meter/Sec^2" );
                zCord.setText( "Z Cordinate : " + Float.toString( sensorEvent.values[2]) + " Meter/Sec^2" );
            }
            
           if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT)
            {
         
        	   float max =  sensorEvent.values[0];
            	 xCord.setText( "Light % : " + Float.toString(max));
            	 yCord.setText("");
            	 zCord.setText("");
            }
           
           if(sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE)
           {
        	   xCord.setText("Pressure " + Float.toString(sensorEvent.values[0]));
        	   yCord.setText("");
        	   zCord.setText("");
           }
          
            if (gravity != null && geomag != null){
			   success = SensorManager.getRotationMatrix(inR, I, gravity, geomag);
			   		if (success){
			   				SensorManager.getOrientation(inR, orientVals);
			   				float azimuth = orientVals[0]*rad2deg;
			              xCord.setText("AZIMUTH: " + Float.toString(azimuth) + " Degrees" );
			    }
            }
            
       }
        
        

    }
	 public void startMagneticSensor(View v)
	 {
		 gravity=null;
		 sensorManager.unregisterListener(this);
		 sensorManager.registerListener(this,
	                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
	                SensorManager.SENSOR_DELAY_NORMAL);
	 }
	 
	 public void startAccelSensor(View v){
		 geomag=null;
		 sensorManager.unregisterListener(this);
		 sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	                SensorManager.SENSOR_DELAY_NORMAL);
	 }
	 
	 
	public void startAzymuthSensor(View v){
		 sensorManager.unregisterListener(this);
		 sensorManager.registerListener(this,
	                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
	                SensorManager.SENSOR_DELAY_NORMAL);
		 sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	                SensorManager.SENSOR_DELAY_NORMAL); 
	 }
	
	public void startLightSensor(View v){
		 sensorManager.unregisterListener(this);
		 sensorManager.registerListener(this,
	                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
	                SensorManager.SENSOR_DELAY_NORMAL); 
	}
	
	public void startPressureSensor(View v){
		 Sensor pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
		 if (pressureSensor == null )
		 {
			 Toast toast = Toast.makeText(context, "This Android dont support Pressure Sensor", Toast.LENGTH_SHORT);
			 toast.show();
			 return;
		 }
		 sensorManager.unregisterListener(this);
		 sensorManager.registerListener(this,
	                sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
	                SensorManager.SENSOR_DELAY_NORMAL); 
	}
	 
}
