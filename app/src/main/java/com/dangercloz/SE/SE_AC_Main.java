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

package com.dangercloz.SE;

import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dangercloz.BO.BO_AC_Main;
import com.dangercloz.BO.BO_FR_PocketAdd;
import com.dangercloz.BO.BO_FR_PocketAll;
import com.dangercloz.CO.CO_FR_FiberInfo;
import com.dangercloz.CO.CO_LO_DataCenter;
import com.dangercloz.CO.CO_OB_Fiber;
import com.dangercloz.DC.DC_AC_Main;
import com.dangercloz.MI.MI_AC_Main;
import com.dangercloz.pocketswatch.CO_RangeSeekBar;
import com.dangercloz.pocketswatch.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;

public class SE_AC_Main extends Activity {

    static ViewGroup all_layout, matching_layout, dragging_holder, matching_info, matching_info_layout, result_margin, count_frame, item_layout;
    static ViewGroup type_area, mixture_area, color_area, density_area, thick_area, price_area, setted_value_holder, matching_holder;
    static Activity activity;
    static ImageView tagging_intro;
    public static TextView current_name;
    static String draggingFiber;
    static CO_OB_Fiber matching_fiber;
    static RelativeLayout swatch_matching;
    public static Fragment pocket_fragment, pocket_sub_fragment;
    public static ViewGroup pocket_layout, pocket_sub_layout, add_layout;
    static boolean[] type_flag = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    static String[] type_string = {"화섬", "직기", "니트", "다이마루", "나염", "인조피혁", "면", "누빔", "양복지", "양장지", "광목", "스판", "레이스", "실크", "울", "알파카", "인조모피", "디지털프린트", "털", "기타"};
    static String[] type_string2 = {"hwasum", "jikgi", "nit", "daima", "nayum", "yinjo", "myeon", "nubim", "yangbok", "yangjang", "gwang", "span", "race", "silk", "wool", "alpha", "mopi", "digital", "tul", "etc"};
    static boolean[] mixture_flag = {false, false, false, false, false, false, false, false};
    static int[] thick_int = {5, 15, 25, 35, 45};
    static boolean[] thick_flag = {false, false, false, false, false};
    static String[] color_list = {"Red", "Orange", "Yellow", "Green", "Turquoise", "Poolblue", "Blue", "Purple", "Black", "Gray", "Brown", "White"};
    static String[] color_value = {"#ED222D", "#F97529", "#EEB42C", "#70843E", "#009793", "#009793", "#004072", "#442858", "#000000", "#FFFFFF", "#744D23", "#FFFFFF"};
    static String[] mixture_string = {"C", "CM", "P", "R", "W", "A", "PA", "T"};
    static String[] mixture_string2 = {"c", "cm", "p", "r", "w", "a", "pa", "t"};
    static String[] density_string = {"127x86", "110x76", "96x64"};
    static String selected_color = "All";
    static int density_weight = 3;
    static int thick_weight = 0;
    static int price_min = 1, price_max = 16;
    static boolean[] price_flag = {false, false};
    public static LinearLayout result_holder;
    static ArrayList<CO_OB_Fiber> fiberArrayList;
    static TextView btn_hwasum, btn_jikgi, btn_nit, btn_daima, btn_nayum, btn_yinjo, btn_myeon, btn_nubim;
    static TextView btn_yangbok, btn_yangjang, btn_gwang, btn_span;
    static TextView btn_race, btn_silk, btn_wool, btn_alpha, btn_mopi, btn_digital, btn_tul, btn_etc;
    static TextView btn_c, btn_cm, btn_p, btn_r, btn_w, btn_a, btn_pa, btn_t;
    static TextView btn_5t, btn_15t, btn_25t, btn_35t, btn_45t;
    static TextView min_price_text, max_price_text, count_text;
    static CO_RangeSeekBar<Integer> seekBar, thick_seekbar;
    static Fragment item_fragment, add_fragment;
    static LinearLayout count_layout, value_holder;
    static FrameLayout colorChanger, darkChanger, btn_matching_close;
    static Drawable colorBackground;
    static SeekBar color_seekBar;
    static ImageView btn_type, btn_mixture, btn_color, btn_density, btn_thick, btn_price;
    static boolean[] values_changed = {false, false, false, false, false, false};
    static ImageView single_arrow, btn_matching, btn_matching_tab;

    NfcAdapter mNfcAdapter; // NFC 어댑터
    PendingIntent mPendingIntent; // 수신받은 데이터가 저장된 인텐트
    IntentFilter[] mIntentFilters; // 인텐트 필터
    String[][] mNFCTechLists;

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    float touchY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_ac_main);
        getWindow().setWindowAnimations(0);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // NFC 데이터 활성화에 필요한 인텐트를 생성
        Intent intent = new Intent(this, getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // NFC 데이터 활성화에 필요한 인텐트 필터를 생성
        IntentFilter iFilter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            iFilter.addDataType("*/*");
            mIntentFilters = new IntentFilter[] { iFilter };
        } catch (Exception e) {
        }

        mNFCTechLists = new String[][] { new String[] { NfcF.class.getName() } };

        activity = this;
        all_layout = (ViewGroup)findViewById(R.id.all_layout);
        pocket_sub_layout = (ViewGroup)findViewById(R.id.pocket_sub_layout);
        tagging_intro = (ImageView)findViewById(R.id.tagging_intro);
        dragging_holder = (ViewGroup)findViewById(R.id.dragging_holder);
        matching_info = (ViewGroup)findViewById(R.id.matching_info);
        matching_info_layout = (ViewGroup)findViewById(R.id.matching_info_layout);
        result_margin = (ViewGroup)findViewById(R.id.result_margin);

        type_area = (ViewGroup)findViewById(R.id.type_area);
        mixture_area = (ViewGroup)findViewById(R.id.mixture_area);
        color_area = (ViewGroup)findViewById(R.id.color_area);
        density_area = (ViewGroup)findViewById(R.id.density_area);
        thick_area = (ViewGroup)findViewById(R.id.thick_area);
        price_area = (ViewGroup)findViewById(R.id.price_area);

        result_holder = (LinearLayout)findViewById(R.id.result_holder);
        fiberArrayList = new ArrayList<CO_OB_Fiber>();
        item_layout = (ViewGroup)findViewById(R.id.item_layout);
        count_layout = (LinearLayout)findViewById(R.id.count_layout);

        type_area.setVisibility(View.GONE);
        mixture_area.setVisibility(View.GONE);
        color_area.setVisibility(View.GONE);
        density_area.setVisibility(View.GONE);
        thick_area.setVisibility(View.GONE);
        price_area.setVisibility(View.GONE);

        setted_value_holder = (ViewGroup)findViewById(R.id.setted_value_holder);
        matching_holder = (ViewGroup)findViewById(R.id.matching_holder);
        setted_value_holder.setVisibility(View.GONE);
        matching_holder.setVisibility(View.GONE);
        tagging_intro.setVisibility(View.VISIBLE);

        btn_hwasum = (TextView)findViewById(R.id.btn_hwasum);
        btn_jikgi = (TextView)findViewById(R.id.btn_jikgi);
        btn_nit = (TextView)findViewById(R.id.btn_nit);
        btn_daima = (TextView)findViewById(R.id.btn_daima);
        btn_nayum = (TextView)findViewById(R.id.btn_nayum);
        btn_yinjo = (TextView)findViewById(R.id.btn_yinjo);
        btn_myeon = (TextView)findViewById(R.id.btn_myeon);
        btn_nubim = (TextView)findViewById(R.id.btn_nubim);
        btn_yangbok = (TextView)findViewById(R.id.btn_yangbok);
        btn_yangjang = (TextView)findViewById(R.id.btn_yangjang);
        btn_gwang = (TextView)findViewById(R.id.btn_gwang);
        btn_span = (TextView)findViewById(R.id.btn_span);
        btn_c = (TextView)findViewById(R.id.btn_c);
        btn_cm = (TextView)findViewById(R.id.btn_cm);
        btn_p = (TextView)findViewById(R.id.btn_p);
        btn_r = (TextView)findViewById(R.id.btn_r);
        btn_w = (TextView)findViewById(R.id.btn_w);
        btn_a = (TextView)findViewById(R.id.btn_a);
        btn_pa = (TextView)findViewById(R.id.btn_pa);
        btn_t = (TextView)findViewById(R.id.btn_t);
        min_price_text = (TextView)findViewById(R.id.min_price);
        max_price_text = (TextView)findViewById(R.id.max_price);
        seekBar = new CO_RangeSeekBar<Integer>(1, 16, getBaseContext());
        thick_seekbar = new CO_RangeSeekBar<Integer>(0, 5, getBaseContext());
        count_text = (TextView)findViewById(R.id.count_text);
        count_frame = (ViewGroup)findViewById(R.id.count_frame);
        count_frame.setVisibility(View.GONE);
        colorChanger = (FrameLayout)findViewById(R.id.color_changer);
        darkChanger = (FrameLayout)findViewById(R.id.dark_changer);
        colorBackground = colorChanger.getBackground();
        value_holder = (LinearLayout)findViewById(R.id.value_holder);
        single_arrow = (ImageView)findViewById(R.id.arrow_image);
        btn_matching_close = (FrameLayout)findViewById(R.id.matching_tab_close);
        btn_matching = (ImageView)findViewById(R.id.btn_matching_tab);
        btn_matching_tab = (ImageView)findViewById(R.id.btn_matching_tab);
        btn_race = (TextView)findViewById(R.id.btn_race);
        btn_silk = (TextView)findViewById(R.id.btn_silk);
        btn_wool = (TextView)findViewById(R.id.btn_wool);
        btn_alpha = (TextView)findViewById(R.id.btn_alpha);
        btn_mopi = (TextView)findViewById(R.id.btn_mopi);
        btn_digital = (TextView)findViewById(R.id.btn_digital);
        btn_tul = (TextView)findViewById(R.id.btn_tul);
        btn_etc = (TextView)findViewById(R.id.btn_etc);
        btn_5t = (TextView)findViewById(R.id.btn_5t);
        btn_15t = (TextView)findViewById(R.id.btn_15t);
        btn_25t = (TextView)findViewById(R.id.btn_25t);
        btn_35t = (TextView)findViewById(R.id.btn_35t);
        btn_45t = (TextView)findViewById(R.id.btn_45t);

        value_holder.removeAllViews();

        for(int i=0;i<type_flag.length;i++)
            type_flag[i] = false;
        for(int i=0;i<mixture_flag.length;i++)
            mixture_flag[i] = false;
        for(int i=0;i<thick_flag.length;i++)
            thick_flag[i] = false;
        selected_color = "All";

        if(colorBackground instanceof GradientDrawable) {
            ((GradientDrawable) colorBackground).setColor(Color.parseColor("#666666"));
            darkChanger.setAlpha(0);
        }

        color_seekBar = (SeekBar)findViewById(R.id.color_seekbar);
        color_seekBar.setProgress(0);

        density_weight = 3;
        thick_weight = 0;
        price_min = 1;
        price_max = 16;
        seekBar.setSelectedMinValue(1);
        seekBar.setSelectedMaxValue(16);
        min_price_text.setText(String.format("%d,000원", price_min));
        max_price_text.setText(String.format("%d,000원", price_max));

        thick_seekbar.setSelectedMinValue(0);
        thick_seekbar.setSelectedMaxValue(5);

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            matching_fiber = CO_LO_DataCenter.getFiber(extra.getInt("fiberID"));
            matching_holder.setVisibility(View.VISIBLE);
            setted_value_holder.setVisibility(View.VISIBLE);
            tagging_intro.setVisibility(View.GONE);

            initialize_fiber_setting();
        }

        ImageButton btn_remove_all = (ImageButton)findViewById(R.id.delete_all);
        btn_remove_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value_holder.removeAllViews();

                for (int i = 0; i < type_flag.length; i++)
                    type_flag[i] = false;
                for (int i = 0; i < mixture_flag.length; i++)
                    mixture_flag[i] = false;
                for (int i = 0; i < thick_flag.length; i++)
                    thick_flag[i] = false;
                selected_color = "All";

                if (colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#666666"));
                    darkChanger.setAlpha(0);
                }

                color_seekBar = (SeekBar) findViewById(R.id.color_seekbar);
                color_seekBar.setProgress(0);

                density_weight = 3;
                thick_weight = 0;
                price_min = 1;
                price_max = 16;
                seekBar.setSelectedMinValue(1);
                seekBar.setSelectedMaxValue(16);
                min_price_text.setText(String.format("%d,000원", price_min));
                max_price_text.setText(String.format("%d,000원", price_max));

                thick_seekbar.setSelectedMinValue(0);
                thick_seekbar.setSelectedMaxValue(5);

                refresh_search_result();
                refresh_boolean();
            }
        });

        ImageButton btn_board = (ImageButton)findViewById(R.id.btn_menu_board);
        btn_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_fiber = null;
                Intent intent = new Intent(v.getContext(), BO_AC_Main.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton btn_mine = (ImageButton)findViewById(R.id.btn_menu_mine);
        btn_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_fiber = null;
                Intent intent = new Intent(v.getContext(), MI_AC_Main.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton btn_DDM = (ImageButton)findViewById(R.id.btn_menu_ddm);
        btn_DDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matching_fiber = null;
                Intent intent = new Intent(v.getContext(), DC_AC_Main.class);
                startActivity(intent);
                finish();
            }
        });

        swatch_matching = (RelativeLayout)findViewById(R.id.swatch_matching);
        swatch_matching.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (tagging_intro.getVisibility() == View.VISIBLE) {
                            if (touchY + 20 > event.getY()) {
                                matching_holder.setVisibility(View.VISIBLE);
                                setted_value_holder.setVisibility(View.VISIBLE);
                                type_area.setVisibility(View.VISIBLE);
                                result_margin.setVisibility(View.VISIBLE);
                                btn_type.setImageResource(R.drawable.u_type_on);
                                single_arrow.setImageResource(R.drawable.single_arrow_down);
                                btn_matching_tab.setImageResource(R.drawable.searching_arrow_up);
                                tagging_intro.setVisibility(View.GONE);
                            }
                        } else {
                            if (touchY - 20 < event.getY()) {
                                tagging_intro.setVisibility(View.VISIBLE);
                                matching_holder.setVisibility(View.GONE);
                                setted_value_holder.setVisibility(View.GONE);
                                type_area.setVisibility(View.GONE);
                                result_margin.setVisibility(View.GONE);
                                single_arrow.setImageResource(R.drawable.single_arrow_up);
                            }
                        }
                        break;
                }
                return true;
            }
        });
        /*swatch_matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tagging_intro.getVisibility() == View.GONE) {
                    tagging_intro.setVisibility(View.VISIBLE);
                    matching_holder.setVisibility(View.GONE);
                    setted_value_holder.setVisibility(View.GONE);
                    type_area.setVisibility(View.GONE);
                    result_margin.setVisibility(View.GONE);
                    single_arrow.setImageResource(R.drawable.single_arrow_up);
                } else {
                    tagging_intro.setVisibility(View.GONE);
                    matching_holder.setVisibility(View.VISIBLE);
                    setted_value_holder.setVisibility(View.VISIBLE);
                    type_area.setVisibility(View.VISIBLE);
                    result_margin.setVisibility(View.VISIBLE);
                    btn_type.setImageResource(R.drawable.u_type_on);
                    single_arrow.setImageResource(R.drawable.single_arrow_down);
                }
            }
        });*/

        matching_info_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tagging_intro.getVisibility() == View.GONE) {
                    tagging_intro.setVisibility(View.VISIBLE);
                } else {
                    tagging_intro.setVisibility(View.GONE);
                }
            }
        });

        btn_hwasum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[0]) {
                    type_flag[0] = false;
                    btn_hwasum.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("hwasum")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[0] = true;
                    btn_hwasum.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("화섬");
                    tagView.setTag("hwasum");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[0] = false;
                            btn_hwasum.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_jikgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[1]) {
                    type_flag[1] = false;
                    btn_jikgi.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("jikgi")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[1] = true;
                    btn_jikgi.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("직기");
                    tagView.setTag("jikgi");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[1] = false;
                            btn_jikgi.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_nit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[2]) {
                    type_flag[2] = false;
                    btn_nit.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("nit")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[2] = true;
                    btn_nit.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("니트");
                    tagView.setTag("nit");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[2] = false;
                            btn_nit.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_daima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[3]) {
                    type_flag[3] = false;
                    btn_daima.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("daima")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[3] = true;
                    btn_daima.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("다이마루");
                    tagView.setTag("daima");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[3] = false;
                            btn_daima.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_nayum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[4]) {
                    type_flag[4] = false;
                    btn_nayum.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("nayum")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[4] = true;
                    btn_nayum.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("나염");
                    tagView.setTag("nayum");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[4] = false;
                            btn_nayum.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_yinjo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[5]) {
                    type_flag[5] = false;
                    btn_yinjo.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("yinjo")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[5] = true;
                    btn_yinjo.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("인조피혁");
                    tagView.setTag("yinjo");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[5] = false;
                            btn_yinjo.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_myeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[6]) {
                    type_flag[6] = false;
                    btn_myeon.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("myeon")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[6] = true;
                    btn_myeon.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("면");
                    tagView.setTag("myeon");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[6] = false;
                            btn_myeon.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_nubim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[7]) {
                    type_flag[7] = false;
                    btn_nubim.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("nubim")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[7] = true;
                    btn_nubim.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("누빔");
                    tagView.setTag("nubim");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[7] = false;
                            btn_nubim.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_yangbok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[8]) {
                    type_flag[8] = false;
                    btn_yangbok.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("yangbok")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[8] = true;
                    btn_yangbok.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("양복지");
                    tagView.setTag("yangbok");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[8] = false;
                            btn_yangbok.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_yangjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[9]) {
                    type_flag[9] = false;
                    btn_yangjang.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("yangjang")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[9] = true;
                    btn_yangjang.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("양장지");
                    tagView.setTag("yangjang");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[9] = false;
                            btn_yangjang.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_gwang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[10]) {
                    type_flag[10] = false;
                    btn_gwang.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("gwang")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[10] = true;
                    btn_gwang.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("광목");
                    tagView.setTag("gwang");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[10] = false;
                            btn_gwang.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_span.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[11]) {
                    type_flag[11] = false;
                    btn_span.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("span")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[11] = true;
                    btn_span.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("스판");
                    tagView.setTag("span");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[11] = false;
                            btn_span.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[12]) {
                    type_flag[12] = false;
                    btn_race.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("race")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[12] = true;
                    btn_race.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("레이스");
                    tagView.setTag("race");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[12] = false;
                            btn_race.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_silk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[13]) {
                    type_flag[13] = false;
                    btn_silk.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("silk")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[13] = true;
                    btn_silk.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("실크");
                    tagView.setTag("silk");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[13] = false;
                            btn_silk.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_wool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[14]) {
                    type_flag[14] = false;
                    btn_wool.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("wool")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[14] = true;
                    btn_wool.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("울");
                    tagView.setTag("wool");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[14] = false;
                            btn_wool.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[15]) {
                    type_flag[15] = false;
                    btn_alpha.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("alpha")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[15] = true;
                    btn_alpha.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("알파카");
                    tagView.setTag("alpha");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[15] = false;
                            btn_alpha.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_mopi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[16]) {
                    type_flag[16] = false;
                    btn_mopi.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("mopi")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[16] = true;
                    btn_mopi.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("인조모피");
                    tagView.setTag("mopi");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[16] = false;
                            btn_mopi.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_digital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[17]) {
                    type_flag[17] = false;
                    btn_digital.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("digital")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[17] = true;
                    btn_digital.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("디지털프린트");
                    tagView.setTag("digital");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[17] = false;
                            btn_digital.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_tul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type_flag[18]) {
                    type_flag[18] = false;
                    btn_tul.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("tul")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[18] = true;
                    btn_tul.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("털");
                    tagView.setTag("tul");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[18] = false;
                            btn_tul.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type_flag[19]) {
                    type_flag[19] = false;
                    btn_etc.setBackgroundColor(Color.TRANSPARENT);

                    for (int i = 0; i < value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if (view.getTag().equals("etc")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    type_flag[19] = true;
                    btn_etc.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView) tagView.findViewById(R.id.tag_string);
                    textView.setText("기타");
                    tagView.setTag("etc");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            type_flag[19] = false;
                            btn_etc.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mixture_flag[0]) {
                    mixture_flag[0] = false;
                    btn_c.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("c")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    mixture_flag[0] = true;
                    btn_c.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("C");
                    tagView.setTag("c");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            mixture_flag[0] = false;
                            btn_c.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_cm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mixture_flag[1]) {
                    mixture_flag[1] = false;
                    btn_cm.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("cm")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    mixture_flag[1] = true;
                    btn_cm.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("CM");
                    tagView.setTag("cm");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            mixture_flag[1] = false;
                            btn_cm.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mixture_flag[2]) {
                    mixture_flag[2] = false;
                    btn_p.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("p")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    mixture_flag[2] = true;
                    btn_p.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("P");
                    tagView.setTag("p");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            mixture_flag[2] = false;
                            btn_p.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mixture_flag[3]) {
                    mixture_flag[3] = false;
                    btn_r.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("r")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    mixture_flag[3] = true;
                    btn_r.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("R");
                    tagView.setTag("r");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            mixture_flag[3] = false;
                            btn_r.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mixture_flag[4]) {
                    mixture_flag[4] = false;
                    btn_w.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("w")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    mixture_flag[4] = true;
                    btn_w.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("W");
                    tagView.setTag("w");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            mixture_flag[4] = false;
                            btn_w.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mixture_flag[5]) {
                    mixture_flag[5] = false;
                    btn_a.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("a")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    mixture_flag[5] = true;
                    btn_a.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("A");
                    tagView.setTag("a");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            mixture_flag[5] = false;
                            btn_a.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mixture_flag[6]) {
                    mixture_flag[6] = false;
                    btn_pa.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("pa")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    mixture_flag[6] = true;
                    btn_pa.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("PA");
                    tagView.setTag("pa");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            mixture_flag[6] = false;
                            btn_pa.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mixture_flag[7]) {
                    mixture_flag[7] = false;
                    btn_t.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("t")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    mixture_flag[7] = true;
                    btn_t.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("T");
                    tagView.setTag("t");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            mixture_flag[7] = false;
                            btn_t.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_1 = (FrameLayout)findViewById(R.id.color_1);
        color_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#ED222D"));

                    selected_color = "Red";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#ED222D"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_2 = (FrameLayout)findViewById(R.id.color_2);
        color_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#F97529"));

                    selected_color = "Orange";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#F97529"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_3 = (FrameLayout)findViewById(R.id.color_3);
        color_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#EEB42C"));

                    selected_color = "Yellow";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#EEB42C"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_4 = (FrameLayout)findViewById(R.id.color_4);
        color_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#70843E"));

                    selected_color = "Green";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#70843E"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_5 = (FrameLayout)findViewById(R.id.color_5);
        color_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#009793"));

                    selected_color = "Poolblue";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#009793"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_6 = (FrameLayout)findViewById(R.id.color_6);
        color_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#004072"));

                    selected_color = "Blue";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#004072"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_7 = (FrameLayout)findViewById(R.id.color_7);
        color_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#442858"));

                    selected_color = "Purple";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#442858"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_8 = (FrameLayout)findViewById(R.id.color_8);
        color_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#744D23"));

                    selected_color = "Brown";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#744D23"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_9 = (FrameLayout)findViewById(R.id.color_9);
        color_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#FFFFFF"));

                    selected_color = "White";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        FrameLayout color_10 = (FrameLayout)findViewById(R.id.color_10);
        color_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(colorBackground instanceof GradientDrawable) {
                    ((GradientDrawable) colorBackground).setColor(Color.parseColor("#000000"));

                    selected_color = "Black";
                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("color")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("색상");
                    textView.setTextColor(Color.parseColor("#000000"));
                    tagView.setTag("color");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            selected_color = "All";
                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        final ImageView density_image = (ImageView)findViewById(R.id.density_image);
        final Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.density);
        SeekBar densitySeekBar = (SeekBar)findViewById(R.id.density_seekbar);
        if(density_weight == 3)
            densitySeekBar.setProgress(0);
        else
            densitySeekBar.setProgress(density_weight * 4);
        int init_height = 430 - (9 - densitySeekBar.getProgress()) * 20;
        density_image.setImageBitmap(Bitmap.createScaledBitmap(bm, init_height * 3, init_height, true));
        densitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int height = 430 - (9 - progress) * 20;
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(bm, height * 3, height, true);

                switch (progress) {
                    case 0:
                    case 1:
                    case 2:
                        density_weight = 0;
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        density_weight = 1;
                        break;
                    case 7:
                    case 8:
                    case 9:
                        density_weight = 2;
                        break;
                }

                density_image.setImageBitmap(resizedBitmap);

                for (int i = 0; i < value_holder.getChildCount(); i++) {
                    View view = value_holder.getChildAt(i);
                    if (view.getTag().equals("density")) {
                        value_holder.removeView(view);
                        break;
                    }
                }

                final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                TextView textView = (TextView) tagView.findViewById(R.id.tag_string);
                switch (density_weight) {
                    case 0:
                        textView.setText("127x86");
                        break;
                    case 1:
                        textView.setText("110x76");
                        break;
                    case 2:
                        textView.setText("96x64");
                        break;
                }
                tagView.setTag("density");
                tagView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        value_holder.removeView(tagView);
                        density_weight = 3;
                        refresh_search_result();
                    }
                });

                value_holder.addView(tagView);

                refresh_search_result();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar.setOnRangeSeekBarChangeListener(new CO_RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(CO_RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                Log.i("RSB", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
                min_price_text.setText(String.format("%d,000원", minValue));
                max_price_text.setText(String.format("%d,000원", maxValue));

                price_min = minValue;
                price_max = maxValue;

                refresh_search_result();
            }
        });
        FrameLayout seekBarLayer = (FrameLayout)findViewById(R.id.range_seek_bar);
        seekBarLayer.addView(seekBar);

/*        thick_seekbar.setOnRangeSeekBarChangeListener(new CO_RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(CO_RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {

            }
        });
        FrameLayout thick_frame = (FrameLayout)findViewById(R.id.thick_seekbar);
        thick_frame.addView(thick_seekbar);*/
        btn_5t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thick_flag[0]) {
                    thick_flag[0] = false;
                    btn_5t.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("5t")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    thick_flag[0] = true;
                    btn_5t.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("5수");
                    tagView.setTag("5t");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            thick_flag[0] = false;
                            btn_5t.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_15t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thick_flag[1]) {
                    thick_flag[1] = false;
                    btn_15t.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("15t")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    thick_flag[1] = true;
                    btn_15t.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("15수");
                    tagView.setTag("15t");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            thick_flag[1] = false;
                            btn_15t.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_25t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thick_flag[2]) {
                    thick_flag[2] = false;
                    btn_25t.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("25t")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    thick_flag[2] = true;
                    btn_25t.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("25수");
                    tagView.setTag("25t");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            thick_flag[2] = false;
                            btn_25t.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_35t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thick_flag[3]) {
                    thick_flag[3] = false;
                    btn_35t.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("35t")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    thick_flag[3] = true;
                    btn_35t.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("35수");
                    tagView.setTag("35t");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            thick_flag[3] = false;
                            btn_35t.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        btn_45t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thick_flag[4]) {
                    thick_flag[4] = false;
                    btn_45t.setBackgroundColor(Color.TRANSPARENT);

                    for(int i=0; i<value_holder.getChildCount(); i++) {
                        View view = value_holder.getChildAt(i);
                        if(view.getTag().equals("45t")) {
                            value_holder.removeView(view);
                            break;
                        }
                    }
                } else {
                    thick_flag[4] = true;
                    btn_45t.setBackgroundColor(Color.parseColor("#1F9A95"));

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText("45수");
                    tagView.setTag("45t");
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);
                            thick_flag[4] = false;
                            btn_45t.setBackgroundColor(Color.TRANSPARENT);

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }

                refresh_search_result();
            }
        });

        final ImageButton btn_need = (ImageButton)findViewById(R.id.btn_need);
        btn_need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (price_flag[0]) {
                    price_flag[0] = false;
                    btn_need.setImageResource(R.drawable.price_off);
                } else {
                    price_flag[0] = true;
                    btn_need.setImageResource(R.drawable.price_on);
                }

                refresh_search_result();
            }
        });

        final ImageButton btn_possible = (ImageButton)findViewById(R.id.btn_possible);
        btn_possible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (price_flag[1]) {
                    price_flag[1] = false;
                    btn_possible.setImageResource(R.drawable.price_off);
                } else {
                    price_flag[1] = true;
                    btn_possible.setImageResource(R.drawable.price_on);
                }

                refresh_search_result();
            }
        });

        matching_layout = (ViewGroup)findViewById(R.id.matching_layout);
        btn_matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        pocket_layout = (ViewGroup)findViewById(R.id.pocket_layout);
        ImageButton btn_pocket_all = (ImageButton)findViewById(R.id.btn_pocket_all);
        btn_pocket_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                getFragmentManager().popBackStack();
                pocket_fragment = getFragmentManager().findFragmentById(R.id.pocket_layout);

                if (pocket_fragment == null) {
                    ViewGroup.LayoutParams layoutParams = pocket_layout.getLayoutParams();
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.animator.slide_up,
                                    R.animator.slide_down,
                                    R.animator.slide_up,
                                    R.animator.slide_down)
                            .add(R.id.pocket_layout, new BO_FR_PocketAll())
                            .addToBackStack(null)
                            .commit();
                } else {
                    getFragmentManager().popBackStack();
                }
            }
        });

        add_layout = (ViewGroup)findViewById(R.id.add_layout);
        ImageButton btn_pocket_add = (ImageButton)findViewById(R.id.btn_pocket_add);
        btn_pocket_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                getFragmentManager().popBackStack();
                add_fragment = getFragmentManager().findFragmentById(R.id.add_layout);

                if (add_fragment == null) {
                    ViewGroup.LayoutParams layoutParams = add_layout.getLayoutParams();
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.animator.slide_up,
                                    R.animator.slide_down,
                                    R.animator.slide_up,
                                    R.animator.slide_down)
                            .add(R.id.add_layout, new BO_FR_PocketAdd())
                            .addToBackStack(null)
                            .commit();
                } else {
                    getFragmentManager().popBackStack();
                }
            }
        });

        //ImageView btn_mini_pocket = (ImageView)findViewById(R.id.mini_pocket);
        FrameLayout btn_mini_pocket = (FrameLayout)findViewById(R.id.mini_pocket_touch);
        btn_mini_pocket.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(BO_AC_Main.currentPocket != null) {
                            if (touchY + 20 > event.getY()) {
                                getFragmentManager().popBackStack();
                                pocket_sub_fragment = getFragmentManager().findFragmentById(R.id.pocket_sub_layout);

                                if (pocket_sub_fragment == null) {
                                    ViewGroup.LayoutParams layoutParams = pocket_sub_layout.getLayoutParams();
                                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                                    Bundle bundle = new Bundle();
                                    bundle.putInt("id", BO_AC_Main.currentPocket.getPocketID());
                                    SE_FR_PocketDetail detail = new SE_FR_PocketDetail();
                                    detail.setArguments(bundle);

                                    getFragmentManager().beginTransaction()
                                            .setCustomAnimations(R.animator.slide_up_half,
                                                    R.animator.slide_down_half,
                                                    R.animator.slide_up_half,
                                                    R.animator.slide_down_half)
                                            .add(R.id.pocket_sub_layout, detail)
                                            .addToBackStack(null)
                                            .commit();
                                } else {
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }
                        break;
                }

                return true;
            }
        });

        btn_type = (ImageView)findViewById(R.id.btn_type);
        btn_mixture = (ImageView)findViewById(R.id.btn_mixture);
        btn_color = (ImageView)findViewById(R.id.btn_color);
        btn_density = (ImageView)findViewById(R.id.btn_density);
        btn_thick = (ImageView)findViewById(R.id.btn_thick);
        btn_price = (ImageView)findViewById(R.id.btn_price);

        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_type.setImageResource(R.drawable.u_type_on);
                btn_mixture.setImageResource(R.drawable.u_mixture_off);
                btn_color.setImageResource(R.drawable.u_color_off);
                btn_density.setImageResource(R.drawable.u_density_off);
                btn_thick.setImageResource(R.drawable.u_thick_off);
                btn_price.setImageResource(R.drawable.u_price_off);

                type_area.setVisibility(View.VISIBLE);
                mixture_area.setVisibility(View.GONE);
                color_area.setVisibility(View.GONE);
                density_area.setVisibility(View.GONE);
                thick_area.setVisibility(View.GONE);
                price_area.setVisibility(View.GONE);

                btn_matching_tab.setImageResource(R.drawable.searching_arrow_up);
                result_margin.setVisibility(View.VISIBLE);
                count_layout.setPadding(0, 450, 0, 0);
                buttonImageChanger(0);
            }
        });

        btn_mixture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_type.setImageResource(R.drawable.u_type_off);
                btn_mixture.setImageResource(R.drawable.u_mixture_on);
                btn_color.setImageResource(R.drawable.u_color_off);
                btn_density.setImageResource(R.drawable.u_density_off);
                btn_thick.setImageResource(R.drawable.u_thick_off);
                btn_price.setImageResource(R.drawable.u_price_off);

                type_area.setVisibility(View.GONE);
                mixture_area.setVisibility(View.VISIBLE);
                color_area.setVisibility(View.GONE);
                density_area.setVisibility(View.GONE);
                thick_area.setVisibility(View.GONE);
                price_area.setVisibility(View.GONE);

                btn_matching_tab.setImageResource(R.drawable.searching_arrow_up);
                result_margin.setVisibility(View.VISIBLE);
                count_layout.setPadding(0, 450, 0, 0);
                buttonImageChanger(1);
            }
        });

        btn_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_type.setImageResource(R.drawable.u_type_off);
                btn_mixture.setImageResource(R.drawable.u_mixture_off);
                btn_color.setImageResource(R.drawable.u_color_on);
                btn_density.setImageResource(R.drawable.u_density_off);
                btn_thick.setImageResource(R.drawable.u_thick_off);
                btn_price.setImageResource(R.drawable.u_price_off);

                type_area.setVisibility(View.GONE);
                mixture_area.setVisibility(View.GONE);
                color_area.setVisibility(View.VISIBLE);
                density_area.setVisibility(View.GONE);
                thick_area.setVisibility(View.GONE);
                price_area.setVisibility(View.GONE);

                btn_matching_tab.setImageResource(R.drawable.searching_arrow_up);
                result_margin.setVisibility(View.VISIBLE);
                count_layout.setPadding(0, 450, 0, 0);

                color_seekBar.setMax(10);
                color_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float value = (float) progress / 20;
                        System.out.println(value);
                        darkChanger.setAlpha(value);

                        if(selected_color.equals("Turquoise") || selected_color.equals("Poolblue")) {
                            if(progress < 4) {
                                selected_color = "Poolblue";
                            } else {
                                selected_color = "Turquoise";
                            }
                        } else if(selected_color.equals("White") || selected_color.equals("Gray")) {
                            if(progress < 4) {
                                selected_color = "White";
                            } else {
                                selected_color = "Gray";
                            }
                        }

                        refresh_search_result();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
                buttonImageChanger(2);
            }
        });

        btn_density.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_type.setImageResource(R.drawable.u_type_off);
                btn_mixture.setImageResource(R.drawable.u_mixture_off);
                btn_color.setImageResource(R.drawable.u_color_off);
                btn_density.setImageResource(R.drawable.u_density_on);
                btn_thick.setImageResource(R.drawable.u_thick_off);
                btn_price.setImageResource(R.drawable.u_price_off);

                type_area.setVisibility(View.GONE);
                mixture_area.setVisibility(View.GONE);
                color_area.setVisibility(View.GONE);
                density_area.setVisibility(View.VISIBLE);
                thick_area.setVisibility(View.GONE);
                price_area.setVisibility(View.GONE);

                btn_matching_tab.setImageResource(R.drawable.searching_arrow_up);
                result_margin.setVisibility(View.VISIBLE);
                count_layout.setPadding(0, 450, 0, 0);
                buttonImageChanger(3);
            }
        });

        btn_thick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_type.setImageResource(R.drawable.u_type_off);
                btn_mixture.setImageResource(R.drawable.u_mixture_off);
                btn_color.setImageResource(R.drawable.u_color_off);
                btn_density.setImageResource(R.drawable.u_density_off);
                btn_thick.setImageResource(R.drawable.u_thick_on);
                btn_price.setImageResource(R.drawable.u_price_off);

                type_area.setVisibility(View.GONE);
                mixture_area.setVisibility(View.GONE);
                color_area.setVisibility(View.GONE);
                density_area.setVisibility(View.GONE);
                thick_area.setVisibility(View.VISIBLE);
                price_area.setVisibility(View.GONE);

                btn_matching_tab.setImageResource(R.drawable.searching_arrow_up);
                result_margin.setVisibility(View.VISIBLE);
                count_layout.setPadding(0, 450, 0, 0);
                buttonImageChanger(4);
            }
        });

        btn_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_type.setImageResource(R.drawable.u_type_off);
                btn_mixture.setImageResource(R.drawable.u_mixture_off);
                btn_color.setImageResource(R.drawable.u_color_off);
                btn_density.setImageResource(R.drawable.u_density_off);
                btn_thick.setImageResource(R.drawable.u_thick_off);
                btn_price.setImageResource(R.drawable.u_price_on);

                type_area.setVisibility(View.GONE);
                mixture_area.setVisibility(View.GONE);
                color_area.setVisibility(View.GONE);
                density_area.setVisibility(View.GONE);
                thick_area.setVisibility(View.GONE);
                price_area.setVisibility(View.VISIBLE);

                btn_matching_tab.setImageResource(R.drawable.searching_arrow_up);
                result_margin.setVisibility(View.VISIBLE);
                count_layout.setPadding(0, 450, 0, 0);
                buttonImageChanger(5);
            }
        });

        btn_matching_close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (touchY + 20 > event.getY()) {
                            btn_type.setImageResource(R.drawable.u_type_off);
                            btn_mixture.setImageResource(R.drawable.u_mixture_off);
                            btn_color.setImageResource(R.drawable.u_color_off);
                            btn_density.setImageResource(R.drawable.u_density_off);
                            btn_thick.setImageResource(R.drawable.u_thick_off);
                            btn_price.setImageResource(R.drawable.u_price_off);

                            type_area.setVisibility(View.GONE);
                            mixture_area.setVisibility(View.GONE);
                            color_area.setVisibility(View.GONE);
                            density_area.setVisibility(View.GONE);
                            thick_area.setVisibility(View.GONE);
                            price_area.setVisibility(View.GONE);

                            btn_matching_tab.setImageResource(R.drawable.searching_arrow_down);
                            result_margin.setVisibility(View.GONE);
                            count_layout.setPadding(0, 200, 0, 0);

                            buttonImageChanger(9);
                        }
                        break;
                }

                return true;
            }
        });

        matching_holder.setVisibility(View.VISIBLE);
        setted_value_holder.setVisibility(View.VISIBLE);
        type_area.setVisibility(View.VISIBLE);
        result_margin.setVisibility(View.VISIBLE);
        btn_type.setImageResource(R.drawable.u_type_on);
        single_arrow.setImageResource(R.drawable.single_arrow_down);
        btn_matching_tab.setImageResource(R.drawable.searching_arrow_up);
        tagging_intro.setVisibility(View.GONE);

        current_name = (TextView) activity.findViewById(R.id.current_pocket_name);

        refresh_boolean();
        refresh_layout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_se_ac_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
            {
                super.onBackPressed();
                return;
            }
            else { Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show(); }

            mBackPressed = System.currentTimeMillis();
        }
    }

    public static void initialize_fiber_setting() {
        value_holder.removeAllViews();

        for(int i=0;i<type_flag.length;i++)
            type_flag[i] = false;
        for(int i=0;i<mixture_flag.length;i++)
            mixture_flag[i] = false;
        for(int i=0;i<thick_flag.length;i++)
            thick_flag[i] = false;

        for(int i=0;i<type_string.length;i++) {
            if(type_string[i].equals(matching_fiber.getFiberType())) {
                type_flag[i] = true;

                final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                textView.setText(type_string[i]);
                tagView.setTag(type_string2[i]);
                tagView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        value_holder.removeView(tagView);

                        if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("화섬")) {
                            type_flag[0] = false;
                            btn_hwasum.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("직기")) {
                            type_flag[1] = false;
                            btn_jikgi.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("니트")) {
                            type_flag[2] = false;
                            btn_nit.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("다이마루")) {
                            type_flag[3] = false;
                            btn_daima.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("나염")) {
                            type_flag[4] = false;
                            btn_nayum.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("인조피혁")) {
                            type_flag[5] = false;
                            btn_yinjo.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("면")) {
                            type_flag[6] = false;
                            btn_myeon.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("누빔")) {
                            type_flag[7] = false;
                            btn_nubim.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("양복지")) {
                            type_flag[8] = false;
                            btn_yangbok.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("양장지")) {
                            type_flag[9] = false;
                            btn_yangjang.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("광목")) {
                            type_flag[10] = false;
                            btn_gwang.setBackgroundColor(Color.TRANSPARENT);
                        } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("스판")) {
                            type_flag[11] = false;
                            btn_span.setBackgroundColor(Color.TRANSPARENT);
                        }

                        refresh_search_result();
                    }
                });

                value_holder.addView(tagView);
            }
        }

        StringTokenizer st = new StringTokenizer(matching_fiber.getFiberMixture(), "/");
        ArrayList<String> mixtures = new ArrayList<String>();
        while(st.hasMoreTokens()) {
            String token = st.nextToken();
            mixtures.add(token.replaceAll("[0-9]", ""));
        }
        for(int i=0; i<mixture_flag.length; i++) {
            for(String temp : mixtures) {
                if(temp.equals(mixture_string[i])) {
                    mixture_flag[i] = true;

                    final View tagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                    TextView textView = (TextView)tagView.findViewById(R.id.tag_string);
                    textView.setText(mixture_string[i]);
                    tagView.setTag(mixture_string2[i]);
                    tagView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            value_holder.removeView(tagView);

                            if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("C")) {
                                mixture_flag[0] = false;
                                btn_c.setBackgroundColor(Color.TRANSPARENT);
                            } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("CM")) {
                                mixture_flag[1] = false;
                                btn_cm.setBackgroundColor(Color.TRANSPARENT);
                            } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("P")) {
                                mixture_flag[2] = false;
                                btn_p.setBackgroundColor(Color.TRANSPARENT);
                            } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("R")) {
                                mixture_flag[3] = false;
                                btn_r.setBackgroundColor(Color.TRANSPARENT);
                            } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("W")) {
                                mixture_flag[4] = false;
                                btn_w.setBackgroundColor(Color.TRANSPARENT);
                            } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("A")) {
                                mixture_flag[5] = false;
                                btn_a.setBackgroundColor(Color.TRANSPARENT);
                            } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("PA")) {
                                mixture_flag[6] = false;
                                btn_pa.setBackgroundColor(Color.TRANSPARENT);
                            } else if(((TextView) tagView.findViewById(R.id.tag_string)).getText().equals("T")) {
                                mixture_flag[7] = false;
                                btn_t.setBackgroundColor(Color.TRANSPARENT);
                            }

                            refresh_search_result();
                        }
                    });

                    value_holder.addView(tagView);
                }
            }
        }

        selected_color = matching_fiber.getFiberColor();
        for(int i=0;i<color_list.length;i++) {
            if(color_list[i].equals(matching_fiber.getFiberColor())) {
                ((GradientDrawable) colorBackground).setColor(Color.parseColor(color_value[i]));

                if(selected_color.equals("Turquoise") || selected_color.equals("Gray")) {
                    color_seekBar.setProgress(7);
                    darkChanger.setAlpha(7/20);
                }

                final View colorTagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
                TextView textView = (TextView)colorTagView.findViewById(R.id.tag_string);
                textView.setText("색상");
                textView.setTextColor(Color.parseColor(color_value[i]));
                colorTagView.setTag("color");
                colorTagView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        value_holder.removeView(colorTagView);
                        selected_color = "All";
                        refresh_search_result();
                    }
                });

                value_holder.addView(colorTagView);
            }
        }

        if(matching_fiber.getFiberDensity().equals(density_string[0]))
            density_weight = 0;
        else if(matching_fiber.getFiberDensity().equals(density_string[1]))
            density_weight = 1;
        else
            density_weight = 2;

        switch(matching_fiber.getFiberThick()) {
            case 5:
                thick_flag[0] = true;
                break;
            case 15:
                thick_flag[1] = true;
                break;
            case 25:
                thick_flag[2] = true;
                break;
            case 35:
                thick_flag[3] = true;
                break;
            case 45:
                thick_flag[4] = true;
                break;
            default:
                break;
        }

        final View densityTagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
        TextView densityTextView = (TextView)densityTagView.findViewById(R.id.tag_string);
        switch(density_weight) {
            case 0:
                densityTextView.setText("127x86");
                break;
            case 1:
                densityTextView.setText("110x76");
                break;
            case 2:
                densityTextView.setText("96x64");
                break;
            default:
                break;
        }
        densityTagView.setTag("density");
        densityTagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value_holder.removeView(densityTagView);
                density_weight = 3;
                refresh_search_result();
            }
        });
        value_holder.addView(densityTagView);

        thick_weight = matching_fiber.getFiberThick();
        final View thickTagView = activity.getLayoutInflater().inflate(R.layout.custom_adapter_selected2, null);
        TextView thickTextView = (TextView)thickTagView.findViewById(R.id.tag_string);
        thickTextView.setText(String.format("%d수", thick_weight));
        thickTagView.setTag("thick");
        thickTagView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value_holder.removeView(thickTagView);
                refresh_search_result();
            }
        });
        value_holder.addView(thickTagView);

        price_min = 1;
        price_max = 16;
        seekBar.setSelectedMinValue(1);
        seekBar.setSelectedMaxValue(16);
        min_price_text.setText(String.format("%d,000원", price_min));
        max_price_text.setText(String.format("%d,000원", price_max));

        thick_seekbar.setSelectedMinValue(0);
        thick_seekbar.setSelectedMaxValue(5);

        refresh_boolean();
        refresh_search_result();
    }

    public static void refresh_layout() {
        if(BO_AC_Main.currentPocket == null) {
            current_name.setText("새 포켓을 만드세요");
        } else {
            current_name.setText(BO_AC_Main.currentPocket.getPocketName());
            TextView current_count = (TextView) activity.findViewById(R.id.current_pocket_count);
            current_count.setText(String.valueOf(CO_LO_DataCenter.getPocketContainer(String.valueOf(BO_AC_Main.currentPocket.getPocketID())).size()));
        }

        if(matching_fiber != null) {
            swatch_matching.setVisibility(View.GONE);
            matching_info.setVisibility(View.VISIBLE);
            setted_value_holder.setVisibility(View.VISIBLE);
            matching_holder.setVisibility(View.VISIBLE);

            TextView name_text = (TextView)activity.findViewById(R.id.name_text);
            name_text.setText(matching_fiber.getFiberName());

            TextView type_text = (TextView)activity.findViewById(R.id.type_text);
            type_text.setText(matching_fiber.getFiberType());

            TextView mixture_text = (TextView)activity.findViewById(R.id.mixture_text);
            mixture_text.setText(matching_fiber.getFiberMixture());

            TextView density_text = (TextView)activity.findViewById(R.id.density_text);
            density_text.setText(matching_fiber.getFiberDensity());

            TextView thick_text = (TextView)activity.findViewById(R.id.thick_text);
            thick_text.setText(String.format("%d수", matching_fiber.getFiberThick()));

            TextView price_text = (TextView)activity.findViewById(R.id.price_text);
            price_text.setText(String.format("%s원/YD", NumberFormat.getNumberInstance(Locale.US).format(matching_fiber.getFiberPrice())));
        } else {
            matching_info.setVisibility(View.GONE);
        }

        all_layout.requestLayout();
    }

    public static void getMatchingResult() {
        fiberArrayList.clear();
        fiberArrayList.addAll(CO_LO_DataCenter.getAllFibers());

        boolean true_flag = false;
        for(int i=0;i<type_flag.length;i++) {
            System.out.println(type_flag[i]);
            if(type_flag[i]) {
                true_flag = true;
            }
        }

        if(true_flag) {
            for (int i = 0; i < type_flag.length; i++) {
                if (!type_flag[i]) {
                    for (Iterator<CO_OB_Fiber> iterator = fiberArrayList.iterator(); iterator.hasNext(); ) {
                        CO_OB_Fiber fiber = iterator.next();

                        if (fiber.getFiberType().equals(type_string[i])) {
                            iterator.remove();
                        }
                    }
                }
            }
        }

        for(Iterator<CO_OB_Fiber> iterator = fiberArrayList.iterator(); iterator.hasNext();) {
            CO_OB_Fiber fiber = iterator.next();

            StringTokenizer st = new StringTokenizer(fiber.getFiberMixture(), "/");
            ArrayList<String> mixtures = new ArrayList<String>();

            while(st.hasMoreTokens()) {
                String token = st.nextToken();
                mixtures.add(token.replaceAll("[0-9]", ""));
            }

            boolean mixture_found = false;
            boolean all_flag_false = true;
            for(int i=0; i<mixture_flag.length; i++) {
                if(mixture_flag[i]) {
                    all_flag_false = false;

                    if(mixtures.contains(mixture_string[i])) {
                        mixture_found = true;
                    }
                }
            }

            if(!mixture_found && !all_flag_false) {
                iterator.remove();
            }
        }

        boolean thick_true_flag = false;
        for(int i=0;i<thick_flag.length;i++) {
            if(thick_flag[i])
                thick_true_flag = true;
        }

        if(thick_true_flag) {
            for(int i=0; i<thick_flag.length;i++) {
                if(!thick_flag[i]) {
                    for(Iterator<CO_OB_Fiber> iterator = fiberArrayList.iterator(); iterator.hasNext();) {
                        CO_OB_Fiber fiber = iterator.next();

                        if(fiber.getFiberThick() == thick_int[i]) {
                            iterator.remove();
                        }
                    }
                }
            }
        }

        if(density_weight != 3) {
            for (Iterator<CO_OB_Fiber> iterator = fiberArrayList.iterator(); iterator.hasNext(); ) {
                CO_OB_Fiber fiber = iterator.next();

                if (!fiber.getFiberDensity().equals(density_string[density_weight])) {
                    iterator.remove();
                    continue;
                }
            }
        }

        for (Iterator<CO_OB_Fiber> iterator = fiberArrayList.iterator(); iterator.hasNext(); ) {
            CO_OB_Fiber fiber = iterator.next();

            if (fiber.getFiberPrice() < price_min * 1000 || fiber.getFiberPrice() > price_max * 1000) {
                iterator.remove();
            }
        }

        if(!selected_color.equals("All")) {
            for(Iterator<CO_OB_Fiber> iterator = fiberArrayList.iterator(); iterator.hasNext();) {
                CO_OB_Fiber fiber = iterator.next();

                if(!fiber.getFiberColor().equals(selected_color)) {
                    iterator.remove();
                    continue;
                }
            }
        }
    }

    public static void refresh_search_result() {
        int i = 0;
        LinearLayout current = null;

        result_holder.removeAllViews();
        getMatchingResult();

        for(CO_OB_Fiber fiber : fiberArrayList) {
            if(i%4 == 0) {
                LinearLayout temp = new LinearLayout(activity.getApplicationContext());
                temp.setOrientation(LinearLayout.HORIZONTAL);

                current = temp;
                result_holder.addView(current);
            }

            final ImageView image = new ImageView(activity.getApplicationContext());
            image.setMaxHeight(180);
            image.setMaxWidth(180);
            image.setMinimumHeight(180);
            image.setMinimumWidth(180);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setAdjustViewBounds(false);
            int resId = activity.getApplicationContext().getResources().getIdentifier(fiber.getFiberImage(), "drawable", activity.getApplicationContext().getPackageName());
            image.setImageResource(resId);
            image.setPadding(10, 10, 10, 10);
            image.setTag(String.valueOf(fiber.getFiberID()));

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item_fragment = activity.getFragmentManager().findFragmentById(R.id.item_layout);

                    if (item_fragment == null) {
                        ViewGroup.LayoutParams layoutParams = item_layout.getLayoutParams();
                        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                        Bundle bundle = new Bundle();
                        bundle.putString("id", v.getTag().toString());

                        CO_FR_FiberInfo fiberInfo = new CO_FR_FiberInfo();
                        fiberInfo.setArguments(bundle);

                        activity.getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.animator.search_up,
                                        R.animator.search_down,
                                        R.animator.search_up,
                                        R.animator.search_down)
                                .add(R.id.item_layout, fiberInfo)
                                .addToBackStack(null)
                                .commit();
                    }

                }
            });

            image.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

                    String[] mymeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                    ClipData dragData = new ClipData(v.getTag().toString(), mymeTypes, item);
                    draggingFiber = v.getTag().toString();

                    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(image);

                    v.startDrag(dragData, myShadow, v, 0);

                    return true;
                }
            });

            current.addView(image);
            i++;
        }

        dragging_holder.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_LOCATION:
                        System.out.println(event.getY());
                        break;
                    case DragEvent.ACTION_DROP:
                        if (event.getY() > 900) {
                            if (BO_AC_Main.currentPocket != null) {
                                if(!CO_LO_DataCenter.pocketFiberExists(Integer.parseInt(draggingFiber))) {
                                    CO_LO_DataCenter.addFiberToPocket(String.valueOf(BO_AC_Main.currentPocket.getPocketID()), draggingFiber);

                                    Toast.makeText(v.getContext(), "원단이 포켓박스에 추가되었습니다", Toast.LENGTH_SHORT).show();
                                    refresh_layout();
                                } else {
                                    Toast.makeText(v.getContext(), "원단이 이미 존재합니다", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(activity.getBaseContext(), "먼저 포켓을 만드세요", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                }

                return true;
            }
        });

        count_text.setText(String.valueOf(fiberArrayList.size()));
        count_frame.setVisibility(View.VISIBLE);

        if(value_holder.getChildCount() == 0) {
            count_frame.setVisibility(View.GONE);
            result_holder.removeAllViews();
        }
    }

    public static void refresh_boolean() {
        btn_hwasum.setBackgroundColor(Color.TRANSPARENT);
        btn_jikgi.setBackgroundColor(Color.TRANSPARENT);
        btn_nit.setBackgroundColor(Color.TRANSPARENT);
        btn_daima.setBackgroundColor(Color.TRANSPARENT);
        btn_nayum.setBackgroundColor(Color.TRANSPARENT);
        btn_yinjo.setBackgroundColor(Color.TRANSPARENT);
        btn_myeon.setBackgroundColor(Color.TRANSPARENT);
        btn_nubim.setBackgroundColor(Color.TRANSPARENT);
        btn_yangbok.setBackgroundColor(Color.TRANSPARENT);
        btn_yangjang.setBackgroundColor(Color.TRANSPARENT);
        btn_gwang.setBackgroundColor(Color.TRANSPARENT);
        btn_span.setBackgroundColor(Color.TRANSPARENT);
        btn_race.setBackgroundColor(Color.TRANSPARENT);
        btn_silk.setBackgroundColor(Color.TRANSPARENT);
        btn_wool.setBackgroundColor(Color.TRANSPARENT);
        btn_alpha.setBackgroundColor(Color.TRANSPARENT);
        btn_mopi.setBackgroundColor(Color.TRANSPARENT);
        btn_digital.setBackgroundColor(Color.TRANSPARENT);
        btn_tul.setBackgroundColor(Color.TRANSPARENT);
        btn_etc.setBackgroundColor(Color.TRANSPARENT);

        btn_c.setBackgroundColor(Color.TRANSPARENT);
        btn_cm.setBackgroundColor(Color.TRANSPARENT);
        btn_p.setBackgroundColor(Color.TRANSPARENT);
        btn_r.setBackgroundColor(Color.TRANSPARENT);
        btn_w.setBackgroundColor(Color.TRANSPARENT);
        btn_a.setBackgroundColor(Color.TRANSPARENT);
        btn_pa.setBackgroundColor(Color.TRANSPARENT);
        btn_t.setBackgroundColor(Color.TRANSPARENT);

        btn_5t.setBackgroundColor(Color.TRANSPARENT);
        btn_15t.setBackgroundColor(Color.TRANSPARENT);
        btn_25t.setBackgroundColor(Color.TRANSPARENT);
        btn_35t.setBackgroundColor(Color.TRANSPARENT);
        btn_45t.setBackgroundColor(Color.TRANSPARENT);

        if(type_flag[0])
            btn_hwasum.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[1])
            btn_jikgi.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[2])
            btn_nit.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[3])
            btn_daima.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[4])
            btn_nayum.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[5])
            btn_yinjo.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[6])
            btn_myeon.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[7])
            btn_nubim.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[8])
            btn_yangbok.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[9])
            btn_yangjang.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[10])
            btn_gwang.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[11])
            btn_span.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[12])
            btn_race.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[13])
            btn_silk.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[14])
            btn_wool.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[15])
            btn_alpha.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[16])
            btn_mopi.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[17])
            btn_digital.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[18])
            btn_tul.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(type_flag[19])
            btn_etc.setBackgroundColor(Color.parseColor("#1F9A95"));

        if(mixture_flag[0])
            btn_c.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(mixture_flag[1])
            btn_cm.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(mixture_flag[2])
            btn_p.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(mixture_flag[3])
            btn_r.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(mixture_flag[4])
            btn_w.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(mixture_flag[5])
            btn_a.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(mixture_flag[6])
            btn_pa.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(mixture_flag[7])
            btn_t.setBackgroundColor(Color.parseColor("#1F9A95"));

        if(thick_flag[0])
            btn_5t.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(thick_flag[1])
            btn_15t.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(thick_flag[2])
            btn_25t.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(thick_flag[3])
            btn_35t.setBackgroundColor(Color.parseColor("#1F9A95"));
        if(thick_flag[4])
            btn_45t.setBackgroundColor(Color.parseColor("#1F9A95"));
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mNfcAdapter != null)
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFilters, mNFCTechLists);

        if( NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction()) )
            // 인텐트에 포함된 정보를 분석해서 화면에 표시
            onNewIntent(getIntent());
    }

    @Override
    public void onNewIntent(Intent intent) {
        // 인텐트에서 액션을 추출
        String action = intent.getAction();
        // 인텐트에서 태그 정보 추출
        String tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG).toString();
        String strMsg = action + "\n\n" + tag;

        // 인텐트에서 NDEF 메시지 배열을 구한다
        Parcelable[] messages = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        if(messages == null) return;

        for(int i=0; i < messages.length; i++)
            // NDEF 메시지를 화면에 출력
            showMsg((NdefMessage)messages[i]);
    }

    public void showMsg(NdefMessage mMessage) {
        String strMsg = "", strRec="";
        // NDEF 메시지에서 NDEF 레코드 배열을 구한다
        NdefRecord[] recs = mMessage.getRecords();
        for (int i = 0; i < recs.length; i++) {
            // 개별 레코드 데이터를 구한다
            NdefRecord record = recs[i];
            byte[] payload = record.getPayload();
            // 레코드 데이터 종류가 텍스트 일때
            if( Arrays.equals(record.getType(), NdefRecord.RTD_TEXT) ) {
                // 버퍼 데이터를 인코딩 변환
                strRec = byteDecoding(payload);
                //strRec = "Text: " + strRec;
            }
            // 레코드 데이터 종류가 URI 일때
            else if( Arrays.equals(record.getType(), NdefRecord.RTD_URI) ) {
                strRec = new String(payload, 0, payload.length);
                //strRec = "URI: " + strRec;
            }
        }

        if(strRec.length() != 0) {
            matching_fiber = CO_LO_DataCenter.getFiber(Integer.parseInt(strRec));
            initialize_fiber_setting();
            refresh_layout();
            tagging_intro.setVisibility(View.GONE);
        }
    }

    public String byteDecoding(byte[] buf) {
        String strText="";
        String textEncoding = ((buf[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
        int langCodeLen = buf[0] & 0077;

        try {
            strText = new String(buf, langCodeLen + 1,
                    buf.length - langCodeLen - 1, textEncoding);
        } catch(Exception e) {
            Log.d("tag1", e.toString());
        }
        return strText;
    }

    public static void buttonImageChanger(int switcher) {
        for(int i=0;i<values_changed.length;i++) {
            values_changed[i] = false;
        }

        for(int i=0;i<value_holder.getChildCount();i++) {
            View tagView = value_holder.getChildAt(i);
            String tag = String.valueOf(tagView.getTag());

            if(tag.equals("hwasum") || tag.equals("jikgi") || tag.equals("nit") || tag.equals("daima") || tag.equals("nayum") || tag.equals("yinjo") || tag.equals("myeon") || tag.equals("nubim") || tag.equals("yangbok") || tag.equals("yangjang") || tag.equals("gwang") || tag.equals("span")) {
                values_changed[0] = true;
            } else if(tag.equals("c") || tag.equals("cm") || tag.equals("p") || tag.equals("r") || tag.equals("w") || tag.equals("a") || tag.equals("pa") || tag.equals("t")) {
                values_changed[1] = true;
            } else if(tag.equals("color")) {
                values_changed[2] = true;
            } else if(tag.equals("density")) {
                values_changed[3] = true;
            } else if(tag.equals("thick")) {
                values_changed[4] = true;
            } else if(tag.equals("price")) {
                values_changed[5] = true;
            }
        }

        if(values_changed[0]) {
            if(switcher == 0)
                btn_type.setImageResource(R.drawable.c_type_on);
            else
                btn_type.setImageResource(R.drawable.c_type_off);
        }
        if(values_changed[1]) {
            if(switcher == 1)
                btn_mixture.setImageResource(R.drawable.c_mixture_on);
            else
                btn_mixture.setImageResource(R.drawable.c_mixture_off);
        }
        if(values_changed[2]) {
            if(switcher == 2)
                btn_color.setImageResource(R.drawable.c_color_on);
            else
                btn_color.setImageResource(R.drawable.c_color_off);
        }
        if(values_changed[3]) {
            if(switcher == 3)
                btn_density.setImageResource(R.drawable.c_density_on);
            else
                btn_density.setImageResource(R.drawable.c_density_off);
        }
        if(values_changed[4]) {
            if(switcher == 4)
                btn_thick.setImageResource(R.drawable.c_thick_on);
            else
                btn_thick.setImageResource(R.drawable.c_thick_off);
        }
        if(values_changed[5]) {
            if(switcher == 5)
                btn_price.setImageResource(R.drawable.c_price_on);
            else
                btn_price.setImageResource(R.drawable.c_price_off);
        }
    }
}
