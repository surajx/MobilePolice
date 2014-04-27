package com.pyro.mobilepolice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import android.widget.Toast;
import com.pyro.mobilepolice.R;


public class SettingsActivity extends PreferenceActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
	}

	
	  @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	super.onOptionsItemSelected(item);
	    	
	    	switch ( item.getItemId() ){
	    	
	    	case(android.R.id.home):
	    		 this.finish();
	    		return true;
	    	}
	    	
	        
	        return true;
	    }
}
