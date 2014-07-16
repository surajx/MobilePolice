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
import com.pyro.mobilepolice.data.PreferenceManager;

public class SavePinFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_save_pin, container,
				false);
		return view;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		Button btnSave = (Button) getActivity().findViewById(R.id.btnDoSavePin);
		final boolean isFirstPINSave = PreferenceManager.getInstance()
				.isPINNotSet();
		if (isFirstPINSave) {
			((EditText) getActivity().findViewById(R.id.txtPIN))
					.setVisibility(View.GONE);
			((TextView) getActivity().findViewById(R.id.textOptions))
					.setVisibility(View.GONE);
			((EditText) getActivity().findViewById(R.id.txtPIN1))
					.requestFocus();
		}
		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				PreferenceManager preferenceManager = PreferenceManager
						.getInstance();
				EditText newPin = (EditText) getActivity().findViewById(
						R.id.txtPIN1);
				String pin = newPin.getText().toString();
				if (!isFirstPINSave) {
					EditText oldPin = (EditText) getActivity().findViewById(
							R.id.txtPIN);
					String oldPinStr = oldPin.getText().toString();
					if (oldPinStr == null || oldPinStr.length() == 0) {
						Toast.makeText(getActivity().getApplicationContext(),
								"Please enter Old PIN.", Toast.LENGTH_LONG)
								.show();
						return;
					}
					if (!oldPinStr.equals(preferenceManager.getPINValue())) {
						Toast.makeText(getActivity().getApplicationContext(),
								"Invalid Old PIN.", Toast.LENGTH_LONG).show();
						return;
					}
				}
				if (pin == null || pin.length() == 0) {
					Toast.makeText(getActivity().getApplicationContext(),
							"You must enter a New PIN.", Toast.LENGTH_LONG)
							.show();
					return;
				}
				if (pin.length() < 4) {
					Toast.makeText(getActivity().getApplicationContext(),
							"PIN must be 4 or more digits long.",
							Toast.LENGTH_LONG).show();
					return;
				}
				preferenceManager.putPINValue(pin);
				Toast.makeText(getActivity().getApplicationContext(),
						"PIN Saved", Toast.LENGTH_LONG).show();
				getFragmentManager().popBackStack();
			}

		});

	}
}
