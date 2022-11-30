package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class InformActivity extends AppCompatActivity {
    TextView notice_text,Date1;
    Button btn_notice_list;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);

        notice_text = findViewById(R.id.notice_text);
        Date1 = findViewById(R.id.Date1);
        btn_notice_list = findViewById(R.id.btn_notice_list);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        Date1.setText(date);

        long now = System.currentTimeMillis();
        Date mdate = new Date(now);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String gettime = simpleDateFormat.format(mdate);
        if(date.equals(databaseReference.child("inform_table").child("data").child(date).getKey())) {
            databaseReference.child("inform_table").child("data").child(date).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    notice_text.setText(snapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            System.exit(0);
        }

            btn_notice_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
    }
}