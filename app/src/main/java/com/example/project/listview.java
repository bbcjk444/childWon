package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listview extends AppCompatActivity {
    ArrayList<SampleData> DataList;

    Button btn_back,btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        this.addData();

        ListView listView = findViewById(R.id.listView);
        final myAdapter myAdapter1 = new myAdapter(this, DataList);

        listView.setAdapter(myAdapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
            }
        });
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                Intent intent1 = getIntent();
                String id = intent1.getStringExtra("id");
                intent.putExtra("id",id);
                startActivity(intent);
                finish();
            }
        });
        btn_write = findViewById(R.id.btn_write);
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),comu_add2.class);
                Intent intent1 = getIntent();
                String id = intent1.getStringExtra("id");
                intent.putExtra("id",id);
                Log.d("id_id",id);
                startActivity(intent);
                finish();
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("test123");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds :snapshot.getChildren()){
                    String id12 = ds.child("id").getValue(String.class);
                    String title12 = ds.child("title").getValue(String.class);
                    String content12 = ds.child("content").getValue(String.class);
                    String time12 = ds.child("time").getValue(String.class);
                    Log.d("every",id12+"");
                    DataList.add(new SampleData(""+id12,""+title12,""+content12,""+time12));
                }
                myAdapter1.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void addData() {
        DataList = new ArrayList<SampleData>();
//        DataList.add(new SampleData("연하맘","오늘 날씨가 좋네요~ ㅎㅎ","날씨 좋으니 딸데리고 산책나가야겠어요","2021-05-07 3:26"));
//        DataList.add(new SampleData("연하맘","오늘 날씨가 좋네요~ ㅎㅎ","날씨 좋으니 딸데리고 산책나가야겠어요","2021-05-07 3:26"));
//        DataList.add(new SampleData("연하맘","오늘 날씨가 좋네요~ ㅎㅎ","날씨 좋으니 딸데리고 산책나가야겠어요","2021-05-07 3:26"));
//        DataList.add(new SampleData("연하맘","오늘 날씨가 좋네요~ ㅎㅎ","날씨 좋으니 딸데리고 산책나가야겠어요","2021-05-07 3:26"));

    }


}