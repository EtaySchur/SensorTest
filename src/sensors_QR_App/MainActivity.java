package sensors_QR_App;



import java.util.List;

import com.example.sensortest.R;
import com.example.sensortest.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button						qrScanButton;
	private Button						generateQRcode;
	private Button						sensorsButton;
	private SensorManager				sensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		qrScanButton   = (Button) findViewById (id.QRscan);
		generateQRcode = (Button) findViewById (id.GenerateCode);
		sensorsButton  = (Button) findViewById (id.Sensors);
		sensorManager  = (SensorManager) getSystemService(Context.SENSOR_SERVICE );
		//List sensors
		List <Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		for(int i =0 ; i < sensors.size() ; i++){
			System.out.println(sensors.get(i).getName());
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}
	
	public void startQrScan(View v)
	{
		startActivity( new Intent( this , QRScanActivity.class ) );
	}
	public void generateQRcode(View v)
	{
		startActivity( new Intent( this , GenerateQRCodeActivity.class ) );
	}
	public void Sensor(View v)
	{
		startActivity( new Intent( this , SensorsActivity.class ) );
	}
	
	

}
