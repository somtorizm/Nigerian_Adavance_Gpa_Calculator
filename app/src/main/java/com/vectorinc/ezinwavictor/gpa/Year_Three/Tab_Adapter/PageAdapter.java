package com.vectorinc.ezinwavictor.gpa.Year_Three.Tab_Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vectorinc.ezinwavictor.gpa.Year_Three.first_semester_yr3;
import com.vectorinc.ezinwavictor.gpa.Year_Three.second_semester_yr3;


public class PageAdapter extends FragmentPagerAdapter {
    int numberoftabs;

    public PageAdapter(FragmentManager fm, int NumofTabs) {
        super(fm);
        this.numberoftabs = NumofTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                first_semester_yr3 tab1 = new first_semester_yr3();
                return tab1;
            case 1:
                second_semester_yr3 tab2 = new second_semester_yr3();
                return tab2;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numberoftabs;
    }
}
