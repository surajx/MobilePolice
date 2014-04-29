package com.pyro.mobilepolice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

import com.pyro.mobilepolice.R;

public class CallForwardFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_call_forward, container, false);
        return view;
 
    }
	
	@Override
	public void onActivityCreated (Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		TextView textDescription = (TextView)getActivity().findViewById(R.id.textDescription);
		textDescription.setText("Using <b>'Enable Call Forward'</b> button you can set if call forwarding can be initiated via SMS for this mobile. Using 'Initiate Call Forward' you can initiate call forward via SMS from another mobile in which MobilePolice has been installed. ");
		
		
	}
	
}
