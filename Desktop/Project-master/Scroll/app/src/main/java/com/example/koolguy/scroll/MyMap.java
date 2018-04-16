package com.example.koolguy.scroll;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.CameraPosition;

public class MyMap implements OnMapReadyCallback {
    GoogleMap googleMap;
    FragmentTransaction ft;
    Activity activity;
    Context context;
    View view;
    CameraPosition saveCamera;

    public MyMap(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;


    }

    public void makeMap() {
        MapFragment gmap = new MapFragment();
        ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.frames, gmap);
        ft.addToBackStack(null);
        ft.commit();
        gmap.getMapAsync(this);

    }

    public boolean googleServicesAvaliable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(context);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(activity, isAvailable, 0);
            dialog.show();
        } else {

        }
        return false;
    }

    private void setSaveCamera() {
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(saveCamera));
    }

         public void saveCamera() {
        if (googleMap != null) saveCamera = googleMap.getCameraPosition();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        if (saveCamera != null) setSaveCamera();


    }
}
