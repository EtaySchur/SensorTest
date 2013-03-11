package sensors_QR_App;

import com.example.sensortest.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;


public class GenerateQRCodeActivity extends Activity {
	IntentIntegrator integrator;
	String QRcode;
	EditText  editCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generate_qrcode);
		editCode = (EditText) findViewById  (R.id.editCode);
	    integrator = new IntentIntegrator(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_generate_qrcode, menu);
		return true;
	}
	public void showQRCode(View v)
	{
		//Generate QR
		QRcode = editCode.getText().toString();
		integrator.shareText(QRcode);
	}

}
