package com.pyrocrypt.mobilepolice.activity;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.pyrocrypt.mobilepolice.R;

public class SettingsActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);

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
