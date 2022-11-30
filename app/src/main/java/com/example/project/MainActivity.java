package com.example.project;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

public class MainActivity extends AppCompatActivity {

    ImageView menu_img;

    ImageButton menu_album,menu_cal,menu_bus,menu_comu,menu_alarm;
    Button btn_logout;
    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu_alarm = findViewById(R.id.menu_alarm);
        btn_logout = findViewById(R.id.btn_logout);
        menu_album = findViewById(R.id.menu_album);
        menu_bus = findViewById(R.id.menu_bus);
        menu_cal = findViewById(R.id.menu_cal);
        menu_comu = findViewById(R.id.menu_comu);


        menu_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),alarmpage.class);
                startActivity(intent);
                finish();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mainPage.class);
                startActivity(intent);
                finish();
            }
        });

        menu_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),albumPage2.class);
                startActivity(intent);
                finish();
            }
        });

        menu_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),calanderPage.class);
                startActivity(intent);
                finish();
            }
        });

        menu_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mapPage.class);
                startActivity(intent);
                finish();
            }
        });

        menu_comu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),listview.class);
                Intent intent1 = getIntent();
                String id = intent1.getStringExtra("id");
                intent.putExtra("id",id);
                Log.d("id_id",id);
                startActivity(intent);
                finish();
            }
        });

    }

}