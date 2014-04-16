package com.pyro.mobilepolice.mission;

import android.content.Context;

import com.pyro.mobilepolice.utils.Utils;

public class MakeCallMission implements Mission {

	@Override
	public void execute(Context context, String mMissionData) {
		int callTextIndex = mMissionData.indexOf("+");
		String phoneNumber = mMissionData.substring(callTextIndex + 1,
				callTextIndex + 11);
		Utils.callPhoneNumber(context, phoneNumber);
	}

}
