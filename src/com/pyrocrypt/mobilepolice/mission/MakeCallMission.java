package com.pyrocrypt.mobilepolice.mission;

import android.content.Context;

import com.pyrocrypt.mobilepolice.data.MissionRequest;
import com.pyrocrypt.mobilepolice.utils.Utils;

public class MakeCallMission implements Mission {

	@Override
	public void execute(Context context, MissionRequest mRequest) {
		String mMissionData = mRequest.getMissionData();
		Utils.callPhoneNumber(context, mMissionData);
	}
}
