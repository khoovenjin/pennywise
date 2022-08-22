package com.budgettracking.pennywise.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.budgettracking.pennywise.R;
import com.budgettracking.pennywise.ui.login.WelcomePage;

public class WelcomePagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;

    public WelcomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        WelcomePage tp = null;
        switch (position) {
            case 0:
                tp = WelcomePage.newInstance(R.layout.layout_welcome_first);
                break;
            case 1:
                tp = WelcomePage.newInstance(R.layout.layout_welcome_second);
                break;
            case 2:
                tp = WelcomePage.newInstance(R.layout.layout_welcome_third);
                break;
        }
        return tp;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

}