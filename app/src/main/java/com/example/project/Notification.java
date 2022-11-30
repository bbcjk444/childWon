package com.example.project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Notification {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("alarm_tb");

    // 현재 시간 나타내기
    long mNow = System.currentTimeMillis();
    Date date = new Date(mNow);
    SimpleDateFormat mFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분");
    String formatDate = mFormat.format(date);
    NotificationManager notificationManager;


    // 알림
    public void Notification_finish(NotificationManager notificationManager, Context context) {
        this.notificationManager = notificationManager;

        // 알림 누르면 보낼 곳
        Intent intent = new Intent(context, alarmpage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        myRef.push().setValue(formatDate+"에 유치원에 도착하였습니다.");

        // 알림 메시지 구조
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "100001")
                .setSmallIcon(R.drawable.bus)
                .setContentTitle("애든원") // 타이틀
                .setContentText(formatDate+"에 유치원에 도착하였습니다.") // 서브타이틀
                .setContentIntent(pendingIntent) // 알림 누르는 시점에 이동
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); // mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "노티페케이션 채널";
            String description = "오레오 이상을 위한 것임";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName ,importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder.setSmallIcon(R.mipmap.ic_launcher_round); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴
    }


    // 알림
    public void Notification_start(NotificationManager notificationManager, Context context){
        this.notificationManager = notificationManager;
        // 알림 누르면 보낼 곳
        Intent intent = new Intent(context, alarmpage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        String sever = "sever";
        intent.putExtra("sever",sever);

        myRef.push().setValue(formatDate+"에 안전운행 시작합니다.");


        // 알림 메시지 구조
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.bus)
                .setContentTitle("애든원") // 타이틀
                .setContentText(formatDate+" 안전운행 종료합니다.") // 서브타이틀
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