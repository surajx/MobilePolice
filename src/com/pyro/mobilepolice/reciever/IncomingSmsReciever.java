package com.pyro.mobilepolice.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.pyro.mobilepolice.data.MissionRequest;
import com.pyro.mobilepolice.data.PreferenceManager;
import com.pyro.mobilepolice.gps.GPSTracker;
import com.pyro.mobilepolice.mission.Mission;
import com.pyro.mobilepolice.mission.MissionFactory;
import com.pyro.mobilepolice.utils.Utils;

public class IncomingSmsReciever extends BroadcastReceiver {

	public void onReceive(Context context, Intent intent) {

		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();

		if (bundle != null) {

			final Object[] pdusObj = (Object[]) bundle.get("pdus");

			for (int i = 0; i < pdusObj.length; i++) {

				SmsMessage currentMessage = SmsMessage
						.createFromPdu((byte[]) pdusObj[i]);
				String smsMessage = currentMessage.getDisplayMessageBody();
				// TODO: As of now Mission request identifier is not part of the
				// incoming sms.
				if (Utils.checkIfMissionRequest(smsMessage)) {
					MissionRequest mRequest = Utils
							.parseMissionRequest(smsMessage);
					if (Utils.authenticateRequest(mRequest.getPin(), context)) {
						Mission mMission = MissionFactory
								.createMission(mRequest.getMissionIdentifier());
						mMission.execute(context, mRequest.getMissionData());
					}
				}
			}
		}

		// TODO: Delete the below code when all fucntionalities have been
		// implemented in the new architecture.

		try {

			if (bundle != null) {

				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) {

					SmsMessage currentMessage = SmsMessage
							.createFromPdu((byte[]) pdusObj[i]);
					String message = currentMessage.getDisplayMessageBody();
					// TODO: Check if incoming message format satisfies Mobile
					// police SMS format. if not continue.

					String phoneNumber = currentMessage
							.getDisplayOriginatingAddress();

					String senderNum = phoneNumber;
					if (message.contains("***Call")) {
						String callingPhoneNumber = null;
						callingPhoneNumber = getNumberFromMessage(context,
								message);

						forwardCall(context, callingPhoneNumber);
					} else if (message.contains("***Play")) {

						boolean isPinMatched = checkPin(message, context);
						if (isPinMatched)
							playRingtone(context);
					} else if (message.contains("***Location")) {
						boolean isPinMatched = checkPin(message, context);
						if (isPinMatched) {
							String textingPhoneNumber = null;
							textingPhoneNumber = getNumberFromMessage(context,
									message);
							if (textingPhoneNumber == null)
								textingPhoneNumber = senderNum;
							getLocation(context, textingPhoneNumber);
						}
					} else if (message.contains("***Startforward")) {
						boolean isPinMatched = checkPin(message, context);
						if (isPinMatched) {
							String callForwardingNumber = null;
							callForwardingNumber = getNumberFromMessage(
									context, message);
							PreferenceManager preferenceManager = new PreferenceManager(
									context);
							preferenceManager
									.putCallForwardingNumber(callForwardingNumber);
							callForwardingNumber = "**21*"
									+ callForwardingNumber + "#";

							forwardCall(context, callForwardingNumber);
						}
					} else if (message.contains("***Stopforward")) {
						boolean isPinMatched = checkPin(message, context);
						if (isPinMatched) {
							String callForwardingNumber = "##21#";
							PreferenceManager preferenceManager = new PreferenceManager(
									context);
							preferenceManager.removeCallForwardNumber();

							forwardCall(context, callForwardingNumber);
						}
					} else {
						PreferenceManager preferenceManager = new PreferenceManager(
								context);
						String callForwardNumber = preferenceManager
								.getCallForwardingNumber();
						if (!"NIL".equalsIgnoreCase(callForwardNumber)) {
							SMSSender.sendSMS(callForwardNumber, message);
						}
					}

				} // end for loop
			} // bundle is null

		} catch (Exception e) {
			Log.e("SmsReceiver", "Exception smsReceiver" + e);

		}
	}

	private boolean checkPin(String message, Context context) {
		String pin = getPIN(message);
		Log.e("SmsReceiver", "pin" + pin);
		PreferenceManager preferenceManager = new PreferenceManager(context);
		String savedPin = preferenceManager.getPINValue();
		Log.e("SmsReceiver", "savedPin" + savedPin);
		return (pin.equalsIgnoreCase(savedPin));
	}

	private String getPIN(String message) {
		int index = message.indexOf("-");
		String pin = message.substring(index + 1, index + 5);
		return pin;
	}

	private String getNumberFromMessage(Context context, String message) {
		String phoneNumber = null;
		try {
			int callTextIndex = message.indexOf("+");

			phoneNumber = message.substring(callTextIndex + 1,
					callTextIndex + 11);
		} catch (Exception ex) {
			Toast.makeText(context, "No phone no. in sms", Toast.LENGTH_LONG)
					.show();
		}
		return phoneNumber;
	}

	private void getLocation(Context context, String textingPhoneNumber) {

		GPSTracker gpsTracker = new GPSTracker(context);
		if (gpsTracker.canGetLocation) {
			double latitude = gpsTracker.getLatitude();
			double longitude = gpsTracker.getLongitude();
			Toast.makeText(
					context,
					"Your Location is - \nLat: " + latitude + "\nLong: "
							+ longitude, Toast.LENGTH_LONG).show();
			gpsTracker.stopUsingGPS();
			if (textingPhoneNumber != null) {
				sendSMS(latitude, longitude, textingPhoneNumber, context);
			}
		} else {
			Toast.makeText(context, "Location not available", Toast.LENGTH_LONG)
					.show();

		}
	}

	private void sendSMS(double latitude, double longitude,
			String textingPhoneNumber, Context context) {
		try {
			String message = "Your Location is - \nLat: " + latitude
					+ "\nLong: " + longitude;

			SMSSender.sendSMS(textingPhoneNumber, message);

			Toast.makeText(context, "SMS Sent!", Toast.LENGTH_LONG).show();
		} catch (Exception e) {

			Toast.makeText(context, "SMS sending failed", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}

	}

	private void forwardCall(Context context, String callingPhoneNumber) {
		if (callingPhoneNumber != null) {
			Intent intentCallForward = new Intent(Intent.ACTION_CALL);

			Uri uri = Uri.fromParts("tel", callingPhoneNumber, "#");
			intentCallForward.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intentCallForward.setData(uri);
			Context appContext = context.getApplicationContext();
			appContext.startActivity(intentCallForward);
		}

	}

	private void playRingtone(Context context) {
		Uri notification = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		AudioManager mAudioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		mAudioManager.setStreamVolume(AudioManager.STREAM_RING, 100,
				AudioManager.FLAG_PLAY_SOUND
						| AudioManager.FLAG_ALLOW_RINGER_MODES);
		Ringtone r = RingtoneManager.getRingtone(context, notification);
		r.play();

	}

}
