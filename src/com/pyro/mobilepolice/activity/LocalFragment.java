
package com.pyro.mobilepolice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pyro.mobilepolice.R;

public class LocalFragment extends Fragment {
	
	private String funcSelected;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
        View view =  inflater.inflate(R.layout.fragment_local, container, false);
        return view;
 
    }
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle args = getArguments();
		funcSelected = args.getString("func");		
		Button btnInitiate = (Button) getActivity().findViewById(R.id.btnInitiate);
		
		if (funcSelected.equals(getResources().getString(R.string.navExportContactText))){
			
			btnInitiate.setText("Export Local Contact List");
			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "export contact initiated",
							Toast.LENGTH_SHORT).show();
				}
			});

		} else if (funcSelected.equals(getResources().getString(R.string.navExportSMSText))) {
			btnInitiate.setText("Export SMS");
			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "export sms initiated",
							Toast.LENGTH_SHORT).show();
				}
			});
		}
		
	}
}