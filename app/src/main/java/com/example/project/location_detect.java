package com.example.project;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class location_detect {

    LocationManager manager;
    GPSListener gpsListener;
    Location location = null;
    double latitude;
    double longitude;
    Busloc busloc = null;
    Map<String, Object> locValue = null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("busLocation");




    public String Detect_location(LocationManager lo_manager){
        manager = lo_manager;
        gpsListener = new GPSListener();
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        String msg = "";
        try {
            long minTime = 0;        // 0초마다 갱신 - 바로바로갱신`
            float minDistance = 0;

            if (manager.isProviderEnabled(manager.GPS_PROVIDER)) {
                location = manager.getLastKnownLocation(manager.GPS_PROVIDER);

                // 위도 경도값 받아오기
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                msg = "최근 위치1 -> Latitude : " + latitude + "\n Longitude : " + longitude;
                Log.d("MyLocTest", "최근 위치1 : " + msg);

                // 파이어베이스에 값 저장
                busloc = new Busloc(latitude, longitude);
                locValue = busloc.toMap();
                myRef.setValue(locValue);

                //위치 요청하기
                manager.requestLocationUpdates(manager.GPS_PROVIDER, minTime, minDistance, gpsListener);


            } else if (manager.isProviderEnabled(manager.NETWORK_PROVIDER)) {

                location = manager.getLastKnownLocation(manager.NETWORK_PROVIDER);

                latitude = location.getLatitude();
                longitude = location.getLongitude();

                msg = "최근 위치2 -> Latitude : " + latitude + "\n Longitude : " + longitude;
                Log.d("MyLocTest", "최근 위치2 : " + msg);

                busloc = new Busloc(latitude, longitude);
                locValue = busloc.toMap();
                myRef.setValue(locValue);

                //위치 요청하기
                manager.requestLocationUpdates(manager.NETWORK_PROVIDER, minTime, minDistance, gpsListener);

            }

        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return msg;
    }
    class GPSListener implements LocationListener {

        // 위치 확인되었을때 자동으로 호출됨 (일정시간 and 일정거리)
        @Override
        public void onLocationChanged(Location location) {

            manager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            String msg = "최근 위치1 -> Latitude : " + latitude + "\n Longitude : " + longitude;
            Log.i("MyLocTest", "최근 위치 : " + msg);

            busloc = new Busloc(latitude, longitude);
            locValue = busloc.toMap();
            myRef.setValue(locValue);

            Log.i("MyLocTest", "onLocationChanged() 호출되었습니다.");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    public static class Busloc {
        public double lat;
        public double longi;


        public Busloc(double lat, double longi) {
            this.lat = lat;
            this.longi = longi;
        }

        public Map<String, Object> toMap() {
            HashMap<String, Object> buslocation = new HashMap<>();
            buslocation.put("lat", lat);
            buslocation.put("longi", longi);

            return buslocation;
        }
    }

}
