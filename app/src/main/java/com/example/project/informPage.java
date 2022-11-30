package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class informPage extends AppCompatActivity {

    Button inform_exit,inform_ok;
    TextView inform_data,inform_date;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_page);

        inform_ok = findViewById(R.id.inform_ok);
        inform_date = findViewById(R.id.inform_date);
        inform_data = findViewById(R.id.inform_data);
        inform_exit = findViewById(R.id.inform_exit);

        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        String gettime = mFormat.format(date);

        Intent intent = getIntent();
        String date2 = intent.getStringExtra("date");
        inform_date.setText(date2);

        inform_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),c_menuPage.class);
                startActivity(intent);
                finish();
            }
        });
        inform_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("inform_table").child("data").child(date2).setValue(inform_data.getText().toString());
                Intent intent = new Intent(getApplicationContext(),c_menuPage.class);
                startActivity(intent);
            }
        });
    }
}