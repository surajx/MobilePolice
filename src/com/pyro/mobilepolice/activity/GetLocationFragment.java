

	package com.pyro.mobilepolice.activity;

	import android.os.Bundle;
	import android.support.v4.app.Fragment;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;

	import com.pyro.mobilepolice.R;

	public class GetLocationFragment extends Fragment {
		
		@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
			
			super.onCreateView(inflater, container, savedInstanceState);
	        View view =  inflater.inflate(R.layout.fragment_get_location, container, false);
	        return view;
	 
	    }
	}