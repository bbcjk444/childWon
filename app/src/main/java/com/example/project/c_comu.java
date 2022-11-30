package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class c_comu extends AppCompatActivity {

    Button comu_delete,comu_exit,comu_add;
    TextView comu_list;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_comu);

        comu_add = findViewById(R.id.comu_add);
        comu_delete = findViewById(R.id.comu_delete);
        comu_exit = findViewById(R.id.comu_exit);
        comu_list = findViewById(R.id.comu_list);

        comu_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //체크박스 버튼을 누른 키값을 선택> 삭제
                databaseReference.child("comu_table").child("키값").setValue(null);
                finish();
            }
        });
        comu_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),c_menuPage.class);
                startActivity(intent);
                finish();
            }
        });
        comu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),comu_add.class);
                Intent intent1 = getIntent();
                String id = intent1.getStringExtra("id");
                intent.putExtra("id",id);
                startActivity(intent);
                finish();
            }
        });

    }
}