package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.SampleData;

import java.util.ArrayList;

public class myAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleData> sample ;

    public myAdapter(Context context,ArrayList<SampleData> data){
        mContext =context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public Object getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.list_item,null);
        TextView tv_id = (TextView)view.findViewById(R.id.tv_id);
        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
        TextView TV_content = (TextView)view.findViewById(R.id.TV_content);
        TextView tv_time = (TextView)view.findViewById(R.id.tv_time);

        tv_id.setText(sample.get(position).getId());
        tv_title.setText(sample.get(position).getTitle());
        TV_content.setText(sample.get(position).getContent());
        tv_time.setText(sample.get(position).getTime());

        return view;
    }
}
