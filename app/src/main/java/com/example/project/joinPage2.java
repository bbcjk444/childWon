package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class joinPage2 extends AppCompatActivity {
    EditText c_join_name,c_join_id,c_join_pw,c_join_pwc,c_join_cname,c_join_add,c_join_phone;
    Button c_join_addfind,c_join_ok,c_join_cancel,c_join_idcheck;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_page2);

        c_join_idcheck = findViewById(R.id.c_join_idcheck);
        c_join_name = findViewById(R.id.c_join_name);
        c_join_id = findViewById(R.id.c_join_id);
        c_join_pw = findViewById(R.id.c_join_pw);
        c_join_pwc = findViewById(R.id.c_join_pwc);
        c_join_cname = findViewById(R.id.c_join_cname);
        c_join_add = findViewById(R.id.c_join_add);
        c_join_phone = findViewById(R.id.c_join_phone);
        c_join_addfind = findViewById(R.id.c_join_addfind);
        c_join_ok = findViewById(R.id.c_join_ok);
        c_join_cancel = findViewById(R.id.c_join_cancel);

        c_join_addfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        c_join_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c_join_name.getText().length() == 0 || c_join_id.getText().length() == 0 || c_join_pw.getText().length() == 0 || c_join_pwc.getText().length() == 0 || c_join_cname.getText().length() == 0 || c_join_phone.getText().length() == 0 &&
                      c_join_add.getText().length() == 0) {
                    Intent intent = new Intent(getApplicationContext(), joinPage2.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "빈칸을 입력하세요", Toast.LENGTH_SHORT).show();
                }else{
                    if (c_join_pw.getText().toString().equals(c_join_pwc.getText().toString())) {
                        Intent intent = new Intent(getApplicationContext(), mainPage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호가 서로 다릅니다", Toast.LENGTH_SHORT).show();
                    }
                }
                databaseReference.child("c_table").child("c_name").setValue(c_join_name.getText().toString());
                databaseReference.child("c_table").child("c_id").setValue(c_join_id.getText().toString());
                databaseReference.child("c_table").child("c_pw").setValue(c_join_pw.getText().toString());
                databaseReference.child("c_table").child("c_pwc").setValue(c_join_pwc.getText().toString());
                databaseReference.child("c_table").child("c_cname").setValue(c_join_cname.getText().toString());
                databaseReference.child("c_table").child("c_add").setValue(c_join_add.getText().toString());
                databaseReference.child("c_table").child("c_phone").setValue(c_join_phone.getText().toString());
            }
        });
        c_join_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mainPage2.class);
                startActivity(intent);
                finish();
            }
        });
        c_join_idcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c_join_id.getText().toString().equals("root")){
                    Toast.makeText(getApplicationContext(),"이미 사용중인 아이디입니다.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(joinPage2.this,"사용 가능한 아이디입니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}