package com.pyrocrypt.mobilepolice.mission;

import android.content.Context;
import android.util.Log;

import com.pyrocrypt.mobilepolice.data.MissionRequest;
import com.pyrocrypt.mobilepolice.data.PreferenceManager;
import com.pyrocrypt.mobilepolice.utils.Utils;

public class CallForwardingMission implements Mission {

	public final static String TAG = "CallForwardingMission";

	private boolean isStartForwarding;
	private String startForwardingUSSDPrefix = "**21*";
	private String startForwardingUSSDSuffix = "#";
	private String stopForwardingUSSDCode = "##21#";

	public CallForwardingMission(boolean isStartForwarding) {
		this.isStartForwarding = isStartForwarding;
	}

	@Override
	public void execute(Context context, MissionRequest mRequest) {
		String mMissionData = mRequest.getMissionData();
		PreferenceManager preferenceManager = PreferenceManager.getInstance();
		if (isStartForwarding) {
			preferenceManager.putCallForwardingNumber(mMissionData);
			String callFwdUSSD = startForwardingUSSDPrefix + mMissionData
					+ startForwardingUSSDSuffix;
			// Log.d(TAG, "callFwdUSSD: " + callFwdUSSD);
			Utils.callPhoneNumber(context, callFwdUSSD);
		} else {
			preferenceManager.removeCallForwardNumber();
			Utils.callPhoneNumber(context, stopForwardingUSSDCode);
		}
	}
}
