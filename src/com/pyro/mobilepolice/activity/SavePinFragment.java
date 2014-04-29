package com.pyro.mobilepolice.activity;

import com.pyro.mobilepolice.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class SavePinFragment extends Fragment {

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		 	
		 	super.onCreateView(inflater, container, savedInstanceState);
	        View view =  inflater.inflate(R.layout.fragment_save_pin, container, false);
	        return view;
	 
	    }
}
