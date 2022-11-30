package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlarmAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<String> data;
    LayoutInflater inflater;

    public AlarmAdapter(Context context, int layout, ArrayList<String> data){
        this.context = context;
        this.layout = layout;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(layout, null);
        }

        TextView tv_title = convertView.findViewById(R.id.tv_title);
        TextView tv_content = convertView.findViewById(R.id.tv_content);
        tv_content.setText(data.get(position));

        return convertView;
    }
}
