package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class albumMychild extends AppCompatActivity {

    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_mychild);
        // 1. 다량의 데이터
        // 2. Adapter
        // 3. AdapterView - GridView
        int img[] = {
                R.drawable.willi1,R.drawable.willil2,R.drawable.willi3,R.drawable.willi4,R.drawable.willi5};

        res = getResources();

        // 커스텀 아답타 생성
        albumMychild.MyAdapter adapter = new albumMychild.MyAdapter(
                getApplicationContext(),
                R.layout.row,       // GridView 항목의 레이아웃 row.xml
                img);    // 데이터
        GridView gv = (GridView)findViewById(R.id.gridFriut);
        gv.setAdapter((ListAdapter) adapter);  // 커스텀 아답타를 GridView 에 적용

        final TextView tv = (TextView) findViewById(R.id.FruitText);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // tv.setText("position : " + position);
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                i.putExtra("imgRes",img[position]);
                startActivity(i);

            }
        });
    } // end of onCreate

    class MyAdapter extends BaseAdapter {
        Context context;
        int layout;
        int img[];
        LayoutInflater inf;

        public MyAdapter(Context context, int layout, int[] img) {
            this.context = context;
            this.layout = layout;
            this.img = img;
            inf = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public Object getItem(int position) {
            return img[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null)
                convertView = inf.inflate(layout, null);
            ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);

            Bitmap bmp= BitmapFactory.decodeResource( res, img[position]);//image is your image
            bmp=Bitmap.createScaledBitmap(bmp, 300,300, true);
            iv.setImageBitmap(bmp);


            //iv.setImageResource(img[position]);


            return convertView;
        }


    }
}