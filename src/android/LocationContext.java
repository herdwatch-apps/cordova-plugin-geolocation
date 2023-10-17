package org.apache.cordova.geolocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

public class LocationContext {

    public enum Type {
        RETRIEVAL,
        UPDATE
    }

    private int id;
    private LocationContext.Type type;
    private JSONArray executeArgs;
    private CallbackContext callbackContext;
    private LocationCallback locationCallback;
    private final OnLocationResultEventListener listener;
    private LocationManager locationManager;
    private LocationListener locationListener;

    public LocationContext(int id, LocationContext.Type type, JSONArray executeArgs, CallbackContext callbackContext, OnLocationResultEventListener listener, Context context) {
        this.id = id;
        this.type = type;
        this.executeArgs = executeArgs;
        this.callbackContext = callbackContext;
        this.listener = listener;

        this.locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if(LocationContext.this.listener != null) {
                    if(locationResult == null) {
                        LocationContext.this.listener.onLocationResultError(LocationContext.this, LocationError.LOCATION_NULL);
                    }
                    else {
                        LocationContext.this.listener.onLocationResultSuccess(LocationContext.this, locationResult);
                    }
                }
            }
        };

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                locationManager.removeUpdates(this);
                if(LocationContext.this.listener != null) {
                    LocationContext.this.listener.onLocationGPSResultSuccess(LocationContext.this, location);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // do nothing
            }

            @Override
            public void onProviderEnabled(String provider) {
                // do nothing
            }

            @Override
            public void onProviderDisabled(String provider) {
                // do nothing
            }
        };
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public JSONArray getExecuteArgs() {
        return executeArgs;
    }

    public CallbackContext getCallbackContext() {
        return callbackContext;
    }

    public LocationCallback getLocationCallback() {
        return locationCallback;
    }

    public LocationListener getLocationListener() {
        return locationListener;
    }

}
