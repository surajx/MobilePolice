package com.pyro.mobilepolice.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.pyro.mobilepolice.R;
import com.pyro.mobilepolice.task.WritingTask;
import com.pyro.mobilepolice.utils.Utils;

public class MenuActivity extends FragmentActivity {
	ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		ImageButton imgPin = (ImageButton) findViewById(R.id.imgBtnPIN);
		imgPin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MenuActivity.this,
						PinChangeActivity.class);
				startActivity(i);

			}
		});

		ImageButton imghelp = (ImageButton) findViewById(R.id.imgHelp);
		imghelp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				progressDialog = Utils.showProgressDialogWithMessage(
						"Saving contacts and message", MenuActivity.this);
				WritingTask writingTask = new WritingTask(
						getApplicationContext(), MenuActivity.this);
				writingTask.execute("Send");

			}
		});
	}

	public void afterSave(String result) {
		Utils.dismissProgressDialog(progressDialog);
		Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
