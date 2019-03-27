package com.vectorinc.ezinwavictor.gpa.Year_One.Tab_Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vectorinc.ezinwavictor.gpa.Year_One.firstsemesteryr1;
import com.vectorinc.ezinwavictor.gpa.Year_One.secondsemesteryr1;

public class PagerAdapter extends FragmentPagerAdapter {
    int numberoftabs;

    public PagerAdapter(FragmentManager fm,int NumofTabs) {
        super(fm);
        this.numberoftabs = NumofTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                firstsemesteryr1 tab1 = new firstsemesteryr1();
                return tab1;
            case 1:
                secondsemesteryr1 tab2 = new secondsemesteryr1();
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
