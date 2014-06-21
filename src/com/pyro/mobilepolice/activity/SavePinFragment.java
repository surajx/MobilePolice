package com.pyro.mobilepolice.activity;

import com.pyro.mobilepolice.R;
import com.pyro.mobilepolice.data.PreferenceManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SavePinFragment extends Fragment {

	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		 	
		 	super.onCreateView(inflater, container, savedInstanceState);
	        View view =  inflater.inflate(R.layout.fragment_save_pin, container, false);
	        return view;
	        
	    }

	 @Override
	 public void onActivityCreated (Bundle savedInstanceState)
	 {
		 
		 super.onActivityCreated(savedInstanceState);
		 Button btnSave = (Button) getActivity().findViewById(R.id.btnSavePin);
		 btnSave.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					EditText txtPin = (EditText) getActivity().findViewById(R.id.txtPIN);
					String pin = txtPin.getText().toString();
					if (pin == null || pin.length()==0) {
						Toast.makeText(getActivity().getApplicationContext(),
								"You must enter a PIN.", Toast.LENGTH_LONG).show();
						return;
					}
					if (pin.length() < 4) {
						Toast.makeText(getActivity().getApplicationContext(),
								"PIN must be 4 or more characters long.",
								Toast.LENGTH_LONG).show();
						return;
					}
					PreferenceManager preferenceManager = PreferenceManager.getInstance();
					preferenceManager.setContext(getActivity().getApplicationContext());
					preferenceManager.putPINValue(pin);
					Toast.makeText(getActivity().getApplicationContext(), "PIN Saved",
							Toast.LENGTH_LONG).show();
				}
				
		 });
			
	 }
	 
}
