package com.founderr.founderr;

/* Created by mahaboob.basha on 19/09/17.
 */


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private final List<Category> catList;
    private final LayoutInflater layoutInflater;

    public SpinnerAdapter(Context applicationContext, List<Category> catList) {
        this.catList = catList;
        layoutInflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int i) {
        if (catList.size() > 0)
            return catList.get(i);
        else
            return new Category();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.spinner_item, viewGroup, false);
        TextView names = view.findViewById(R.id.spinner_item_text);
        names.setText(catList.get(i).getCategoyrName());
        return view;
    }
}
