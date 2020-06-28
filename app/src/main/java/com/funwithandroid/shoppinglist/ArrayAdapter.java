package com.funwithandroid.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayAdapter extends BaseAdapter {
    private ArrayList<ItemObject> arrayList;
    public ArrayAdapter(ArrayList<ItemObject> arrayList) {
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return  arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        TextView item_name=convertView.findViewById(R.id.card_list_name);
        TextView item_count=convertView.findViewById(R.id.card_list_quantity);
        item_name.setText(arrayList.get(position).getItem_name());
        item_count.setText(arrayList.get(position).getItem_quality());
        return convertView;
    }
}
