package com.mobiversal.practica.proiectpractica.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Lenovo on 10.07.2017.
 */

public class ViewPagerAdapterMain extends FragmentPagerAdapter {

    private Fragment[] fragments;



    public ViewPagerAdapterMain(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }


}
