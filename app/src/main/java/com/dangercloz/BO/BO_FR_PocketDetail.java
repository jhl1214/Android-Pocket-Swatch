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

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dangercloz.CO.CO_FR_FiberInfo;
import com.dangercloz.CO.CO_LO_DataCenter;
import com.dangercloz.CO.CO_OB_Fiber;
import com.dangercloz.CO.CO_OB_Pocket;
import com.dangercloz.CO.CO_OB_PocketContainer;
import com.dangercloz.pocketswatch.R;

import java.util.ArrayList;
import java.util.List;

public class BO_FR_PocketDetail extends Fragment {

    List<CO_OB_Fiber> current_pocket_item;
    CO_OB_Pocket current_pocket;
    Fragment detail_fragment;
    public static LinearLayout item_holder;

    float touchY;

    public BO_FR_PocketDetail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bo_fr_pocket_detail, container, false);

        current_pocket_item = new ArrayList<CO_OB_Fiber>();
        List<CO_OB_PocketContainer> list = CO_LO_DataCenter.getPocketContainer(String.valueOf(getArguments().getInt("id")));
        for(CO_OB_PocketContainer pocketContainer : list) {
            CO_OB_Fiber fiber = CO_LO_DataCenter.getFiber(Integer.parseInt(pocketContainer.getFiberID()));
            current_pocket_item.add(fiber);
        }

        current_pocket = CO_LO_DataCenter.getPocket(getArguments().getInt("id"));
        TextView pocket_name = (TextView)v.findViewById(R.id.pocket_name);
        pocket_name.setText(current_pocket.getPocketName());
        TextView pocket_count = (TextView)v.findViewById(R.id.pocket_count);
        pocket_count.setText(String.valueOf(CO_LO_DataCenter.getPocketContainer(String.valueOf(current_pocket.getPocketID())).size()));

        item_holder = (LinearLayout)v.findViewById(R.id.item_holder);
        LinearLayout current = null;

        int i=0;
        for(CO_OB_Fiber fiber : current_pocket_item) {
            if(i%3 == 0) {
                LinearLayout temp = new LinearLayout(v.getContext());
                temp.setOrientation(LinearLayout.HORIZONTAL);

                current = temp;
                item_holder.addView(current);
            }

            ImageView image = new ImageView(v.getContext());
            image.setMaxHeight(180);
            image.setMaxWidth(180);
            image.setMinimumHeight(180);
            image.setMinimumWidth(180);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setAdjustViewBounds(false);
            int resId = v.getContext().getResources().getIdentifier(fiber.getFiberImage(), "drawable", v.getContext().getPackageName());
            image.setImageResource(resId);
            image.setPadding(10, 10, 10, 10);
            image.setTag(String.valueOf(fiber.getFiberID()));

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detail_fragment = BO_AC_Main.detail_fragment;

                    getFragmentManager().popBackStack();
                    detail_fragment = getFragmentManager().findFragmentById(R.id.pocket_detail);

                    if (detail_fragment == null) {
                        ViewGroup.LayoutParams layoutParams = BO_AC_Main.pocket_detail.getLayoutParams();
                        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                        Bundle bundle = new Bundle();
                        bundle.putString("id", v.getTag().toString());

                        CO_FR_FiberInfo fiberInfo = new CO_FR_FiberInfo();
                        fiberInfo.setArguments(bundle);

                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.animator.search_up,
                                        R.animator.search_down,
                                        R.animator.search_up,
                                        R.animator.search_down)
                                .add(R.id.pocket_detail, fiberInfo)
                                .addToBackStack(null)
                                .commit();

                        item_holder.setVisibility(View.GONE);
                    }
                }
            });

            current.addView(image);
            i++;
        }

        FrameLayout btn_close = (FrameLayout)v.findViewById(R.id.btn_close);
        btn_close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(touchY - 20 < event.getY()) {
                            InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                            getFragmentManager().popBackStack();
                            BO_AC_Main.grid_holder.setVisibility(View.VISIBLE);
                        }
                        break;
                }
                return true;
            }
        });

        return v;
    }


}
