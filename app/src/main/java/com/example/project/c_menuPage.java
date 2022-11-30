package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class c_menuPage extends AppCompatActivity {
    ImageButton btn_drive,btn_inform,btn_comu,btn_album;
    Button btn_c_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_menu_page);

        btn_album = findViewById(R.id.btn_album);
        btn_comu = findViewById(R.id.btn_comu);
        btn_inform = findViewById(R.id.btn_inform);
        btn_drive = findViewById(R.id.btn_drive);
        btn_c_logout = findViewById(R.id.btn_c_logout);


        btn_drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),camerax.class);
                startActivity(intent);
                finish();
            }
        });

        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),c_album.class);
                startActivity(intent);
                finish();
            }
        });

        btn_comu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),listview.class);
                startActivity(intent);
                finish();
            }
        });


        btn_c_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mainPage2.class);
                startActivity(intent);
                finish();
            }
        });

        btn_inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),calanderPage2.class);
                startActivity(intent);
                finish();
            }
        });

    }
}