package com.sia.siaandroidapp.java.ui.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 4;
    private List<Fragment> fragments = new ArrayList<>();


    public MyPagerAdapter(List<Fragment> fragments, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragments = fragments;

    }

    // Returns total number of pages
    @Override
    public int getCount() {
//        return NUM_ITEMS;
        return fragments.size();

    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

//        switch (position) {
//            case 0: // Fragment # 0 - This will show FirstFragment
//                return KnowUBetter1Fragment.newInstance(0, "Page # 1");
//            case 1: // Fragment # 0 - This will show FirstFragment different title
//                return KnowUBetter2Fragment.newInstance(1, "Page # 2");
//            case 2: // Fragment # 1 - This will show SecondFragment
//                return KnowUBetter3Fragment.newInstance(2, "Page # 3");
//            case 3:
//                return KnowUBetter4Fragment.newInstance(2, "Page # 3");
//            default:
//                return null;
//        }

        return fragments.get(position);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}