package com.pyro.mobilepolice.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.pyro.mobilepolice.utils.Utils;

public class PreferenceManager {
	private static final String PREFS_NAME = "MyPrefsFile";
	private static PreferenceManager mPreferenceManager;
	private Context context;
	public final static String TAG = "pyro.PreferenceManager";

	private PreferenceManager() {
	}

	public static synchronized PreferenceManager getInstance() {
		if (mPreferenceManager == null)
			mPreferenceManager = new PreferenceManager();
		return mPreferenceManager;
	}

	public void putPINValue(String pin) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		String encryptedPIN = Utils.encryptPIN(pin);
		editor.putString("PIN", encryptedPIN);
		editor.commit();
		System.out.println(settings.getString("PIN", "NIL"));
	}

	public String getPINValue() {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		String encryptedPIN = settings.getString("PIN", "NIL");
		return Utils.decryptPIN(encryptedPIN);
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
		Editor editor = settings.edit();
		editor.remove("CallForward");
		editor.commit();

	}

	public SharedPreferences getDefaultSharedPreferences() {
		return android.preference.PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
