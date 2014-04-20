package com.pyro.mobilepolice.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallReciever extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {

		try {
			// TELEPHONY MANAGER class object to register one listner
			TelephonyManager tmgr = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			// Create Listner
			MyPhoneStateListener PhoneListener = new MyPhoneStateListener(
					context);

			// Register listener for LISTEN_CALL_STATE
			tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

		} catch (Exception e) {
			Log.e("Phone Receive Error", " " + e);
		}

	}

}
