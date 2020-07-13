package com.example.p08_locationaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_Singapore = new LatLng(1.371792, 103.803361);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore, 11));

                LatLng poi_North = new LatLng(1.463020, 103.828981);
                final Marker north = map.addMarker(new MarkerOptions().position(poi_North).title("HQ - North").snippet("Block 333, Admiralty Ave 3, 765654\nOperating hours: 10am-5pm\nTel:65433456\n").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

                LatLng poi_Central = new LatLng(1.305464, 103.844807);

                final Marker central = map.addMarker(new MarkerOptions().position(poi_Central).title("Central").snippet("Block 3A, Orchard Ave 3, 134542\nOperating hours: 11am-8pm\nTel:67788652\n").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_East = new LatLng(1.352175, 103.931748);

                final Marker east = map.addMarker(new MarkerOptions().position(poi_East).title("East").snippet("Block 555, Tampines Ave 3, 287788\nOperating hours: 9am-5pm\nTel:66776677\n").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        if (marker.equals(north)) {
                            north.showInfoWindow();
                            Toast.makeText(MainActivity.this, "North - HQ", Toast.LENGTH_LONG).show();
                        } else if (marker.equals(central)) {
                            central.showInfoWindow();
                            Toast.makeText(MainActivity.this, "Central", Toast.LENGTH_LONG).show();
                        } else if (marker.equals(east)) {
                            east.showInfoWindow();
                            Toast.makeText(MainActivity.this, "East", Toast.LENGTH_LONG).show();
                        }
                            return true;
                        }
                });
            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LatLng poi_North = new LatLng(1.463020, 103.828981);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North, 15));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LatLng poi_Central = new LatLng(1.305464, 103.844807);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central, 15));

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LatLng poi_East = new LatLng(1.352175, 103.931748);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East, 15));
            }
        });

    }


}
