package org.apache.cordova.geolocation;

import android.location.Location;
import com.google.android.gms.location.LocationResult;

interface OnLocationResultEventListener {

    void onLocationResultSuccess(LocationContext locationContext, LocationResult result);
    void onLocationGPSResultSuccess(LocationContext locationContext, Location result);
    void onLocationResultError(LocationContext locationContext, LocationError error);
}