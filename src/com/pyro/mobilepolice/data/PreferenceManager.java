package com.pyro.mobilepolice.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceManager {
	public static final String PREFS_NAME = "MyPrefsFile";
	private static Context context;

	public PreferenceManager(Context context) {
		this.context = context;
	}

	public void putPINValue(String pin) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("PIN", pin);
		 editor.commit();
		 System.out.println(settings.getString("PIN", "NIL"));
	}

	public String getPINValue() {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		String pin = settings.getString("PIN", "NIL");
		return pin;
	}

	public void putCallForwardingNumber(String callForwardingNumber) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("CallForward", callForwardingNumber);
		 editor.commit();
		 System.out.println(settings.getString("CallForward", "NIL"));
		
	}
	public String getCallForwardingNumber() {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		String number = settings.getString("CallForward", "NIL");
		return number;
	}

	public void removeCallForwardNumber() {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		Editor editor=settings.edit();
		editor.remove("CallForward");
		editor.commit();
		
	}
}
