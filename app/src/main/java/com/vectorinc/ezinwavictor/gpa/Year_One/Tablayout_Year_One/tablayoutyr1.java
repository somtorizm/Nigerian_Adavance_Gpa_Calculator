package com.vectorinc.ezinwavictor.gpa.Year_One.Tablayout_Year_One;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;
import android.widget.TextView;

import com.vectorinc.ezinwavictor.gpa.Year_One.Tab_Adapter.PagerAdapter;
import com.vectorinc.ezinwavictor.gpa.R;

public class tablayoutyr1 extends AppCompatActivity  {

    Toolbar toolbar;
    TabLayout tabLayout;
    Spinner spinner1;
    TextView editText;
    String coursename;

    Intent intent;
    private  long BackPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        intent = new Intent(this, tablayoutyr1.class);
        coursename = intent.getStringExtra("coursename");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        initInstancesDrawer();
        setTitle(null);



    }



    private void initInstancesDrawer() {


        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("First Semester"));
        tabLayout.addTab(tabLayout.newTab().setText("Second Semester"));
        final ViewPager viewPager = findViewById(R.id.view_pager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {

        if (BackPressedTime + 2000 > System.currentTimeMillis()){
            finish();
        }
        else{
            Snackbar.make(findViewById(R.id.drawer_design_support_layout), "BackPress Again to Move Back",Snackbar.LENGTH_SHORT).show();
        }
        BackPressedTime = System.currentTimeMillis();


    }
}
