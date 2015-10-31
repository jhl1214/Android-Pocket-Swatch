/**
 *
 *  Pocket Swatch
 *
 *  Copyright © 2015 Junho. All rights reserved.
 *
 *  This is a proprietary of Junho, and you may not use this file except in compliance with license agreement with Junho.
 *  Any redistribution or use of this software, with or without modification shall be strictly prohibited without prior
 *  written approval of Junho, and the copyright notice above does not evidence any actual or intended publication of such software.
 *
 */

package com.dangercloz.BO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dangercloz.pocketswatch.R;

public class BO_LO_CustomAdapter extends BaseAdapter {

    private Context context;
    private final String[] tag_values;

    public BO_LO_CustomAdapter(Context context, String[] values) {
        this.context = context;
        this.tag_values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertView == null) {
            boolean temp = false;

            for(String sel_tag : BO_FR_SearchTag.selected_tag) {
                if(sel_tag.equals(tag_values[position])) {
                    temp = true;
                }
            }

            if(temp) {
                gridView = inflater.inflate(R.layout.custom_adapter_selected, null);
            } else {
                gridView = inflater.inflate(R.layout.custom_adapter, null);
            }

            final TextView textView = (TextView)gridView.findViewById(R.id.tag_string);
            textView.setText(tag_values[position]);

            FrameLayout tagView = (FrameLayout)gridView.findViewById(R.id.tag_view);
            tagView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BO_FR_SearchTag.setSelected_tag(textView.getText().toString());
                    BO_FR_SearchTag.refresh_list();
                }
            });

            //textView.measure(0, 0);

            //ListView.LayoutParams params = new ListView.LayoutParams(textView.getMeasuredWidth()+10, ViewGroup.LayoutParams.WRAP_CONTENT);
            //gridView.setLayoutParams(params);
        } else {
            gridView = convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return tag_values.length;
    }

    @Override
    public Object getItem(int position) {
        return tag_values[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return false;
    }

    @Override
    public boolean isEnabled(int position)
    {
        return false;
    }

}