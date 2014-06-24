package com.pyro.mobilepolice.data;

import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

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
		// Log.d(TAG, "encryptedPIN: " + settings.getString("PIN", "NIL"));
	}

	public boolean isPINNotSet() {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		String encryptedPIN = settings.getString("PIN", "NIL");
		// Log.d(TAG, "encryptedPIN: " + encryptedPIN);
		return ("NIL".equals(encryptedPIN) || encryptedPIN == null || encryptedPIN
				.isEmpty());
	}

	public String getPINValue() {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		String encryptedPIN = settings.getString("PIN", "NIL");
		// Log.d(TAG, "encryptedPIN: " + encryptedPIN);
		if ("NIL".equals(encryptedPIN) || encryptedPIN == null
				|| encryptedPIN.isEmpty()) {
			Random rnd = new Random();
			byte[] nbyte = new byte[50];
			rnd.nextBytes(nbyte);
			return new String(nbyte);
		}
		return Utils.decryptPIN(encryptedPIN);
	}

	public void putCallForwardingNumber(String callForwardingNumber) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("CallForward", callForwardingNumber);
		editor.commit();
		// Log.d(TAG, settings.getString("CallForward", "NIL"));

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
