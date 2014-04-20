package com.pyro.mobilepolice.mission;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.pyro.mobilepolice.data.MissionRequest;
import com.pyro.mobilepolice.gps.GPSTracker;
import com.pyro.mobilepolice.utils.Utils;

public class GPSLocateMission implements Mission {

	public final static String TAG = "GPSLocateMission";

	@Override
	public void execute(Context context, MissionRequest mRequest) {
		String senderNumber = "";
		if (mRequest.getMissionData() == null
				|| mRequest.getMissionData().isEmpty()) {
			senderNumber = mRequest.getMissionOrigin();
		} else {
			senderNumber = mRequest.getMissionData();
		}
		GPSTracker gpsTracker = new GPSTracker(context);
		if (gpsTracker.canGetLocation) {
			double latitude = gpsTracker.getLatitude();
			double longitude = gpsTracker.getLongitude();
			gpsTracker.stopUsingGPS();
			String message = "Your Location is - \nLat: " + latitude
					+ "\nLong: " + longitude;
			Utils.sendSMS(senderNumber, message);
		} else {
			Toast toast = Toast.makeText(context,
					"Unable to get GPS Location at this moment.",
					Toast.LENGTH_SHORT);
			toast.show();
			Log.d(TAG, "Unable to get GPS Location at this moment.");
		}
	}

}
