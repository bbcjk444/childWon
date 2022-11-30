package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class calanderPage2 extends AppCompatActivity {
    Button exit2;
    CalendarView calv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander_page2);

        calv = findViewById(R.id.calv);
        exit2 = findViewById(R.id.exit2);
        exit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),c_menuPage.class);
                startActivity(intent);
                finish();
            }
        });

        calv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if(month+1<10){
                    if(dayOfMonth<10) {
                        String date = year + "-" + "0" + (month + 1) + "-" + "0" + dayOfMonth;
                        Intent intent = new Intent(calanderPage2.this,informPage.class);
                        intent.putExtra("date",date);
                        startActivity(intent);

                    }else{
                        String date = year +"-" + "0" + (month+1) + "-" + dayOfMonth;
                        Intent intent = new Intent(calanderPage2.this,informPage.class);
                        intent.putExtra("date",date);
                        startActivity(intent);
                    }
                }else{
                    String date = year +"-" + (month+1) + "-" + dayOfMonth;
                    Intent intent = new Intent(calanderPage2.this,informPage.class);
                    intent.putExtra("date",date);
                    startActivity(intent);

                }
            }
        });
    }
}