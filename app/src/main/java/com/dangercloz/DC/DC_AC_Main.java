package com.dangercloz.DC;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dangercloz.BO.BO_AC_Main;
import com.dangercloz.BO.BO_FR_PocketAdd;
import com.dangercloz.BO.BO_FR_PocketAll;
import com.dangercloz.CO.CO_FR_FiberInfo;
import com.dangercloz.CO.CO_LO_DataCenter;
import com.dangercloz.CO.CO_OB_Fiber;
import com.dangercloz.CO.CO_OB_PurchaseHistory;
import com.dangercloz.CO.CO_OB_Store;
import com.dangercloz.MI.MI_AC_Main;
import com.dangercloz.SE.SE_AC_Main;
import com.dangercloz.pocketswatch.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DC_AC_Main extends Activity {

    static Context context;
    static LayoutInflater inflater;
    static FragmentManager fragmentManager;
    static ImageButton btn_pocket_all, btn_pocket_add;
    static ImageButton btn_1st, btn_2nd, btn_3rd, btn_4th;
    static TextView btn_adong, btn_bdong, btn_cdong, btn_ddong;
    static String floor, dong;
    static ScrollView ddm_scroll;
    static LinearLayout pickup_linear;
    static Fragment pocket_fragment, add_fragment, detail_fragment;
    static ViewGroup pocket_layout, add_layout, pickup_temp, ddm_holder, detail_layout;
    static List<CO_OB_PurchaseHistory> purchaseHistory;

    static String[] curve_list = {"b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8"};
    static String[] background_list = {"#686868", "#5C5C5C", "#4E4E4E", "#424242", "#3D3D3D", "#363636", "#303030", "#292929"};
    static String[] total_background_list = {"#5C5C5C", "#4E4E4E", "#424242", "#3D3D3D", "#363636", "#303030", "#292929", "#686868"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dc_ac_main);
        getWindow().setWindowAnimations(0);

        context = getApplicationContext();
        inflater = LayoutInflater.from(this);
        fragmentManager = getFragmentManager();

        btn_pocket_all = (ImageButton)findViewById(R.id.btn_pocket_all);
        btn_pocket_add = (ImageButton)findViewById(R.id.btn_pocket_add);
        pocket_layout = (ViewGroup)findViewById(R.id.pocket_layout);
        add_layout = (ViewGroup)findViewById(R.id.add_layout);
        btn_1st = (ImageButton)findViewById(R.id.btn_1st);
        btn_2nd = (ImageButton)findViewById(R.id.btn_2nd);
        btn_3rd = (ImageButton)findViewById(R.id.btn_3rd);
        btn_4th = (ImageButton)findViewById(R.id.btn_4th);
        btn_adong = (TextView)findViewById(R.id.btn_adong);
        btn_bdong = (TextView)findViewById(R.id.btn_bdong);
        btn_cdong = (TextView)findViewById(R.id.btn_cdong);
        btn_ddong = (TextView)findViewById(R.id.btn_ddong);
        ddm_scroll = (ScrollView)findViewById(R.id.ddm_scrollview);
        pickup_linear = (LinearLayout)findViewById(R.id.pickup_linear);
        pickup_temp = (ViewGroup)findViewById(R.id.pickup_temp);
        ddm_holder = (ViewGroup)findViewById(R.id.ddm_holder);
        detail_layout = (ViewGroup)findViewById(R.id.detail_layout);

        floor = "1";    dong = "A";

        ImageButton btn_board = (ImageButton)findViewById(R.id.btn_menu_board);
        btn_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BO_AC_Main.class);
                startActivity(intent);
                finish();
            }
        });

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

        /* Floor button section */

        btn_1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_1st.setImageResource(R.drawable.floor1_on);
                btn_2nd.setImageResource(R.drawable.floor2_off);
                btn_3rd.setImageResource(R.drawable.floor3_off);
                btn_4th.setImageResource(R.drawable.floor4_off);

                btn_adong.setTextColor(Color.parseColor("#3EB5B0"));
                btn_bdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_cdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_ddong.setTextColor(Color.parseColor("#FFFFFF"));

                floor = "1";
                dong = "A";
                refreshDDM();
            }
        });

        btn_2nd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_1st.setImageResource(R.drawable.floor1_off);
                btn_2nd.setImageResource(R.drawable.floor2_on);
                btn_3rd.setImageResource(R.drawable.floor3_off);
                btn_4th.setImageResource(R.drawable.floor4_off);

                btn_adong.setTextColor(Color.parseColor("#3EB5B0"));
                btn_bdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_cdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_ddong.setTextColor(Color.parseColor("#FFFFFF"));

                floor = "2";
                dong = "A";
                refreshDDM();
            }
        });

        btn_3rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_1st.setImageResource(R.drawable.floor1_off);
                btn_2nd.setImageResource(R.drawable.floor2_off);
                btn_3rd.setImageResource(R.drawable.floor3_on);
                btn_4th.setImageResource(R.drawable.floor4_off);

                btn_adong.setTextColor(Color.parseColor("#3EB5B0"));
                btn_bdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_cdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_ddong.setTextColor(Color.parseColor("#FFFFFF"));

                floor = "3";
                dong = "A";
                refreshDDM();
            }
        });

        btn_4th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_1st.setImageResource(R.drawable.floor1_off);
                btn_2nd.setImageResource(R.drawable.floor2_off);
                btn_3rd.setImageResource(R.drawable.floor3_off);
                btn_4th.setImageResource(R.drawable.floor4_on);

                btn_adong.setTextColor(Color.parseColor("#3EB5B0"));
                btn_bdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_cdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_ddong.setTextColor(Color.parseColor("#FFFFFF"));

                floor = "4";
                dong = "A";
                refreshDDM();
            }
        });

        /* Dong button section */

        btn_adong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_adong.setTextColor(Color.parseColor("#3EB5B0"));
                btn_bdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_cdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_ddong.setTextColor(Color.parseColor("#FFFFFF"));

                dong = "A";
                refreshDDM();
            }
        });

        btn_bdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_adong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_bdong.setTextColor(Color.parseColor("#3EB5B0"));
                btn_cdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_ddong.setTextColor(Color.parseColor("#FFFFFF"));

                dong = "B";
                refreshDDM();
            }
        });

        btn_cdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_adong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_bdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_cdong.setTextColor(Color.parseColor("#3EB5B0"));
                btn_ddong.setTextColor(Color.parseColor("#FFFFFF"));

                dong = "C";
                refreshDDM();
            }
        });

        btn_ddong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_adong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_bdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_cdong.setTextColor(Color.parseColor("#FFFFFF"));
                btn_ddong.setTextColor(Color.parseColor("#3EB5B0"));

                dong = "D";
                refreshDDM();
            }
        });

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

        refreshDDM();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dc_ac_main, menu);
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

    public static void refreshDDM() {
        pickup_linear.removeAllViews();
        pickup_linear.addView(pickup_temp);

        int count = 0;
        purchaseHistory = CO_LO_DataCenter.getPurchaseHistory();
        for(CO_OB_PurchaseHistory purchase : purchaseHistory) {
            if(purchase.getCompletion().equals("F") && purchase.getPurchaseType().equals("P")) {
                final CO_OB_Store store = CO_LO_DataCenter.getStore(purchase.getFiberID());
                String current_dong = store.getStoreLocation().substring(0,1);
                String current_floor = store.getStoreLocation().substring(3,4);

                if(dong.equals(current_dong) && floor.equals(current_floor)) {
                    final View row = inflater.inflate(R.layout.custom_ddm_row, pickup_linear, false);
                    CO_OB_Fiber fiber = CO_LO_DataCenter.getFiber(purchase.getFiberID());
                    row.setTag(String.valueOf(purchase.getPurchaseID()));
                    row.setId(fiber.getFiberID());

                    TextView fiberName = (TextView) row.findViewById(R.id.fiberName);
                    fiberName.setText(fiber.getFiberName());
                    TextView fiberInfo = (TextView) row.findViewById(R.id.fiberPrice);
                    fiberInfo.setText(String.format("%dYD_%d원", purchase.getFiberNumber(), (fiber.getFiberPrice() * purchase.getFiberNumber())));
                    TextView storeName = (TextView) row.findViewById(R.id.storeName);
                    storeName.setText(store.getStoreName());
                    int resId = context.getResources().getIdentifier(fiber.getFiberImage(), "drawable", context.getPackageName());
                    ImageView fiberImage = (ImageView) row.findViewById(R.id.fiberImage);
                    fiberImage.setImageResource(resId);
                    ViewGroup btn_call = (ViewGroup)row.findViewById(R.id.btn_call);
                    btn_call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, String.format("%s에 전화", store.getStoreName()), Toast.LENGTH_SHORT).show();
                        }
                    });
                    ViewGroup btn_pickup = (ViewGroup)row.findViewById(R.id.btn_pickup);
                    btn_pickup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pid = Integer.parseInt(row.getTag().toString());
                            CO_OB_PurchaseHistory purchase = CO_LO_DataCenter.getPurchase(pid);

                            purchase.setCompletion("T");
                            String compTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
                            purchase.setCompletionTime(compTime);

                            CO_LO_DataCenter.updatePurchaseHistory(purchase);

                            Toast.makeText(context, "픽업완료 되었습니다", Toast.LENGTH_SHORT).show();

                            refreshDDM();
                        }
                    });

                    ViewGroup info = (ViewGroup) row.findViewById(R.id.info_background);
                    info.setBackgroundColor(Color.parseColor(background_list[count % 8]));
                    ViewGroup total = (ViewGroup) row.findViewById(R.id.total_background);
                    total.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                    ddm_holder.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                    ImageView curved = (ImageView) row.findViewById(R.id.curve_background);
                    int curveId = context.getResources().getIdentifier(curve_list[count % 8], "drawable", context.getPackageName());
                    curved.setImageResource(curveId);

                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            detail_fragment = fragmentManager.findFragmentById(R.id.detail_layout);

                            if(detail_fragment == null) {
                                ViewGroup.LayoutParams layoutParams = detail_layout.getLayoutParams();
                                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                                Bundle bundle = new Bundle();
                                bundle.putString("id", String.valueOf(v.getId()));

                                CO_FR_FiberInfo fiberInfo = new CO_FR_FiberInfo();
                                fiberInfo.setArguments(bundle);

                                fragmentManager.beginTransaction()
                                        .setCustomAnimations(R.animator.search_up,
                                                R.animator.search_down,
                                                R.animator.search_up,
                                                R.animator.search_down)
                                        .add(R.id.detail_layout, fiberInfo)
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }
                    });

                    pickup_linear.addView(row);
                    count += 1;
                }
            }
        }

        if(pickup_linear.getChildCount() == 1) {
            pickup_temp.setBackgroundColor(Color.parseColor(background_list[0]));
            ddm_holder.setBackgroundColor(Color.parseColor(background_list[0]));
        }
    }

}
