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
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dangercloz.CO.CO_LO_DataCenter;
import com.dangercloz.CO.CO_LO_PocketDB;
import com.dangercloz.CO.CO_OB_Pocket;
import com.dangercloz.SE.SE_AC_Main;
import com.dangercloz.pocketswatch.R;

import java.util.List;

public class BO_FR_PocketAll extends Fragment {

    static CO_LO_PocketDB pocketDB;
    static List<CO_OB_Pocket> pocketList;
    static LayoutInflater inflater_temp;
    static TableLayout pocketLayout;
    static String[] row_image_string = {"pocket2", "pocket3", "pocket4", "pocket5", "pocket6", "pocket7"};

    public BO_FR_PocketAll() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bo_fr_pocket_all, container, false);

        pocketLayout = (TableLayout)v.findViewById(R.id.pocket_table);
        inflater_temp = inflater;

        pocketDB = new CO_LO_PocketDB(v.getContext());
        pocketList = pocketDB.getUserPockets(BO_AC_Main.userID);

        createPocketList(false);

        final TextView pocket_all_text = (TextView)v.findViewById(R.id.all_textview);
        pocket_all_text.setText(String.valueOf(pocketList.size()));
        final TextView pocket_all_title = (TextView)v.findViewById(R.id.all_title);

        final TextView pocket_share_text = (TextView)v.findViewById(R.id.share_textview);
        int count = 0;
        for(CO_OB_Pocket pocket : pocketList) {
            if(pocket.getPocketSecrete().equals("T")) {
                count++;
            }
        }
        pocket_share_text.setText(String.valueOf(count));
        final TextView pocket_share_title = (TextView)v.findViewById(R.id.share_title);

        FrameLayout btn_all = (FrameLayout)v.findViewById(R.id.btn_all);
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pocket_all_text.setTextColor(Color.parseColor("#3EB5B0"));
                pocket_all_title.setTextColor(Color.parseColor("#3EB5B0"));

                pocket_share_text.setTextColor(Color.parseColor("#FFFFFF"));
                pocket_share_title.setTextColor(Color.parseColor("#FFFFFF"));

                createPocketList(false);
            }
        });

        FrameLayout btn_share = (FrameLayout)v.findViewById(R.id.btn_share);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pocket_all_text.setTextColor(Color.parseColor("#FFFFFF"));
                pocket_all_title.setTextColor(Color.parseColor("#FFFFFF"));

                pocket_share_text.setTextColor(Color.parseColor("#3EB5B0"));
                pocket_share_title.setTextColor(Color.parseColor("#3EB5B0"));

                createPocketList(true);
            }
        });

        return v;
    }

    public void createPocketList(boolean shared) {
        pocketLayout.removeAllViews();

        if(shared) {
            int i=0;
            boolean first = true;
            for(CO_OB_Pocket pocket : pocketList) {
                if(pocket.getPocketSecrete().equals("T")) {
                    View row = inflater_temp.inflate(R.layout.custom_pocket_row, pocketLayout, false);
                    row.setId(pocket.getPocketID());

                    TextView name = (TextView)row.findViewById(R.id.pocket_name);
                    name.setText(pocket.getPocketName());
                    TextView count = (TextView)row.findViewById(R.id.pocket_count);
                    count.setText(String.valueOf(CO_LO_DataCenter.getPocketContainer(String.valueOf(pocket.getPocketID())).size()));

                    if(first) {
                        first = false;
                    } else {
                        ViewGroup background = (ViewGroup)row.findViewById(R.id.row_background);
                        background.setBackgroundColor(Color.parseColor("#696969"));
                    }

                    int resId = row.getContext().getResources().getIdentifier(row_image_string[i%6], "drawable", row.getContext().getPackageName());
                    ImageView row_image = (ImageView)row.findViewById(R.id.row_image);
                    row_image.setImageResource(resId);

                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CO_OB_Pocket pocket = pocketDB.getPocket(v.getId());

                            getFragmentManager().popBackStack();
                            BO_AC_Main.pocket_sub_fragment = getFragmentManager().findFragmentById(R.id.pocket_sub_layout);

                            if(BO_AC_Main.pocket_sub_fragment == null) {
                                ViewGroup.LayoutParams layoutParams = BO_AC_Main.pocket_sub_layout.getLayoutParams();
                                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                                Bundle bundle = new Bundle();
                                bundle.putInt("id", pocket.getPocketID());
                                BO_FR_PocketDetail detail = new BO_FR_PocketDetail();
                                detail.setArguments(bundle);

                                getFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.animator.slide_up,
                                                R.animator.slide_down,
                                                R.animator.slide_up,
                                                R.animator.slide_down)
                                        .add(R.id.pocket_sub_layout, detail)
                                        .addToBackStack(null)
                                        .commit();

                                BO_AC_Main.grid_holder.setVisibility(View.GONE);
                            }
                        }
                    });

                    row.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            Toast.makeText(v.getContext(), "미니 포켓으로 설정되었습니다.", Toast.LENGTH_SHORT).show();

                            BO_AC_Main.currentPocket = pocketDB.getPocket(v.getId());
                            BO_AC_Main.refresh_selected_tag_bar();
                            if (SE_AC_Main.current_name != null) {
                                SE_AC_Main.refresh_layout();
                            }

                            return true;
                        }
                    });

                    pocketLayout.addView(row);
                }
            }
        } else {
            int i=0;
            boolean first = true;
            for(CO_OB_Pocket pocket : pocketList) {
                View row = inflater_temp.inflate(R.layout.custom_pocket_row, pocketLayout, false);
                row.setId(pocket.getPocketID());

                TextView name = (TextView)row.findViewById(R.id.pocket_name);
                name.setText(pocket.getPocketName());
                TextView count = (TextView)row.findViewById(R.id.pocket_count);
                count.setText(String.valueOf(CO_LO_DataCenter.getPocketContainer(String.valueOf(pocket.getPocketID())).size()));

                if(first) {
                    first = false;
                } else {
                    ViewGroup background = (ViewGroup)row.findViewById(R.id.row_background);
                    background.setBackgroundColor(Color.parseColor("#696969"));
                }

                int resId = row.getContext().getResources().getIdentifier(row_image_string[i%6], "drawable", row.getContext().getPackageName());
                ImageView row_image = (ImageView)row.findViewById(R.id.row_image);
                row_image.setImageResource(resId);

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CO_OB_Pocket pocket = pocketDB.getPocket(v.getId());

                        getFragmentManager().popBackStack();
                        BO_AC_Main.pocket_sub_fragment = getFragmentManager().findFragmentById(R.id.pocket_sub_layout);

                        if (BO_AC_Main.pocket_sub_fragment == null) {
                            ViewGroup.LayoutParams layoutParams = BO_AC_Main.pocket_sub_layout.getLayoutParams();
                            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                            Bundle bundle = new Bundle();
                            bundle.putInt("id", pocket.getPocketID());
                            BO_FR_PocketDetail detail = new BO_FR_PocketDetail();
                            detail.setArguments(bundle);

                            getFragmentManager().beginTransaction()
                                    .setCustomAnimations(R.animator.slide_up,
                                            R.animator.slide_down,
                                            R.animator.slide_up,
                                            R.animator.slide_down)
                                    .add(R.id.pocket_sub_layout, detail)
                                    .addToBackStack(null)
                                    .commit();

                            BO_AC_Main.grid_holder.setVisibility(View.GONE);
                        }
                    }
                });

                row.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText(v.getContext(), "미니 포켓으로 설정되었습니다.", Toast.LENGTH_SHORT).show();

                        BO_AC_Main.currentPocket = pocketDB.getPocket(v.getId());
                        BO_AC_Main.refresh_selected_tag_bar();
                        if (SE_AC_Main.current_name != null) {
                            SE_AC_Main.refresh_layout();
                        }

                        return true;
                    }
                });

                pocketLayout.addView(row);
            }
        }
    }


}
