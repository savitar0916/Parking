package com.example.parking;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Element;

public class ViewAdapter extends BaseAdapter {

    private String[][] ElementsData;
    private LayoutInflater inflater;

    static class ViewHolder{
        LinearLayout rlBorder;
        TextView Name;
        TextView Quantity;
    }

    public ViewAdapter(String[][] data,LayoutInflater inflater)
    {
        this.ElementsData=data;
        this.inflater=inflater;
    }

    @Override
    public int getCount() {
        return ElementsData.length;
    }

    @Override
    public Object getItem(int position) {
        return ElementsData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_park_listview,null);
            holder.Name=(TextView)convertView.findViewById(R.id.txv_Name);
            holder.Quantity=(TextView)convertView.findViewById(R.id.txv_Quantity);
            holder.rlBorder=(LinearLayout)convertView.findViewById(R.id.llBorder);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        int a=Math.abs(Integer.parseInt(ElementsData[position][2]));

        if(ElementsData[position][0].equals("1"))
        {
            holder.Name.setText(ElementsData[position][1]);
            holder.Name.setTextColor(Color.parseColor("#000000"));
            holder.Quantity.setText("空位:"+a+"個");
            holder.Quantity.setTextColor(Color.parseColor("#000000"));
            holder.rlBorder.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        else if(ElementsData[position][0].equals("2"))
        {
            holder.Name.setText(ElementsData[position][1]);
            holder.Name.setTextColor(Color.parseColor("#FFFFFF"));
            holder.Quantity.setText("空位:"+a+"個");
            holder.Quantity.setTextColor(Color.parseColor("#FFFFFF"));
            holder.rlBorder.setBackgroundColor(Color.parseColor("#f8969f"));
        }
        else if(ElementsData[position][0].equals("3"))
        {
            holder.Name.setText(ElementsData[position][1]);
            holder.Name.setTextColor(Color.parseColor("#FFFFFF"));
            holder.Quantity.setText("空位:"+a+"個");
            holder.Quantity.setTextColor(Color.parseColor("#FFFFFF"));
            holder.rlBorder.setBackgroundColor(Color.parseColor("#9999cc"));
        }
        return convertView;
    }
}
