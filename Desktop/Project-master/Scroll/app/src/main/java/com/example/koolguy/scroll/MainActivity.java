package com.example.koolguy.scroll;

import android.Manifest;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    HorizontalScrollMenuView menu;
    TextView textView;
    GoogleMap map;
    CameraPosition saveCamera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = (HorizontalScrollMenuView) findViewById(R.id.menu);
        textView = (TextView) findViewById(R.id.text);
        int i = 0;
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 9999);
        initMenu();



    }

    private void makeMap() {
        if (googleServicesAvaliable()) {
            MapFragment mapFragment = new MapFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frames, mapFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            mapFragment.getMapAsync(this);

        }

    }

    private void mateToast(int position) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }
    private void anotherFragment()
    {
        Check mapFragment = new Check();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frames, mapFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    private void initMenu() {
        menu.addItem("Transcation", R.drawable.ic_money);
        menu.addItem("Map", R.drawable.ic_map);
        menu.addItem("Account", R.drawable.ic_account);
        menu.addItem("Support", R.drawable.ic_check);
        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {
                switch (position) {
                    case 0:
                        saveCamera();
                        anotherFragment();
                        break;
                    case 1:
                        saveCamera();
                        makeMap();
                        break;
                    case 2:
                        saveCamera();
                        anotherFragment();
                        break;
                    case 3:
                        saveCamera();
                        anotherFragment();
                        break;
                }

            }
        });

    }

    public boolean googleServicesAvaliable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "can.t coonect", Toast.LENGTH_LONG).show();
        }
        return false;
    }
    private void setSaveCamera()
    {
        map.moveCamera(CameraUpdateFactory.newCameraPosition(saveCamera));
    }
    private void saveCamera()
    {
        if(map!=null) saveCamera = map.getCameraPosition();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        map.setMyLocationEnabled(true);
        if(saveCamera!=null)setSaveCamera();

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_flag))
                        .draggable(true)

                );
            }
        });
    }


}
