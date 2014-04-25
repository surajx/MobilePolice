package com.pyro.mobilepolice.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.pyro.mobilepolice.constants.Const;
import com.pyro.mobilepolice.data.MissionRequest;
import com.pyro.mobilepolice.data.PreferenceManager;
import com.pyro.mobilepolice.mission.Mission;
import com.pyro.mobilepolice.mission.MissionFactory;
import com.pyro.mobilepolice.utils.Utils;

public class IncomingSmsReciever extends BroadcastReceiver {

	public final static String TAG = "IncomingSmsReciever";

	public void onReceive(Context context, Intent intent) {

		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();

		if (bundle != null) {

			final Object[] pdusObj = (Object[]) bundle.get("pdus");

			for (int i = 0; i < pdusObj.length; i++) {

				SmsMessage currentMessage = SmsMessage
						.createFromPdu((byte[]) pdusObj[i]);
				String smsMessage = currentMessage.getDisplayMessageBody();
				String senderNumber = currentMessage
						.getDisplayOriginatingAddress();
				smsMessage = smsMessage.trim();
				if (Utils.checkIfMissionRequest(smsMessage))
					try {
						Log.d(TAG, "Valid Request received: " + smsMessage);
						MissionRequest mRequest = Utils
								.parseMissionRequest(smsMessage);
						mRequest.setMissionOrigin(senderNumber);
						PreferenceManager.getInstance().setContext(context);
						if (Utils.authenticateRequest(mRequest.getPin())) {
							Mission mMission = MissionFactory
									.createMission(mRequest
											.getMissionIdentifier());
							mMission.execute(context, mRequest);
							SharedPreferences sharedPref = PreferenceManager
									.getInstance()
									.getDefaultSharedPreferences();
							boolean shouldSendResp = sharedPref.getBoolean(
									Const.KEY_PREF_SHOULD_SEND_RESP, false);
							if (shouldSendResp) {
								Utils.sendSMS(senderNumber,
										"Mission Successfully Executed.");
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						Log.e(TAG, "Received an ill formatted Request SMS");
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	}
}
