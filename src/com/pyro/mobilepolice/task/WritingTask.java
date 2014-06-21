package com.pyro.mobilepolice.task;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.pyro.mobilepolice.ContactsManager.ContactsManager;
import com.pyro.mobilepolice.SMSManager.SMSManager;
import com.pyro.mobilepolice.activity.MenuActivity;
import com.pyro.mobilepolice.utils.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class WritingTask extends AsyncTask<String, String, String> {

	Context context;
	MenuActivity menuActivity;

	public WritingTask(Context context, MenuActivity menuActivity) {
		this.context = context;
		this.menuActivity = menuActivity;
	}

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected String doInBackground(String... url) {
		ContactsManager contactsManager = new ContactsManager();
		contactsManager.readAndWriteContacts(context);

		SMSManager smsManager = new SMSManager();
		smsManager.readAndWriteSMS(context);
		return "Success";
	}

	protected String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {

		InputStream in = entity.getContent();

		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	@Override
	protected void onPostExecute(String result) {

		menuActivity.afterSave(result);
		super.onPostExecute(result);
	}
}
