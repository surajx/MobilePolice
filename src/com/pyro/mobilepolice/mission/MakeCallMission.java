package com.pyro.mobilepolice.mission;

import android.content.Context;

import com.pyro.mobilepolice.data.MissionRequest;
import com.pyro.mobilepolice.utils.Utils;

public class MakeCallMission implements Mission {

	@Override
	public void execute(Context context, MissionRequest mRequest) {
		String mMissionData = mRequest.getMissionData();
		Utils.callPhoneNumber(context, mMissionData);
	}
}
