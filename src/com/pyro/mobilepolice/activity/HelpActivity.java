package com.pyro.mobilepolice.activity;

import com.pyro.mobilepolice.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class HelpActivity extends ActionBarActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_help);
		
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