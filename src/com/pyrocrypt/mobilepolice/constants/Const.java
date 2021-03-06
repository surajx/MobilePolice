package com.pyrocrypt.mobilepolice.constants;

import java.util.HashMap;
import java.util.Map;

public class Const {

	// Mission Request Constants
	public static final String MISSION_REQUEST_IDENTIFIER = "MPREQ";
	public static final String MISSION_REQUEST_DELIMITER = " ";

	// Mission Identifier Constants
	public static final String MISSION_IDENTIFIER_MAKE_CALL = "CALL";
	public static final String MISSION_IDENTIFIER_START_CALL_FWD = "STARTFWD";
	public static final String MISSION_IDENTIFIER_STOP_CALL_FWD = "STOPFWD";
	public static final String MISSION_IDENTIFIER_RING = "RING";
	public static final String MISSION_IDENTIFIER_LOCATE = "LOCATE";

	// Export Data constants
	public static final String EXPORT_SMS = "EXPORT_SMS";
	public static final String EXPORT_CONTACTS = "EXPORT_CONTACTS";

	// Settings Page Preferences Key Constants
	public static final String KEY_PREF_SHOULD_SEND_RESP = "should_send_resp";
	/*
	 * TODO Preference activity should create a key "should_send_resp" and save
	 * the value of Is Response Sent after Mission Request setting in it. By
	 * default the setting is false i.e. no response is sent.
	 */

	public static final Map<String, String> PREFERENCE_MAP;
	static {
		PREFERENCE_MAP = new HashMap<String, String>();
		PREFERENCE_MAP.put(MISSION_IDENTIFIER_MAKE_CALL, "pref_key_call_back");
		PREFERENCE_MAP.put(MISSION_IDENTIFIER_START_CALL_FWD,
				"pref_key_call_forward");
		PREFERENCE_MAP.put(MISSION_IDENTIFIER_RING, "pref_key_ring_phone");
		PREFERENCE_MAP.put(MISSION_IDENTIFIER_LOCATE, "pref_key_get_location");

	}

}
