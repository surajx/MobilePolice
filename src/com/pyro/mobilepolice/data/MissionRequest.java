package com.pyro.mobilepolice.data;

public class MissionRequest {

	private String pin;
	private String missionIdentifier;
	private String missionData;

	public String getMissionData() {
		return missionData;
	}

	public void setMissionData(String missionData) {
		this.missionData = missionData;
	}

	public String getMissionIdentifier() {
		return missionIdentifier;
	}

	public void setMissionIdentifier(String missionIdentifier) {
		this.missionIdentifier = missionIdentifier;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
}
