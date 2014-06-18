package com.pyro.mobilepolice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pyro.mobilepolice.R;

public class HomeFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        return view;
        
    }


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	
		final Button btnSavePin = (Button) getActivity().findViewById(R.id.btnSavePin);
		final Button btnCallBack = (Button) getActivity().findViewById(R.id.btnCallBack);
		final Button btnCallForward = (Button) getActivity().findViewById(R.id.btnCallForward);
		final Button btnExportContact = (Button) getActivity().findViewById(R.id.btnExportContact);
		final Button btnExportSMS = (Button) getActivity().findViewById(R.id.btnExportSMS);
		final Button btnGetLocation = (Button) getActivity().findViewById(R.id.btnGetLocation);
		final Button btnRingPhone = (Button) getActivity().findViewById(R.id.btnRingPhone);
		
		
		
		btnSavePin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Integer position = findPosition(btnSavePin.getText().toString());
				
			}
		});
		btnCallBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Integer position = findPosition(btnCallBack.getText().toString());

			}
		});
		btnCallForward.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Integer position = findPosition(btnCallForward.getText().toString());

			}
		});
		btnExportContact.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Integer position = findPosition(btnExportContact.getText().toString());

			}
		});
		btnExportSMS.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Integer position = findPosition(btnExportSMS.getText().toString());

			}
		});
		btnGetLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Integer position = findPosition(btnGetLocation.getText().toString());

			}
		});
		btnRingPhone.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Integer position = findPosition(btnRingPhone.getText().toString());

			}
		});
	
	}

	private Integer findPosition(String text) {
		String[] allOptions = getResources().getStringArray(R.array.navDrawerOptions);
		int position = 1;
		for(int i = 1;i<=allOptions.length;i++)
		{
			if(allOptions[i].equals(text))
			{	position = i;	}
			
		}
		return position;
		
		
	}
	
	public void implementNavigation(int position) {
		Fragment fragment = null;
		Boolean home = false;
		
		String[] navDrawerOptions = getResources().getStringArray(R.array.navDrawerOptions);
		String fragmentDescription = navDrawerOptions[position].replaceAll("\\s+","");
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Integer count = fm.getBackStackEntryCount();
		
		if (count > 0) {fm.popBackStack();}
		
		if (navDrawerOptions[position].equals(getResources().getString(R.string.navHomeText))) {
			fm.popBackStack(fragmentDescription, 1);
			home = true;
		}
		else if (navDrawerOptions[position].equals(getResources().getString(R.string.navSavePinText))) {
			fragment = new SavePinFragment();
		}
		else if ((navDrawerOptions[position].equals(getResources().getString(R.string.navExportContactText))) 
			| (navDrawerOptions[position].equals(getResources().getString(R.string.navExportSMSText))))
		{
			fragment = localFragmentClass(navDrawerOptions[position]);
		}
		else if (
		  (navDrawerOptions[position].equals(getResources().getString(R.string.navCallForwardText)))
		| (navDrawerOptions[position].equals(getResources().getString(R.string.navRingPhoneText)))
		| (navDrawerOptions[position].equals(getResources().getString(R.string.navCallBackText)))
		| (navDrawerOptions[position].equals(getResources().getString(R.string.navGetLocationText)))
		)
		{
			fragment = remoteFragmentClass(navDrawerOptions[position]);
			
		}
		
		if(home == false)
		{
			ft.replace(R.id.content_frame, fragment,
					fragmentDescription).addToBackStack(null);
			ft.commit();
		}
		
		
	}

	private RemoteFragment remoteFragmentClass(String fragmentDescription)
	{
		RemoteFragment frag = new RemoteFragment();
		Bundle bundle = new Bundle();
		bundle.putString("func", fragmentDescription);
		frag.setArguments(bundle);
		return frag;
	}
	
	private LocalFragment localFragmentClass(String fragmentDescription)
	{
		LocalFragment frag = new LocalFragment();
		Bundle bundle = new Bundle();
		bundle.putString("func", fragmentDescription);
		frag.setArguments(bundle);
		return frag;
	}
	
}