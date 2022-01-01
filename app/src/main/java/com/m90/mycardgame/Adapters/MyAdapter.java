package com.m90.mycardgame.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.m90.mycardgame.Fragments.FourCardResultFragment;
import com.m90.mycardgame.Fragments.ThirteenCardResultFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FourCardResultFragment fourCardResultFragment = new FourCardResultFragment();
                return fourCardResultFragment;
            case 1:
                ThirteenCardResultFragment thirteenCardResultFragment = new ThirteenCardResultFragment();
                return thirteenCardResultFragment;
            default:
                return null;

        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

}
