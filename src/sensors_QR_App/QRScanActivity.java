package sensors_QR_App;

import com.example.sensortest.R;
import com.example.sensortest.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class QRScanActivity extends Activity {
	IntentIntegrator integrator;
	TextView resultText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qrscan);
		resultText = (TextView) findViewById(id.scanText);
	    integrator = new IntentIntegrator(this);
	    integrator.initiateScan();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_qrscan, menu);
		return true;
	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
		String result = scanResult.getContents();
		resultText.setText(result);
		integrator.shareText(result);
		integrator.getTargetApplications();
	
		
		} 
		finish();}
	
	

}
