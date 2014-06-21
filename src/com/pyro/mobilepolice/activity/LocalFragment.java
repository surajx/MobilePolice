package com.pyro.mobilepolice.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pyro.mobilepolice.R;
import com.pyro.mobilepolice.ContactsManager.ContactsManager;
import com.pyro.mobilepolice.SMSManager.SMSManager;
import com.pyro.mobilepolice.constants.Const;
import com.pyro.mobilepolice.utils.Utils;

public class LocalFragment extends Fragment {

	ProgressDialog progressDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_local, container, false);
		return view;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle args = getArguments();
		String funcSelected = args.getString("func");
		Button btnInitiate = (Button) getActivity().findViewById(
				R.id.btnInitiate);
		TextView mFuncDesc = (TextView) getActivity().findViewById(
				R.id.funcDescription);

		if (funcSelected.equals(getResources().getString(
				R.string.navExportContactText))) {

			mFuncDesc.setText(R.string.export_contacts_desc);
			btnInitiate.setText("Export Contact List");
			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					ExportTask exportContactsTask = new ExportTask();
					exportContactsTask.execute(Const.EXPORT_CONTACTS);
				}
			});

		} else if (funcSelected.equals(getResources().getString(
				R.string.navExportSMSText))) {

			mFuncDesc.setText(R.string.export_sms_desc);
			btnInitiate.setText("Export SMS");
			btnInitiate.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					ExportTask exportContactsTask = new ExportTask();
					exportContactsTask.execute(Const.EXPORT_SMS);
				}
			});
		}

	}

	public class ExportTask extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			progressDialog = Utils.showProgressDialogWithMessage(
					"Saving Data...", getActivity());

		}

		@Override
		protected void onPostExecute(Void result) {
			if (progressDialog != null)
				Utils.dismissProgressDialog(progressDialog);
		}

		@Override
		protected Void doInBackground(String... params) {
			if (Const.EXPORT_SMS.equals(params[0])) {
				SMSManager smsManager = new SMSManager();
				smsManager.readAndWriteSMS(getActivity());
			} else if (Const.EXPORT_CONTACTS.equals(params[0])) {
				ContactsManager contactsManager = new ContactsManager();
				contactsManager.readAndWriteContacts(getActivity());
			}
			return null;
		}
	}
}