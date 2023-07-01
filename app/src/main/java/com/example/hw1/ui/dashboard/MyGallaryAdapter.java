package com.example.hw1.ui.dashboard;

//import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.Api21Impl.getResources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hw1.R;
import com.example.hw1.R.id;

import java.util.ArrayList;

public class MyGallaryAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MyData> dataList;

    public MyGallaryAdapter(Context context, ArrayList<MyData> data) {
        mContext = context;
        dataList = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.layout_gallary_list_item, null);

        ImageView imageView = (ImageView) view.findViewById(id.gallary_img);
        TextView textView = (TextView) view.findViewById(id.gallary_text);

        String img_src = dataList.get(i).getSrc();
        int img_id = mContext.getResources().getIdentifier(img_src, "drawable", mContext.getPackageName());


        Glide.with(mContext)
                .load(img_id)
                .into(imageView);

        textView.setText(dataList.get(i).getText());


        return view;
    }
}