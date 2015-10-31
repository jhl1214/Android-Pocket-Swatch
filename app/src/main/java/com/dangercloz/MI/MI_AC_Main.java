package com.dangercloz.MI;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
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
import com.dangercloz.CO.CO_OB_WishList;
import com.dangercloz.DC.DC_AC_Main;
import com.dangercloz.SE.SE_AC_Main;
import com.dangercloz.pocketswatch.R;

import java.util.List;

public class MI_AC_Main extends Activity {

    public static String initialized = null;
    static Context context;
    static LayoutInflater inflater;
    static FragmentManager fragmentManager;
    static ImageView curve_image, btn_call1, btn_call2, btn_call3, btn_call4;
    static ImageButton btn_wishlist, btn_order, btn_network, btn_pocket_all, btn_pocket_add;
    static Fragment pocket_fragment, add_fragment, fiber_fragment;
    static ViewGroup pocket_layout, add_layout, wishlist_holder, order_holder, network_holder, deliver_temp, pickup_temp;
    static ViewGroup fiber_info_layout;
    static ViewGroup designer1, designer2, designer3, designer4, designer5, designer6, designer7, designer8, designer9;
    static TextView btn_deliver, btn_pickup, btn_designer, btn_store;
    static LinearLayout wishlist_linear, store_linear, deliver_linear, pickup_linear, total_background, info_background;
    static ScrollView designer_scroll, store_scroll, deliver_scroll, pickup_scroll;
    static List<CO_OB_WishList> wishList;
    static List<CO_OB_Store> storeList;
    static List<CO_OB_PurchaseHistory> purchaseHistory;

    static String[] curve_list = {"b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8"};
    static String[] background_list = {"#686868", "#5C5C5C", "#4E4E4E", "#424242", "#3D3D3D", "#363636", "#303030", "#292929"};
    static String[] total_background_list = {"#5C5C5C", "#4E4E4E", "#424242", "#3D3D3D", "#363636", "#303030", "#292929", "#686868"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_ac_main);
        getWindow().setWindowAnimations(0);

        context = getApplicationContext();
        inflater = LayoutInflater.from(this);
        fragmentManager = getFragmentManager();
        initialized = "initialized";

        TextView userName = (TextView)findViewById(R.id.text_userName);
        btn_wishlist = (ImageButton)findViewById(R.id.btn_wishlist);
        btn_order = (ImageButton)findViewById(R.id.btn_order);
        btn_network = (ImageButton)findViewById(R.id.btn_network);
        btn_pocket_all = (ImageButton)findViewById(R.id.btn_pocket_all);
        btn_pocket_add = (ImageButton)findViewById(R.id.btn_pocket_add);
        pocket_layout = (ViewGroup)findViewById(R.id.pocket_layout);
        add_layout = (ViewGroup)findViewById(R.id.add_layout);
        wishlist_holder = (ViewGroup)findViewById(R.id.wishlist_holder);
        order_holder = (ViewGroup)findViewById(R.id.order_holder);
        network_holder = (ViewGroup)findViewById(R.id.network_holder);
        btn_deliver = (TextView)findViewById(R.id.btn_deliver);
        btn_pickup = (TextView)findViewById(R.id.btn_pickup);
        btn_designer = (TextView)findViewById(R.id.btn_designer);
        btn_store = (TextView)findViewById(R.id.btn_store);
        wishlist_linear = (LinearLayout)findViewById(R.id.wishlist_linear);
        store_linear = (LinearLayout)findViewById(R.id.store_linear);
        deliver_linear = (LinearLayout)findViewById(R.id.deliver_linear);
        pickup_linear = (LinearLayout)findViewById(R.id.pickup_linear);
        designer_scroll = (ScrollView)findViewById(R.id.designer_scrollview);
        store_scroll = (ScrollView)findViewById(R.id.store_scrollview);
        curve_image = (ImageView)findViewById(R.id.curve_background);
        total_background = (LinearLayout)findViewById(R.id.total_background);
        info_background = (LinearLayout)findViewById(R.id.info_background);
        fiber_info_layout = (ViewGroup)findViewById(R.id.fiber_info_layout);
        deliver_temp = (ViewGroup)findViewById(R.id.deliver_temp);
        pickup_temp = (ViewGroup)findViewById(R.id.pickup_temp);
        deliver_scroll = (ScrollView)findViewById(R.id.deliver_scrollview);
        pickup_scroll = (ScrollView)findViewById(R.id.pickup_scrollview);

        designer1 = (ViewGroup)findViewById(R.id.designer1);
        designer2 = (ViewGroup)findViewById(R.id.designer2);
        designer3 = (ViewGroup)findViewById(R.id.designer3);
        designer4 = (ViewGroup)findViewById(R.id.designer4);
        designer5 = (ViewGroup)findViewById(R.id.designer5);
        designer6 = (ViewGroup)findViewById(R.id.designer6);
        designer7 = (ViewGroup)findViewById(R.id.designer7);
        designer8 = (ViewGroup)findViewById(R.id.designer8);
        designer9 = (ViewGroup)findViewById(R.id.designer9);
        btn_call1 = (ImageView)findViewById(R.id.btn_call1);
        btn_call2 = (ImageView)findViewById(R.id.btn_call2);
        btn_call3 = (ImageView)findViewById(R.id.btn_call3);
        btn_call4 = (ImageView)findViewById(R.id.btn_call4);

        wishList = CO_LO_DataCenter.getWishList();
        storeList = CO_LO_DataCenter.getAllStores();
        purchaseHistory = CO_LO_DataCenter.getPurchaseHistory();
        //pocketList = pocketDB.getUserPockets(BO_AC_Main.userID);

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

        ImageButton btn_DDM = (ImageButton)findViewById(R.id.btn_menu_ddm);
        btn_DDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DC_AC_Main.class);
                startActivity(intent);
                finish();
            }
        });

        userName.setText(String.format("%s's", BO_AC_Main.userName));

        /* Wishlist section */

        btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_wishlist.setImageResource(R.drawable.wish_on);
                btn_order.setImageResource(R.drawable.order_off);
                btn_network.setImageResource(R.drawable.network_off);
                wishlist_holder.setVisibility(View.VISIBLE);
                order_holder.setVisibility(View.GONE);
                network_holder.setVisibility(View.GONE);
            }
        });

        int count = 0;
        for(CO_OB_WishList wish : wishList) {
            CO_OB_Fiber fiber = CO_LO_DataCenter.getFiber(wish.getFiberID());
            View row = inflater.inflate(R.layout.custom_wishlist_row, wishlist_linear, false);
            row.setId(fiber.getFiberID());
            row.setTag(fiber.getFiberID());

            CO_OB_Store store = CO_LO_DataCenter.getStore(fiber.getFiberID());
            TextView storeName = (TextView)row.findViewById(R.id.storeName);
            storeName.setText(store.getStoreName());
            TextView storeLocation = (TextView)row.findViewById(R.id.storeLocation);
            storeLocation.setText(store.getStoreLocation());
            TextView fiberName = (TextView)row.findViewById(R.id.fiberName);
            fiberName.setText(fiber.getFiberName());
            TextView fiberPrice = (TextView)row.findViewById(R.id.fiberPrice);
            fiberPrice.setText(String.format("%d원/YD", fiber.getFiberPrice()));
            int resId = context.getResources().getIdentifier(fiber.getFiberImage(), "drawable", context.getPackageName());
            ImageView fiberImage = (ImageView)row.findViewById(R.id.fiberImage);
            fiberImage.setImageResource(resId);

            ViewGroup info = (ViewGroup)row.findViewById(R.id.info_background);
            info.setBackgroundColor(Color.parseColor(background_list[count % 8]));
            ViewGroup total = (ViewGroup)row.findViewById(R.id.total_background);
            total.setBackgroundColor(Color.parseColor(total_background_list[count%8]));
            wishlist_holder.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
            ImageView curved = (ImageView)row.findViewById(R.id.curve_background);
            int curveId = context.getResources().getIdentifier(curve_list[count % 8], "drawable", context.getPackageName());
            curved.setImageResource(curveId);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fiber_fragment = getFragmentManager().findFragmentById(R.id.fiber_info_layout);

                    if(fiber_fragment == null) {
                        ViewGroup.LayoutParams layoutParams = fiber_info_layout.getLayoutParams();
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
                                .add(R.id.fiber_info_layout, fiberInfo)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });

            wishlist_linear.addView(row);
            count += 1;
        }
        if(count == 0) {
            wishlist_holder.setBackgroundColor(Color.parseColor(total_background_list[count]));
        }

        /* Order section */

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_wishlist.setImageResource(R.drawable.wish_off);
                btn_order.setImageResource(R.drawable.order_on);
                btn_network.setImageResource(R.drawable.network_off);
                wishlist_holder.setVisibility(View.GONE);
                order_holder.setVisibility(View.VISIBLE);
                network_holder.setVisibility(View.GONE);

                btn_deliver.setTextColor(Color.parseColor("#3EB5B0"));
                btn_pickup.setTextColor(Color.parseColor("#FFFFFF"));
                deliver_linear.setVisibility(View.VISIBLE);
                deliver_scroll.setVisibility(View.VISIBLE);
                pickup_linear.setVisibility(View.GONE);
                pickup_scroll.setVisibility(View.GONE);

                if(deliver_linear.getChildCount() != 1) {
                    order_holder.setBackgroundColor(Color.parseColor(total_background_list[(deliver_linear.getChildCount() - 2) % 8]));
                } else {
                    order_holder.setBackgroundColor(Color.parseColor(background_list[0]));
                }
            }
        });

        btn_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_deliver.setTextColor(Color.parseColor("#3EB5B0"));
                btn_pickup.setTextColor(Color.parseColor("#FFFFFF"));

                deliver_linear.setVisibility(View.VISIBLE);
                deliver_scroll.setVisibility(View.VISIBLE);
                pickup_linear.setVisibility(View.GONE);
                pickup_scroll.setVisibility(View.GONE);

                if(deliver_linear.getChildCount() != 1) {
                    deliver_temp.setBackgroundColor(Color.parseColor(background_list[0]));
                    order_holder.setBackgroundColor(Color.parseColor(total_background_list[(deliver_linear.getChildCount() - 2) % 8]));
                } else {
                    deliver_temp.setBackgroundColor(Color.parseColor(background_list[0]));
                    order_holder.setBackgroundColor(Color.parseColor(background_list[0]));
                }
            }
        });

        btn_pickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_deliver.setTextColor(Color.parseColor("#FFFFFF"));
                btn_pickup.setTextColor(Color.parseColor("#3EB5B0"));

                deliver_linear.setVisibility(View.GONE);
                deliver_scroll.setVisibility(View.GONE);
                pickup_linear.setVisibility(View.VISIBLE);
                pickup_scroll.setVisibility(View.VISIBLE);

                if(pickup_linear.getChildCount() != 1) {
                    pickup_temp.setBackgroundColor(Color.parseColor(background_list[0]));
                    order_holder.setBackgroundColor(Color.parseColor(total_background_list[(pickup_linear.getChildCount() - 2) % 8]));
                } else {
                    pickup_temp.setBackgroundColor(Color.parseColor(background_list[0]));
                    order_holder.setBackgroundColor(Color.parseColor(background_list[0]));
                }
            }
        });

        count = 0;
        for(CO_OB_PurchaseHistory purchase : purchaseHistory) {
            if(purchase.getPurchaseType().equals("D") && purchase.getDelete().equals("F")) {
                final View row = inflater.inflate(R.layout.custom_purchase_row, deliver_linear, false);
                row.setTag(String.valueOf(purchase.getPurchaseID()));

                CO_OB_Fiber fiber = CO_LO_DataCenter.getFiber(purchase.getFiberID());
                TextView fiberName = (TextView) row.findViewById(R.id.fiberInfo);
                fiberName.setText(String.format("%s %dYD", fiber.getFiberName(), purchase.getFiberNumber()));
                TextView fiberPrice = (TextView) row.findViewById(R.id.price);
                fiberPrice.setText(String.format("%d원", fiber.getFiberPrice() * purchase.getFiberNumber()));

                int resId = context.getResources().getIdentifier(fiber.getFiberImage(), "drawable", context.getPackageName());
                ImageView fiberImage = (ImageView) row.findViewById(R.id.fiberImage);
                fiberImage.setImageResource(resId);

                CO_OB_Store store = CO_LO_DataCenter.getStore(purchase.getFiberID());
                TextView storeName = (TextView) row.findViewById(R.id.storeName);
                storeName.setText(store.getStoreName());
                TextView storeLocation = (TextView) row.findViewById(R.id.storeLocation);
                storeLocation.setText(store.getStoreLocation());

                TextView purchaseDate = (TextView) row.findViewById(R.id.purchaseDate);
                java.util.StringTokenizer st = new java.util.StringTokenizer(purchase.getPurchaseTime(), " ");
                //purchaseDate.setText(purchase.getPurchaseTime());
                purchaseDate.setText(st.nextToken());

                ViewGroup btn_cancel = (ViewGroup)row.findViewById(R.id.btn_cancel);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pid = Integer.parseInt(row.getTag().toString());
                        final CO_OB_PurchaseHistory purchase = CO_LO_DataCenter.getPurchase(pid);

                        if (purchase.getCompletion().equals("F")) {
                            CO_LO_DataCenter.deletePurchaseHistory(pid);

                            Toast.makeText(context, "구매 취소되었습니다", Toast.LENGTH_SHORT).show();
                            refreshPurchase();
                        } else {
                            AlertDialog.Builder question = new AlertDialog.Builder(v.getContext());
                            question.setMessage("이미 구매확정 된 상품입니다.\n목록에서 삭제하시겠습니까?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    purchase.setDelete("T");
                                    CO_LO_DataCenter.updatePurchaseHistory(purchase);

                                    Toast.makeText(context, "삭제 되었습니다", Toast.LENGTH_SHORT).show();
                                    refreshPurchase();
                                }
                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });

                            AlertDialog alertDialog = question.create();
                            alertDialog.show();
                        }
                    }
                });

                ViewGroup info = (ViewGroup) row.findViewById(R.id.info_background);
                info.setBackgroundColor(Color.parseColor(background_list[count % 8]));
                ViewGroup total = (ViewGroup) row.findViewById(R.id.total_background);
                total.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                order_holder.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                ImageView curved = (ImageView) row.findViewById(R.id.curve_background);
                int curveId = context.getResources().getIdentifier(curve_list[count % 8], "drawable", context.getPackageName());
                curved.setImageResource(curveId);

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_fragment = getFragmentManager().findFragmentById(R.id.fiber_info_layout);

                        if (fiber_fragment == null) {
                            ViewGroup.LayoutParams layoutParams = fiber_info_layout.getLayoutParams();
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
                                    .add(R.id.fiber_info_layout, fiberInfo)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });

                deliver_linear.addView(row);
                count += 1;
            }
        }

        count = 0;
        for(CO_OB_PurchaseHistory purchase : purchaseHistory) {
            if(purchase.getPurchaseType().equals("P") && purchase.getDelete().equals("F")) {
                final View row = inflater.inflate(R.layout.custom_purchase_row, pickup_linear, false);
                row.setTag(String.valueOf(purchase.getPurchaseID()));

                CO_OB_Fiber fiber = CO_LO_DataCenter.getFiber(purchase.getFiberID());
                TextView fiberName = (TextView) row.findViewById(R.id.fiberInfo);
                fiberName.setText(String.format("%s %dYD", fiber.getFiberName(), purchase.getFiberNumber()));
                TextView fiberPrice = (TextView) row.findViewById(R.id.price);
                fiberPrice.setText(String.format("%d원", fiber.getFiberPrice() * purchase.getFiberNumber()));

                int resId = context.getResources().getIdentifier(fiber.getFiberImage(), "drawable", context.getPackageName());
                ImageView fiberImage = (ImageView) row.findViewById(R.id.fiberImage);
                fiberImage.setImageResource(resId);

                CO_OB_Store store = CO_LO_DataCenter.getStore(purchase.getFiberID());
                TextView storeName = (TextView) row.findViewById(R.id.storeName);
                storeName.setText(store.getStoreName());
                TextView storeLocation = (TextView) row.findViewById(R.id.storeLocation);
                storeLocation.setText(store.getStoreLocation());

                TextView purchaseDate = (TextView) row.findViewById(R.id.purchaseDate);
                java.util.StringTokenizer st = new java.util.StringTokenizer(purchase.getPurchaseTime(), " ");
                //purchaseDate.setText(purchase.getPurchaseTime());
                purchaseDate.setText(st.nextToken());

                ViewGroup btn_cancel = (ViewGroup)row.findViewById(R.id.btn_cancel);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pid = Integer.parseInt(row.getTag().toString());
                        final CO_OB_PurchaseHistory purchase = CO_LO_DataCenter.getPurchase(pid);

                        if (purchase.getCompletion().equals("F")) {
                            CO_LO_DataCenter.deletePurchaseHistory(pid);

                            Toast.makeText(context, "구매 취소되었습니다", Toast.LENGTH_SHORT).show();
                            refreshPurchase();
                        } else {
                            AlertDialog.Builder question = new AlertDialog.Builder(v.getContext());
                            question.setMessage("이미 구매확정 된 상품입니다.\n목록에서 삭제하시겠습니까?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    purchase.setDelete("T");
                                    CO_LO_DataCenter.updatePurchaseHistory(purchase);

                                    Toast.makeText(context, "삭제 되었습니다", Toast.LENGTH_SHORT).show();
                                    refreshPurchase();
                                }
                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });

                            AlertDialog alertDialog = question.create();
                            alertDialog.show();
                        }
                    }
                });

                ViewGroup info = (ViewGroup) row.findViewById(R.id.info_background);
                info.setBackgroundColor(Color.parseColor(background_list[count % 8]));
                ViewGroup total = (ViewGroup) row.findViewById(R.id.total_background);
                total.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                order_holder.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                ImageView curved = (ImageView) row.findViewById(R.id.curve_background);
                int curveId = context.getResources().getIdentifier(curve_list[count % 8], "drawable", context.getPackageName());
                curved.setImageResource(curveId);

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_fragment = getFragmentManager().findFragmentById(R.id.fiber_info_layout);

                        if (fiber_fragment == null) {
                            ViewGroup.LayoutParams layoutParams = fiber_info_layout.getLayoutParams();
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
                                    .add(R.id.fiber_info_layout, fiberInfo)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });

                pickup_linear.addView(row);
                count += 1;
            }
        }

        if (deliver_linear.getChildCount() != 1) {
            deliver_temp.setBackgroundColor(Color.parseColor(background_list[0]));
            order_holder.setBackgroundColor(Color.parseColor(total_background_list[(deliver_linear.getChildCount() - 2) % 8]));
        } else {
            deliver_temp.setBackgroundColor(Color.parseColor(background_list[0]));
            order_holder.setBackgroundColor(Color.parseColor(background_list[0]));
        }

        btn_network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_wishlist.setImageResource(R.drawable.wish_off);
                btn_order.setImageResource(R.drawable.order_off);
                btn_network.setImageResource(R.drawable.network_on);
                wishlist_holder.setVisibility(View.GONE);
                order_holder.setVisibility(View.GONE);
                network_holder.setVisibility(View.VISIBLE);

                btn_designer.setTextColor(Color.parseColor("#3EB5B0"));
                btn_store.setTextColor(Color.parseColor("#FFFFFF"));
                designer_scroll.setVisibility(View.VISIBLE);
                designer1.setBackgroundColor(Color.parseColor("#686868"));
                designer2.setBackgroundColor(Color.parseColor("#5C5C5C"));
                designer3.setBackgroundColor(Color.parseColor("#686868"));
                designer4.setBackgroundColor(Color.parseColor("#4E4E4E"));
                designer5.setBackgroundColor(Color.parseColor("#5C5C5C"));
                designer6.setBackgroundColor(Color.parseColor("#424242"));
                designer7.setBackgroundColor(Color.parseColor("#4E4E4E"));
                designer8.setBackgroundColor(Color.parseColor("#3D3D3D"));
                designer9.setBackgroundColor(Color.parseColor("#424242"));
                store_scroll.setVisibility(View.GONE);
            }
        });

        btn_designer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_designer.setTextColor(Color.parseColor("#3EB5B0"));
                btn_store.setTextColor(Color.parseColor("#FFFFFF"));

                designer_scroll.setVisibility(View.VISIBLE);
                store_scroll.setVisibility(View.GONE);

                designer1.setBackgroundColor(Color.parseColor("#686868"));
                designer2.setBackgroundColor(Color.parseColor("#5C5C5C"));
                designer3.setBackgroundColor(Color.parseColor("#686868"));
                designer4.setBackgroundColor(Color.parseColor("#4E4E4E"));
                designer5.setBackgroundColor(Color.parseColor("#5C5C5C"));
                designer6.setBackgroundColor(Color.parseColor("#424242"));
                designer7.setBackgroundColor(Color.parseColor("#4E4E4E"));
                designer8.setBackgroundColor(Color.parseColor("#3D3D3D"));
                designer9.setBackgroundColor(Color.parseColor("#424242"));
            }
        });

        btn_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_designer.setTextColor(Color.parseColor("#FFFFFF"));
                btn_store.setTextColor(Color.parseColor("#3EB5B0"));

                designer_scroll.setVisibility(View.GONE);
                store_scroll.setVisibility(View.VISIBLE);
            }
        });

        btn_call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Minjung Kwon에게 전화", Toast.LENGTH_SHORT).show();
            }
        });

        btn_call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Jinju Park에게 전화", Toast.LENGTH_SHORT).show();
            }
        });

        btn_call3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Sunwoo Lee에게 전화", Toast.LENGTH_SHORT).show();
            }
        });

        btn_call4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Koeun Kim에게 전화", Toast.LENGTH_SHORT).show();
            }
        });

        count = 0;
        for(CO_OB_Store store : storeList) {
            if(CO_LO_DataCenter.getPurchaseCount(store.getStoreID()) != 0) {
                View row = inflater.inflate(R.layout.custom_store_row, store_linear, false);
                row.setId(store.getStoreID());

                TextView storeName = (TextView) row.findViewById(R.id.storeName);
                storeName.setText(store.getStoreName());
                TextView storeLocation = (TextView) row.findViewById(R.id.storeLocation);
                storeLocation.setText(store.getStoreLocation());
                TextView storeFax = (TextView) row.findViewById(R.id.storeFax);
                storeFax.setText(store.getStoreFax());
                ViewGroup btn_phoneCall = (ViewGroup) row.findViewById(R.id.btn_phonecall);
                TextView storeCount = (TextView) row.findViewById(R.id.orderCount);
                storeCount.setText(String.format("%d 번", CO_LO_DataCenter.getPurchaseCount(store.getStoreID())));
                btn_phoneCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "전화걸기는 아직 지원되지 않습니다", Toast.LENGTH_SHORT).show();
                    }
                });

                CO_OB_Fiber fiber = CO_LO_DataCenter.getFiber(store.getStoreID());
                int resId = context.getResources().getIdentifier(fiber.getFiberImage(), "drawable", context.getPackageName());
                ImageView fiberImage = (ImageView)row.findViewById(R.id.fiberImage);
                fiberImage.setImageResource(resId);

                ViewGroup info = (ViewGroup) row.findViewById(R.id.info_background);
                info.setBackgroundColor(Color.parseColor(background_list[count % 8]));
                ViewGroup total = (ViewGroup) row.findViewById(R.id.total_background);
                total.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                network_holder.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                ImageView curved = (ImageView) row.findViewById(R.id.curve_background);
                int curveId = context.getResources().getIdentifier(curve_list[count % 8], "drawable", context.getPackageName());
                curved.setImageResource(curveId);

                store_linear.addView(row);
                count += 1;
            }
        }

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mi_ac_main, menu);
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

    public static void refreshWishList() {
        wishlist_linear.removeAllViews();
        wishList = CO_LO_DataCenter.getWishList();

        int count = 0;
        for(CO_OB_WishList wish : wishList) {
            CO_OB_Fiber fiber = CO_LO_DataCenter.getFiber(wish.getFiberID());
            View row = inflater.inflate(R.layout.custom_wishlist_row, wishlist_linear, false);
            row.setId(fiber.getFiberID());
            row.setTag(fiber.getFiberID());

            CO_OB_Store store = CO_LO_DataCenter.getStore(fiber.getFiberID());
            TextView storeName = (TextView)row.findViewById(R.id.storeName);
            storeName.setText(store.getStoreName());
            TextView storeLocation = (TextView)row.findViewById(R.id.storeLocation);
            storeLocation.setText(store.getStoreLocation());
            TextView fiberName = (TextView)row.findViewById(R.id.fiberName);
            fiberName.setText(fiber.getFiberName());
            TextView fiberPrice = (TextView)row.findViewById(R.id.fiberPrice);
            fiberPrice.setText(String.format("%d원/YD", fiber.getFiberPrice()));
            int resId = context.getResources().getIdentifier(fiber.getFiberImage(), "drawable", context.getPackageName());
            ImageView fiberImage = (ImageView)row.findViewById(R.id.fiberImage);
            fiberImage.setImageResource(resId);

            ViewGroup info = (ViewGroup)row.findViewById(R.id.info_background);
            info.setBackgroundColor(Color.parseColor(background_list[count % 8]));
            ViewGroup total = (ViewGroup)row.findViewById(R.id.total_background);
            total.setBackgroundColor(Color.parseColor(total_background_list[count%8]));
            wishlist_holder.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
            ImageView curved = (ImageView)row.findViewById(R.id.curve_background);
            int curveId = context.getResources().getIdentifier(curve_list[count % 8], "drawable", context.getPackageName());
            curved.setImageResource(curveId);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fiber_fragment = fragmentManager.findFragmentById(R.id.fiber_info_layout);

                    if(fiber_fragment == null) {
                        ViewGroup.LayoutParams layoutParams = fiber_info_layout.getLayoutParams();
                        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                        Bundle bundle = new Bundle();
                        bundle.putString("id", v.getTag().toString());

                        CO_FR_FiberInfo fiberInfo = new CO_FR_FiberInfo();
                        fiberInfo.setArguments(bundle);

                        fragmentManager.beginTransaction()
                                .setCustomAnimations(R.animator.search_up,
                                        R.animator.search_down,
                                        R.animator.search_up,
                                        R.animator.search_down)
                                .add(R.id.fiber_info_layout, fiberInfo)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });

            wishlist_linear.addView(row);
            count += 1;
        }
        if(count == 0) {
            wishlist_holder.setBackgroundColor(Color.parseColor(total_background_list[count]));
        }
    }

    public static void refreshPurchase() {
        deliver_linear.removeAllViews();
        pickup_linear.removeAllViews();
        deliver_linear.addView(deliver_temp);
        pickup_linear.addView(pickup_temp);
        purchaseHistory = CO_LO_DataCenter.getPurchaseHistory();

        int count = 0;
        for(CO_OB_PurchaseHistory purchase : purchaseHistory) {
            if(purchase.getPurchaseType().equals("D") && purchase.getDelete().equals("F")) {
                final View row = inflater.inflate(R.layout.custom_purchase_row, deliver_linear, false);
                row.setTag(String.valueOf(purchase.getPurchaseID()));

                CO_OB_Fiber fiber = CO_LO_DataCenter.getFiber(purchase.getFiberID());
                TextView fiberName = (TextView) row.findViewById(R.id.fiberInfo);
                fiberName.setText(String.format("%s %dYD", fiber.getFiberName(), purchase.getFiberNumber()));
                TextView fiberPrice = (TextView) row.findViewById(R.id.price);
                fiberPrice.setText(String.format("%d원", fiber.getFiberPrice() * purchase.getFiberNumber()));

                int resId = context.getResources().getIdentifier(fiber.getFiberImage(), "drawable", context.getPackageName());
                ImageView fiberImage = (ImageView) row.findViewById(R.id.fiberImage);
                fiberImage.setImageResource(resId);

                CO_OB_Store store = CO_LO_DataCenter.getStore(purchase.getFiberID());
                TextView storeName = (TextView) row.findViewById(R.id.storeName);
                storeName.setText(store.getStoreName());
                TextView storeLocation = (TextView) row.findViewById(R.id.storeLocation);
                storeLocation.setText(store.getStoreLocation());

                TextView purchaseDate = (TextView) row.findViewById(R.id.purchaseDate);
                java.util.StringTokenizer st = new java.util.StringTokenizer(purchase.getPurchaseTime(), " ");
                //purchaseDate.setText(purchase.getPurchaseTime());
                purchaseDate.setText(st.nextToken());

                ViewGroup btn_cancel = (ViewGroup)row.findViewById(R.id.btn_cancel);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pid = Integer.parseInt(row.getTag().toString());
                        final CO_OB_PurchaseHistory purchase = CO_LO_DataCenter.getPurchase(pid);

                        if(purchase.getCompletion().equals("F")) {
                            CO_LO_DataCenter.deletePurchaseHistory(pid);

                            Toast.makeText(context, "구매 취소되었습니다", Toast.LENGTH_SHORT).show();
                            refreshPurchase();
                        } else {
                            AlertDialog.Builder question = new AlertDialog.Builder(v.getContext());
                            question.setMessage("이미 구매확정 된 상품입니다.\n목록에서 삭제하시겠습니까?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    purchase.setDelete("T");
                                    CO_LO_DataCenter.updatePurchaseHistory(purchase);

                                    Toast.makeText(context, "삭제 되었습니다", Toast.LENGTH_SHORT).show();
                                    refreshPurchase();
                                }
                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });

                            AlertDialog alertDialog = question.create();
                            alertDialog.show();
                        }
                    }
                });

                ViewGroup info = (ViewGroup) row.findViewById(R.id.info_background);
                info.setBackgroundColor(Color.parseColor(background_list[count % 8]));
                ViewGroup total = (ViewGroup) row.findViewById(R.id.total_background);
                total.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                order_holder.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                ImageView curved = (ImageView) row.findViewById(R.id.curve_background);
                int curveId = context.getResources().getIdentifier(curve_list[count % 8], "drawable", context.getPackageName());
                curved.setImageResource(curveId);

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_fragment = fragmentManager.findFragmentById(R.id.fiber_info_layout);

                        if (fiber_fragment == null) {
                            ViewGroup.LayoutParams layoutParams = fiber_info_layout.getLayoutParams();
                            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                            Bundle bundle = new Bundle();
                            bundle.putString("id", v.getTag().toString());

                            CO_FR_FiberInfo fiberInfo = new CO_FR_FiberInfo();
                            fiberInfo.setArguments(bundle);

                            fragmentManager.beginTransaction()
                                    .setCustomAnimations(R.animator.search_up,
                                            R.animator.search_down,
                                            R.animator.search_up,
                                            R.animator.search_down)
                                    .add(R.id.fiber_info_layout, fiberInfo)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });

                deliver_linear.addView(row);
                count += 1;
            }
        }

        count = 0;
        for(CO_OB_PurchaseHistory purchase : purchaseHistory) {
            if(purchase.getPurchaseType().equals("P") && purchase.getDelete().equals("F")) {
                final View row = inflater.inflate(R.layout.custom_purchase_row, pickup_linear, false);
                row.setTag(String.valueOf(purchase.getPurchaseID()));

                CO_OB_Fiber fiber = CO_LO_DataCenter.getFiber(purchase.getFiberID());
                TextView fiberName = (TextView) row.findViewById(R.id.fiberInfo);
                fiberName.setText(String.format("%s %dYD", fiber.getFiberName(), purchase.getFiberNumber()));
                TextView fiberPrice = (TextView) row.findViewById(R.id.price);
                fiberPrice.setText(String.format("%d원", fiber.getFiberPrice() * purchase.getFiberNumber()));

                int resId = context.getResources().getIdentifier(fiber.getFiberImage(), "drawable", context.getPackageName());
                ImageView fiberImage = (ImageView) row.findViewById(R.id.fiberImage);
                fiberImage.setImageResource(resId);

                CO_OB_Store store = CO_LO_DataCenter.getStore(purchase.getFiberID());
                TextView storeName = (TextView) row.findViewById(R.id.storeName);
                storeName.setText(store.getStoreName());
                TextView storeLocation = (TextView) row.findViewById(R.id.storeLocation);
                storeLocation.setText(store.getStoreLocation());

                TextView purchaseDate = (TextView) row.findViewById(R.id.purchaseDate);
                java.util.StringTokenizer st = new java.util.StringTokenizer(purchase.getPurchaseTime(), " ");
                //purchaseDate.setText(purchase.getPurchaseTime());
                purchaseDate.setText(st.nextToken());

                ViewGroup btn_cancel = (ViewGroup)row.findViewById(R.id.btn_cancel);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pid = Integer.parseInt(row.getTag().toString());
                        final CO_OB_PurchaseHistory purchase = CO_LO_DataCenter.getPurchase(pid);

                        if (purchase.getCompletion().equals("F")) {
                            CO_LO_DataCenter.deletePurchaseHistory(pid);

                            Toast.makeText(context, "구매 취소되었습니다", Toast.LENGTH_SHORT).show();
                            refreshPurchase();
                        } else {
                            AlertDialog.Builder question = new AlertDialog.Builder(v.getContext());
                            question.setMessage("이미 구매확정 된 상품입니다.\n목록에서 삭제하시겠습니까?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    purchase.setDelete("T");
                                    CO_LO_DataCenter.updatePurchaseHistory(purchase);

                                    Toast.makeText(context, "삭제 되었습니다", Toast.LENGTH_SHORT).show();
                                    refreshPurchase();
                                }
                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });

                            AlertDialog alertDialog = question.create();
                            alertDialog.show();
                        }
                    }
                });

                ViewGroup info = (ViewGroup) row.findViewById(R.id.info_background);
                info.setBackgroundColor(Color.parseColor(background_list[count % 8]));
                ViewGroup total = (ViewGroup) row.findViewById(R.id.total_background);
                total.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                order_holder.setBackgroundColor(Color.parseColor(total_background_list[count % 8]));
                ImageView curved = (ImageView) row.findViewById(R.id.curve_background);
                int curveId = context.getResources().getIdentifier(curve_list[count % 8], "drawable", context.getPackageName());
                curved.setImageResource(curveId);

                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_fragment = fragmentManager.findFragmentById(R.id.fiber_info_layout);

                        if (fiber_fragment == null) {
                            ViewGroup.LayoutParams layoutParams = fiber_info_layout.getLayoutParams();
                            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

                            Bundle bundle = new Bundle();
                            bundle.putString("id", v.getTag().toString());

                            CO_FR_FiberInfo fiberInfo = new CO_FR_FiberInfo();
                            fiberInfo.setArguments(bundle);

                            fragmentManager.beginTransaction()
                                    .setCustomAnimations(R.animator.search_up,
                                            R.animator.search_down,
                                            R.animator.search_up,
                                            R.animator.search_down)
                                    .add(R.id.fiber_info_layout, fiberInfo)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });

                pickup_linear.addView(row);
                count += 1;
            }
        }

        if(deliver_scroll.getVisibility() == View.VISIBLE) {
            if (deliver_linear.getChildCount() != 1) {
                deliver_temp.setBackgroundColor(Color.parseColor(background_list[0]));
                order_holder.setBackgroundColor(Color.parseColor(total_background_list[(deliver_linear.getChildCount() - 2) % 8]));
            } else {
                deliver_temp.setBackgroundColor(Color.parseColor(background_list[0]));
                order_holder.setBackgroundColor(Color.parseColor(background_list[0]));
            }
        } else {
            if (pickup_linear.getChildCount() != 1) {
                pickup_temp.setBackgroundColor(Color.parseColor(background_list[0]));
                order_holder.setBackgroundColor(Color.parseColor(total_background_list[(pickup_linear.getChildCount() - 2) % 8]));
            } else {
                pickup_temp.setBackgroundColor(Color.parseColor(background_list[0]));
                order_holder.setBackgroundColor(Color.parseColor(background_list[0]));
            }
        }
    }
}
