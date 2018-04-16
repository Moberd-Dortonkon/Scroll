package com.example.koolguy.scroll;

import android.Manifest;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


public class MainActivity extends AppCompatActivity {
    HorizontalScrollMenuView menu;
    TextView textView;
    MyMap map;
    CameraPosition saveCamera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = (HorizontalScrollMenuView) findViewById(R.id.menu);
        textView = (TextView) findViewById(R.id.text);
        int i = 0;
         map = new MyMap(this,this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 9999);
        initMenu();



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
                        map.saveCamera();
                        anotherFragment();
                        break;
                    case 1:
                        map.saveCamera();
                         map.makeMap();
                        break;
                    case 2:
                        map.saveCamera();
                        anotherFragment();
                        break;
                    case 3:
                        map.saveCamera();
                        anotherFragment();
                        break;
                }

            }
        });

    }






}
