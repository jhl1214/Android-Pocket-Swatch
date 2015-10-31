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

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dangercloz.CO.CO_OB_Fiber;
import com.dangercloz.pocketswatch.R;

public class BO_LO_ResultAdapter extends BaseAdapter {

    private Context context;
    private final CO_OB_Fiber[] fibers;
    int endx, endy;

    public BO_LO_ResultAdapter(Context context, CO_OB_Fiber[] values) {
        this.context = context;
        this.fibers = values;
    }

    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View gridView;
        if(convertView == null) {
            gridView = inflater.inflate(R.layout.result_adapter, null);

            final ImageView fiber_image = (ImageView)gridView.findViewById(R.id.fiber_image);
            int resId = context.getResources().getIdentifier(fibers[position].getFiberImage(), "drawable", context.getPackageName());
            fiber_image.setImageResource(resId);
            gridView.setTag(String.valueOf(fibers[position].getFiberID()));

            gridView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

                    String[] mymeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                    ClipData dragData = new ClipData(v.getTag().toString(), mymeTypes, item);

                    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(fiber_image);

                    v.startDrag(dragData, myShadow, v, 0);

                    return true;
                }
            });

            /*gridView.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    switch(event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            //Log.d("DRAG", "Action drag started");
                            break;
                        case DragEvent.ACTION_DRAG_ENTERED:
                            //Log.d("DRAG", String.format("Drag entered %d %d", (int)event.getX(), (int)event.getY()));
                            break;
                        case DragEvent.ACTION_DRAG_LOCATION:
                            //Log.d("DRAG", String.format("Drag location %d %d", (int)event.getX(), (int)event.getY()));
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            //Log.d("DRAG", String.format("Drag exited %d %d", (int)event.getX(), (int)event.getY()));
                            endx = (int)event.getX();
                            endy = (int)event.getY();
                            break;
                        case DragEvent.ACTION_DRAG_ENDED:
                            //Log.d("DRAG", String.format("Drag ended %d %d", endx, endy));
                            break;
                        case DragEvent.ACTION_DROP:
                            //Log.d("DRAG", "Action drop");
                            break;
                    }

                    return true;
                }
            });*/
        } else {
            gridView = convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return fibers.length;
    }

    @Override
    public Object getItem(int position) {
        return fibers[position];
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