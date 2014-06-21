package com.pyro.mobilepolice.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pyro.mobilepolice.R;
import com.pyro.mobilepolice.constants.Const;
import com.pyro.mobilepolice.utils.Utils;

public class RemoteFragment extends Fragment {

	private String funcSelected;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater
				.inflate(R.layout.fragment_remote, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle args = getArguments();
		funcSelected = args.getString("func");

		final TextView tvFromPhone = (TextView) getActivity().findViewById(
				R.id.lostPhoneHeader);
		final TextView tvToPhone = (TextView) getActivity().findViewById(
				R.id.toPhoneHeader);
		final TextView tvPin = (TextView) getActivity().findViewById(
				R.id.pinNumberHeader);
		Button btnInitiate = (Button) getActivity().findViewById(
				R.id.btnInitiate);
		final EditText etFromPhone = (EditText) getActivity().findViewById(
				R.id.lostPhoneNumber);
		final EditText etToPhone = (EditText) getActivity().findViewById(
				R.id.toPhoneNumber);
		final EditText etPin = (EditText) getActivity().findViewById(
				R.id.pinNumber);
		TextView mFuncDescription = (TextView) getActivity().findViewById(
				R.id.funcDescription);

		if (funcSelected.equals(getResources().getString(
				R.string.navRingPhoneText))) {
			etToPhone.setVisibility(View.GONE);
			tvToPhone.setVisibility(View.GONE);
			btnInitiate.setText("Ring Phone");
			mFuncDescription.setText(R.string.ring_phone_desc);
			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String requestMessage = Const.MISSION_REQUEST_IDENTIFIER
							+ " " + etPin.getText().toString() + " "
							+ Const.MISSION_IDENTIFIER_RING;
					Utils.sendSMS(etFromPhone.getText().toString(),
							requestMessage);
					Toast.makeText(getActivity(),
							"Request SMS to Play Ringtone Sent.",
							Toast.LENGTH_LONG).show();
				}
			});
		} else if (funcSelected.equals(getResources().getString(
				R.string.navCallBackText))) {
			mFuncDescription.setText(R.string.init_call_desc);
			btnInitiate.setText("Call Back");

			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String requestMessage = Const.MISSION_REQUEST_IDENTIFIER
							+ " " + etPin.getText().toString() + " "
							+ Const.MISSION_IDENTIFIER_MAKE_CALL + " "
							+ etToPhone.getText().toString();
					Utils.sendSMS(etFromPhone.getText().toString(),
							requestMessage);
					Toast.makeText(getActivity(),
							"Remote Call Initiation Request SMS Sent.",
							Toast.LENGTH_LONG).show();
				}
			});
		} else if (funcSelected.equals(getResources().getString(
				R.string.navGetLocationText))) {
			mFuncDescription.setText(R.string.get_location_desc);
			etToPhone.setVisibility(View.GONE);
			tvToPhone.setVisibility(View.GONE);
			btnInitiate.setText("Get Location");

			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String requestMessage = Const.MISSION_REQUEST_IDENTIFIER
							+ " " + etPin.getText().toString() + " "
							+ Const.MISSION_IDENTIFIER_LOCATE;
					Utils.sendSMS(etFromPhone.getText().toString(),
							requestMessage);
					Toast.makeText(getActivity(),
							"SMS Request to Retrieve Location Sent.",
							Toast.LENGTH_LONG).show();

				}
			});
		} else if (funcSelected.equals(getResources().getString(
				R.string.navCallForwardText))) {
			mFuncDescription.setText(R.string.call_fwd_desc);
			btnInitiate.setText("Start Call Forward");

			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					String requestMessage = Const.MISSION_REQUEST_IDENTIFIER
							+ " " + etPin.getText().toString() + " "
							+ Const.MISSION_IDENTIFIER_START_CALL_FWD + " "
							+ etToPhone.getText().toString();
					Utils.sendSMS(etFromPhone.getText().toString(),
							requestMessage);
					Toast.makeText(getActivity(),
							"Call Forwarding Request SMS Sent.",
							Toast.LENGTH_LONG).show();
				}
			});
		}

	}
}
