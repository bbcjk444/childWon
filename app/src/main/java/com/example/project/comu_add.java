package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class comu_add extends AppCompatActivity {
    EditText comu_add_content,comu_add_title;
    Button comu_add_exit;

    String id;
    String title;
    String content;
    String time;

    public comu_add(String id, String title, String content, String time){
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comu_add);

        comu_add_content =findViewById(R.id.comu_add_content2);
        comu_add_title = findViewById(R.id.comu_add_title2);
        comu_add_exit = findViewById(R.id.comu_add_exit2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference userRef = ref.child("comu_table");


        long time2 = System.currentTimeMillis();
        Date date = new Date(time2);
        SimpleDateFormat mFormat = new SimpleDateFormat("MM월 dd일 hh시 mm분");
        String gettime = mFormat.format(date);


        comu_add ca = new comu_add("연하맘", "오늘 날씨가 좋네요", "딸 데리고 산책 다녀와야겠어요",gettime);

        comu_add_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userRef.push().setValue(ca);

            }
        });
    }
}