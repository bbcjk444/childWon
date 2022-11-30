package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class alarmpage extends AppCompatActivity {
    Button al_exit;
    ListView lv;
    ArrayList<String> alarm = new ArrayList<>();

    FirebaseDatabase database =  FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("alarm_tb");
    ChildEventListener mChild = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_page);
        al_exit = findViewById(R.id.al_exit);

        lv = findViewById(R.id.lv);

        AlarmAdapter adapter = new AlarmAdapter(getApplicationContext(),R.layout.alarm_list,alarm);
        lv.setAdapter(adapter);

        // 데이터베이스 값 불러오기
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                initDatabase();

                for(DataSnapshot messageData : snapshot.getChildren()){
                    // child 내에 있는 데이터만큼 반복
                    String message = messageData.getValue().toString();
                    Log.d("mesgd",message);
                    alarm.add(message+"\n");
                }
                adapter.notifyDataSetChanged();
                lv.setSelection(adapter.getCount()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("alarmpage", "Failed to read value.", error.toException());
            }
        });

        // 나가기
        al_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    }

    private void initDatabase() {

        mChild = new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.addChildEventListener(mChild);
    }
}

