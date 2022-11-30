package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class albumPage2 extends AppCompatActivity {

    ImageButton btn_flower,btn_plant,btn_every,btn_fruit,btn_mychild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("hhd","oncreate albumPage2");
        setContentView(R.layout.activity_album_page2);

        btn_flower = findViewById(R.id.btn_flower);
        btn_plant = findViewById(R.id.btn_plant);
        btn_every = findViewById(R.id.btn_every);
        btn_fruit = findViewById(R.id.btn_fruit);
        btn_mychild = findViewById(R.id.btn_mychild);



        btn_flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),albumFlower.class);
                startActivity(intent);
            }
        });

        btn_mychild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),albumMychild.class);
                startActivity(intent);
            }
        });

        btn_fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),albumFruit.class);
                startActivity(intent);
            }
        });


        btn_every.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),albumPage.class);
                startActivity(intent);
            }
        });

        btn_plant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),albumPlant.class);
                startActivity(intent);
            }
        });




    }
}