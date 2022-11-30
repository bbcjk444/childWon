package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mainPage extends AppCompatActivity {
    Button p_login,p_join,btn_c;
    EditText p_Id,p_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        p_login = findViewById(R.id.p_login);
        p_join = findViewById(R.id.p_join);

        btn_c = findViewById(R.id.btn_c);
        p_Id = findViewById(R.id.p_Id);
        p_pw = findViewById(R.id.p_pw);

        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mainPage2.class);
                startActivity(intent);
                finish();
            }
        });

        p_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(p_Id.getText().toString().equals("우둥이맘") && p_pw.getText().toString().equals("1234"))
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    String id = p_Id.getText().toString();
                    intent.putExtra("id",id);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(mainPage.this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        p_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),joinPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /*class NoThread extends Thread{

        @Override
        public void run() {
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    facematchPage facematchPage = new facematchPage();
                    NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    facematchPage.Notification_start(getApplicationContext(),notificationManager);
                }
            },10000);
        }

    }*/

}