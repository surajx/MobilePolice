package com.pyro.mobilepolice.reciever;

import android.telephony.SmsManager;

public class SMSSender {
	public static void sendSMS(String[] destinationNumbers, String message) {
		SmsManager sms = SmsManager.getDefault();
		for (String number : destinationNumbers) {
			sms.sendTextMessage(number, null, message, null, null);
		}
	}

	public static void sendSMS(String destinationNumber, String message) {
		SmsManager sms = SmsManager.getDefault();

		sms.sendTextMessage(destinationNumber, null, message, null, null);

	}

}
