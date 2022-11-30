package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = VERSION_CODES.O)
public class driverPage extends AppCompatActivity {
    Button dr_start,dr_stop,dr_exit;

    FirebaseDatabase database =  FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("alarm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_page);

        dr_start = findViewById(R.id.dr_start);
        dr_stop = findViewById(R.id.dr_stop);
        dr_exit = findViewById(R.id.dr_exit);

        dr_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.dr_start) {
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat mFormat = new SimpleDateFormat("MM월 dd일 hh시 mm분");
                    String gettime = mFormat.format(date);
                    String childtime = gettime + "OOO아이가 잘 탑승하였습니다.";
                    gettime = gettime + " 운행을 시작하였습니다";

                    Intent intent = new Intent(getApplicationContext(),facematchPage.class);
                    startActivity(intent);

                    myRef.push().setValue(gettime);
                }
            }

        });

        dr_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.dr_stop) {
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("MM월 dd일 hh시 mm분");
                    String gettime = format.format(date);
                    String childtime = gettime + "OOO아이가 잘 하차하였습니다.";
                    gettime = gettime + " 운행을 종료하였습니다";

                    myRef.push().setValue(gettime);

                }
            }
        });

        dr_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),c_menuPage.class);
                startActivity(intent);
            }
        });

    }

}
