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

package com.dangercloz.CO;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dangercloz.BO.BO_AC_Main;
import com.dangercloz.BO.BO_FR_PocketDetail;
import com.dangercloz.MI.MI_AC_Main;
import com.dangercloz.SE.SE_AC_Main;
import com.dangercloz.pocketswatch.R;

import java.util.Date;
import java.text.SimpleDateFormat;

public class CO_FR_FiberInfo extends Fragment {

    Context context;
    static CO_OB_Fiber currentFiber;
    float touchY, touchX;
    static int scroll_count = 0;
    static ImageButton btn_wishlist;
    static LayoutInflater inflater_temp;
    static LinearLayout color_layout;
    static ImageView fiber_image;

    public CO_FR_FiberInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_co_fr_fiber_info, container, false);

        context = v.getContext();
        inflater_temp = inflater;
        currentFiber = CO_LO_DataCenter.getFiber(Integer.parseInt(getArguments().getString("id")));
        btn_wishlist = (ImageButton)v.findViewById(R.id.btn_wishlist);
        color_layout = (LinearLayout)v.findViewById(R.id.color_layout);

        final TextView btn_detail = (TextView)v.findViewById(R.id.btn_detail);
        final TextView btn_property = (TextView)v.findViewById(R.id.btn_property);
        final TextView btn_store = (TextView)v.findViewById(R.id.btn_store);
        final TextView btn_reference = (TextView)v.findViewById(R.id.btn_reference);
        final TextView fiber_color_count = (TextView)v.findViewById(R.id.fiber_color_count);

        final RelativeLayout detail_layout = (RelativeLayout)v.findViewById(R.id.detail_layout);
        final RelativeLayout property_layout = (RelativeLayout)v.findViewById(R.id.property_layout);
        final RelativeLayout store_layout = (RelativeLayout)v.findViewById(R.id.store_layout);
        final RelativeLayout reference_layout = (RelativeLayout)v.findViewById(R.id.reference_layout);

        fiber_image = (ImageView)v.findViewById(R.id.fiber_image);

        ImageButton btn_matching = (ImageButton)v.findViewById(R.id.btn_matching);
        btn_matching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SE_AC_Main.class);
                intent.putExtra("fiberID", currentFiber.getFiberID());
                startActivity(intent);
                getActivity().finish();
            }
        });

        View spacing = null;
        View spacing2 = null, spacing3 = null, spacing4 = null, spacing5 = null;
        View blue, green, black, red, brown, brown2, brown3;
        spacing = inflater_temp.inflate(R.layout.custom_color_space, color_layout, false);
        spacing2 = inflater_temp.inflate(R.layout.custom_color_space, color_layout, false);
        spacing3 = inflater_temp.inflate(R.layout.custom_color_space, color_layout, false);
        spacing4 = inflater_temp.inflate(R.layout.custom_color_space, color_layout, false);
        spacing5 = inflater_temp.inflate(R.layout.custom_color_space, color_layout, false);
        switch(currentFiber.getFiberID()) {
            case 108:
                fiber_color_count.setText(String.format("총 2개의 색상"));

                black = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                black.setBackgroundResource(context.getResources().getIdentifier("custom_color_black", "drawable", context.getPackageName()));
                black.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("maple_dia_blackori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(black);

                color_layout.addView(spacing);

                blue = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                blue.setBackgroundResource(context.getResources().getIdentifier("custom_color_blue", "drawable", context.getPackageName()));
                blue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("maple_dia_blueori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(blue);
                break;
            case 111:
                fiber_color_count.setText(String.format("총 2개의 색상"));

                green = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                green.setBackgroundResource(context.getResources().getIdentifier("custom_color_green", "drawable", context.getPackageName()));
                green.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("pucanu_greenori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(green);

                color_layout.addView(spacing);

                red = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                red.setBackgroundResource(context.getResources().getIdentifier("custom_color_red", "drawable", context.getPackageName()));
                red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("pucanu_redori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(red);
                break;
            case 109:
                fiber_color_count.setText(String.format("총 3개의 색상"));

                blue = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                blue.setBackgroundResource(context.getResources().getIdentifier("custom_color_blue", "drawable", context.getPackageName()));
                blue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("pentagon_blueori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(blue);

                color_layout.addView(spacing);

                green = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                green.setBackgroundResource(context.getResources().getIdentifier("custom_color_green", "drawable", context.getPackageName()));
                green.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("pentagon_greenori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(green);

                color_layout.addView(spacing2);

                red = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                red.setBackgroundResource(context.getResources().getIdentifier("custom_color_red", "drawable", context.getPackageName()));
                red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("pentagon_redori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(red);

                break;
            case 117:
                fiber_color_count.setText(String.format("총 5개의 색상"));

                brown = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                brown.setBackgroundResource(context.getResources().getIdentifier("custom_color_brown", "drawable", context.getPackageName()));
                brown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("bercley_brownori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(brown);

                color_layout.addView(spacing2);

                black = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                black.setBackgroundResource(context.getResources().getIdentifier("custom_color_black", "drawable", context.getPackageName()));
                black.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("bercley_blackori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(black);

                color_layout.addView(spacing3);

                blue = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                blue.setBackgroundResource(context.getResources().getIdentifier("custom_color_blue", "drawable", context.getPackageName()));
                blue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("bercley_blueori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(blue);

                color_layout.addView(spacing4);

                brown2 = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                brown2.setBackgroundResource(context.getResources().getIdentifier("custom_color_brown2", "drawable", context.getPackageName()));
                brown2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("bercley_brown2ori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(brown2);

                color_layout.addView(spacing5);

                brown3 = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                brown3.setBackgroundResource(context.getResources().getIdentifier("custom_color_brown3", "drawable", context.getPackageName()));
                brown3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("bercley_brown3ori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(brown3);
                break;
            case 119:
                fiber_color_count.setText(String.format("총 3개의 색상"));

                green = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                green.setBackgroundResource(context.getResources().getIdentifier("custom_color_green", "drawable", context.getPackageName()));
                green.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("pupency_greenori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(green);

                color_layout.addView(spacing);

                black = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                black.setBackgroundResource(context.getResources().getIdentifier("custom_color_black", "drawable", context.getPackageName()));
                black.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("pupency_blackori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(black);

                color_layout.addView(spacing2);

                brown = inflater_temp.inflate(R.layout.custom_color_cell, color_layout, false);
                brown.setBackgroundResource(context.getResources().getIdentifier("custom_color_brown", "drawable", context.getPackageName()));
                brown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fiber_image.setImageResource(context.getResources().getIdentifier("pupency_brownori", "drawable", context.getPackageName()));
                    }
                });
                color_layout.addView(brown);
                break;
            default:
                break;
        }

        if(CO_LO_DataCenter.wishListExists(currentFiber.getFiberID()) == null) {
            btn_wishlist.setImageResource(R.drawable.wish_off);
        } else {
            btn_wishlist.setImageResource(R.drawable.wish_on);
        }

        btn_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CO_OB_WishList wish = CO_LO_DataCenter.wishListExists(currentFiber.getFiberID());

                if(wish != null) {
                    CO_LO_DataCenter.deleteWishlist(currentFiber.getFiberID());
                    Toast.makeText(v.getContext(), "위시리스트에서 제거되었습니다", Toast.LENGTH_SHORT).show();

                    btn_wishlist.setImageResource(R.drawable.wish_off);

                    if(MI_AC_Main.initialized != null)
                        MI_AC_Main.refreshWishList();
                } else {
                    CO_LO_DataCenter.addToWishlist(currentFiber.getFiberID());
                    Toast.makeText(v.getContext(), "위시리스트에 추가되었습니다", Toast.LENGTH_SHORT).show();

                    btn_wishlist.setImageResource(R.drawable.wish_on);
                }
            }
        });

        ImageButton btn_purchase = (ImageButton)v.findViewById(R.id.btn_purchase);
        btn_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder selectType = new AlertDialog.Builder(v.getContext());
                selectType.setMessage("어떤 방식으로 구매하시겠습니까?").setCancelable(false).setPositiveButton("배송", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String purchaseTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
                        CO_LO_DataCenter.addPurchaseHistory(currentFiber.getFiberID(), 1, purchaseTime, "D", "F", purchaseTime, "F");

                        Toast.makeText(context, "정상적으로 구매되었습니다", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("픽업", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String purchaseTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
                        CO_LO_DataCenter.addPurchaseHistory(currentFiber.getFiberID(), 1, purchaseTime, "P", "F", purchaseTime, "F");

                        Toast.makeText(context, "정상적으로 구매되었습니다", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = selectType.create();
                alertDialog.show();
            }
        });

        ImageView btn_close = (ImageView)v.findViewById(R.id.btn_close_info);
        btn_close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (touchY + 20 > event.getY()) {
                            if (getFragmentManager().getBackStackEntryCount() == 1) {
                                getFragmentManager().popBackStack();
                                BO_AC_Main.grid_holder.setVisibility(View.VISIBLE);
                            } else {
                                getFragmentManager().popBackStack();
                                BO_FR_PocketDetail.item_holder.setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                }
                return true;
            }
        });

        TextView fiber_name_textView = (TextView)v.findViewById(R.id.fiber_name_textview);
        fiber_name_textView.setText(currentFiber.getFiberName());

        String image_string = String.format("%sori", currentFiber.getFiberImage());
        int resId = v.getContext().getResources().getIdentifier(image_string, "drawable", v.getContext().getPackageName());
        fiber_image.setImageResource(resId);

        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail_layout.setVisibility(View.VISIBLE);
                property_layout.setVisibility(View.GONE);
                store_layout.setVisibility(View.GONE);
                reference_layout.setVisibility(View.GONE);

                btn_detail.setTextColor(Color.parseColor("#3EB5B0"));
                btn_property.setTextColor(Color.parseColor("#FFFFFF"));
                btn_store.setTextColor(Color.parseColor("#FFFFFF"));
                btn_reference.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });

        btn_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail_layout.setVisibility(View.GONE);
                property_layout.setVisibility(View.VISIBLE);
                store_layout.setVisibility(View.GONE);
                reference_layout.setVisibility(View.GONE);

                btn_detail.setTextColor(Color.parseColor("#FFFFFF"));
                btn_property.setTextColor(Color.parseColor("#3EB5B0"));
                btn_store.setTextColor(Color.parseColor("#FFFFFF"));
                btn_reference.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });

        btn_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail_layout.setVisibility(View.GONE);
                property_layout.setVisibility(View.GONE);
                store_layout.setVisibility(View.VISIBLE);
                reference_layout.setVisibility(View.GONE);

                btn_detail.setTextColor(Color.parseColor("#FFFFFF"));
                btn_property.setTextColor(Color.parseColor("#FFFFFF"));
                btn_store.setTextColor(Color.parseColor("#3EB5B0"));
                btn_reference.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });

        btn_reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail_layout.setVisibility(View.GONE);
                property_layout.setVisibility(View.GONE);
                store_layout.setVisibility(View.GONE);
                reference_layout.setVisibility(View.VISIBLE);

                btn_detail.setTextColor(Color.parseColor("#FFFFFF"));
                btn_property.setTextColor(Color.parseColor("#FFFFFF"));
                btn_store.setTextColor(Color.parseColor("#FFFFFF"));
                btn_reference.setTextColor(Color.parseColor("#3EB5B0"));
            }
        });

        TextView item_text = (TextView)v.findViewById(R.id.item_text);
        item_text.setText(currentFiber.getFiberType());

        TextView mixture_text = (TextView)v.findViewById(R.id.mixture_text);
        mixture_text.setText(currentFiber.getFiberMixture());

        TextView density_text = (TextView)v.findViewById(R.id.density_text);
        density_text.setText(currentFiber.getFiberDensity());

        TextView thick_text = (TextView)v.findViewById(R.id.thick_text);
        thick_text.setText(String.format("%d수", currentFiber.getFiberThick()));

        LinearLayout tag_scroll = (LinearLayout)v.findViewById(R.id.hash_holder_scroll);
        for(String tag : CO_LO_DataCenter.getHashListofItem(currentFiber.getFiberID())) {
            TextView tag_text = new TextView(v.getContext());
            tag_text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tag_text.setGravity(Gravity.CENTER);
            tag_text.setTextColor(Color.parseColor("#FFFFFF"));
            tag_text.setText(tag);
            tag_text.setPadding(0, 0, 25, 0);

            tag_scroll.addView(tag_text);
        }

        CO_OB_Store currentStore = CO_LO_DataCenter.getStore(currentFiber.getFiberID());
        TextView store_name = (TextView)v.findViewById(R.id.store_name);
        store_name.setText(currentStore.getStoreName());

        TextView store_phone = (TextView)v.findViewById(R.id.store_phone);
        store_phone.setText(currentStore.getStoreTelephone());

        TextView store_fax = (TextView)v.findViewById(R.id.store_fax);
        store_fax.setText(currentStore.getStoreFax());

        TextView store_address = (TextView)v.findViewById(R.id.store_address);
        store_address.setText(String.format("서울시 종로6가 266 동대문종합상가 %s", currentStore.getStoreLocation()));

        TextView store_open = (TextView)v.findViewById(R.id.store_open);
        store_open.setText(currentStore.getStoreOpenTime());

        LinearLayout reference_scroll = (LinearLayout)v.findViewById(R.id.reference_image);
        ImageView ref1 = new ImageView(v.getContext());
        ref1.setImageResource(R.drawable.reference1);
        ref1.setAdjustViewBounds(true);
        ref1.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ref1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        reference_scroll.addView(ref1);

        ImageView ref2 = new ImageView(v.getContext());
        ref2.setImageResource(R.drawable.reference2);
        ref2.setAdjustViewBounds(true);
        ref2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ref2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        reference_scroll.addView(ref2);

        ImageView ref3 = new ImageView(v.getContext());
        ref3.setImageResource(R.drawable.reference3);
        ref3.setAdjustViewBounds(true);
        ref3.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ref3.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        reference_scroll.addView(ref3);

        ImageView ref4 = new ImageView(v.getContext());
        ref4.setImageResource(R.drawable.reference4);
        ref4.setAdjustViewBounds(true);
        ref4.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ref4.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        reference_scroll.addView(ref4);

        final HorizontalScrollView hori_scroll = (HorizontalScrollView)v.findViewById(R.id.hori_scroll);
        hori_scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(touchX - 50 > event.getX()) {
                            if(scroll_count != 3) {
                                hori_scroll.smoothScrollBy(790, 0);
                                scroll_count++;
                            }
                        } else if(touchX + 50 < event.getX()) {
                            if(scroll_count != 0) {
                                hori_scroll.smoothScrollBy(-790, 0);
                                scroll_count--;
                            }
                        }
                        break;
                }

                return true;
            }
        });

        return v;
    }

    public static void setWishlistListener(View v) {
        CO_OB_WishList wish = CO_LO_DataCenter.wishListExists(currentFiber.getFiberID());
        if(wish != null) {
            btn_wishlist.setImageResource(R.drawable.wish_on);

            btn_wishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CO_LO_DataCenter.deleteWishlist(currentFiber.getFiberID());
                    Toast.makeText(v.getContext(), "위시리스트에서 제거되었습니다", Toast.LENGTH_SHORT).show();

                    setWishlistListener(v);
                }
            });
        } else {
            btn_wishlist.setImageResource(R.drawable.wish_off);

            btn_wishlist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CO_LO_DataCenter.addToWishlist(currentFiber.getFiberID());
                    Toast.makeText(v.getContext(), "위시리스트에 추가되었습니다", Toast.LENGTH_SHORT).show();

                    setWishlistListener(v);
                }
            });
        }
    }


}
