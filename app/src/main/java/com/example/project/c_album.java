package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class c_album extends AppCompatActivity {

    Button c_album_add, c_album_delete, c_album_exit;
    TableLayout tableLayout;
    private static final int REQUEST_CODE = 0;
    ArrayList<String> imageViews = new ArrayList<>();
    LinearLayout linearLayout,linearLayout3,linearLayout4;
    ImageView imageView16;
    ListView lv3;
    albumAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_album);
        lv3 = findViewById(R.id.lv3);
        //linearLayout = findViewById(R.id.linearLayout);
        //linearLayout4 = findViewById(R.id.linearLayout4);
        //linearLayout3 = findViewById(R.id.lv3);
        imageView16 = findViewById(R.id.imageView16);
        c_album_add = findViewById(R.id.c_album_add);
        c_album_delete = findViewById(R.id.c_album_delete);
        c_album_exit = findViewById(R.id.c_album_exit);

        adapter = new albumAdapter(getApplicationContext(),R.layout.layout, imageViews);
        lv3.setAdapter(adapter);
        c_album_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이미지추가
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        c_album_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), c_menuPage.class);
                startActivity(intent);
                finish();
            }
        });

        c_album_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //이미지삭제
            }
        });

    }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    try {
                        InputStream in = getContentResolver().openInputStream(data.getData());

                        Bitmap img = BitmapFactory.decodeStream(in);
                        in.close();
                        imageView16.setImageBitmap(img);
                        //imageViews.add()
                        //imageViews.add(img+"");
                        //lv3.setSelection(adapter.getCount()-1);

                    } catch (Exception e) {

                    }
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
                }
            }
        }
    }





