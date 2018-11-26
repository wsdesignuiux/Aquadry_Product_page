package com.wolfsoft.aquadry_product_page;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.ProductsPagerAdapter;
import adapter.TimingsAdapter;


public class MainActivity extends AppCompatActivity {

    private ProductsPagerAdapter productsPagerAdapter;
    private TabLayout customTab;
    private ViewPager pager;

    Typeface mTypeface;


    private Dialog bottomDialog;
    private TextView tvToday,tvTomorrow;
    private LinearLayout linearDialog;
    private FrameLayout frameOk;
    private RecyclerView rvTimings;
    private TimingsAdapter timingsAdapter;

    private String[] NAMES_TIMMING = {"9 TO 10 am","10 to 11 am","11 to 12 am","12 to 1 pm","1 to 2 pm","2 to 3 pm","3 to 4 pm"};
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private ImageView btn;

    Animation slideUpAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        slideUpAnimation= AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_up_linear);

        for (int i=0; i<NAMES_TIMMING.length ; i++){

            stringArrayList.add(NAMES_TIMMING[i]);
        }




        bottomDialog  = new Dialog(MainActivity.this,R.style.BottomDialog);
        bottomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        bottomDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
        layoutParams.copyFrom(bottomDialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.BOTTOM;
        bottomDialog.getWindow().setAttributes(layoutParams);
        bottomDialog.setCancelable(true);
        bottomDialog.setContentView(R.layout.dilaog_bottom);
        tvToday = (TextView)bottomDialog.findViewById(R.id.tvToday);
        tvTomorrow= (TextView)bottomDialog.findViewById(R.id.tvTomorrow);
        frameOk= (FrameLayout) bottomDialog.findViewById(R.id.frameOk);
        linearDialog= (LinearLayout) bottomDialog.findViewById(R.id.linearDialog);
        rvTimings = (RecyclerView) bottomDialog.findViewById(R.id.rvTimings);
        RecyclerView.LayoutManager mLayoutManagerHappyHours = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        rvTimings.setLayoutManager(mLayoutManagerHappyHours);
        rvTimings.setNestedScrollingEnabled(false);
        rvTimings.setItemAnimator(new DefaultItemAnimator());
        rvTimings.setHasFixedSize(false);

        timingsAdapter = new TimingsAdapter(MainActivity.this, stringArrayList);
        rvTimings.setAdapter(timingsAdapter);

        btn = (ImageView)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomDialog.show();

                linearDialog.startAnimation(slideUpAnimation);
            }
        });

        frameOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomDialog.dismiss();
                Intent it = new Intent(MainActivity.this, DetailPageActivity.class);
                startActivity(it);
            }
        });


        tvToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvToday.setBackgroundResource(R.drawable.round_button_dark);
                tvToday.setTextColor(Color.parseColor("#ffffff"));
                tvTomorrow.setBackgroundResource(R.drawable.round_button_light);
                tvTomorrow.setTextColor(Color.parseColor("#28a7fc"));


            }
        });


        tvTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tvTomorrow.setBackgroundResource(R.drawable.round_button_dark);
                tvTomorrow.setTextColor(Color.parseColor("#ffffff"));
                tvToday.setBackgroundResource(R.drawable.round_button_light);
                tvToday.setTextColor(Color.parseColor("#28a7fc"));

            }
        });



        pager = (ViewPager)findViewById(R.id.pager);
        customTab= (TabLayout) findViewById(R.id.customTab);


        customTab.addTab(customTab.newTab().setText("Men's Wears"));
        customTab.addTab(customTab.newTab().setText("Women's Wears"));
        customTab.addTab(customTab.newTab().setText("HouseHolds"));

        mTypeface = Typeface.createFromAsset(this.getAssets(), "myfonts/Roboto-Medium.ttf");
        ViewGroup vg = (ViewGroup) customTab.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                }
            }
        }


        productsPagerAdapter = new ProductsPagerAdapter
                (getSupportFragmentManager(), customTab.getTabCount());
        pager.setAdapter(productsPagerAdapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(customTab));
        pager.setOffscreenPageLimit(customTab.getTabCount());

        customTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });









    }}
