package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class calanderPage extends AppCompatActivity {
    CalendarView calendarView;
    Button cal_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander_page);

        calendarView = findViewById(R.id.calendarView);
        cal_exit = findViewById(R.id.cal_exit);

        if(calendarView.getVisibility()==View.INVISIBLE){
            calendarView.setVisibility(View.VISIBLE);
        }
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if(month+1<10){
                    if(dayOfMonth<10) {
                        String date = year + "-" + "0" + (month + 1) + "-" + "0" + dayOfMonth;
                        Intent intent = new Intent(calanderPage.this,InformActivity.class);
                        intent.putExtra("date",date);
                        startActivity(intent);

                    }else{
                        String date = year +"-" + "0" + (month+1) + "-" + dayOfMonth;
                        Intent intent = new Intent(calanderPage.this,InformActivity.class);
                        intent.putExtra("date",date);
                        startActivity(intent);

                    }
                    }else{
                    String date = year +"-" + (month+1) + "-" + dayOfMonth;
                    Intent intent = new Intent(calanderPage.this,InformActivity.class);
                    intent.putExtra("date",date);
                    startActivity(intent);

                }

            }
        });

        cal_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}