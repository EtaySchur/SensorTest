package sensors_QR_App;

import com.example.sensortest.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AccelerationSensorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceleration_sensor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_acceleration_sensor, menu);
		return true;
	}

}
