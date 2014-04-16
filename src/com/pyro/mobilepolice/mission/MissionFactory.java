package com.pyro.mobilepolice.mission;

import com.pyro.mobilepolice.constants.Const;

public class MissionFactory {

	public static Mission createMission(String missionIdentifier) {
		if (Const.MISSION_IDENTIFIER_MAKE_CALL.equals(missionIdentifier)) {
			return new MakeCallMission();
		} else if (Const.MISSION_IDENTIFIER_START_CALL_FWD
				.equals(missionIdentifier)) {
			return new CallForwardingMission(true);
		} else if (Const.MISSION_IDENTIFIER_STOP_CALL_FWD
				.equals(missionIdentifier)) {
			return new CallForwardingMission(true);
		}
		// TODO: add other mission types.
		return null;
	}

}
