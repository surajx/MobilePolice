package com.pyro.mobilepolice.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pyro.mobilepolice.R;

public class MainActivity extends ActionBarActivity implements
		HomeFragment.OnHomeSelectionListener {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerList;

	public void onMenuButtonSelected(int position) {
		implementNavigation(position);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_drawer);
		com.pyro.mobilepolice.data.PreferenceManager preferenceManager = com.pyro.mobilepolice.data.PreferenceManager
				.getInstance();
		preferenceManager.setContext(getApplicationContext());
		PreferenceManager.setDefaultValues(this, R.xml.preference, false);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		String[] navDrawerOptions = getResources().getStringArray(
				R.array.navDrawerOptions);

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.nav_drawer_open, /* "open drawer" description */
		R.string.nav_drawer_close /* "close drawer" description */
		) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
				inputMethodManager.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), 0);
				super.onDrawerOpened(drawerView);

			}

		};

		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.ab_titlebar0));

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		NavListAdapter navListAdapter = new NavListAdapter(navDrawerOptions);
		mDrawerList.setAdapter(navListAdapter);
		mDrawerList.setClickable(true);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
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

		if (savedInstanceState == null) {
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.content_frame, new HomeFragment());
			ft.commit();
		}
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			implementNavigation(position);
		}

	}

	private Boolean isFragmentAlreadyLoaded(String tag) {
		Boolean fragmentLoaded = false;
		FragmentManager fm = getSupportFragmentManager();
		Fragment frag = fm.findFragmentByTag(tag);
		if (frag != null) {
			fragmentLoaded = true;
		}
		return fragmentLoaded;
	}

	private RemoteFragment remoteFragmentClass(String fragmentDescription) {
		RemoteFragment frag = new RemoteFragment();
		Bundle bundle = new Bundle();
		bundle.putString("func", fragmentDescription);
		frag.setArguments(bundle);
		return frag;
	}

	private LocalFragment localFragmentClass(String fragmentDescription) {
		LocalFragment frag = new LocalFragment();
		Bundle bundle = new Bundle();
		bundle.putString("func", fragmentDescription);
		frag.setArguments(bundle);
		return frag;
	}

	public void implementNavigation(int position) {
		Fragment fragment = null;
		Boolean home = false;

		String[] navDrawerOptions = getResources().getStringArray(
				R.array.navDrawerOptions);
		String fragmentDescription = navDrawerOptions[position].replaceAll(
				"\\s+", "");

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Integer count = fm.getBackStackEntryCount();

		if (count > 0) {
			fm.popBackStack();
		}

		if (navDrawerOptions[position].equals(getResources().getString(
				R.string.navHomeText))) {
			fm.popBackStack(fragmentDescription, 1);
			home = true;
		} else if (navDrawerOptions[position].equals(getResources().getString(
				R.string.navSavePinText))) {
			fragment = new SavePinFragment();
		} else if ((navDrawerOptions[position].equals(getResources().getString(
				R.string.navExportContactText)))
				| (navDrawerOptions[position].equals(getResources().getString(
						R.string.navExportSMSText)))) {
			fragment = localFragmentClass(navDrawerOptions[position]);
		} else if ((navDrawerOptions[position].equals(getResources().getString(
				R.string.navCallForwardText)))
				| (navDrawerOptions[position].equals(getResources().getString(
						R.string.navRingPhoneText)))
				| (navDrawerOptions[position].equals(getResources().getString(
						R.string.navCallBackText)))
				| (navDrawerOptions[position].equals(getResources().getString(
						R.string.navGetLocationText)))) {
			fragment = remoteFragmentClass(navDrawerOptions[position]);

		}

		if (home == false) {
			ft.replace(R.id.content_frame, fragment, fragmentDescription)
					.addToBackStack(null);
			ft.commit();
		}

		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {

		case (R.id.action_settings):
			Intent intent = new Intent(this,
					com.pyro.mobilepolice.activity.SettingsActivity.class);
			startActivity(intent);
			return true;
		}
		return true;
	}

	public class NavListAdapter extends BaseAdapter {

		final String[] Title;

		private NavListAdapter() {
			Title = null;
		}

		public NavListAdapter(String[] text) {
			Title = text;
		}

		public int getCount() {
			return Title.length;
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = getLayoutInflater();
			View row;
			row = inflater.inflate(R.layout.list_row_nav_drawer, parent, false);
			TextView title;
			ImageView icons;
			LinearLayout mainOption;
			title = (TextView) row.findViewById(R.id.textOptions);
			icons = (ImageView) row.findViewById(R.id.imageView1);
			title.setText(Title[position]);

			if (Title[position].equals(getResources().getString(
					R.string.navHomeText))) {
				icons.setImageResource(R.drawable.ic_home);
			} else if (Title[position].equals(getResources().getString(
					R.string.navSavePinText))) {
				icons.setImageResource(R.drawable.ic_savepin);
			} else if (Title[position].equals(getResources().getString(
					R.string.navExportContactText))) {
				icons.setImageResource(R.drawable.ic_exportcontact);
			} else if (Title[position].equals(getResources().getString(
					R.string.navExportSMSText))) {
				icons.setImageResource(R.drawable.ic_exportsms);
			} else if (Title[position].equals(getResources().getString(
					R.string.navCallForwardText))) {
				icons.setImageResource(R.drawable.ic_callforward);
			} else if (Title[position].equals(getResources().getString(
					R.string.navRingPhoneText))) {
				icons.setImageResource(R.drawable.ic_ringphone);
			} else if (Title[position].equals(getResources().getString(
					R.string.navCallBackText))) {
				icons.setImageResource(R.drawable.ic_callback);
			} else if (Title[position].equals(getResources().getString(
					R.string.navGetLocationText))) {
				icons.setImageResource(R.drawable.ic_location);
			} else if (Title[position].equals(getResources().getString(
					R.string.navSettingText))) {
				icons.setImageResource(R.drawable.ic_settings);
			}

			return (row);
		}

	}

}
