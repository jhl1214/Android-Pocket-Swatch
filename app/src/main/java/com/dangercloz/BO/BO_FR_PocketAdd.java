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

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.dangercloz.CO.CO_LO_PocketDB;
import com.dangercloz.CO.CO_OB_Pocket;
import com.dangercloz.pocketswatch.R;

public class BO_FR_PocketAdd extends Fragment {

    EditText name_field, desc_field;
    CO_LO_PocketDB pocketDB;
    boolean secrete = false;

    public BO_FR_PocketAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bo_fr_pocket_add, container, false);

        pocketDB = new CO_LO_PocketDB(v.getContext());

        name_field = (EditText)v.findViewById(R.id.pocket_name_field);
        desc_field = (EditText)v.findViewById(R.id.pocket_desc_field);

        ImageButton btn_close = (ImageButton)v.findViewById(R.id.btn_create_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                getFragmentManager().popBackStack();
                BO_AC_Main.grid_holder.setVisibility(View.VISIBLE);
            }
        });

        ImageButton btn_create = (ImageButton)v.findViewById(R.id.btn_create_confirm);
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_field.getText().length() != 0) {
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                    alert.setMessage("포켓박스를 만드시겠습니까?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String flag = "F";
                            if(secrete)
                                flag = "T";

                            // create
                            CO_OB_Pocket pocket = new CO_OB_Pocket(BO_AC_Main.userID, name_field.getText().toString(), desc_field.getText().toString(), flag, "01");

                            pocketDB.addPocket(pocket);
                            getFragmentManager().popBackStack();
                            BO_AC_Main.grid_holder.setVisibility(View.VISIBLE);

                            System.out.println("포켓박스가 생성되었습니다");
                        }
                    }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // cancel
                            return;
                        }
                    });

                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(v.getContext(), "포켓박스 이름을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final ImageView btn_secrete = (ImageView)v.findViewById(R.id.btn_secrete);
        btn_secrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(secrete) {
                    secrete = false;
                    btn_secrete.setImageResource(R.drawable.btn_off);
                } else {
                    secrete = true;
                    btn_secrete.setImageResource(R.drawable.btn_on);
                }
            }
        });

        /*FrameLayout pocket_color = (FrameLayout)v.findViewById(R.id.background_color);
        pocket_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable drawable = (GradientDrawable)v.getBackground();

                drawable.setColor(Color.RED);
                v.setBackground(drawable);
            }
        });*/

        return v;
    }


}
