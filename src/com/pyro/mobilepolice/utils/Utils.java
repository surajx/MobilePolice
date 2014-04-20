package com.pyro.mobilepolice.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

import com.pyro.mobilepolice.constants.Const;
import com.pyro.mobilepolice.data.MissionRequest;
import com.pyro.mobilepolice.data.PreferenceManager;
import com.pyro.mobilepolice.reciever.SMSSender;

public class Utils {

	public final static String TAG = "Utils";

	public static ProgressDialog showProgressDialogWithMessage(String message,
			Context context) {

		ProgressDialog myProgressDialog = new ProgressDialog(context);
		try {
			myProgressDialog.setMessage(message);
			myProgressDialog.setCancelable(false);
			myProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			myProgressDialog.show();
			myProgressDialog
					.setOnKeyListener(new DialogInterface.OnKeyListener() {

						public boolean onKey(DialogInterface dialog,
								int keyCode, KeyEvent event) {
							if (keyCode == KeyEvent.KEYCODE_SEARCH
									&& event.getRepeatCount() == 0) {
								return true;
							}
							if (keyCode == KeyEvent.KEYCODE_MENU) {
								return true;
							}
							return false;
						}

					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return myProgressDialog;
	}

	public static void dismissProgressDialog(ProgressDialog myProgressDialog) {
		if (myProgressDialog != null) {
			try {
				myProgressDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean checkIfMissionRequest(String smsMessage) {
		return smsMessage.startsWith(Const.MISSION_REQUEST_IDENTIFIER);
	}

	public static MissionRequest parseMissionRequest(String smsMessage)
			throws Exception {
		String parts[] = smsMessage.split(Const.MISSION_REQUEST_DELIMITER);
		MissionRequest mMissionRequest = new MissionRequest();
		mMissionRequest.setPin(parts[1]); // pin - required
		mMissionRequest.setMissionIdentifier(parts[2]);
		// mission identifier - required
		try {
			mMissionRequest.setMissionData(parts[3]);// mission data - optional
		} catch (Exception e) {
			Log.d(TAG, "Mission data not provided.");
		}
		return mMissionRequest;
	}

	public static void callPhoneNumber(Context context, String phoneNumber) {
		if (phoneNumber != null) {
			Intent intentCallForward = new Intent(Intent.ACTION_CALL);

			Uri uri = Uri.fromParts("tel", phoneNumber, "#");
			intentCallForward.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intentCallForward.setData(uri);
			Context appContext = context.getApplicationContext();
			appContext.startActivity(intentCallForward);
		}
	}

	public static boolean authenticateRequest(String pin, Context context) {
		Log.d(TAG, "pin" + pin);
		PreferenceManager preferenceManager = new PreferenceManager(context);
		String savedPin = preferenceManager.getPINValue();
		Log.e(TAG, "savedPin" + savedPin);
		return (pin.equalsIgnoreCase(savedPin));
	}

	public static void sendSMS(String senderNumber, String message) {
		SMSSender.sendSMS(senderNumber, message);
	}
}
