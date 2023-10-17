package org.apache.cordova.geolocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;

public class LocationHelper {
    private final LocationManager locationManager;

    public LocationHelper(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    public void requestGPSLocationUpdates(long minTime, float minDistance, Context context, LocationListener locationListener) {
        // no need to ask for permission because we already asked for it before this code is reached
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, locationListener);
    }
}
