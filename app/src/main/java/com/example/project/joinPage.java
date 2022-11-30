package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class joinPage extends AppCompatActivity {
    EditText p_join_name,p_join_id,p_join_pw,p_join_pwc,p_join_add,p_join_phone,p_join_phone2,p_join_image;
    Button p_join_addfind,p_join_imgupload,p_join_ok,p_join_cancel, p_join_idcheck;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_page);

        p_join_idcheck = findViewById(R.id.p_join_idcheck);
        p_join_name = findViewById(R.id.p_join_name);
        p_join_id = findViewById(R.id.p_join_id);
        p_join_pw = findViewById(R.id.p_join_pw);
        p_join_pwc = findViewById(R.id.p_join_pwc);
        p_join_add = findViewById(R.id.p_join_add);
        p_join_addfind = findViewById(R.id.p_join_addfind);
        p_join_phone = findViewById(R.id.p_join_phone);
        p_join_phone2 = findViewById(R.id.p_join_phone2);
        p_join_image = findViewById(R.id.p_join_image);
        p_join_imgupload = findViewById(R.id.p_join_imgupload);
        p_join_ok = findViewById(R.id.p_join_ok);
        p_join_cancel = findViewById(R.id.p_join_cancel);


            p_join_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (p_join_name.getText().length() == 0 || p_join_id.getText().length() == 0 || p_join_pw.getText().length() == 0 || p_join_pwc.getText().length() == 0 || p_join_add.getText().length() == 0 || p_join_phone.getText().length() == 0 &&
                            p_join_phone2.getText().length() == 0 || p_join_image.getText().length() == 0) {

                        Intent intent = new Intent(getApplicationContext(), joinPage.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "빈칸을 입력하세요", Toast.LENGTH_SHORT).show();
                    } else {
                        if (p_join_pw.getText().toString().equals(p_join_pwc.getText().toString())) {
                            Intent intent = new Intent(getApplicationContext(), mainPage.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "비밀번호가 서로 다릅니다", Toast.LENGTH_SHORT).show();
                        }
                    }


                    databaseReference.child("p_table").child("p_name").setValue(p_join_name.getText().toString());
                    databaseReference.child("p_table").child("p_id").setValue(p_join_id.getText().toString());
                    databaseReference.child("p_table").child("p_pw").setValue(p_join_pw.getText().toString());
                    databaseReference.child("p_table").child("p_pwc").setValue(p_join_pwc.getText().toString());
                    databaseReference.child("p_table").child("p_add").setValue(p_join_add.getText().toString());
                    databaseReference.child("p_table").child("p_phone").setValue(p_join_phone.getText().toString());
                    databaseReference.child("p_table").child("p_phone2").setValue(p_join_phone2.getText().toString());
                    databaseReference.child("p_table").child("p_image").setValue(p_join_image.getText().toString());

                }
            });

        p_join_idcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("joinPage","onClick()");
                if(p_join_id.getText().toString().equals("root")){

                    Log.v("joinPage","if");
                    Toast.makeText(getApplicationContext(),"이미 사용중인 아이디입니다.",Toast.LENGTH_SHORT).show();
                }else{

                    Log.v("joinPage","else");
                    //Toast.makeText(this,"사용 가능한 아이디입니다.",Toast.LENGTH_SHORT).show();
                    Toast.makeText(joinPage.this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                }

                Log.v("joinPage","onClick(2");
            }
        });

        p_join_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mainPage.class);
                startActivity(intent);
                finish();
            }
        });


    }
}