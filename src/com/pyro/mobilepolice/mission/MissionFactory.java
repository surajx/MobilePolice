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
			return new CallForwardingMission(false);
		} else if (Const.MISSION_IDENTIFIER_RING.equals(missionIdentifier)) {
			return new PlayRingtoneMission();
		} else if (Const.MISSION_IDENTIFIER_LOCATE.equals(missionIdentifier)) {
			return new GPSLocateMission();
		}
		//Add other mission types.
		return null;
	}

}
