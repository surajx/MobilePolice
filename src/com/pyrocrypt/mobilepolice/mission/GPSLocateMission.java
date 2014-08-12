package com.pyrocrypt.mobilepolice.mission;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.location.Location;

import com.pyrocrypt.mobilepolice.data.MissionRequest;
import com.pyrocrypt.mobilepolice.gps.LocationReporter;
import com.pyrocrypt.mobilepolice.utils.Utils;

public class GPSLocateMission implements Mission, Observer {

	public final static String TAG = "GPSLocateMission";

	private String senderNumber;

	@Override
	public void execute(Context context, MissionRequest mRequest) {
		if (mRequest.getMissionData() == null
				|| mRequest.getMissionData().isEmpty()) {
			senderNumber = mRequest.getMissionOrigin();
		} else {
			senderNumber = mRequest.getMissionData();
		}
		LocationReporter locationReporter = new LocationReporter(context);
		locationReporter.requestForLocation(this);
	}

	@Override
	public void update(Observable observable, Object data) {
		Location mLocation = (Location) data;
		String message = "Unable to retrieve Location at this time.";
		if (mLocation != null) {
			double latitude = mLocation.getLatitude();
			double longitude = mLocation.getLongitude();
			message = "Your Location is - \nLat: " + latitude + "\nLong: "
					+ longitude + "\n" + "http://maps.google.com/maps?q="
					+ latitude + "," + longitude;
		}
		Utils.sendSMS(senderNumber, message);
	}
}
