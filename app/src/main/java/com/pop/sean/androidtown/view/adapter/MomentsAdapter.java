package com.pop.sean.androidtown.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pop.sean.androidtown.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stanzhao on 2/27/16.
 */
public class MomentsAdapter extends BaseAdapter {

    private List<String> dataList = new ArrayList<>();
    private ViewHolder holder;
    private Context context;

    public MomentsAdapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_moments, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(dataList.get(position));
        return convertView;

    }

    class ViewHolder {
        TextView tv;
        ImageView iv;

    }
}
