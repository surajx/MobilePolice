package com.pyro.mobilepolice.mission;

import android.content.Context;

import com.pyro.mobilepolice.data.PreferenceManager;
import com.pyro.mobilepolice.utils.Utils;

public class CallForwardingMission implements Mission {

	private boolean isStartForwarding;
	private String startForwardingUSSDPrefix = "**21*";
	private String startForwardingUSSDSuffix = "#";
	private String stopForwardingUSSDCode = "##21#";

	public CallForwardingMission(boolean isStartForwarding) {
		this.isStartForwarding = isStartForwarding;
	}

	@Override
	public void execute(Context context, String mMissionData) {
		PreferenceManager preferenceManager = new PreferenceManager(context);
		if (isStartForwarding) {
			int callTextIndex = mMissionData.indexOf("+");
			// TODO not a good logic
			String phoneNumber = mMissionData.substring(callTextIndex + 1,
					callTextIndex + 11);
			preferenceManager.putCallForwardingNumber(phoneNumber);
			Utils.callPhoneNumber(context, startForwardingUSSDPrefix
					+ phoneNumber + startForwardingUSSDSuffix);
		} else {
			preferenceManager.removeCallForwardNumber();
			Utils.callPhoneNumber(context, stopForwardingUSSDCode);
		}
	}

}
