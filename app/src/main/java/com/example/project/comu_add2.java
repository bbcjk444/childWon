package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class comu_add2 extends AppCompatActivity {
    Button btn_comu_add, comu_add_exit2;
    EditText comu_add_title2, comu_add_content2;
    String id;
    String title;
    String content;
    String time;
    public comu_add2(){}

    public comu_add2(String id,String title,String content,String time){
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comu_add2);

        Intent intent1 = getIntent();
        String id = intent1.getStringExtra("id");
        Log.d("id_id",id);

        long time2 = System.currentTimeMillis();
        Date date = new Date(time2);
        SimpleDateFormat mFormat = new SimpleDateFormat("MM-dd-hh-mm");
        String gettime = mFormat.format(date);
        Log.d("id_id",gettime);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("test123");

        comu_add_title2 = findViewById(R.id.comu_add_title2);

        comu_add_content2 = findViewById(R.id.comu_add_content2);

        btn_comu_add = findViewById(R.id.btn_comu_add);


        btn_comu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = comu_add_title2.getText().toString();
                String content = comu_add_content2.getText().toString();
                VO vo = new VO(id,title,content,gettime);
                Log.d("id_id","id :"+id+", title : "+title+", content : "+content+", time: "+gettime);

                ref.push().setValue(vo);
                Intent intent = new Intent(getApplicationContext(),listview.class);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();
            }
        });
        comu_add_exit2 = findViewById(R.id.comu_add_exit2);
        comu_add_exit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),listview.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
