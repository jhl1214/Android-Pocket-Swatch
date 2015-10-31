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
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.dangercloz.CO.CO_LO_DataCenter;
import com.dangercloz.CO.CO_OB_Hash;
import com.dangercloz.pocketswatch.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BO_FR_SearchTag extends Fragment {

    static GridView tag_gridview;
    static LinearLayout selected_bar;
    SearchView tag_search_view;
    ImageButton btn_tag_search, btn_close_tag;
    static HashMap<String, List<CO_OB_Hash>> hashMap;
    static ArrayList<String> selected_tag;
    static LayoutInflater inflater;
    static Context context;

    float touchY;

    public BO_FR_SearchTag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bo_fr_search_tag, container, false);
        this.inflater = inflater;
        context = v.getContext();

        tag_gridview = (GridView)v.findViewById(R.id.tag_gridview);
        tag_search_view = (SearchView)v.findViewById(R.id.tag_search_view);
        btn_tag_search = (ImageButton)v.findViewById(R.id.btn_tag_search);
        btn_close_tag = (ImageButton)v.findViewById(R.id.btn_close_tag);
        selected_bar = (LinearLayout)v.findViewById(R.id.selected_tag_bar);

        if(selected_tag == null) {
            selected_tag = new ArrayList<String>();
        }

        Bundle bundle = getArguments();
        if(bundle != null) {
            tag_search_view.setVisibility(View.VISIBLE);
            btn_tag_search.setVisibility(View.INVISIBLE);
        }

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                BO_AC_Main.refresh_selected_tag_bar();
            }
        });

        hashMap = CO_LO_DataCenter.getHashList();
        tag_gridview.setAdapter(new BO_LO_CustomAdapter(v.getContext(), hashMap.keySet().toArray(new String[0])));
        tag_gridview.setColumnWidth(200);

        btn_tag_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag_search_view.setVisibility(View.VISIBLE);
                btn_tag_search.setVisibility(View.INVISIBLE);
            }
        });

        ImageView btn_tag_list = (ImageView)v.findViewById(R.id.btn_tag_list);
        btn_tag_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(touchY + 20 > event.getY()) {
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

        tag_search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equals("")) {
                    //tag_gridview.setAdapter(new BO_LO_CustomAdapter(context, hashMap.keySet().toArray(new String[0])));
                } else {
                    newText = newText.toLowerCase();

                    ArrayList<String> tempList = new ArrayList<String>();
                    for(String key : hashMap.keySet()) {
                        if(key.toLowerCase().contains(newText)) {
                            tempList.add(key);
                        }
                    }

                    tag_gridview.setAdapter(new BO_LO_CustomAdapter(context, tempList.toArray(new String[0])));
                }

                tag_gridview.setFocusable(false);

                return true;
            }
        });

        btn_close_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_tag.clear();

                refresh_selected_tag_bar();
            }
        });

        if(selected_tag != null)
            refresh_selected_tag_bar();

        return v;
    }

    public static void setSelected_tag(String tag) {
        for(String temp : selected_tag) {
            if(temp.equals(tag)) {
                selected_tag.remove(temp);
                refresh_selected_tag_bar();
                return;
            }
        }

        selected_tag.add(tag);
        refresh_selected_tag_bar();
    }

    public static void refresh_selected_tag_bar() {
        selected_bar.removeAllViews();

        for(String temp : selected_tag) {
            View tagView = inflater.inflate(R.layout.custom_adapter_selected, null);

            final TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
            textView.setText(temp);

            tagView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected_tag.remove(textView.getText().toString());
                    refresh_selected_tag_bar();
                }
            });

            selected_bar.addView(tagView);
        }

        refresh_list();
        selected_bar.requestLayout();
    }

    public static void refresh_list() {
        tag_gridview.setAdapter(new BO_LO_CustomAdapter(context, hashMap.keySet().toArray(new String[0])));
    }

}