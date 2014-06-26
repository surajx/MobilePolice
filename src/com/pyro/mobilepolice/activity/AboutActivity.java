package com.pyro.mobilepolice.activity;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pyro.mobilepolice.R;

public class AboutActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_about);

		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.ab_titlebar0));

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		final int actionBarTitle = Resources.getSystem().getIdentifier(
				"action_bar_title", "id", "android");

		final TextView title = (TextView) getWindow().findViewById(
				actionBarTitle);
		if (title != null) {

			Typeface mTypeface = Typeface.createFromAsset(
					getApplicationContext().getAssets(), "fonts/14359.ttf");
			title.setTypeface(mTypeface);
			title.setTextSize(22);
		}

		Drawable background = getResources().getDrawable(
				R.drawable.splash_screen);
		LinearLayout layout = ((LinearLayout) findViewById(R.id.abtLinearLayout));
		background.setAlpha(150);
		layout.setBackgroundDrawable(background);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {

		case (android.R.id.home):
			this.finish();
			return true;
		}

		return true;
	}
}