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
import com.pyro.mobilepolice.utils.Utils;

public class RemoteFragment extends Fragment {

	private String funcSelected;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_remote, container,
				false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle args = getArguments();
		funcSelected = args.getString("func");
		String fromPhoneHeader = getResources().getString(
				R.string.frag_remote_phone_header);
		String toPhoneHeader = getResources().getString(
				R.string.frag_target_phone_header);
		String pinHeader = getResources().getString(
				R.string.frag_remote_pin_header);

		final TextView tvFromPhone = (TextView) getActivity().findViewById(R.id.lostPhoneHeader);
		final TextView tvToPhone = (TextView) getActivity().findViewById(R.id.toPhoneHeader);
		final TextView tvPin = (TextView) getActivity().findViewById(R.id.pinNumberHeader);
		Button btnInitiate = (Button) getActivity().findViewById(R.id.btnInitiate);
		EditText etFromPhone = (EditText) getActivity().findViewById(R.id.lostPhoneNumber);
		EditText etToPhone = (EditText) getActivity().findViewById(R.id.toPhoneNumber);
		EditText etPin = (EditText) getActivity().findViewById(R.id.pinNumber);
		final String smsMessage;

		String htmlFromPhoneHeader = "<u>" + fromPhoneHeader + "</u>";
		String htmlToPhoneHeader = "<u>" + toPhoneHeader + "</u>";
		String htmlPinHeader = "<u>" + pinHeader + "</u>";

		tvFromPhone.setText(Html.fromHtml(htmlFromPhoneHeader));
		tvToPhone.setText(Html.fromHtml(htmlToPhoneHeader));
		tvPin.setText(Html.fromHtml(htmlPinHeader));

		if (funcSelected.equals(getResources().getString(
				R.string.navRingPhoneText))) {
			etToPhone.setVisibility(View.GONE);
			tvToPhone.setVisibility(View.GONE);
			btnInitiate.setText("Ring Phone");

			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "ring phone sms sent",
							Toast.LENGTH_SHORT).show();

				}
			});
		} else if (funcSelected.equals(getResources().getString(
				R.string.navCallBackText))) {
			btnInitiate.setText("Call Back");

			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "call back sms sent",
							Toast.LENGTH_SHORT).show();
				}
			});
		} else if (funcSelected.equals(getResources().getString(
				R.string.navGetLocationText))) {
			etToPhone.setVisibility(View.GONE);
			tvToPhone.setVisibility(View.GONE);
			btnInitiate.setText("Get Location");

			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "get location sms sent",
							Toast.LENGTH_SHORT).show();

				}
			});
		} else if (funcSelected.equals(getResources().getString(R.string.navCallForwardText))) {
			
			btnInitiate.setText("Start Call Forward");

			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "call back sms sent",
							Toast.LENGTH_SHORT).show();
				}
			});
		}
		
	}
}
