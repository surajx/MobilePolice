package com.pyro.mobilepolice.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pyro.mobilepolice.R;
import com.pyro.mobilepolice.data.PreferenceManager;

public class PinChangeActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		Button btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				EditText txtPin = (EditText) findViewById(R.id.txtPIN);
				String pin = txtPin.getText().toString();
				if (pin == null || pin.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"You must enter a PIN.", Toast.LENGTH_LONG).show();
					return;
				}
				if (pin.length() < 4) {
					Toast.makeText(getApplicationContext(),
							"PIN must be 4 or more characters long.",
							Toast.LENGTH_LONG).show();
					return;
				}
				PreferenceManager preferenceManager = PreferenceManager
						.getInstance();
				preferenceManager.setContext(getApplicationContext());
				preferenceManager.putPINValue(pin);
				Toast.makeText(getApplicationContext(), "PIN Saved",
						Toast.LENGTH_LONG).show();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
