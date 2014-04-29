package com.pyro.mobilepolice.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pyro.mobilepolice.R;

public class MainActivity extends ActionBarActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerList;
	//private String[] fragmentDescription = getResources().getStringArray(R.array.fragmentDescriptions);

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_drawer);
		PreferenceManager.setDefaultValues(this, R.xml.preference, false);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		String[] navDrawerOptions = getResources().getStringArray(R.array.navDrawerOptions);
		int[] iconList = new int[] { R.drawable.ic_action_save,
				R.drawable.ic_action_save, R.drawable.ic_action_save,
				R.drawable.ic_action_save, R.drawable.ic_action_save,
				R.drawable.ic_action_save, R.drawable.ic_action_save,
				R.drawable.ic_action_save, R.drawable.ic_action_save };

		mDrawerToggle = new ActionBarDrawerToggle
							(this, /* host Activity */
							mDrawerLayout, /* DrawerLayout object */
							R.drawable.icon, /* nav drawer icon to replace 'Up' caret */
							R.string.nav_drawer_open, /* "open drawer" description */
							R.string.nav_drawer_close /* "close drawer" description */
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) 
			{
				super.onDrawerClosed(view);
				//getSupportActionBar().setTitle("Mobile Police");
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) 
			{
				super.onDrawerOpened(drawerView);
				//getSupportActionBar().setTitle("Mobile Police");
			}

		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		NavListAdapter navListAdapter = new NavListAdapter(navDrawerOptions,iconList);
		mDrawerList.setAdapter(navListAdapter);
		mDrawerList.setClickable(true);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		getSupportActionBar().setHomeButtonEnabled(true);
		
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//load home screen fragment
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.content_frame, new HomeFragment());
		ft.commit();
		
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			implementNavigation(position);

		}

	}

	private Boolean isFragmentAlreadyLoaded(String tag)
	{
		Boolean fragmentLoaded = false;
		FragmentManager fm = getSupportFragmentManager();
		Fragment frag = fm. findFragmentByTag(tag);
		if(frag != null)
		{fragmentLoaded = true;}
		return fragmentLoaded;
	}
	
	
	public void implementNavigation(int position) 
	{
		Fragment fragment = null;
		String description = null;
		String[] fragmentDescription = getResources().getStringArray(R.array.fragmentDescriptions);
		//Boolean fragmentLoaded = isFragmentAlreadyLoaded(fragmentDescription[position]);
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Integer count = fm.getBackStackEntryCount();
		
		if(count>0)
		{fm.popBackStack();}
		
		switch (position) {
		case 0:
			fm.popBackStack(fragmentDescription[position],1);
			break;
		case 1:
			ft.replace(R.id.content_frame, new SavePinFragment(),fragmentDescription[position]).addToBackStack(null);
			ft.commit();	
			break;
		case 2:
			ft.replace(R.id.content_frame, new ExportLocalFragment(),fragmentDescription[position]).addToBackStack(null);
			ft.commit();	
			break;			
		case 3:
			ft.replace(R.id.content_frame, new ExportSmsFragment(),fragmentDescription[position]).addToBackStack(null);
			ft.commit();
			break;
		case 4:
			ft.replace(R.id.content_frame, new CallForwardFragment(),fragmentDescription[position]).addToBackStack(null);
			ft.commit();
			break;
		case 5:
			ft.replace(R.id.content_frame, new RingPhoneFragment(),fragmentDescription[position]).addToBackStack(null);
			ft.commit();
			break;
		case 6:
			ft.replace(R.id.content_frame, new CallBackFragment(),fragmentDescription[position]).addToBackStack(null);
			ft.commit();
			break;
		case 7:
			ft.replace(R.id.content_frame, new GetLocationFragment(),fragmentDescription[position]).addToBackStack(null);
			ft.commit();
			break;

		default:
			break;
		}

		mDrawerLayout.closeDrawer(mDrawerList);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) 
	{
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) 
	{
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		if (mDrawerToggle.onOptionsItemSelected(item)) 
		{
			return true;
		}
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) 
		{

		case (R.id.action_settings):
			Intent intent = new Intent(this,
					com.pyro.mobilepolice.activity.SettingsActivity.class);
			startActivity(intent);
			return true;

			/*
			 * case(R.id.Pin): Intent intent1 = new
			 * Intent(this,com.pyro.mobilepolice
			 * .activity.SavePinActivity.class); startActivity(intent1); return
			 * true;
			 */
		}

		Toast.makeText(this, "Selected Item: " + item.getTitle(),
				Toast.LENGTH_SHORT).show();
		return true;
	}

	public class NavListAdapter extends BaseAdapter {
		final String[] Title;

		final int[] imge;

		private NavListAdapter() {
			Title = null;
			imge = null;

		}

		public NavListAdapter(String[] text, int[] text2) {
			Title = text;

			imge = text2;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return Title.length;
		}

		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
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

			/*
			 * LinearLayout mainOption; mainOption = (LinearLayout)
			 * row.findViewById(R.id.optionRow);
			 * mainOption.setOnClickListener(new View.OnClickListener() {
			 * 
			 * @Override public void onClick(View v) { AlertDialog.Builder
			 * dlgAlert = new AlertDialog.Builder( v.getContext());
			 * dlgAlert.setMessage("row click"); dlgAlert.setTitle("Info");
			 * dlgAlert.create().show(); } });
			 */

			title.setText(Title[position]);
			icons.setImageResource(imge[position]);
			return (row);

		}

	}

}
