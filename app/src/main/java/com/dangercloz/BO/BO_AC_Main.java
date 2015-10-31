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

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
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
import android.widget.TextView;
import android.widget.Toast;

import com.dangercloz.CO.CO_FR_FiberInfo;
import com.dangercloz.CO.CO_LO_DataCenter;
import com.dangercloz.CO.CO_LO_PocketDB;
import com.dangercloz.CO.CO_OB_Fiber;
import com.dangercloz.CO.CO_OB_Pocket;
import com.dangercloz.DC.DC_AC_Main;
import com.dangercloz.MI.MI_AC_Main;
import com.dangercloz.SE.SE_AC_Main;
import com.dangercloz.pocketswatch.R;

import java.util.ArrayList;
import java.util.List;

public class BO_AC_Main extends Activity {

    static Fragment pocket_fragment, add_fragment, tag_fragment, pocket_sub_fragment, detail_fragment;
    static ViewGroup pocket_layout, main_tag_bar, add_layout, hash_search_holder, search_layout, pocket_sub_layout, pocket_detail;
    static FragmentManager fragmentMan;
    public static LinearLayout selected_bar, grid_holder;
    static LayoutInflater inflater;
    static RelativeLayout tag_holder;
    static ArrayList<CO_OB_Fiber> fiberArrayList;
    static Context context;
    static FrameLayout temp_margin, count_frame;
    static TextView count_text, current_pocket_name, current_pocket_count;
    static String draggingFiber;
    static FrameLayout btn_mini;
    public static String userID = "manjonging";
    public static String userName = "Min Jung Kwon";
    public static CO_OB_Pocket currentPocket;

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    static float touchY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_ac_main);
        getWindow().setWindowAnimations(0);

        context = getApplicationContext();
        inflater = LayoutInflater.from(this);
        selected_bar = (LinearLayout)findViewById(R.id.selected_tag_bar);
        grid_holder = (LinearLayout)findViewById(R.id.grid_holder);
        temp_margin = (FrameLayout)findViewById(R.id.temp_margin);
        count_frame = (FrameLayout)findViewById(R.id.count_frame);
        count_text = (TextView)findViewById(R.id.count_text);
        current_pocket_name = (TextView)findViewById(R.id.current_pocket_name);
        current_pocket_count = (TextView)findViewById(R.id.current_pocket_count);
        fragmentMan = getFragmentManager();
        btn_mini = (FrameLayout)findViewById(R.id.mini_pocket_touch);

        new CO_LO_DataCenter(context);

        ArrayList<String> first = new ArrayList<String>();
        first.add("NEW");   first.add("HOT");
        fiberArrayList = CO_LO_DataCenter.searchResultList(first);
        count_text.setText(String.valueOf(fiberArrayList.size()));

        if(currentPocket == null) {
            CO_LO_PocketDB pocketDB = new CO_LO_PocketDB(context);
            List<CO_OB_Pocket> pockets = pocketDB.getUserPockets(userID);
            if (pockets.size() != 0) {
                currentPocket = pockets.get(0);
            }
        }

        ImageButton btn_search = (ImageButton)findViewById(R.id.btn_menu_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SE_AC_Main.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton btn_mine = (ImageButton)findViewById(R.id.btn_menu_mine);
        btn_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MI_AC_Main.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton btn_DDM = (ImageButton)findViewById(R.id.btn_menu_ddm);
        btn_DDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DC_AC_Main.class);
                startActivity(intent);
                finish();
            }
        });

        pocket_layout = (ViewGroup)findViewById(R.id.pocket_layout);
        pocket_sub_layout = (ViewGroup)findViewById(R.id.pocket_sub_layout);
        final ImageButton btn_pocket_all = (ImageButton)findViewById(R.id.btn_pocket_all);
        btn_pocket_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                getFragmentManager().popBackStack();
                pocket_fragment = getFragmentManager().findFragmentById(R.id.pocket_layout);

                if(pocket_fragment == null) {
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

                    grid_holder.setVisibility(View.GONE);
                } else {
                    getFragmentManager().popBackStack();
                    grid_holder.setVisibility(View.VISIBLE);
                }
            }
        });

        add_layout = (ViewGroup)findViewById(R.id.add_layout);
        ImageButton btn_pocket_add = (ImageButton)findViewById(R.id.btn_pocket_add);
        btn_pocket_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                getFragmentManager().popBackStack();
                add_fragment = getFragmentManager().findFragmentById(R.id.add_layout);

                if(add_fragment == null) {
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

                    grid_holder.setVisibility(View.GONE);
                } else {
                    getFragmentManager().popBackStack();
                    grid_holder.setVisibility(View.VISIBLE);
                }
            }
        });

        search_layout = (ViewGroup)findViewById(R.id.search_layout);
        pocket_detail = (ViewGroup)findViewById(R.id.pocket_detail);
        main_tag_bar = (ViewGroup)findViewById(R.id.main_tag_bar);
        ImageView btn_tag_list = (ImageView)findViewById(R.id.btn_tag_list);
        btn_tag_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(touchY - 20 < event.getY()) {
                            getFragmentManager().popBackStack();
                            tag_fragment = getFragmentManager().findFragmentById(R.id.search_layout);

                            if(tag_fragment == null) {
                                ViewGroup.LayoutParams layoutParams = search_layout.getLayoutParams();
                                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                                getFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.animator.search_up,
                                                R.animator.search_down,
                                                R.animator.search_up,
                                                R.animator.search_down)
                                        .add(R.id.search_layout, new BO_FR_SearchTag())
                                        .addToBackStack(null)
                                        .commit();

                                grid_holder.setVisibility(View.GONE);
                            }
                        }
                        break;
                }
                return true;
            }
        });

        ImageButton btn_tag_search = (ImageButton)findViewById(R.id.btn_tag_search);
        btn_tag_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
                tag_fragment = getFragmentManager().findFragmentById(R.id.search_layout);

                if(tag_fragment == null) {
                    ViewGroup.LayoutParams layoutParams = search_layout.getLayoutParams();
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                    BO_FR_SearchTag searchTag = new BO_FR_SearchTag();
                    Bundle data = new Bundle();
                    data.putString("type", "Y");
                    searchTag.setArguments(data);

                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(R.animator.search_up,
                                    R.animator.search_down,
                                    R.animator.search_up,
                                    R.animator.search_down)
                            .add(R.id.search_layout, searchTag)
                            .addToBackStack(null)
                            .commit();

                    grid_holder.setVisibility(View.GONE);
                }
            }
        });

        ImageButton btn_tag_close = (ImageButton)findViewById(R.id.btn_close_tag);
        btn_tag_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BO_FR_SearchTag.selected_tag.clear();
                refresh_selected_tag_bar();
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            String value = extras.getString("ID");

            tag_fragment = fragmentMan.findFragmentById(R.id.search_layout);

                    if (tag_fragment == null) {
                        ViewGroup.LayoutParams layoutParams = search_layout.getLayoutParams();
                        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                        Bundle bundle = new Bundle();
                        bundle.putString("id", value);

                        CO_FR_FiberInfo fiberInfo = new CO_FR_FiberInfo();
                        fiberInfo.setArguments(bundle);

                        fragmentMan.beginTransaction()
                                .setCustomAnimations(R.animator.search_up,
                                        R.animator.search_down,
                                        R.animator.search_up,
                                        R.animator.search_down)
                                .add(R.id.search_layout, fiberInfo)
                                .addToBackStack(null)
                                .commit();

                        grid_holder.setVisibility(View.GONE);
                    }
        }

        tag_holder = (RelativeLayout)findViewById(R.id.tag_holder);
        refresh_selected_tag_bar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bo_ac_main, menu);
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

    public static void refresh_selected_tag_bar() {
        selected_bar.removeAllViews();

        if(BO_FR_SearchTag.selected_tag != null) {
            for (String temp : BO_FR_SearchTag.selected_tag) {
                View tagView = inflater.inflate(R.layout.custom_adapter_selected, null);

                final TextView textView = (TextView) tagView.findViewById(R.id.tag_string);
                textView.setText(temp);

                tagView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BO_FR_SearchTag.selected_tag.remove(textView.getText().toString());
                        refresh_selected_tag_bar();
                    }
                });

                selected_bar.addView(tagView);
            }
        }

        if(BO_FR_SearchTag.selected_tag == null || BO_FR_SearchTag.selected_tag.size() == 0) {
            tag_holder.setVisibility(View.GONE);
            temp_margin.setVisibility(View.GONE);

            if(BO_FR_SearchTag.selected_tag != null) {
                ArrayList<String> first = new ArrayList<String>();
                first.add("NEW"); first.add("HOT");

                fiberArrayList = CO_LO_DataCenter.searchResultList(first);
            }
        } else {
            tag_holder.setVisibility(View.VISIBLE);
            temp_margin.setVisibility(View.VISIBLE);

            fiberArrayList = CO_LO_DataCenter.searchResultList(BO_FR_SearchTag.selected_tag);
        }

        count_text.setText(String.valueOf(fiberArrayList.size()));

        if(currentPocket == null) {
            current_pocket_name.setText("새 포켓을 만드세요");
        } else {
            current_pocket_name.setText(String.format("%s", currentPocket.getPocketName()));
            btn_mini.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch(event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            touchY = event.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            if(touchY + 20 > event.getY()) {
                                fragmentMan.popBackStack();
                                pocket_sub_fragment = fragmentMan.findFragmentById(R.id.pocket_sub_layout);

                                if(pocket_sub_fragment == null) {
                                    ViewGroup.LayoutParams layoutParams = pocket_sub_layout.getLayoutParams();
                                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                                    Bundle bundle = new Bundle();
                                    bundle.putInt("id", currentPocket.getPocketID());
                                    BO_FR_PocketDetail detail = new BO_FR_PocketDetail();
                                    detail.setArguments(bundle);

                                    fragmentMan.beginTransaction()
                                            .setCustomAnimations(R.animator.slide_up,
                                                    R.animator.slide_down,
                                                    R.animator.slide_up,
                                                    R.animator.slide_down)
                                            .add(R.id.pocket_sub_layout, detail)
                                            .addToBackStack(null)
                                            .commit();

                                    grid_holder.setVisibility(View.GONE);
                                } else {
                                    fragmentMan.popBackStack();
                                    grid_holder.setVisibility(View.VISIBLE);
                                }
                            }
                            break;
                    }

                    return true;
                }
            });

            current_pocket_count.setText(String.valueOf(CO_LO_DataCenter.getPocketContainer(String.valueOf(currentPocket.getPocketID())).size()));
        }

        resultLayout();

        selected_bar.requestLayout();
    }

    public static void resultLayout() {
        int i = 0;
        LinearLayout current = null;

        grid_holder.removeAllViews();
        grid_holder.addView(temp_margin);

        for(CO_OB_Fiber fiber : fiberArrayList) {
            if(i % 4 == 0) {
                LinearLayout temp = new LinearLayout(context);
                temp.setOrientation(LinearLayout.HORIZONTAL);

                current = temp;
                grid_holder.addView(current);
            }

            final ImageView image = new ImageView(context);
            image.setMaxHeight(180);
            image.setMaxWidth(180);
            image.setMinimumHeight(180);
            image.setMinimumWidth(180);
            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setAdjustViewBounds(false);
            int resId = context.getResources().getIdentifier(fiber.getFiberImage(), "drawable", context.getPackageName());
            image.setImageResource(resId);
            image.setPadding(10, 10, 10, 10);
            image.setTag(String.valueOf(fiber.getFiberID()));

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tag_fragment = fragmentMan.findFragmentById(R.id.search_layout);

                    if (tag_fragment == null) {
                        ViewGroup.LayoutParams layoutParams = search_layout.getLayoutParams();
                        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                        Bundle bundle = new Bundle();
                        bundle.putString("id", v.getTag().toString());

                        CO_FR_FiberInfo fiberInfo = new CO_FR_FiberInfo();
                        fiberInfo.setArguments(bundle);

                        fragmentMan.beginTransaction()
                                .setCustomAnimations(R.animator.search_up,
                                        R.animator.search_down,
                                        R.animator.search_up,
                                        R.animator.search_down)
                                .add(R.id.search_layout, fiberInfo)
                                .addToBackStack(null)
                                .commit();

                        grid_holder.setVisibility(View.GONE);
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

            i += 1;
        }

        grid_holder.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
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
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        //Log.d("DRAG", String.format("Drag ended"));
                        break;
                    case DragEvent.ACTION_DROP:
                        //Log.d("DRAG", "Action drop");
                        if (event.getY() > 700) {
                            if(currentPocket != null) {
                                if(!CO_LO_DataCenter.pocketFiberExists(Integer.parseInt(draggingFiber))) {
                                    CO_LO_DataCenter.addFiberToPocket(String.valueOf(currentPocket.getPocketID()), draggingFiber);

                                    Toast.makeText(context, "원단이 포켓박스에 추가되었습니다", Toast.LENGTH_SHORT).show();
                                    refresh_selected_tag_bar();
                                } else {
                                    Toast.makeText(context, "원단이 이미 존재합니다", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(context, "먼저 포켓을 만드세요", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    default:
                        return false;
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
            grid_holder.setVisibility(View.VISIBLE);
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

}