package com.vectorinc.ezinwavictor.gpa.Year_Six.Tab_Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vectorinc.ezinwavictor.gpa.Year_Six.first_semester_yr6;
import com.vectorinc.ezinwavictor.gpa.Year_Six.second_semester_yr6;

public class PageAdapter extends FragmentPagerAdapter {
    int numberofTabs;

    public PageAdapter(FragmentManager fm, int numberofTabs) {
        super(fm);
        this.numberofTabs = numberofTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                first_semester_yr6 tab1 = new first_semester_yr6();
                return tab1;
            case 1:
                second_semester_yr6 tab2 = new second_semester_yr6();
                return tab2;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return numberofTabs;
    }
}
