package com.pyrocrypt.mobilepolice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.pyrocrypt.mobilepolice.R;

public class SplashScreenActivity extends Activity {

	private static int SPLASH_TIME_OUT = 1500;

	private static boolean isSplashShowing;

	@Override
	protected void onStop() {
		isSplashShowing = false;
		super.onStop();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		isSplashShowing = true;

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (isSplashShowing) {
					Intent i = new Intent(SplashScreenActivity.this,
							MainActivity.class);
					startActivity(i);
				}
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
}
