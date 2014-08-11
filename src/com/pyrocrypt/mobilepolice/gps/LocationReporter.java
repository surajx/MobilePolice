package com.pyrocrypt.mobilepolice.gps;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationReporter extends Observable implements LocationListener {

	public final static String TAG = "LocationReporter";
	private final Context mContext;

	private Location curLocation = null;
	private boolean isGPSEnabled = false;
	private boolean isNetworkEnabled = false;

	public LocationReporter(Context context) {
		this.mContext = context;
	}

	private LocationManager locationManager;

	public void requestForLocation(Observer requestor) {
		this.addObserver(requestor);
		initiateLocationRequest();
	}

	private void initiateLocationRequest() {
		locationManager = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);
		// getting GPS status
		isGPSEnabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (isGPSEnabled) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, this);
		}
		// getting network status
		isNetworkEnabled = locationManager
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if (isNetworkEnabled) {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 60000, 0, this);
		}
		// Both Cell Tower, Wifi, and GPS is unavailable - use last known good
		// location
		if (!isNetworkEnabled && !isGPSEnabled) {
			onLocationChanged(locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// Log.d(TAG, "received location:" + location);
		if (isGPSEnabled) {
			if (!LocationManager.GPS_PROVIDER.equals(location.getProvider())
					&& curLocation == null) {
				curLocation = location;
				return;
			}
		}
		setChanged();
		notifyObservers((Object) location);
		locationManager.removeUpdates(this);
		this.deleteObservers();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}
