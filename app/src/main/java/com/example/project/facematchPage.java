package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.MapsInitializer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class facematchPage extends AppCompatActivity {

    View faceView;
    Button face_exit;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("busLocation");

    Map<String, Object> locValue = null;
    Busloc busloc = null;

    LocationManager manager;
    GPSListener gpsListener;
    Location location = null;

    double latitude;
    double longitude;
    // 현재 시간 나타내기
    long mNow = System.currentTimeMillis();
    Date date = new Date(mNow);
    SimpleDateFormat mFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분");
    String formatDate = mFormat.format(date);
    NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facematch_page);

        faceView = findViewById(R.id.faceView);
        face_exit = findViewById(R.id.face_exit);

        face_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), driverPage.class);
                startActivity(intent);
                onPause();
            }
        });
    }
    // 위도 경도 값 파이어 베이스에 넣을 값 저장
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
    @Override
    protected void onPause() {
        super.onPause();
        manager.removeUpdates(gpsListener);
    }
    public String Detect_location(LocationManager lo_manager){
        manager = lo_manager;
        gpsListener = new GPSListener();
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
                Log.i("MyLocTest", "최근 위치1 : " + msg);

                // 파이어베이스에 값 저장
                busloc = new Busloc(latitude, longitude);
                locValue = busloc.toMap();
                myRef.setValue(locValue);

                //위치 요청하기
                manager.requestLocationUpdates(manager.GPS_PROVIDER, minTime, minDistance, gpsListener);
                Log.i("MyLocTest", "requestLocationUpdates() 내 위치1에서 호출시작 ~~ ");

            } else if (manager.isProviderEnabled(manager.NETWORK_PROVIDER)) {

                location = manager.getLastKnownLocation(manager.NETWORK_PROVIDER);

                latitude = location.getLatitude();
                longitude = location.getLongitude();

                msg = "최근 위치2 -> Latitude : " + latitude + "\n Longitude : " + longitude;
                Log.i("MyLocTest", "최근 위치2 : " + msg);

                busloc = new Busloc(latitude, longitude);
                locValue = busloc.toMap();
                myRef.setValue(locValue);

                //위치 요청하기
                manager.requestLocationUpdates(manager.NETWORK_PROVIDER, minTime, minDistance, gpsListener);
                Log.i("MyLocTest", "requestLocationUpdates() 내 위치2에서 호출시작 ~~ ");
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        }
        
        return msg;
    }
    // 알림
    public void Notification_finish(Context context, NotificationManager notificationManager) {
        this.notificationManager = notificationManager;

        // 알림 누르면 보낼 곳
        Intent intent = new Intent(context, alarmpage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        myRef.push().setValue(formatDate+"에 안전운행 종료합니다.");

        // 알림 메시지 구조
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.bus)
                .setContentTitle("애든원") // 타이틀
                .setContentText(formatDate+"에 안전운행 종료합니다.") // 서브타이틀
                .setContentIntent(pendingIntent) // 알림 누르는 시점에 이동
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); // mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName , importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder.setSmallIcon(R.mipmap.ic_launcher_round); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴
    }
    // 알림
    public void Notification_start(Context context, NotificationManager notificationManager) {
        this.notificationManager = notificationManager;

        // 알림 누르면 보낼 곳
        Intent intent = new Intent(context, alarmpage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        myRef.push().setValue("'홍우영'어린이가 안전하게 승차하였습니다");

        // 알림 메시지 구조
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.bus)
                .setContentTitle("애든원") // 타이틀
                .setContentText(formatDate+"에 안전운행 시작합니다.") // 서브타이틀
                .setContentIntent(pendingIntent) // 알림 누르는 시점에 이동
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); // mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName , importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder.setSmallIcon(R.mipmap.ic_launcher_round); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴
    }
}