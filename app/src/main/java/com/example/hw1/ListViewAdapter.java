package com.example.hw1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<Junbun> items;
    private Context mContext;

    public ListViewAdapter(ArrayList<Junbun> items, Context mContext) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listview, viewGroup, false);
        }

        TextView name = view.findViewById(R.id.textView);
        TextView number = view.findViewById(R.id.textView2);
        name.setText(items.get(i).name);
        number.setText(items.get(i).number);
        return view;
    }
}
