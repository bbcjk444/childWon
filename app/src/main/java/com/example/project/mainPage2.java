package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class mainPage2 extends AppCompatActivity {
    EditText c_Id,c_pw;
    Button btn_p1,c_login,c_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page2);

        c_Id = findViewById(R.id.c_Id);
        c_pw = findViewById(R.id.c_pw);

        btn_p1 = findViewById(R.id.btn_p1);
        c_login = findViewById(R.id.c_login);
        c_join = findViewById(R.id.c_join);


        btn_p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mainPage.class);
                startActivity(intent);
                finish();
            }
        });

        c_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c_Id.getText().toString().equals("root") && c_pw.getText().toString().equals("1234"))
                {
                    Intent intent = new Intent(getApplicationContext(), c_menuPage.class);
                    intent.putExtra("id",c_Id.getText());
                    startActivity(intent);
                    finish();
                }

            }
        });
        c_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),joinPage2.class);
                startActivity(intent);
                finish();
            }
        });


    }
}