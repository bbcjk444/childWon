package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pedro.library.AutoPermissions;

public class mapPage extends AppCompatActivity {
    Button map_exit;
    TextView textView;
    GoogleMap map2;
    MarkerOptions myLocationMarker;
    LocationManager manager;
    boolean detect = true;

    FirebaseDatabase database =  FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("busLocation");
    double latitude;
    double longitude;


    Marker myMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_page2);

        map_exit = findViewById(R.id.map_exit);
        textView = findViewById(R.id.textView16);



        // 파이어 베이스 값 가져오기
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    try {
//                        MyLocation location = snapshot.getValue(MyLocation.class);
//                        if (location != null){
//                            myMarker.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
//                        }
//                    }catch(Exception e){
//                        Toast.makeText(mapPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
                Log.d("MainActivity", "ValueEventListener" + snapshot.getValue());

                String latVALUE = String.valueOf(snapshot.child("lat").getValue());
                String logiVALUE = String.valueOf(snapshot.child("longi").getValue());

                latitude = Double.parseDouble(latVALUE);
                longitude = Double.parseDouble(logiVALUE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                map2 = googleMap;
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                map2.setMyLocationEnabled(true);

                LatLng bus_loc2 = new LatLng(latitude,longitude);
                /*map2.addMarker(new MarkerOptions()
                                .position(bus_loc2)
                                .title("현재 버스 위치")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation)));

                map2.getUiSettings().setZoomControlsEnabled(true);
                map2.getUiSettings().setAllGesturesEnabled(true);
                map2.moveCamera(CameraUpdateFactory.newLatLng(bus_loc2));*/
                map2.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        myLocationMarker = new MarkerOptions();
                        myLocationMarker.position(bus_loc2);
                        myLocationMarker.title("현재 버스 위치");
                        map2.getUiSettings().setAllGesturesEnabled(true);
                        map2.getUiSettings().setZoomControlsEnabled(true);
                        map2.animateCamera(CameraUpdateFactory.newLatLngZoom(bus_loc2,17));
                    }
                });
            }
        });

        map_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                detect=false;
                startActivity(intent);
            }
        });
    }

}