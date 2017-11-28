package com.minor2k17.nimble;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shaur on 02-11-2017.
 */


public class year_adapter extends ArrayAdapter<year_list> {

    public year_adapter (Activity context, ArrayList<year_list> Num)
    {
        super(context,0,Num);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v=convertView;
        if(v==null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listitem, parent,false);
        }

        //Listitem position
        year_list current_year = getItem(position);

        TextView nameTextView = (TextView) v.findViewById(R.id.list_item_title);
        nameTextView.setText(current_year.getlisttitle());

        TextView linkTextView = (TextView) v.findViewById(R.id.list_item_link);
        linkTextView.setText(current_year.getlistlink());

        ImageView imageview = (ImageView) v.findViewById(R.id.list_item_icon);
        imageview.setImageResource(current_year.getmImageResourceId());

        return v;
    }

}
